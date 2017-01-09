package com.compassecg.test720.compassecg.Home;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.adapter.CBPageAdapter;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.CommunityForum.activity.PostDetailsActivityH;
import com.compassecg.test720.compassecg.GroupConsultation.activity.ConsultationDetailsActivityH;
import com.compassecg.test720.compassecg.Home.AcitvityW.city_choose;
import com.compassecg.test720.compassecg.Home.AcitvityW.message.messageActivityW;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.infoactivityW;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW;
import com.compassecg.test720.compassecg.Home.adapter.Gradview_adapterW;
import com.compassecg.test720.compassecg.Home.adapter.NetworkImageHolderView2W;
import com.compassecg.test720.compassecg.Home.adapter.Post_list_adapterW;
import com.compassecg.test720.compassecg.Home.adapter.forum_list_adapterW;
import com.compassecg.test720.compassecg.Home.baen.Ad;
import com.compassecg.test720.compassecg.Home.baen.Answer;
import com.compassecg.test720.compassecg.Home.baen.RmdZj;
import com.compassecg.test720.compassecg.Home.baen.Sqlt;
import com.compassecg.test720.compassecg.Home.seach.search_activity;
import com.compassecg.test720.compassecg.LearningFragment.Activity.DoctorActivity;
import com.compassecg.test720.compassecg.LoginActivity.LoginActivity;
import com.compassecg.test720.compassecg.MainActivity;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.BottomScrollView;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.ActivityUtil;
import com.test720.auxiliary.Utils.BaseFragment;
import com.test720.auxiliary.Utils.T;
import com.test720.auxiliary.View.HorizontalGridView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.compassecg.test720.compassecg.Home.seach.search_activity.ALNGKALG;

/**
 * Created by hp on 2016/12/6.
 */

public class MainFragmentW extends BaseFragment implements forum_list_adapterW.Callback, Post_list_adapterW.Callback, Gradview_adapterW.Callback {
    List<String> list = new ArrayList<>();
    Gradview_adapterW adapter1;
    public static MainFragmentW maninfrag = null;
    private HorizontalGridView gv_my_game;
    ConvenientBanner convenientBanner;
    private List<String> list1 = new ArrayList<>();
    private CBPageAdapter adapter;
    PtrClassicFrameLayout ptrFramel;
    private PtrFrameLayout ptrFrame;
    private float mLastX;
    BottomScrollView scroll_view;
    HorizontalScrollView HorizontalGridView;
    ListView listview;
    ListView list1view;
    private String[] city = {"不限", "全部", "大专以下", "大专", "本科", "硕士"};
    Post_list_adapterW adapter2;//帖子适配器
    forum_list_adapterW adapter3;//论坛
    SwipeRefreshLayout swip;
    TextView cityTv;
    private boolean isfoool = true;
    RelativeLayout backgunag;
    ImageView search;
    public final int STAT = 1;//首页
    private final int STATTT = 2;//关注
    private int thispage = 1;

    List<Ad> adList = new ArrayList<>();
    List<RmdZj> rmdZjList = new ArrayList<>();
    List<Sqlt> sqltList = new ArrayList<>();
    List<Answer> listanswer = new ArrayList<>();

    private void address() {
        //todo shuaxin
        if (isfoool) {
            backgunag.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!"".equals(APP.baiduCity)) {
                        cityTv.setText(APP.baiduCity);
                        isfoool = false;
                    } else {
                        isfoool = true;
                        address();
                    }
                }
            }, 1000);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        maninfrag = this;

        APP.cityName = APP.baiduCity;
        gv_my_game = getView(R.id.gv_my_game);
        getView(R.id.cityLayout).setOnClickListener(this);
        backgunag = getView(R.id.backgunag);
        cityTv = getView(R.id.cityTv);
        listview = getView(R.id.list);
        list1view = getView(R.id.list1);
        swip = getView(R.id.swip);
        address();

        adapter1 = new Gradview_adapterW(getContext(), rmdZjList, this);
        gv_my_game.setAdapter(adapter1);

        scroll_view = getView(R.id.scroll_view);
        getView(R.id.message).setOnClickListener(this);
        getView(R.id.myhome).setOnClickListener(this);
        getView(R.id.zuanjia).setOnClickListener(this);
        getView(R.id.huizhng).setOnClickListener(this);
        getView(R.id.luntan).setOnClickListener(this);

        search = getView(R.id.search);
        search.setOnClickListener(this);
        initAdsView();
        getDate();
        HorizontalGridView = getView(R.id.HorizontalGridView);
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

        adapter2 = new Post_list_adapterW(getContext(), sqltList, getActivity(), this);
        adapter3 = new forum_list_adapterW(getContext(), listanswer, getActivity(), this);
        listview.setAdapter(adapter3);
        list1view.setAdapter(adapter2);
        listtenger();
    }


    public void getDate() {
        RequestParams params = new RequestParams();
        params.put("province", "四川");
        params.put("p", thispage);
        Get(Connector.getIndex, params, STAT);
    }

    public void listtenger() {
        gv_my_game.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowToast("跳详情，需要认证！");
                startActivity(new Intent(getActivity(), markActivityW.class).putExtra("type", rmdZjList.get(position).getId())
                        .putExtra("nicname", rmdZjList.get(position).getNickname()));
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowToast("会诊跳转，里面关注需要认证，未登录进去需要登录");
                startActivity(new Intent(getContext(), ConsultationDetailsActivityH.class));
            }
        });
        list1view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowToast("论坛跳转，里面关注需要认证，未登录进去需要登录");

                startActivity(new Intent(getContext(), PostDetailsActivityH.class).putExtra("id", sqltList.get(position).getId()));
            }
        });
        scroll_view.setOnScrollToBottomLintener(new BottomScrollView.OnScrollToBottomListener() {

            @Override
            public void onScrollBottomListener(boolean isBottom) {
                // TODO Auto-generated method stub
//                ShowToast("上拉加载");
//                if (thisPage < MaxPage & !progressBar.isShowing()) {
//                    thisPage++;
//
//                    fetchData();
//                }
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


    private void initAdsView() {
        convenientBanner = getView(R.id.convenientBanner);

        convenientBanner.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    convenientBanner.requestDisallowInterceptTouchEvent(false);
                } else {
                    convenientBanner.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView2W>() {
            @Override
            public NetworkImageHolderView2W createHolder() {
                return new NetworkImageHolderView2W();
            }

        }, adList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.dian2, R.drawable.dian1})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }

                })//监听翻页事件
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
//                        Intent intent = new Intent(getActivity(), web.class);
//
//                        intent.putExtra("url", list1.get(position).getId());
//
//                        startActivity(intent);


                    }
                });
        convenientBanner.getViewPager().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        swip.setEnabled(false);
                        scroll_view.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        scroll_view.requestDisallowInterceptTouchEvent(false);
                        swip.setEnabled(true);
                        break;
                }
                return false;
            }
        });
        //设置动画效果
