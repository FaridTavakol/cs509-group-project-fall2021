package edu.wpi.cs.proteus.http;

public class AddImplementationRequest {
	String algorithmName;
	String language;
	String details;

	public AddImplementationRequest(String algorithmName, String language) {
		this.algorithmName = algorithmName;
		this.language = language;
	}

	public AddImplementationRequest(String algorithmName, String language, String details) {
		this.algorithmName = algorithmName;
		this.language = language;
		this.details = details;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}
	public void setAlgorithmName(String algorithmName) {
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
}
