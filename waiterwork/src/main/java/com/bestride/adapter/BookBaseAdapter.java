package com.bestride.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

abstract class BookBaseAdapter extends BaseAdapter {

	private List<Object> mData = new ArrayList<Object>();
	private String errorMessage = null;
	private String responseCode = null;
	
	public BookBaseAdapter(List<Object> mData) {
		super();
		this.mData = mData;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public List<Object> getmData() {
		return mData;
	}

	public void setmData(ArrayList<Object> mData) {
		this.mData = mData;
	}

	public void addData(ArrayList<Object> mData) {
		this.mData.add(mData);
	}
	
	public void clear() {
		this.mData.clear();
	}
	
	public void remove(int position){
		this.mData.remove(position);
	}
}
