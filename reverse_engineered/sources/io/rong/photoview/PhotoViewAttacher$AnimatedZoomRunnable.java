package io.rong.photoview;

import android.view.View;

class PhotoViewAttacher$AnimatedZoomRunnable implements Runnable {
    private final float mFocalX;
    private final float mFocalY;
    private final long mStartTime = System.currentTimeMillis();
    private final float mZoomEnd;
    private final float mZoomStart;
    final /* synthetic */ PhotoViewAttacher this$0;

    public PhotoViewAttacher$AnimatedZoomRunnable(PhotoViewAttacher photoViewAttacher, float f, float f2, float f3, float f4) {
        this.this$0 = photoViewAttacher;
        this.mFocalX = f3;
        this.mFocalY = f4;
        this.mZoomStart = f;
        this.mZoomEnd = f2;
    }

    public void run() {
        View imageView = this.this$0.getImageView();
        if (imageView != null) {
            float interpolate = interpolate();
            this.this$0.onScale((this.mZoomStart + ((this.mZoomEnd - this.mZoomStart) * interpolate)) / this.this$0.getScale(), this.mFocalX, this.mFocalY);
            if (interpolate < 1.0f) {
                Compat.postOnAnimation(imageView, this);
            }
        }
    }

    private float interpolate() {
        return PhotoViewAttacher.sInterpolator.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.mStartTime)) * 1.0f) / ((float) this.this$0.ZOOM_DURATION)));
    }
}
