package com.bestride.data.back;

import com.bestride.helper.FinalValue;

public class BillBack {

	private int message_code;
	private String message_info;
	public BillBack(int message_code, String message_info) {
		super();
		this.message_code = message_code;
		this.message_info = message_info;
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
	public boolean isSuccess(){
		if(message_code == FinalValue.SUCCESS_CODE){
			return true;
		}else{
			return false;
		}
	}
}
