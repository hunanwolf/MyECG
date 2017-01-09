package com.compassecg.test720.compassecg.Home.AcitvityW.my;

import android.os.Bundle;
import android.widget.ListView;

import com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW.EXrecord_list_adapterW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;

public class ExchangerecordActivity extends BarBaseActivity {
    ListView list;
    EXrecord_list_adapterW adapterW;
    String []url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchangerecord);
        list=getView(R.id.list);
        setTitleString("兑换记录");
        adapterW=new EXrecord_list_adapterW(mContext,url);
        list.setAdapter(adapterW);
    }
}
