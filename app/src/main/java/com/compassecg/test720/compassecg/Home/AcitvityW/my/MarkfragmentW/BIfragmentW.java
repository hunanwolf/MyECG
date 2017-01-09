package com.compassecg.test720.compassecg.Home.AcitvityW.my.MarkfragmentW;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.CircleImageView;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.BaseFragment;

import static com.compassecg.test720.compassecg.Home.AcitvityW.my.markActivityW.uid;

/**
 * Created by hp on 2016/12/14.
 */

public class BIfragmentW extends BaseFragment {
    CircleImageView img_header;
    TextView name;//名字
    TextView zhiwei;//职位
    TextView zhicheng;//职称
    TextView histoy;//医院
    TextView keshi;//科室
    TextView intro;//专长
    TextView textView7;//经历
    private final int STATl = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_introduction, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        img_header = getView(R.id.img_header);
        name = getView(R.id.name);
        zhiwei = getView(R.id.zhiwei);
        zhicheng = getView(R.id.zhicheng);
        histoy = getView(R.id.histoy);
        keshi = getView(R.id.keshi);
        intro = getView(R.id.intro);
        textView7 = getView(R.id.textView7);
        getDate();

    }

    public void getDate() {
        RequestParams params = new RequestParams();
        params.put("to_uid", uid);
        params.put("uid", APP.uuid);
        Postl(Connector.homeInfo, params, STATl);
    }


    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);
        switch (what) {
            case STATl:

                if (jsonObject.getIntValue("code") == 1) {

                    JSONObject object = jsonObject.getJSONObject("list");
                    name.setText(object.getString("nickname"));
                    zhiwei.setText(object.getString("hospital") + "·" + object.getString("desk") + "·" + object.getString("job"));
                    zhicheng.setText(object.getString("job"));
                    keshi.setText(object.getString("desk"));
                    histoy.setText(object.getString("hospital"));
                    intro.setText(object.getString("speciality"));
                    textView7.setText(object.getString("experience"));
                    Glide.with(getActivity())
                            .load(Connector.lll + object.getString("pic"))
                            .placeholder(R.drawable.index_head)
                            .centerCrop()
                            .into(img_header);
                }
                break;
        }
    }
}
