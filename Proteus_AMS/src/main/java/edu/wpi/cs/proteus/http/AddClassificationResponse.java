package edu.wpi.cs.proteus.http;

public class AddClassificationResponse {
	public final String response;
	public final int httpCode;
	
	public AddClassificationResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public AddClassificationResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
	
}
