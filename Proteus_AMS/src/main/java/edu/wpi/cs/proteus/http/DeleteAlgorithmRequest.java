package edu.wpi.cs.proteus.http;

public class DeleteAlgorithmRequest {

	String name;
	String algorithmId;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAlgorithmID()
	{
		return algorithmId;
	}

	public void setAlgorithmID(String algorithmID)
	{
		this.algorithmId = algorithmID;
	}

	public DeleteAlgorithmRequest()
	{
	}

	public DeleteAlgorithmRequest(String name, String algorithmID)
	{
		this.name = name;
		this.algorithmId = algorithmID;
	}

	public String toString()
	{
		return "DeleteAlgorithm(" + name + ")";
	}
}
