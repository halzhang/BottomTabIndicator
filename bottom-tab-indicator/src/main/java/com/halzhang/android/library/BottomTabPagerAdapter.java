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

import android.support.v4.view.PagerAdapter;

/**
 * 适配器，重写 {@link #getPageIcon(int)} 定义每个tab的图标
 * Created by zhanghanguo@yy.com on 2015/7/14.
 */
public abstract class BottomTabPagerAdapter extends PagerAdapter {

    /**
     * 返回tab的图标
     *
     * @param position 页面序号
     * @return 图标的资源id
     */
    public int getPageIcon(int position) {
        return 0;
    }

    /**
     * 获取 tab id，用来设置给每个 tab 的viewId {@link android.view.View#setId(int)}
     * @return tab id
     * @see android.view.View#setId(int)
     */
    public int[] getTabViewIds(){
        return null;
    }

}
