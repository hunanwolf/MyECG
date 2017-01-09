package com.compassecg.test720.compassecg.Home.AcitvityW.my;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.MarkfragmentW.AnswerW;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.MarkfragmentW.BIfragmentW;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.MarkfragmentW.CoursewareW;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.MarkfragmentW.HisGroupW;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.MarkfragmentW.MagazineW;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW.MarkPagerAdapterW;
import com.compassecg.test720.compassecg.Home.adapter.Intion_adapterW;
import com.compassecg.test720.compassecg.Home.baen.mygoup;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.CustomViewPager;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.T;

import java.util.ArrayList;
import java.util.List;


public class markActivityW extends BarBaseActivity {
    private List<Fragment> pagerContent = new ArrayList<>();
    private TabLayout tabs;
    LinearLayout elsel;
    private CustomViewPager pager;
    private MarkPagerAdapterW pagerAdapter;
    TextView intvtion;//邀请
    TextView farll;//关注
    TextView chat;//私聊
    public static String uid;
    private final int STATTT = 1;
    private final int STTTT = 2;
    private final int STTTTL = 3;
    List<mygoup> listmygoup = new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
        setTitleString(getIntent().getStringExtra("nicname"));
        elsel = getView(R.id.elsel);
        if (getIntent().getExtras().getString("type").equals(APP.uuid)) {
            elsel.setVisibility(View.GONE);
        } else {
            elsel.setVisibility(View.VISIBLE);
            getDate();
        }
        uid = getIntent().getExtras().getString("type");
        intvtion = getView(R.id.intvtion);
        intvtion.setOnClickListener(this);
        getView(R.id.chat).setOnClickListener(this);
        getView(R.id.farll).setOnClickListener(this);
        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void getDate() {
        RequestParams parms = new RequestParams();
        parms.put("uid", APP.uuid);
        parms.put("to_uid", uid);
        Postl(Connector.myGroup, parms, STTTT);
    }


    public void initView() {
        tabs = getView(R.id.tabs);
        pager = getView(R.id.pager);
        pagerContent.add(new BIfragmentW());
        pagerContent.add(new HisGroupW());
        pagerContent.add(new AnswerW());
        pagerContent.add(new MagazineW(-1));
        pagerContent.add(new CoursewareW(-1));
//        tabs.setTabMode(TabLayout.MODE_FIXED);
        //1.MODE_SCROLLABLE模式
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

//        //2.MODE_FIXED模式
//        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.addTab(tabs.newTab().setText("简介"));
        if (getIntent().getExtras().getString("type").equals(APP.uuid)) {
            tabs.addTab(tabs.newTab().setText("我的组"));
        } else {
            tabs.addTab(tabs.newTab().setText("TA的组"));
        }

        tabs.addTab(tabs.newTab().setText("回答"));
        tabs.addTab(tabs.newTab().setText("杂志"));
        tabs.addTab(tabs.newTab().setText("课件"));
        pagerAdapter = new MarkPagerAdapterW(getSupportFragmentManager(), pagerContent);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);
        pager.setPagingEnabled(false);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                tabs.getTabAt(position).select();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//        pager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.chat:
                T.showShort(mContext, "私聊");
                break;

            case R.id.farll:

                RequestParams params = new RequestParams();
                params.put("uid", APP.uuid);
                params.put("pid", uid);
                Postl(Connector.addGuanzhu, params, STATTT);
                T.showShort(mContext, "关注");
                break;

            case R.id.intvtion:
                T.showShort(mContext, "邀请");
                changgeedit();
                break;
        }
    }

    //    public void getsendInvite() {
//        RequestParams parms = new RequestParams();
//        parms.put("uid", APP.uuid);
//        parms.put("get_uid", uid);
//        parms.put("gid", listmygoup.get());
//        parms.put("gname");
//        Postl(Connector.sendInvite, parms, STTTTL);
//    }
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
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);
        switch (what) {
            case STATTT:
                if (jsonObject.getIntValue("code") == 1) {
                    intvtion.setText("已关注");

                } else {
                    ShowToast("关注失败！");
                }

                break;

            case STTTTL:
                if (jsonObject.getIntValue("code") == 1) {
                    ShowToast("邀请已发出");
                }

                break;
            case STTTT:

                if (jsonObject.getIntValue("code") == 1) {
                    JSONArray jsonArray2 = jsonObject.getJSONArray("list");
                    List<mygoup> problj2 = JSONObject.parseArray(jsonArray2.toString(), mygoup.class);
                    listmygoup.addAll(problj2);

                }
                break;
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("markActivityW Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
