package io.rong.imkit.widget;

import android.text.Layout;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.Touch;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;

public class LinkTextViewMovementMethod extends LinkMovementMethod {
    private long mLastActionDownTime;
    private ILinkClickListener mListener;

    public LinkTextViewMovementMethod(ILinkClickListener iLinkClickListener) {
        this.mListener = iLinkClickListener;
    }

    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1 && action != 0) {
            return Touch.onTouchEvent(textView, spannable, motionEvent);
        }
        int x = (((int) motionEvent.getX()) - textView.getTotalPaddingLeft()) + textView.getScrollX();
        int y = (((int) motionEvent.getY()) - textView.getTotalPaddingTop()) + textView.getScrollY();
        Layout layout = textView.getLayout();
        x = layout.getOffsetForHorizontal(layout.getLineForVertical(y), (float) x);
        ClickableSpan[] clickableSpanArr = (ClickableSpan[]) spannable.getSpans(x, x, ClickableSpan.class);
        if (clickableSpanArr.length != 0) {
            if (action == 1) {
                if (System.currentTimeMillis() - this.mLastActionDownTime > ((long) ViewConfiguration.getLongPressTimeout())) {
                    return true;
                }
                String str = null;
                if (clickableSpanArr[0] instanceof URLSpan) {
                    str = ((URLSpan) clickableSpanArr[0]).getURL();
                }
                if (this.mListener != null && this.mListener.onLinkClick(r1)) {
                    return true;
                }
                clickableSpanArr[0].onClick(textView);
            } else if (action == 0) {
                this.mLastActionDownTime = System.currentTimeMillis();
            }
            return true;
        }
        Touch.onTouchEvent(textView, spannable, motionEvent);
        return false;
    }
}
