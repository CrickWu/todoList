package com.demo.dao;

public class UserEntry {
	private String user;
	private String password;
	private int id;

	public UserEntry(int id, String user, String password) {
		// TODO 自动生成的构造函数存根
		this.user = user;
		this.password = password;
		this.id = id;
	}
	@Override
	public boolean equals(Object o) {
		UserEntry userEntry = (UserEntry) o;
		if (this.user == null || this.password == null)
			return false;
		if (this.user.equals(userEntry.user)
				&& this.password.equals(userEntry.password))
			return true;
		return false;
	}
	
	public UserEntry(String user, String password) {
		// TODO 自动生成的构造函数存根
		this.user = user;
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
	public String getUser() {
		return this.user;
	}
	public int getId() {
		return id;
	}
}
