package com.compassecg.test720.compassecg.Home.AcitvityW.my;

import android.os.Bundle;
import android.widget.ListView;

import com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW.record_list_adapterW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;

public class mingxiActivity extends BarBaseActivity {
    ListView list;
    record_list_adapterW adapterW;
    String []url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mingxi);
        setTitleString("积分明细");
        list = getView(R.id.list);
        adapterW=new record_list_adapterW(mContext,url);
        list.setAdapter(adapterW);
    }
}
