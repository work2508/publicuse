	package dao;
	
	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ExercisesDTO;
	
	public class ExercisesDAO extends BaseDAO {
	
		//★SELECT画面用★
		public List<ExercisesDTO> getExercises(int partsid, int typeid)
				throws SQLException, ClassNotFoundException {
	
			List<ExercisesDTO> exerciseslist = new ArrayList<ExercisesDTO>();
	
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
	
			//ExercisesDTO dto = null;　　いらない（whileループでその都度生成するから）
	
			try {
	
				conn = getConnection();
	
				ps = conn.prepareStatement("SELECT *  FROM exercises  WHERE parts_id = ? AND type_id = ?");
	
				ps.setInt(1, partsid);
				ps.setInt(2, typeid);
	
				rs = ps.executeQuery();
	
				while (rs.next()) {
	
					ExercisesDTO dto = new ExercisesDTO();
	
					dto.setExerciseId(rs.getInt("exercise_id"));
					dto.setPartsId(rs.getInt("parts_id"));
					dto.setTypeId(rs.getInt("type_id"));
					dto.setExerciseName(rs.getString("exercise_name"));
	
					exerciseslist.add(dto);
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
			return exerciseslist;
		}
	}