package com.tt.tcustomview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tt.tcustomview.R;

/**
 * FileName:
 * com.tt.tcustomview.fragment.FingerBallFragment.java
 * Author: TT
 * Date: 2016-11-04
 * Description: <描述当前版本功能>
 */
public class FingerBallFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_finger_ball, container, false);
    }
}
