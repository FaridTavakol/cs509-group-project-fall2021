package edu.wpi.cs.proteus.http;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddImplementationRequest {
	@JsonProperty("algorithmName") String algorithmName;
	@JsonProperty("language") String language;
	@JsonProperty("details") String details;
	@JsonProperty("url") String url;

	@JsonCreator
	public AddImplementationRequest(@JsonProperty("algorithmName") String algorithmName, @JsonProperty("language") String language, 
			@JsonProperty("details") String details, @JsonProperty("url") String url) {
		this.algorithmName = algorithmName;
		this.language = language;
		this.details = details;
		this.url = url;
	}
	
	public AddImplementationRequest() {
		
	}
	
	@Override
	public String toString() {
		return "AlgorimthName: " + this.algorithmName + ". Language: " + this.language + ". Details: " + this.details + ". URL: " + this.url;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}
	public void setAalgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
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
