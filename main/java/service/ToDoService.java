package service;

import java.sql.SQLException;

import dao.ToDoDAO;

public class ToDoService {

	public int registerToDo(int userId, int exerciseId, double weight, int countnum, int setnum, String detail,
			String memo) {
		
		ToDoDAO todoDAO = new ToDoDAO();
		
		try {
			int result = todoDAO.insertToDo(userId, exerciseId, weight, countnum, setnum, detail,memo);
		
			return result;
			
		}catch (SQLException | ClassNotFoundException e) {
	        // 例外が発生した場合、詳細をコンソールに出力
	        e.printStackTrace();
	        // 呼び出し元（コントローラー）に失敗を伝える
	        return -1;
			}
		}
	}
