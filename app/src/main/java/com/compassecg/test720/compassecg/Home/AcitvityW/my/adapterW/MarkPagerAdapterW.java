package com.compassecg.test720.compassecg.Home.AcitvityW.my.adapterW;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by jesus on 2016/8/9.
 */
public class MarkPagerAdapterW extends FragmentPagerAdapter {
    List<Fragment> list;

    public MarkPagerAdapterW(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
