package edu.wpi.cs.proteus.http;

import java.util.Map;

public class GetImplementationResponse {
	public Map<String, String> implementation;
	public int statusCode;
	
	public GetImplementationResponse (int statusCode, Map<String, String> implementation) {
		this.statusCode = statusCode;
		this.implementation = implementation;
	}
}
