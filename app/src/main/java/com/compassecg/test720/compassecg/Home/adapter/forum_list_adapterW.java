package com.compassecg.test720.compassecg.Home.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.BigImage.ImageViewpagerActivity;
import com.compassecg.test720.compassecg.Home.baen.Answer;
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

public class forum_list_adapterW extends BaseAdapter {
    private Context mContext;
    private Activity activity;
    String[] urls;
    private int current_posion;//当前position
    private Callback mCallback;

    List<Answer> listanswer;

    public interface Callback {


        public void Clean(View v);
    }

    public forum_list_adapterW(Context mContext, List<Answer> listanswer, Activity activity, Callback callback) {
        this.mContext = mContext;
        this.listanswer = listanswer;
        this.activity = activity;
        this.mCallback = callback;

    }

    @Override
    public int getCount() {
        if (listanswer.size() != 0) {
            return listanswer.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return listanswer.get(position);
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
            convertView = View.inflate(mContext, R.layout.item_forum_layout, null);
            indicator.htderde = (CircleImageView) convertView.findViewById(R.id.htderde);
            indicator.nicname = (TextView) convertView.findViewById(R.id.nicname);
            indicator.gallery = (NineGridView) convertView.findViewById(R.id.gallery);
            indicator.clean = (RelativeLayout) convertView.findViewById(R.id.clean);
            indicator.lt_title = (TextView) convertView.findViewById(R.id.lt_title);
            indicator.time = (TextView) convertView.findViewById(R.id.time);
            indicator.lv_huiofuc = (TextView) convertView.findViewById(R.id.lv_huiofu);
            convertView.setTag(indicator);
        } else {
            indicator = (Indicator) convertView.getTag();
        }

        if (setisition == -1) {
            indicator.clean.setVisibility(View.INVISIBLE);
        } else {
            indicator.clean.setVisibility(View.VISIBLE);
        }

        Glide.with(mContext)
                .load(Connector.lll + listanswer.get(position).getImg())
                .placeholder(R.color.hui)
                .centerCrop()
                .into(indicator.htderde);
        indicator.nicname.setText(listanswer.get(position).getNickname());
        indicator.lt_title.setText(listanswer.get(position).getContent());
        String time = DateUtils.getTimeInterval(getTime(Long.parseLong(listanswer.get(position).getTime() + "000")));
        indicator.time.setText(time);
        indicator.lv_huiofuc.setText(listanswer.get(position).getPl_num());

        List<String> lll = new ArrayList<>();
        for (int i = 0; i < listanswer.get(position).getImg().size(); i++) {
            lll.add(listanswer.get(position).getImg().get(i).getUrl());
        }
        NetworkImageAdapter adapter = new NetworkImageAdapter(activity, lll);
        indicator.gallery.setAdapter(adapter);
        indicator.gallery.setVisibility(View.VISIBLE);
        indicator.gallery.setEnabled(false);
        indicator.clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.Clean(v);
            }
        });

        indicator.gallery.setOnImageClickListener(new Nicgriadview(position, lll));
//        if (list3.get(position).getPicture().size() == 0) {
//            indicator.gallery.setVisibility(View.GONE);
//        } else {
//            indicator.gallery.setVisibility(View.VISIBLE);
//            NetworkImageAdapter Gradview_Adapter = new NetworkImageAdapter(activity, list3.get(position).getPicture());
//            indicator.gallery.setAdapter(Gradview_Adapter);
//            indicator.gallery.setOnImageClickListener(new Nicgriadview(position));
//        }

        return convertView;
    }

    public class Indicator {
        CircleImageView htderde;
        TextView nicname;
        TextView lt_title;
        TextView lt_conuntext;
        TextView time;
        TextView lv_huiofuc;
        NineGridView gallery;
        RelativeLayout clean;
    }

    public void setVisition(int setisition) {
        this.setisition = setisition;
    }

    private int setisition = -1;

    public class Nicgriadview implements NineGridView.OnImageClickListener {
        private int posn;
        List<String> lll;

        public Nicgriadview(int postion, List<String> lll) {
            this.posn = postion;
            this.lll = lll;
        }

        @Override
        public void onImageCilcked(int position, View view) {
            current_posion = position;
            mContext.startActivity(new Intent(mContext, ImageViewpagerActivity.class).putExtra("type", current_posion)
                    .putStringArrayListExtra("list", (ArrayList<String>) lll));
            activity.overridePendingTransition(R.anim.umeng_socialize_fade_in, R.anim.umeng_socialize_fade_out);
        }

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



