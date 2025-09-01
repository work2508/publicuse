	package service;
	
	import java.sql.SQLException;

import dao.ToDoDAO;
import dto.ToDoDTO;
	
	public class TodoEditViewService {
	
		public ToDoDTO ViewSched(int id, int userId) {
			
			   ToDoDAO todoDAO = new ToDoDAO();
	
		   try{
			  return todoDAO.filledOutToDo(id, userId);
		}catch (SQLException | ClassNotFoundException e) {
	        // 例外が発生した場合、詳細をコンソールに出力
	        e.printStackTrace();
	        // 呼び出し元（コントローラー）に失敗を伝える
	        return null;
			}
		}
	}