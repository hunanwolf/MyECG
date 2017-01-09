package com.compassecg.test720.compassecg.Home.AcitvityW.my.Setfocus;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.NoBarBaseActivity;
import com.test720.auxiliary.Utils.T;


public class FeedbackActivity extends NoBarBaseActivity {


    ImageView back;

    EditText title;

    EditText detail;

    Button ok;
    private final int SSRRR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feedback);
        setview();

    }


    public void getDatae() {

        String detail1 = detail.getText().toString();

        if (!detail1.equals("")) {

            RequestParams params = new RequestParams();
            params.put("uid", APP.uuid);
            params.put("content",detail1);
            Post(Connector.feedback, params, SSRRR);

        } else
            T.showLong(this, "" + "请完善信息！");
    }

    public void setview() {
        getView(R.id.back).setOnClickListener(this);
        getView(R.id.ok).setOnClickListener(this);

        detail = getView(R.id.detail);

        detail.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (detail.getText().length() == 140) {
                    ShowToast("意见反馈不能超过140字");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ok:
                getDatae();
                break;
        }
    }

    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);
        int msg = jsonObject.getIntValue("code");
        if (msg == 1) {
            T.showLong(this, "" + "发送成功！");
            finish();
        } else
            T.showLong(this, "" + "发送失败，请重试！");
    }
}
