package edu.wpi.cs.proteus.http;

public class GetObjectsByID {
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	String ID;
	String requestedBy;

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public GetObjectsByID(String iD,String requestedBy) {
		ID = iD;
		this.requestedBy = requestedBy;
	}
	public GetObjectsByID()
	{
		ID="0";
		
	}

}
