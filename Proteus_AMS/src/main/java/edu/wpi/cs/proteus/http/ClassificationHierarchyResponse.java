package edu.wpi.cs.proteus.http;

import java.util.LinkedList;
import java.util.List;
import edu.wpi.cs.proteus.model.Classification;

public class ClassificationHierarchyResponse {
	
	public final List<Classification> list;
	public final int httpCode;
	public final String error;
	
	public ClassificationHierarchyResponse(List<Classification> list, int code) {
		this.list = list;
		this.httpCode = code;
		error = "";
	}
	
	// 200 means success
	public ClassificationHierarchyResponse(int code, String error) {
		list = new LinkedList<Classification>();
		this.httpCode = code;
		this.error = error;
	}
	
	public String toString() {
		return "Response(" + list.size() + ")";
	}

}
