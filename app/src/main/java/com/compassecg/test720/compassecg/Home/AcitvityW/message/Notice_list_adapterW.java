package com.compassecg.test720.compassecg.Home.AcitvityW.message;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.CircleImageView;

import java.util.List;

/**
 * Created by anim on 2016/8/10.
 */

public class Notice_list_adapterW extends BaseAdapter {
    private Context mContext;
    private Activity activity;
   List<String> urls;
//    String urls[] = {"即时通", "我的消息", "申请与邀请", "公告"};
//    String list[] = {"北京", "安徽", "上海", "云南"};

    public Notice_list_adapterW(Context mContext,List<String> urls) {
        this.mContext = mContext;
        this.urls = urls;


    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return 20;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Indicator indicator = null;
        if (convertView == null) {
            indicator = new Indicator();
            convertView = View.inflate(mContext, R.layout.item_notice, null);

            indicator.title = (TextView) convertView.findViewById(R.id.title);
            indicator.rad = (TextView) convertView.findViewById(R.id.rad);
            indicator.context = (TextView) convertView.findViewById(R.id.context);

            convertView.setTag(indicator);
        } else {
            indicator = (Indicator) convertView.getTag();
        }
//        indicator.nameTv.setText(urls[position]);

//
        return convertView;
    }

    public class Indicator {

        TextView title;
        CircleImageView img;
        TextView rad;

        TextView context;
    }
}



