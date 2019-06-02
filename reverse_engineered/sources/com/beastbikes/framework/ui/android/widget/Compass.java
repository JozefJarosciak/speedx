package com.beastbikes.framework.ui.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.beastbikes.framework.ui.android.C2824R;

public class Compass extends View implements SensorEventListener {
    private Sensor aSensor;
    private float azimuth;
    private final Drawable dialFace;
    private final Drawable dialNeedle;
    private final float[] gravity;
    private Sensor mSensor;
    private final float[] magnetic;
    private float pitch;
    private float roll;
    private SensorManager sensorManager;

    public enum Direction {
        NORTH,
        NORTH_EAST,
        EAST,
        SOUTH_EAST,
        SOUTH,
        SOUTH_WEST,
        WEST,
        NORTH_WEST
    }

    static Direction getDirectionFromDegrees(float f) {
        if (((double) f) >= -22.5d && ((double) f) < 22.5d) {
            return Direction.NORTH;
        }
        if (((double) f) >= 22.5d && ((double) f) < 67.5d) {
            return Direction.NORTH_EAST;
        }
        if (((double) f) >= 67.5d && ((double) f) < 112.5d) {
            return Direction.EAST;
        }
        if (((double) f) >= 112.5d && ((double) f) < 157.5d) {
            return Direction.SOUTH_EAST;
        }
        if (((double) f) >= 157.5d || ((double) f) < -157.5d) {
            return Direction.SOUTH;
        }
        if (((double) f) >= -157.5d && ((double) f) < -112.5d) {
            return Direction.SOUTH_WEST;
        }
        if (((double) f) >= -112.5d && ((double) f) < -67.5d) {
            return Direction.WEST;
        }
        if (((double) f) < -67.5d || ((double) f) >= -22.5d) {
            return null;
        }
        return Direction.NORTH_WEST;
    }

    public Compass(Context context) {
        this(context, null);
    }

    public Compass(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Compass(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.gravity = new float[3];
        this.magnetic = new float[3];
        this.azimuth = 0.0f;
        this.pitch = 0.0f;
        this.roll = 0.0f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2824R.styleable.Compass);
        this.dialFace = obtainStyledAttributes.getDrawable(C2824R.styleable.Compass_dialFace);
        this.dialNeedle = obtainStyledAttributes.getDrawable(C2824R.styleable.Compass_dialNeedle);
        obtainStyledAttributes.recycle();
    }

    protected void onAttachedToWindow() {
        boolean z = true;
        try {
            this.sensorManager = (SensorManager) getContext().getSystemService("sensor");
            this.aSensor = this.sensorManager.getDefaultSensor(1);
            this.mSensor = this.sensorManager.getDefaultSensor(2);
            this.sensorManager.registerListener(this, this.aSensor, 2);
            this.sensorManager.registerListener(this, this.mSensor, 2);
        } catch (UnsupportedOperationException e) {
        }
        super.onAttachedToWindow();
        if (this.dialFace != null) {
            this.dialFace.setVisible(getVisibility() == 0, false);
        }
        if (this.dialNeedle != null) {
            Drawable drawable = this.dialNeedle;
            if (getVisibility() != 0) {
                z = false;
            }
            drawable.setVisible(z, false);
        }
    }

    protected void onDetachedFromWindow() {
        if (this.sensorManager != null) {
            this.sensorManager.unregisterListener(this, this.aSensor);
            this.sensorManager.unregisterListener(this, this.mSensor);
        }
        super.onDetachedFromWindow();
        if (this.dialFace != null) {
            this.dialFace.setVisible(false, false);
        }
        if (this.dialNeedle != null) {
            this.dialNeedle.setVisible(false, false);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int i = width >> 1;
        int i2 = height >> 1;
        if (width <= height) {
            height = width;
        }
        height >>= 1;
        canvas.save();
        if (this.dialFace != null) {
            this.dialFace.setBounds(i - height, i2 - height, i + height, i2 + height);
            this.dialFace.draw(canvas);
        }
        canvas.rotate(-this.azimuth, (float) i, (float) i2);
        if (this.dialNeedle != null) {
            this.dialNeedle.setBounds(i - height, i2 - height, i + height, height + i2);
            this.dialNeedle.draw(canvas);
        }
        canvas.restore();
    }

    protected void onMeasure(int i, int i2) {
        int intrinsicWidth;
        int i3 = 0;
        if (this.dialFace != null) {
            intrinsicWidth = this.dialFace.getIntrinsicWidth();
            i3 = this.dialFace.getIntrinsicHeight();
        } else if (this.dialNeedle != null) {
            intrinsicWidth = this.dialNeedle.getIntrinsicWidth();
            i3 = this.dialNeedle.getIntrinsicHeight();
        } else {
            intrinsicWidth = 0;
        }
        setMeasuredDimension(resolveAdjustedSize(intrinsicWidth, Integer.MAX_VALUE, i), resolveAdjustedSize(i3, Integer.MAX_VALUE, i2));
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case 1:
                System.arraycopy(sensorEvent.values, 0, this.gravity, 0, 3);
                break;
            case 2:
                System.arraycopy(sensorEvent.values, 0, this.magnetic, 0, 3);
                break;
            default:
                return;
        }
        float[] fArr = new float[9];
        float[] fArr2 = new float[3];
        if (SensorManager.getRotationMatrix(fArr, new float[9], this.gravity, this.magnetic)) {
            SensorManager.getOrientation(fArr, fArr2);
            for (int i = 0; i < fArr2.length; i++) {
                fArr2[i] = (float) Math.toDegrees((double) fArr2[i]);
            }
            this.azimuth = fArr2[0];
            this.pitch = fArr2[1];
            this.roll = fArr2[2];
            invalidate();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    private int resolveAdjustedSize(int i, int i2, int i3) {
        int mode = MeasureSpec.getMode(i3);
        int size = MeasureSpec.getSize(i3);
        switch (mode) {
            case Integer.MIN_VALUE:
                return Math.min(Math.min(i, size), i2);
            case 0:
                return Math.min(i, i2);
            default:
                return size;
        }
    }
}
