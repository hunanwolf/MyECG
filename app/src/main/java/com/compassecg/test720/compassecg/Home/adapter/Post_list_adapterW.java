package com.compassecg.test720.compassecg.Home.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.Home.baen.Sqlt;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.CircleImageView;
import com.compassecg.test720.compassecg.View.NetworkImageAdapter;
import com.compassecg.test720.compassecg.View.NineGridView;
import com.compassecg.test720.compassecg.unitl.Connector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.finalteam.toolsfinal.DateUtils;

/**
 * Created by anim on 2016/8/10.
 */

public class Post_list_adapterW extends BaseAdapter {
    private Context mContext;
    private Activity activity;
    List<Sqlt> sqltList;
    private Callback mCallback;


    public interface Callback {
        public void Clean(View v);
    }

    public Post_list_adapterW(Context mContext, List<Sqlt> sqltList, Activity activity, Callback callback) {
        this.mContext = mContext;
        this.sqltList = sqltList;
        this.activity = activity;
        this.mCallback = callback;

    }

    @Override
    public int getCount() {
        if (sqltList.size() != 0) {
            return sqltList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return sqltList.get(position);
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
            convertView = View.inflate(mContext, R.layout.item_post_list, null);
            indicator.htderde = (CircleImageView) convertView.findViewById(R.id.htderde);
            indicator.nicname = (TextView) convertView.findViewById(R.id.nicname);
            indicator.clean = (RelativeLayout) convertView.findViewById(R.id.clean);
            indicator.gallery = (NineGridView) convertView.findViewById(R.id.gallery);
            indicator.time = (TextView) convertView.findViewById(R.id.time);
            indicator.lv_huiofu = (TextView) convertView.findViewById(R.id.lv_huiofu);
            indicator.lt_title = (TextView) convertView.findViewById(R.id.lt_title);
            indicator.lt_conuntext = (TextView) convertView.findViewById(R.id.lt_conuntext);
            convertView.setTag(indicator);
        } else {
            indicator = (Indicator) convertView.getTag();
        }
        if (setisition == -1) {
            indicator.clean.setVisibility(View.INVISIBLE);
        } else {
            indicator.clean.setVisibility(View.VISIBLE);
        }

        Glide.with(mContext).load(Connector.lll + sqltList.get(position).getPic()).placeholder(R.drawable.ic_placeholder).into(indicator.htderde);
        indicator.lv_huiofu.setText(sqltList.get(position).getAsw_num());
        indicator.nicname.setText(sqltList.get(position).getNickname());
        String time = DateUtils.getTimeInterval(getTime(Long.parseLong(sqltList.get(position).getTime() + "000")));
        indicator.time.setText(time);
        indicator.lt_title.setText(sqltList.get(position).getTitle());
        indicator.lt_conuntext.setText(sqltList.get(position).getContent());
        indicator.gallery.setVisibility(View.VISIBLE);

        List<String> lll = new ArrayList<>();

        for (int i = 0; i < sqltList.get(position).getImg().size(); i++) {
            lll.add(sqltList.get(position).getImg().get(i).getUrl());
        }

        NetworkImageAdapter adapter = new NetworkImageAdapter(mContext, lll);
        indicator.gallery.setAdapter(adapter);
        indicator.clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.Clean(v);
            }
        });
//
        return convertView;
    }

    public void setVisition(int setisition) {
        this.setisition = setisition;
    }

    private int setisition = -1;

    public class Indicator {
        CircleImageView htderde;
        TextView nicname;
        TextView lv_huiofu;
        TextView lt_conuntext;
        TextView lt_title;
        RelativeLayout clean;
        TextView time;
        NineGridView gallery;
    }

    /**
     * 将毫秒转为yyyy-MM-dd HH:mm:ss的date格式
     *
     * @param time
     * @return
     */
    private String getTime(Long time) {
        //long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date(time);
        String t1 = format.format(d1);
        Log.e("msg", t1);
        return t1;
    }
}



