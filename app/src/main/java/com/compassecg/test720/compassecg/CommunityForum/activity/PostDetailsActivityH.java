package com.compassecg.test720.compassecg.CommunityForum.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.CommunityForum.adapter.GroupKindsAnswerAdapter2H;
import com.compassecg.test720.compassecg.CommunityForum.bean.Answer;
import com.compassecg.test720.compassecg.CommunityForum.bean.Img;
import com.compassecg.test720.compassecg.GroupConsultation.activity.AddAnswerActivityH;
import com.compassecg.test720.compassecg.GroupConsultation.activity.AnswerDetailsActivityH;
import com.compassecg.test720.compassecg.GroupConsultation.activity.BrowsePicActivityH;
import com.compassecg.test720.compassecg.GroupConsultation.activity.ReportActivityH;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.BottomScrollView;
import com.compassecg.test720.compassecg.View.MyListView;
import com.compassecg.test720.compassecg.View.NetworkImageAdapter;
import com.compassecg.test720.compassecg.View.NineGridView;
import com.compassecg.test720.compassecg.View.RoundImageView;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.L;
import com.test720.auxiliary.Utils.NoBarBaseActivity;
import com.test720.auxiliary.Utils.T;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.finalteam.toolsfinal.DateUtils;

public class PostDetailsActivityH extends NoBarBaseActivity implements GroupKindsAnswerAdapter2H.Callback {

