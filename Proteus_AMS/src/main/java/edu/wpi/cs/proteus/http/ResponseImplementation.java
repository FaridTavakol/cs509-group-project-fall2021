package edu.wpi.cs.proteus.http;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseImplementation {

	@JsonProperty("implementationID") String implementationID;
	@JsonProperty("url") String url;
	@JsonProperty("details") String details;
	@JsonProperty("language") String language;
	@JsonProperty("algorithmID") String algorithmID;


	@JsonCreator
	public ResponseImplementation(@JsonProperty("implementationID") String implementationID, @JsonProperty("url")String url, 
			@JsonProperty("details") String details, @JsonProperty("language") String language, @JsonProperty("algorithmID")String algorithmID) {
		this.implementationID = implementationID;
		this.url = url;
		this.details = details;
		this.language = language;
		this.algorithmID = algorithmID;
	}
	
	public ResponseImplementation() {
		
	}

	public String getImplementationID() {
		return this.implementationID;
	}

	public void setImplementationID(String id) {
		this.implementationID = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAlgorithmID() {
		return this.algorithmID;
	}

	public void setAlgorithmID(String algorithmID) {
		this.algorithmID = algorithmID;
	}
}
