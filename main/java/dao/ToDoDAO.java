package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dto.ToDoDTO;

public class ToDoDAO extends BaseDAO {

	//★★★todoテーブルへの登録用メソッド★★★
	public int insertToDo(int userId, int exerciseId, double weight, int countnum, int setnum, String detail,
			String memo)
			throws SQLException, ClassNotFoundException {
		//SQLException=SQL関連で起こりうる例外、DBアクセス時のエラー（SQL文の間違い・接続エラーなど）
		//ClassNotFoundException=クラスが見つからなかった際の例外処理、JDBCドライバのロード時、そのクラスファイルがクラスパスに存在しない

		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		TransactionManager tm = null; // TransactionManagerのインスタンスを宣言
		//変数の宣言および初期化
		//try の外で宣言しておけば → try の中でも catch の中でも finally の中でも使える。
		//try の中だけで宣言した場合 → そのスコープはそのブロック内だけなので、catch/finally から参照できなくなる。

		try {
			conn = getConnection();
			//BaseDAOのgetConnectionメソッド使用、確立したコネクションをconnに代入

			tm = new TransactionManager(conn); // TransactionManagerのインスタンスを生成

			ps = conn.prepareStatement(
					"INSERT INTO todo (user_id, exercise_id, weight, countnum, setnum, detail, memo) VALUES (?, ?, ?, ?, ?, ?, ?)");
			//SQL実行準備

			ps.setInt(1, userId);
			ps.setInt(2, exerciseId);
			ps.setDouble(3, weight);
			ps.setInt(4, countnum);
			ps.setInt(5, setnum);
			ps.setString(6, detail);
			ps.setString(7, memo);
			//プレースホルダに値を入れる

			result = ps.executeUpdate();
			//resultにSQLの結果を代入
			//executeUpdate() は INSERT/UPDATE/DELETE の場合、実際に影響を受けた行数を返す
			//result には「何行INSERTできたか」が入る。
			tm.commit();
			System.out.println("ToDoデータの登録が正常にコミットされました。");

		} catch (SQLException | ClassNotFoundException e) {
			if (tm != null) {
				tm.rollback();
				System.out.println("ToDoデータの登録がロールバックされました。");
			}
			throw e;// 例外を上位層に再スローする
		} finally {	// 最後に必ず実行されるfinallyブロックでリソースを解放する
			if (tm != null) {// nullじゃないならクローズ
				tm.close();
			}
		}
		return result;
	}

	//★★★ToDoテーブルから削除する★★★

	public int deleteToDo(ToDoDTO dto)
			throws SQLException, ClassNotFoundException {

		int result = 0;
		TransactionManager tm = null;

		try {

			Connection conn = getConnection();
			
			tm = new TransactionManager(conn);

			PreparedStatement ps = conn.prepareStatement("DELETE FROM todo WHERE id = ?");

			ps.setInt(1, dto.getId());

			//DBへのdeleteが成功した件数がint型で返却される
			result = ps.executeUpdate();
			tm.commit();
			System.out.println("ToDoデータの削除が正常にコミットされました。");

		} catch (SQLException | ClassNotFoundException e) {
			if (tm != null) {
				tm.rollback();
				System.out.println("ToDoデータの削除がロールバックされました。");
			}
			throw e;
		} finally {
			if (tm != null) {
				tm.close();
			}
		}
		return result;
	}

	//★★★ToDoテーブルの予定を変更する★★★

	public int editToDo(ToDoDTO dto)
			throws SQLException, ClassNotFoundException {

		int result = 0;
		TransactionManager tm = null;

		//DBへ接続
		try {

			Connection conn = getConnection();
		
			tm = new TransactionManager(conn);

			PreparedStatement ps = conn.prepareStatement(
					"UPDATE todo SET weight = ?, countnum = ?, setnum = ?, detail = ?, memo = ? WHERE id = ?");

			ps.setDouble(1, dto.getWeight());
			ps.setInt(2, dto.getCountNum());
			ps.setInt(3, dto.getSetNum());
			ps.setString(4, dto.getDetail());
			ps.setString(5, dto.getMemo());
			ps.setInt(6, dto.getId());

			//DBへのupdateが成功した件数がint型で返却される
			result = ps.executeUpdate();
			tm.commit();
			System.out.println("ToDoデータの変更が正常にコミットされました。");

		} catch (SQLException | ClassNotFoundException e) {
			if (tm != null) {
				tm.rollback();
				System.out.println("ToDoデータの変更がロールバックされました。");
			}
			throw e;
		} finally {
			if (tm != null) {
				tm.close();
			}
		}
		return result;
	}

