package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.User;
import dto.ToDoDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import service.TodoEditService;
import service.TodoEditViewService;
import validation.Validation;

@WebServlet("/schedEdit")
//★カレンダーの予定編集★
//★モーダル画面に予定を表示する→odoEditViewService
//★モーダル画面からの予定編集および成否を返す→TodoEditService

@MultipartConfig
//multipart/form-dataリクエストの処理を許可する
//ブラウザのフォーム送信で enctype="multipart/form-data" が使われる（編集・削除フォームなど）為
public class SchedEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
 //request.getPart("フィールド名") で取得した Part を 文字列に変換するヘルパーメソッド
    private String getValueFromPart(Part part) throws IOException {
    		InputStream is = null;
    		BufferedReader reader = null;
    		StringBuilder value = new StringBuilder();

    	try {
    	    is = part.getInputStream();
    	    //送信されたデータ）を バイト単位で読むためのストリーム を取得
    	    reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
    	    //バイト列を 文字（UTF-8）に変換
    	    char[] buffer = new char[1024];
    	    //文字を 1024文字ずつ 一時的に格納する配列を作成
    	    int length;
    	    while ((length = reader.read(buffer)) != -1) {
    	    //buffer に文字を読み込み、読み込んだ文字数を返す
    	    //読み込む文字がもうなければ -1 を返してループ終了
    	    value.append(buffer, 0, length);
    	    }
    		} catch (IOException e) {
    	    // 入出力エラーが発生した場合の処理
    	    e.printStackTrace();
    	    // 必要に応じて、ここで例外を再スローするか、エラーメッセージを返す処理を記述
    		} finally {
    	    // リソースを確実に解放する
    	    if (reader != null) {
    	        try {
    	            reader.close();
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        }
    	    	}
    	    if (is != null) {
    	        try {
    	            is.close();
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        }
    	    	}
    		}
    	return value.toString().trim();}

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			
    		req.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			String idString;
			
			idString = req.getParameter("id");
			
			HttpSession session = req.getSession(false);
			User user = (User) session.getAttribute("user");
			
			if (user == null) {
				System.out.println("ログインしていません。ログインページにリダイレクトします。");
			    res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
			    return;
			}
		
			int userId = user.getUserId();
			int id = 0;

			try {
				id = Integer.parseInt(idString);
			} catch (NumberFormatException e) {
				//型変換できない場合エラー
				e.printStackTrace();
				req.setAttribute("editError", "値が不正です");
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/home.jsp");
				rd.forward(req, res);
				return; //return文で後続処理を停止させる	
			}
				TodoEditViewService todoeditviewservice = new TodoEditViewService();
				
				ToDoDTO dto = todoeditviewservice.ViewSched(id, userId);
				
				if (dto != null) {
					req.setAttribute("dto", dto);
					RequestDispatcher rd = req.getRequestDispatcher("/jsp/todoedit.jsp");
					rd.forward(req, res);
				} else {
					// データベースアクセスに失敗した場合、または該当データがない場合
					req.setAttribute("error", "予定情報の取得に失敗しました。");
					res.sendRedirect(req.getContextPath() + "/jsp/home.jsp?id=" + id + "&userId=" + userId);
			}
		}
    
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

	        req.setCharacterEncoding("UTF-8");
	        res.setContentType("application/json");
	        res.setCharacterEncoding("UTF-8");
	
	        HttpSession session = req.getSession(false);
	        User user = (User) session.getAttribute("user");
	
	        if (user == null) {
	            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            res.getWriter().write("{\"success\": false, \"message\": \"ログインしてください\"}");
	            return;
	        }
	
	        try {
	            // FormData(multipart/form-data)から各Partを取得
	        	//JSから鯖へデータ送信する際の一つのreq形式。
	        	//キーを取得。""。オブジェクトの取得。
	            Part idPart = req.getPart("id");
	            Part exerciseIdPart = req.getPart("exerciseId");
	            Part weightPart = req.getPart("weight");
	            Part countNumPart = req.getPart("countnum");
	            Part setNumPart = req.getPart("setnum");
	            Part detailPart = req.getPart("detail");
	            Part memoPart = req.getPart("memo");
	
	            // Partから文字列値を取得
	            //オブジェクトから値を文字列として取得
	            String idString = getValueFromPart(idPart);
	            String exerciseIdString = getValueFromPart(exerciseIdPart);
	            String weightString = getValueFromPart(weightPart);
	            String countNumString = getValueFromPart(countNumPart);
	            String setNumString = getValueFromPart(setNumPart);
	            String detail = getValueFromPart(detailPart);
	            String memo = getValueFromPart(memoPart);
	            int userId = user.getUserId();
	
	            // バリデーション
	            Validation validation = new Validation();
	            validation.isBlank("重量", weightString);
	            validation.isBlank("回数", countNumString);
	            validation.isBlank("SET数", setNumString);
	            
	        // バリデーションエラーが発生した場合
	        if (validation.hasErrorMsg()) {

	        	// JSONを扱うためのObjectMapperをインスタンス化
	            ObjectMapper mapper = new ObjectMapper();
	
	            // HTTPステータスコードを400 (Bad Request) に設定
	            // これは、クライアントのリクエストに問題があることをブラウザに伝える
	            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	
	            // レスポンスボディにJSON形式のエラーメッセージを書き込む
	            // 'Result' オブジェクトをJSON文字列に変換し、ブラウザに送る
	            res.getWriter().write(mapper.writeValueAsString(
	                new Result(false, String.join(", ", validation.getErrorMsgList()))
	            ));
	
	            // 後続の処理を実行せずにメソッドを終了する
	            return;
	        }
	
	        	int id = 0;
	        	int exerciseId = 0;
	        	double weight = 0;
	        	int countnum = 0;
	        	int setnum = 0;
	        	boolean result = false;
	        	
	        	//Partから取得した値を型変換し代入
	            id = Integer.parseInt(idString);
	            exerciseId = Integer.parseInt(exerciseIdString);
	            weight = Double.parseDouble(weightString);
	            countnum = Integer.parseInt(countNumString);
	            setnum = Integer.parseInt(setNumString);
	    
	            ToDoDTO dto = new ToDoDTO(id, userId, exerciseId, weight, countnum, setnum, detail, memo);
	            //System.out.println(dto); 確認用
	
	            TodoEditService todoeditService = new TodoEditService();
	
	            result = todoeditService.EditSched(dto);
	
	            ObjectMapper mapper = new ObjectMapper();
	            String jsonResponse = mapper.writeValueAsString(
	            new Result(result, result ? "編集成功" : "編集失敗")
	        );
	         //DB処理の結果（resultとメッセージを含むJavaオブジェクト（Resultクラスのインスタンスを生成
	         //resultがtrueなら「編集成功」、falseなら「編集失敗」というメッセージを設定
	
	            //レスポンスの出力ストリームにJSON文字列を書き込み送信
	            res.getWriter().write(jsonResponse);
	            
	        }catch (NumberFormatException e) {
	        	 // 数値変換エラー
	            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            res.getWriter().write("{\"success\": false, \"message\": \"数値形式が不正です\"}");
	        } catch (ServletException e) {
	            // Partの取得エラー
	            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            res.getWriter().write("{\"success\": false, \"message\": \"リクエストの処理中にエラーが発生しました\"}");
	            e.printStackTrace();
	        }
	    }
    	//Resultを使用するために必要
	    	static class Result {
	        // 処理が成功したかどうかを示す真偽値
	        public boolean success;
	        //表示するメッセージ
	        public String message;
	
	        public Result(boolean success, String message) {
	            this.success = success;
	            this.message = message;
	        }
	    }
	}