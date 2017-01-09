package com.compassecg.test720.compassecg.CommunityForum.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.CommunityForum.bean.Answer;
import com.compassecg.test720.compassecg.GroupConsultation.activity.BrowsePicActivityH;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.NetworkImageAdapter;
import com.compassecg.test720.compassecg.View.NineGridView;
import com.compassecg.test720.compassecg.View.RoundImageView;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.test720.auxiliary.Utils.L;
import com.test720.auxiliary.Utils.T;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.finalteam.toolsfinal.DateUtils;

/**
 * 地址adapter
 * Created by Administrator on 2016/8/3 0003.
 */
public class GroupKindsAnswerAdapter2H extends BaseAdapter {
    private Context context;

    List<Answer> newStr1;
    private Callback mCallback;

    public interface Callback {


        public void Zan(View v);
    }

    public GroupKindsAnswerAdapter2H(Context context, List<Answer> newStr1, Callback callback) {
        this.context = context;
        this.newStr1 = newStr1;
        this.mCallback = callback;
    }

    @Override
    public int getCount() {
        if (newStr1.size() != 0) {
            return newStr1.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return newStr1.get(position);
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
            convertView = View.inflate(context, R.layout.item_for_group_kinds_answer2, null);

            holder.imgIv = (RoundImageView) convertView.findViewById(R.id.iv1);
            holder.nameTv = (TextView) convertView.findViewById(R.id.tv1);
            holder.contentTv = (TextView) convertView.findViewById(R.id.tv2);
            holder.timeTv = (TextView) convertView.findViewById(R.id.tv5);
            holder.numTv = (TextView) convertView.findViewById(R.id.tv4);
            holder.imgSong = (ImageView) convertView.findViewById(R.id.iv_song);
            holder.mNineGridView = (NineGridView) convertView.findViewById(R.id.mNineGridView);
            holder.pulnum = (TextView) convertView.findViewById(R.id.tv6);
            holder.zan = (LinearLayout) convertView.findViewById(R.id.zan);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imgIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, markActivityW.class).putExtra("type", newStr1.get(position).getUid()));
            }
        });
        Glide.with(context).load(Connector.lll + newStr1.get(position).getPic()).placeholder(R.drawable.ic_placeholder).into(holder.imgIv);
        holder.nameTv.setText(newStr1.get(position).getNickname());
        String time = DateUtils.getTimeInterval(getTime(Long.parseLong(newStr1.get(position).getTime() + "000")));
//        Date d1 = new Date(newStr1.get(position).getTime());
        holder.timeTv.setText(time);
        holder.numTv.setText(newStr1.get(position).getZan_num());
        holder.pulnum.setText(newStr1.get(position).getPl_num());
        holder.contentTv.setText(newStr1.get(position).getContent());
        holder.imgSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(context, "语音");
            }
        });
        holder.zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.Zan(v);
            }
        });


        List<String> list = new ArrayList<String>();
        for (int i = 0; i < newStr1.get(position).getImg().size(); i++) {
            list.add(newStr1.get(position).getImg().get(i).getUrl());
        }
        L.e("img", list.toString());
        NetworkImageAdapter adapter = new NetworkImageAdapter(context, list);
        holder.mNineGridView.setAdapter(adapter);
        holder.mNineGridView.setOnImageClickListener(new Nicgriadview(position, list));
        holder.zan.setTag(position);
        return convertView;

    }

    public class Nicgriadview implements NineGridView.OnImageClickListener {
        private int posn;
        List<String> list;

        public Nicgriadview(int postion, List<String> list) {
            this.posn = postion;
            this.list = list;
        }

        @Override
        public void onImageCilcked(int position, View view) {
            /*current_posion = position;
            setViewPager(list3.get(posn).getPicture());*/
            Intent intent = new Intent(context, BrowsePicActivityH.class);
            intent.putStringArrayListExtra("path", (ArrayList<String>) list);
            intent.putExtra("position", String.valueOf(posn));
            context.startActivity(intent);
        }

    }

    class ViewHolder {

        public TextView nameTv, contentTv, timeTv, numTv, pulnum;
        public RoundImageView imgIv;
        public ImageView imgSong;
        public NineGridView mNineGridView;

        public LinearLayout zan;
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
