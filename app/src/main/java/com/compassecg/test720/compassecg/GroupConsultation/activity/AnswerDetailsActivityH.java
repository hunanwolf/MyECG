package com.compassecg.test720.compassecg.GroupConsultation.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.CommunityForum.bean.Comment;
import com.compassecg.test720.compassecg.GroupConsultation.adapter.GroupKindsAnswerFinalAdapterH;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.KeyboardListenRelativeLayout;
import com.compassecg.test720.compassecg.View.MyListView;
import com.compassecg.test720.compassecg.View.NetworkImageAdapter;
import com.compassecg.test720.compassecg.View.NineGridView;
import com.compassecg.test720.compassecg.View.RoundImageView;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.Logger;
import com.test720.auxiliary.Utils.NoBarBaseActivity;
import com.test720.auxiliary.Utils.T;

import java.util.ArrayList;
import java.util.List;

public class AnswerDetailsActivityH extends NoBarBaseActivity {

    private NineGridView mNineGridView;
    private MyListView listView;
    List<String> list = new ArrayList<String>();
    private ImageView iv_back;
    private RelativeLayout rl_left;
    private RelativeLayout rl_right;
    private KeyboardListenRelativeLayout root;
    private EditText et_content;
    private RelativeLayout rl_message;
    private LinearLayout ll_bottom;
    private ImageView iv_more;
    private RelativeLayout rl1;
    private TextView tv_delete;
    private RelativeLayout rl_top;
    private LinearLayout ll2;
    private RoundImageView iv_pic;
    int thispage = 1;//分页
    private final int SATAT = 1;
    TextView name;//名字
    TextView worlk;//工作
    TextView content;//内容
    TextView pulnum;//评论数
    TextView zan;//点赞数
    NetworkImageAdapter adapter;
    String uid = "";
    TextView iv_send;
    private final int STATT = 2;
    List<Comment> listll = new ArrayList<>();
    GroupKindsAnswerFinalAdapterH adapter_answer;
    private final int STARTl = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_details_activity_h);
        initView();
        setAdapter();
        getDate();

        setListenner();
    }


    public void getDate() {
        RequestParams params = new RequestParams();
        params.put("id", getIntent().getExtras().getString("huidaid"));
        params.put("uid", APP.uuid);
        params.put("p", thispage);
        Get(Connector.answerDetails, params, SATAT);
    }

    public void getDatel() {
        RequestParams params = new RequestParams();
        params.put("id", getIntent().getExtras().getString("huidaid"));
        params.put("uid", APP.uuid);
        params.put("p", thispage);
        Getl(Connector.answerDetails, params, SATAT);
    }

    private void setListenner() {
        iv_back.setOnClickListener(this);
        rl_left.setOnClickListener(this);
        rl_right.setOnClickListener(this);
        iv_more.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        iv_pic.setOnClickListener(this);
        rl_top.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tv_delete.getVisibility() == View.VISIBLE) {
                    tv_delete.setVisibility(View.GONE);
                }
                return false;
            }
        });
        ll2.setOnTouchListener(new View.OnTouchListener() {
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
        ll_bottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tv_delete.getVisibility() == View.VISIBLE) {
                    tv_delete.setVisibility(View.GONE);
                }
                return false;
            }
        });

        root.setOnKeyboardStateChangedListener(new KeyboardListenRelativeLayout.IOnKeyboardStateChangedListener() {
            @Override
            public void onKeyboardStateChanged(int state) {
                switch (state) {
                    case KeyboardListenRelativeLayout.KEYBOARD_STATE_HIDE://软键盘隐藏
                        Log.i("WOLF", "软键盘隐藏");
                        Logger.d("软键盘隐藏");
                        controlKeyboardLayout(root);

                        rl_message.setVisibility(View.GONE);
                        ll_bottom.setVisibility(View.VISIBLE);
                        break;
                    case KeyboardListenRelativeLayout.KEYBOARD_STATE_SHOW://软键盘显示
                        Log.i("WOLF", "软键盘显示");
                        Logger.d("软键盘显示");

                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void setAdapter() {

        adapter = new NetworkImageAdapter(this, list);

        mNineGridView.setOnImageClickListener(new Nicgriadview());

        /*GroupKindsAnswerAdapterH adapter_answer=new GroupKindsAnswerAdapterH(this);
        listView.setAdapter(adapter_answer);
        listView.setFocusable(false);*/

        adapter_answer = new GroupKindsAnswerFinalAdapterH(mContext, listll);
        listView.setAdapter(adapter_answer);
        listView.setFocusable(false);
    }


    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);

        switch (what) {
            case SATAT:
                if (jsonObject.getIntValue("code") == 1) {

                    JSONObject object = jsonObject.getJSONObject("list");
                    uid = object.getString("uid");
                  /*  iv_pic
                     name = getView(R.id.name);
        worlk = getView(R.id.worlk);
        content = getView(R.id.content);
            pulnum=getView(R.id.pulnum);
        zan=getView(R.id.zan);
        //头像*/
                    Glide.with(mContext).load(Connector.lll + object.getString("pic")).placeholder(R.drawable.ic_placeholder).into(iv_pic);
                    name.setText(object.getString("nickname"));
                    worlk.setText(object.getString("hospital") + "·" + object.getString("desk") + "·" + object.getString("job"));
                    content.setText(object.getString("content"));
                    zan.setText(object.getString("zan_num"));
                    pulnum.setText(object.getString("pl_num"));
                    JSONArray answer = object.getJSONArray("img");
                    list.clear();
                    list.addAll(JSONObject.parseArray(answer.toJSONString(), String.class));
                    mNineGridView.setAdapter(adapter);
                    JSONArray comment = object.getJSONArray("comment");
                    List<Comment> newerll = JSONObject.parseArray(comment.toJSONString(), Comment.class);
                    listll.addAll(newerll);

                }

                break;

            case STATT:
                if (jsonObject.getIntValue("code") == 1) {
                    ShowToast("发送成功！");
                    getDatel();

                } else {
                    ShowToast("发送失败！");
                }

                break;

            case STARTl:


                break;
        }
    }

    private void initView() {
        mNineGridView = getView(R.id.mNineGridView);
        listView = getView(R.id.listView);
        iv_back = getView(R.id.iv_back);
        rl_left = getView(R.id.rl_left);
        rl_right = getView(R.id.rl_right);
        root = getView(R.id.root);
        et_content = getView(R.id.et_content);
        rl_message = getView(R.id.rl_message);
        ll_bottom = getView(R.id.ll_bottom);
        iv_more = getView(R.id.iv_more);
        rl1 = getView(R.id.rl1);
        tv_delete = getView(R.id.tv_delete);
        rl_top = getView(R.id.rl_top);
        ll2 = getView(R.id.ll2);
        iv_pic = getView(R.id.iv_pic);
        name = getView(R.id.name);
        worlk = getView(R.id.worlk);
        content = getView(R.id.content);
        pulnum = getView(R.id.pulnum);
        zan = getView(R.id.zan);
        iv_send = getView(R.id.iv_send);
        iv_send.setOnClickListener(this);
    }

    public class Nicgriadview implements NineGridView.OnImageClickListener {

        public Nicgriadview() {

        }

        @Override
        public void onImageCilcked(int position, View view) {
            /*current_posion = position;
            setViewPager(list3.get(posn).getPicture());*/
            Intent intent = new Intent(AnswerDetailsActivityH.this, BrowsePicActivityH.class);
            intent.putStringArrayListExtra("path", (ArrayList<String>) list);
            intent.putExtra("position", String.valueOf(position));
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_left:
                RequestParams params = new RequestParams();
                params.put("uid", APP.uuid);
                params.put("id", getIntent().getExtras().getString("huidaid"));
                Post(Connector.zan, params, STARTl);
                break;
            case R.id.rl_right:
                popInput();
                rl_message.setVisibility(View.VISIBLE);
                ll_bottom.setVisibility(View.GONE);

                break;
            case R.id.iv_more:
                if (tv_delete.getVisibility() == View.GONE) {
                    tv_delete.setVisibility(View.VISIBLE);
                } else {
                    tv_delete.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_delete:
                T.showShort(AnswerDetailsActivityH.this, "点击了删除");
                Intent intent1 = new Intent(AnswerDetailsActivityH.this, ReportActivityH.class);
                startActivity(intent1);
                tv_delete.setVisibility(View.GONE);
                break;
            case R.id.iv_pic:
                if (!uid.equals("")) {
                    startActivity(new Intent(AnswerDetailsActivityH.this, markActivityW.class).putExtra("type", uid));
                }
                break;

            case R.id.iv_send:

                if (et_content.getText().toString().equals("")) {
                    ShowToast("请填写您的评论！");
                    return;
                }
                showConflictDialog();
                break;
        }
    }

    private void showConflictDialog() {
        final android.app.AlertDialog dlgShowBack = new android.app.AlertDialog.Builder(this).create();
        dlgShowBack.setTitle("提示");
        dlgShowBack.setMessage("是否确认发送？");
        dlgShowBack.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                RequestParams params = new RequestParams();
                params.put("uid", APP.uuid);
                params.put("aid", getIntent().getExtras().getString("huidaid"));
                params.put("content", et_content.getText().toString());
                Post(Connector.addComment, params, STATT);

            }
        });
//
        dlgShowBack.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dlgShowBack.setCancelable(false);
        dlgShowBack.show();
        Button btnNegative = dlgShowBack.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
        btnNegative.setTextColor(getResources().getColor(R.color.lv));
    }

    /**
     * 最外层布局，需要调整的布局
     * 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     * /
     **/
    private void controlKeyboardLayout(final View root) {
        // 注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        //获取当前界面可视部分
                        getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                        //获取屏幕的高度
                        int screenHeight = getWindow().getDecorView().getRootView().getHeight();
                        //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                        int heightDifference = screenHeight - r.bottom;
                        Log.e("Keyboard Size", "Size: " + heightDifference);

                    }
                });
    }

    /**
     * 获取焦点并弹出输入法
     */
    private void popInput() {
        et_content.setFocusable(true);
        et_content.setFocusableInTouchMode(true);
        et_content.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) et_content.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(et_content, 0);
    }
}
