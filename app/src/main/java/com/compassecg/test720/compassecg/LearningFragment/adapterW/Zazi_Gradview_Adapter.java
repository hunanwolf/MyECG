package com.compassecg.test720.compassecg.LearningFragment.adapterW;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.Home.AcitvityW.my.MarkfragmentW.MagazineW;
import com.compassecg.test720.compassecg.LearningFragment.bean.Courseware;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.Connector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2016/12/20.
 */

public class Zazi_Gradview_Adapter extends BaseAdapter {
    List<Courseware> listll;
    Context context;


    public Zazi_Gradview_Adapter(Context context, List<Courseware> listll) {
        this.context = context;
        this.listll = listll;
    }

    @Override
    public int getCount() {
        if (listll.size() != 0) {
            return listll.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return listll.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Indicator indicator = null;
        if (convertView == null) {
            indicator = new Indicator();
            convertView = View.inflate(context, R.layout.item_coureseware_gradview, null);
            indicator.title = (TextView) convertView.findViewById(R.id.title);
            indicator.img = (ImageView) convertView.findViewById(R.id.img);
            indicator.domwlod = (TextView) convertView.findViewById(R.id.domwlod);
            indicator.tureload = (ImageView) convertView.findViewById(R.id.tureload);
            indicator.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            indicator.checkBox.setOnCheckedChangeListener(MagazineW.maninfrag);
            getFileDir(rootPath);
            convertView.setTag(indicator);
        } else {
            indicator = (Indicator) convertView.getTag();
        }
        for (int i = 0; i < items.size(); i++) {
            if (listll.get(position).getTitle().equals(items.get(i))) {
                indicator.tureload.setVisibility(View.VISIBLE);
            }else {
                indicator.tureload.setVisibility(View.INVISIBLE);
            }
        }
        Glide.with(context)
                .load(Connector.lll + listll.get(position).getCover())
                .placeholder(R.mipmap.zazhi)
                .centerCrop()
                .into(indicator.img);

        indicator.title.setText(listll.get(position).getTitle());

        return convertView;
    }

    public class Indicator {
        private TextView title;
        private ImageView img;
        private ImageView tureload;
        private CheckBox checkBox;
        private TextView domwlod;
    }

    private List<String> items = null;//存放名称
    private List<String> paths = null;//存放路径
    private String rootPath = "/storage/emulated/0/Android/data/com.compassecg.test720.mydonwload/cache/MyMobileDownlod/";

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

}
