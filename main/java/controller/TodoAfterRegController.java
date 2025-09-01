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

@WebServlet("/SchedDoneAfter")

//予定登録用
//予定登録後成功メッセージを表示し同画面を出す
//同一コントローラーのアノテーションを指定するとループの懸念があったため別で作成

public class TodoAfterRegController extends HttpServlet {
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
				
				@SuppressWarnings("unchecked")//警告出たので使用
				List<ExercisesDTO> exerciseslist = (List<ExercisesDTO>)session.getAttribute("exerciseslist");
			
				req.setAttribute("exerciseslist", exerciseslist);
				
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/traininglist.jsp");
				rd.forward(req, res);
				}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}