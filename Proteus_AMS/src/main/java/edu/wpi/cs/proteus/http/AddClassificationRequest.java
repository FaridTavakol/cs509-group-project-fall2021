package edu.wpi.cs.proteus.http;

public class AddClassificationRequest {
	
	String classificationName;
	String superClassification;
	
	public AddClassificationRequest() {
	}
	
	public AddClassificationRequest (String name, String superClass) {
		this.classificationName = name;
		this.superClassification = superClass;
	}
	
	public String getClassificationName( ) { return classificationName; }
	public void setClassificationName(String classificationName) { this.classificationName = classificationName; }
	
	public String getSuperClassification( ) { return superClassification; }
	public void setSuperClassification(String superClassification) { this.superClassification = superClassification; }
	
	
	public String toString() {
		return "AddClassification(" + classificationName + ", " + superClassification + ")";
	}

}
