package com.compassecg.test720.compassecg.LoginActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.L;
import com.test720.auxiliary.Utils.RegularUtil;
import com.test720.auxiliary.Utils.T;

public class PassLostActivity extends BarBaseActivity {
    public static String TAG = "com.compassecg.test720.compassecg.LoginActivity.PassLostActivity";
    EditText phone;
    EditText pass;
    EditText password;
    EditText newpassword;

    ImageView clear;
    TextView clear1;
    ImageView clear2;
    ImageView clear3;
    private CountDownTimer timer;
    private static final int SATAT = 1;//验证码
    private static final int SATATl = 2;//重置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_lost);
        setTitleString("忘记密码");

        getView(R.id.ok).setOnClickListener(this);
        clear = getView(R.id.clear);
        clear.setOnClickListener(this);
        clear1 = getView(R.id.clear1);
        clear1.setOnClickListener(this);
        clear2 = getView(R.id.clear2);
        clear2.setOnClickListener(this);
        clear3 = getView(R.id.clear3);
        clear3.setOnClickListener(this);

        phone = getView(R.id.phone);
        pass = getView(R.id.pass);
        password = getView(R.id.password);
        newpassword = getView(R.id.newpassword);


        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
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


        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (password.getText().length() == 0) {
                    clear2.setVisibility(View.GONE);
                } else {
                    clear2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        newpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (newpassword.getText().length() == 0) {
                    clear3.setVisibility(View.GONE);
                } else {
                    clear3.setVisibility(View.VISIBLE);
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

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {

            case R.id.ok:
                String phonel = phone.getText().toString();
                L.e("phone.getText().toString()", phonel);
                if (!RegularUtil.isPhone(phonel)) {
                    phone.setError("请输入手机号");
                    return;
                }
                if (pass.getText().length() == 0) {
                    T.showShort(mContext, "请输入验证码！");
                    pass.setError("请输入验证码");
                    return;
                }

                if (password.getText().length() == 0) {

                    password.setError("密码不能为空！");
                    return;
                }

                if (newpassword.getText().length() == 0) {

                    newpassword.setError("请确认密码");
                    return;
                }
                if (!password.getText().toString().equals(newpassword.getText().toString())) {
                    newpassword.setError("请确认密码");
                    return;
                }
                editPwd();
                break;

            case R.id.clear:
                phone.setText("");
                clear.setVisibility(View.GONE);
                break;

            case R.id.clear1:

                String phone2 = phone.getText().toString();
                L.e("phone.getText().toString()", phone2);
                if (!RegularUtil.isPhone(phone2)) {
                    phone.setError("请输入手机号");
                    return;
                }
                clear1.setText("已发送");
                gainCode();
                break;

            case R.id.clear2:
                password.setText("");
                clear2.setVisibility(View.GONE);
                break;

            case R.id.clear3:
                newpassword.setText("");
                clear3.setVisibility(View.GONE);
                break;
        }
    }

    //获取验证码
    public void gainCode() {

        RequestParams params = new RequestParams();
        params.put("type", 2);
        params.put("tel", phone.getText().toString());
        Postl(Connector.gainCode, params, SATAT);
    }

    //重置密码
    public void editPwd() {

        RequestParams params = new RequestParams();
        params.put("rand", pass.getText().toString());
        params.put("new_pwd", password.getText().toString());
        params.put("tel", phone.getText().toString());
        Post(Connector.editPwd, params, SATATl);
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
                        ShowToast("重置成功");
                    } else {
                        ShowToast("重置失败");
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            L.e(TAG, "数据炸了!");
        }
    }
}
