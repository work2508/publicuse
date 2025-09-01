package dto;

public class PartsDTO {

private int partsId;

private String partsName;

public PartsDTO() {}

public PartsDTO(int partsId, String partsName) {

this.partsId = partsId;
this.partsName = partsName;
}

public int getPartsId() {
return partsId;
}

public void setPartsId(int partsId) {
this.partsId = partsId;
}

public String getPartsName() {
return partsName;
}

public void setPartsName(String partsName) {
this.partsName = partsName;
}
}