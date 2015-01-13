package com.bestride.data.post;

public class WorkPost {

	private String session_id;
	private String username;
	private int role;
	public WorkPost(String session_id, String username, int role) {
		super();
		this.session_id = session_id;
		this.username = username;
		this.role = role;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
}
