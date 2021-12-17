package edu.wpi.cs.proteus.http;

public class GetClassificationByIdResponse {
	public final String errorMessage;
	public final String statusCode;
	public final String classificationName;

	public GetClassificationByIdResponse(String classificationName)
	{
		this.errorMessage = "Success!";
		this.statusCode = "200";
		this.classificationName = classificationName;

	}

	public GetClassificationByIdResponse(String statusCode, String errorMessage)
	{
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
		this.classificationName = null;

	}

}
