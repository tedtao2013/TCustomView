package com.tt.tcustomview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tt.tcustomview.R;
import com.tt.tcustomview.view.RightSlideClosedView;

/**
 * FileName:
 * com.tt.tcustomview.fragment.RightSlideClosedFragment.java
 * Author: TT
 * Date: 2016-11-03
 * Description: <描述当前版本功能>
 */
public class RightSlideClosedFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_slide_closed, container, false);
        ((RightSlideClosedView) view.findViewById(R.id.rscv_test)).setRightSlideListener(new RightSlideClosedView.RightSlideListener() {
            @Override
            public void rightSlide() {
                getActivity().finish();
            }
        });
        return view;
    }
}
