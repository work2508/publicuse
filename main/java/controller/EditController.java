package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import validation.Validation;

@WebServlet("/edit")

public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			//edit.jspへフォワード
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/edit.jsp");
			rd.forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
			
			req.setCharacterEncoding("UTF-8");
			//POSTフォームなどで送られてきた文字列を正しく読む
			
			String editPassword;
			String editName;
			
			//リクエストパラメーターを取得
			editPassword = req.getParameter("editPassword");
			editName = req.getParameter("editName");
			
			//バリテーション
			Validation validation = new Validation();
			validation.isBlank("パスワード", editPassword);
			validation.isBlank("お名前", editName);
			validation.length("パスワード", editPassword, 2, 10);
			validation.length("お名前", editName, 1, 10);
			
			//入力エラーがあった場合
			if(validation.hasErrorMsg()){
				req.setAttribute("errorMsg", validation.getErrorMsgList());
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/edit.jsp");
				rd.forward(req, res);
			}else {
			      //変更後の値をリクエストスコープに保存して確認画面へ
			      req.setAttribute("editName", editName);
			      req.setAttribute("editPassword", editPassword);
			      RequestDispatcher rd = req.getRequestDispatcher("/jsp/editConfirm.jsp");
			      rd.forward(req, res);
			}			
	}
}
