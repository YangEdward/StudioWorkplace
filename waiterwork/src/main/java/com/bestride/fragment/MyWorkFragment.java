package com.bestride.fragment;


import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.bestride.adapter.WorkAdapter;
import com.bestride.data.back.RoomBack;
import com.bestride.data.helper.JsonTree;
import com.bestride.data.helper.MessageJsonBean;
import com.bestride.data.helper.WorkDetail;
import com.bestride.data.post.WorkPost;
import com.bestride.helper.FinalValue;
import com.bestride.view.BadgeView;
import com.bestride.waiterwork.HotelApplication;
import com.bestride.waiterwork.MainActivity_;
import com.bestride.waiterwork.R;
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
import java.util.List;

/**
 * Created by Administrator on 2015/1/8.
 */
@EFragment(R.layout.my_work)
public class MyWorkFragment extends BaseFragment implements View.OnClickListener{
    @ViewById ListView recycleView;
    private WorkAdapter leftAdapter;
    private List<Object> left = new ArrayList<Object>();


    @AfterViews void initViews(){
        leftAdapter = new WorkAdapter(left, getActivity());
        SwingBottomInAnimationAdapter mSwingButton = new SwingBottomInAnimationAdapter(leftAdapter);
        mSwingButton.setAbsListView(recycleView);
        assert mSwingButton.getViewAnimator() != null;
        mSwingButton.getViewAnimator().setInitialDelayMillis(FinalValue.INITIAL_DELAY_MILLIS);
        recycleView.setAdapter(mSwingButton);
        for (int i = 0; i<20; i++){
            left.add(new WorkDetail());
        }
        leftAdapter.notifyDataSetChanged();
        //setTip();
        //getMyWorks();
    }

    @Override
    protected void getAgainListener(){
        getMyWorks();
    }

    public void getMyWorks(){
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
                            JsonArray worksArray = result.getAsJsonArray(FinalValue.ROOM_ARRAY);
                            for(int i = 0; i < worksArray.size(); i++){
                                WorkDetail works = JsonTree.fromJson(worksArray.get(i), WorkDetail.class);
                                left.add(works);
                                /*if(HotelApplication.isLeader){
                                    if(works.getTaskstate() == FinalValue.UNFINISHED
                                            || works.getTaskstate() == FinalValue.FINISHED){
                                        left.add(works);
                                    }else{
                                        right.add(works);
                                    }
                                }else{
                                    if(works.getTaskstate() == FinalValue.UNFINISHED){
                                        left.add(works);
                                    }else{
                                        right.add(works);
                                    }
                                }*/
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

    @UiThread void setTip(){
        BadgeView badge = new BadgeView(getActivity());
        MainActivity_.setInformationTip(badge,"32");
    }
    @UiThread void showGetAgainView(){
    }

    @UiThread void hideGetAgainView(){
    }

    private void updateUi(){
        leftAdapter.notifyDataSetChanged();
    }

    private void updateWork(MessageJsonBean.MessageObj message) {
        if(!detach){
            return;
        }
        Log.e(MyWorkFragment.class.getSimpleName(), message.text);
        String content = message.text.replace("&quot;", "\"");
        WorkDetail works = new Gson().fromJson(content, WorkDetail.class);
        if(content != null && !content.isEmpty()){
            /*if(HotelApplication.isLeader){
                if(works.getTaskstate() == FinalValue.UNFINISHED
                        || works.getTaskstate() == FinalValue.FINISHED){
                    left.add(works);
                }else{
                    right.add(works);
                }
            }else{
                if(works.getTaskstate() == FinalValue.UNFINISHED){
                    left.add(works);
                }else{
                    right.add(works);
                }
            }*/
            left.add(works);
            updateUi();
        }

    }
}
