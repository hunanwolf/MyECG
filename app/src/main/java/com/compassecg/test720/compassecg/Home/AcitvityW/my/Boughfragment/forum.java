package com.compassecg.test720.compassecg.Home.AcitvityW.my.Boughfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.compassecg.test720.compassecg.Home.adapter.forum_list_adapterW;
import com.compassecg.test720.compassecg.Home.baen.Answer;
import com.compassecg.test720.compassecg.R;
import com.test720.auxiliary.Utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2016/12/14.
 */

public class forum extends BaseFragment implements forum_list_adapterW.Callback {
    forum_list_adapterW adapter;
    ListView list;
    SwipeRefreshLayout swip;
    String[] urls = {""};
    int index = -1;

    public forum(int i) {
        super();
        index = i;
    }

    List<Answer> listanswer = new ArrayList<>();

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
        adapter.setVisition(index);
    }

    @Override
    public void Clean(View v) {
        ShowToast("删除");
    }
}
