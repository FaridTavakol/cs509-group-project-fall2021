package edu.wpi.cs.proteus.http;

public class RegisterRequest {
	String name;
	String email;
	String password;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
	public RegisterRequest (String n, String e,String p) {
		this.name = n;
		this.email = e;
		this.password = p;
	}
	
	public RegisterRequest() {
	}

}
