package com.compassecg.test720.compassecg.Home.AcitvityW.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW.integral_list_adapterW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.CircleImageView;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.test720.auxiliary.Utils.NoBarBaseActivity;

public class integralActivity extends NoBarBaseActivity implements integral_list_adapterW.Callback {
    SwipeRefreshLayout swip;
    String[] urls;
    ListView list;
    integral_list_adapterW adapterW;
    CircleImageView header_img;
    RelativeLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        list = getView(R.id.list);
        swip = getView(R.id.swip);
        header_img = getView(R.id.header_img);

        adapterW = new integral_list_adapterW(mContext, urls, this, this);
        Glide.with(mContext)
                .load(Connector.lll + "k")
                .placeholder(R.drawable.index_head)
                .centerCrop()
                .into(header_img);
        intisview();
    }

    public void intisview() {
        getView(R.id.lv_mingxi).setOnClickListener(this);
        getView(R.id.rule).setOnClickListener(this);
        getView(R.id.Exchangerecord).setOnClickListener(this);
        getView(R.id.back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {

            case R.id.lv_mingxi:
                startActivity(new Intent(this, mingxiActivity.class));
                break;

            case R.id.rule:
                startActivity(new Intent(this, ruleActivity.class));
                break;

            case R.id.Exchangerecord:
                startActivity(new Intent(this, ExchangerecordActivity.class));
                break;

            case R.id.back:
                finish();
                break;
        }
    }

    //点击按钮 兑换 、阅读  按钮操作。
    @Override
    public void Invitationclick(View v) {
        ShowToast("兑换");
    }

    @Override
    public void follclick(View v) {

    }
}
