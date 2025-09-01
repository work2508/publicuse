package service;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import dao.UserDAO;
import domain.User;
import dto.UserDTO;

public class UserEditService{
	
public boolean userEditDo(User user){
	//確認用★コントローラーから受け取ったパスワードを確認
	//System.out.println("Serviceが受け取ったパスワード: " + user.getPassword());
	
	String plainPassword = user.getPassword();
    String hashedPassword = null;
    if (plainPassword != null && !plainPassword.trim().isEmpty()) {
    //trim:文字列の先頭と末尾にある空白文字（スペース、タブ、改行など）をすべて取り除く
   //パスワードが空でない場合のみハッシュ化を実行
    hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    System.out.println("ハッシュ化されたパスワード: " + hashedPassword);
    }
	
	//パスワードのハッシュ化
	//String plainPassword = user.getPassword();
	//String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	
	//確認用★ハッシュ化されたパスワードをログ出力。
	//System.out.println("ハッシュ化されたパスワード: " + hashedPassword);
	
	UserDTO dto = new UserDTO(
			user.getUserId(),
			user.getLoginId(),
			hashedPassword, //ハッシュ化されたパスワードをセット
			user.getName()
			);
	
	UserDAO userDAO = new UserDAO();
	try {
	int result = userDAO.edit(dto);
	
	//★確認用★DAOに渡すDTOの値をログに出力
    //System.out.println("DAOに渡すDTOのパスワード: " + dto.getPassword());
	
	if(result == 1) {
		return true;
	}else {
		return false;
	}
	}catch (SQLException | ClassNotFoundException e) {
        // 例外が発生した場合、詳細をコンソールに出力
        e.printStackTrace();
        // 呼び出し元（コントローラー）に失敗を伝える
        return false; // ★ここが追加・修正された部分
	}
  }
}