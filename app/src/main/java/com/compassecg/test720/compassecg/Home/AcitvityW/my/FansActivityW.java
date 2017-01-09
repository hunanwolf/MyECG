package com.compassecg.test720.compassecg.Home.AcitvityW.my;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW.Fans_list_adapterW;
import com.compassecg.test720.compassecg.LearningFragment.bean.doctorList;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class FansActivityW extends BarBaseActivity implements Fans_list_adapterW.Callback{
    Fans_list_adapterW adapter;
    SwipeRefreshLayout swip;
    ListView list;
    List<doctorList> list1 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fans);
        setTitleString("我的粉丝");
        list=getView(R.id.list);
        swip=getView(R.id.swip);
        adapter=new Fans_list_adapterW(this,list1,this,this,2);
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
