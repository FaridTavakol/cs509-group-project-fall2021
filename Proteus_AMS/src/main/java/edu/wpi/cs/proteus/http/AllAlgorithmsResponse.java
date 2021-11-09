package edu.wpi.cs.proteus.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.proteus.model.Algorithm;

public class AllAlgorithmsResponse {

	public final List<Algorithm> list;
	public final int statusCode;
	public final String error;

	public AllAlgorithmsResponse(List<Algorithm> list, int code)
	{
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}

	public AllAlgorithmsResponse(int code, String errorMessage)
	{
		this.list = new ArrayList<Algorithm>();
		this.statusCode = code;
		this.error = errorMessage;
	}

	public boolean equals(AllAlgorithmsResponse resp)
	{
		return (this.statusCode == resp.statusCode && this.error.equals(resp.error) && this.list.equals(resp.list));
	}

	public String toString()
	{
		if (list == null)
		{
			return "NoAlgorithms";
		}
		return "AllAlgorithms(" + list.size() + ")";
	}

}
