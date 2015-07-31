package com.jizheng.nicefound.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.jizheng.nicefound.R;

/**
 * Created by cpp on 2015/7/29.
 */
public class HorizontalProgressBar extends View {

    private Paint paint = new Paint();
    private Paint paint2 = new Paint();

    {
        paint.setColor(getResources().getColor(R.color.app_bg_darker_gray));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);

        paint2.setColor(getResources().getColor(R.color.tab_red));
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setAntiAlias(true);


    }

    public HorizontalProgressBar(Context context) {
        super(context);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBar);
        progress = array.getInt(R.styleable.HorizontalProgressBar_progress, 0); //提供默认值，放置未指定
        max = array.getInt(R.styleable.HorizontalProgressBar_max, 0); //提供默认值，放置未指定
        array.recycle();
    }


    public HorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int i = getBottom() - getTop();
        canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), i, i, paint);
        canvas.drawRoundRect(new RectF(0, 0, getWidth()*progress/max, getHeight()), i, i, paint2);
    }

    private float max = 100;
    private float progress = 0;

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
