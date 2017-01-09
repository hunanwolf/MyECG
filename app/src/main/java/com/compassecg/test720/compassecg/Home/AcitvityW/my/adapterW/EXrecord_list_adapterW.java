package com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.compassecg.test720.compassecg.R;

/**
 * Created by anim on 2016/8/10.
 */

public class EXrecord_list_adapterW extends BaseAdapter {
    private Context mContext;
    private Activity activity;
    String[] urls;
    private Callback mCallback;

    public interface Callback {
        public void Invitationclick(View v);

        public void follclick(View v);
    }

    public EXrecord_list_adapterW(Context mContext, String[] urls) {
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
            convertView = View.inflate(mContext, R.layout.item_exchangerall_list, null);
            indicator.time = (TextView) convertView.findViewById(R.id.time);
            indicator.title = (TextView) convertView.findViewById(R.id.title);
            indicator.jifen = (TextView) convertView.findViewById(R.id.jifen);
            indicator.img = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(indicator);
        } else {
            indicator = (Indicator) convertView.getTag();
        }


        return convertView;
    }

    public class Indicator {
        TextView time;
        TextView title;
        TextView jifen;
        ImageView img;

    }
}



