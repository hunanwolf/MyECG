package com.compassecg.test720.compassecg.Home.AcitvityW.message;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;
import com.kyleduo.switchbutton.SwitchButton;

public class disturbActivity extends BarBaseActivity {
    SwitchButton swbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disturb);
        setTitleString("通知设置");
        swbtn = getView(R.id.swbtn);
        swbtn.setThumbDrawable(mContext.getResources().getDrawable(R.drawable.switchbtn));
//        swbtn.setThumbSize(70,40);
//        swbtn.setThumbRadius(10);
//        swbtn.setThumbColorRes(R.color.wenhong);
        // 监听器来监听事件
        swbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(disturbActivity.this, "Default style button, new state: " + (isChecked ? "on" : "off"), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
