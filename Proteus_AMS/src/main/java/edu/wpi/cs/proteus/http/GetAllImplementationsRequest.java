package edu.wpi.cs.proteus.http;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAllImplementationsRequest {
	@JsonProperty("algorithmID") String algorithmID;
	@JsonProperty("requestedBy") String requestedBy;

	public String getAlgorithmID() {
		return this.algorithmID;
	}

	public void setAlgorithmID(String id) {
		this.algorithmID = id;
	}
	
	public String getRequestedBy() {
		return this.requestedBy;
	}
	
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	
	@JsonCreator
	public GetAllImplementationsRequest(@JsonProperty("algorithmID") String algorithmID, @JsonProperty("requestedBy") String requestedBy) {
		this.algorithmID = algorithmID;
		this.requestedBy = requestedBy;
	}
	
	public GetAllImplementationsRequest() {
		
	}
	
	@Override
	public String toString() {
		return "[GetAllImplementationsRequest] algorithmID: " + this.algorithmID + ", requestedBy: " + this.requestedBy;
	}

}
