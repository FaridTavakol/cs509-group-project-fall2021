package edu.wpi.cs.proteus.http;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddImplementationRequest {
	@JsonProperty("algorithmID") String algorithmID;
	@JsonProperty("language") String language;
	@JsonProperty("details") String details;
	@JsonProperty("url") String url;

	@JsonCreator
	public AddImplementationRequest(@JsonProperty("algorithmID") String algorithmID, @JsonProperty("language") String language, 
			@JsonProperty("details") String details, @JsonProperty("url") String url) {
		this.algorithmID = algorithmID;
		this.language = language;
		this.details = details;
		this.url = url;
	}
	
	public AddImplementationRequest() {
		
	}
	
	@Override
	public String toString() {
		return "AlgorimthID: " + this.algorithmID + ". Language: " + this.language + ". Details: " + this.details + ". URL: " + this.url;
	}

	public String getAlgorithmID() {
		return algorithmID;
	}
	public void setAlgorithmID(String algorithmID) {
		this.algorithmID = algorithmID;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
