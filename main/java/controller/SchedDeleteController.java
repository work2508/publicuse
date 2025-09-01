package controller;

import java.io.IOException;

import domain.User;
import dto.ToDoDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.TodoDeleteService;
import service.TodoEditViewService;

@WebServlet("/schedDelete")
//★カレンダーの予定削除★
//★モーダル画面に予定を表示する→TodoEditViewService
//★モーダル画面からの予定削除→TodoDeleteServiceS

public class SchedDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	res.setContentType("text/html; charset=UTF-8");
	
	String idString;
	
	idString = req.getParameter("id");
	
	HttpSession session = req.getSession(false);
	User user = (User) session.getAttribute("user");
	if (user == null) {
		System.out.println("ログインしていません。ログインページにリダイレクトします。");
	    res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
	    return;
	}

	int userId = user.getUserId();
	
	int id = 0;

	try {
		id = Integer.parseInt(idString);
	} catch (NumberFormatException e) {
		// 型変換できない場合エラー
		e.printStackTrace();
		req.setAttribute("editError", "値が不正です");
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/home.jsp");
		rd.forward(req, res);
		return; 
	}
		TodoEditViewService todoeditviewservice = new TodoEditViewService();
		
		ToDoDTO dto = todoeditviewservice.ViewSched(id, userId);
		
		if (dto != null) {
			req.setAttribute("dto", dto);
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/tododelete.jsp");
			rd.forward(req, res);
		} else {
			// データベースアクセスに失敗した場合、または該当データがない場合
			req.setAttribute("error", "予定情報の取得に失敗しました。");
			res.sendRedirect(req.getContextPath() + "/jsp/home.jsp?id=" + id + "&userId=" + userId);
	}
}
		protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
			
		req.setCharacterEncoding("UTF-8");

		String idString = req.getParameter("id");

		int id = 0;

		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			// 型変換できない場合エラー
			e.printStackTrace();
			req.setAttribute("editError", "値が不正です");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/home.jsp");
			rd.forward(req, res);
			return; // ★ return文で後続処理を停止させる
		}

		ToDoDTO dto = new ToDoDTO(id);

		TodoDeleteService tododeleteService = new TodoDeleteService();

		boolean result = tododeleteService.DeleteSched(dto);

		//削除が成功した場合resultにtrueが格納されている
		if (result == true) {
			 req.getSession().setAttribute("SUCSESS", "削除しました。");
			 res.sendRedirect(req.getContextPath() + "/home");	
		} else {
			//削除に失敗した場合はエラーMsgを用意して削除確認画面へ戻す
			 req.getSession().setAttribute("FAIL", "削除に失敗しました。");
			 RequestDispatcher rd = req.getRequestDispatcher("/jsp/editview.jsp");
			    rd.forward(req, res);
		}
  }
}