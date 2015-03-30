package com.bestride.data.post;

public class HandlePost {

	private String workid;
	private String hoteltype;
	private String session_id;
	private int handle;
	
	
	public HandlePost(String workid, String hoteltype, String session_id,
			int handle) {
		super();
		this.workid = workid;
		this.hoteltype = hoteltype;
		this.session_id = session_id;
		this.handle = handle;
	}
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}

    public String getHoteltype() {
        return hoteltype;
    }

    public void setHoteltype(String hoteltype) {
        this.hoteltype = hoteltype;
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
