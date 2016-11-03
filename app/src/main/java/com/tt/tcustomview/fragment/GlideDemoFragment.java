package com.tt.tcustomview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tt.tcustomview.R;

/**
 * FileName:
 * com.tt.tcustomview.fragment.GlideDemoFragment.java
 * Author: TT
 * Date: 2016-11-02
 * Description: <描述当前版本功能>
 */
public class GlideDemoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_glide_demo, container, false);
        ImageView ivPicture = (ImageView) view.findViewById(R.id.iv_glide_picture);
        Glide.with(this)
                .load("http://img2.3lian.com/2014/f6/173/d/51.jpg")
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivPicture);
        return view;
    }
}
