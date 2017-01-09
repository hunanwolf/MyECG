package com.compassecg.test720.compassecg.LearningFragment.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.compassecg.test720.compassecg.LearningFragment.Learningfragment;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.tooclass.avtivity.EaseChatFragment;
import com.test720.auxiliary.Utils.NoBarBaseActivity;


/**
 * 聊天页面，需要fragment的使用{@link #EaseChatFragment}
 */
public class learniingActivity extends NoBarBaseActivity {
    public static learniingActivity activityInstance;
    private Learningfragment chatFragment;
    String toChatUsername;
    public static String preobleid;
    public static String preobletype;
    public static String herdaea;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.em_activity_chat);
        activityInstance = this;

        //可以直接new EaseChatFratFragment使用
        chatFragment = new Learningfragment();
//        //传入参数
//        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }




}
