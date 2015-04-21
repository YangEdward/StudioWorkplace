package com.bestride.data.back;

import java.util.List;
import java.util.Map;

import com.bestride.data.helper.MenuItemU;
import com.bestride.helper.FinalValue;

public class LoginEntity {
	private int messageCode;
	private String messageInfo;
	private String realname;
    private String userId;
	private String sessionId;
    private String hotelcode;
	private Map<String,MenuItemU> menuPermissions;
	private Map<String,List<String>> rmPermissions;
	
	public int getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Map<String, MenuItemU> getMenuPermissions() {
		return menuPermissions;
	}
	public void setMenuPermissions(Map<String, MenuItemU> menuPermissions) {
		this.menuPermissions = menuPermissions;
	}
	
	public Map<String, List<String>> getRmPermissions() {
		return rmPermissions;
	}
	public void setRmPermissions(Map<String, List<String>> rmPermissions) {
		this.rmPermissions = rmPermissions;
	}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHotelcode() {
        return hotelcode;
    }

    public void setHotelcode(String hotelcode) {
        this.hotelcode = hotelcode;
    }

    public boolean isSuccess(){
		if(messageCode == FinalValue.SUCCESS_CODE){
			return true;
		}else{
			return false;
		}
	}

}
