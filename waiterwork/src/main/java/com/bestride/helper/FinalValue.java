package com.bestride.helper;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class FinalValue {

	public static final int GRID_NUM = 10;
	public static final boolean DEBUG = false;
	public static final int USER_LENGTH = 32;
	public static final int ZERO = 0;
	public static final String CHECK_FIT = "check_fit";
	public static final String CHECK_OUT = "check_out";
	public static final String FLOOR_INFOR = "floor_infor";
	public static final String ROOM_DETAIL = "room_detail";
	public static final String BILL_INFOR = "bill_infor";
	public static final String RESERVATION = "reservation";
	public static final String MODE = "mode";
	public static final String REPORT_DETAIL = "report_detail";
	public static final String FLOOR_ENGLISH = "F";
	
	public static final String ROOM = "room";
	public static final String CHECKOUT = "checkout";
	public static final String PRICE = "price";
	public static final String PRICECODE = "pricecode";
	public static final String DESC = "desc";
	
	public static final String POSITION = "position";
	public static final String DISPATCH = "dispatch";
	public static final String MY_WORK = "my_work";
	public static final String REPORT = "report";
	
	public static final String BOOK_HOTEL = "book_hotel";

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

    public static final int NORMAL_REPORT = 0;
    public static final int OVER_REPORT = 1;
	/*permission*/
	public static final String RM_PERSSITION = "global_pad_rm_dispatch";

	/*Parcelable*/
	public static final String ROOMS = "rooms";
	
	/*internet connect*/
	public static final int SUCCESS_CODE = 20;
//	private static final String IP_ADDRESS = "http://192.168.2.160:9090/shmp/pms/";
	private static final String IP_ADDRESS = "http://192.168.2.200:8081/pms/";
	public static final String BASE_URL = IP_ADDRESS + "fm/PAD/";
	private static final String ROOM_URL = IP_ADDRESS + "rm/roomPAD/";
	
	public static final String LOGIN_POST = IP_ADDRESS + "cm/PAD/" + "pad_login.page";
	
	public static final String GET_ROOMS_POST = ROOM_URL + "app_get_rm_room.page";
	public static final String TYPE_ROOMS_POST = ROOM_URL + "app_get_rm_room_typeid.page";
	public static final String ROOM_TYPE = "roomType";
	public static final String ROOM_DIAGRAM = "roomDiagram";
	public static final String HOUSE_INTRO_POST = ROOM_URL + "app_get_rm_roomType.page";
	/*ԤԼ����*/
	public static final String RESERVATION_POST = BASE_URL + "app_get_fm_order_info.page";
	public static final String BILL_POST = BASE_URL + "app_post_fm_open.page";
	/*ԤԼ����*/
	public static final String RESERVATION_IN = BASE_URL + "app_post_fm_order.page";
	public static final String ROOM_ORDER_ARRAY = "roomorder_array";
	public static final String ROOM_ARRAY = "room_array";
	
	/*dispatch*/
	public static final String HANDLE_POST = ROOM_URL + "app_set_rm_dispatching.page";
	public static final String GET_WORK_POST = ROOM_URL + "app_get_rm_dispatching.page";
	
	/*�����˷�*/
	public static final String CHECK_OUT_SIMPLE = BASE_URL + "app_post_fm_co_querycanco.page";
	public static final String CHECK_OUT_DETAIL = BASE_URL + "app_post_fm_co_queryexpense.page";
	public static final String CHECK_OUT_POST = BASE_URL + "app_post_fm_co.page";
	public static final String TRADE_CODE = "002";
	public static final String TRADE_DESC = "������ֽ�";
	/*�����ύ*/
	public static final String REPORT_POST = BASE_URL + "app_post_fm_reportbar.page";
	public static final String REPORT_TRADE_CODE = "200";
	public static final String REPORT_TRADE_DESC = "�ͷ������";
	public static final String GET_GOODS_POST = IP_ADDRESS + "cm/PAD/" + "app_get_goods.page";
	public static final String MINI_POSID = "200";
	public static final int CLEAR_NUMBER = 0;
	/*����ά�޷�*/
	public static final String SET_REPAIR = ROOM_URL + "app_get_rm_dispatching.page";
	
	public static String UPLOAD_URL = FinalValue.BASE_URL + "app_post_fm_co_signpicture.page";
	/*exit time*/
	public static final long EXIT_TIME = 2000;
	
	/*work status */
	public static final int UNFINISHED = 2; //��2����δ�깤
	public static final int FINISHED = 3; //��3�������깤
	public static final int ACCEPTANCE = 4; //��4����������
	
	/*role*/
	public static final int MANAGER = 1; //��1��������
	public static final int FOREMAN = 2; //��2�������
	public static final int WAITER = 3; //��3������ͨ����Ա
	
	/*handle post status*/
	public static final int WORK_FINISHED = 1; //1���깤
	public static final int WORK_BACK = 2; //2������
	public static final int WORK_ACCEPTANCE = 3; //3����������
	public static final int WORK_UNACCEPTANCE = 4; //4������ȡ������
	
	/*for notification*/
	public static String ACTION_MESSAGE_ARRIVED = "com.bestride.action.MESSAGE_ARRIVED";
	public static String NOTI_BASE_URL = "http://www.ideawu.com/icomet/chat";
	
	public static void HideKeyboard(View v){
		
		InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );     
		if ( imm.isActive( ) ) {     
          imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );   
        }    
    }
}
