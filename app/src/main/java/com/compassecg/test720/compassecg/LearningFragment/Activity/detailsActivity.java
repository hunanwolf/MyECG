package com.compassecg.test720.compassecg.LearningFragment.Activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.GridView;

import com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW.magazine_Grad_adapterW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;

import java.util.ArrayList;
import java.util.List;


public class detailsActivity extends BarBaseActivity {
    magazine_Grad_adapterW adapterW;
    SwipeRefreshLayout swip;
    GridView gridview;
    List<String> list = new ArrayList<>();
    List<String> listll = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        gridview = getView(R.id.gridview);
        listll.clear();
        swip = getView(R.id.swip);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");
        list.add("13");

        adapterW = new magazine_Grad_adapterW(this, list);
//        adapterW.setSelectItem(-1);
        gridview.setAdapter(adapterW);
    }
}
