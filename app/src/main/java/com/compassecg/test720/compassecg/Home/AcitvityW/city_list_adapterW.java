package com.compassecg.test720.compassecg.Home.AcitvityW;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.compassecg.test720.compassecg.R;

/**
 * Created by anim on 2016/8/10.
 */

public class city_list_adapterW extends BaseAdapter {
    private Context mContext;
    private Activity activity;
    //    String[] urls;
    String urls[] = {"北京", "安徽", "上海", "云南", "贵州", "辽宁", "福建", "新疆", "广东", "河北", "江苏", "台湾", "吉林", "内蒙古", "浙江", "西藏", "青海", "澳门", "湖北", "黑龙江",
            "广西", "河南", "山西", "重庆", "江西", "天津", "宁夏", "陕西", "香港", "甘肃", "湖南", "湖南","四川","山东"};

    public city_list_adapterW(Context mContext) {
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
            convertView = View.inflate(mContext, R.layout.layout_city, null);

            indicator.nameTv = (TextView) convertView.findViewById(R.id.nameTv);

            convertView.setTag(indicator);
        } else {
            indicator = (Indicator) convertView.getTag();
        }
        indicator.nameTv.setText(urls[position]);

//
        return convertView;
    }

    public class Indicator {

        TextView nameTv;

    }
}



