package com.bestride.data.back;

import java.util.List;

import com.bestride.data.helper.Goods;
import com.bestride.helper.FinalValue;
import com.google.gson.annotations.SerializedName;

public class GoodsBack {

	private int messageCode;
	private String messageInfo;
	private int goodsNumbers;
	List<Goods> goodsArray;
	
	public GoodsBack() {
		
	}
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

	public int getGoodsNumbers() {
		return goodsNumbers;
	}



	public void setGoodsNumbers(int goodsNumbers) {
		this.goodsNumbers = goodsNumbers;
	}



	public List<Goods> getGoodsArray() {
		return goodsArray;
	}



	public void setGoodsArray(List<Goods> goodsArray) {
		this.goodsArray = goodsArray;
	}



	public boolean isSuccess(){
		if(messageCode == FinalValue.SUCCESS_CODE){
			return true;
		}else{
			return false;
		}
	}
}
