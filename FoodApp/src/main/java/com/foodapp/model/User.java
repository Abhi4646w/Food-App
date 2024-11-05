package com.foodapp.model;

public class User
{
	private int userId;
	private String username;
	private String email;
	private String password;
	private String address;
	private int mobile;
	private String role;
	public int getUser_id() {
		return userId;
	}
	public void setUser_id(int user_id) {
		this.userId = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public User(String username, String email, String password, String address, int mobile) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;
		this.mobile = mobile;
	}
	public User() {
		super();
	}
	public User(int int1, String string, String string2, String string3, String string4, int int2, String string5) {
		// TODO Auto-generated constructor stub
		super();
		this.userId = int1;
		this.username = string;
		this.email = string2;
		this.password = string3;
		this.address = string4;
		this.mobile = int2;
		this.role = string5;
	}
	
	
	@Override
	public String toString() {
		return "User [user_id=" + userId + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", address=" + address + ", mobile=" + mobile + ", role=" + role + "]";
	}
	
	
	

}
