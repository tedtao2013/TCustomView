package com.tt.tcustomview.view;

import android.view.View;
import android.content.Context;
import android.util.AttributeSet;

import com.tt.tcustomview.utils.PxUtils;

/**
 * FileName:
 * com.tt.tcustomview.view.BezierView.java
 * Author: TT
 * Date: 2016-10-29
 * Description: 贝塞尔曲线
 */
public class BezierView extends View{

    private Context mContext;

    // 宽
    int mWidth;

    // 高
    int mHeight;

    // 半径
    int mRadius;
    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int iWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int iWidthSize = MeasureSpec.getMode(widthMeasureSpec);
        int iHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int iHeightSize = MeasureSpec.getMode(heightMeasureSpec);
        if (iWidthMode == MeasureSpec.EXACTLY) {
            mWidth = iWidthSize;
        }else {
            mWidth = (int) PxUtils.dpToPx(200, mContext);
        }
        if (iHeightMode == MeasureSpec.EXACTLY) {
            mHeight = iHeightSize;
        }else {
            mHeight = (int) PxUtils.dpToPx(200, mContext);
        }
        mRadius = (mWidth < mHeight ? mWidth : mHeight) / 2;
        setMeasuredDimension(mWidth, mHeight);
    }
}
