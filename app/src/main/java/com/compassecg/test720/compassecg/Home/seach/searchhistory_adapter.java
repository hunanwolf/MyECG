package com.compassecg.test720.compassecg.Home.seach;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.bean.UserList;
import com.compassecg.test720.compassecg.unitl.UuidUtil;

import java.util.List;

/**
 * Created by anim on 2016/8/11.
 */

public class searchhistory_adapter extends BaseAdapter {
    private Context mContext;
    List<UserList> list;

    public searchhistory_adapter(Context mContext, List<UserList> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {

        if (list.size() != 0) {
            return list.size();
        } else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        Indicator indicator = null;
        if (convertView == null) {
            indicator = new Indicator();
            convertView = View.inflate(mContext, R.layout.item_arraylaouy, null);
            indicator.nameTv = (TextView) convertView.findViewById(R.id.nameTv);
            indicator.img= (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(indicator);
        } else {

            indicator = (Indicator) convertView.getTag();
        }
        indicator.nameTv.setText(list.get(position).getName());
        indicator.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(position);

                UuidUtil.AddRecordList(mContext, list, APP.uuid);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public class Indicator {
        ImageView img;
        TextView nameTv;
        TextView price;
        TextView textView2;
        TextView compry;
        TextView address;
        ImageView imggouqi;
    }
}