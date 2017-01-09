package com.compassecg.test720.compassecg.Home.AcitvityW.my;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.compassecg.test720.compassecg.Home.AcitvityW.my.Boughfragment.consultation;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.Boughfragment.forum;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW.MarkPagerAdapterW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.CustomViewPager;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class boughtActivity extends BarBaseActivity {
    private List<Fragment> pagerContent = new ArrayList<>();
    private  TabLayout tabs;
    View frist;
    View tow;
    private CustomViewPager pager;
    private MarkPagerAdapterW pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bought);
        int type=getIntent().getExtras().getInt("type");
        switch (type){
            case 1:
                setTitleString("我的提问");
                break;

            case 2:
                setTitleString("我的收藏");
                break;
        }

        initView();
    }

    public void initView() {

        tabs=getView(R.id.tabs);
        pager=getView(R.id.pager);
        pagerContent.add(new forum(1));
        pagerContent.add(
        new consultation(1));
        frist=getView(R.id.frist);
        tow=getView(R.id.tow);
       tabs.setTabMode(TabLayout.MODE_FIXED);
        //1.MODE_SCROLLABLE模式
//        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

//        //2.MODE_FIXED模式
//        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.addTab(tabs.newTab().setText("会诊"));
        tabs.addTab(tabs.newTab().setText("论坛"));

        pagerAdapter = new MarkPagerAdapterW(getSupportFragmentManager(), pagerContent);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);
        frist.setVisibility(View.VISIBLE);
        tow.setVisibility(View.INVISIBLE);
        pager.setPagingEnabled(false);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0){
                    frist.setVisibility(View.VISIBLE);
                    tow.setVisibility(View.INVISIBLE);
                }else {
                    frist.setVisibility(View.INVISIBLE);
                    tow.setVisibility(View.VISIBLE);
                }
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
}
