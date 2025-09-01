	package service;
	
	import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import dao.ExercisesDAO;
import dto.ExercisesDTO;
	
	public class ExercisesService {
	
		public List<ExercisesDTO> getExercisesList(int partsId, int typeId){
		
		ExercisesDAO exercisesDAO = new ExercisesDAO();
		
		try {	
		List<ExercisesDTO> exerciseslist = exercisesDAO.getExercises(partsId, typeId);
		return exerciseslist;
		
			}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace(); 
            return Collections.emptyList(); 
        }// エラーが発生したことをコントローラーに伝えるため、空のリストを返す
    }	//List型で返すから整数もnullも不可
}