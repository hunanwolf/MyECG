package com.compassecg.test720.compassecg.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import com.test720.auxiliary.Utils.DensityUtil;

/**
 * Created by hp on 2016/12/8.
 */

public class HorizontalGridView2 extends GridView {
    public HorizontalGridView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public HorizontalGridView2(Context context) {
        super(context);
    }

    public HorizontalGridView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int childWidth = DensityUtil.dip2px(getContext(),145);
        int childHeight = DensityUtil.dip2px(getContext(),200);
        int lastPadding = DensityUtil.dip2px(getContext(),10);
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(expandSpec , heightMeasureSpec);

        //设置GridView的宽度
        setMeasuredDimension(childCount * childWidth + lastPadding, childHeight);
    }

}