package edu.wpi.cs.proteus.http;

public class RegisterResponse {
	public String Message;
	public int statusCode;
		
	public RegisterResponse (int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.Message = errorMessage;
	}


}
