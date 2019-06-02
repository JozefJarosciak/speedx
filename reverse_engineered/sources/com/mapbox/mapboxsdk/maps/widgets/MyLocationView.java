package com.mapbox.mapboxsdk.maps.widgets;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationServices;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Projection;

public class MyLocationView extends View {
    private static final int COMPASS_UPDATE_RATE_MS = 500;
    private float accuracy;
    private ValueAnimator accuracyAnimator;
    private Paint accuracyPaint;
    private Rect backgroundBounds;
    private Drawable backgroundDrawable;
    private int backgroundOffsetBottom;
    private int backgroundOffsetLeft;
    private int backgroundOffsetRight;
    private int backgroundOffsetTop;
    private float bearing;
    private Camera camera;
    private MyLocationView$CompassListener compassListener;
    private float contentPaddingX;
    private float contentPaddingY;
    private ValueAnimator directionAnimator;
    private Drawable foregroundBearingDrawable;
    private Rect foregroundBounds;
    private Drawable foregroundDrawable;
    private AnimatorUpdateListener invalidateSelfOnUpdateListener;
    private LatLng latLng;
    private Location location;
    private ValueAnimator locationChangeAnimator;
    private long locationUpdateTimestamp;
    private MapboxMap mapboxMap;
    private Matrix matrix;
    private int myBearingTrackingMode;
    private MyLocationView$MyLocationBehavior myLocationBehavior;
    private int myLocationTrackingMode;
    private float previousDirection;
    private float[] projectedCoordinate;
    private float projectedX;
    private float projectedY;
    private Projection projection;
    private PointF screenLocation;
    private float tilt;
    private MyLocationView$GpsLocationListener userLocationListener;

    public MyLocationView(Context context) {
        super(context);
        this.projectedCoordinate = new float[2];
        this.invalidateSelfOnUpdateListener = new MyLocationView$1(this);
        init(context);
    }

