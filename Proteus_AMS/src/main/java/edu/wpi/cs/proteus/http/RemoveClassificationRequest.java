package edu.wpi.cs.proteus.http;

public class RemoveClassificationRequest {
	
	String classificationName;
	
	public RemoveClassificationRequest() {
	}
	
	public RemoveClassificationRequest (String name) {
		this.classificationName = name;
	}
	
	public String getClassificationName( ) { return classificationName; }
	public void setClassificationName(String classificationName) { this.classificationName = classificationName; }
	
	public String toString() {
		return "AddClassification(" + classificationName + ")";
	}

}
