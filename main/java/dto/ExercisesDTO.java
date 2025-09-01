package dto;

public class ExercisesDTO{

private int exerciseId;

private int partsId;

private int typeId;

private String exerciseName;

public ExercisesDTO() {}

public ExercisesDTO(int exerciseId, int partsId, int typeId, String exerciseName) {

this.exerciseId = exerciseId;
this.partsId = partsId;
this.typeId = typeId;
this.exerciseName = exerciseName;
}

public int getExerciseId() {
return exerciseId;
}

public void setExerciseId(int exerciseId) {
this.exerciseId = exerciseId;
}

public int getPartsId() {
return partsId;
}

public void setPartsId(int partsId) {
this.partsId = partsId;
}

public int getTypeId() {
return typeId;
}

public void setTypeId(int typeId) {
this.typeId = typeId;
}

public String getExerciseName() {
return exerciseName;
}

public void setExerciseName(String exerciseName) {
this.exerciseName = exerciseName;}
}