package com.bestride.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestride.data.back.BillBack;
import com.bestride.data.helper.DespatchWork;
import com.bestride.data.helper.JsonTree;
import com.bestride.data.helper.WorkDetail;
import com.bestride.data.post.HandlePost;
import com.bestride.helper.FinalValue;
import com.bestride.view.RippleView;
import com.bestride.waiterwork.HotelApplication;
import com.bestride.waiterwork.R;
import com.github.johnpersano.supertoasts.SuperToast;
import com.google.gson.JsonObject;
import com.ygledward.async.future.FutureCallback;
import com.ygledward.ion.Ion;

import java.util.List;

public class WorkAdapter extends BookBaseAdapter {
	
	private Context mContext;
	private LayoutInflater inflater = null;
	public WorkAdapter(List<Object> mData,Context mContext) {
		super(mData);
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mContext = mContext;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(getItem(position) instanceof DespatchWork){
			View v = convertView;
			ViewHolder holder;
			if(v == null){
				v = inflater.inflate(R.layout.work_list_item,parent, false);
				holder = new ViewHolder();
				holder.workImage = (ImageView)v.findViewById(R.id.workImage);
				holder.roomNumber = (TextView) v.findViewById(R.id.roomNumber);
				holder.workTime = (TextView) v.findViewById(R.id.workTime);
				holder.workContent = (TextView) v.findViewById(R.id.workContent);
				holder.workFinished = (Button)v.findViewById(R.id.workFinished);
				holder.rippleView = (RippleView)v.findViewById(R.id.rippleView);
				v.setTag(holder);
			}else{
				holder = (ViewHolder) v.getTag();
			}
			DespatchWork mWork = (DespatchWork) getItem(position);

			holder.roomNumber.setText(mWork.getRoomno());
            holder.workTime.setText(mWork.getBegintime());
            //holder.workContent.setText(""+mWork.getServtype());
            if(mWork.getServtype() == FinalValue.CHECK_OUT){
                holder.workImage.setImageDrawable(mContext.getResources().
                        getDrawable(R.drawable.check_out));
                holder.workContent.setText(mContext.getResources().
                        getString(R.string.check_out));
                holder.rippleView.setVisibility(View.GONE);
            }else{
                holder.workContent.setText(mContext.getResources().
                        getString(R.string.clean));
                holder.workImage.setImageDrawable(mContext.getResources().
                        getDrawable(R.drawable.clean));
                holder.workFinished.setOnClickListener(new HandleClickListener(position));
            }
			return v;
		}else{
			throw new IllegalArgumentException("Data format is error");
		}
	}

	static class ViewHolder {
        TextView roomNumber;
        ImageView workImage;
        TextView workTime;
        TextView workContent;
        Button workFinished;
        RippleView rippleView;
    }
	
	private class HandleClickListener implements OnClickListener {
		private int position;

		public HandleClickListener(int position) {
			super();
			this.position = position;
		}

		@Override
		public void onClick(View v) {
            DespatchWork mWork = (DespatchWork) getItem(position);
			JsonObject json = JsonTree.toJson(new HandlePost(mWork.getWorkid(),
					mWork.getHoteltype(), HotelApplication.sessionId,1));
			Ion.with(mContext)
			.load(FinalValue.HANDLE_POST)
			.setJsonObjectBody(json)
			.asJsonObject()
			.setCallback(new FutureCallback<JsonObject>() {
			   @Override
			    public void onCompleted(Exception e, JsonObject result) {
			        // do stuff with the result or error
				   if(result == null){
					   ((Activity)mContext).runOnUiThread(new Runnable() {
						   @Override
							public void run() {
								showInformation(mContext.getString(
									   R.string.please_check_your_internet),true);
							}
					   });
					   return;
				   }
				   final BillBack response = JsonTree.fromJson(result, BillBack.class);
				   if(response.isSuccess()){
					   remove(position);
					   ((Activity)mContext).runOnUiThread(new Runnable() {
						   @Override
							public void run() {
							   	notifyDataSetChanged();
								showInformation(mContext.getString(
									   R.string.handle_success),false);
							}
					   });
                       notifyDataSetChanged();
				   }else{
					   ((Activity)mContext).runOnUiThread(new Runnable() {
						   @Override
							public void run() {
							   showInformation(response.getMessage_info(),true);
							}
					   });
					   
				   }
			    }
			});
		}
	}
	
	void showInformation(String messageInfo,boolean isError) {
		SuperToast superToast = new SuperToast(mContext);
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
