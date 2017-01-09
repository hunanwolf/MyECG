package com.compassecg.test720.compassecg.LearningFragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.Home.seach.search_activity;
import com.compassecg.test720.compassecg.LearningFragment.Activity.CoursewaredetailsActivity;
import com.compassecg.test720.compassecg.LearningFragment.Activity.DoctorActivity;
import com.compassecg.test720.compassecg.LearningFragment.Activity.LearningMoveWActivity;
import com.compassecg.test720.compassecg.LearningFragment.adapterW.Gradview_Adapter;
import com.compassecg.test720.compassecg.LearningFragment.adapterW.Zazi_Gradview_Adapter;
import com.compassecg.test720.compassecg.LearningFragment.bean.Courseware;
import com.compassecg.test720.compassecg.LearningFragment.bean.Magazine;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.BottomScrollView;
import com.compassecg.test720.compassecg.View.HorizontalGridView2;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.BaseFragment;
import com.test720.auxiliary.Utils.T;

import java.util.ArrayList;
import java.util.List;

import static com.compassecg.test720.compassecg.Home.seach.search_activity.LKANGALGNA;

/**
 * Created by hp on 2016/12/20.
 */

public class Learningfragment extends BaseFragment {
    GridView gridview;
    GridView gv_my_mazy;//杂志
    HorizontalGridView2 gv_my_kejian;//课件
    List<String> list_gradview = new ArrayList<>();
    Gradview_Adapter adapter;
    Zazi_Gradview_Adapter adapter1;
    Zazi_Gradview_Adapter adapter2;
    List<String> list = new ArrayList<>();
    String url[] = {"医生列表", "专家讲座", "直播", "杂志阅读", "课件下载", "模拟考试"};
    HorizontalScrollView HorizontalGridView, HorizontalGridView1;
    private float mLastX;
    SwipeRefreshLayout swip;
    BottomScrollView scroll_view;
    private float mLastX1;
    LinearLayout liner, liner1;
    private LayoutInflater mInflater, mInflater1;
    RelativeLayout search;
    private final int STATT = 1;
    List<Magazine> list1 = new ArrayList<>();
    List<Courseware> list2 = new ArrayList<>();
    private int thispage = 1;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.learningfragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mInflater = LayoutInflater.from(getActivity());
        mInflater1 = LayoutInflater.from(getActivity());
        gridview = getView(R.id.gridview);

        search = getView(R.id.search);
        search.setOnClickListener(this);
        adapter = new Gradview_Adapter(getContext());
        gridview.setAdapter(adapter);

        setView();
        getDate();


