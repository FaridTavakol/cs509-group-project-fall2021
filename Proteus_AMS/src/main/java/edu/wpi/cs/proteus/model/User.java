package edu.wpi.cs.proteus.model;

public class User {
	String Name;
	String Email;
	String Password;

	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	public User (String n, String e,String p) {
		this.Name = n;
		this.Email = e;
		this.Password = p;
	}
	
	public User() {
	}
}
