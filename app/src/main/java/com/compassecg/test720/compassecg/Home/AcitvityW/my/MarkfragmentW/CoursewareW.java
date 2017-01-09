package com.compassecg.test720.compassecg.Home.AcitvityW.my.MarkfragmentW;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.LearningFragment.bean.Magazine;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.BaseFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW.uid;

/**
 * Created by hp on 2016/12/14.
 */

public class CoursewareW extends BaseFragment implements CompoundButton.OnCheckedChangeListener {

    courseare_Grad_adapterW adapterW;
    SwipeRefreshLayout swip;
    GridView gridview;
    List<Magazine> list = new ArrayList<>();
    List<Magazine> listll = new ArrayList<>();
    public static CoursewareW maninfrag = null;
    int index = -1;
    private int thispage = 1;
    private final int SATTT = 1;

    public CoursewareW(int i) {
        super();
        this.index = i;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_courseware, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        maninfrag = this;
        listll.clear();
        gridview = getView(R.id.gridview);
        swip = getView(R.id.swip);


        adapterW = new courseare_Grad_adapterW(getContext(), list);
        adapterW.setSelectItem(index);
        gridview.setAdapter(adapterW);
        getDate();
    }

    public void getDate() {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("to_uid", uid);
        params.put("p", thispage);
        Get(Connector.courseware, params, SATTT);
    }

    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);
        switch (what) {
            case SATTT:
                if (jsonObject.getIntValue("code") == 1) {
                    if (thispage == 1) {

                        list.clear();
                    }
                    JSONObject object = jsonObject.getJSONObject("list");
                    JSONArray jsonArray2 = object.getJSONArray("courseware");
                    List<Magazine> problj2 = JSONObject.parseArray(jsonArray2.toString(), Magazine.class);
                    list.addAll(problj2);
                    adapterW.notifyDataSetChanged();
                }
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            if (listll.contains(buttonView.getTag())) {
                listll.remove(buttonView.getTag());
                //已选中移除
                ShowToast("删除" + listll.size());
            }
        } else {
            if (!listll.contains(buttonView.getTag())) {
                //未选中添加

                listll.add((Magazine) buttonView.getTag());
                ShowToast("添加" + listll.size());
            }
        }
    }

    public class courseare_Grad_adapterW extends BaseAdapter {


        List<Magazine> list;
        private Context mContext;

        public courseare_Grad_adapterW(Context mContext, List<Magazine> list) {
            this.mContext = mContext;
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list.size() != 0) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
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
                convertView = View.inflate(mContext, R.layout.item_coureseware_gradview, null);
                indicator.title = (TextView) convertView.findViewById(R.id.title);
                indicator.img = (ImageView) convertView.findViewById(R.id.img);
                indicator.domwlod = (TextView) convertView.findViewById(R.id.domwlod);
                indicator.tureload = (ImageView) convertView.findViewById(R.id.tureload);
                indicator.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
                indicator.checkBox.setOnCheckedChangeListener(CoursewareW.maninfrag);
                getFileDir(rootPath);
                convertView.setTag(indicator);
            } else {
                indicator = (Indicator) convertView.getTag();
            }

            Glide.with(mContext)
                    .load(Connector.lll + list.get(position).getCover())
                    .placeholder(R.mipmap.kej)
                    .centerCrop()
                    .into(indicator.img);
            if (selectItem == -1) {
                indicator.checkBox.setVisibility(View.INVISIBLE);
            } else {
                indicator.checkBox.setVisibility(View.VISIBLE);
            }
            for (int i = 0; i < items.size(); i++) {
                if (list.get(position).getTitle().equals(items.get(i))) {
                    indicator.tureload.setVisibility(View.VISIBLE);
                } else {
                    indicator.tureload.setVisibility(View.INVISIBLE);
                }
            }
            indicator.checkBox.setTag(list.get(position));
            indicator.checkBox.setChecked(listll.contains(list.get(position)));//重用


            indicator.title.setText(list.get(position).getTitle());
//       indicator.img.setImageResource(icon[position]);

            return convertView;
        }

        public void setSelectItem(int selectItem) {
            this.selectItem = selectItem;
        }

        private int selectItem = -1;

        public class Indicator {
            private TextView title;
            private ImageView img;
            private ImageView tureload;
            private CheckBox checkBox;
            private TextView domwlod;

        }
    }

    public void adapternoyicefiy(int i) {
        adapterW.setSelectItem(i);
        adapterW.notifyDataSetChanged();
    }

    public void changgede() {
        list.removeAll(listll);
        adapterW.setSelectItem(-1);
        adapterW.notifyDataSetChanged();
        listll.clear();
    }

    public void adachangge() {
        listll.clear();
        adapterW.setSelectItem(-1);
        adapterW.notifyDataSetChanged();
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
