package edu.wpi.cs.proteus.http;

public class GetAlgorithmIdByNameResponse {
	public String algorithmId;
	public String errorMessage;
	public String statusCode;

	public GetAlgorithmIdByNameResponse(String algorithmId)
	{
		this.statusCode = "200";
		this.errorMessage = "Success!";
		this.algorithmId = algorithmId;
	}

	public GetAlgorithmIdByNameResponse(String errorMessage, String statusCode)
	{
		this.algorithmId = null;
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
	}

	public GetAlgorithmIdByNameResponse()
	{
	}
}
