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
import com.bestride.helper.SPUtils;
import com.bestride.view.RippleView;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.ygledward.async.future.FutureCallback;
import com.ygledward.ion.Ion;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;

@EActivity(R.layout.login)
public class LoginActivity extends BaseActivity implements OnClickListener {

    @ViewById(R.id.LoginName) EditText name;
    @ViewById(R.id.LoginPassword) EditText password;

    @Override
    protected void getAgainListener(){

    }
	@AfterViews void initView(){
		findViewById(R.id.login).setOnClickListener(this);
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
				   HotelApplication app = (HotelApplication)getApplication();
				   app.setUserCode(name.getText().toString().trim());
				   app.setUserName(response.getRealname());
                   app.setUserId(response.getUserId());
                   app.setHotelcode(response.getHotelcode());
				   HotelApplication.sessionId = response.getSessionId();
				   if(response.getRmPermissions().isEmpty()){
					   HotelApplication.isLeader = false;
				   }else{
                       HotelApplication.isLeader = response.getRmPermissions().get(FinalValue.RM_PERSSITION).
                               contains(FinalValue.DISPATCH);
				   }
				   app.setManHotel(true);
				   app.setDispatch(true);
				   loginSuccess(response.getMessageInfo());
                   //turnToMainActivity(response.getMessageInfo());
			   }else{
				   showInformation(response.getMessageInfo(),true);
			   }
		    }
		});
	}

    @UiThread
	void loginSuccess(String infor){
		findViewById(R.id.login).setEnabled(true);
		//HotelApplication app = (HotelApplication)getApplication();
		turnToMainActivity(infor);
		//doLogin(app.getUserCode(),infor);
	}
	
	private void startICometService(String cname) {
		Intent service = new Intent(getApplicationContext(), ICometService.class);
		service.putExtra("cname", cname);
		getApplication().startService(service);
	}

    @UiThread
	void turnToMainActivity(String infor){
		HotelApplication app = (HotelApplication)getApplication();
		//startICometService(app.getUserCode());
        startICometService(app.getUserId());
		showInformation(infor,false);
		startActivity(new Intent(this,MainActivity_.class));
	}
	protected void doLogin(final String cname,final String infor) {
		AsyncHttpClient client = new AsyncHttpClient();
		client.setEnableRedirects(false);
		RequestParams params = new RequestParams();
		params.put("cname", cname);
        params.put("seq",0);
		client.get(LoginActivity.this, FinalValue.NOTI_BASE_URl/* + "/login.php"*/,params,
                 new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				super.onSuccess(arg0, arg1, arg2);
				String cookie;
				for (Header h : arg1) {
					if (h.getName().equalsIgnoreCase("SET-COOKIE")) {
						String value = h.getValue();
						cookie = value.substring(0, value.indexOf(";")).trim();
						SPUtils.putString(getApplicationContext(), "cookie", cookie);
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
			}
		});

	}

}
