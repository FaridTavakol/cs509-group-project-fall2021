package edu.wpi.cs.proteus.http;

public class GetAlgorithmByIdRequest {

	public String algorithmId;

	public GetAlgorithmByIdRequest(String Id)
	{
		this.algorithmId = Id;
	}

	public GetAlgorithmByIdRequest()
	{

	}

	public String getID()
	{
		return this.algorithmId;
	}

	public void setID(String Id)
	{
		this.algorithmId = Id;
	}

}
