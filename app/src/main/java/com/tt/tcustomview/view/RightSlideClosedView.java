package com.tt.tcustomview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.tt.tcustomview.R;

/**
 * FileName:
 * com.tt.tcustomview.view.RightSlideClosedView.java
 * Author: TT
 * Date: 2016-11-03
 * Description: 自定义控件右滑关闭
 */
public class RightSlideClosedView extends FrameLayout{

    /**
     * X轴最小滑动距离
     */
    private int mMinXDistance;

    /**
     * Y轴最小滑动距离
     */
    private int mMinYDistance;

    /**
     * 滑动时间
     */
    private int mTouchTime;

    /**
     * 滑动角度
     */
    private int mArc;

    RightSlideListener l;

    public RightSlideClosedView(Context context) {
        this(context, null);
    }

    public RightSlideClosedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RightSlideClosedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RightSlideClosedView,defStyleAttr, 0);
        mMinXDistance = a.getInteger(R.styleable.RightSlideClosedView_mMinXDistance, 80);
        mMinYDistance = a.getInteger(R.styleable.RightSlideClosedView_mMinYDistance, 100);
        mTouchTime = a.getInteger(R.styleable.RightSlideClosedView_mTouchTime, 1000);
        mArc = a.getInteger(R.styleable.RightSlideClosedView_mArc, 30);
        if (mArc > 30) {
            mArc = 30;
        }
        a.recycle();
    }

    private boolean isClosed(float x1, float y1, float x2, float y2, long time) {
        float xDistance = Math.abs(x1 - x2);
        float yDistance = Math.abs(y1 - y2);
        double ratio = Math.tan(Math.PI/180 * mArc);  //  ratio = y / x
        return time < mTouchTime && xDistance > mMinXDistance && yDistance < mMinYDistance && yDistance / xDistance < ratio;
    }

    float dx = 0;
    float dy = 0;
    float ux = 0;
    float uy = 0;
    long starttime = 0;
    long endtime = 0;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        boolean isEvent = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                dx = ev.getX();
                dy = ev.getY();
                starttime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                ux = ev.getX();
                uy = ev.getY();
                endtime = System.currentTimeMillis();
                isEvent = isClosed(dx, dy, ux, uy, endtime - starttime);
                break;
        }
        if (isEvent) {
            if( l != null) {
                l.rightSlide();
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public interface RightSlideListener {
        void rightSlide();
    }


    public void setRightSlideListener(RightSlideListener listenr) {
        l = listenr;
    }
}
