package edu.wpi.cs.proteus.http;

import java.util.ArrayList;
import java.util.List;
import edu.wpi.cs.proteus.model.Classification;

public class AllClassificationsResponse {
	
	public final List<Classification> list;
	public final int httpCode;
	public final String error;
	
	public AllClassificationsResponse(List<Classification> list, int code) {
		this.list = list;
		this.httpCode = code;
		error = "";
	}
	
	// 200 means success
	public AllClassificationsResponse(int code, String error) {
		list = new ArrayList<Classification>();
		this.httpCode = code;
		this.error = error;
	}
	
	public String toString() {
		return "Response(" + list.size() + ")";
	}

}
