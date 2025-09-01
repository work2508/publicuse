package service;

import java.sql.SQLException;

import dao.ToDoDAO;
import dto.ToDoDTO;

public class TodoDeleteService {

	public boolean DeleteSched(ToDoDTO dto) {

		//削除したユーザーのLoginIdをDTOに格納

		ToDoDAO todoDAO = new ToDoDAO();

		int result = 0;
		try {
			result = todoDAO.deleteToDo(dto);

			if (result == 1) {
				return true;

			} else
				return false;
		} catch (SQLException | ClassNotFoundException e) {
			// 例外が発生した場合、詳細をコンソールに出力
			e.printStackTrace();
			// 呼び出し元（コントローラー）に失敗を伝える
			return false;
		}
	}
}
