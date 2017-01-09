package com.compassecg.test720.compassecg.GroupConsultation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.compassecg.test720.compassecg.GroupConsultation.adapter.GroupKindsAnswerAdapterH;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.MyListView;
import com.compassecg.test720.compassecg.View.NetworkImageAdapter;
import com.compassecg.test720.compassecg.View.NineGridView;
import com.compassecg.test720.compassecg.View.RoundImageView;
import com.test720.auxiliary.Utils.NoBarBaseActivity;
import com.test720.auxiliary.Utils.T;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDetailsActivityH extends NoBarBaseActivity {

    private NineGridView mNineGridView;
    private MyListView listView;
    List<String> list=new ArrayList<String>();
    private TextView tv_new;
    private TextView tv_hot;
    private RelativeLayout rl_collection;
    private RelativeLayout rl_add;
    private ImageView iv_back;
    private ImageView iv_more;
    private TextView tv_delete;
    private FrameLayout fl_root;
    private LinearLayout ll1;
    private RelativeLayout rl1;
    private RelativeLayout rl_top;
    private RoundImageView iv_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_details_activity_h);
        initView();
        setAdapter();
        setListenner();
    }

    private void setListenner() {
        tv_new.setOnClickListener(this);
        tv_hot.setOnClickListener(this);
        rl_collection.setOnClickListener(this);
        rl_add.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        iv_more.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        iv_pic.setOnClickListener(this);
        ll1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(tv_delete.getVisibility()==View.VISIBLE){
                    tv_delete.setVisibility(View.GONE);
                }
                return false;
            }
        });
        rl_top.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(tv_delete.getVisibility()==View.VISIBLE){
                    tv_delete.setVisibility(View.GONE);
                }
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ConsultationDetailsActivityH.this,AnswerDetailsActivityH.class);
                startActivity(intent);
            }
        });
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(tv_delete.getVisibility()==View.VISIBLE){
                    tv_delete.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    private void setAdapter() {
        list.clear();
        list.add("http://i.dimg.cc/8f/3c/9f/39/8e/48/0b/b4/ff/0d/a8/8a/62/22/f3/6a.jpg");
        list.add("http://i.dimg.cc/8f/3c/9f/39/8e/48/0b/b4/ff/0d/a8/8a/62/22/f3/6a.jpg");
        list.add("http://i.dimg.cc/8f/3c/9f/39/8e/48/0b/b4/ff/0d/a8/8a/62/22/f3/6a.jpg");
        list.add("http://i.dimg.cc/8f/3c/9f/39/8e/48/0b/b4/ff/0d/a8/8a/62/22/f3/6a.jpg");
        list.add("http://i.dimg.cc/8f/3c/9f/39/8e/48/0b/b4/ff/0d/a8/8a/62/22/f3/6a.jpg");
        NetworkImageAdapter adapter = new NetworkImageAdapter(this, list);
        mNineGridView.setAdapter(adapter);
        mNineGridView.setOnImageClickListener(new Nicgriadview());

        GroupKindsAnswerAdapterH adapter_answer=new GroupKindsAnswerAdapterH(this);
        listView.setAdapter(adapter_answer);
        listView.setFocusable(false);
    }

    private void initView() {
        mNineGridView=getView(R.id.mNineGridView);
        listView=getView(R.id.listView);
        tv_new=getView(R.id.tv_new);
        tv_hot=getView(R.id.tv_hot);
        rl_collection=getView(R.id.rl_collection);
        rl_add=getView(R.id.rl_add);
        iv_back=getView(R.id.iv_back);
        iv_more=getView(R.id.iv_more);
        tv_delete=getView(R.id.tv_delete);
        fl_root=getView(R.id.fl_root);
        ll1=getView(R.id.ll1);
        rl1=getView(R.id.rl1);
        rl_top=getView(R.id.rl_top);
        iv_pic=getView(R.id.iv_pic);
    }
    public class Nicgriadview implements NineGridView.OnImageClickListener {

        public Nicgriadview() {

        }

        @Override
        public void onImageCilcked(int position, View view) {
            /*current_posion = position;
            setViewPager(list3.get(posn).getPicture());*/
            Intent intent=new Intent(ConsultationDetailsActivityH.this, BrowsePicActivityH.class);
            intent.putExtra("path", (Serializable) list);
            intent.putExtra("position",String.valueOf(position));
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_new:
                tv_new.setTextColor(getResources().getColor(R.color.lvse));
                tv_hot.setTextColor(getResources().getColor(R.color.text_light_gray));
                break;
            case R.id.tv_hot:
                tv_hot.setTextColor(getResources().getColor(R.color.lvse));
                tv_new.setTextColor(getResources().getColor(R.color.text_light_gray));
                break;
            case R.id.rl_add:
                Intent intent=new Intent(ConsultationDetailsActivityH.this,AddAnswerActivityH.class);
                startActivity(intent);
                if(tv_delete.getVisibility()==View.VISIBLE){
                    tv_delete.setVisibility(View.GONE);
                }
                break;
            case R.id.rl_collection:
                if(tv_delete.getVisibility()==View.VISIBLE){
                    tv_delete.setVisibility(View.GONE);
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_delete:
                T.showShort(ConsultationDetailsActivityH.this,"点击了删除");
                Intent intent1=new Intent(ConsultationDetailsActivityH.this,ReportActivityH.class);
                startActivity(intent1);
                tv_delete.setVisibility(View.GONE);
                break;
            case R.id.iv_more:
                if(tv_delete.getVisibility()==View.GONE){
                    tv_delete.setVisibility(View.VISIBLE);
                }else {
                    tv_delete.setVisibility(View.GONE);
                }

                break;
            case R.id.iv_pic:
                startActivity(new Intent(ConsultationDetailsActivityH.this, markActivityW.class).putExtra("type", 666));
                break;
        }
    }

}