    public MyLocationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.projectedCoordinate = new float[2];
        this.invalidateSelfOnUpdateListener = new MyLocationView$1(this);
        init(context);
    }

    public MyLocationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.projectedCoordinate = new float[2];
        this.invalidateSelfOnUpdateListener = new MyLocationView$1(this);
        init(context);
    }

    private void init(Context context) {
        if (!isInEditMode()) {
            setEnabled(false);
            setLayoutParams(new LayoutParams(-1, -1));
            this.matrix = new Matrix();
            this.camera = new Camera();
            this.camera.setLocation(0.0f, 0.0f, -1000.0f);
            this.accuracyPaint = new Paint();
            this.myLocationBehavior = new MyLocationView$MyLocationBehaviorFactory(this, null).getBehavioralModel(0);
            this.compassListener = new MyLocationView$CompassListener(this, context);
        }
    }

    public final void setForegroundDrawables(Drawable drawable, Drawable drawable2) {
        if (drawable != null) {
            if (drawable2 == null) {
                drawable2 = drawable.getConstantState().newDrawable();
            }
            if (this.backgroundDrawable == null) {
                this.backgroundDrawable = drawable.getConstantState().newDrawable();
            }
            if (drawable.getIntrinsicWidth() == drawable2.getIntrinsicWidth() && drawable.getIntrinsicHeight() == drawable2.getIntrinsicHeight()) {
                this.foregroundDrawable = drawable;
                this.foregroundBearingDrawable = drawable2;
                invalidateBounds();
                return;
            }
            throw new RuntimeException("The dimensions from location and bearing drawables should be match");
        }
    }

    public final void setForegroundDrawableTint(@ColorInt int i) {
        if (i != 0) {
            if (this.foregroundDrawable != null) {
                this.foregroundDrawable.mutate().setColorFilter(i, Mode.SRC_IN);
            }
            if (this.foregroundBearingDrawable != null) {
                this.foregroundBearingDrawable.mutate().setColorFilter(i, Mode.SRC_IN);
            }
        }
        invalidate();
    }

    public final void setShadowDrawable(Drawable drawable) {
        setShadowDrawable(drawable, 0, 0, 0, 0);
    }

    public final void setShadowDrawable(Drawable drawable, int i, int i2, int i3, int i4) {
        if (drawable != null) {
            this.backgroundDrawable = drawable;
        }
        this.backgroundOffsetLeft = i;
        this.backgroundOffsetTop = i2;
        this.backgroundOffsetRight = i3;
        this.backgroundOffsetBottom = i4;
        invalidateBounds();
    }

    public final void setShadowDrawableTint(@ColorInt int i) {
        if (i != 0) {
            if (this.backgroundDrawable != null) {
                this.backgroundDrawable.mutate().setColorFilter(i, Mode.SRC_IN);
            } else {
                return;
            }
        }
        invalidate();
    }

    public final void setAccuracyTint(@ColorInt int i) {
        int alpha = this.accuracyPaint.getAlpha();
        this.accuracyPaint.setColor(i);
        this.accuracyPaint.setAlpha(alpha);
        invalidate();
    }

    public final void setAccuracyAlpha(@IntRange(from = 0, to = 255) int i) {
        this.accuracyPaint.setAlpha(i);
        invalidate();
    }

    private void invalidateBounds() {
        if (this.backgroundDrawable != null && this.foregroundDrawable != null && this.foregroundBearingDrawable != null) {
            int intrinsicWidth = this.backgroundDrawable.getIntrinsicWidth();
            int intrinsicHeight = this.backgroundDrawable.getIntrinsicHeight();
            int i = this.backgroundOffsetLeft - this.backgroundOffsetRight;
            int i2 = this.backgroundOffsetTop - this.backgroundOffsetBottom;
            this.backgroundBounds = new Rect(((-intrinsicWidth) / 2) + i, ((-intrinsicHeight) / 2) + i2, (intrinsicWidth / 2) + i, (intrinsicHeight / 2) + i2);
            this.backgroundDrawable.setBounds(this.backgroundBounds);
            intrinsicWidth = this.foregroundDrawable.getIntrinsicWidth();
            intrinsicHeight = this.foregroundDrawable.getIntrinsicHeight();
            this.foregroundBounds = new Rect((-intrinsicWidth) / 2, (-intrinsicHeight) / 2, intrinsicWidth / 2, intrinsicHeight / 2);
            this.foregroundDrawable.setBounds(this.foregroundBounds);
            this.foregroundBearingDrawable.setBounds(this.foregroundBounds);
            invalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.location != null && this.foregroundBounds != null && this.backgroundBounds != null && this.accuracyAnimator != null && this.screenLocation != null) {
            PointF pointF = this.screenLocation;
            float floatValue = (((Float) this.accuracyAnimator.getAnimatedValue()).floatValue() / ((float) this.projection.getMetersPerPixelAtLatitude(this.location.getLatitude()))) / 2.0f;
            float width = (float) (getWidth() / 2);
            if (floatValue <= width) {
                width = floatValue;
            }
            this.matrix.reset();
            this.projectedCoordinate[0] = 0.0f;
            this.projectedCoordinate[1] = 0.0f;
            this.camera.save();
            this.camera.rotate(this.tilt, 0.0f, 0.0f);
            this.camera.getMatrix(this.matrix);
            if (!(this.myBearingTrackingMode == 0 || this.directionAnimator == null)) {
                this.matrix.preRotate(((Float) this.directionAnimator.getAnimatedValue()).floatValue());
            }
            this.matrix.preTranslate(0.0f, this.contentPaddingY);
            this.matrix.postTranslate(pointF.x, pointF.y - this.contentPaddingY);
            canvas.concat(this.matrix);
            this.matrix.mapPoints(this.projectedCoordinate);
            this.projectedX = pointF.x - this.projectedCoordinate[0];
            this.projectedY = pointF.y - this.projectedCoordinate[1];
            this.camera.restore();
            canvas.drawCircle(0.0f, 0.0f, width, this.accuracyPaint);
            if (this.backgroundDrawable != null) {
                this.backgroundDrawable.draw(canvas);
            }
            if (this.myBearingTrackingMode == 0) {
                if (this.foregroundDrawable != null) {
                    this.foregroundDrawable.draw(canvas);
                }
            } else if (this.foregroundBearingDrawable != null && this.foregroundBounds != null) {
                this.foregroundBearingDrawable.draw(canvas);
            }
        }
    }

    public void setTilt(@FloatRange(from = 0.0d, to = 60.0d) double d) {
        this.tilt = (float) d;
        if (this.myLocationTrackingMode == 4) {
            this.mapboxMap.getUiSettings().setFocalPoint(new PointF(getCenterX(), getCenterY()));
        }
    }

    public void setBearing(double d) {
        this.bearing = (float) d;
    }

    public void setCameraPosition(CameraPosition cameraPosition) {
        setTilt(cameraPosition.tilt);
        setBearing(cameraPosition.bearing);
    }

    public void onResume() {
        if (this.myBearingTrackingMode == 4) {
            this.compassListener.onResume();
        }
        if (isEnabled()) {
            toggleGps(true);
        }
    }

    public void onPause() {
        this.compassListener.onPause();
        toggleGps(false);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.locationChangeAnimator != null) {
            this.locationChangeAnimator.cancel();
            this.locationChangeAnimator = null;
        }
        if (this.accuracyAnimator != null) {
            this.accuracyAnimator.cancel();
            this.accuracyAnimator = null;
        }
        if (this.directionAnimator != null) {
            this.directionAnimator.cancel();
            this.directionAnimator = null;
        }
        if (this.userLocationListener != null) {
            LocationServices.getLocationServices(getContext()).removeLocationListener(this.userLocationListener);
            this.userLocationListener = null;
        }
    }

    public void update() {
        if (isEnabled()) {
            this.myLocationBehavior.invalidate();
        } else {
            setVisibility(4);
        }
    }

    public void setMapboxMap(MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        this.projection = mapboxMap.getProjection();
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setVisibility(z ? 0 : 4);
        toggleGps(z);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putFloat("tilt", this.tilt);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.tilt = bundle.getFloat("tilt");
            parcelable = bundle.getParcelable("superState");
        }
        super.onRestoreInstanceState(parcelable);
    }

    private void toggleGps(boolean z) {
        LocationServices locationServices = LocationServices.getLocationServices(getContext());
        if (z) {
            Location lastLocation = locationServices.getLastLocation();
            if (lastLocation != null) {
                setLocation(lastLocation);
            }
            if (this.userLocationListener == null) {
                this.userLocationListener = new MyLocationView$GpsLocationListener(this);
            }
            locationServices.addLocationListener(this.userLocationListener);
        } else {
            this.location = null;
            locationServices.removeLocationListener(this.userLocationListener);
        }
        locationServices.toggleGPS(z);
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        if (location == null) {
            this.location = null;
            return;
        }
        this.location = location;
        this.myLocationBehavior.updateLatLng(location);
    }

    public void setMyBearingTrackingMode(int i) {
        this.myBearingTrackingMode = i;
        if (i == 4) {
            this.compassListener.onResume();
        } else {
            this.compassListener.onPause();
            if (this.myLocationTrackingMode == 4) {
                setCompass(0.0f);
            }
        }
        invalidate();
    }

    public void setMyLocationTrackingMode(int i) {
        this.myLocationBehavior = new MyLocationView$MyLocationBehaviorFactory(this, null).getBehavioralModel(i);
        if (this.location != null) {
            if (i == 4) {
                this.mapboxMap.easeCamera(CameraUpdateFactory.newLatLng(new LatLng(this.location)), 0, false, false, null);
            } else {
                this.latLng = null;
            }
            this.myLocationBehavior.updateLatLng(this.location);
        }
        this.myLocationTrackingMode = i;
        invalidate();
    }

    private void setCompass(float f) {
        float f2 = this.previousDirection;
        if (this.directionAnimator != null) {
            f2 = ((Float) this.directionAnimator.getAnimatedValue()).floatValue();
            this.directionAnimator.end();
            this.directionAnimator = null;
        }
        float f3 = f2 - f;
        if (f3 > 180.0f) {
            f += 360.0f;
        } else if (f3 < -180.0f) {
            f -= 360.0f;
        }
        this.previousDirection = f;
        this.directionAnimator = ValueAnimator.ofFloat(new float[]{f2, f});
        this.directionAnimator.setDuration(500);
        this.directionAnimator.addUpdateListener(this.invalidateSelfOnUpdateListener);
        this.directionAnimator.start();
    }

    public float getCenterX() {
        return (((getX() + ((float) getMeasuredWidth())) / 2.0f) + this.contentPaddingX) - this.projectedX;
    }

    public float getCenterY() {
        return (((getY() + ((float) getMeasuredHeight())) / 2.0f) + this.contentPaddingY) - this.projectedY;
    }

    public void setContentPadding(int[] iArr) {
        this.contentPaddingX = (float) ((iArr[0] - iArr[2]) / 2);
        this.contentPaddingY = (float) ((iArr[1] - iArr[3]) / 2);
    }
}
