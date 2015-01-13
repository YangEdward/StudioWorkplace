package com.bestride.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bestride.adapter.FloorHeaderAdapter;
import com.bestride.data.back.RoomBack;
import com.bestride.data.helper.JsonTree;
import com.bestride.data.helper.Room;
import com.bestride.data.post.RoomPost;
import com.bestride.helper.FinalValue;
import com.bestride.waiterwork.HotelApplication;
import com.bestride.waiterwork.R;
import com.bestride.waiterwork.ReportActivity_;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ygledward.async.future.FutureCallback;
import com.ygledward.ion.Ion;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.report)
public class ReportFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    @ViewById
    GridView floorStatus;
    private FloorHeaderAdapter mAdapter;
    private List<Object> rooms = new ArrayList<>();


    @AfterViews void initViews(){
        floorStatus.setOnItemClickListener(this);
        mAdapter = new FloorHeaderAdapter(rooms, getActivity());
        floorStatus.setAdapter(mAdapter);
        for (int i = 0; i<20; i++){
            rooms.add(new Room());
        }
        for (int i = 0; i<20; i++){
            Room room = new Room();
            room.setFloor(11);
            rooms.add(room);
        }
        for (int i = 0; i<20; i++){
            Room room = new Room();
            room.setFloor(12);
            rooms.add(room);
        }
        mAdapter.notifyDataSetChanged();
        //getRoomInformation();
    }

    @Override
    protected void getAgainListener(){
        getRoomInformation();
    }

    private void getRoomInformation() {
        setEmptyView(R.layout.loading_view,floorStatus,true);
        HotelApplication app = (HotelApplication) getActivity().getApplication();
        RoomPost mRoomsPost = new RoomPost(app.getUserName(),
                app.getHotelcode(),HotelApplication.sessionId,
                "",FinalValue.USED);
        JsonObject json = JsonTree.toJson(mRoomsPost);
        Ion.with(this)
                .load(FinalValue.GET_ROOMS_POST)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(result == null){
                            setEmptyView(R.layout.no_data_dialog,floorStatus,false);
                            return;
                        }
                        RoomBack mRoomsBack = JsonTree.fromJson(result, RoomBack.class);
                        if(mRoomsBack.isSuccess()){
                            JsonArray roomsArray = result.getAsJsonArray(FinalValue.ROOM_DIAGRAM);
                            for(int i = 0; i < roomsArray.size(); i++){
                                Room room = JsonTree.fromJson(roomsArray.get(i), Room.class);
                                rooms.add(room);
                            }
                            if(rooms.size() == 0){
                                setEmptyView(R.layout.has_no_data,floorStatus,true);
                            }else{
                                showRooms();
                            }
                        }else{
                            showInformation(mRoomsBack.getMessage_info(),true);
                        }
                    }

                });

    }



    @UiThread void showRooms(){
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Room roomDetail = (Room) rooms.get(position);
        Intent intent = new Intent(getActivity(), ReportActivity_.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(FinalValue.REPORT_DETAIL,roomDetail);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
