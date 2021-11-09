package edu.wpi.cs.proteus.http;

public class AddAlgorithmRequest {

	// Members
	String algorithmName;
	String classificationId;
	String algorithmId;

	// Methods
	public String getAlgorithmName()
	{
		return this.algorithmName;
	}

	public void setAlgorithmName(String name_)
	{
		this.algorithmName = name_;
	}

	public String getClassificationId()
	{
		return this.classificationId;
	}

	public void setClassificationId(String classificationId_)
	{
		this.classificationId = classificationId_;
	}

	public String getAlgorithmId()
	{
		return this.algorithmId;
	}

	public void setAlgorithmId(String AlgorithmId_)
	{
		this.algorithmId = AlgorithmId_;
	}

	// Constructor

	public AddAlgorithmRequest(String algorithmName_, String classificationId_, String algorithmId_)
	{
		this.algorithmName = algorithmName_;
		this.classificationId = classificationId_;
		this.algorithmId = algorithmId_;
	}

	public AddAlgorithmRequest()
	{
	}

}
