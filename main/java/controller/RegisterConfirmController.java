	package controller;
	
	import java.io.IOException;

import domain.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserRegisterService;
	
@WebServlet("/registerExecute")
//新規会員登録

	public class RegisterConfirmController extends HttpServlet {
		private static final long serialVersionUID = 1L;
	      
		protected void doGet(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
				req.setCharacterEncoding("UTF-8");
		}
		protected void doPost(HttpServletRequest req, HttpServletResponse res) 
				throws ServletException, IOException {
				req.setCharacterEncoding("UTF-8");
		
				String loginId;
				String password;
				String name;
				
				loginId = req.getParameter("loginId");
				password = req.getParameter("password");
				name = req.getParameter("name");
				
				//登録する会員情報をDomainに格納
				User user = new User(loginId, password, name);
				
				UserRegisterService userRegister = new UserRegisterService();
				boolean result = userRegister.userEntryDo(user);
				
				//登録に成功した場合はresultにtrueが格納されている
			
				if(result == true) {
					RequestDispatcher rd = req.getRequestDispatcher("/jsp/registerDone.jsp");
					rd.forward(req, res);
			
				}else {
					//新規会員登録に失敗した場合はエラーMsgを用意して会員登録画面に戻す
					req.setAttribute("registerError", "新規会員登録に失敗しました。");	
					RequestDispatcher rd = req.getRequestDispatcher("/jsp/register.jsp");
					rd.forward(req, res);
				}
		}
	}