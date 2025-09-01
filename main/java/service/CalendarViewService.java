	package service;
	
	import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ToDoDAO;
import dto.ToDoDTO;
	
	public class CalendarViewService{
	
		public List<ToDoDTO> getClendarView (int userId,  Date start, Date end){
	
			   List<ToDoDTO> todolist = new ArrayList<ToDoDTO>();
			
       			ToDoDAO todoDAO = new ToDoDAO();
			
		try {
			todolist = todoDAO.displayToDo(userId, start, end);
			
			if(todolist != null) {
				for(ToDoDTO dto : todolist) {
					if (dto.getExerciseId() >= 1 && dto.getExerciseId() <= 10) {
					    dto.setColor("green");
					} else if (dto.getExerciseId() >= 11 && dto.getExerciseId() <= 17) {
					    dto.setColor("blue");
					} else if (dto.getExerciseId() >= 18 && dto.getExerciseId() <= 38) {
					    dto.setColor("orange");
					} else if (dto.getExerciseId() >= 39 && dto.getExerciseId() <= 49) {
					    dto.setColor("red");
					} else {
					    dto.setColor("gray");
					}
					}
				}
			return todolist;
		}catch (SQLException | ClassNotFoundException e) {
	        // 例外が発生した場合、詳細をコンソールに出力
	        e.printStackTrace();
	        // 呼び出し元（コントローラー）に失敗を伝える
	        return null;
		}
	}
}