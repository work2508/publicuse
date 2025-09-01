package service;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import dao.UserDAO;
import domain.User;
import dto.UserDTO;

public class UserRegisterService {
	public boolean userEntryConfirm(User user) {

		//DBにユーザーが既に存在するかチェック
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = null;
		try {
		userDTO = userDAO.selectByLoginId(user.getLoginId());

		//ユーザーが存在しない場合は登録内容確認画面へ進む
		if (userDTO == null) {
			return true;	
		} else {
			return false;
		}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false; // DBエラー時はログイン失敗扱い
		}
	}

	public boolean userEntryDo(User user) {
		
		//☆パスワードのハッシュ化
				String plainPassword = user.getPassword();
				String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

		UserDAO userDAO = new UserDAO();
		UserDTO dto = new UserDTO(
				user.getUserId(),
				user.getLoginId(),
				hashedPassword, //☆ハッシュ化されたパスワードをセット
				user.getName());
		try {	
			int result = userDAO.insert(dto);
			if (result == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}