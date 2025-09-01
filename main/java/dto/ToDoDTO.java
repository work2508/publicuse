	package dto;
	
	import java.sql.Timestamp;
	
	public class ToDoDTO {
	
		private int id;
	
		private int userId;
	
		private int exerciseId;
	
		private double weight;
	
		private int countnum;
	
		private int setnum;
	
		private String detail = "";
	
		private String memo = "";
	
		private Timestamp createdAt;
	
		private Timestamp updatedAt;
	
		private String exerciseName;
	
		private String color;
		
		public ToDoDTO() {
		}
	
		public ToDoDTO(int id, int userId, int exerciseId, double weight, int countnum, int setnum, String detail,
				String memo) {
			//トレーニング項目登録、カレンダー更新用
	
			this.id = id;
			this.userId = userId;
			this.exerciseId = exerciseId;
			this.weight = weight;
			this.countnum = countnum;
			this.setnum = setnum;
			this.detail = detail;
			this.memo = memo;
		}
	
		public ToDoDTO(int exerciseId, double weight, int countnum, int setnum, String detail, String memo,
				Timestamp createdAt) {
			//Calendar予定登録用
	
			this.exerciseId = exerciseId;
			this.weight = weight;
			this.countnum = countnum;
			this.setnum = setnum;
			this.detail = detail;
			this.memo = memo;
			this.createdAt = createdAt;
		}

		public ToDoDTO(int id) {
			//Calendar予定削除用
			this.id = id;
		}
						
		public ToDoDTO(String exerciseName) {
			//Calandar予定登録時、exerciseIdとexerciseNameの変換時に使用
	
			this.exerciseName = exerciseName;
		}
	
		public int getId() {
			return id;
		}
	
		public void setId(int id) {
			this.id = id;
		}
	
		public int getUserId() {
			return userId;
		}
	
		public void setUserId(int userId) {
			this.userId = userId;
		}
	
		public int getExerciseId() {
			return exerciseId;
		}
	
		public void setExerciseId(int exerciseId) {
			this.exerciseId = exerciseId;
		}
	
		public double getWeight() {
			return weight;
		}
	
		public void setWeight(double weight) {
			this.weight = weight;
		}
	
		public int getCountNum() {
			return countnum;
		}
	
		public void setCountNum(int countnum) {
			this.countnum = countnum;
		}
	
		public int getSetNum() {
			return setnum;
		}
	
		public void setSetNum(int setnum) {
			this.setnum = setnum;
		}
	
		public String getDetail() {
			return detail;
		}
	
		public void setDetail(String detail) {
			this.detail = detail;
		}
	
		public String getMemo() {
			return memo;
		}
	
		public void setMemo(String memo) {
			this.memo = memo;
		}
	
		public Timestamp getCreatedAt() {
			return createdAt;
		}
	
		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}
	
		public Timestamp getUpdatedAt() {
			return updatedAt;
		}
	
		public void setUpdatedAt(Timestamp updatedAt) {
			this.updatedAt = updatedAt;
		}
	
		public String getExerciseName() {
			return exerciseName;
		}
	
		public void setExerciseName(String exerciseName) {
			this.exerciseName = exerciseName;
		}
		public String getColor() {
			return color;
		}
	
		public void setColor(String color) {
			this.color = color;
		}
	}
