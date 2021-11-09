package edu.wpi.cs.proteus.http;

public class AddAlgorithmResponse {

	public final String Message;
	public final int statusCode;

	public AddAlgorithmResponse(String errorMessage, int statusCode)
	{

		this.statusCode = statusCode;
		this.Message = errorMessage;
	}

	public AddAlgorithmResponse(String errorMessage)
	{

		this.Message = errorMessage;
		this.statusCode = 200;
	}

	public String toString()
	{
		return "Response(" + Message + ")";
	}

}
