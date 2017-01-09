package com.compassecg.test720.compassecg.Home.AcitvityW.message;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class mymessageActivityW extends BarBaseActivity {
    SwipeRefreshLayout swip;
    ListView list;
    mymessage_list_adapterW adapterW;
    List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymessage_w);
        setTitleString("我的消息");
        list = getView(R.id.list);
        swip = getView(R.id.swip);
        adapterW = new mymessage_list_adapterW(mContext, urls);
        list.setAdapter(adapterW);
    }
}
