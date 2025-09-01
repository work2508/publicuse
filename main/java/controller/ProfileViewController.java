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

@WebServlet("/profile")

public class ProfileViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		res.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			System.out.println("ログインしていません。ログインページにリダイレクトします。");
		    res.sendRedirect(req.getContextPath() + "/login");
		    return;
		}
		
		req.setAttribute("userProfile", user);
        RequestDispatcher rd = req.getRequestDispatcher("/jsp/profile.jsp");
        rd.forward(req, res);
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
