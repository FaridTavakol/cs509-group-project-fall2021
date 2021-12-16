package edu.wpi.cs.proteus.http;

public class GetClassificationByIdRequest {
	public String classificationId;
	
	public GetClassificationByIdRequest() {
	}

	public GetClassificationByIdRequest(String classificationId)
	{
		this.classificationId = classificationId;
	}

	public String getClassificationId()
	{
		return this.classificationId;
	}
	
	public void setClassificationId(String classificationId) { this.classificationId = classificationId; }

}
