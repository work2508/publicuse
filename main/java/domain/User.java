package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

	private int userId;
	private String loginId;
	private String password;
	private String name;
	private Date createdAt;
	private String createdAtStr;

	//引数なしコンストラクタ
	public User() {
	}

	//引数ありコンストラクタ
	public User(int userId, String loginId, String password, String name) {
		this.userId = userId;
		this.loginId = loginId;
		this.password = password;
		this.name = name;
	}

	//引数ありコンストラクタ2
	public User(String loginId, String password, String name) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
	}

	//getter setter　※createdAtStrのsetterは除く

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
		this.createdAtStr = new SimpleDateFormat("yyyy年MM月dd日hh時mm分").format(createdAt);
	}

	public String getCreatedAtStr() {
		return createdAtStr;
	}
}