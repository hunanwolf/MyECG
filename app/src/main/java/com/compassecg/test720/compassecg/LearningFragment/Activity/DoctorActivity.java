package com.compassecg.test720.compassecg.LearningFragment.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW.Fans_list_adapterW;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW;
import com.compassecg.test720.compassecg.Home.adapter.Intion_adapterW;
import com.compassecg.test720.compassecg.Home.baen.mygoup;
import com.compassecg.test720.compassecg.Home.seach.search_activity;
import com.compassecg.test720.compassecg.LearningFragment.bean.doctorList;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.NoBarBaseActivity;

import java.util.ArrayList;
import java.util.List;

import static com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW.uid;
import static com.compassecg.test720.compassecg.Home.seach.search_activity.JNLAKNGANGALKM;

public class DoctorActivity extends NoBarBaseActivity implements Fans_list_adapterW.Callback {
    ListView listView;
    SwipeRefreshLayout swip;
    Fans_list_adapterW adapter;
    String[] urls = {};
    RelativeLayout back;
    RelativeLayout search;
    private TextView tv_title;
    private int thispage = 1;
    private final int START = 1;
    List<doctorList> list = new ArrayList<>();

    private final int STATTT = 2;
    private final int STARTL = 3;
    List<mygoup> listmygoup = new ArrayList<>();
    private final  int STTTTL = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        listView = getView(R.id.list);
        swip = getView(R.id.swip);
        tv_title = getView(R.id.tv_title);
        search = getView(R.id.search);

        getView(R.id.back).setOnClickListener(this);
        getView(R.id.search).setOnClickListener(this);

        if ("成员".equals(getIntent().getStringExtra("type"))) {
            tv_title.setText("成员列表");
            search.setVisibility(View.GONE);
            adapter = new Fans_list_adapterW(this, list, this, this, 1);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //startActivity(new Intent(DoctorActivity.this, markActivityW.class).putExtra("type", 2));
                }
            });
        } else if ("医生".equals(getIntent().getStringExtra("type"))) {
            tv_title.setText("医生列表");
            adapter = new Fans_list_adapterW(this, list, this, this, 2);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    startActivity(new Intent(DoctorActivity.this, markActivityW.class).putExtra("type", 2));
                }
            });
        }


        getDate();

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
        params.put("p", thispage);
        Get(Connector.doctorList, params, START);
    }

    @Override
    public void Invitationclick(View v) {

        if ("医生".equals(getIntent().getStringExtra("type"))) {
            changgeedit();
//            ShowToast("邀请");
        } else {
            RequestParams params = new RequestParams();
            params.put("uid", list.get((Integer) v.getTag()).getId());
            params.put("gid", thispage);
            Post(Connector.groupOut, params, STARTL);
        }

    }

    private int index = -1;

    private void changgeedit() {
        // 取得自定义View

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View myLoginView = layoutInflater.inflate(R.layout.invitionlayout, null);
        final TextView ok = (TextView) myLoginView.findViewById(R.id.ok);
        final TextView clean = (TextView) myLoginView.findViewById(R.id.clean);
        final ListView list = (ListView) myLoginView.findViewById(R.id.list);
        final Intion_adapterW adapter = new Intion_adapterW(mContext, listmygoup);
        list.setAdapter(adapter);
        adapter.setSelectItem(-1);

        final AlertDialog dlgShowBack = new AlertDialog.Builder(this).create();

        dlgShowBack.setView(myLoginView);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == -1) {
                    ShowToast("请选择邀请进哪个组");
                    return;
                }
                RequestParams parms = new RequestParams();
                parms.put("uid", APP.uuid);
                parms.put("get_uid", uid);
                parms.put("gid", listmygoup.get(index).getId());
                parms.put("gname", listmygoup.get(index).getName());
                Postl(Connector.sendInvite, parms, STTTTL);
                dlgShowBack.dismiss();
            }
        });
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlgShowBack.dismiss();
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                adapter.setSelectItem(position);
                adapter.notifyDataSetChanged();
            }
        });
        dlgShowBack.setCancelable(false);
        dlgShowBack.show();

    }

    @Override
    public void follclick(View v) {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("pid", uid);
        Postl(Connector.addGuanzhu, params, STATTT);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.search: //搜索


                startActivity(new Intent(this, search_activity.class).putExtra("type", JNLAKNGANGALKM));
                break;
        }
    }

    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);
        switch (what) {

            case START:

                if (jsonObject.getIntValue("code") == 1) {

                    JSONArray jsonArray = jsonObject.getJSONArray("list");

                    List<doctorList> problj = JSONObject.parseArray(jsonArray.toString(), doctorList.class);
                    list.addAll(problj);
                    adapter.notifyDataSetChanged();
                }
                break;

            case STATTT:

                if (jsonObject.getIntValue("code") == 1) {
                    ShowToast("关注");
                }

                break;

            case STARTL:
                if (jsonObject.getIntValue("code") == 1) {
                    ShowToast("已踢出");
                    RequestParams params = new RequestParams();
                    params.put("uid", APP.uuid);
                    params.put("p", thispage);
                    Getl(Connector.doctorList, params, START);
                }

                break;
        }
    }
}
