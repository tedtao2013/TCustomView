package com.tt.tcustomview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.tt.tcustomview.R;

/**
 * FileName:
 * com.tt.tcustomview.fragment.CustomScrollViewFragment.java
 * Author: TT
 * Date: 2016-11-03
 * Description: <描述当前版本功能>
 */
public class CustomScrollViewFragment extends Fragment implements View.OnTouchListener, GestureDetector.OnGestureListener{

    GestureDetector mGestureDetector;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mGestureDetector = new GestureDetector(this);
        View view = inflater.inflate(R.layout.fragment_scrollview_demo, container, false);
        view.findViewById(R.id.show_cont_ScrollView).setOnTouchListener(this);
        view.findViewById(R.id.show_cont_ScrollView).setLongClickable(true);
        return view;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float iXDistance = Math.abs(e1.getX() - e2.getX());
        float iYDistance = Math.abs(e1.getY() - e2.getY());
        Log.d("TED2016", "xDistance =" + iXDistance + " -- yDistance = " + iYDistance);
        Log.d("TED2016", "e1.getX() = " + e1.getX());
        Log.d("TED2016", "e2.getX() = " + e2.getX());
        Log.d("TED2016", "e1.getY() = " + e1.getY());
        Log.d("TED2016", "e2.getY() = " + e2.getY());
        if (iXDistance > 100 && iYDistance < 30 && e2.getX() > e1.getX()) {
            getActivity().finish();
        }
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
}
