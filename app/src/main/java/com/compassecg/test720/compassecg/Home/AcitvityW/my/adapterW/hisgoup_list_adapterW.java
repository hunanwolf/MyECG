package com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.Home.baen.goup;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.Connector;

import java.util.List;

import static com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW.uid;

/**
 * Created by anim on 2016/8/10.
 */

public class hisgoup_list_adapterW extends BaseAdapter {
    private Context mContext;
    private Activity activity;
    String[] urls;
    private Callback mCallback;
    List<goup> listgou;

    public interface Callback {
        public void Invitationclick(View v);

        public void follclick(View v);
    }

    public hisgoup_list_adapterW(Context mContext, List<goup> listgou, Activity activity, Callback callback) {
        this.mContext = mContext;
        this.listgou = listgou;
        this.activity = activity;
        this.mCallback = callback;
    }

    @Override
    public int getCount() {
        if (listgou.size() != 0) {
            return listgou.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return listgou.get(position);
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
            convertView = View.inflate(mContext, R.layout.item_hisgoup_list, null);
            indicator.lm_img = (ImageView) convertView.findViewById(R.id.lm_img);
            indicator.iv_name = (TextView) convertView.findViewById(R.id.iv_name);
            indicator.lv_position = (TextView) convertView.findViewById(R.id.lv_position);
            indicator.follow = (TextView) convertView.findViewById(R.id.follow);
            if (uid.equals(APP.uuid)) {
                indicator.follow.setVisibility(View.INVISIBLE);
            }
            convertView.setTag(indicator);
        } else {
            indicator = (Indicator) convertView.getTag();
        }
        indicator.iv_name.setText(listgou.get(position).getName());
        indicator.lv_position.setText(listgou.get(position).getNum());
        Glide.with(mContext)
                .load(Connector.lll + listgou.get(position).getCover())
                .placeholder(R.color.hui)
                .centerCrop()
                .into(indicator.lm_img);
        if (listgou.get(position).getIs_gid() == 1) {
            indicator.follow.setText("已加入");
        } else {
            indicator.follow.setText("申请加入");
        }
        indicator.follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listgou.get(position).getIs_gid() == 0) {
                    mCallback.follclick(v);
                }
            }
        });//申请

        return convertView;
    }

    public class Indicator {
        ImageView lm_img;
        TextView iv_name;
        TextView lv_position;
        TextView follow;


    }
}



