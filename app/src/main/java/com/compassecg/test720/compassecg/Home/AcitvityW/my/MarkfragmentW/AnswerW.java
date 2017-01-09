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
import com.compassecg.test720.compassecg.Home.adapter.forum_list_adapterW;
import com.compassecg.test720.compassecg.Home.baen.Answer;
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

public class AnswerW extends BaseFragment implements forum_list_adapterW.Callback {

    forum_list_adapterW adapter;
    ListView list;
    SwipeRefreshLayout swip;
    String[] urls = {""};
    int index = -1;
    private final int STRTL = 1;

    List<Answer> listanswer = new ArrayList<>();
    private int thisPage = 1;
    private int MaxPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_answer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = getView(R.id.list);
        swip = getView(R.id.swip);
        adapter = new forum_list_adapterW(getContext(), listanswer, getActivity(), this);
        list.setAdapter(adapter);
        if (uid.equals(APP.uuid)) {
            adapter.setVisition(index);
        }

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
                    getDate();
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
                getDate();
            }
        });
    }


    public void getDate() {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("p", thisPage);
        Get(Connector.answer, params, STRTL);

    }

    @Override
    public void Clean(View v) {

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
            case STRTL:

                if (jsonObject.getIntValue("code") == 1) {
                    if (thisPage == 1) {
                        listanswer.clear();
                    }
                    JSONObject jsonArray2 = jsonObject.getJSONObject("list");
                    JSONArray jsonArray= jsonArray2.getJSONArray("answer");
                    List<Answer> problj2 = JSONObject.parseArray(jsonArray.toString(), Answer.class);
                    listanswer.addAll(problj2);

                }
                adapter.notifyDataSetChanged();
                break;
        }

    }
}
