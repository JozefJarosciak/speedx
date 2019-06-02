package io.rong.photoview;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.alibaba.fastjson.asm.Opcodes;
import io.rong.photoview.gestures.OnGestureListener;
import io.rong.photoview.gestures.VersionedGestureDetector;
import io.rong.photoview.log.LogManager;
import java.lang.ref.WeakReference;

public class PhotoViewAttacher implements OnTouchListener, OnGlobalLayoutListener, IPhotoView, OnGestureListener {
    private static final boolean DEBUG = Log.isLoggable(LOG_TAG, 3);
    static final int EDGE_BOTH = 2;
    static final int EDGE_LEFT = 0;
    static final int EDGE_NONE = -1;
    static final int EDGE_RIGHT = 1;
    private static final String LOG_TAG = "PhotoViewAttacher";
    static int SINGLE_TOUCH = 1;
    static final Interpolator sInterpolator = new AccelerateDecelerateInterpolator();
    int ZOOM_DURATION;
    private boolean mAllowParentInterceptOnEdge;
    private final Matrix mBaseMatrix;
    private float mBaseRotation;
    private boolean mBlockParentIntercept;
    private PhotoViewAttacher$FlingRunnable mCurrentFlingRunnable;
    private final RectF mDisplayRect;
    private final Matrix mDrawMatrix;
    private GestureDetector mGestureDetector;
    private WeakReference<ImageView> mImageView;
    private int mIvBottom;
    private int mIvLeft;
    private int mIvRight;
    private int mIvTop;
    private OnLongClickListener mLongClickListener;
    private PhotoViewAttacher$OnMatrixChangedListener mMatrixChangeListener;
    private final float[] mMatrixValues;
    private float mMaxScale;
    private float mMidScale;
    private float mMinScale;
    private PhotoViewAttacher$OnPhotoTapListener mPhotoTapListener;
    private PhotoViewAttacher$OnScaleChangeListener mScaleChangeListener;
    private io.rong.photoview.gestures.GestureDetector mScaleDragDetector;
    private ScaleType mScaleType;
    private int mScrollEdge;
    private PhotoViewAttacher$OnSingleFlingListener mSingleFlingListener;
    private final Matrix mSuppMatrix;
    private PhotoViewAttacher$OnViewTapListener mViewTapListener;
    private boolean mZoomEnabled;

