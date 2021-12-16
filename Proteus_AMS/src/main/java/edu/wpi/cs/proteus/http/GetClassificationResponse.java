package edu.wpi.cs.proteus.http;

import edu.wpi.cs.proteus.model.Classification;

public class GetClassificationResponse {
	
	public final Classification classification;
	public final int httpCode;
	public final String error;
	
	public GetClassificationResponse(Classification classification, int code) {
		this.classification = classification;
		this.httpCode = code;
		error = "";
	}
	
	// 200 means success
	public GetClassificationResponse(int code, String error) {
		this.classification = new Classification();
		this.httpCode = code;
		this.error = error;
	}
	
	public String toString() {
		return "Response(" + classification.getClassificationName() + ")";
	}

}
