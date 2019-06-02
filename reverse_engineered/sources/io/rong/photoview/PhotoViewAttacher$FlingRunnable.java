package io.rong.photoview;

import android.content.Context;
import android.graphics.RectF;
import android.view.View;
import io.rong.photoview.log.LogManager;
import io.rong.photoview.scrollerproxy.ScrollerProxy;

class PhotoViewAttacher$FlingRunnable implements Runnable {
    private int mCurrentX;
    private int mCurrentY;
    private final ScrollerProxy mScroller;
    final /* synthetic */ PhotoViewAttacher this$0;

    public PhotoViewAttacher$FlingRunnable(PhotoViewAttacher photoViewAttacher, Context context) {
        this.this$0 = photoViewAttacher;
        this.mScroller = ScrollerProxy.getScroller(context);
    }

    public void cancelFling() {
        if (PhotoViewAttacher.access$200()) {
            LogManager.getLogger().mo6647d("PhotoViewAttacher", "Cancel Fling");
        }
        this.mScroller.forceFinished(true);
    }

    public void fling(int i, int i2, int i3, int i4) {
        RectF displayRect = this.this$0.getDisplayRect();
        if (displayRect != null) {
            int round;
            int i5;
            int round2;
            int i6;
            int round3 = Math.round(-displayRect.left);
            if (((float) i) < displayRect.width()) {
                round = Math.round(displayRect.width() - ((float) i));
                i5 = 0;
            } else {
                round = round3;
                i5 = round3;
            }
            int round4 = Math.round(-displayRect.top);
            if (((float) i2) < displayRect.height()) {
                round2 = Math.round(displayRect.height() - ((float) i2));
                i6 = 0;
            } else {
                round2 = round4;
                i6 = round4;
            }
            this.mCurrentX = round3;
            this.mCurrentY = round4;
            if (PhotoViewAttacher.access$200()) {
                LogManager.getLogger().mo6647d("PhotoViewAttacher", "fling. StartX:" + round3 + " StartY:" + round4 + " MaxX:" + round + " MaxY:" + round2);
            }
            if (round3 != round || round4 != round2) {
                this.mScroller.fling(round3, round4, i3, i4, i5, round, i6, round2, 0, 0);
            }
        }
    }

    public void run() {
        if (!this.mScroller.isFinished()) {
            View imageView = this.this$0.getImageView();
            if (imageView != null && this.mScroller.computeScrollOffset()) {
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (PhotoViewAttacher.access$200()) {
                    LogManager.getLogger().mo6647d("PhotoViewAttacher", "fling run(). CurrentX:" + this.mCurrentX + " CurrentY:" + this.mCurrentY + " NewX:" + currX + " NewY:" + currY);
                }
                PhotoViewAttacher.access$300(this.this$0).postTranslate((float) (this.mCurrentX - currX), (float) (this.mCurrentY - currY));
                PhotoViewAttacher.access$400(this.this$0, this.this$0.getDrawMatrix());
                this.mCurrentX = currX;
                this.mCurrentY = currY;
                Compat.postOnAnimation(imageView, this);
            }
        }
    }
}
