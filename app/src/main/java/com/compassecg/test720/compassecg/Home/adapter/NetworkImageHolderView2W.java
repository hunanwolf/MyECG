package com.compassecg.test720.compassecg.Home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.Home.baen.Ad;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.Connector;


/**
 * Created by anim on 2016/8/25.
 */

public class NetworkImageHolderView2W implements Holder<Ad> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Ad data) {
        imageView.setImageResource(R.color.shallowGrey);

        Glide.with(context).load(Connector.lll+data.getCover()) .placeholder(R.drawable.ic_placeholder).into(imageView);
    }






}