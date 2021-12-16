package edu.wpi.cs.proteus.http;

public class DeleteAlgorithmRequest {

	public String algorithmId;

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

	public DeleteAlgorithmRequest(String algorithmID)
	{
		this.algorithmId = algorithmID;
	}

	public String toString()
	{
		return "DeleteAlgorithm(" + algorithmId + ")";
	}
}
