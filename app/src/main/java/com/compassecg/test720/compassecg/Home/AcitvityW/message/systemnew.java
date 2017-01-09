package com.compassecg.test720.compassecg.Home.AcitvityW.message;

import android.os.Bundle;
import android.widget.TextView;

import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;


public class systemnew extends BarBaseActivity {

    TextView title;
    TextView content;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemnew);
        setTitleString("系统公告");
        title = getView(R.id.title);
        content = getView(R.id.content);
        time = getView(R.id.time);
//        title.setText(getIntent().getExtras().getString("title"));
//        content.setText("   " + getIntent().getExtras().getString("news"));
//        time.setText(getIntent().getExtras().getString("time"));
    }
}
