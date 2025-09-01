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

@WebServlet("/home")
//転送用

public class GoToHomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			System.out.println("ログインしていません。ログインページにリダイレクトします。");
		    res.sendRedirect(req.getContextPath() + "/login");
		    return;
		}
		
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/home.jsp");
				rd.forward(req, res);
		}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req, res);
	}

}
