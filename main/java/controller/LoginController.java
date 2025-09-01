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
import service.UserLoginService;

@WebServlet("/login")

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		res.setCharacterEncoding("UTF-8");

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/login.jsp");
		rd.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		res.setContentType("text/html; charset=UTF-8");

		res.setCharacterEncoding("UTF-8");

		String loginId;
		String password;

		loginId = req.getParameter("loginId");
		password = req.getParameter("password");

		UserLoginService loginservice = new UserLoginService();
		User user = loginservice.loginCheck(loginId, password);

		if (user != null) {
			//ログインが成功した場合はセッションスコープにDomainを保存
			HttpSession session = req.getSession();
			session.setAttribute("user", user);

			res.sendRedirect(req.getContextPath() + "/home");

		} else {
			//ログインIDやパスワードが間違ってた場合はリクエストスコープにErrorMsg格納
			//JSP側で使用
			req.setAttribute("loginError", "ログインIＤまたはパスワードが違います。");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/login.jsp");
			rd.forward(req, res);
		}
	}
}
