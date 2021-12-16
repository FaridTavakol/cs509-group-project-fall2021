package edu.wpi.cs.proteus.http;

public class GetClassificationRequest {
	
	String classificationName;
	
	public GetClassificationRequest() {
	}
	
	public GetClassificationRequest (String name) {
		this.classificationName = name;
	}
	
	public String getClassificationName( ) { return classificationName; }
	public void setClassificationName(String classificationName) { this.classificationName = classificationName; }
	
	public String toString() {
		return "AddClassification(" + classificationName + ")";
	}

}
