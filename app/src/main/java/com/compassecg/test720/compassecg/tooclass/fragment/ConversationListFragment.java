package com.compassecg.test720.compassecg.tooclass.fragment;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.compassecg.test720.compassecg.Constant;
import com.compassecg.test720.compassecg.MainActivity;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.tooclass.InviteMessgeDao;
import com.compassecg.test720.compassecg.tooclass.avtivity.ChatActivity;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMConversation.EMConversationType;
import com.easemob.exceptions.EaseMobException;
import com.easemob.util.NetUtils;

import java.util.List;


public class ConversationListFragment extends EaseConversationListFragment {

    private TextView errorText;
    private String cradid;
    private String type;
    List<String> list3;

    @Override

    protected void initView() {
        super.initView();
        View errorView = (LinearLayout) View.inflate(getActivity(), R.layout.em_chat_neterror_item, null);
        errorItemContainer.addView(errorView);
        errorText = (TextView) errorView.findViewById(R.id.tv_connect_errormsg);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        // 注册上下文菜单
        registerForContextMenu(conversationListView);
        conversationListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = conversationListView.getItem(position);
                String username = conversation.getUserName();

                try {
                    cradid = conversationList.get(position).getLastMessage().getStringAttribute("id");
                    type = conversationList.get(position).getLastMessage().getStringAttribute("type");
                } catch (EaseMobException e) {
                    e.printStackTrace();
                }
                if (username.equals(EMChatManager.getInstance().getCurrentUser()))
                    Toast.makeText(getActivity(), R.string.Cant_chat_with_yourself, Toast.LENGTH_SHORT).show();
                else {
                    // 进入聊天页面
                    Intent intent = new Intent(getActivity(), ChatActivity.class);
                    if (conversation.isGroup()) {
                        if (conversation.getType() == EMConversationType.ChatRoom) {
                            // it's group chat
                            intent.putExtra(Constant.EXTRA_CHAT_TYPE, Constant.CHATTYPE_CHATROOM);
                        } else {
                            intent.putExtra(Constant.EXTRA_CHAT_TYPE, Constant.CHATTYPE_GROUP);
                        }

                    }

                    intent.putExtra(Constant.EXTRA_USER_ID, username);
                    intent.putExtra(Constant.PROLMELE_ID, cradid);
                    intent.putExtra(Constant.PROLMELE_TYPE, type);
                    intent.putExtra("header", list3.get(position));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void rturnlist(List<String> list3) {
        super.rturnlist(list3);
        this.list3=list3;
    }

    @Override
    protected void onConnectionDisconnected() {
        super.onConnectionDisconnected();
        if (NetUtils.hasNetwork(getActivity())) {
            errorText.setText(R.string.can_not_connect_chat_server_connection);
        } else {
            errorText.setText(R.string.the_current_network);
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.em_delete_message, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        boolean handled = false;
        boolean deleteMessage = false;
        /*if (item.getItemId() == R.id.delete_message) {
            deleteMessage = true;
            handled = true;
        } else*/
        if (item.getItemId() == R.id.delete_conversation) {
            deleteMessage = true;
            EMConversation tobeDeleteCons = conversationListView.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
            // 删除此会话
            EMChatManager.getInstance().deleteConversation(tobeDeleteCons.getUserName(), tobeDeleteCons.isGroup(), deleteMessage);
            InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(getActivity());
            inviteMessgeDao.deleteMessage(tobeDeleteCons.getUserName());
            refresh();

//            // 更新消息未读数
//            ((MainActivity) getActivity()).message();
        }
        return true;
    }

}
