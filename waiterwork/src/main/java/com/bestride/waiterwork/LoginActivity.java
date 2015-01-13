package com.bestride.waiterwork;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.bestride.comet.ICometService;
import com.bestride.data.back.LoginEntity;
import com.bestride.data.helper.JsonTree;
import com.bestride.data.post.LoginPost;
import com.bestride.helper.FinalValue;
import com.bestride.view.RippleView;
import com.google.gson.JsonObject;
import com.ygledward.async.future.FutureCallback;
import com.ygledward.ion.Ion;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.login)
public class LoginActivity extends BaseActivity implements OnClickListener {//,TextWatcher{

    @ViewById(R.id.LoginName) EditText name;
    @ViewById(R.id.LoginPassword) EditText password;

    @Override
    protected void getAgainListener(){

    }
	@AfterViews void initView(){
		findViewById(R.id.login).setOnClickListener(this);
		//name.addTextChangedListener(this);
		//name.setText("admin");
		//password.setText("123456");
	}

    @Override
    protected void onStop() {
        super.onStop();
        ((RippleView)findViewById(R.id.login)).setAnimationRunning(false);
    }

    @Override
	public void onClick(View v) {
		if(name.getText().toString().isEmpty()){
			showInformation(getString(R.string.please_input_name),true);
			return;
		}
		if(name.getText().toString().length() > FinalValue.USER_LENGTH){
			showInformation(getString(R.string.length_over),true);
			return;
		}
		if(password.getText().toString().isEmpty()){
			showInformation(getString(R.string.please_input_password),true);
			return;
		}
		if(password.getText().toString().length() > FinalValue.USER_LENGTH){
			showInformation(getString(R.string.length_over),true);
			return;
		}
		v.setEnabled(false);
		JsonObject json = JsonTree.toJson(new LoginPost(name.getText().toString(), 
				password.getText().toString()));
		Ion.with(this)
		.load(FinalValue.LOGIN_POST)
		.setJsonObjectBody(json)
		.asJsonObject()
		.setCallback(new FutureCallback<JsonObject>() {
		   @Override
		    public void onCompleted(Exception e, JsonObject result) {
		        // do stuff with the result or error
			   if(result == null){
				   showInformation(getString(R.string.please_check_your_internet),true);
				   return;
			   }
			   LoginEntity response = JsonTree.fromJson(result, LoginEntity.class);
			   if(response.isSuccess()){
				   HotelApplication app = HotelApplication.getInstance();
				   app.setUserCode(name.getText().toString().trim());
				   app.setUserName(response.getRealname());
				   HotelApplication.sessionId = response.getSessionId();
				   if(response.getRmPermissions().isEmpty()){
					   HotelApplication.isLeader = false;
				   }else{
                       HotelApplication.isLeader = response.getRmPermissions().get(FinalValue.RM_PERSSITION).
                               contains(FinalValue.DISPATCH);
				   }
				   app.setManHotel(true);
				   app.setDispatch(true);
                   turnToMainActivity(response.getMessageInfo());
				   //loginSuccess(response.getMessageInfo());
			   }else{
				   showInformation(response.getMessageInfo(),true);
			   }
		    }
		});
	}

    @UiThread
	void loginSuccess(String infor){
		/*SharedPreferences sp = getSharedPreferences(FinalValue.BOOK_HOTEL,
				Context.MODE_PRIVATE);
		String key = name.getText().toString();
		Editor mE = sp.edit();
		if(isRemember.isChecked()){
			mE.putString(key, password.getText().toString());
			mE.commit();
		}else{
			if (sp.getString(key, null) != null) {
				mE.remove(key);
				mE.commit();
			}
		}*/
		findViewById(R.id.login).setEnabled(true);
//		HotelApplication app = HotelApplication.getInstance();
		turnToMainActivity(infor);
//		doLogin(app.getUserCode(),infor);
//		HotelApplication app = HotelApplication.getInstance();
//		if(app.isManHotel()){
//			startActivity(new Intent(this,BookHotelActivity_.class));
//		}else if(app.isDispatch()){
//			startICometService(app.getUserName());
//			startActivity(new Intent(this,DispatchActivity_.class));
//		}else{
//			showInformation(getString(R.string.has_no_permision),true);
//		}
	}
	


	/*@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		SharedPreferences sp = getSharedPreferences(FinalValue.BOOK_HOTEL, 
				Context.MODE_PRIVATE);
		String paw = sp.getString(name.getText().toString(), null);
		if(paw != null){
			password.setText(paw);
			*//*isRemember.setChecked(true);*//*
		}
	}

	@Override
	public void afterTextChanged(Editable s) {
		
	}*/
	
	private void startICometService(String uname) {
		Intent service = new Intent(getApplicationContext(), ICometService.class);
		service.putExtra("uname", uname);
		getApplication().startService(service);
	}

    @UiThread
	void turnToMainActivity(String infor){
		HotelApplication app = HotelApplication.getInstance();
		startICometService(app.getUserCode());
		showInformation(infor,false);
		startActivity(new Intent(this,MainActivity_.class));
	}
	/*protected void doLogin(final String uname,final String infor) {
//		mProgressDialog = UIUtils.showProgressDialog(LoginActivity.this, "");
		AsyncHttpClient client = new AsyncHttpClient();
		client.setEnableRedirects(false);
		RequestParams params = new RequestParams();
		params.put("uname", uname);
		client.post(LoginActivity.this, FinalValue.NOTI_BASE_URL + "/login.php", params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				super.onSuccess(arg0, arg1, arg2);
//				dismissDialog();
				*//*for (Header h : arg1) {
					Log.d("SU_HEADER: ", "name: " + h.getName() + "  value: " + h.getValue());
				}
				Log.d("success content: ", new String(arg2));*//*
//				Log.d(TAG, "success");
				String cookie;
				for (Header h : arg1) {
//					Log.d(TAG, "HEADER_NAME: " + h.getName());
					if (h.getName().equalsIgnoreCase("SET-COOKIE")) {
						String value = h.getValue();
						cookie = value.substring(0, value.indexOf(";")).trim();
//						try {
//							Log.d(TAG, URLDecoder.decode(URLDecoder.decode(cookie, "utf-8"), "utf-8"));
//						} catch (UnsupportedEncodingException e) {
//							e.printStackTrace();
//						}
						SPUtils.putString(getApplicationContext(), "cookie", cookie);
//						((CSApplication) getApplication()).uname = uname;
					}
				}
				turnToMainActivity(infor);
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				super.onFailure(arg0, arg1, arg2, arg3);

				if (arg0 == 302) {
					onSuccess(arg0, arg1, arg2);
					return;
				}

				arg3.printStackTrace();
//				dismissDialog();
				Log.d("FAIL", "FAIL " + arg0);
			}
		});

	}*/

}
