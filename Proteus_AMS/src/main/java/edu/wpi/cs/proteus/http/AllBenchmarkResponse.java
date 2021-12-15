package edu.wpi.cs.proteus.http;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.proteus.model.Benchmark;

public class AllBenchmarkResponse {
	
	public final List<Benchmark> list;
	public final int httpCode;
	public final String error;
	
	public AllBenchmarkResponse(List<Benchmark> list, int code) {
		this.list = list;
		this.httpCode = code;
		error = "";
	}
	
	// 200 means success
	public AllBenchmarkResponse(int code, String error) {
		list = new ArrayList<Benchmark>();
		this.httpCode = code;
		this.error = error;
	}
	

}