    private static void checkZoomLevels(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("MinZoom has to be less than MidZoom");
        } else if (f2 >= f3) {
            throw new IllegalArgumentException("MidZoom has to be less than MaxZoom");
        }
    }

    private static boolean hasDrawable(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    private static boolean isSupportedScaleType(ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        switch (PhotoViewAttacher$2.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()]) {
            case 1:
                throw new IllegalArgumentException(scaleType.name() + " is not supported in PhotoView");
            default:
                return true;
        }
    }

    private static void setImageViewScaleTypeMatrix(ImageView imageView) {
        if (imageView != null && !(imageView instanceof IPhotoView) && !ScaleType.MATRIX.equals(imageView.getScaleType())) {
            imageView.setScaleType(ScaleType.MATRIX);
        }
    }

    public PhotoViewAttacher(ImageView imageView) {
        this(imageView, true);
    }

    public PhotoViewAttacher(ImageView imageView, boolean z) {
        this.ZOOM_DURATION = 200;
        this.mMinScale = 1.0f;
        this.mMidScale = 1.75f;
        this.mMaxScale = 3.0f;
        this.mAllowParentInterceptOnEdge = true;
        this.mBlockParentIntercept = false;
        this.mBaseMatrix = new Matrix();
        this.mDrawMatrix = new Matrix();
        this.mSuppMatrix = new Matrix();
        this.mDisplayRect = new RectF();
        this.mMatrixValues = new float[9];
        this.mScrollEdge = 2;
        this.mScaleType = ScaleType.FIT_CENTER;
        this.mImageView = new WeakReference(imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.setOnTouchListener(this);
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        setImageViewScaleTypeMatrix(imageView);
        if (!imageView.isInEditMode()) {
            this.mScaleDragDetector = VersionedGestureDetector.newInstance(imageView.getContext(), this);
            this.mGestureDetector = new GestureDetector(imageView.getContext(), new PhotoViewAttacher$1(this));
            this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
            this.mBaseRotation = 0.0f;
            setZoomable(z);
        }
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        if (onDoubleTapListener != null) {
            this.mGestureDetector.setOnDoubleTapListener(onDoubleTapListener);
        } else {
            this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
        }
    }

    public void setOnScaleChangeListener(PhotoViewAttacher$OnScaleChangeListener photoViewAttacher$OnScaleChangeListener) {
        this.mScaleChangeListener = photoViewAttacher$OnScaleChangeListener;
    }

    public void setOnSingleFlingListener(PhotoViewAttacher$OnSingleFlingListener photoViewAttacher$OnSingleFlingListener) {
        this.mSingleFlingListener = photoViewAttacher$OnSingleFlingListener;
    }

    public boolean canZoom() {
        return this.mZoomEnabled;
    }

    public void cleanup() {
        if (this.mImageView != null) {
            ImageView imageView = (ImageView) this.mImageView.get();
            if (imageView != null) {
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                imageView.setOnTouchListener(null);
                cancelFling();
            }
            if (this.mGestureDetector != null) {
                this.mGestureDetector.setOnDoubleTapListener(null);
            }
            this.mMatrixChangeListener = null;
            this.mPhotoTapListener = null;
            this.mViewTapListener = null;
            this.mImageView = null;
        }
    }

    public RectF getDisplayRect() {
        checkMatrixBounds();
        return getDisplayRect(getDrawMatrix());
    }

    public boolean setDisplayMatrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        ImageView imageView = getImageView();
        if (imageView == null || imageView.getDrawable() == null) {
            return false;
        }
        this.mSuppMatrix.set(matrix);
        setImageViewMatrix(getDrawMatrix());
        checkMatrixBounds();
        return true;
    }

    public void setBaseRotation(float f) {
        this.mBaseRotation = f % 360.0f;
        update();
        setRotationBy(this.mBaseRotation);
        checkAndDisplayMatrix();
    }

    public void setPhotoViewRotation(float f) {
        this.mSuppMatrix.setRotate(f % 360.0f);
        checkAndDisplayMatrix();
    }

    public void setRotationTo(float f) {
        this.mSuppMatrix.setRotate(f % 360.0f);
        checkAndDisplayMatrix();
    }

    public void setRotationBy(float f) {
        this.mSuppMatrix.postRotate(f % 360.0f);
        checkAndDisplayMatrix();
    }

    public ImageView getImageView() {
        ImageView imageView = null;
        if (this.mImageView != null) {
            imageView = (ImageView) this.mImageView.get();
        }
        if (imageView == null) {
            cleanup();
            LogManager.getLogger().i(LOG_TAG, "ImageView no longer exists. You should not use this PhotoViewAttacher any more.");
        }
        return imageView;
    }

    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    public float getMinimumScale() {
        return this.mMinScale;
    }

    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    public float getMediumScale() {
        return this.mMidScale;
    }

    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    public float getMaximumScale() {
        return this.mMaxScale;
    }

    public float getScale() {
        return (float) Math.sqrt((double) (((float) Math.pow((double) getValue(this.mSuppMatrix, 0), 2.0d)) + ((float) Math.pow((double) getValue(this.mSuppMatrix, 3), 2.0d))));
    }

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void onDrag(float f, float f2) {
        if (!this.mScaleDragDetector.isScaling()) {
            if (DEBUG) {
                LogManager.getLogger().d(LOG_TAG, String.format("onDrag: dx: %.2f. dy: %.2f", new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
            }
            ImageView imageView = getImageView();
            this.mSuppMatrix.postTranslate(f, f2);
            checkAndDisplayMatrix();
            ViewParent parent = imageView.getParent();
            if (!this.mAllowParentInterceptOnEdge || this.mScaleDragDetector.isScaling() || this.mBlockParentIntercept) {
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            } else if ((this.mScrollEdge == 2 || ((this.mScrollEdge == 0 && f >= 1.0f) || (this.mScrollEdge == 1 && f <= -1.0f))) && parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }
    }

    public void onFling(float f, float f2, float f3, float f4) {
        if (DEBUG) {
            LogManager.getLogger().d(LOG_TAG, "onFling. sX: " + f + " sY: " + f2 + " Vx: " + f3 + " Vy: " + f4);
        }
        ImageView imageView = getImageView();
        this.mCurrentFlingRunnable = new PhotoViewAttacher$FlingRunnable(this, imageView.getContext());
        this.mCurrentFlingRunnable.fling(getImageViewWidth(imageView), getImageViewHeight(imageView), (int) f3, (int) f4);
        imageView.post(this.mCurrentFlingRunnable);
    }

    public void onGlobalLayout() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (this.mZoomEnabled) {
            int top = imageView.getTop();
            int right = imageView.getRight();
            int bottom = imageView.getBottom();
            int left = imageView.getLeft();
            if (top != this.mIvTop || bottom != this.mIvBottom || left != this.mIvLeft || right != this.mIvRight) {
                updateBaseMatrix(imageView.getDrawable());
                this.mIvTop = top;
                this.mIvRight = right;
                this.mIvBottom = bottom;
                this.mIvLeft = left;
                return;
            }
            return;
        }
        updateBaseMatrix(imageView.getDrawable());
    }

    public void onScale(float f, float f2, float f3) {
        if (DEBUG) {
            LogManager.getLogger().d(LOG_TAG, String.format("onScale: scale: %.2f. fX: %.2f. fY: %.2f", new Object[]{Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3)}));
        }
        if (getScale() >= this.mMaxScale && f >= 1.0f) {
            return;
        }
        if (getScale() > this.mMinScale || f > 1.0f) {
            if (this.mScaleChangeListener != null) {
                this.mScaleChangeListener.onScaleChange(f, f2, f3);
            }
            this.mSuppMatrix.postScale(f, f, f2, f3);
            checkAndDisplayMatrix();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(android.view.View r9, android.view.MotionEvent r10) {
        /*
        r8 = this;
        r6 = 0;
        r7 = 1;
        r0 = r8.mZoomEnabled;
        if (r0 == 0) goto L_0x009f;
    L_0x0006:
        r0 = r9;
        r0 = (android.widget.ImageView) r0;
        r0 = hasDrawable(r0);
        if (r0 == 0) goto L_0x009f;
    L_0x000f:
        r0 = r9.getParent();
        r1 = r10.getAction();
        switch(r1) {
            case 0: goto L_0x005c;
            case 1: goto L_0x0072;
            case 2: goto L_0x001a;
            case 3: goto L_0x0072;
            default: goto L_0x001a;
        };
    L_0x001a:
        r0 = r6;
    L_0x001b:
        r1 = r8.mScaleDragDetector;
        if (r1 == 0) goto L_0x004e;
    L_0x001f:
        r0 = r8.mScaleDragDetector;
        r1 = r0.isScaling();
        r0 = r8.mScaleDragDetector;
        r3 = r0.isDragging();
        r0 = r8.mScaleDragDetector;
        r0 = r0.onTouchEvent(r10);
        if (r1 != 0) goto L_0x009b;
    L_0x0033:
        r1 = r8.mScaleDragDetector;
        r1 = r1.isScaling();
        if (r1 != 0) goto L_0x009b;
    L_0x003b:
        r2 = r7;
    L_0x003c:
        if (r3 != 0) goto L_0x009d;
    L_0x003e:
        r1 = r8.mScaleDragDetector;
        r1 = r1.isDragging();
        if (r1 != 0) goto L_0x009d;
    L_0x0046:
        r1 = r7;
    L_0x0047:
        if (r2 == 0) goto L_0x004c;
    L_0x0049:
        if (r1 == 0) goto L_0x004c;
    L_0x004b:
        r6 = r7;
    L_0x004c:
        r8.mBlockParentIntercept = r6;
    L_0x004e:
        r1 = r8.mGestureDetector;
        if (r1 == 0) goto L_0x005b;
    L_0x0052:
        r1 = r8.mGestureDetector;
        r1 = r1.onTouchEvent(r10);
        if (r1 == 0) goto L_0x005b;
    L_0x005a:
        r0 = r7;
    L_0x005b:
        return r0;
    L_0x005c:
        if (r0 == 0) goto L_0x0066;
    L_0x005e:
        r0.requestDisallowInterceptTouchEvent(r7);
    L_0x0061:
        r8.cancelFling();
        r0 = r6;
        goto L_0x001b;
    L_0x0066:
        r0 = io.rong.photoview.log.LogManager.getLogger();
        r1 = "PhotoViewAttacher";
        r2 = "onTouch getParent() returned null";
        r0.i(r1, r2);
        goto L_0x0061;
    L_0x0072:
        r0 = r8.getScale();
        r1 = r8.mMinScale;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 >= 0) goto L_0x001a;
    L_0x007c:
        r1 = r8.getDisplayRect();
        if (r1 == 0) goto L_0x001a;
    L_0x0082:
        r0 = new io.rong.photoview.PhotoViewAttacher$AnimatedZoomRunnable;
        r2 = r8.getScale();
        r3 = r8.mMinScale;
        r4 = r1.centerX();
        r5 = r1.centerY();
        r1 = r8;
        r0.<init>(r1, r2, r3, r4, r5);
        r9.post(r0);
        r0 = r7;
        goto L_0x001b;
    L_0x009b:
        r2 = r6;
        goto L_0x003c;
    L_0x009d:
        r1 = r6;
        goto L_0x0047;
    L_0x009f:
        r0 = r6;
        goto L_0x005b;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rong.photoview.PhotoViewAttacher.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.mAllowParentInterceptOnEdge = z;
    }

    @Deprecated
    public void setMinScale(float f) {
        setMinimumScale(f);
    }

    public void setMinimumScale(float f) {
        checkZoomLevels(f, this.mMidScale, this.mMaxScale);
        this.mMinScale = f;
    }

    @Deprecated
    public void setMidScale(float f) {
        setMediumScale(f);
    }

    public void setMediumScale(float f) {
        checkZoomLevels(this.mMinScale, f, this.mMaxScale);
        this.mMidScale = f;
    }

    @Deprecated
    public void setMaxScale(float f) {
        setMaximumScale(f);
    }

    public void setMaximumScale(float f) {
        checkZoomLevels(this.mMinScale, this.mMidScale, f);
        this.mMaxScale = f;
    }

    public void setScaleLevels(float f, float f2, float f3) {
        checkZoomLevels(f, f2, f3);
        this.mMinScale = f;
        this.mMidScale = f2;
        this.mMaxScale = f3;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.mLongClickListener = onLongClickListener;
    }

    public void setOnMatrixChangeListener(PhotoViewAttacher$OnMatrixChangedListener photoViewAttacher$OnMatrixChangedListener) {
        this.mMatrixChangeListener = photoViewAttacher$OnMatrixChangedListener;
    }

    public void setOnPhotoTapListener(PhotoViewAttacher$OnPhotoTapListener photoViewAttacher$OnPhotoTapListener) {
        this.mPhotoTapListener = photoViewAttacher$OnPhotoTapListener;
    }

    public PhotoViewAttacher$OnPhotoTapListener getOnPhotoTapListener() {
        return this.mPhotoTapListener;
    }

    public void setOnViewTapListener(PhotoViewAttacher$OnViewTapListener photoViewAttacher$OnViewTapListener) {
        this.mViewTapListener = photoViewAttacher$OnViewTapListener;
    }

    public PhotoViewAttacher$OnViewTapListener getOnViewTapListener() {
        return this.mViewTapListener;
    }

    public void setScale(float f) {
        setScale(f, false);
    }

    public void setScale(float f, boolean z) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            setScale(f, (float) (imageView.getRight() / 2), (float) (imageView.getBottom() / 2), z);
        }
    }

    public void setScale(float f, float f2, float f3, boolean z) {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (f < this.mMinScale || f > this.mMaxScale) {
            LogManager.getLogger().i(LOG_TAG, "Scale must be within the range of minScale and maxScale");
        } else if (z) {
            imageView.post(new PhotoViewAttacher$AnimatedZoomRunnable(this, getScale(), f, f2, f3));
        } else {
            this.mSuppMatrix.setScale(f, f, f2, f3);
            checkAndDisplayMatrix();
        }
    }

    public void setScaleType(ScaleType scaleType) {
        if (isSupportedScaleType(scaleType) && scaleType != this.mScaleType) {
            this.mScaleType = scaleType;
            update();
        }
    }

    public void setZoomable(boolean z) {
        this.mZoomEnabled = z;
        update();
    }

    public void update() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (this.mZoomEnabled) {
            setImageViewScaleTypeMatrix(imageView);
            updateBaseMatrix(imageView.getDrawable());
            return;
        }
        resetMatrix();
    }

    public Matrix getDisplayMatrix() {
        return new Matrix(getDrawMatrix());
    }

    public void getDisplayMatrix(Matrix matrix) {
        matrix.set(getDrawMatrix());
    }

    public Matrix getDrawMatrix() {
        this.mDrawMatrix.set(this.mBaseMatrix);
        this.mDrawMatrix.postConcat(this.mSuppMatrix);
        return this.mDrawMatrix;
    }

    private void cancelFling() {
        if (this.mCurrentFlingRunnable != null) {
            this.mCurrentFlingRunnable.cancelFling();
            this.mCurrentFlingRunnable = null;
        }
    }

    private void checkAndDisplayMatrix() {
        if (checkMatrixBounds()) {
            setImageViewMatrix(getDrawMatrix());
        }
    }

    private void checkImageViewScaleType() {
        ImageView imageView = getImageView();
        if (imageView != null && !(imageView instanceof IPhotoView) && !ScaleType.MATRIX.equals(imageView.getScaleType())) {
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher");
        }
    }

    private boolean checkMatrixBounds() {
        float f = 0.0f;
        ImageView imageView = getImageView();
        if (imageView == null) {
            return false;
        }
        RectF displayRect = getDisplayRect(getDrawMatrix());
        if (displayRect == null) {
            return false;
        }
        float height = displayRect.height();
        float width = displayRect.width();
        int imageViewHeight = getImageViewHeight(imageView);
        if (height <= ((float) imageViewHeight)) {
            switch (PhotoViewAttacher$2.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                case 2:
                    height = -displayRect.top;
                    break;
                case 3:
                    height = (((float) imageViewHeight) - height) - displayRect.top;
                    break;
                default:
                    height = ((((float) imageViewHeight) - height) / 2.0f) - displayRect.top;
                    break;
            }
        } else if (displayRect.top > 0.0f) {
            height = -displayRect.top;
        } else if (displayRect.bottom < ((float) imageViewHeight)) {
            height = ((float) imageViewHeight) - displayRect.bottom;
        } else {
            height = 0.0f;
        }
        int imageViewWidth = getImageViewWidth(imageView);
        if (width <= ((float) imageViewWidth)) {
            switch (PhotoViewAttacher$2.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                case 2:
                    f = -displayRect.left;
                    break;
                case 3:
                    f = (((float) imageViewWidth) - width) - displayRect.left;
                    break;
                default:
                    f = ((((float) imageViewWidth) - width) / 2.0f) - displayRect.left;
                    break;
            }
            this.mScrollEdge = 2;
        } else if (displayRect.left > 0.0f) {
            this.mScrollEdge = 0;
            f = -displayRect.left;
        } else if (displayRect.right < ((float) imageViewWidth)) {
            f = ((float) imageViewWidth) - displayRect.right;
            this.mScrollEdge = 1;
        } else {
            this.mScrollEdge = -1;
        }
        this.mSuppMatrix.postTranslate(f, height);
        return true;
    }

    private RectF getDisplayRect(Matrix matrix) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                this.mDisplayRect.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
                matrix.mapRect(this.mDisplayRect);
                return this.mDisplayRect;
            }
        }
        return null;
    }

    public Bitmap getVisibleRectangleBitmap() {
        ImageView imageView = getImageView();
        return imageView == null ? null : imageView.getDrawingCache();
    }

    public void setZoomTransitionDuration(int i) {
        if (i < 0) {
            i = 200;
        }
        this.ZOOM_DURATION = i;
    }

    public IPhotoView getIPhotoViewImplementation() {
        return this;
    }

    private float getValue(Matrix matrix, int i) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i];
    }

    private void resetMatrix() {
        this.mSuppMatrix.reset();
        setRotationBy(this.mBaseRotation);
        setImageViewMatrix(getDrawMatrix());
        checkMatrixBounds();
    }

    private void setImageViewMatrix(Matrix matrix) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            checkImageViewScaleType();
            imageView.setImageMatrix(matrix);
            if (this.mMatrixChangeListener != null) {
                RectF displayRect = getDisplayRect(matrix);
                if (displayRect != null) {
                    this.mMatrixChangeListener.onMatrixChanged(displayRect);
                }
            }
        }
    }

    private void updateBaseMatrix(Drawable drawable) {
        ImageView imageView = getImageView();
        if (imageView != null && drawable != null) {
            float imageViewWidth = (float) getImageViewWidth(imageView);
            float imageViewHeight = (float) getImageViewHeight(imageView);
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.mBaseMatrix.reset();
            float f = imageViewWidth / ((float) intrinsicWidth);
            float f2 = imageViewHeight / ((float) intrinsicHeight);
            if (this.mScaleType != ScaleType.CENTER) {
                if (this.mScaleType != ScaleType.CENTER_CROP) {
                    if (this.mScaleType != ScaleType.CENTER_INSIDE) {
                        RectF rectF = new RectF(0.0f, 0.0f, (float) intrinsicWidth, (float) intrinsicHeight);
                        RectF rectF2 = new RectF(0.0f, 0.0f, imageViewWidth, imageViewHeight);
                        if (((int) this.mBaseRotation) % Opcodes.GETFIELD != 0) {
                            rectF = new RectF(0.0f, 0.0f, (float) intrinsicHeight, (float) intrinsicWidth);
                        }
                        switch (PhotoViewAttacher$2.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
                            case 2:
                                this.mBaseMatrix.setRectToRect(rectF, rectF2, ScaleToFit.START);
                                break;
                            case 3:
                                this.mBaseMatrix.setRectToRect(rectF, rectF2, ScaleToFit.END);
                                break;
                            case 4:
                                this.mBaseMatrix.setRectToRect(rectF, rectF2, ScaleToFit.CENTER);
                                break;
                            case 5:
                                this.mBaseMatrix.setRectToRect(rectF, rectF2, ScaleToFit.FILL);
                                break;
                            default:
                                break;
                        }
                    }
                    f = Math.min(1.0f, Math.min(f, f2));
                    this.mBaseMatrix.postScale(f, f);
                    this.mBaseMatrix.postTranslate((imageViewWidth - (((float) intrinsicWidth) * f)) / 2.0f, (imageViewHeight - (f * ((float) intrinsicHeight))) / 2.0f);
                } else {
                    f = Math.max(f, f2);
                    this.mBaseMatrix.postScale(f, f);
                    this.mBaseMatrix.postTranslate((imageViewWidth - (((float) intrinsicWidth) * f)) / 2.0f, (imageViewHeight - (f * ((float) intrinsicHeight))) / 2.0f);
                }
            } else {
                this.mBaseMatrix.postTranslate((imageViewWidth - ((float) intrinsicWidth)) / 2.0f, (imageViewHeight - ((float) intrinsicHeight)) / 2.0f);
            }
            resetMatrix();
        }
    }

    private int getImageViewWidth(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    private int getImageViewHeight(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }
}