    private NineGridView mNineGridView;
    private MyListView listView;
    List<String> list = new ArrayList<String>();
    private TextView tv_new;
    private TextView tv_hot;
    private RelativeLayout rl_collection;
    private RelativeLayout rl_add;
    private ImageView iv_back;
    private ImageView iv_more;
    private TextView tv_delete;
    private RelativeLayout rl_top;
    private LinearLayout ll1;
    private RoundImageView iv1;
    private final int START = 1;
    private int thispage = 1;//当前页数
    private int MaxPage;//最大页数
    private TextView tv1;//姓名
    private TextView tv2;//标题
    private TextView tv5;//内容
    private TextView tv3;//时间
    TextView tv4;//回答数
    NetworkImageAdapter adapter;
    List<Answer> Answerlist = new ArrayList<>();
    private final int STARTl = 2;
    GroupKindsAnswerAdapter2H adapter_answer;
    String uid;//问题id
    BottomScrollView scroll_view;
    ImageView collction;
    private final int STATTL = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details_activity_h);
        initView();
        setAdapter();

        setListenner();
    }

    @Override
    protected void onResume() {
        super.onResume();
        thispage = 1;
        getDtae();
    }

    public void getDtae() {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("id", getIntent().getExtras().getString("id"));
        params.put("p", thispage);
        Post(Connector.problemDetails, params, START);
    }

    private void setListenner() {
        tv1 = getView(R.id.tv1);
        tv2 = getView(R.id.tv2);
        tv5 = getView(R.id.tv5);
        tv3 = getView(R.id.tv3);
        tv4 = getView(R.id.tv4);
        rl_collection.setOnClickListener(this);
        rl_add.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        iv_more.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        iv1.setOnClickListener(this);
        scroll_view = getView(R.id.scroll_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PostDetailsActivityH.this, AnswerDetailsActivityH.class);
                intent.putExtra("huidaid", Answerlist.get(position).getId());
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (view.getLastVisiblePosition() == view.getCount() - 1 && thispage < MaxPage & !progressBar.isShowing()) {
                    thispage++;
//                    Toast.makeText(getActivity(), "上拉加载更多！", Toast.LENGTH_SHORT).show();
                    getDtae();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

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

    }

    private void setAdapter() {

        adapter = new NetworkImageAdapter(this, list);

        mNineGridView.setOnImageClickListener(new Nicgriadview());
        adapter_answer = new GroupKindsAnswerAdapter2H(mContext, Answerlist, this);
        listView.setAdapter(adapter_answer);

    }

    private void initView() {
        mNineGridView = getView(R.id.mNineGridView);
        listView = getView(R.id.listView);
        rl_collection = getView(R.id.rl_collection);
        rl_add = getView(R.id.rl_add);
        iv_back = getView(R.id.iv_back);
        iv_more = getView(R.id.iv_more);
        tv_delete = getView(R.id.tv_delete);
        rl_top = getView(R.id.rl_top);
        ll1 = getView(R.id.ll1);
        iv1 = getView(R.id.iv1);
        collction = getView(R.id.collction);
        rl_top.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tv_delete.getVisibility() == View.VISIBLE) {
                    tv_delete.setVisibility(View.GONE);
                }
                return false;
            }
        });
        ll1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tv_delete.getVisibility() == View.VISIBLE) {
                    tv_delete.setVisibility(View.GONE);
                }
                return false;
            }
        });
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tv_delete.getVisibility() == View.VISIBLE) {
                    tv_delete.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    //点赞
    @Override
    public void Zan(View v) {
        L.e("position", (Integer) v.getTag() + "");
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("id", Answerlist.get((Integer) v.getTag()).getId());
        Post(Connector.zan, params, STARTl);
    }

    public class Nicgriadview implements NineGridView.OnImageClickListener {

        public Nicgriadview() {

        }

        @Override
        public void onImageCilcked(int position, View view) {
            /*current_posion = position;
            setViewPager(list3.get(posn).getPicture());*/
            Intent intent = new Intent(PostDetailsActivityH.this, BrowsePicActivityH.class);
            intent.putStringArrayListExtra("path", (ArrayList<String>) list);
            intent.putExtra("position", String.valueOf(position));
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_collection:
//                if (tv_delete.getVisibility() == View.VISIBLE) {
//                    tv_delete.setVisibility(View.GONE);
//                }

                RequestParams params = new RequestParams();
                params.put("uid", APP.uuid);
                params.put("id", getIntent().getExtras().getString("id"));
                params.put("type", 1);
                Post(Connector.collectProblem, params, STATTL);
                break;
            case R.id.rl_add:
                if (tv_delete.getVisibility() == View.VISIBLE) {
                    tv_delete.setVisibility(View.GONE);
                }
                Intent intent = new Intent(PostDetailsActivityH.this, AddAnswerActivityH.class);
                intent.putExtra("answerid", getIntent().getExtras().getString("id"));
                startActivity(intent);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_delete:
                T.showShort(PostDetailsActivityH.this, "点击了删除");
                Intent intent1 = new Intent(PostDetailsActivityH.this, ReportActivityH.class);
                startActivity(intent1);
                break;
            case R.id.iv_more:
                if (tv_delete.getVisibility() == View.GONE) {
                    tv_delete.setVisibility(View.VISIBLE);
                } else {
                    tv_delete.setVisibility(View.GONE);
                }

                break;
            case R.id.iv1:
                startActivity(new Intent(PostDetailsActivityH.this, markActivityW.class).putExtra("type", getIntent().getExtras().getString("id")));
                break;

        }
    }


    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);

        switch (what) {
            case START:

                if (jsonObject.getIntValue("code") == 1) {
                    JSONObject object = jsonObject.getJSONObject("list");

                    Glide.with(getApplicationContext())
                            .load(Connector.lll + object.getString("pic"))
                            .centerCrop()
                            .override(100, 100)
                            .into(iv1);
                    uid = object.getString("uid");
                    tv1.setText(object.getString("nickname"));
                    tv2.setText(object.getString("title"));
                    tv5.setText(object.getString("content"));
                    String time = DateUtils.getTimeInterval(getTime(Long.parseLong(object.getString("time") + "000")));
                    tv3.setText(time);
                    tv4.setText(object.getString("asw_num"));
                    list.clear();
                    JSONArray img = object.getJSONArray("img");
                    List<Img> newStr = JSONObject.parseArray(img.toJSONString(), Img.class);
                    for (int i = 0; i < newStr.size(); i++) {
                        list.add(newStr.get(i).getUrl());
                    }
                    mNineGridView.setAdapter(adapter);
                    Answerlist.clear();
                    JSONArray answer = object.getJSONArray("answer");
                    List<Answer> newStr1 = JSONObject.parseArray(answer.toJSONString(), Answer.class);
                    Answerlist.addAll(newStr1);
                    adapter_answer.notifyDataSetChanged();

                    if (object.getString("is_collect").equals("1")) {//是否已点赞
                        collction.setImageResource(R.mipmap.btn_collection_pre);
                    } else {

                        collction.setImageResource(R.mipmap.btn_collection);
                    }
                }
                break;

            case STARTl:

                if (jsonObject.getIntValue("code") == 1) {
                    ShowToast("点赞成功");
                    thispage = 1;
                    RequestParams params = new RequestParams();
                    params.put("uid", APP.uuid);
                    params.put("id", getIntent().getExtras().getString("id"));
                    params.put("p", thispage);
                    Postl(Connector.problemDetails, params, START);
                }


                break;


            case STATTL:
                if (jsonObject.getIntValue("code") == 1) {

                    ShowToast(jsonObject.getString("msg"));
                    if (jsonObject.getString("msg").equals("收藏成功")) {
                        collction.setImageResource(R.mipmap.btn_collection_pre);
                    } else if (jsonObject.getString("msg").equals("取消收藏")) {

                    } else {

                    }
                }

                break;
        }
    }

    /**
     * 将毫秒转为yyyy-MM-dd HH:mm:ss的date格式
     *
     * @param time
     * @return
     */
    private String getTime(Long time) {
        //long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date(time);
        String t1 = format.format(d1);
        Log.e("msg", t1);
        return t1;
    }
}
