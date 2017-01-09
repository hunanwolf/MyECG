package com.compassecg.test720.compassecg.Home.AcitvityW.message;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.compassecg.test720.compassecg.DemoHelper;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.tooclass.utils.EaseCommonUtils;
import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.test720.auxiliary.Utils.NoBarBaseActivity;

public class messageActivityW extends NoBarBaseActivity {
    SwipeRefreshLayout swip;
    ListView list;
    message_list_adapterW adapterW;
    RelativeLayout move, btn;
    TextView title;
    private boolean progressShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_w);
        move = getView(R.id.move);
        getView(R.id.btn).setOnClickListener(this);
        move.setOnClickListener(this);
        title = getView(R.id.title);
        title.setText("消息中心");
        swip = getView(R.id.swip);
        list = getView(R.id.list);
        adapterW = new message_list_adapterW(mContext);
        list.setAdapter(adapterW);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (DemoHelper.getInstance().isLoggedIn()) {
                            startActivity(new Intent(messageActivityW.this, ConversationActivity.class));
                            return;
                        } else {
                            login();
                        }
                        break;
                    case 1:
                        startActivity(new Intent(messageActivityW.this, mymessageActivityW.class));
                        break;
                    case 2:
                        startActivity(new Intent(messageActivityW.this, ApplicationAndNoticeActivityW.class));

                        break;

                    case 3:
                        startActivity(new Intent(messageActivityW.this, NoticeActivityW.class));
                        break;
                }
            }
        });
    }

    /**
     * 登录
     */
    public void login() {
        if (!EaseCommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }
//        currentUsername = phone.getText().toString().trim();
//        currentPassword = "123";
//
//        if (TextUtils.isEmpty(currentUsername)) {
//            Toast.makeText(this, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(currentPassword)) {
//            Toast.makeText(this, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
//            return;
//        }


        progressShow = true;
        final ProgressDialog pd = new ProgressDialog(messageActivityW.this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                progressShow = false;
            }
        });
        pd.setMessage(getString(R.string.Is_landing));
        pd.show();

        final long start = System.currentTimeMillis();
        // 调用sdk登陆方法登陆聊天服务器
        EMChatManager.getInstance().login("15828676432", "123", new EMCallBack() {

            @Override
            public void onSuccess() {
                if (!progressShow) {
                    return;
                }
                // 登陆成功，保存用户名
                DemoHelper.getInstance().setCurrentUserName("15828676432");
                // 注册群组和联系人监听
                DemoHelper.getInstance().registerGroupAndContactListener();

                // ** 第一次登录或者之前logout后再登录，加载所有本地群和回话
                // ** manually load all local groups and
//                EMGroupManager.getInstance().loadAllGroups();
                EMChatManager.getInstance().loadAllConversations();

                //异步获取当前用户的昵称和头像(从自己服务器获取，demo使用的一个第三方服务)
                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();

                if (!messageActivityW.this.isFinishing() && pd.isShowing()) {
                    pd.dismiss();
                }

                startActivity(new Intent(messageActivityW.this, ConversationActivity.class));
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        getDatae();


//                ActivityUtil.finishAllActivity();
//                finish();
//                // 进入主页面
//                Intent intent = new Intent(messageActivityW.this, MainActivity.class);
//                startActivity(intent);
////                    }
////                });
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(final int code, final String message) {
                if (!progressShow) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.move:
                finish();
                break;

            case R.id.btn:
                startActivity(new Intent(messageActivityW.this, disturbActivity.class));
                break;
        }
    }
}
