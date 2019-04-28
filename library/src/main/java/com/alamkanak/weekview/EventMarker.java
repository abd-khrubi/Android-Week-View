package com.alamkanak.weekview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.alamkanak.weekview.WeekView.EventRect;

public class EventMarker {

    public enum MarkerType {
        FILLED, DASHED, NONE
    }

    public static final EventMarker NO_MARKER = new EventMarker(0, MarkerType.NONE);

    public static final float DEFAULT_WIDTH = 15;
    public static final float DEFAULT_DASH_HEIGHT = 30;
    public static final float DEFAULT_DASH_GAP = 15;

    private float width;
    private float dashHeight, dashGap;
    private int color;
    private int cornerRadius;
    private MarkerType type;

    public EventMarker(float width, int cornerRadius, float dashHeight, float dashGap, int color, MarkerType type) {
        this.width = width;
        this.cornerRadius = cornerRadius;
        this.dashHeight = dashHeight;
        this.dashGap = dashGap;
        this.color = color;
        this.type = type;
    }

    public EventMarker(float width, float dashHeight, float dashGap, int color, MarkerType type) {
        this(width, 0, dashHeight, dashGap, color, type);
    }

    public EventMarker(int color, MarkerType type) {
        this(DEFAULT_WIDTH, DEFAULT_DASH_HEIGHT, DEFAULT_DASH_GAP, color, type);
    }

    public void draw(Canvas canvas, EventRect parentRect) {
        if (this.type == MarkerType.NONE) {
            return;
        }
        Paint paint = new Paint();
        paint.setColor(this.color);
        paint.setShader(parentRect.event.getShader());

        if (this.type == MarkerType.FILLED) {
            canvas.drawRoundRect(new RectF(parentRect.rectF.left, parentRect.rectF.top, parentRect.rectF.left + this.width, parentRect.rectF.bottom), cornerRadius, cornerRadius, paint);
        } else if (this.type == MarkerType.DASHED) {
            for (float y = parentRect.rectF.top; y < parentRect.rectF.bottom; y += this.dashHeight + this.dashGap) {
                RectF tmp = new RectF(parentRect.rectF.left, y, parentRect.rectF.left + this.width, y + dashHeight > parentRect.rectF.bottom ? parentRect.rectF.bottom : y + dashHeight);
                canvas.drawRoundRect(tmp, cornerRadius, cornerRadius, paint);
            }
        }
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getDashHeight() {
        return dashHeight;
    }

    public void setDashHeight(float dashHeight) {
        this.dashHeight = dashHeight;
    }

    public float getDashGap() {
        return dashGap;
    }

    public void setDashGap(float dashGap) {
        this.dashGap = dashGap;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public MarkerType getType() {
        return type;
    }

    public void setType(MarkerType type) {
        this.type = type;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }
}
