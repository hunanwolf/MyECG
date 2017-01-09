package com.compassecg.test720.compassecg.Home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.compassecg.test720.compassecg.Home.baen.mygoup;
import com.compassecg.test720.compassecg.R;
import com.test720.auxiliary.Utils.L;

import java.util.List;

/**
 * Created by anim on 2016/8/9.
 */

public class Intion_adapterW extends BaseAdapter {


    List<mygoup> listmygoup;
    List<String> list;
    private Context mContext;

    public Intion_adapterW(Context mContext, List<mygoup> listmygoup) {
        this.mContext = mContext;
        this.listmygoup = listmygoup;
        L.e("lst", listmygoup.toString());
    }

    @Override
    public int getCount() {
        if (listmygoup.size() != 0) {
            return listmygoup.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return listmygoup.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Indicator indicator = null;
        if (convertView == null) {
            indicator = new Indicator();
            convertView = View.inflate(mContext, R.layout.item_intion, null);
            indicator.name = (TextView) convertView.findViewById(R.id.name);

            indicator.img = (ImageView) convertView.findViewById(R.id.img);

            convertView.setTag(indicator);
        } else {
            indicator = (Indicator) convertView.getTag();
        }
        indicator.name.setText(listmygoup.get(position).getName());
        if (selectItem == position) {
            indicator.img.setImageResource(R.mipmap.group_choice_pre);
        } else {
            indicator.img.setImageResource(R.mipmap.group_choice);
        }
        return convertView;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    private int selectItem = -1;

    public class Indicator {
        private TextView name;
        private ImageView img;

    }
}

