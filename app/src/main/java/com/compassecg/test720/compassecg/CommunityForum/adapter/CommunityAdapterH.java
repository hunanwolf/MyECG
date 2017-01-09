package com.compassecg.test720.compassecg.CommunityForum.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.CommunityForum.bean.Problem;
import com.compassecg.test720.compassecg.GroupConsultation.activity.BrowsePicActivityH;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.NetworkImageAdapter;
import com.compassecg.test720.compassecg.View.NineGridView;
import com.compassecg.test720.compassecg.View.RoundImageView;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.test720.auxiliary.Utils.L;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.finalteam.toolsfinal.DateUtils;

import static com.compassecg.test720.compassecg.R.id.mNineGridView;

/**
 * 地址adapter
 * Created by Administrator on 2016/8/3 0003.
 */
public class CommunityAdapterH extends BaseAdapter {
    private final Context context;
    List<Problem> list = new ArrayList<>();

    public CommunityAdapterH(Context context, List<Problem> list) {
        this.context = context;
        this.list = list;
        L.e("list1", list.toString());
    }

    @Override
    public int getCount() {
        if (list.size() != 0 || list == null) {
            return list.size();
        }
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_for_community, null);

            holder.imgIv = (RoundImageView) convertView.findViewById(R.id.iv1);
            holder.nameTv = (TextView) convertView.findViewById(R.id.tv1);
            holder.contentTv = (TextView) convertView.findViewById(R.id.tv2);
            holder.timeTv = (TextView) convertView.findViewById(R.id.tv3);
            holder.numTv = (TextView) convertView.findViewById(R.id.tv4);
            holder.content2Tv = (TextView) convertView.findViewById(R.id.tv5);
            holder.mNineGridView = (NineGridView) convertView.findViewById(mNineGridView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTv.setText(list.get(position).getNickname());
        holder.contentTv.setText(list.get(position).getTitle());
        holder.content2Tv.setText(list.get(position).getContent());
        String time = DateUtils.getTimeInterval(getTime(Long.parseLong(list.get(position).getTime() + "000")));
        holder.timeTv.setText(time);
        holder.numTv.setText(list.get(position).getAsw_num());
        Glide.with(context).load(Connector.lll + list.get(position).getPic()).into(holder.imgIv);


        holder.imgIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, markActivityW.class).putExtra("type", list.get(position).getId()));
            }
        });

        List<String> imglist = new ArrayList<>();

        for (int i = 0; i < list.get(position).getImg().size(); i++) {
            imglist.add(list.get(position).getImg().get(i).getUrl());
        }
        if (imglist.size() == 0) {
            holder.mNineGridView.setVisibility(View.GONE);
        } else {
            holder.mNineGridView.setVisibility(View.VISIBLE);
        }

        NetworkImageAdapter adapter = new NetworkImageAdapter(context, imglist);
        holder.mNineGridView.setAdapter(adapter);
        holder.mNineGridView.setOnImageClickListener(new Nicgriadview(position,imglist));
        return convertView;
    }

    public class Nicgriadview implements NineGridView.OnImageClickListener {
        private int posn;
        private List<String> listl;

        public Nicgriadview(int postion, List<String> listl) {
            this.posn = postion;
            this.listl = listl;
        }

        @Override
        public void onImageCilcked(int position, View view) {
            /*current_posion = position;
            setViewPager(list3.get(posn).getPicture());*/
            Intent intent = new Intent(context, BrowsePicActivityH.class);
            intent.putStringArrayListExtra("path", (ArrayList<String>) listl);
            intent.putExtra("position", posn);
            context.startActivity(intent);
        }
    }

    class ViewHolder {
        public TextView nameTv, contentTv, content2Tv, timeTv, numTv;
        public RoundImageView imgIv;
        public NineGridView mNineGridView;
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
