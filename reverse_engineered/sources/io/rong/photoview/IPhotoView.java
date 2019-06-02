package io.rong.photoview;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView.ScaleType;

public interface IPhotoView {
    public static final float DEFAULT_MAX_SCALE = 3.0f;
    public static final float DEFAULT_MID_SCALE = 1.75f;
    public static final float DEFAULT_MIN_SCALE = 1.0f;
    public static final int DEFAULT_ZOOM_DURATION = 200;

    boolean canZoom();

    Matrix getDisplayMatrix();

    void getDisplayMatrix(Matrix matrix);

    RectF getDisplayRect();

    IPhotoView getIPhotoViewImplementation();

    @Deprecated
    float getMaxScale();

    float getMaximumScale();

    float getMediumScale();

    @Deprecated
    float getMidScale();

    @Deprecated
    float getMinScale();

    float getMinimumScale();

    PhotoViewAttacher$OnPhotoTapListener getOnPhotoTapListener();

    PhotoViewAttacher$OnViewTapListener getOnViewTapListener();

    float getScale();

    ScaleType getScaleType();

    Bitmap getVisibleRectangleBitmap();

    void setAllowParentInterceptOnEdge(boolean z);

    boolean setDisplayMatrix(Matrix matrix);

    @Deprecated
    void setMaxScale(float f);

    void setMaximumScale(float f);

    void setMediumScale(float f);

    @Deprecated
    void setMidScale(float f);

    @Deprecated
    void setMinScale(float f);

    void setMinimumScale(float f);

    void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener);

    void setOnLongClickListener(OnLongClickListener onLongClickListener);

    void setOnMatrixChangeListener(PhotoViewAttacher$OnMatrixChangedListener photoViewAttacher$OnMatrixChangedListener);

    void setOnPhotoTapListener(PhotoViewAttacher$OnPhotoTapListener photoViewAttacher$OnPhotoTapListener);

    void setOnScaleChangeListener(PhotoViewAttacher$OnScaleChangeListener photoViewAttacher$OnScaleChangeListener);

    void setOnSingleFlingListener(PhotoViewAttacher$OnSingleFlingListener photoViewAttacher$OnSingleFlingListener);

    void setOnViewTapListener(PhotoViewAttacher$OnViewTapListener photoViewAttacher$OnViewTapListener);

    void setPhotoViewRotation(float f);

    void setRotationBy(float f);

    void setRotationTo(float f);

    void setScale(float f);

    void setScale(float f, float f2, float f3, boolean z);

    void setScale(float f, boolean z);

    void setScaleLevels(float f, float f2, float f3);

    void setScaleType(ScaleType scaleType);

    void setZoomTransitionDuration(int i);

    void setZoomable(boolean z);
}
