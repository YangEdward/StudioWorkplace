package com.bestride.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bestride.data.helper.Goods;
import com.bestride.waiterwork.R;

import java.util.List;

public class MiniAdapter extends BookBaseAdapter {
	
	private LayoutInflater inflater = null;
	
	
	public MiniAdapter(List<Object> mData,Context mContext) {
		super(mData);
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(getItem(position) instanceof Goods){
			ViewHolder holder;
			View v = convertView;
			if(v == null){
				v = inflater.inflate(R.layout.report_list_item,parent, false);
				holder = new ViewHolder();
                holder.code = (TextView)v.findViewById(R.id.code);
				holder.name = (TextView)v.findViewById(R.id.costCommodity);
				holder.number = (TextView)v.findViewById(R.id.numbers);
				v.setTag(holder);
			}else{
				holder = (ViewHolder)v.getTag();
			}
			Goods good= (Goods) getItem(position);
			holder.name.setText(good.getGoodsname());
			holder.code.setText(""+(position + 1));
			if(good.getNumber() != 0){
				holder.number.setText(""+good.getNumber());
			}else{
				holder.number.setText("");
			}
			return v;
		}else{
			throw new IllegalArgumentException("Data format is error");
		}
	}

	static class ViewHolder {
        TextView name;
        TextView code;
        TextView number;
    }
}
