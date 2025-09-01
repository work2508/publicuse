package service;

import java.sql.SQLException;

import dao.ToDoDAO;
import dto.ToDoDTO;

public class TodoEditService {

	public boolean EditSched(ToDoDTO dto) {

		ToDoDAO todoDAO = new ToDoDAO();

		int result = 0;

		try {
		result = todoDAO.editToDo(dto);
		// 引数で受け取ったDTOを使ってDAOのメソッドを呼び出す
		
		if (result == 1) {
			return true;
		} else {
			return false;
		}
		}catch (SQLException | ClassNotFoundException e) {
	        // 例外が発生した場合、詳細をコンソールに出力
	        e.printStackTrace();
	        // 呼び出し元（コントローラー）に失敗を伝える
	        return false;
			}
		}
	}