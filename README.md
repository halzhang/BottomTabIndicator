# BottomTabIndicator
Bottom Tab Indicator，Tab Navigate

底部导航tab控件

<img src="https://raw.githubusercontent.com/halzhang/BottomTabIndicator/master/device-2015-07-14-163800.png" width="480"/>

用法
----
1、布局

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:tools="http://schemas.android.com/tools"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:orientation="vertical"
              tools:context=".MainActivity">

        <android.support.v4.view.ViewPager
            android:id="@+id/viewpager"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"/>

        <com.halzhang.android.library.BottomTabIndicator
            android:id="@+id/indicator"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@android:color/background_dark"
            android:paddingBottom="10dp"
            android:paddingTop="10dp"/>
    </LinearLayout>

2、初始化

    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            viewPager.setAdapter(new MyPagerAdapter());
            BottomTabIndicator indicator = (BottomTabIndicator) findViewById(R.id.indicator);
            indicator.setViewPager(viewPager, 0);
        }

4、自定义Adapter，需要继承 [BottomTabFragmentPagerAdapter](https://github.com/halzhang/BottomTabIndicator/blob/master/library/src/main/java/com/halzhang/android/library/BottomTabFragmentPagerAdapter.java) 或者 [BottomTabPagerAdapter](https://github.com/halzhang/BottomTabIndicator/blob/master/library/src/main/java/com/halzhang/android/library/BottomTabPagerAdapter.java)

        private class MyPagerAdapter extends BottomTabFragmentPagerAdapter {

            public MyPagerAdapter() {
                super(getSupportFragmentManager());
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Fragment getItem(int position) {
                return TextFragment.newInstance(position);
            }

            @Override
            public int getPageIcon(int position) {
                return R.drawable.ic_tab;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Tab " + position;
            }
        }

3、自定义tab字体大小和颜色

        <style name="bottom_tab_indicator">
            <item name="android:textSize">10sp</item>
            <item name="android:textColor">@color/tab_text_color</item>
        </style>

Download
-----

    repositories {
        maven {
            url 'https://dl.bintray.com/halzhang/maven/'
        }
    }

    ...

    dependencies {
        compile 'com.github.halzhang.android.library:bottom-tab-indicator:1.0.0'
    }


License
-----
    Copyright 2015 Zhanghanguo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
