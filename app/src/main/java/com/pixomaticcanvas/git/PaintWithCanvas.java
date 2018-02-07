package com.pixomaticcanvas.git;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class PaintWithCanvas extends View {
    Paint mPaint;
    Rect mRect;
    IDegreeChange mDegreeChange;

    int mDegree = 0;
    float xDown, xMove;

    public PaintWithCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setOnDrawCalledListener(IDegreeChange listener) {
        this.mDegreeChange = listener;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mRect = new Rect(
                getLeft() + (getRight() - getLeft()) / 5,
                getLeft() + (getRight() - getLeft()) / 5,
                getRight() - (getRight() - getLeft()) / 5,
                getRight() - (getRight() - getLeft()) / 5
        );
    }

    void init() {

        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.rgb(26, 188, 156));
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getX();
                return true;

            case MotionEvent.ACTION_MOVE:
                xMove = event.getX() - xDown;
                if (xMove > 0) {
                    mDegree++;
                } else {
                    mDegree--;
                }
                mDegree %= 360;
                mDegreeChange.onDegreeCalled(mDegree);
                xDown = event.getX();
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.rotate(mDegree, mRect.centerX(), mRect.centerY());
        canvas.drawRect(mRect, mPaint);
    }

}
