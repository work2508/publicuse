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
import validation.Validation;

@WebServlet("/register")
//新規会員登録確認

public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		//register.jspへフォワード
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/register.jsp");
		rd.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		//リクエストパラメータ取得
		
		String loginId;
		String password;
		String name;

		loginId = req.getParameter("loginId");
		password = req.getParameter("password");
		name = req.getParameter("name");

		//バリテーションで入力値チェック

		Validation validation = new Validation();
		
		validation.isBlank("ユーザーID", loginId);
		validation.isBlank("パスワード", password);
		validation.isBlank("お名前", name);
		
		validation.length("ユーザーID", loginId, 1, 8);
		validation.length("パスワード", password, 2, 10);
		validation.length("お名前", name, 1, 10);

		//入力エラーがあった場合のerrorMsg

		if (validation.hasErrorMsg()) {
			req.setAttribute("errorMsg", validation.getErrorMsgList());
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/register.jsp");
			rd.forward(req, res);
		}
		
		//新規登録したいユーザーがDBに既に存在するかチェック
		UserRegisterService registerService = new UserRegisterService();

		//ユーザー情報をDomainに格納
		User user = new User(loginId, password, name);

		boolean result = registerService.userEntryConfirm(user);

		//ユーザーが存在しない場合はresultにtrueが格納されている
		if (result == true) {
			//リクエストスコープにdomainを格納してフォワード
			req.setAttribute("user", user);
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/registerConfirm.jsp");
			rd.forward(req, res);
		} else {
			//すでにユーザーが存在する場合エラーMSGを用意して新規登録画面へ戻す
			validation.addErrorMsg("入力いただいたID「" + loginId + "」は既に使われています");
			req.setAttribute("errorMsg", validation.getErrorMsgList());
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/register.jsp");
			rd.forward(req, res);
		}
	}
}