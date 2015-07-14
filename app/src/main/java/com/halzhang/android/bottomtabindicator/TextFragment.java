package com.halzhang.android.bottomtabindicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A TextView Fragment
 * Created by zhanghanguo@yy.com on 2015/7/14.
 */
public class TextFragment extends Fragment {

    public static TextFragment newInstance(int position) {
        TextFragment textFragment = new TextFragment();
        Bundle bundle = new Bundle(1);
        bundle.putInt("position", position);
        textFragment.setArguments(bundle);
        return textFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        int position = getArguments().getInt("position");
        textView.setText(String.valueOf(position));
        return textView;
    }
}
