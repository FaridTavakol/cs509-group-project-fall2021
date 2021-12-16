package edu.wpi.cs.proteus.http;

public class GetClassificationByIdResponse {
	public final String errorMessage;
	public final String statusCode;
	public final String classificationName;

	GetClassificationByIdResponse(String classificaitonName)
	{
		this.errorMessage = "Success!";
		this.statusCode = "200";
		this.classificationName = classificaitonName;

	}

	GetClassificationByIdResponse(String statusCode, String errorMessage)
	{
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
		this.classificationName = null;

	}

}
