package com.compassecg.test720.compassecg.View;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.squareup.picasso.Picasso;
import com.test720.auxiliary.Utils.L;

import java.util.List;

/**
 * Created by xing on 11/15/15.
 */
public class NetworkImageAdapter extends DefaultAdapter<String> {

    public NetworkImageAdapter(Context context, List<String> t) {
        super(context, t);
        L.e("t", t.size() + "");
    }

    @Override
    public View getView(int positon, View recycleView) {
        String url = Connector.lll + getItem(positon);
//      String url = getItem(positon) ;
        ImageView imageView;

        if (recycleView == null) {
            imageView = generialDefaultImageView();
        } else {
            imageView = (ImageView) recycleView;
        }

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(context).load(url).placeholder(R.drawable.ic_placeholder).into(imageView);

        return imageView;
    }
}
