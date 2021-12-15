package edu.wpi.cs.proteus.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.proteus.model.Implementation;

public class AllImplementationsResponse {
	public final List<Implementation> list;
	public final int httpCode;
	public final String error;
	
	public AllImplementationsResponse(List<Implementation> list, int code) {
		this.list = list;
		this.httpCode = code;
		error = "";
	}
	
	// 200 means success
	public AllImplementationsResponse(int code, String error) {
		list = new ArrayList<Implementation>();
		this.httpCode = code;
		this.error = error;
	}


}
