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
import service.TodoEditViewService;

@WebServlet("/schedView")

//★カレンダー予定編集用★

public class SchedEditViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//★カレンダーで予定確認画面を表示★//

		protected void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
	
			req.setCharacterEncoding("UTF-8");
	
			HttpSession session = req.getSession(false);

			User user = (User) session.getAttribute("user");
			if (user == null) {
			System.out.println("ログインしていません。ログインページにリダイレクトします。");
			res.sendRedirect(req.getContextPath() + "/login");
			return;
			}
			
			String idString = req.getParameter("id");
			
			int userId = user.getUserId(); // int 型のユーザーIDを取得
			
	
			int id = 0;
	
			try {
				id = Integer.parseInt(idString);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				// ユーザーにエラーを通知
				req.setAttribute("error", "パラメータの形式が不正です。");
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/error.jsp");
				rd.forward(req, res);
			    return;
			}
	
			TodoEditViewService todoeditviewservice = new TodoEditViewService();
	
			ToDoDTO dto = todoeditviewservice.ViewSched(id, userId);
	
			if (dto != null) {
				req.setAttribute("dto", dto);
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/editview.jsp");
				rd.forward(req, res);
	
			} else {
			    // データベースアクセスに失敗した場合、または該当データがない場合
			    req.setAttribute("error", "予定情報の取得に失敗しました。");
			    // フォワードでhome.jspに戻る
			    RequestDispatcher rd = req.getRequestDispatcher("/jsp/home.jsp");
			    rd.forward(req, res);
			}
	
		}
	}