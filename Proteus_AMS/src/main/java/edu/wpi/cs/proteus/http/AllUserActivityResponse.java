package edu.wpi.cs.proteus.http;

import java.util.ArrayList;
import java.util.List;
import edu.wpi.cs.proteus.model.Log;



public class AllUserActivityResponse {

	public final List<Log> list;
	public final int httpCode;
	public final String error;
	
	public AllUserActivityResponse(List<Log> list, int code) {
		this.list = list;
		this.httpCode = code;
		error = "";
	}
	
	// 200 means success
	public AllUserActivityResponse(int code, String error) {
		list = new ArrayList<Log>();
		this.httpCode = code;
		this.error = error;
	}

}
