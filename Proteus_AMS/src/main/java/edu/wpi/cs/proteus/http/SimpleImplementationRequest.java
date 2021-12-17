package edu.wpi.cs.proteus.http;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleImplementationRequest {
	@JsonProperty("implementationID") String implementationID;
	@JsonProperty("requestedBy") String requestedBy;

	public String getImplementationID() {
		return this.implementationID;
	}

	public void setImplementationID(String id) {
		this.implementationID = id;
	}
	
	public String getRequestedBy() {
		return this.requestedBy;
	}
	
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	
	@JsonCreator
	public SimpleImplementationRequest(@JsonProperty("implementationID") String implementationID, @JsonProperty("requestedBy") String requestedBy) {
		this.implementationID = implementationID;
		this.requestedBy = requestedBy;
	}
	
	public SimpleImplementationRequest() {
		
	}
	
//	@Override
//	public String toString() {
//		return "[GetImplementationRequest] implementationID: " + this.implementationID;
//	}

}
