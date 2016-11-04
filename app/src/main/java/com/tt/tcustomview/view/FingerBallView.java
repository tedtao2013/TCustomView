package com.tt.tcustomview.view;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;

import com.tt.tcustomview.R;
import com.tt.tcustomview.utils.PxUtils;

/**
 * FileName:
 * com.tt.tcustomview.view.FingerBallView.java
 * Author: TT
 * Date: 2016-11-04
 * Description: 跟随手指的小球
 */
public class FingerBallView extends View {

    /**
     * 控件的宽
     */
    private int mWidth;
    /**
     * 控件的高
     */
    private int mHeight;

    /**
     * 小球半径
     */
    private float mRadius;

    /**
     * 小球颜色
     */
    private int mBallColor;

    private Context mContext;
    private Paint mPaint;

    private float dx = -1;
    private float dy = -1;
    public FingerBallView(Context context) {
        this(context, null);
    }

    public FingerBallView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FingerBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FingerBallView, defStyleAttr, 0);
        mRadius = a.getDimension(R.styleable.FingerBallView_mRadius, PxUtils.dpToPx(4, context));
        mBallColor = a.getColor(R.styleable.FingerBallView_mBallColor, ContextCompat.getColor(context, R.color.DarkVoilet));
        a.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mBallColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int iWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int iWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int iHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int iHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (iWidthMode == MeasureSpec.EXACTLY) {
            mWidth = iWidthSize;
        }else {
            mWidth =(int)(mRadius * 2 +  PxUtils.dpToPx(20, mContext));
        }
        if (iHeightMode == MeasureSpec.EXACTLY) {
            mHeight = iHeightSize;
        }else {
            mHeight = (int)(mRadius * 2 +  PxUtils.dpToPx(20, mContext));
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(dx != -1 && dy != -1) {
            canvas.drawCircle(dx, dy, mRadius, mPaint);
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dx = event.getX();
        dy = event.getY();
        invalidate();
        return true;
    }
}
