package edu.wpi.cs.proteus.model;

import java.util.LinkedList;
import java.util.List;

public class Classification {
	
	String classificationID;
	String classificationName;
	String superClassification;
	String hNum;
	
	public Classification(String id, String name, String superClass) {
		this.classificationID = id;
		this.classificationName = name;
		this.superClassification = superClass;
		this.hNum = "0";
	}
	
	public Classification(String id, String name, String superClass, String hNum) {
		this.classificationID = id;
		this.classificationName = name;
		this.superClassification = superClass;
		this.hNum = hNum;
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
	
	public void setH(String h) {
		this.hNum = h;
	}
	public String getH() {
		return this.hNum;
	}
	public String toString() {
		String s = classificationID + ", " + classificationName + ", " + superClassification + ", " + hNum;
		return s;
	}

}
