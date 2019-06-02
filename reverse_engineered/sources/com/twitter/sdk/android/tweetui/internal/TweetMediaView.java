package com.twitter.sdk.android.tweetui.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.twitter.sdk.android.core.internal.util.AspectRatioImageView;

public class TweetMediaView extends AspectRatioImageView {
    /* renamed from: a */
    C4708a f16552a = new C4708a(null);

    /* renamed from: com.twitter.sdk.android.tweetui.internal.TweetMediaView$a */
    protected static class C4708a {
        /* renamed from: a */
        final Drawable f16551a;

        C4708a(Drawable drawable) {
            this.f16551a = drawable;
        }

        /* renamed from: a */
        protected void m18538a(ImageView imageView) {
            if (this.f16551a != null) {
                this.f16551a.setCallback(null);
                imageView.unscheduleDrawable(this.f16551a);
            }
        }

        /* renamed from: a */
        protected void m18536a(int i, int i2) {
            if (this.f16551a != null) {
                this.f16551a.setBounds(0, 0, i, i2);
            }
        }

        /* renamed from: a */
        protected void m18539a(int[] iArr) {
            if (this.f16551a != null && this.f16551a.isStateful()) {
                this.f16551a.setState(iArr);
            }
        }

        /* renamed from: a */
        protected void m18537a(Canvas canvas) {
            if (this.f16551a != null) {
                this.f16551a.draw(canvas);
            }
        }
    }

    public TweetMediaView(Context context) {
        super(context);
    }

    public TweetMediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f16552a.m18537a(canvas);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.f16552a.m18539a(getDrawableState());
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f16552a.m18536a(getMeasuredWidth(), getMeasuredHeight());
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f16552a.m18536a(i, i2);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (drawable == this.f16552a.f16551a) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public void setOverlayDrawable(Drawable drawable) {
        this.f16552a.m18538a((ImageView) this);
        if (drawable != null) {
            drawable.setCallback(this);
        }
        this.f16552a = new C4708a(drawable);
        this.f16552a.m18539a(getDrawableState());
        requestLayout();
    }
}
