package com.bestride.helper;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

public class MyDialog extends Dialog {

	private boolean isShow = false;
	
	public MyDialog(Context context,int resId){
		super(context);
		// TODO Auto-generated constructor stub
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(resId);
	}
	@Override
	public void show(){
		if(isShow){
			return;
		}
		super.show();
		isShow = true;
	}
	@Override
	public void dismiss() {
		if(!isShow){
			return;
		}
		super.dismiss();
		isShow = false;
	}
}
