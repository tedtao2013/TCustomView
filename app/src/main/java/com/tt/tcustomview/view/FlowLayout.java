package com.tt.tcustomview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName:
 * com.tt.tcustomview.view.FlowLayout.java
 * Author: 加菲糖-西安
 */
public class FlowLayout extends ViewGroup {
    private List<List<View>> mAllViews;
    private List<Integer> mLineHeight;

    /**
     * the three constructor
     *
     * @param context
     */
    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mAllViews = new ArrayList<>();
        mLineHeight = new ArrayList<>();
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //通过测量值，先得到宽高的“测量大小”和“测量模式”
        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int specHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int specHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        mAllViews.clear();
        mLineHeight.clear();
        // measureChildren(widthMeasureSpec,heightMeasureSpec);//计算出所有child的宽和高，接下来有用

        // 如果是wrap_content则计算宽高
        int childCount = getChildCount();
        int width = 0;
        int height = 0;
        int lineWidth = 0;
        int lineHeight = 0;
        List<View> mLineViews = new ArrayList<>();

        for (int i=0;i<childCount;i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth;
            int childHeight;
            childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (lineWidth + childWidth > specWidthSize){
                //如果需要换行,先保存数据比较好
                mAllViews.add(mLineViews);
                mLineHeight.add(lineHeight);
                mLineViews = new ArrayList<>();
                mLineViews.add(child);
                width = Math.max(lineWidth,width);
                height += lineHeight;
                lineWidth = childWidth;
                lineHeight = childHeight;
            }else {
                //如果不需要换行
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight,childHeight);
                mLineViews.add(child);
            }

            if (i == childCount-1){
                width = Math.max(lineWidth,width);
                height += lineHeight;
                mAllViews.add(mLineViews);
                mLineHeight.add(lineHeight);
            }
        }//for循环结束
        setMeasuredDimension(specWidthMode == MeasureSpec.EXACTLY ? specWidthSize:width,
                specHeightMode == MeasureSpec.EXACTLY ? specHeightSize:height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineNum = mAllViews.size();
        List<View> mLineView;
        int lineHeight;
        int left = 0;
        int top = 0;

        for (int i=0;i<lineNum;i++){
            mLineView = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);
            for (int j=0;j<mLineView.size();j++){
                View child = mLineView.get(j);
                if (child.getVisibility() == View.GONE){
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int childLeft = left + lp.leftMargin;
                int childTop = top + lp.topMargin;
                int childRight = childLeft + child.getMeasuredWidth();
                int childBottom = childTop + child.getMeasuredHeight();
                child.layout(childLeft,childTop,childRight,childBottom);
                left = childRight + lp.rightMargin;
            }
            left = 0;
            top += lineHeight;
        }
    }
}
