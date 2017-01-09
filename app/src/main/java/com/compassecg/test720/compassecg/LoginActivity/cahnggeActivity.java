package com.compassecg.test720.compassecg.LoginActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.L;
import com.test720.auxiliary.Utils.RegularUtil;

public class cahnggeActivity extends BarBaseActivity {

    public static String TAG = "com.compassecg.test720.compassecg.LoginActivity.BinDingActivity";
    EditText phone;
    EditText pass;

    ImageView clear;
    TextView clear1;
    Button ok;
    private static final int SATAT = 1; //验证码
    private static final int SATATl = 2;//注册
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changgeactivity);
        setTitleString("更改绑定手机");
        phone = getView(R.id.phone);
        pass = getView(R.id.pass);
        clear = getView(R.id.clear);
        clear1 = getView(R.id.clear1);
        clear.setOnClickListener(this);
        clear1.setOnClickListener(this);
        getView(R.id.ok).setOnClickListener(this);
        // 如果用户名改变，清空密码
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                L.e("phone", phone.getText().length() + "");
                if (phone.getText().length() == 0) {
                    clear.setVisibility(View.GONE);
                } else {
                    clear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.clear:
                phone.setText("");
                clear.setVisibility(View.GONE);
                break;

            case R.id.clear1:
                String phonel = phone.getText().toString();
                L.e("phonel", phonel);
//                if (!RegularUtil.isPhone(phonel)) {
//                    phone.setError("请正确输入手机号");
//                    return;
//                }
                gainCode();
                clear1.setText("已发送");
                break;

            case R.id.ok:
                String phone2 = phone.getText().toString();
                L.e("phone2", phone2);
                if (!RegularUtil.isPhone(phone2)) {
                    phone.setError("请正确输入手机号");
                    return;
                }
                if (pass.getText().length() == 0) {
                    pass.setError("请输入验证码");
                    return;
                }

                zhuce();
                break;
        }
    }

    private void fetchCode() {

        clear1.setTextColor(getResources().getColor(R.color.gray_normal));
        clear1.setText("重新获取(60S)");
        clear1.setClickable(false);
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                clear1.setText("重新获取(" + (int) (millisUntilFinished / 1000) + ")");
            }

            @Override
            public void onFinish() {
                clear1.setTextColor(getResources().getColor(R.color.lv));
                clear1.setText("获取验证码");
                clear1.setClickable(true);
            }
        }.start();
    }

    public void gainCode() {

        RequestParams params = new RequestParams();
        params.put("type", 4);
        params.put("tel",  getIntent().getExtras().getString("tel"));
        Postl(Connector.gainCode, params, SATAT);
    }

    public void zhuce() {

        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("old_tel", getIntent().getExtras().getString("tel"));
        params.put("new_tel", phone.getText().toString());
        params.put("rand", pass.getText().toString());
        Post(Connector.editPhone, params, SATATl);
    }

    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);

        try {
            switch (what) {
                case SATAT:
                    if (jsonObject.getIntValue("code") == 1) {
                        ShowToast("发送成功");
                        fetchCode();
                        return;
                    }
                    if (jsonObject.getIntValue("code") == 0) {
                        ShowToast("发送失败");
                        return;
                    }
                    if (jsonObject.getIntValue("code") == 2) {
                        ShowToast("该手机已注册");
                        return;
                    }
                    if (jsonObject.getIntValue("code") == 3) {

                        ShowToast("该手机未注册");
                        return;
                    }

                    break;

                case SATATl:

                    if (jsonObject.getIntValue("code") == 1) {
                        ShowToast("绑定成功");
                        RegisterActivity.test_a.finish();
                        finish();
                        return;
                    }
                    if (jsonObject.getIntValue("code") == 0) {
                        ShowToast("绑定失败");
                        return;
                    }
                    if (jsonObject.getIntValue("code") == 2) {
                        ShowToast("验证码不正确");
                        return;
                    }
                    if (jsonObject.getIntValue("code") == 3) {
                        ShowToast("该手机已注册");
                        return;
                    }

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            L.e(TAG, "数据炸了!");
        }
    }
}
