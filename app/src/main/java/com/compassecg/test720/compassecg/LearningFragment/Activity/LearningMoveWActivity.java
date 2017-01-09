package com.compassecg.test720.compassecg.LearningFragment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.Home.seach.search_activity;
import com.compassecg.test720.compassecg.LearningFragment.adapterW.Zazi_Gradview_Adapter;
import com.compassecg.test720.compassecg.LearningFragment.bean.Courseware;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.NoBarBaseActivity;

import java.util.ArrayList;
import java.util.List;

import static com.compassecg.test720.compassecg.Home.seach.search_activity.JMLKMLLLKL;
import static com.compassecg.test720.compassecg.Home.seach.search_activity.KAJNGANGIOAN;

public class LearningMoveWActivity extends NoBarBaseActivity {
    GridView list;

    SwipeRefreshLayout swip;
    Zazi_Gradview_Adapter adapterW;
    List<Courseware> listll = new ArrayList<>();
    TextView tite;
    RelativeLayout back;

    RelativeLayout search;
    private int thispage = 1;
    private final int STATT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_move_w);
        tite = getView(R.id.tite);
        switch (getIntent().getExtras().getInt("type")) {
            case 1:
                tite.setText("杂志阅读");

                break;
            case 2:
                tite.setText("课件下载");

                break;
        }


        list = getView(R.id.list);
        swip = getView(R.id.swip);
        adapterW = new Zazi_Gradview_Adapter(mContext, listll);
        list.setAdapter(adapterW);
        getDate();

        getView(R.id.back).setOnClickListener(this);
        getView(R.id.search).setOnClickListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (getIntent().getExtras().getInt("type")) {
                    case 1:

                        startActivity(new Intent(mContext, CoursewaredetailsActivity.class).putExtra("type", 1));
                        break;

                    case 2:
                        startActivity(new Intent(mContext, CoursewaredetailsActivity.class).putExtra("type", 2));
                        break;
                }

            }
        });
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swip.setRefreshing(false);
                thispage = 1;
                getDate();
            }
        });

    }

    public void getDate() {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("type", getIntent().getExtras().getInt("type"));
        params.put("p", thispage);
        Post(Connector.Studymore, params, STATT);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.back:


                finish();
                break;

            case R.id.search://搜索
                switch (getIntent().getExtras().getInt("type")) {
                    case 1:
                        startActivity(new Intent(this, search_activity.class).putExtra("type", KAJNGANGIOAN));
                        break;

                    case 2:
                        startActivity(new Intent(this, search_activity.class).putExtra("type", JMLKMLLLKL));
                        break;
                }

                break;
        }
    }

    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);

        switch (what) {

            case STATT:

                if (jsonObject.getIntValue("code") == 1) {
                    if (thispage==1){
                        listll.clear();
                    }
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    List<Courseware> problj = JSONObject.parseArray(jsonArray.toString(), Courseware.class);
                    listll.addAll(problj);
                    adapterW.notifyDataSetChanged();
                }
                break;
        }

    }
}
