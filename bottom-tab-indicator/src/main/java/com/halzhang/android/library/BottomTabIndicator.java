/*
 * Copyright (C) 2015 Zhanghanguo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.halzhang.android.library;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * 底部tab导航
 * Created by zhanghanguo@yy.com on 2015/7/14.
 */
public class BottomTabIndicator extends RadioGroup implements ViewPager.OnPageChangeListener {

    private static final String LOG_TAG = BottomTabIndicator.class.getSimpleName();

    private ViewPager mViewPager;
    private int[] mTabViewIds;

    public BottomTabIndicator(Context context) {
        this(context, null);
    }

    public BottomTabIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(RadioGroup.HORIZONTAL);
        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mViewPager != null) {
                    int currentItem = getPositionForViewId(checkedId);
                    if (currentItem == mViewPager.getCurrentItem()) {
                        return;
                    }
                    Log.i(LOG_TAG, "checkedId: " + checkedId + " Current item : " + currentItem);
                    mViewPager.setCurrentItem(currentItem);
                }
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void setupChildView() {
        if (mViewPager != null && mViewPager.getAdapter() != null) {
            removeAllViews();
            PagerAdapter adapter = mViewPager.getAdapter();
            int count = adapter.getCount();
            if (count < 1) {
                throw new RuntimeException("The adapter's count must be > 0");
            }
            int[] tabViewIds = getTabViewId(adapter);
            if (tabViewIds == null) {
                mTabViewIds = new int[count];
                for (int i = 0; i < count; i++) {
                    mTabViewIds[i] = i;
                }
            } else {
                mTabViewIds = tabViewIds;
                //TODO 校验长度是否和 count 一样
            }
            for (int i = 0; i < count; i++) {
                RadioButton radioButton = (RadioButton) LayoutInflater.from(getContext()).inflate(R.layout.tab_indicator, null);
                int pageIcon = getPageIcon(adapter, i);
                if (pageIcon > 0) {
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(getContext(), pageIcon), null, null);
                }
                radioButton.setText(adapter.getPageTitle(i));
                radioButton.setId(mTabViewIds[i]);
                LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                addView(radioButton, layoutParams);
            }
        }
    }

    private int getPositionForViewId(int viewId) {
        for (int i = 0; i < mTabViewIds.length; i++) {
            if (mTabViewIds[i] == viewId) {
                return i;
            }
        }
        Log.w(LOG_TAG, "Can not get position for viewId: " + viewId);
        return 0;
    }

    private Drawable getDrawable(Context context, int resId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(resId);
        } else {
            return context.getDrawable(resId);
        }
    }

    private int getPageIcon(PagerAdapter adapter, int position) {
        if (adapter == null) {
            return 0;
        }
        if (adapter instanceof BottomTabPagerAdapter) {
            return ((BottomTabPagerAdapter) adapter).getPageIcon(position);
        }
        if (adapter instanceof BottomTabFragmentPagerAdapter) {
            return ((BottomTabFragmentPagerAdapter) adapter).getPageIcon(position);
        }
        return 0;
    }

    private int[] getTabViewId(PagerAdapter adapter) {
        if (adapter == null) {
            return null;
        }
        if (adapter instanceof BottomTabPagerAdapter) {
            return ((BottomTabPagerAdapter) adapter).getTabViewIds();
        }
        if (adapter instanceof BottomTabFragmentPagerAdapter) {
            return ((BottomTabFragmentPagerAdapter) adapter).getTabViewIds();
        }
        return null;
    }

    public void setViewPager(ViewPager viewPager) {
        if (mViewPager == viewPager) {
            return;
        }
        if (mViewPager != null) {
            mViewPager.removeOnPageChangeListener(this);
        }
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager must have adapter instance.");
        }
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
        setupChildView();
        requestLayout();
    }

    public void setViewPager(ViewPager viewPager, int initialPosition) {
        setViewPager(viewPager);
        setCurrentItem(initialPosition);
    }

    /**
     * 设置ViewPager当前页
     *
     * @param item
     */
    public void setCurrentItem(int item) {
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        mViewPager.setCurrentItem(item);
        invalidate();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        check(mTabViewIds[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
