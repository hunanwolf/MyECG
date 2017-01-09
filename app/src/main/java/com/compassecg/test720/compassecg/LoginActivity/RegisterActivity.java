package com.compassecg.test720.compassecg.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;
import com.test720.auxiliary.Utils.RegularUtil;
import com.test720.auxiliary.Utils.T;

public class RegisterActivity extends BarBaseActivity {

    EditText phone;//账号
    EditText pass;//密码
    EditText newpass;//确认密码
    ImageView clear, clear1, clear2;
    Button ok;//下一步

    public static RegisterActivity   test_a=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        test_a=this;
        setTitleString("注册");
        phone = getView(R.id.phone);
        pass = getView(R.id.pass);
        newpass = getView(R.id.newpass);
        ok = getView(R.id.ok);
        clear = getView(R.id.clear);
        clear1 = getView(R.id.clear1);
        clear2 = getView(R.id.clear2);
        clear.setOnClickListener(this);
        clear1.setOnClickListener(this);
        clear2.setOnClickListener(this);
        ok.setOnClickListener(this);
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (phone.getText().length() != 0) {

                    clear.setVisibility(View.VISIBLE);
                } else {
                    clear.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (pass.getText().length() != 0) {

                    clear1.setVisibility(View.VISIBLE);
                } else {
                    clear1.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        newpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (newpass.getText().length() != 0) {

                    clear2.setVisibility(View.VISIBLE);
                } else {
                    clear2.setVisibility(View.INVISIBLE);
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
                pass.setText("");
                clear1.setVisibility(View.GONE);
                break;
            case R.id.clear2:
                newpass.setText("");
                clear2.setVisibility(View.GONE);
                break;

            case R.id.ok:
                goutinfo();
                break;
        }
    }


    public void goutinfo() {

        if (phone.getText().length() == 0) {
            phone.setError("请输入账号！");
            return;
        }
        if (!RegularUtil.isnumber(phone.getText().toString())) {

            phone.setError("请输入字母开头加数字组成的账号");
            T.showShort(mContext, "请输入字母开头加数字组成的账号");

            return;
        }
        //     pass.getText().length() == 0
        if (!RegularUtil.ispassworld(pass.getText().toString())) {
            pass.setError("请输入6至16位密码！");
            T.showShort(mContext, "请输入6至16位密码");
            return;
        }
        if (!RegularUtil.ispassworld(newpass.getText().toString())) {
            newpass.setError("请确认6至16位密码！");
            T.showShort(mContext, "请确认6至16位密码");
            return;
        }
        startActivity(new Intent(this, BinDingActivity.class).putExtra("phone", phone.getText().toString()).putExtra("pass", pass.getText().toString()));

    }
}
