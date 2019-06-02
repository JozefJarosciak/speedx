package io.rong.photoview;

import android.support.v4.view.MotionEventCompat;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

class PhotoViewAttacher$1 extends SimpleOnGestureListener {
    final /* synthetic */ PhotoViewAttacher this$0;

    PhotoViewAttacher$1(PhotoViewAttacher photoViewAttacher) {
        this.this$0 = photoViewAttacher;
    }

    public void onLongPress(MotionEvent motionEvent) {
        if (PhotoViewAttacher.access$000(this.this$0) != null) {
            PhotoViewAttacher.access$000(this.this$0).onLongClick(this.this$0.getImageView());
        }
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (PhotoViewAttacher.access$100(this.this$0) == null || this.this$0.getScale() > 1.0f || MotionEventCompat.getPointerCount(motionEvent) > PhotoViewAttacher.SINGLE_TOUCH || MotionEventCompat.getPointerCount(motionEvent2) > PhotoViewAttacher.SINGLE_TOUCH) {
            return false;
        }
        return PhotoViewAttacher.access$100(this.this$0).onFling(motionEvent, motionEvent2, f, f2);
    }
}
