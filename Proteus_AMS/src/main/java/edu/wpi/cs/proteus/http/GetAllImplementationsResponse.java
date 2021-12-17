package edu.wpi.cs.proteus.http;

import java.util.List;
import java.util.Map;

public class GetAllImplementationsResponse {
	public List<Map<String, String>> implementations;
	public int statusCode;
	
	public GetAllImplementationsResponse (int statusCode, List<Map<String, String>> implementations) {
		this.statusCode = statusCode;
		this.implementations = implementations;
	}
}
