package edu.wpi.cs.proteus.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.proteus.model.User;

public class AllUsersResponse {
	
	public final List<User> list;
	public final int httpCode;
	public final String error;
	
	public AllUsersResponse(List<User> list, int code) {
		this.list = list;
		this.httpCode = code;
		error = "";
	}
	
	// 200 means success
	public AllUsersResponse(int code, String error) {
		list = new ArrayList<User>();
		this.httpCode = code;
		this.error = error;
	}


	
}
