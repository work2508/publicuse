package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")

public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
			
		//セッションオブジェクト取得
			HttpSession session = req.getSession();
			
			//セッションの破棄
			session.invalidate();
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/login.jsp");
			rd.forward(req, res);
	}

}
