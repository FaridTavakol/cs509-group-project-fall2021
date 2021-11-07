package edu.wpi.cs.proteus.http;

public class Response {
	public String Message;
	public int statusCode;
	
	public Response (int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.Message = errorMessage;
	}
}
