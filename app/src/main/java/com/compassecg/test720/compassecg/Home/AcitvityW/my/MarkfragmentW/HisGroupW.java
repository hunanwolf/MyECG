package com.compassecg.test720.compassecg.Home.AcitvityW.my.MarkfragmentW;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW.hisgoup_list_adapterW;
import com.compassecg.test720.compassecg.Home.baen.goup;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import static com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW.uid;

/**
 * Created by hp on 2016/12/14.
 */

public class HisGroupW extends BaseFragment implements hisgoup_list_adapterW.Callback {

    SwipeRefreshLayout swip;
    ListView list;
    hisgoup_list_adapterW adapter;
    String[] urls = {""};
    private final int SATTT = 1;
    List<goup> listgou = new ArrayList<>();
    private int thispage = 1;
    private final int STATl = 2;
    private int thisPage = 1;
    private int MaxPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_hisgroup, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new hisgoup_list_adapterW(getContext(), listgou, getActivity(), this);
        list = getView(R.id.list);
        swip = getView(R.id.swip);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (view.getLastVisiblePosition() == view.getCount() - 1 && thisPage < MaxPage & !progressBar.isShowing()) {
                    thisPage++;
//                    Toast.makeText(getActivity(), "上拉加载更多！", Toast.LENGTH_SHORT).show();
                    fetchData();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swip.setRefreshing(false);
                thisPage = 1;
                fetchData();
            }
        });
    }

    public void fetchData() {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("to_uid", uid);
        Get(Connector.group, params, SATTT);
    }

    public void getDate() {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("to_uid", uid);
        Post(Connector.group, params, SATTT);
    }

    @Override
    public void Invitationclick(View v) {

    }

    @Override
    public void follclick(View v) {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("gid", listgou.get((Integer) v.getTag()).getId());
        params.put("guid", listgou.get((Integer) v.getTag()).getUid());
        Postl(Connector.applyGroup, params, STATl);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDate();
    }

    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);
        switch (what) {
            case SATTT:
                if (jsonObject.getIntValue("code") == 1) {
                    if (thispage == 1) {
                        listgou.clear();
                    }
                    JSONObject object=jsonObject.getJSONObject("list");
                    JSONArray jsonArray2 = object.getJSONArray("group");
                    List<goup> problj2 = JSONObject.parseArray(jsonArray2.toString(), goup.class);
                    listgou.addAll(problj2);
                }
                adapter.notifyDataSetChanged();
                break;

            case STATl:

                if (jsonObject.getIntValue("code") == 1) {
                    RequestParams params = new RequestParams();
                    params.put("uid", APP.uuid);
                    params.put("to_uid", uid);
                    Postl(Connector.group, params, SATTT);
                    ShowToast("已发送申请");
                    thispage = 1;
                } else {
                    ShowToast("发送失败");
                }
                break;
        }
    }
}
