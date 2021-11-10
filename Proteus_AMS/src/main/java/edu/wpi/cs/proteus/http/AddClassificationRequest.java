package edu.wpi.cs.proteus.http;

public class AddClassificationRequest {
	
	String classificationID;
	String classificationName;
	String superClassification;
	
	public AddClassificationRequest() {
	}
	
	public AddClassificationRequest (String id, String name, String superClass) {
		this.classificationID = id;
		this.classificationName = name;
		this.superClassification = superClass;
	}
	
	public String getClassificationID( ) { return classificationID; }
	public void setClassificationID(String classificationID) { this.classificationID = classificationID; }
	
	public String getClassificationName( ) { return classificationName; }
	public void setClassificationName(String classificationName) { this.classificationName = classificationName; }
	
	public String getSuperClassification( ) { return superClassification; }
	public void setSuperClassification(String superClassification) { this.superClassification = superClassification; }
	
	
	public String toString() {
		return "AddClassification(" + classificationID + ", " + classificationName + ", " + superClassification + ")";
	}

}