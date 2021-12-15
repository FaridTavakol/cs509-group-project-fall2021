package edu.wpi.cs.proteus.model;

public class Log {

	int ID;
	String activityPerformedBy;
	String activityPerformed;
	String Date;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getActivityPerformedBy() {
		return activityPerformedBy;
	}
	public void setActivityPerformedBy(String activityPerformedBy) {
		this.activityPerformedBy = activityPerformedBy;
	}
	public Log() {
		super();
	}
	public Log(String activityPerformedBy, String activityPerformed, String date) {
		super();
		this.activityPerformedBy = activityPerformedBy;
		this.activityPerformed = activityPerformed;
		Date = date;
	}
	public String getActivityPerformed() {
		return activityPerformed;
	}
	public void setActivityPerformed(String activityPerformed) {
		this.activityPerformed = activityPerformed;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	

	public Log(int iD, String activityPerformedBy, String activityPerformed, String date) {
		super();
		ID = iD;
		this.activityPerformedBy = activityPerformedBy;
		this.activityPerformed = activityPerformed;
		Date = date;
	}
	
}
