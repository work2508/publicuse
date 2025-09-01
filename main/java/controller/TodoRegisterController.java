	package controller;
	
	import java.io.IOException;

import domain.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ToDoService;
import validation.Validation;
	
	@WebServlet("/schedRegDo")
	
	//★予定登録用★
	
	public class TodoRegisterController extends HttpServlet {
	
		private static final long serialVersionUID = 1L;
	
		protected void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
		}
	
		protected void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
		
			req.setCharacterEncoding("UTF-8");
	
			HttpSession session = req.getSession(false);
			User user = (User) session.getAttribute("user");
			
			if (user == null) {
				System.out.println("ログインしていません。ログインページにリダイレクトします。");
			    res.sendRedirect(req.getContextPath() + "/login");
			    return;
			}
		
			int userId = user.getUserId();
			
			String exerciseidString;
			String weightString;
			String countnumString;
			String setnumString;
			String detail;
			String memo;
			
			 exerciseidString = req.getParameter("exerciseid");
			 weightString = req.getParameter("weight");
			 countnumString = req.getParameter("countnum");
			 setnumString = req.getParameter("setnum");
			 detail = req.getParameter("detail");
			 memo = req.getParameter("memo");
			
			Validation validation = new Validation();
	
		    // バリデーション
		    validation.isBlank("トレーニング種目", exerciseidString);
		    validation.isBlank("重量", weightString);
		    validation.isBlank("回数", countnumString);
		    validation.isBlank("SET数", setnumString);
	
			    // エラーがあればjspにフォワードして表示
		    if (validation.hasErrorMsg()) {
		        req.setAttribute("errorMsgList", validation.getErrorMsgList());
		        RequestDispatcher rd = req.getRequestDispatcher("/jsp/traininglist.jsp");
		        rd.forward(req, res);   //★forwardはrdに対して呼ぶ
		        return;
		    }
			
			int exerciseId = 0;
			double weight = 0;
			int countnum = 0;
			int setnum = 0;
		
			int result = 0;
			
			try {
				exerciseId = Integer.parseInt(exerciseidString);
				weight = Double.parseDouble(weightString);
				countnum = Integer.parseInt(countnumString);
				setnum = Integer.parseInt(setnumString);
				} catch (NumberFormatException e){
					// 型変換できない場合エラー
					e.printStackTrace();
					req.setAttribute("editError", "入力された値の形式が不正です。");
			        RequestDispatcher rd = req.getRequestDispatcher("/jsp/traininglist.jsp");
			        rd.forward(req, res);
			        return; //★return文で後続処理を停止させる
				}
				
				ToDoService todoservice = new ToDoService();
				
				result = todoservice.registerToDo(userId, exerciseId, weight, countnum, setnum, detail, memo);
	
					if (result > 0) {
						System.out.println("SUCCESS");
						session.setAttribute("tododata", result);
						session.setAttribute("successMessage", "登録が完了しました。");
						 res.sendRedirect(req.getContextPath() + "/SchedDoneAfter");
		
				} else {
					System.out.println("Error");
					// エラーメッセージをリクエストスコープにセット
				    req.setAttribute("errorMessage", "何らかのエラーが発生しました。");
				    //エラーにフォワード
				    RequestDispatcher rd = req.getRequestDispatcher("/error");
				    rd.forward(req, res);
				}
			}
		}