package com.compassecg.test720.compassecg.Home.AcitvityW.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivityW extends BarBaseActivity {
    SwipeRefreshLayout swip;
    ListView list;
    Notice_list_adapterW adapterW;
    List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymessage_w);
        setTitleString("公告");
        list = getView(R.id.list);
        swip = getView(R.id.swip);
        adapterW = new Notice_list_adapterW(mContext, stringList);
        list.setAdapter(adapterW);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(NoticeActivityW.this, systemnew.class));
            }
        });
    }
}
