package com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.LearningFragment.bean.doctorList;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.Connector;

import java.util.List;

/**
 * Created by anim on 2016/8/10.
 */

public class Fans_list_adapterW extends BaseAdapter {
    private final int type;
    private Context mContext;
    private Activity activity;
    List<doctorList> list;
    private Callback mCallback;

    public interface Callback {
        public void Invitationclick(View v);

        public void follclick(View v);
    }

    public Fans_list_adapterW(Context mContext, List<doctorList> list, Activity activity, Callback callback, int type) {
        this.mContext = mContext;
        this.list = list;
        this.activity = activity;
        this.mCallback = callback;
        this.type = type;
    }

    @Override
    public int getCount() {
        if (list.size() != 0) {
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
        final Indicator indicator;
        if (convertView == null) {
            indicator = new Indicator();
            convertView = View.inflate(mContext, R.layout.item_fans_list, null);
            indicator.lm_img = (ImageView) convertView.findViewById(R.id.lm_img);
            indicator.iv_name = (TextView) convertView.findViewById(R.id.iv_name);
            indicator.lv_position = (TextView) convertView.findViewById(R.id.lv_position);
            indicator.click = (LinearLayout) convertView.findViewById(R.id.click);
            indicator.follow = (TextView) convertView.findViewById(R.id.follow);
            indicator.Invitation = (TextView) convertView.findViewById(R.id.Invitation);
            convertView.setTag(indicator);
        } else {
            indicator = (Indicator) convertView.getTag();
        }


        indicator.iv_name.setText(list.get(position).getNickname());
        indicator.lv_position.setText(list.get(position).getHospital() + "·" + list.get(position).getDesk() + "·" + list.get(position).getJob());

        Glide.with(mContext)
                .load(Connector.lll + list.get(position).getPic())
                .placeholder(R.mipmap.zazhi)
                .centerCrop()
                .into(indicator.lm_img);

        //成员列表
        if (type == 1) {
            indicator.Invitation.setText("踢出");
            indicator.Invitation.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.drawoblelv));
            indicator.follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.follclick(v);

                    if (list.get(position).getIs_gz() == 1) {
                        indicator.follow.setText("已关注");
                    } else {
                        indicator.follow.setText("关注");
                    }
                }
            });

            indicator.Invitation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.Invitationclick(v);
                    //indicator.Invitation.setText("已邀请");

                }
            });
        }
        //医生列表
        if (type == 2) {

            indicator.Invitation.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.drawoblelv));
            indicator.follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.follclick(v);
                    indicator.follow.setText("已关注");
                }
            });

            if (list.get(position).getIs_yq() == 1) {
                indicator.Invitation.setVisibility(View.INVISIBLE);
            } else {
                indicator.Invitation.setText("邀请");
                indicator.Invitation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCallback.Invitationclick(v);
                        indicator.Invitation.setText("已邀请");
                    }
                });
            }


        }

        return convertView;
    }

    public class Indicator {
        ImageView lm_img;
        TextView iv_name;
        TextView lv_position;
        TextView follow;

        TextView Invitation;
        TextView lv_huiofuc;

        LinearLayout click;
    }
}



