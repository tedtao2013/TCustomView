package com.tt.tcustomview.view;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Paint;

import com.tt.tcustomview.R;

public class RandomNumberView extends View{
    // 自定义属性
    private int mLen;
    private int mTextColor;
    private int mBackground;
    private float mTextSize;
    private float mPadding;

    // 画笔
    private Paint paint;

    // 宽
    private int mWidth;
    // 高
    private int mHeight;

    private String mContent;

    private Rect mBound;
    public RandomNumberView (Context context) {
        this(context, null);
    }

    public RandomNumberView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RandomNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RandomNumberView, defStyleAttr, 0);
        mLen = a.getInteger(R.styleable.RandomNumberView_mlen, 4);
        mBackground = a.getColor(R.styleable.RandomNumberView_mBackground, Color.BLACK);
        mTextSize = a.getDimension(R.styleable.RandomNumberView_mTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP
                , 16, getResources().getDisplayMetrics()));
        mPadding = a.getDimension(R.styleable.RandomNumberView_mPadding, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                20, getResources().getDisplayMetrics()));
        mTextColor = a.getColor(R.styleable.RandomNumberView_mTextColor, Color.WHITE);
        a.recycle();
        paint = new Paint(); // 初始化画笔
        paint.setAntiAlias(true);  // 设置抗锯齿
        paint.setTextSize(mTextSize);
        mContent = getRandomNumber(mLen);
        mBound = new Rect();
        paint.getTextBounds(mContent, 0, mContent.length(), mBound);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumber();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int iWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int iWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int iHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int iHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        int iMinWidth = mBound.width() + (int)mPadding * 2;
        int iMinHeight = mBound.height() + (int) mPadding * 2;
        if (iWidthMode == MeasureSpec.EXACTLY && iWidthSize > iMinWidth) {
            mWidth = iWidthSize;
        }else {
            mWidth = iMinWidth;
        }
        if (iHeightMode == MeasureSpec.EXACTLY && iHeightSize > iMinHeight) {
            mHeight = iHeightSize;
        }else {
            mHeight = iMinHeight;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(mBackground);
        canvas.drawRect(0, 0, mWidth, mHeight, paint);
        paint.setColor(mTextColor);
        paint.setTextSize(mTextSize);
        canvas.drawText(mContent, (mWidth - mBound.width()) / 2.0f, (mHeight + mBound.height()) / 2.0f, paint);
        super.onDraw(canvas);
    }

    private String getRandomNumber(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }

    private void setNumber() {
        final int iSleep = 10;
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    try {
                        Thread.sleep(iSleep);
                        mContent = getRandomNumber(mLen);
                        RandomNumberView.this.postInvalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