	//★★★ToDoテーブルの予定を変更する/項目欄に既存の情報を表示するために使用★★★

	public ToDoDTO filledOutToDo(int id, int userId)
			throws SQLException, ClassNotFoundException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			conn = getConnection();

			ps = conn.prepareStatement(
					"SELECT t.*, e.exercise_name " +
							"FROM todo AS t " +
							"JOIN exercises AS e ON t.exercise_id = e.exercise_id " + 
							"WHERE t.id = ? " + // todo_id で1件取得
							"AND t.user_id = ?" // ログインユーザーのデータだけ
			);

			ps.setInt(1, id);

			ps.setInt(2, userId);
			
			rs = ps.executeQuery();

			if (rs.next()) {

				ToDoDTO dto = new ToDoDTO();

				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("user_id"));
				dto.setExerciseId(rs.getInt("exercise_id"));
				dto.setWeight(rs.getDouble("weight"));
				dto.setCountNum(rs.getInt("countnum"));
				dto.setSetNum(rs.getInt("setnum"));
				dto.setDetail(rs.getString("detail"));
				dto.setMemo(rs.getString("memo"));
				dto.setCreatedAt(rs.getTimestamp("created_at"));
				dto.setUpdatedAt(rs.getTimestamp("updated_at"));
				dto.setExerciseName(rs.getString("exercise_name"));

				return dto;
			}
		} finally {

			try {

				if (rs != null)
					rs.close();

				if (ps != null)
					ps.close();

				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	//★★★カレンダーに予定を表示するためのメソッド★★★
	public List<ToDoDTO> displayToDo(int userId, Date start, Date end)
			throws SQLException, ClassNotFoundException {

		List<ToDoDTO> todolist = new ArrayList<ToDoDTO>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		try {

			conn = getConnection();

			ps = conn.prepareStatement(
					"SELECT t.*, e.exercise_name " +
							"FROM todo AS t " +
							"JOIN exercises AS e ON t.exercise_id = e.exercise_id " +
							"WHERE t.user_id = ? " +
							"AND t.created_at >= ? AND t.created_at < ?"
			);
			//☆SQL実行準備
			//t（todoエイリアス)のすべてと、e(exerciseエイリアス）のexercise_name
			//FROM A JOIN B: Aが左、Bが右。Aを基準に、Bのデータをくっつける。
			//tのexercise_idとeのidを結合
			//tのuser_idにセットされた値と等しい行のみ抽出
			//EXTRACT：日付やタイムスタンプから年、月、日などの一部を抽出する関数

			//☆プレースホルダに値を入れる

			Calendar cal = Calendar.getInstance();
			//現在の日時を持つオブジェクト

			cal.setTime(end); // endで上書き
			cal.add(Calendar.DAY_OF_MONTH, 1); // 1日加算する※FullCalendarの都合により

			ps.setInt(1, userId);

			ps.setDate(2, new java.sql.Date(start.getTime()));

			ps.setDate(3, new java.sql.Date(cal.getTimeInMillis()));

			rs = ps.executeQuery();
			//SELECT文実行、rsへ代入

			while (rs.next()) {

				ToDoDTO dto = new ToDoDTO();

				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getInt("user_id"));
				dto.setExerciseId(rs.getInt("exercise_id"));
				dto.setWeight(rs.getDouble("weight"));
				dto.setCountNum(rs.getInt("countnum"));
				dto.setSetNum(rs.getInt("setnum"));
				dto.setDetail(rs.getString("detail"));
				dto.setMemo(rs.getString("memo"));
				dto.setCreatedAt(rs.getTimestamp("created_at"));
				dto.setUpdatedAt(rs.getTimestamp("updated_at"));
				dto.setExerciseName(rs.getString("exercise_name"));

				todolist.add(dto);
			}
		} finally {
		

			try {
				if (rs != null) 
					rs.close();

				if (ps != null) 
					ps.close();

				if (conn != null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return todolist;
	}
}