        onlistennising();
    }


    public void getDate() {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        Post(Connector.Studyindex, params, STATT);

    }

    //杂志推荐
    private void initView() {
        for (int i = 0; i < list1.size(); i++) {
            View view = mInflater.inflate(R.layout.item_kejian_layout,
                    liner, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.img);
//            img.setImageResource(list.get(i));
            Glide.with(getActivity())
                    .load(Connector.lll + list1.get(i).getCover())
                    .placeholder(R.mipmap.zazhi)
                    .centerCrop()
                    .into(img);
            TextView txt = (TextView) view
                    .findViewById(R.id.title);
            txt.setText(list1.get(i).getTitle());
            liner.addView(view);
        }
    }

    //课件推荐
    private void initView2() {
        for (int i = 0; i < list2.size(); i++) {

            View view = mInflater1.inflate(R.layout.item_kejian_layout,
                    liner1, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.img);
            Glide.with(getActivity())
                    .load(Connector.lll + list2.get(i).getCover())
                    .placeholder(R.mipmap.zazhi)
                    .centerCrop()
                    .into(img);
//            img.setImageResource(list.get(i));
            TextView txt = (TextView) view
                    .findViewById(R.id.title);
            txt.setText(list2.get(i).getTitle());
            liner1.addView(view);
        }
    }

    //导航条的点击事件
    private void modelEvent() {
        for (int i = 0; i < liner.getChildCount(); i++) {
            View modelView = liner.getChildAt(i);
            modelView.setTag(i);
            modelView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    ShowToast("position" + position + "");
                    startActivity(new Intent(getContext(), CoursewaredetailsActivity.class).putExtra("type", 1).putExtra("id", list1.get(position).getId()));
                }
            });
        }

        for (int i = 0; i < liner1.getChildCount(); i++) {
            View modelView = liner1.getChildAt(i);
            modelView.setTag(i);
            modelView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    ShowToast("position" + position + "");
                    startActivity(new Intent(getContext(), CoursewaredetailsActivity.class).putExtra("type", 2).putExtra("id", list2.get(position).getId()));
                }
            });
        }
    }

    public void setView() {

        getView(R.id.lv_zazi).setOnClickListener(this);
        getView(R.id.move_kej).setOnClickListener(this);
        liner1 = getView(R.id.liner1);
        liner = getView(R.id.liner);


        HorizontalGridView1 = getView(R.id.HorizontalGridView1);
        HorizontalGridView = getView(R.id.HorizontalGridView);
        swip = getView(R.id.swip);
        scroll_view = getView(R.id.scroll_view);


        HorizontalGridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    // 记录点击到ViewPager时候，手指的X坐标
                    mLastX = event.getX();
                }
                if (action == MotionEvent.ACTION_MOVE) {
                    // 超过阈值
                    if (Math.abs(event.getX() - mLastX) > 60f) {
                        swip.setEnabled(false);
                        scroll_view.requestDisallowInterceptTouchEvent(true);
                    }
                }
                if (action == MotionEvent.ACTION_UP) {
                    // 用户抬起手指，恢复父布局状态
                    scroll_view.requestDisallowInterceptTouchEvent(false);
                    swip.setEnabled(true);
                }
                return false;
            }
        });
        HorizontalGridView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    // 记录点击到ViewPager时候，手指的X坐标
                    mLastX1 = event.getX();
                }
                if (action == MotionEvent.ACTION_MOVE) {
                    // 超过阈值
                    if (Math.abs(event.getX() - mLastX1) > 60f) {
                        swip.setEnabled(false);
                        scroll_view.requestDisallowInterceptTouchEvent(true);
                    }
                }
                if (action == MotionEvent.ACTION_UP) {
                    // 用户抬起手指，恢复父布局状态
                    scroll_view.requestDisallowInterceptTouchEvent(false);
                    swip.setEnabled(true);
                }
                return false;
            }
        });

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swip.setRefreshing(false);
                thispage = 1;
                getDate();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)  //计算gradvieew高度
    private void setGridViewHeight(GridView gridView) {
        ListAdapter adapter = gridView.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeigt = 0;
        double itemCount = adapter.getCount();
        double horizentalNumger = gridView.getNumColumns();
        int verticalNumber = (int) Math.ceil(itemCount / horizentalNumger);
        View item = adapter.getView(0, null, gridView);
        item.measure(0, 0);
        int itemHeight = item.getMeasuredHeight();
        int space = gridView.getVerticalSpacing();
        totalHeigt = verticalNumber * itemHeight + (verticalNumber - 1) * space;
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeigt;
        gridView.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.lv_zazi://杂志

                startActivity(new Intent(getActivity(), LearningMoveWActivity.class).putExtra("type", 1));
                break;

            case R.id.move_kej://课件

                startActivity(new Intent(getActivity(), LearningMoveWActivity.class).putExtra("type", 2));
                break;

            case R.id.search://搜索
                startActivity(new Intent(getActivity(), search_activity.class).putExtra("type", LKANGALGNA));
                break;

        }
    }

    public void onlistennising() {
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent intent = new Intent(getActivity(), DoctorActivity.class);
                        intent.putExtra("type", "医生");
                        startActivity(intent);
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), LearningMoveWActivity.class).putExtra("type", 1));
                        break;
                    case 4:

                        startActivity(new Intent(getActivity(), LearningMoveWActivity.class).putExtra("type", 2));
                        break;
                    case 5:

                        break;
                }

                T.showShort(getContext(), "" + url[position]);
            }
        });

    }


    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);
        switch (what) {
            case STATT:
                if (jsonObject.getIntValue("code") == 1) {

                    if (thispage == 1) {
                        list2.clear();
                        list1.clear();
                    }
                    JSONObject object = jsonObject.getJSONObject("list");
                    JSONArray jsonArray2 = object.getJSONArray("magazine");
                    List<Magazine> problj2 = JSONObject.parseArray(jsonArray2.toString(), Magazine.class);
                    list1.addAll(problj2);

                    JSONArray jsonArray = object.getJSONArray("courseware");
                    List<Courseware> problj = JSONObject.parseArray(jsonArray.toString(), Courseware.class);
                    list2.addAll(problj);

                    initView();
                    initView2();
                    modelEvent();
                }

                break;
        }
    }
}
