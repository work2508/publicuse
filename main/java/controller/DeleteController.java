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
import service.UserDeleteService;

@WebServlet("/delete")

public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			
			//☆deleteConfirm.jspへフォワード
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/deleteConfirm.jsp");
		rd.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
			
			String loginId = req.getParameter("deleteLoginId");
			//System.out.println("コントローラー loginId: " + loginId);	 確認用
			
			//☆削除したユーザー情報のloginIdをDomainに格納
			
			User user = new User(loginId, null, null);

			UserDeleteService deleteService = new UserDeleteService();
			boolean result = deleteService.userDeleteDo(user);
			
			//削除が成功した場合resultにtrueが格納されている
			if(result == true) {
				
				//☆セッションの破棄
				HttpSession session = req.getSession();
				session.invalidate();
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/deleteDone.jsp");
				rd.forward(req, res);
			}else {
				//☆削除に失敗した場合はエラーMsgを用意して削除確認画面へ戻す			
				req.setAttribute("deleteError", "削除に失敗しました。");	
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/deleteConfirm.jsp");
				rd.forward(req, res);
			}
	}
}