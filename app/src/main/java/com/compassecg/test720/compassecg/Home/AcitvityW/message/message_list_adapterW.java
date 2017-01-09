package com.compassecg.test720.compassecg.Home.AcitvityW.message;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.compassecg.test720.compassecg.R;

import static android.widget.ListPopupWindow.WRAP_CONTENT;

/**
 * Created by anim on 2016/8/10.
 */

public class message_list_adapterW extends BaseAdapter {
    private Context mContext;
    private Activity activity;
    //    String[] urls;
    String urls[] = {"即时通", "我的消息", "申请与邀请", "公告"};
    int[] list = {R.mipmap.icon_chat, R.mipmap.icon_news, R.mipmap.icon_shenqing, R.mipmap.icon_notice};

    public message_list_adapterW(Context mContext) {
        this.mContext = mContext;
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public Object getItem(int position) {
        return urls[position];
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
            convertView = View.inflate(mContext, R.layout.item_message, null);
            indicator.nameTv = (TextView) convertView.findViewById(R.id.nameTv);
            indicator.img = (ImageView) convertView.findViewById(R.id.img);
            indicator.unread_msg_number = (TextView) convertView.findViewById(R.id.unread_msg_number);
            convertView.setTag(indicator);
        } else {
            indicator = (Indicator) convertView.getTag();
        }
        if (position == 0) {
            indicator.unread_msg_number.setText("7");
            indicator.unread_msg_number.setWidth(WRAP_CONTENT);
            indicator.unread_msg_number.setWidth(WRAP_CONTENT);
        } else {
            indicator.unread_msg_number.setWidth(10);
            indicator.unread_msg_number.setWidth(10);
        }
        indicator.nameTv.setText(urls[position]);
        indicator.img.setImageResource(list[position]);

        return convertView;
    }

    public class Indicator {

        TextView nameTv;
        ImageView img;
        TextView unread_msg_number;
    }
}



