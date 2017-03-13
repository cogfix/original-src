package org.phss.hgis.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;

public class CustomImageButton extends View {
    TypedArray array;
    int backgroundColor;
    int color;
    Canvas dCanvas;
    int dWidth;
    int dheight;
    private final Bitmap image;
    private final int imageResId;
    private final String label;
    OnClickListener listener;
    TypedArray tarray;
    int textColor;

    public CustomImageButton(Context context, int resImage, String label, int width, int height) {
        super(context);
        this.array = null;
        this.tarray = null;
        this.backgroundColor = 0;
        this.textColor = 0;
        this.color = -12434878;
        this.dWidth = 0;
        this.dheight = 0;
        this.label = label;
        this.imageResId = resImage;
        this.image = BitmapFactory.decodeResource(context.getResources(), this.imageResId);
        this.array = context.getTheme().obtainStyledAttributes(new int[]{16842801, 16842806});
        this.backgroundColor = this.array.getColor(0, 16711935);
        this.textColor = this.array.getColor(1, 16711935);
        setFocusable(true);
        setBackgroundColor(this.backgroundColor);
        setClickable(true);
        this.tarray = context.obtainStyledAttributes(new int[]{16973886});
        this.dWidth = width;
        this.dheight = height;
    }

    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        if (!gainFocus) {
            setBackgroundColor(this.backgroundColor);
        }
    }

    protected void onDraw(Canvas canvas) {
        this.dCanvas = canvas;
        Paint textPaint = new Paint();
        textPaint.setColor(this.textColor);
        textPaint.setAntiAlias(true);
        int i = this.dWidth;
        if (r0 < 300) {
            textPaint.setTextSize(9.0f);
        }
        int bitWide = this.image.getWidth();
        int bitHeight = this.image.getHeight();
        int canvasWide = (this.image.getWidth() * 2) - 10;
        int canvasHeight = this.image.getHeight() * 2;
        int widePadding = (canvasWide / 2) - (bitWide / 2);
        int heightPadding = (canvasHeight / 2) - (bitHeight / 2);
        int txtWidePadding = (canvasWide / 2) - (((int) textPaint.measureText(this.label)) / 2);
        Paint paint = new Paint();
        RectF rectF = new RectF(new Rect(0, 0, canvasWide, canvasHeight));
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(this.color);
        canvas.drawRoundRect(rectF, 10.6f, 10.6f, paint);
        canvas.drawBitmap(this.image, (float) widePadding, (float) (heightPadding - 10), null);
        canvas.drawText(this.label, (float) txtWidePadding, (float) ((this.image.getHeight() + heightPadding) + 5), textPaint);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        return getMeasurement(measureSpec, this.image.getWidth() * 2);
    }

    private int measureHeight(int measureSpec) {
        return getMeasurement(measureSpec, this.image.getHeight() * 2);
    }

    private int getMeasurement(int measureSpec, int preferred) {
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (MeasureSpec.getMode(measureSpec)) {
            case Integer.MIN_VALUE:
                return Math.min(preferred, specSize);
            case 1073741824:
                return specSize;
            default:
                return preferred;
        }
    }

    public String getLabel() {
        return this.label;
    }

    public int getImageResId() {
        return this.imageResId;
    }

    public void setColor() {
        this.color = -16776961;
    }

    public void setColor(int col) {
        this.color = col;
    }

    public int getColor() {
        return this.color;
    }

    public void setDefaultColor() {
        this.color = -12434878;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }
}
