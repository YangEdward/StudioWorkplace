package com.bestride.data.post;


public class RoomPost {

	private String username;
	private String hotelcode;
	private String session_id;
	private String  typeid;
	private int roomstatus;
	public RoomPost(String username, String hotelcode, String session_id,
			String typecode,int roomstatus) {
		super();
		this.username = username;
		this.hotelcode = hotelcode;
		this.session_id = session_id;
		this.typeid = typecode;
		this.roomstatus = roomstatus;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHotelcode() {
		return hotelcode;
	}
	public void setHotelcode(String hotelcode) {
		this.hotelcode = hotelcode;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getTypecode() {
		return typeid;
	}
	public void setTypecode(String typecode) {
		this.typeid = typecode;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public int getRoomstatus() {
		return roomstatus;
	}
	public void setRoomstatus(int roomstatus) {
		this.roomstatus = roomstatus;
	}

}
