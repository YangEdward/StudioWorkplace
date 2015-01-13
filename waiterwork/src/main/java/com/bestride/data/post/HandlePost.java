package com.bestride.data.post;

public class HandlePost {

	private String workid;
	private String typecode;
	private String session_id;
	private int handle;
	
	
	public HandlePost(String workid, String typecode, String session_id,
			int handle) {
		super();
		this.workid = workid;
		this.typecode = typecode;
		this.session_id = session_id;
		this.handle = handle;
	}
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}
	public String getTypecode() {
		return typecode;
	}
	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public int getHandle() {
		return handle;
	}
	public void setHandle(int handle) {
		this.handle = handle;
	}
	
}
