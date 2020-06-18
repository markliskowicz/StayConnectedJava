package edu.stayConnected.FBO;

import java.util.ArrayList;

public class Account {
	String Name;
	String Rnumber;
	String email;
	String password;
	String confirmPassword;
	String code;
	String role;
	// ArrayList<EmploymentHistory> history;
	//ArrayList<Skill> skills;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRnumber() {
		return Rnumber;
	}

	public void setRnumber(String rnumber) {
		Rnumber = rnumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
