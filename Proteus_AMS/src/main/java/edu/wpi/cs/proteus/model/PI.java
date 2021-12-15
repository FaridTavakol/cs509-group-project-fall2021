package edu.wpi.cs.proteus.model;

public class PI {
	String algorithmID;
	String Name;
	String Description;
	String URL;
	String fileContent;
	String requestedBy;
	String ID;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getAlgorithmID() {
		return algorithmID;
	}
	public void setAlgorithmID(String algorithmID) {
		this.algorithmID = algorithmID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent.trim();
	}
	
	public PI(String ID,String algorithmID, String name, String description, String uRL,
			String fileContent,String requestedBy) {
		this.ID=ID;
		this.algorithmID = algorithmID;
		Name = name;
		Description = description;
		URL = uRL;
		this.fileContent = fileContent.trim();
		this.requestedBy=requestedBy;
	}

	public PI()
	{
		
	}

}
