package com.compassecg.test720.compassecg.Home.AcitvityW.message;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.CircleImageView;
import com.compassecg.test720.compassecg.unitl.Connector;

import java.util.List;

/**
 * Created by anim on 2016/8/10.
 */

public class ApplicationAndNoticeadapterW extends BaseAdapter {
    private Context mContext;
    private Activity activity;
    List<String> urls;
    private Callback mCallback;

    public interface Callback {
        public void Invitationclick(View v);

        public void follclick(View v);
    }

    public ApplicationAndNoticeadapterW(Context mContext, List<String> urls, Activity activity, Callback callback) {
        this.mContext = mContext;
        this.urls = urls;
        this.activity = activity;
        this.mCallback = callback;
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
        final Indicator indicator;
        if (convertView == null) {
            indicator = new Indicator();
            convertView = View.inflate(mContext, R.layout.item_fans_list, null);
            indicator.lm_img = (CircleImageView) convertView.findViewById(R.id.lm_img);
            indicator.iv_name = (TextView) convertView.findViewById(R.id.iv_name);
            indicator.lv_position = (TextView) convertView.findViewById(R.id.lv_position);
            indicator.click = (LinearLayout) convertView.findViewById(R.id.click);
            indicator.follow = (TextView) convertView.findViewById(R.id.follow);
            indicator.Invitation = (TextView) convertView.findViewById(R.id.Invitation);
            indicator.iv_name.setTextColor(mContext.getResources().getColor(R.color.gray));
            indicator.lv_position.setTextColor(mContext.getResources().getColor(R.color.gwe));
            Glide.with(mContext)
                    .load(Connector.lll + "k")
                    .placeholder(R.drawable.ic_placeholder)
                    .centerCrop()
                    .into(indicator.lm_img);
            convertView.setTag(indicator);

        } else {
            indicator = (Indicator) convertView.getTag();
        }
        indicator.follow.setText("同意");
        indicator.Invitation.setText("拒绝");
        indicator.Invitation.setTextColor(mContext.getResources().getColor(R.color.gwe));
        indicator.Invitation.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.drawoblelll));
        indicator.follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.follclick(v);
                indicator.click.setVisibility(View.INVISIBLE);
            }
        });

        indicator.Invitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.Invitationclick(v);
                indicator.click.setVisibility(View.INVISIBLE);
            }
        });
        return convertView;
    }

    private static class Indicator {
        CircleImageView lm_img;
        TextView iv_name;
        TextView lv_position;
        TextView follow;

        TextView Invitation;
        TextView lv_huiofuc;

        LinearLayout click;
    }
}



