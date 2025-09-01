package dto;

import java.sql.Timestamp;

public class UserDTO{
	
	private int userId;
	private String loginId;
	private String password;
	private String name;
	private Timestamp createdAt;
	
	//引数なしコンストラクタ
		public UserDTO() {}
		
		//引数ありコンストラクタ
		public UserDTO(int userId, String loginId, String password, String name) {
			this.userId = userId;
			this.loginId = loginId;
			this.password = password;
			this.name = name;
		}

		//引数ありコンストラクタ★Delete
				public UserDTO(String loginId) {
					this.loginId = loginId;
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
		public Timestamp getCreatedAt() {
			return createdAt;
		}
		
		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}
}