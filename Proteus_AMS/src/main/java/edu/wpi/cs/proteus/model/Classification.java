package edu.wpi.cs.proteus.model;

public class Classification {
	
	String classificationID;
	String classificationName;
	String superClassification;
	
	public Classification(String id, String name, String superClass) {
		this.classificationID = id;
		this.classificationName = name;
		this.superClassification = superClass;
	}
	
	public Classification() {
		
	}
	
	public void setClassificationID(String id) {
		this.classificationID = id;
	}
	public String getClassificationID() {
		return this.classificationID;
	}
	
	public void setClassificationName(String name) {
		this.classificationName = name;
	}
	public String getClassificationName() {
		return this.classificationName;
	}
	
	public void getSuperClassification(String superClass) {
		this.superClassification = superClass;
	}
	public String getSuperClassification() {
		return this.superClassification;
	}

}
