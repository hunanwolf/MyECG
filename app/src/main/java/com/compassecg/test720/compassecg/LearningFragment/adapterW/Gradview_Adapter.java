package com.compassecg.test720.compassecg.LearningFragment.adapterW;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.compassecg.test720.compassecg.R;

/**
 * Created by hp on 2016/12/20.
 */

public class Gradview_Adapter extends BaseAdapter {
    String url[] = {"医生列表", "专家讲座", "直播", "杂志阅读", "课件下载", "模拟考试"};
    int list[] = {R.mipmap.study_doctor_list, R.mipmap.study_lecture, R.mipmap.study_live, R.mipmap.study_magazine, R.mipmap.study_coursewar, R.mipmap.study_examination};
    Context context;

    public Gradview_Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return url.length;
    }

    @Override
    public Object getItem(int position) {
        return url[position];
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
            convertView = View.inflate(context, R.layout.item_lenarling_gridview, null);
            indicator.lv_titlname = (TextView) convertView.findViewById(R.id.lv_titlname);
            indicator.lv_imge= (ImageView) convertView.findViewById(R.id.lv_imge);

            convertView.setTag(indicator);
        } else {
            indicator = (Indicator) convertView.getTag();
        }
        indicator.lv_titlname.setText(url[position]);
//        indicator.iv_icon.setImageResource(icon[position]);
        indicator.lv_imge.setImageResource(list[position]);

        return convertView;
    }

    public class Indicator {
        private TextView lv_titlname;

        private ImageView lv_imge;
    }
}
