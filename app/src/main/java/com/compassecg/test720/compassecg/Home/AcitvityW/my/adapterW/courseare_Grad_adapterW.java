package com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.compassecg.test720.compassecg.R;

import java.util.List;

/**
 * Created by anim on 2016/8/9.
 */

public class courseare_Grad_adapterW extends BaseAdapter {

//    private String list[] = {"待付抢单","待付款","待发货","待收货","待评价","异常订单"};
//    private int icon[] = {R.drawable.qiang_da,R.drawable.dai_fukuan,R.drawable.dai_fahuo,R.drawable.dai_shouhuo,R.drawable.dai_evaluate,R.drawable.yichang_dingdan};

    List<String> list;
    private Context mContext;

    public courseare_Grad_adapterW(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
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
        Indicator indicator = null;
        if (convertView == null) {
            indicator = new Indicator();
            convertView = View.inflate(mContext, R.layout.item_coureseware_gradview, null);
            indicator.title = (TextView) convertView.findViewById(R.id.title);
            indicator.img = (ImageView) convertView.findViewById(R.id.img);
            indicator.domwlod = (TextView) convertView.findViewById(R.id.domwlod);
            indicator.tureload = (ImageView) convertView.findViewById(R.id.tureload);
            indicator.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            indicator.checkBox.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) mContext);
            convertView.setTag(indicator);
        } else {
            indicator = (Indicator) convertView.getTag();
        }
//        indicator.title.setText(list.get(position));
//       indicator.img.setImageResource(icon[position]);

        return convertView;
    }

    public class Indicator {
        private TextView title;
        private ImageView img;
        private ImageView tureload;
        private CheckBox checkBox;
        private TextView domwlod;

    }
}

