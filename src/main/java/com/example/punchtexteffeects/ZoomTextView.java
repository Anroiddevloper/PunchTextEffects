package com.example.punchtexteffeects;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class ZoomTextView extends androidx.appcompat.widget.AppCompatTextView {

    private static final String TAG = "ZoomTextView";
    private ScaleGestureDetector mScaleDetector;

    private float mScaleFactor = 1.f;
    private float defaultSize;

    private float zoomLimit = 3.0f;
    public ZoomTextView(Context context) {
        super(context);
        initialize();
    }

    public ZoomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ZoomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }
    private void initialize() {
        defaultSize = getTextSize();
        mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());

    }
    public void setZoomLimit(float zoomLimit) {
        this.zoomLimit = zoomLimit;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {
        super.onTouchEvent(ev);
        mScaleDetector.onTouchEvent(ev);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(1.0f, Math.min(mScaleFactor, zoomLimit));
            setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultSize * mScaleFactor);
            Log.e(TAG, String.valueOf(mScaleFactor));
            return true;
        }
    }

}
