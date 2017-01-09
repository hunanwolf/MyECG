package com.compassecg.test720.compassecg.LearningFragment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.LearningFragment.service.DownladService;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CoursewaredetailsActivity extends BarBaseActivity {
    TextView tv_contt;
    private final int SRAT = 1;
    ImageView imageView;
    ImageView tul;
    public static CoursewaredetailsActivity test_a = null;
    TextView title;
    TextView donwlado;
    private String rootPath = "/storage/emulated/0/Android/data/com.compassecg.test720.mydonwload/cache/MyMobileDownlod/";
    String url;
    String titlel;
    String getUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursewaredetails);
        test_a = this;
        switch (getIntent().getExtras().getInt("type")) {
            case 1:
                setTitleString("杂志详情");
                break;

            case 2:
                setTitleString("课件详情");
                break;
        }
        getFileDir(rootPath);
        donwlado = getView(R.id.donwlado);
        donwlado.setOnClickListener(this);
        imageView = getView(R.id.imageView);
        tul = getView(R.id.tul);
        title = getView(R.id.title);
        tv_contt = getView(R.id.tv_content);
        getDate();
    }

    public void getDate() {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("id", getIntent().getExtras().getString("id"));
        Post(Connector.pdfDetails, params, SRAT);
    }

    //显示下载量
    public void chang(final int a, final String path, String url) {
        this.getUrl = url;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (path.equals(getIntent().getStringExtra("type"))) {
                    if (donwlado == null) {
                        donwlado = getView(R.id.donwlado);
                    }
                    if (a == 100) {
                        donwlado.setText("打开");
                    } else {
                        donwlado.setText(a + "%");
                    }
                }
            }
        });
    }

    private List<String> items = null;//存放名称
    private List<String> paths = null;//存放路径

    public void getFileDir(String filePath) {
        try {
//            this.tv.setText("当前路径:"+filePath);// 设置当前所在路径
            items = new ArrayList<String>();
            paths = new ArrayList<String>();
            File f = new File(filePath);
            File[] files = f.listFiles();// 列出所有文件
            // 如果不是根目录,则列出返回根目录和上一目录选项
            if (!filePath.equals(rootPath)) {
                items.add("返回根目录");
                paths.add(rootPath);
                items.add("返回上一层目录");
                paths.add(f.getParent());
            }
            // 将所有文件存入list中
            if (files != null) {
                int count = files.length;// 文件个数
                for (int i = 0; i < count; i++) {
                    File file = files[i];
                    items.add(file.getName());
                    paths.add(file.getPath());
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.donwlado:
                if (!"打开".equals(donwlado.getText().toString())) {
                    Intent intent = new Intent(CoursewaredetailsActivity.this, DownladService.class);
                    intent.putExtra("url", url);
                    intent.putExtra("path", titlel);
                    startService(intent);
                } else {
                    startActivity(new Intent(mContext, Pdf.class).putExtra("path", getUrl));
                }
                break;
        }
    }

    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);

        try {
            if (jsonObject.getIntValue("code") == 1) {
                JSONObject object = jsonObject.getJSONObject("list");
                title.setText(object.getString("title"));
                tv_contt.setText(object.getString("content"));
                Glide.with(mContext)
                        .load(Connector.lll + object.getString("cover"))
                        .placeholder(R.drawable.ic_placeholder)
                        .centerCrop()
                        .into(imageView);
                url = object.getString("url");
                titlel = object.getString("title");
                if (url.equals("title") | titlel.equals("")) {
                    donwlado.setEnabled(false);
                }
                for (int i = 0; i < items.size(); i++) {
                    if (object.getString("title").equals(items.get(i))) {
                        donwlado.setText("打开");
                        getUrl = paths.get(i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
