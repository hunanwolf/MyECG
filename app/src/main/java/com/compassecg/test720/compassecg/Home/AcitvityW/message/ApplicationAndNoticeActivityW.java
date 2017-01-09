package com.compassecg.test720.compassecg.Home.AcitvityW.message;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ApplicationAndNoticeActivityW extends BarBaseActivity implements ApplicationAndNoticeadapterW.Callback {
    ApplicationAndNoticeadapterW adapter;
    SwipeRefreshLayout swip;
    ListView list;
    List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fans);
        setTitleString("申请与邀请");
        list = getView(R.id.list);
        swip = getView(R.id.swip);
        adapter = new ApplicationAndNoticeadapterW(this, urls, this, this);
        list.setAdapter(adapter);
    }

    @Override
    public void Invitationclick(View v) {
        ShowToast("拒绝");
    }

    @Override
    public void follclick(View v) {
        ShowToast("同意");
    }
}
