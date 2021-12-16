package edu.wpi.cs.proteus.http;

public class GetClassificationByIdRequest {
	public String classificationId;

	public GetClassificationByIdRequest(String classificationId)
	{
		this.classificationId = classificationId;
	}

	public String getClassificationId()
	{
		return this.classificationId;
	}

}
