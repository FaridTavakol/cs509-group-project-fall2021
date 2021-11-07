package edu.wpi.cs.proteus.http;

public class LoginRequest {

	String email;
	String password;

	
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

	public LoginRequest (String e,String p) {
		this.email = e;
		this.password = p;
	}
	
	public LoginRequest() {
	}

}
