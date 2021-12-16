package edu.wpi.cs.proteus.http;

public class MergeClassificationRequest {
	
	String classificationOne;
	String classificationTwo;
	
	public MergeClassificationRequest() {
	}
	
	public MergeClassificationRequest (String one, String two) {
		this.classificationOne = one;
		this.classificationTwo = two;
	}
	
	public String getClassificationOne( ) { return classificationOne; }
	public void setClassificationOne(String one) { this.classificationOne = one; }
	
	public String getClassificationTwo( ) { return classificationTwo; }
	public void setClassificationTwo(String two) { this.classificationTwo = two; }
	
	public String toString() {
		return "AddClassification(" + classificationOne + ", " + classificationTwo + ")";
	}

}
