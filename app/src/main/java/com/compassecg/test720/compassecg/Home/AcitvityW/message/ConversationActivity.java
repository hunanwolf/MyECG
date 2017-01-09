package com.compassecg.test720.compassecg.Home.AcitvityW.message;

import android.content.Intent;
import android.os.Bundle;

import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.tooclass.avtivity.EaseChatFragment;
import com.compassecg.test720.compassecg.tooclass.fragment.ConversationListFragment;
import com.compassecg.test720.compassecg.tooclass.fragment.EaseConversationListFragment;
import com.test720.auxiliary.Utils.NoBarBaseActivity;


/**
 * 聊天页面，需要fragment的使用{@link #EaseChatFragment}
 */
public class ConversationActivity extends NoBarBaseActivity {
    public static ConversationActivity activityInstance;
    private EaseConversationListFragment conversationListFragment;
    String toChatUsername;
    public static String preobleid;
    public static String preobletype;
    public static String herdaea;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.em_activity_chat);
        activityInstance = this;
        //聊天人或群id

        //可以直接new EaseChatFratFragment使用
        conversationListFragment = new ConversationListFragment();
//        //传入参数
//        conversationListFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, conversationListFragment).commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }




    public String getToChatUsername() {
        return toChatUsername;
    }
}
