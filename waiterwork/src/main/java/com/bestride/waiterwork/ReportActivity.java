package com.bestride.waiterwork;

import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bestride.adapter.MiniAdapter;
import com.bestride.data.back.GoodsBack;
import com.bestride.data.back.RoomBack;
import com.bestride.data.helper.Goods;
import com.bestride.data.helper.JsonTree;
import com.bestride.data.helper.ReportDetail;
import com.bestride.data.helper.Room;
import com.bestride.data.post.GoodsPost;
import com.bestride.data.post.ReportPost;
import com.bestride.helper.FinalValue;
import com.bestride.helper.MyDialog;
import com.bestride.view.RippleView;
import com.github.johnpersano.supertoasts.SuperToast;
import com.google.gson.JsonObject;
import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;
import com.ygledward.async.future.FutureCallback;
import com.ygledward.ion.Ion;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_report)
public class ReportActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener{
    @ViewById
    ListView recycleView;
    @ViewById
    Button normalReport,overReport;
    @ViewById RippleView right;
    @SystemService
    WindowManager windowManager;
    //@Extra(FinalValue.REPORT_DETAIL)
    private Room roomDetail;
    private List<Object> miniBar = new ArrayList<Object>();
    private MiniAdapter miniAdapter;
    private List<ReportDetail> detail = new ArrayList<ReportDetail>();
    private int reportFlag = 0;

    @AfterViews void initViews(){
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bar_background));

        normalReport.setOnClickListener(this);
        normalReport.setEnabled(false);
        right.setClickable(false);
        overReport.setOnClickListener(this);
        miniAdapter = new MiniAdapter(miniBar,this);
        SwingRightInAnimationAdapter mAnimAdapter = new SwingRightInAnimationAdapter(miniAdapter);
        mAnimAdapter.setAbsListView(recycleView);
        recycleView.setAdapter(mAnimAdapter);
        recycleView.setOnItemClickListener(this);
        roomDetail = getIntent().getParcelableExtra(FinalValue.REPORT_DETAIL);
        setTitle(roomDetail.getRoomno()+getString(R.string.mini_detail));
        /*for (int i = 0; i<20; i++){
            miniBar.add(new Goods());
        }

        miniAdapter.notifyDataSetChanged();*/
        getDetails();
    }

    protected void getAgainListener(){
        getDetails();
    }

    /*get mini bar information*/
    private void getDetails() {
        miniBar.clear();
        GoodsPost mGoodsPost = new GoodsPost(FinalValue.MINI_POSID,
                HotelApplication.sessionId,"");
        JsonObject json = JsonTree.toJson(mGoodsPost);
        Ion.with(this)
                .load(FinalValue.GET_GOODS_POST)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(result == null){
                            showInformation(getString(R.string.please_check_your_internet),true);
                            return;
                        }
                        GoodsBack mGoodsBack = JsonTree.fromJson(result, GoodsBack.class);
                        if(mGoodsBack.isSuccess()){
                            if(mGoodsBack.getGoodsNumbers() == 0){
                                showInformation(getString(R.string.data_error),true);
                                return;
                            }
                            miniBar.addAll(mGoodsBack.getGoodsArray());
                            showAllGoods();
                        }else{
                            showInformation(mGoodsBack.getMessageInfo(),true);
                        }
                    }

                });

    }

    @UiThread void showAllGoods() {
        miniAdapter.notifyDataSetChanged();
    }

    private void getCosts(){
        detail.clear();
        int size = miniBar.size();
        for(int i = 0; i < size; i++){
            Goods good = (Goods) miniBar.get(i);
            if(good.getNumber() != 0){
                ReportDetail reportChild = new ReportDetail(good.getGoodsname()
                        + "_" + good.getNumber(), (good.getSmallprice() * good.getNumber()));
                detail.add(reportChild);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        getCosts();
        reportFlag = FinalValue.OVER_REPORT;
        switch (itemId){
            case R.id.normalReport:
                if(detail.size() == 0){
                    return;
                }
                reportFlag = FinalValue.NORMAL_REPORT;
            case R.id.overReport:
                submitConform();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        setMiniDataValue(position);
    }

    private void submitConform() {
        final MyDialog dialog = new MyDialog(this, R.layout.gift_back_dialog);
        ((TextView)dialog.findViewById(R.id.title)).setText(R.string.please_conform_report);
        dialog.show();
        final EditText value = (EditText) dialog.findViewById(R.id.value);
        value.setVisibility(View.GONE);
        dialog.findViewById(R.id.DialogCancel).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.DialogConform).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                submitPost();
                dialog.dismiss();
            }
        });
    }

    private void submitPost(){
        normalReport.setEnabled(false);
        ReportPost report = new ReportPost();
        report.setReportFlag(reportFlag);
        report.setBillArray(detail);
        report.setNumber(detail.size());
        report.setRmroomid(roomDetail.getRoomid());
        report.setSession_id(HotelApplication.sessionId);
        report.setTradecode(FinalValue.REPORT_TRADE_CODE);
        report.setTradedesc(FinalValue.REPORT_TRADE_DESC);
        JsonObject json = JsonTree.toJson(report);
        Ion.with(this)
                .load(FinalValue.REPORT_POST)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(result == null){
                            showInformation(getString(R.string.please_check_your_internet),true);
                            enableButton();
                            return;
                        }
                        RoomBack mRoomsBack = JsonTree.fromJson(result, RoomBack.class);
                        if(mRoomsBack.isSuccess()){
                            refreshDetails();
                            showInformation(getString(R.string.report_success),false);
                        }else{
                            showInformation(mRoomsBack.getMessage_info(),true);
                            enableButton();
                        }
                    }

                });
    }

    @UiThread void refreshDetails() {
        int size = miniBar.size();
        for(int i = 0; i < size; i++){
            Goods good = (Goods) miniBar.get(i);
            good.setNumber(FinalValue.CLEAR_NUMBER);
        }
        detail.clear();
        miniAdapter.notifyDataSetChanged();
    }

    @UiThread void enableButton(){
        normalReport.setEnabled(true);
    }

    private void setMiniDataValue(final int position) {
        final MyDialog dialog = new MyDialog(this, R.layout.gift_back_dialog);
        final Goods good = (Goods) miniBar.get(position);
        ((TextView)dialog.findViewById(R.id.title)).setText(good.getGoodsname());
        dialog.show();
        final EditText value = (EditText) dialog.findViewById(R.id.value);
        value.setInputType(InputType.TYPE_CLASS_NUMBER);
        value.setHint(R.string.please_input_values);
        dialog.findViewById(R.id.DialogCancel).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FinalValue.HideKeyboard(value);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.DialogConform).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String num = value.getText().toString().trim();
                if(num.isEmpty()){
                    return;
                }
                int number = Integer.parseInt(num);
                good.setNumber(number);
                if(number != 0){
                    normalReport.setEnabled(true);
                }
                miniAdapter.notifyDataSetChanged();
                FinalValue.HideKeyboard(value);
                dialog.dismiss();
            }
        });
    }

    @UiThread
    void showInformation(String messageInfo,boolean isError) {
        SuperToast superToast = new SuperToast(this);
        superToast.setAnimations(SuperToast.Animations.FLYIN);
        superToast.setDuration(SuperToast.Duration.SHORT);
        if(isError){
            superToast.setBackground(SuperToast.Background.RED);
        }else{
            superToast.setBackground(SuperToast.Background.BLUE);
        }
        superToast.setTextSize(SuperToast.TextSize.LARGE);
        superToast.setText(messageInfo);
        superToast.show();
    }
}
