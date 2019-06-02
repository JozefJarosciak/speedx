package io.rong.photoview.gestures;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import io.rong.photoview.Compat;

@TargetApi(5)
public class EclairGestureDetector extends CupcakeGestureDetector {
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = -1;
    private int mActivePointerIndex = 0;

    public EclairGestureDetector(Context context) {
        super(context);
    }

    float getActiveX(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.mActivePointerIndex);
        } catch (Exception e) {
            return motionEvent.getX();
        }
    }

    float getActiveY(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.mActivePointerIndex);
        } catch (Exception e) {
            return motionEvent.getY();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        int i = 0;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.mActivePointerId = motionEvent.getPointerId(0);
                break;
            case 1:
            case 3:
                this.mActivePointerId = -1;
                break;
            case 6:
                int pointerIndex = Compat.getPointerIndex(motionEvent.getAction());
                if (motionEvent.getPointerId(pointerIndex) == this.mActivePointerId) {
                    if (pointerIndex == 0) {
                        pointerIndex = z;
                    } else {
                        pointerIndex = 0;
                    }
                    this.mActivePointerId = motionEvent.getPointerId(pointerIndex);
                    this.mLastTouchX = motionEvent.getX(pointerIndex);
                    this.mLastTouchY = motionEvent.getY(pointerIndex);
                    break;
                }
                break;
        }
        if (this.mActivePointerId != -1) {
            i = this.mActivePointerId;
        }
        this.mActivePointerIndex = motionEvent.findPointerIndex(i);
        try {
            z = super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
        }
        return z;
    }
}
