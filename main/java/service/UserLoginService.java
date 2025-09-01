package service;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import dao.UserDAO;
import domain.User;
import dto.UserDTO;

public class UserLoginService {

	public User loginCheck(String loginId, String password) {

		UserDAO userDAO = new UserDAO();
		//Controllerから受け取ったパラメータをDAOのメソッドへ渡す
		UserDTO userDTO = null;

		try {
			userDTO = userDAO.selectByLoginId(loginId);
			//DAOでのselect成功時はUserDTOにログインしたユーザー情報が格納されている
			//ユーザーが入力した値とDBの値が等しいかチェック
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
			// DBエラー時はログイン失敗扱い
		}

		//ユーザーが存在しないまたはパスワードが一致しない場合はnullを返す
		if (userDTO != null) {
			if (BCrypt.checkpw(password, userDTO.getPassword())) {
				//パスワードが等しい場合はDTOの情報をDomain(user)へ移行
				User user = new User(
						userDTO.getUserId(),
						userDTO.getLoginId(),
						userDTO.getPassword(),
						userDTO.getName());
				return user;
			}
		}
		return null;
	}
}