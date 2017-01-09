package com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.compassecg.test720.compassecg.R;

/**
 * Created by anim on 2016/8/10.
 */

public class Follow_list_adapterW extends BaseAdapter {
    private Context mContext;
    private Activity activity;
    String[] urls;
    private Callback mCallback;

    public interface Callback {
        public void Invitationclick(View v);

        public void follclick(View v);
    }

    public Follow_list_adapterW(Context mContext, String[] urls, Activity activity, Callback callback) {
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
     final    Indicator indicator ;
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
        indicator.Invitation.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.drawoblelv));
        indicator.click.setOrientation(LinearLayout.HORIZONTAL);
        indicator.follow.setVisibility(View.GONE);
    
        indicator.Invitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.Invitationclick(v);
                indicator.Invitation.setText("已邀请");
            }
        });

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



