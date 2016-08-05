package com.kevin.progressbarlib.progressbarlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.kevin.progressbarlib.R;


public class UpdateProgressBar extends HorizontalProgressBarWithNumber {
    /**
     * mRadius of view
     */
    private int mRadius = dp2px(30);
    private int mMaxPaintWidth;
    private Bitmap mCentralImage;

    public UpdateProgressBar(Context context) {
        this(context, null);
    }

    public UpdateProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        mReachedProgressBarHeight = (int) (mUnReachedProgressBarHeight * 2.5f);
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.UpdateProgressBar);
        mRadius = (int) ta.getDimension(
                R.styleable.UpdateProgressBar_update_radius, mRadius);
        mCentralImage = BitmapFactory.decodeResource(getResources(), ta.getResourceId(R.styleable.UpdateProgressBar_central_image, 0));
        ta.recycle();

        mPaint.setStyle(Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Cap.ROUND);
    }

    /**
     * 这里默认在布局中padding值要么不设置，要么全部设置
     */
    @Override
    protected synchronized void onMeasure(int widthMeasureSpec,
                                          int heightMeasureSpec) {

        mMaxPaintWidth = Math.max(mReachedProgressBarHeight,
                mUnReachedProgressBarHeight);
        int expect = mRadius * 2 + mMaxPaintWidth + getPaddingLeft()
                + getPaddingRight();
        int width = resolveSize(expect, widthMeasureSpec);
        int height = resolveSize(expect, heightMeasureSpec);
        int realWidth = Math.min(width, height);

        mRadius = (realWidth - getPaddingLeft() - getPaddingRight() - mMaxPaintWidth) / 2;

        setMeasuredDimension(realWidth, realWidth);

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {

        String text = getProgress() + "%";
        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent() + mPaint.ascent()) / 2;

        canvas.save();
        canvas.translate(getPaddingLeft() + mMaxPaintWidth / 2, getPaddingTop()
                + mMaxPaintWidth / 2);
        mPaint.setStyle(Style.STROKE);

        // draw unreaded bar
        mPaint.setColor(mUnReachedBarColor);
        mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);

        // draw reached bar
        mPaint.setColor(mReachedBarColor);
        mPaint.setStrokeWidth(mReachedProgressBarHeight);
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        canvas.drawArc(new RectF(0, 0, mRadius * 2, mRadius * 2), 0,
                sweepAngle, false, mPaint);

        // draw central bitmap
        canvas.drawBitmap(mCentralImage, mRadius - mCentralImage.getHeight() / 2, mRadius - mCentralImage.getWidth() / 2, mPaint);
        canvas.restore();
    }

    public Bitmap getCentralImage() {
        return mCentralImage;
    }

    public void setCentralImage(Drawable mCentralImage) {
        BitmapDrawable bd = (BitmapDrawable) mCentralImage;
        this.mCentralImage = bd.getBitmap();
        invalidate();
    }
}
