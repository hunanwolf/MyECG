package com.compassecg.test720.compassecg.Home.AcitvityW.my;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.LoginActivity.LoginActivity;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.compassecg.test720.compassecg.unitl.UuidUtil;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.ActivityUtil;
import com.test720.auxiliary.Utils.L;
import com.test720.auxiliary.Utils.NoBarBaseActivity;
import com.test720.auxiliary.Utils.RegularUtil;
import com.test720.auxiliary.Utils.T;


public class PassChangeActivityW extends NoBarBaseActivity {


    RelativeLayout back;

    EditText pass;

    EditText passNew;

    EditText passNewCon;
    RelativeLayout baotun;
    TextView btntext;
    int SATAT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_change);
        initView();
    }


    public void initView() {

        pass = getView(R.id.pass);
        passNew = getView(R.id.pass_new);
        passNewCon = getView(R.id.pass_new_con);
        baotun = getView(R.id.baotun);
        btntext = getView(R.id.btntext);
        baotun.setOnClickListener(this);
        back = getView(R.id.back);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.baotun:
                getDatae();
//                ShowToast("保存");
                break;
        }
    }

    //修改密码
    public void getDatae() {


        String skil = pass.getText().toString();
        String ski_new = passNew.getText().toString();
        String skil_con = passNewCon.getText().toString();

        if (passNew.getText().length() < 6) {
            passNew.setError("请输入6位数密码");
            ShowToast("请输入6位数密码");
            return;
        }
        if (passNewCon.getText().length() < 6) {
            passNewCon.setError("请输入6位数密码");
            ShowToast("请输入6位数密码");
            return;
        }

        if (!passNew.getText().toString().equals(passNewCon.getText().toString())) {
            ShowToast("两次密码不一致！");
            return;
        }
        if (RegularUtil.isHaveChinese(passNew.getText().toString())) {
            Toast.makeText(this, "不能输入中文！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!skil.equals("") && !ski_new.equals("") && !skil_con.equals("")) {
            if (ski_new.equals(skil_con)) {

                showConflictDialog();
            } else {
                passNewCon.setError("请重新输入密码");
            }
        } else {
            T.showShort(getApplicationContext(), "请填写您的信息");
        }
    }

    private void showConflictDialog() {

        final AlertDialog dlgShowBack = new AlertDialog.Builder(this).create();
        dlgShowBack.setTitle("提示！");
        dlgShowBack.setMessage("是否确认保存？");
        dlgShowBack.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String skil = pass.getText().toString();
                String skil_con = passNewCon.getText().toString();
                RequestParams params = new RequestParams();
                params.put("uid", APP.uuid);
                params.put("old_pwd", skil);
                params.put("new_pwd", skil_con);
                Post(Connector.PersoneditPwd, params, SATAT);
                L.e("params", params.toString());
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
        int msg = jsonObject.getIntValue("code");
        if (msg == 1) {
            T.showShort(this, "修改成功");
            ActivityUtil.finishAllActivity();
            UuidUtil.logout(mContext);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else if (msg == 0) {
            T.showShort(this, "修改失败");
        } else
            T.showLong(this, "原密码错误！请重新设置");
    }
}
