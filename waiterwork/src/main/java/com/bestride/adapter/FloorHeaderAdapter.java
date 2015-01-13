package com.bestride.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bestride.data.helper.GridHeaderData;
import com.bestride.data.helper.Room;
import com.bestride.helper.FinalValue;
import com.bestride.view.gridheader.HeadersSimpleAdapter;
import com.bestride.waiterwork.R;

import java.util.List;

public class FloorHeaderAdapter extends BookBaseAdapter implements
		HeadersSimpleAdapter {

	private Context mContext;
	private LayoutInflater inflater = null;

	public FloorHeaderAdapter(List<Object> mData,Context mContext) {
		super(mData);
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mContext = mContext;
	}

	@Override
	public long getHeaderId(int position) {
		GridHeaderData header = (GridHeaderData) getItem(position);
		return header.getHeader();
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.floor, parent, false);
            holder = new HeaderViewHolder();
            holder.textView = (TextView)convertView.findViewById(R.id.floorTitle);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder)convertView.getTag();
        }

        GridHeaderData item = (GridHeaderData) getItem(position);

        holder.textView.setText(item.getHeaderString());

        return convertView;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder holder;
		if(v == null){
			v = inflater.inflate(R.layout.floor_status,parent, false);
			holder = new ViewHolder();
			holder.roomNumber = (TextView)v.findViewById(R.id.roomNumber);
			v.setTag(holder);
		}else{
			holder = (ViewHolder) v.getTag();
		}
		
		if(getItem(position) instanceof Room){
			Room mRoom = (Room) getItem(position);
			holder.roomNumber.setText(mRoom.getRoomno());

			if(mRoom.getCleanstate() == FinalValue.NEED_CLEAN){
				v.setBackground(mContext.getResources().getDrawable(R.drawable.need_clean));
			}else{
				switch(mRoom.getRoomstate()){
				case FinalValue.USED:
					v.setBackground(mContext.getResources().getDrawable(R.drawable.used_color));
					break;
				case FinalValue.IDLE:
					v.setBackground(mContext.getResources().getDrawable(R.drawable.room_background));
					break;
				}
			}
			return v;
		}else{
			throw new IllegalArgumentException("Data format is error");
		}
	}

	static class HeaderViewHolder {
        public TextView textView;
    }

	static class ViewHolder {
        TextView roomNumber;
    }

}
