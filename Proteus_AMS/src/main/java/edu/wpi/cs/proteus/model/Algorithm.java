//*****************
package edu.wpi.cs.proteus.model;

public class Algorithm {

	public Algorithm(String algorithmName_, String classificationId_, String AlgorithmId_)
	{
		this.algorithmName = algorithmName_;
		this.classificationId = classificationId_;
		this.algorithmId = AlgorithmId_;
//		this.algorithmDescription = description;
	}

	public Algorithm()
	{
	}

	// Members
	String algorithmName;
	String classificationId;
	String algorithmId;
	// String algorithmDescription;

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

	public void setAlgorithmId(String algorithmId_)
	{
		this.algorithmId = algorithmId_;
	}

	public String getAlgorithmId()
	{
		return this.algorithmId;
	}
}
