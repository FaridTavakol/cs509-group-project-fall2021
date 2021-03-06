package edu.wpi.cs.proteus.model;

import java.util.List;

public class Implementation {

	String id;
	String url;
	String details;
	String language;
	String algorithmID;
	List<String> benchmarkIDs;

	public Implementation(String id, String url, String details, String language, String algorithmID,
			List<String> benchmarkIDs) {
		this.id = id;
		this.url = url;
		this.details = details;
		this.language = language;
		this.algorithmID = algorithmID;
		this.benchmarkIDs = benchmarkIDs;
	}
	
	public Implementation() {
		
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public List<String> getBenchmarkIDs() {
		return this.benchmarkIDs;
	}

	public void setBenchmarkIDs(List<String> benchmarkIDs) {
		this.benchmarkIDs = benchmarkIDs;
	}

}

