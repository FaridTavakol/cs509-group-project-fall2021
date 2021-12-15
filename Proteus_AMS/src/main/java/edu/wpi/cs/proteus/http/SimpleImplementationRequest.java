package edu.wpi.cs.proteus.http;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleImplementationRequest {
	@JsonProperty("implementationID") String implementationID;

	public String getImplementationID() {
		return this.implementationID;
	}

	public void setImplementationID(String id) {
		this.implementationID = id;
	}
	
	@JsonCreator
	public SimpleImplementationRequest(@JsonProperty("implementationID") String implementationID) {
		this.implementationID = implementationID;
	}
	
	public SimpleImplementationRequest() {
		
	}
	
//	@Override
//	public String toString() {
//		return "[GetImplementationRequest] implementationID: " + this.implementationID;
//	}

}
