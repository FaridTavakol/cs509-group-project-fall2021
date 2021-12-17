package edu.wpi.cs.proteus.http;

public class GetAlgorithmIdByNameRequest {

	public String algorithmName;

	public GetAlgorithmIdByNameRequest()
	{
	};

	public GetAlgorithmIdByNameRequest(String algorithmName)
	{
		this.algorithmName = algorithmName;
	}

	public String getAlgorithmName()
	{
		return this.algorithmName;
	}

}
