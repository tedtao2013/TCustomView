package com.tt.tcustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * FileName:
 * com.tt.tcustomview.view.CustomSrcollview.java
 * Author: TT
 * Date: 2016-11-03
 * Description: <描述当前版本功能>
 */
public class CustomScrollview extends ScrollView{

    public CustomScrollview(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public CustomScrollview(Context context, AttributeSet attr, int defStyleAttr) {
        super(context, attr, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("TED2016", "" + getHeight());
        super.onDraw(canvas);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
