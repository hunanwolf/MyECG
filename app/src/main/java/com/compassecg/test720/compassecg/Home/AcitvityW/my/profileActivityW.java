package com.compassecg.test720.compassecg.Home.AcitvityW.my;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.NoBarBaseActivity;

public class profileActivityW extends NoBarBaseActivity {

    ImageView back;
    TextView xingwe, title;
    private static final int SATATl = 1;

    private static final int SATAT = 2;
    EditText zhuanc;
    EditText jinli;

    String kl;
    String kllki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_w);
        xingwe = getView(R.id.xingwe);
        xingwe.setText("保存");
        getView(R.id.back).setOnClickListener(this);
        title = getView(R.id.title);
        jinli = getView(R.id.jinli);
        zhuanc = getView(R.id.zhuanc);

        xingwe.setOnClickListener(this);
        title.setText("个人简介");
        getDate();
    }


    public void getDate() {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        Post(Connector.Personinfo, params, SATATl);
    }

    public void getChanggrDate() {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("speciality", zhuanc.getText().toString());
        params.put("experience", jinli.getText().toString());
        Post(Connector.editInfo, params, SATAT);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.back:

                finish();
                break;

            case R.id.xingwe:
//                ShowToast("保存");

                showConflictDialog();

                break;
        }

    }

    private void showConflictDialog() {
        final AlertDialog dlgShowBack = new AlertDialog.Builder(this).create();
        dlgShowBack.setTitle("提示！");
        dlgShowBack.setMessage("是否确认保存？");
        dlgShowBack.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (kl.equals(zhuanc.getText().toString()) && kllki.equals(jinli.getText().toString())) {
                    ShowToast("请修改一下，再保存");
                } else {
                    getChanggrDate();
                }

                dialog.dismiss();
            }
        });
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

    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);
        switch (what) {
            case SATATl:

                if (jsonObject.getIntValue("code") == 1) {

                    JSONObject objext = jsonObject.getJSONObject("list");
                    kl = objext.getString("speciality");
                    kllki = objext.getString("experience");
                    zhuanc.setText(objext.getString("speciality"));
                    jinli.setText(objext.getString("experience"));
                }

                break;

            case SATAT:

                if (jsonObject.getIntValue("code") == 1) {

                    ShowToast("保存成功");
                    finish();
                } else {
                    ShowToast("保存失败");
                }
                break;
        }
    }
}
