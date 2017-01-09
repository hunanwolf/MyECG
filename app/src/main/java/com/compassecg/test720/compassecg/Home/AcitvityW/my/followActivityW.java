package com.compassecg.test720.compassecg.Home.AcitvityW.my;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW.Follow_list_adapterW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;

public class followActivityW extends BarBaseActivity implements Follow_list_adapterW.Callback {
    SwipeRefreshLayout swip;
    ListView list;
    Follow_list_adapterW adapter;
    String[] urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        setTitleString("我的关注");
        list = getView(R.id.list);
        swip = getView(R.id.swip);

        adapter = new Follow_list_adapterW(mContext, urls, followActivityW.this, this);
        list.setAdapter(adapter);

    }

    @Override
    public void Invitationclick(View v) {
        ShowToast("邀请");
    }

    @Override
    public void follclick(View v) {
        ShowToast("关注");
    }
}
