package edu.wpi.cs.proteus.http;

public class ReclassifyAlgorithmRequest {
	public String classificationId;
	public String algorithmId;

	public ReclassifyAlgorithmRequest(final String algorithmId, final String classificationId)
	{
		this.classificationId = classificationId;
		this.algorithmId = algorithmId;
	}

	public String getClassificationId()
	{
		return this.classificationId;
	}

	public String getAlgorithmId()
	{
		return this.algorithmId;
	}

	public ReclassifyAlgorithmRequest()
	{
	}
}