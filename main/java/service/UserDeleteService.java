package service;

import java.sql.SQLException;

import dao.UserDAO;
import domain.User;
import dto.UserDTO;

public class UserDeleteService {

	public boolean userDeleteDo(User user) {
		  UserDAO userDAO = new UserDAO();
	        UserDTO dto = new UserDTO(user.getLoginId());
        
	try {
	int result = userDAO.delete(dto);
	
	if(result == 1) {
		return true;
	
	}else {
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