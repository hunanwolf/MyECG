package com.compassecg.test720.compassecg.GroupConsultation.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.CommunityForum.bean.Comment;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.RoundImageView;
import com.compassecg.test720.compassecg.unitl.Connector;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.finalteam.toolsfinal.DateUtils;

/**
 * 地址adapter
 * Created by Administrator on 2016/8/3 0003.
 */
public class GroupKindsAnswerFinalAdapterH extends BaseAdapter {
    private Context context;
    List<Comment> listll;

    public GroupKindsAnswerFinalAdapterH(Context context, List<Comment> listll) {
        this.context = context;
        this.listll = listll;
    }

    @Override
    public int getCount() {
        if (listll.size() != 0) {
            return listll.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return listll.get(position);
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
            convertView = View.inflate(context, R.layout.item_for_group_kinds_answer_final, null);

            holder.imgIv = (RoundImageView) convertView.findViewById(R.id.iv1);
            holder.nameTv = (TextView) convertView.findViewById(R.id.tv1);
            holder.contentTv = (TextView) convertView.findViewById(R.id.tv2);
            holder.timeTv = (TextView) convertView.findViewById(R.id.tv5);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//
        Glide.with(context).load(Connector.lll + listll.get(position).getPic()).placeholder(R.drawable.ic_placeholder).into(holder.imgIv);
        holder.nameTv.setText(listll.get(position).getNickname());
        holder.contentTv.setText(listll.get(position).getContent());
        String time = DateUtils.getTimeInterval(getTime(Long.parseLong(listll.get(position).getTime() + "000")));
        holder.timeTv.setText(time);
        holder.imgIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, markActivityW.class).putExtra("type", listll.get(position).getUid()));
            }
        });
        return convertView;
    }


    class ViewHolder {

        public TextView nameTv, contentTv, timeTv;
        public RoundImageView imgIv;
    }

    /**
     * 将毫秒转为yyyy-MM-dd HH:mm:ss的date格式
     *
     * @param time
     * @return
     */
    private String getTime(Long time) {
        Log.e("time", String.valueOf(time));
        //long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date(time);
        String t1 = format.format(d1);
        Log.e("msg", t1);
        return t1;
    }
}
