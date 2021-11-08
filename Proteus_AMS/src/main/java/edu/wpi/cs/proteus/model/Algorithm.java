package edu.wpi.cs.proteus.model;

public class Algorithm {
	String id;
	String name;
	
	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public Algorithm(String name) {
		this.name = name;
	}
}
