package com.compassecg.test720.compassecg.Home.AcitvityW.my.Setfocus;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.compassecg.test720.compassecg.LoginActivity.LoginActivity;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.tooclass.domain.friend;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;
import com.compassecg.test720.compassecg.unitl.UuidUtil;
import com.easemob.chat.EMConversation;
import com.test720.auxiliary.Utils.DataCleanManager;

import java.util.ArrayList;
import java.util.List;

public class SetupActivity extends BarBaseActivity {
    private android.app.AlertDialog conflictBuilder;

    RelativeLayout back;
    List<EMConversation> listarray = new ArrayList<>();
    LinearLayout about;
    List<String> listb = new ArrayList<String>();
    List<String> lista = new ArrayList<String>();
    LinearLayout feedback;
    public static List<String> usernames = new ArrayList<>();//好友列表
    public static List<String> huihua = new ArrayList<>();//回话列表
    TextView logout;
    //退出dialog
    private AlertDialog logoutDialog;
    private AlertDialog logoutDialog1;//未登录，去登陆
    private Intent intent;
    public static List<EMConversation> listhuihua = new ArrayList<>();
    private int SATARl = 1;
    List<friend> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
TextView titit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        setTitleString("设置中心");
//        listhuihua.addAll(loadConversationsWithRecentChat());
//        getDatael();
        setview();


    }

    public void setview() {
        logout = getView(R.id.logout);
        logout.setOnClickListener(this);
        titit=getView(R.id.titit);
        try {
            titit.setText(DataCleanManager.getTotalCacheSize(mContext));
        } catch (Exception e) {
            e.printStackTrace();
        }
        getView(R.id.about).setOnClickListener(this);
        getView(R.id.feedback).setOnClickListener(this);
        getView(R.id.huacun).setOnClickListener(this);

    }

    public void getDatael() {



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);

                break;
            case R.id.feedback:
                intent = new Intent(this, FeedbackActivity.class);
                startActivity(intent);

                break;

            case R.id.logout:

                showConflictDialog();
                break;

            case R.id.huacun:
                final AlertDialog dlgShowBack = new AlertDialog.Builder(this).create();
                dlgShowBack.setTitle("提示");
                dlgShowBack.setMessage("是否删除出应用缓存？");
//                Button btnPositive = ((Button)findViewById(android.app.AlertDialog.BUTTON_POSITIVE));
//                Button btnNegative = conflictBuilder(android.app.AlertDialog.BUTTON_NEGATIVE));


//                btnPositive.setTextColor(getResources().getColor(R.color.colorPrimary));

                dlgShowBack.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        final ProgressDialog pd = new ProgressDialog(mContext);
                        String st = getResources().getString(R.string.Are_logged_out);
                        pd.setMessage(st);
                        pd.setCanceledOnTouchOutside(false);
                        pd.show();
                        DataCleanManager.cleanInternalCache(mContext);

                        pd.dismiss();


                        try {
                            titit.setText(DataCleanManager.getTotalCacheSize(mContext));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
//                Button btnPositive =
//                        conflictBuilder.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
//                Button btnNegative =
//                        dlgShowBack.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
                btnNegative.setTextColor(getResources().getColor(R.color.lv));

                break;
        }
    }

    private void showConflictDialog() {
        String st = getResources().getString(R.string.logout_);

        final AlertDialog dlgShowBack = new AlertDialog.Builder(this).create();
        dlgShowBack.setTitle(st);
        dlgShowBack.setMessage("");
//                Button btnPositive = ((Button)findViewById(android.app.AlertDialog.BUTTON_POSITIVE));
//                Button btnNegative = conflictBuilder(android.app.AlertDialog.BUTTON_NEGATIVE));


//                btnPositive.setTextColor(getResources().getColor(R.color.colorPrimary));

        dlgShowBack.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                UuidUtil.logout(mContext);
                startActivity(new Intent(SetupActivity.this, LoginActivity.class));
                dialog.dismiss();
                finish();

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
//                Button btnPositive =
//                        conflictBuilder.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
//                Button btnNegative =
//                        dlgShowBack.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
        btnNegative.setTextColor(getResources().getColor(R.color.lv));

    }

    public void logout() {
        final ProgressDialog pd = new ProgressDialog(this);
        String st = getResources().getString(R.string.Are_logged_out);
        pd.setMessage(st);
        pd.setCanceledOnTouchOutside(false);
        pd.show();


        pd.dismiss();

    }
}
