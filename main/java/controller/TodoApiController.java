package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.User;
import dto.ToDoDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CalendarViewService;

@WebServlet("/events")

//★カレンダー予定表示用★
//ToDoDTOのリストをJSONに変換し返す

public class TodoApiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession(false);
		//既存のセッションがあればそれを返す。なければnullを返す。
		User user = (User) session.getAttribute("user");
		//既存のセッションから"user"を取得

		if (user == null) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			res.getWriter().write("{\"success\": false, \"message\": \"ログインしてください\"}");
			return;
		}

		int userId;
		String startString;
		String endString;
		
		userId = user.getUserId();
		startString = req.getParameter("start");
		endString = req.getParameter("end");
		
		if (startString == null || endString == null) {
		    res.setContentType("application/json");
		    res.setCharacterEncoding("UTF-8");
		    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    res.getWriter().write("{\"error\": \"必須パラメータ（start, end）がありません。\"}");
		    return;
		}

		Date start = null; 
		Date end = null; 

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			
			start = formatter.parse(startString); 
			end = formatter.parse(endString); 
		} catch (NumberFormatException | ParseException e) {
			
			e.printStackTrace();
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
			res.getWriter().write("{\"error\": \"パラメータの形式が不正です。\"}"); 
			return; //
		}

		try {
			CalendarViewService calendarviewservice = new CalendarViewService();

			//確認用コード
			//System.out.println("【userId】" + userId + "【start】 " + start + "【end】 " + end);

			List<ToDoDTO> todolist = calendarviewservice.getClendarView(userId, start, end);

			//確認用コード
			//System.out.println(【todolist size】" + (todolist != null ? todolist.size() : "null"));
			//? = ifelse

			ObjectMapper mapper = new ObjectMapper();
			String jsonResponse = mapper.writeValueAsString(todolist);

			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonResponse);

		} catch (IOException e) {	
			e.printStackTrace();
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.getWriter().write("{\"error\": \"JSON処理中にエラーが発生しました。\"}");
		}
	}
}
