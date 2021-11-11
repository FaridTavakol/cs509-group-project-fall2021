package edu.wpi.cs.proteus.http;

public class GetImplementationResponse {
	public ResponseImplementation implementation;
	public int statusCode;
	
	public GetImplementationResponse (int statusCode, ResponseImplementation implementation) {
		this.statusCode = statusCode;
		this.implementation = implementation;
	}
}
