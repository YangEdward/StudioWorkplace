package com.bestride.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bestride.adapter.WorkAdapter;
import com.bestride.data.back.RoomBack;
import com.bestride.data.helper.DespatchWork;
import com.bestride.data.helper.JsonTree;
import com.bestride.data.helper.MessageJsonBean;
import com.bestride.data.helper.Room;
import com.bestride.data.helper.WorkDetail;
import com.bestride.data.post.WorkPost;
import com.bestride.helper.FinalValue;
import com.bestride.view.BadgeView;
import com.bestride.waiterwork.HotelApplication;
import com.bestride.waiterwork.MainActivity_;
import com.bestride.waiterwork.R;
import com.bestride.waiterwork.ReportActivity_;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.ygledward.async.future.FutureCallback;
import com.ygledward.ion.Ion;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Administrator on 2015/1/8.
 */
@EFragment(R.layout.my_work)
public class MyWorkFragment extends BaseFragment implements View.OnClickListener,
        AdapterView.OnItemClickListener{

    @ViewById ListView recycleView;
    private WorkAdapter leftAdapter;
    private List<Object> left = new ArrayList<>();
    public static Set<String> workIds = new HashSet<>();
    private static int checkOutNumber;

    @AfterViews void initViews(){
        leftAdapter = new WorkAdapter(left, getActivity());
        SwingBottomInAnimationAdapter mSwingButton = new SwingBottomInAnimationAdapter(leftAdapter);
        mSwingButton.setAbsListView(recycleView);
        assert mSwingButton.getViewAnimator() != null;
        mSwingButton.getViewAnimator().setInitialDelayMillis(FinalValue.INITIAL_DELAY_MILLIS);
        recycleView.setAdapter(mSwingButton);
        getMyWorks();
    }

    @Override
    protected void getAgainListener(){
        getMyWorks();
    }

    private void getMyWorks(){
        final HotelApplication app = (HotelApplication) getActivity().getApplication();
        WorkPost mWorkPost = new WorkPost(HotelApplication.sessionId,app.getUserName(),
                app.getRole());
        JsonObject json = JsonTree.toJson(mWorkPost);
        Ion.with(getActivity())
                .load(FinalValue.GET_WORK_POST)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(!detach){
                            return;
                        }
                        if(result == null){
                            showGetAgainView();
                            showInformation(getString(R.string.please_check_your_internet),false);
                            return;
                        }
                        RoomBack mRoomsBack = JsonTree.fromJson(result, RoomBack.class);
                        if(mRoomsBack.isSuccess()){
                            if(mRoomsBack.getNumber() == 0){
                                showInformation(getString(R.string.no_work),false);
                                return;
                            }
                            left.clear();
                            workIds.clear();
                            checkOutNumber = 0;
                            JsonArray worksArray = result.getAsJsonArray(FinalValue.ROOM_ARRAY);
                            for(int i = 0; i < worksArray.size(); i++){
                                DespatchWork works = JsonTree.fromJson(worksArray.get(i), DespatchWork.class);
                                if(!workIds.contains(works.getWorkid())){
                                    left.add(works);
                                    workIds.add(works.getWorkid());
                                    if(works.getServtype() == FinalValue.CHECK_OUT){
                                        checkOutNumber ++;
                                    }
                                }
                            }
                            if(left.size() > 0){
                                updateUi();
                            }
                        }else{
                            showInformation(mRoomsBack.getMessage_info(),true);
                        }
                    }

                });
    }
    @Override
    public void onClick(View v) {
        hideGetAgainView();
        getMyWorks();
    }

    @UiThread void setTip(int value){
        BadgeView badge = new BadgeView(getActivity());
        MainActivity_.setInformationTip(badge,""+value);
    }
    @UiThread void showGetAgainView(){
    }

    @UiThread void hideGetAgainView(){
    }

    private void updateUi(){
        leftAdapter.notifyDataSetChanged();
        if(checkOutNumber > 0){
            setTip(checkOutNumber);
        }
    }

    public void updateWork(MessageJsonBean.MessageObj message) {
        if(!detach){
            return;
        }
        Log.e(MyWorkFragment.class.getSimpleName(), message.text);
        String content = message.text.replace("&quot;", "\"");
        if(content != null && !content.isEmpty()){
            WorkDetail workDetail = new Gson().fromJson(content, WorkDetail.class);
            DespatchWork works = workDetail.getDatalist();
            if(!workIds.contains(works.getWorkid())){
                workIds.add(works.getWorkid());
                left.add(works);
                if(works.getServtype() == FinalValue.CHECK_OUT){
                    checkOutNumber ++;
                }
            }
            updateUi();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        DespatchWork work = (DespatchWork) left.get(position);
        if(work.getServtype() == FinalValue.CHECK_OUT){
            Room room = new Room();
            room.setRoomid(work.getRoomid());
            room.setRoomno(work.getRoomno());
            Intent intent = new Intent(getActivity(), ReportActivity_.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(FinalValue.REPORT_DETAIL,room);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
