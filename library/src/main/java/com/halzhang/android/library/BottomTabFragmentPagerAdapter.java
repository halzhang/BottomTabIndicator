package com.halzhang.android.library;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by zhanghanguo@yy.com on 2015/7/14.
 */
public abstract class BottomTabFragmentPagerAdapter extends FragmentPagerAdapter {

    public BottomTabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * 返回每个tab的图标
     *
     * @param position 页面序号
     * @return 图标的资源id
     */
    public int getPageIcon(int position) {
        return 0;
    }
}
