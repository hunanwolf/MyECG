package com.compassecg.test720.compassecg.Home.AcitvityW.my.Setfocus;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;


public class AboutActivity extends BarBaseActivity {


    RelativeLayout back;

    ImageView logo;

    TextView version;

    TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_about);
        setTitleString("关于我们");

        setview();
    }

    public void setview() {

        logo = getView(R.id.logo);
        version = getView(R.id.version);
//        detail = getView(R.id.detail);
    }


}
