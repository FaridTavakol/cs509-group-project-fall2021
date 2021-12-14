package edu.wpi.cs.proteus.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.proteus.model.PI;

public class AllPIResponse {
	public final List<PI> list;
	public final int httpCode;
	public final String error;
	
	public AllPIResponse(List<PI> list, int code) {
		this.list = list;
		this.httpCode = code;
		error = "";
	}
	
	// 200 means success
	public AllPIResponse(int code, String error) {
		list = new ArrayList<PI>();
		this.httpCode = code;
		this.error = error;
	}
	

}
