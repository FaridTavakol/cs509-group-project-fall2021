package edu.wpi.cs.proteus.model;

import java.util.List;

public class Implementation {

	String id;
	String url;
	String details;
	String language;
	Algorithm algorithm;
	List<Benchmark> benchmarks;

	public Implementation(String id, String url, String language, Algorithm algorithm,
			List<Benchmark> benchmarks) {
		this.id = id;
		this.url = url;
		this.language = language;
		this.algorithm = algorithm;
		this.benchmarks = benchmarks;
	}

	public Implementation(String id, String url, String details, String language, Algorithm algorithm,
			List<Benchmark> benchmarks) {
		this.id = id;
		this.url = url;
		this.details = details;
		this.language = language;
		this.algorithm = algorithm;
		this.benchmarks = benchmarks;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public List<Benchmark> getBenchmarks() {
		return benchmarks;
	}

	public void setBenchmarks(List<Benchmark> benchmarks) {
		this.benchmarks = benchmarks;
	}

}
