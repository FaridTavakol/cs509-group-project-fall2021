package edu.wpi.cs.proteus.model;

public class User {
	String name;
	String email;
	String password;
	String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public User (String n, String e,String p,String r) {
		this.name = n;
		this.email = e;
		this.password = p;
		this.role = r;
	}
	
	public User() {
	}
}
