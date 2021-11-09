package edu.wpi.cs.proteus.http;

public class AddAlgorithmResponse {

	public String Message;
	public int statusCode;

	public AddAlgorithmResponse(int statusCode, String errorMessage)
	{

		this.statusCode = statusCode;
		this.Message = errorMessage;
	}

}
