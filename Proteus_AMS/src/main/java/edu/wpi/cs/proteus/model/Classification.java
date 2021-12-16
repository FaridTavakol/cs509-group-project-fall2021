package edu.wpi.cs.proteus.model;

import java.util.LinkedList;
import java.util.List;

public class Classification {
	
	String classificationID;
	String classificationName;
	String ugh = "";
	String superClassification;
	List<Classification> subClassifications;
	
	public Classification(String id, String name, String superClass) {
		this.classificationID = id;
		this.classificationName = name;
		this.superClassification = superClass;
		this.subClassifications = new LinkedList<Classification>();
	}
	
	public Classification(String id, String name, String superClass, List<Classification> subClasses) {
		this.classificationID = id;
		this.classificationName = name;
		this.superClassification = superClass;
		this.subClassifications = subClasses;
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
	public String toString() {
		String s = classificationID + ", " + classificationName + ", " + superClassification + ", " + subClassifications;
		return s;
	}

}