//        String transforemerName = ZoomOutTranformer.class.getSimpleName();
//        try {
//            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
//            ABaseTransformer transforemer = (ABaseTransformer) cls.newInstance();
//            convenientBanner.getViewPager().setPageTransformer(true, transforemer);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        adapter = convenientBanner.getViewPager().getAdapter();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.message://消息
                if (!APP.uuid.equals("")) {
                    startActivity(new Intent(getActivity(), messageActivityW.class));
                } else {
                    showConflictDialog();
                }

                break;

            case R.id.myhome://个人中心
                if (!APP.uuid.equals("")) {
                    startActivity(new Intent(getActivity(), infoactivityW.class));
                } else {
                    showConflictDialog();
                }
                break;

            case R.id.cityLayout://城市
                startActivity(new Intent(getActivity(), city_choose.class));

                break;

            case R.id.zuanjia://推荐专家

                startActivity(new Intent(getActivity(), DoctorActivity.class));
                break;

            case R.id.huizhng://实时会诊
                MainActivity.test_a.switchPage(1);

                break;
            case R.id.luntan://论坛

                MainActivity.test_a.switchPage(3);
                break;

            case R.id.search:
                startActivity(new Intent(getActivity(), search_activity.class).putExtra("type", ALNGKALG));
                break;
        }
    }

    private void showConflictDialog() {

        final AlertDialog dlgShowBack = new AlertDialog.Builder(getActivity()).create();
        dlgShowBack.setTitle("登录提示！");
        dlgShowBack.setMessage("您还未登录，请先登录！");
        dlgShowBack.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ActivityUtil.finishAllActivity();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
//
        dlgShowBack.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
//        dlgShowBack.setCancelable(false);
        dlgShowBack.show();
        Button btnNegative = dlgShowBack.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
        btnNegative.setTextColor(getResources().getColor(R.color.lv));

    }

    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);
        switch (what) {
            case STAT:

                if (jsonObject.getIntValue("code") == 1) {
               /* sqltList  rmdZjList  adList*/
                    adList.clear();
                    rmdZjList.clear();
                    JSONObject object = jsonObject.getJSONObject("list");
                    JSONArray jsonArray = object.getJSONArray("ad");
                    List<Ad> problj = JSONObject.parseArray(jsonArray.toString(), Ad.class);
                    adList.addAll(problj);

                    JSONArray jsonArray1 = object.getJSONArray("rmd_zj");
                    List<RmdZj> problj1 = JSONObject.parseArray(jsonArray1.toString(), RmdZj.class);
                    rmdZjList.addAll(problj1);

                    JSONArray jsonArray2 = object.getJSONArray("sqlt");
                    List<Sqlt> problj2 = JSONObject.parseArray(jsonArray2.toString(), Sqlt.class);
                    sqltList.addAll(problj2);
                    JSONArray jsonArray3 = object.getJSONArray("sshz");
                    List<Answer> problj3 = JSONObject.parseArray(jsonArray2.toString(), Answer.class);
                    listanswer.addAll(problj3);
                    adapter.notifyDataSetChanged();
                    adapter1.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();
                }
                break;

            case STATTT:

                if (jsonObject.getIntValue("code") == 1) {
                    ShowToast("关注成功！");
                    RequestParams params = new RequestParams();
                    params.put("province", "四川");
                    params.put("p", thispage);
                    Getl(Connector.getIndex, params, STAT);
                } else {
                    ShowToast("关注失败！");
                }

                break;
        }
    }

    @Override
    public void Clean(View v) {

    }

    @Override
    public void Chouse(View v) {
        v.getTag();//position
        T.showShort(getContext(), "关注");

        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("pid", rmdZjList.get((Integer) v.getTag()).getId());
        Postl(Connector.addGuanzhu, params, STATTT);


    }
    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(3000);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }

    //城市选中
    public void setcity(String cityid) {

        cityTv.setText(cityid);
        RequestParams params = new RequestParams();
        params.put("province", cityid);
        params.put("p", 1);
        Get(Connector.getIndex, params, STAT);
    }
}
