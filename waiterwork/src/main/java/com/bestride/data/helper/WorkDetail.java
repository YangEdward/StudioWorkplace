package com.bestride.data.helper;

public class WorkDetail {
	
	private String workid = "23";
    private String roomid;
	private String roomno = "1001";
	private String typecode = "CS";
	private int floor = 10;
	private String dispatchingdate = "2014-12-08 12:08";
	private String completiondate = "2014-12-08 12:08";
	private String acceptancedate = "2014-12-08 12:08";
	private int taskstate = 1;
	public WorkDetail() {
		super();
	}
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getTypecode() {
		return typecode;
	}
	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getDispatchingdate() {
		return dispatchingdate;
	}
	public void setDispatchingdate(String dispatchingdate) {
		this.dispatchingdate = dispatchingdate;
	}
	public String getCompletiondate() {
		return completiondate;
	}
	public void setCompletiondate(String completiondate) {
		this.completiondate = completiondate;
	}
	public String getAcceptancedate() {
		return acceptancedate;
	}
	public void setAcceptancedate(String acceptancedate) {
		this.acceptancedate = acceptancedate;
	}
	public int getTaskstate() {
		return taskstate;
	}
	public void setTaskstate(int taskstate) {
		this.taskstate = taskstate;
	}

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
}
