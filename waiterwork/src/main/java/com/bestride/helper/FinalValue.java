package com.bestride.helper;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class FinalValue {

	public static final boolean DEBUG = false;
	public static final int USER_LENGTH = 32;
	public static final String REPORT_DETAIL = "report_detail";
	public static final String FLOOR_ENGLISH = "F";

	public static final String DISPATCH = "dispatch";

    /*animations delay*/

    public static final int INITIAL_DELAY_MILLIS = 300;

	/*	room status*/	
	
	public static final int IDLE = 1;
	public static final int BOOKED = 2;
	public static final int USED = 3;
	public static final int FITTING = 4;
	public static final int CLOSED = 5;
	public static final int PAUSE = 6;
	public static final int TEMP = 7;
	public static final int ONE_DAY = 1;
	public static final int NEED_CLEAN = 2;  //
    public static final String CHECK_OUT_STATE = "CS";
    public static final int NORMAL_REPORT = 0;
    public static final int OVER_REPORT = 1;
	/*permission*/
	public static final String RM_PERSSITION = "global_pad_rm_dispatch";
	
	/*internet connect*/
	public static final int SUCCESS_CODE = 20;
//	private static final String IP_ADDRESS = "http://192.168.2.114:9090/shmp/pms/";
	private static final String IP_ADDRESS = "http://192.168.2.200:8081/shmp/pms/";
	public static final String BASE_URL = IP_ADDRESS + "fm/PAD/";
	private static final String ROOM_URL = IP_ADDRESS + "rm/roomPAD/";
    public static final String NOTI_BASE_URl = "http://192.168.2.200:8100/poll";
//    public static final String NOTI_BASE_URl = "http://www.ideawu.com/icomet/chat";
    public static final String LOGIN_POST = IP_ADDRESS + "cm/PAD/" + "pad_login.page";
	
	public static final String GET_ROOMS_POST = ROOM_URL + "app_get_rm_room.page";

	public static final String ROOM_DIAGRAM = "roomDiagram";
	public static final String ROOM_ARRAY = "room_array";
	
	/*dispatch*/
	public static final String HANDLE_POST = ROOM_URL + "app_set_rm_dispatching.page";
	public static final String GET_WORK_POST = ROOM_URL + "app_get_rm_dispatching.page";

	//public static final String REPORT_POST = BASE_URL + "app_post_fm_reportbar.page";
    public static final String REPORT_POST = IP_ADDRESS + "cm/PAD/" + "pad_minibar_report.page";

    public static final String REPORT_TRADE_CODE = "200";
	public static final String REPORT_TRADE_DESC = "客房迷你吧";
	public static final String GET_GOODS_POST = IP_ADDRESS + "cm/PAD/" + "app_get_goods.page";
	public static final String MINI_POSID = "200";
	public static final int CLEAR_NUMBER = 0;

	/*exit time*/
	public static final long EXIT_TIME = 2000;
	
	/*work status */
    public static final int CHECK_OUT = 10; //
    public static final int CLEAN = 3; //

    public static final int SMALL_DISH = 1;
    public static final int MID_DISH = 2;
    public static final int BIG_DISH = 3;

	/*for notification*/
	public static final String ACTION_MESSAGE_ARRIVED = "com.bestride.action.MESSAGE_ARRIVED";
	
	public static void hideKeyboard(View v){
		
		InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );     
		if ( imm.isActive( ) ) {     
          imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );   
        }    
    }
}
