package controller;

import java.io.IOException;
import java.util.List;

import domain.User;
import dto.ExercisesDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ExercisesService;
import validation.Validation;

	@WebServlet("/schedRegChose")
	//★予定登録★部位・種別選択

public class ExercisesController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession(false);

		User user = (User) session.getAttribute("user");
		if (user == null) {
		System.out.println("ログインしていません。ログインページにリダイレクトします。");
		res.sendRedirect(req.getContextPath() + "/login");
		return;

		}

	    //ログイン済みならselect.jspへ
	    RequestDispatcher rd = req.getRequestDispatcher("/jsp/select.jsp");
	    rd.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		
				String partsIdString;
				String typeIdString;
				
				partsIdString = req.getParameter("partsId");
				typeIdString = req.getParameter("typeId");
				
				Validation validation = new Validation();
		        
		        // 部位と種別が未選択の場合にエラーメッセージを設定
		        if (partsIdString == null || partsIdString.isEmpty()) {
		            validation.addErrorMsg("部位を選択してください。");
		        }
		        if (typeIdString == null || typeIdString.isEmpty()) {
		            validation.addErrorMsg("種別を選択してください。");
		        }
		
		        // エラーメッセージが存在する場合、select.jspにフォワード
		        if (validation.hasErrorMsg()) {
		            req.setAttribute("errorMsgList", validation.getErrorMsgList());
		            RequestDispatcher rd = req.getRequestDispatcher("/jsp/select.jsp");
		            rd.forward(req, res);
		            return;
		        }
				int partsId = 0;
				int typeId = 0;

				partsId = Integer.parseInt(partsIdString);
				typeId = Integer.parseInt(typeIdString);

				ExercisesService exercisesservice = new ExercisesService();
				List<ExercisesDTO> exerciseslist = exercisesservice.getExercisesList(partsId, typeId);

				//exerciseslistがnullじゃないかつ空でない場合
				if (exerciseslist != null && !exerciseslist.isEmpty()) {
				//セッションスコープにexerciseslistをセットしトレーニング選択画面へフォワード
					HttpSession session = req.getSession();
					session.setAttribute("exerciseslist", exerciseslist);
					RequestDispatcher rd = req.getRequestDispatcher("/jsp/traininglist.jsp");
					rd.forward(req, res);	
				} else {
					System.out.println("exerciseslistがnullもしくはempty");
					RequestDispatcher rd = req.getRequestDispatcher("/jsp/select.jsp");
					rd.forward(req, res);
			}	

		}		
	}