package edu.wpi.cs.proteus.http;

public class GetAlgorithmByIdResponse {

	public final String algorithmName;
	public final String algorithmId;
	public final String classificationId;
	public final String error;
	public final String message;

	public GetAlgorithmByIdResponse(String name, String algoId, String ClassId)
	{
		this.algorithmId = algoId;
		this.classificationId = ClassId;
		this.algorithmName = name;
		this.error = "200"; // Success
		this.message = "Success!";
	}

	public GetAlgorithmByIdResponse()
	{
		this.algorithmId = "";
		this.classificationId = "";
		this.algorithmName = "";
		this.error = "400"; // Failure
		this.message = "Failure!";

	}

	public GetAlgorithmByIdResponse(String message)
	{
		this.algorithmId = "";
		this.classificationId = "";
		this.algorithmName = "";
		this.error = "422"; // Failure
		this.message = message;

	}

}
