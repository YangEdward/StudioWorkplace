package com.bestride.data.post;

import java.util.List;

import com.bestride.data.helper.ReportDetail;

public class ReportPost {
	private String session_id;
	private int number;
	private String rmroomid;
    private String roomno;
    private String realname;
    private String userid;
    private String hotelcode;
	private String tradecode;
	private String tradedesc;
    private int reportFlag;
	private List<ReportDetail> billArray;
	
	
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getRmroomid() {
		return rmroomid;
	}
	public void setRmroomid(String rmroomid) {
		this.rmroomid = rmroomid;
	}
	public String getTradecode() {
		return tradecode;
	}
	public void setTradecode(String tradecode) {
		this.tradecode = tradecode;
	}
	public String getTradedesc() {
		return tradedesc;
	}
	public void setTradedesc(String tradedesc) {
		this.tradedesc = tradedesc;
	}
	public List<ReportDetail> getBillArray() {
		return billArray;
	}
	public void setBillArray(List<ReportDetail> billArray) {
		this.billArray = billArray;
	}

    public int getReportFlag() {
        return reportFlag;
    }

    public void setReportFlag(int reportFlag) {
        this.reportFlag = reportFlag;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getHotelcode() {
        return hotelcode;
    }

    public void setHotelcode(String hotelcode) {
        this.hotelcode = hotelcode;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
