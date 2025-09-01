package controller;


import java.io.IOException;

import domain.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserEditService;

@WebServlet("/editConfirm")

public class EditConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
			req.setCharacterEncoding("UTF-8");
			
			String loginId;
			String name ;
			String password;

			loginId = req.getParameter("loginId");
			name = req.getParameter("editName");
			password = req.getParameter("editPassword");

			//確認用//
			//System.out.println("受け取った loginId: " + loginId);
		    //System.out.println("受け取った name: " + name);
		    //System.out.println("受け取った password: " + password); 
			
	
			//更新後の会員情報をDomainに格納
			
			User user = new User(loginId, password, name);
			
			UserEditService editService = new UserEditService();
			boolean result = editService.userEditDo(user);
			
			//更新に成功した場合はresultにtrueが格納されている
			
			if(result == true) {
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/editDone.jsp");
				rd.forward(req, res);
		
			}else {
				//☆会員情報の更新に失敗した場合はエラーMsgを用意して編集画面に戻す
				req.setAttribute("editError", "更新に失敗しました。");	
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/edit.jsp");
				rd.forward(req, res);
			}
	}
}