package com.compassecg.test720.compassecg.Home.AcitvityW.my;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.compassecg.test720.compassecg.Home.AcitvityW.my.MarkfragmentW.CoursewareW;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.MarkfragmentW.MagazineW;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW.MarkPagerAdapterW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.CustomViewPager;
import com.test720.auxiliary.Utils.L;
import com.test720.auxiliary.Utils.NoBarBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MydownloadActivity extends NoBarBaseActivity {
    private List<Fragment> pagerContent = new ArrayList<>();
    private TabLayout tabs;
    View frist;
    View tow;
    private CustomViewPager pager;
    private MarkPagerAdapterW pagerAdapter;
    TextView xingwe;
    int selde = 0;//编辑 完成
    int chouse = 0;
    TextView clean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydownload);
        initView();
    }

    public void initView() {
        getView(R.id.back).setOnClickListener(this);
        getView(R.id.bainj).setOnClickListener(this);
        clean = getView(R.id.clean);
        clean.setOnClickListener(this);
        xingwe = getView(R.id.xingwe);
        tabs = getView(R.id.tabs);
        pager = getView(R.id.pager);
        pagerContent.add(new MagazineW(-1));
        pagerContent.add(new CoursewareW(-1));
        frist = getView(R.id.frist);
        tow = getView(R.id.tow);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        //1.MODE_SCROLLABLE模式
//        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

//        //2.MODE_FIXED模式
//        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.addTab(tabs.newTab().setText("杂志"));
        tabs.addTab(tabs.newTab().setText("课件"));

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
                chouse = tab.getPosition();

                if (tab.getPosition() == 0) {
                    L.e("1");
                    frist.setVisibility(View.VISIBLE);
                    tow.setVisibility(View.INVISIBLE);
                } else {
                    L.e("2");
                    frist.setVisibility(View.INVISIBLE);
                    tow.setVisibility(View.VISIBLE);
                }
                MagazineW.maninfrag.adachangge();
                CoursewareW.maninfrag.adachangge();
                clean.setVisibility(View.INVISIBLE);
                xingwe.setText("编辑");
                selde = 0;
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
            case R.id.back:
                finish();

                break;

            case R.id.bainj:
                if (selde == 0) {
                    xingwe.setText("完成");
                    selde = 1;
                    clean.setVisibility(View.VISIBLE);
                    if (chouse == 0) {

                        MagazineW.maninfrag.adapternoyicefiy(1);
                    } else {
                        CoursewareW.maninfrag.adapternoyicefiy(1);
                    }
                } else {
                    xingwe.setText("编辑");
                    selde = 0;
                    clean.setVisibility(View.INVISIBLE);
                    if (chouse == 0) {

                        MagazineW.maninfrag.adapternoyicefiy(-1);
                    } else {
                        CoursewareW.maninfrag.adapternoyicefiy(-1);
                    }
                }

                break;

            case R.id.clean:
                showConflictDialog();

                break;
        }
    }

    //是否确定删除
    private void showConflictDialog() {
        String st = getResources().getString(R.string.prompt);
        final AlertDialog dlgShowBack = new AlertDialog.Builder(this).create();
        dlgShowBack.setTitle(st);
        dlgShowBack.setMessage("是否删除？");
        dlgShowBack.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (chouse == 0) {

                    MagazineW.maninfrag.changgede();
                } else {
                    CoursewareW.maninfrag.changgede();
                }
                clean.setVisibility(View.INVISIBLE);
                xingwe.setText("编辑");
                selde = 0;
                dialog.dismiss();

            }
        });

        dlgShowBack.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dlgShowBack.setCancelable(false);
        dlgShowBack.show();
        Button btnNegative = dlgShowBack.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
//                Button btnPositive =
//                        conflictBuilder.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
//                Button btnNegative =
//                        dlgShowBack.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
        btnNegative.setTextColor(getResources().getColor(R.color.lv));

    }
}
