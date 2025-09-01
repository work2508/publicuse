package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDTO;

public class UserDAO extends BaseDAO {

	public UserDTO selectByLoginId(String loginId) throws SQLException, ClassNotFoundException {
		UserDTO dto = null;

		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//引数で受け取った変数loginIdで検索
			ps = conn.prepareStatement("SELECT user_id, login_id, password_hash, name, created_at FROM users WHERE login_id = ?");
			ps.setString(1, loginId);
			rs = ps.executeQuery();

			//検索にヒットしたデータがあればDTOに格納
			if (rs.next()) {
			    dto = new UserDTO();
			    dto.setUserId(rs.getInt("user_id"));
			    dto.setLoginId(rs.getString("login_id"));
			    dto.setPassword(rs.getString("password_hash"));
			    dto.setName(rs.getString("name"));
			    dto.setCreatedAt(rs.getTimestamp("created_at"));
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
		return dto;
	}

	public int insert(UserDTO dto)throws SQLException, ClassNotFoundException{

		int result = 0;
		TransactionManager tm = null;

		try {
			Connection conn = getConnection();
		
			tm = new TransactionManager(conn);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO users(login_id,password_hash,name)VALUES(?,?,?)");
			ps.setString(1, dto.getLoginId());
			ps.setString(2, dto.getPassword());
			ps.setString(3, dto.getName());

			//DBへのinsertが成功した件数がINT型で返却される
			result = ps.executeUpdate();
			
			tm.commit();
			System.out.println("ユーザー情報の更新が正常にコミットされました。");
		}catch
			(SQLException | ClassNotFoundException e) {
				if (tm != null) {
					tm.rollback();
					System.out.println("ロールバックされました。");
				}
				throw e;
		}finally{
			if (tm != null) {
				tm.close();
			}
		}
		return result;
	}

	public int edit(UserDTO dto)throws SQLException, ClassNotFoundException{
		
		int result = 0;
		TransactionManager tm = null;

		try {
			Connection conn = getConnection();
			tm = new TransactionManager(conn);

			PreparedStatement ps = conn.prepareStatement("UPDATE users SET password_hash = ?,name = ? WHERE login_id = ?");
			ps.setString(1, dto.getPassword());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getLoginId());

			//DBへのupdateが成功した件数がint型で返却される
			result = ps.executeUpdate();
			tm.commit();
			System.out.println("ユーザー情報の編集が正常にコミットされました。");
		}catch
		(SQLException | ClassNotFoundException e) {
			if (tm != null) {
				tm.rollback();
				System.out.println("ロールバックされました。");
			}
			throw e;			
		} finally {
			if (tm != null) {
				tm.close();
			}
		}
		return result;
	}

	public int delete(UserDTO dto)throws SQLException, ClassNotFoundException{
		//確認用
		//System.out.println("【削除対象 login_id】" + dto.getLoginId() + "【user_id】" + dto.getUserId());
		
		int result = 0;
		TransactionManager tm = null;

		try {
			Connection conn = getConnection();
			tm = new TransactionManager(conn);

			PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE login_id = ?");
			ps.setString(1, dto.getLoginId());

			//DBへのdeleteが成功した件数がint型で返却される
			result = ps.executeUpdate();
			tm.commit();
			System.out.println("ユーザー情報の削除が正常にコミットされました。");
		}catch
		(SQLException | ClassNotFoundException e) {
			if (tm != null) {
				tm.rollback();
				System.out.println("ロールバックされました。");
			}
			throw e;
		} finally {
			if (tm != null) {
				tm.close();
			}
		}
		return result;
	}
}