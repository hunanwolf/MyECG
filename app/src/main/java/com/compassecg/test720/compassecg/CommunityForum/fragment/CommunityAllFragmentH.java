package com.compassecg.test720.compassecg.CommunityForum.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.compassecg.test720.compassecg.CommunityForum.activity.PostDetailsActivityH;
import com.compassecg.test720.compassecg.CommunityForum.adapter.CommunityAdapterH;
import com.compassecg.test720.compassecg.CommunityForum.bean.Problem;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.MyListView;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.Logger;
import com.test720.auxiliary.Utils.BaseFragment;
import com.test720.auxiliary.Utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2016/12/6.
 */

public class CommunityAllFragmentH extends BaseFragment {


    private static final int DATA1 = 1;
    public static CommunityAllFragmentH maninfrag = null;
    private MyListView lv_group;
    private CommunityAdapterH adapter;
    private RelativeLayout rl_quanguo;
    private RelativeLayout rl_city;
    private ImageView iv_create;
    private ImageView iv_search;
    private ListView listView;
    private int page = 1;
    private List<Problem> list = new ArrayList<>();
    private SwipeRefreshLayout swip;
    String i = "";
    private int thispage = 1;
    private int MaxPage;

    public CommunityAllFragmentH(String i) {
        super();
        this.i = i;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initView(inflater);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setAdapter();
        getData();
        setListener();
    }

    private void getData() {
        RequestParams params = new RequestParams();
        params.put("module", i);
        params.put("p", thispage);
        Get(Connector.index, params, DATA1);

    }

    private void fetchData() {
        RequestParams params = new RequestParams();
        params.put("module", i);
        params.put("p", thispage);
        Get(Connector.index, params, DATA1);

    }

    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        if (what == DATA1) {
            String code = jsonObject.getString("code");
            if ("0".equals(code)) {
                Logger.d("联网失败");
                list.clear();
                adapter.notifyDataSetChanged();
            } else if ("1".equals(code)) {
                Logger.d("联网成功");
                if (thispage == 1) {
                    list.clear();
                }
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                if (jsonArray.size() == 0) {
                    if (thispage > 1) {
                        thispage--;
                    }
                } else {
                    List<Problem> problj = JSONObject.parseArray(jsonArray.toString(), Problem.class);
                    list.addAll(problj);
                    L.e("list",list.toString());
                    adapter.notifyDataSetChanged();
                }
            }

        }
    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PostDetailsActivityH.class);
                intent.putExtra("id", list.get(position).getId());
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (view.getLastVisiblePosition() == view.getCount() - 1 & !progressBar.isShowing()) {
                    thispage++;
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
                thispage = 1;
                getData();
            }
        });
    }

    private void setAdapter() {
        adapter = new CommunityAdapterH(getContext(), list);
        listView.setAdapter(adapter);
    }

    private void initView(LayoutInflater inflater) {
        rootView = inflater.inflate(R.layout.fragment_community_all, null);
        listView = (ListView) rootView.findViewById(R.id.listView);
        swip = (SwipeRefreshLayout) rootView.findViewById(R.id.swip);

    }


}
