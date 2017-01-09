package com.compassecg.test720.compassecg.BigImage;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.compassecg.test720.compassecg.R;
import com.test720.auxiliary.Utils.NoBarBaseActivity;

import java.util.ArrayList;
import java.util.List;


public class ImageViewpagerActivity extends NoBarBaseActivity {

    public static String url1 = "http://img.shu163.com/uploadfiles/wallpaper/2010/6/2010063006111517.jpg";
    public static String url2 = "http://pic.pp3.cn/uploads//allimg/111116/11021321R-4.jpg";
    public static String url3 = "http://pic.yesky.com/imagelist/07/03/1769316_2073.jpg";

    ImageViewPagerAdapter adapter;

    public static ImageViewpagerActivity kl = null;
    HackyViewPager pager;
    TextView title;
    TextView text;
    final List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        kl = this;
        findViewById(R.id.xingwe).setVisibility(View.INVISIBLE);
        title = (TextView) findViewById(R.id.title);
        title.setText("预览");
        text = (TextView) findViewById(R.id.text);
        findViewById(R.id.move).setOnClickListener(this);
        pager = (HackyViewPager) findViewById(R.id.pager);
        list.addAll(getIntent().getStringArrayListExtra("list"));


        adapter = new ImageViewPagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
        text.setText(getIntent().getExtras().getInt("type") + 1 + "/" + list.size());
        pager.setCurrentItem(getIntent().getExtras().getInt("type"));
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                text.setText(position + 1 + "/" + list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.move:
                finish();
                overridePendingTransition(R.anim.umeng_socialize_fade_in, R.anim.umeng_socialize_fade_out);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.umeng_socialize_fade_in, R.anim.umeng_socialize_fade_out);
        }
        return false;
    }
}
