package com.bestride.waiterwork;

import android.app.Application;

public class HotelApplication extends Application {

	private boolean isDispatch = false;
	private boolean isManHotel = false;
	public static String sessionId;
	private String userName;
	private String userCode;
    private String userId;
	private String hotelcode;
	public static boolean isLeader = false;
	private int role;

	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//app = this;
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	public boolean isDispatch() {
		return isDispatch;
	}

	public void setDispatch(boolean isDispatch) {
		this.isDispatch = isDispatch;
	}

	public boolean isManHotel() {
		return isManHotel;
	}

	public void setManHotel(boolean isManHotel) {
		this.isManHotel = isManHotel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHotelcode() {
		return hotelcode;
	}

	public void setHotelcode(String hotelcode) {
		this.hotelcode = hotelcode;
	}
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
