package com.bestride.data.back;

import com.bestride.helper.FinalValue;

public class RoomBack {
	private int message_code;
	private String message_info;
	private int number;
	public RoomBack(int message_code, String message_info, int number) {
		super();
		this.message_code = message_code;
		this.message_info = message_info;
		this.number = number;
	}
	public int getMessage_code() {
		return message_code;
	}
	public void setMessage_code(int message_code) {
		this.message_code = message_code;
	}
	
	public String getMessage_info() {
		return message_info;
	}
	public void setMessage_info(String message_info) {
		this.message_info = message_info;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean isSuccess(){
		if(message_code == FinalValue.SUCCESS_CODE){
			return true;
		}else{
			return false;
		}
	}
}
