package com.compassecg.test720.compassecg.Home.AcitvityW;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.Home.MainFragmentW;
import com.compassecg.test720.compassecg.R;
import com.test720.auxiliary.Utils.NoBarBaseActivity;

import java.util.ArrayList;
import java.util.List;


public class city_choose extends NoBarBaseActivity {
    private ListView listView;

    private List<String> nowProvinces = new ArrayList<>();


    ScrollView scoll;
    ImageButton clearSearch;
    EditText query;
    private InputMethodManager inputMethodManager;
    TextView dingweicity;
    String urls[] = {"北京", "安徽", "上海", "云南", "贵州", "辽宁", "福建", "新疆", "广东", "河北", "江苏", "台湾", "吉林", "内蒙古", "浙江", "西藏", "青海", "澳门", "湖北", "黑龙江",
            "广西", "河南", "山西", "重庆", "江西", "天津", "宁夏", "陕西", "香港", "甘肃", "湖南", "湖南","四川","山东"};
    city_list_adapterW adapterW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choose);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        listView = (ListView) getView(R.id.list);

        scoll = getView(R.id.scoll);
        dingweicity = getView(R.id.dingweicity);
        dingweicity.setText(APP.baiduCity);
        adapterW=new city_list_adapterW(mContext);
        listView.setAdapter(adapterW);
        setListViewHeightBasedOnChildren(listView);
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scoll.fullScroll(ScrollView.FOCUS_UP);
            }
        },0);

        if ("".equals(APP.baiduCity)) {
        } else {
            dingweicity.setOnClickListener(this);
        }
        getView(R.id.back).setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowToast(urls[position]);
                MainFragmentW.maninfrag.setcity(urls[position]);
                finish();
            }
        });

    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        if (listAdapter.getCount() != 0) {
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            System.out.println("aaa===" + totalHeight);
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
        }
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.dingweicity:
               MainFragmentW.maninfrag.setcity(APP.baiduCity);
                finish();
                break;
            case R.id.back:
                finish();
                break;
        }
    }


}
