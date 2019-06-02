package com.beastbikes.android.widget.multiimageselector.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.OverScroller;
import android.widget.Scroller;

public class TouchImageView extends ImageView {
    /* renamed from: A */
    private OnTouchListener f12726A = null;
    /* renamed from: B */
    private C2725e f12727B = null;
    /* renamed from: a */
    private float f12728a;
    /* renamed from: b */
    private Matrix f12729b;
    /* renamed from: c */
    private Matrix f12730c;
    /* renamed from: d */
    private State f12731d;
    /* renamed from: e */
    private float f12732e;
    /* renamed from: f */
    private float f12733f;
    /* renamed from: g */
    private float f12734g;
    /* renamed from: h */
    private float f12735h;
    /* renamed from: i */
    private float[] f12736i;
    /* renamed from: j */
    private Context f12737j;
    /* renamed from: k */
    private C2723c f12738k;
    /* renamed from: l */
    private ScaleType f12739l;
    /* renamed from: m */
    private boolean f12740m;
    /* renamed from: n */
    private boolean f12741n;
    /* renamed from: o */
    private C2728h f12742o;
    /* renamed from: p */
    private int f12743p;
    /* renamed from: q */
    private int f12744q;
    /* renamed from: r */
    private int f12745r;
    /* renamed from: s */
    private int f12746s;
    /* renamed from: t */
    private float f12747t;
    /* renamed from: u */
    private float f12748u;
    /* renamed from: v */
    private float f12749v;
    /* renamed from: w */
    private float f12750w;
    /* renamed from: x */
    private ScaleGestureDetector f12751x;
    /* renamed from: y */
    private GestureDetector f12752y;
    /* renamed from: z */
    private OnDoubleTapListener f12753z = null;

    /* renamed from: com.beastbikes.android.widget.multiimageselector.utils.TouchImageView$1 */
    static /* synthetic */ class C27201 {
        /* renamed from: a */
        static final /* synthetic */ int[] f12698a = new int[ScaleType.values().length];

        static {
            try {
                f12698a[ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f12698a[ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f12698a[ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f12698a[ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f12698a[ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private enum State {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    @TargetApi(9)
    /* renamed from: com.beastbikes.android.widget.multiimageselector.utils.TouchImageView$a */
    private class C2721a {
        /* renamed from: a */
        Scroller f12699a;
        /* renamed from: b */
        OverScroller f12700b;
        /* renamed from: c */
        boolean f12701c;
        /* renamed from: d */
        final /* synthetic */ TouchImageView f12702d;

        public C2721a(TouchImageView touchImageView, Context context) {
            this.f12702d = touchImageView;
            if (VERSION.SDK_INT < 9) {
                this.f12701c = true;
                this.f12699a = new Scroller(context);
                return;
            }
            this.f12701c = false;
            this.f12700b = new OverScroller(context);
        }

        /* renamed from: a */
        public void m13405a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.f12701c) {
                this.f12699a.fling(i, i2, i3, i4, i5, i6, i7, i8);
            } else {
                this.f12700b.fling(i, i2, i3, i4, i5, i6, i7, i8);
            }
        }

        /* renamed from: a */
        public void m13406a(boolean z) {
            if (this.f12701c) {
                this.f12699a.forceFinished(z);
            } else {
                this.f12700b.forceFinished(z);
            }
        }

        /* renamed from: a */
        public boolean m13407a() {
            if (this.f12701c) {
                return this.f12699a.isFinished();
            }
            return this.f12700b.isFinished();
        }

        /* renamed from: b */
        public boolean m13408b() {
            if (this.f12701c) {
                return this.f12699a.computeScrollOffset();
            }
            this.f12700b.computeScrollOffset();
            return this.f12700b.computeScrollOffset();
        }

        /* renamed from: c */
        public int m13409c() {
            if (this.f12701c) {
                return this.f12699a.getCurrX();
            }
            return this.f12700b.getCurrX();
        }

        /* renamed from: d */
        public int m13410d() {
            if (this.f12701c) {
                return this.f12699a.getCurrY();
            }
            return this.f12700b.getCurrY();
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.utils.TouchImageView$b */
    private class C2722b implements Runnable {
        /* renamed from: a */
        final /* synthetic */ TouchImageView f12703a;
        /* renamed from: b */
        private long f12704b;
        /* renamed from: c */
        private float f12705c;
        /* renamed from: d */
        private float f12706d;
        /* renamed from: e */
        private float f12707e;
        /* renamed from: f */
        private float f12708f;
        /* renamed from: g */
        private boolean f12709g;
        /* renamed from: h */
        private AccelerateDecelerateInterpolator f12710h = new AccelerateDecelerateInterpolator();
        /* renamed from: i */
        private PointF f12711i;
        /* renamed from: j */
        private PointF f12712j;

        C2722b(TouchImageView touchImageView, float f, float f2, float f3, boolean z) {
            this.f12703a = touchImageView;
            touchImageView.setState(State.ANIMATE_ZOOM);
            this.f12704b = System.currentTimeMillis();
            this.f12705c = touchImageView.f12728a;
            this.f12706d = f;
            this.f12709g = z;
            PointF a = touchImageView.m13419a(f2, f3, false);
            this.f12707e = a.x;
            this.f12708f = a.y;
            this.f12711i = touchImageView.m13418a(this.f12707e, this.f12708f);
            this.f12712j = new PointF((float) (touchImageView.f12743p / 2), (float) (touchImageView.f12744q / 2));
        }

        public void run() {
            float a = m13411a();
            this.f12703a.m13424a(m13413b(a), this.f12707e, this.f12708f, this.f12709g);
            m13412a(a);
            this.f12703a.m13439e();
            this.f12703a.setImageMatrix(this.f12703a.f12729b);
            if (this.f12703a.f12727B != null) {
                this.f12703a.f12727B.m13415a();
            }
            if (a < 1.0f) {
                this.f12703a.m13430a((Runnable) this);
            } else {
                this.f12703a.setState(State.NONE);
            }
        }

        /* renamed from: a */
        private void m13412a(float f) {
            float f2 = this.f12711i.x + ((this.f12712j.x - this.f12711i.x) * f);
            float f3 = this.f12711i.y + ((this.f12712j.y - this.f12711i.y) * f);
            PointF a = this.f12703a.m13418a(this.f12707e, this.f12708f);
            this.f12703a.f12729b.postTranslate(f2 - a.x, f3 - a.y);
        }

        /* renamed from: a */
        private float m13411a() {
            return this.f12710h.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.f12704b)) / 500.0f));
        }

        /* renamed from: b */
        private double m13413b(float f) {
            return ((double) (this.f12705c + ((this.f12706d - this.f12705c) * f))) / ((double) this.f12703a.f12728a);
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.utils.TouchImageView$c */
    private class C2723c implements Runnable {
        /* renamed from: a */
        C2721a f12713a;
        /* renamed from: b */
        int f12714b;
        /* renamed from: c */
        int f12715c;
        /* renamed from: d */
        final /* synthetic */ TouchImageView f12716d;

        C2723c(TouchImageView touchImageView, int i, int i2) {
            int i3;
            int i4;
            int k;
            int i5;
            this.f12716d = touchImageView;
            touchImageView.setState(State.FLING);
            this.f12713a = new C2721a(touchImageView, touchImageView.f12737j);
            touchImageView.f12729b.getValues(touchImageView.f12736i);
            int i6 = (int) touchImageView.f12736i[2];
            int i7 = (int) touchImageView.f12736i[5];
            if (touchImageView.getImageWidth() > ((float) touchImageView.f12743p)) {
                i3 = touchImageView.f12743p - ((int) touchImageView.getImageWidth());
                i4 = 0;
            } else {
                i4 = i6;
                i3 = i6;
            }
            if (touchImageView.getImageHeight() > ((float) touchImageView.f12744q)) {
                k = touchImageView.f12744q - ((int) touchImageView.getImageHeight());
                i5 = 0;
            } else {
                i5 = i7;
                k = i7;
            }
            this.f12713a.m13405a(i6, i7, i, i2, i3, i4, k, i5);
            this.f12714b = i6;
            this.f12715c = i7;
        }

        /* renamed from: a */
        public void m13414a() {
            if (this.f12713a != null) {
                this.f12716d.setState(State.NONE);
                this.f12713a.m13406a(true);
            }
        }

        public void run() {
            if (this.f12716d.f12727B != null) {
                this.f12716d.f12727B.m13415a();
            }
            if (this.f12713a.m13407a()) {
                this.f12713a = null;
            } else if (this.f12713a.m13408b()) {
                int c = this.f12713a.m13409c();
                int d = this.f12713a.m13410d();
                int i = c - this.f12714b;
                int i2 = d - this.f12715c;
                this.f12714b = c;
                this.f12715c = d;
                this.f12716d.f12729b.postTranslate((float) i, (float) i2);
                this.f12716d.m13437d();
                this.f12716d.setImageMatrix(this.f12716d.f12729b);
                this.f12716d.m13430a((Runnable) this);
            }
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.utils.TouchImageView$d */
    private class C2724d extends SimpleOnGestureListener {
        /* renamed from: a */
        final /* synthetic */ TouchImageView f12717a;

        private C2724d(TouchImageView touchImageView) {
            this.f12717a = touchImageView;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (this.f12717a.f12753z != null) {
                return this.f12717a.f12753z.onSingleTapConfirmed(motionEvent);
            }
            return this.f12717a.performClick();
        }

        public void onLongPress(MotionEvent motionEvent) {
            this.f12717a.performLongClick();
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.f12717a.f12738k != null) {
                this.f12717a.f12738k.m13414a();
            }
            this.f12717a.f12738k = new C2723c(this.f12717a, (int) f, (int) f2);
            this.f12717a.m13430a(this.f12717a.f12738k);
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            boolean onDoubleTap;
            if (this.f12717a.f12753z != null) {
                onDoubleTap = this.f12717a.f12753z.onDoubleTap(motionEvent);
            } else {
                onDoubleTap = false;
            }
            if (this.f12717a.f12731d != State.NONE) {
                return onDoubleTap;
            }
            this.f12717a.m13430a(new C2722b(this.f12717a, this.f12717a.f12728a == this.f12717a.f12732e ? this.f12717a.f12733f : this.f12717a.f12732e, motionEvent.getX(), motionEvent.getY(), false));
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (this.f12717a.f12753z != null) {
                return this.f12717a.f12753z.onDoubleTapEvent(motionEvent);
            }
            return false;
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.utils.TouchImageView$e */
    public interface C2725e {
        /* renamed from: a */
        void m13415a();
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.utils.TouchImageView$f */
    private class C2726f implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ TouchImageView f12718a;
        /* renamed from: b */
        private PointF f12719b;

        private C2726f(TouchImageView touchImageView) {
            this.f12718a = touchImageView;
            this.f12719b = new PointF();
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.f12718a.f12751x.onTouchEvent(motionEvent);
            this.f12718a.f12752y.onTouchEvent(motionEvent);
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            if (this.f12718a.f12731d == State.NONE || this.f12718a.f12731d == State.DRAG || this.f12718a.f12731d == State.FLING) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.f12719b.set(pointF);
                        if (this.f12718a.f12738k != null) {
                            this.f12718a.f12738k.m13414a();
                        }
                        this.f12718a.setState(State.DRAG);
                        break;
                    case 1:
                    case 6:
                        this.f12718a.setState(State.NONE);
                        break;
                    case 2:
                        if (this.f12718a.f12731d == State.DRAG) {
                            float f = pointF.y - this.f12719b.y;
                            this.f12718a.f12729b.postTranslate(this.f12718a.m13433c(pointF.x - this.f12719b.x, (float) this.f12718a.f12743p, this.f12718a.getImageWidth()), this.f12718a.m13433c(f, (float) this.f12718a.f12744q, this.f12718a.getImageHeight()));
                            this.f12718a.m13437d();
                            this.f12719b.set(pointF.x, pointF.y);
                            break;
                        }
                        break;
                }
            }
            this.f12718a.setImageMatrix(this.f12718a.f12729b);
            if (this.f12718a.f12726A != null) {
                this.f12718a.f12726A.onTouch(view, motionEvent);
            }
            if (this.f12718a.f12727B != null) {
                this.f12718a.f12727B.m13415a();
            }
            return true;
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.utils.TouchImageView$g */
    private class C2727g extends SimpleOnScaleGestureListener {
        /* renamed from: a */
        final /* synthetic */ TouchImageView f12720a;

        private C2727g(TouchImageView touchImageView) {
            this.f12720a = touchImageView;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            this.f12720a.setState(State.ZOOM);
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            this.f12720a.m13424a((double) scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), true);
            if (this.f12720a.f12727B != null) {
                this.f12720a.f12727B.m13415a();
            }
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            super.onScaleEnd(scaleGestureDetector);
            this.f12720a.setState(State.NONE);
            boolean z = false;
            float d = this.f12720a.f12728a;
            if (this.f12720a.f12728a > this.f12720a.f12733f) {
                d = this.f12720a.f12733f;
                z = true;
            } else if (this.f12720a.f12728a < this.f12720a.f12732e) {
                d = this.f12720a.f12732e;
                z = true;
            }
            if (z) {
                this.f12720a.m13430a(new C2722b(this.f12720a, d, (float) (this.f12720a.f12743p / 2), (float) (this.f12720a.f12744q / 2), true));
            }
        }
    }

    /* renamed from: com.beastbikes.android.widget.multiimageselector.utils.TouchImageView$h */
    private class C2728h {
        /* renamed from: a */
        public float f12721a;
        /* renamed from: b */
        public float f12722b;
        /* renamed from: c */
        public float f12723c;
        /* renamed from: d */
        public ScaleType f12724d;
        /* renamed from: e */
        final /* synthetic */ TouchImageView f12725e;

        public C2728h(TouchImageView touchImageView, float f, float f2, float f3, ScaleType scaleType) {
            this.f12725e = touchImageView;
            this.f12721a = f;
            this.f12722b = f2;
            this.f12723c = f3;
            this.f12724d = scaleType;
        }
    }

    public TouchImageView(Context context) {
        super(context);
        m13426a(context);
    }

    public TouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m13426a(context);
    }

    public TouchImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13426a(context);
    }

    /* renamed from: a */
    private void m13426a(Context context) {
        super.setClickable(true);
        this.f12737j = context;
        this.f12751x = new ScaleGestureDetector(context, new C2727g());
        this.f12752y = new GestureDetector(context, new C2724d());
        this.f12729b = new Matrix();
        this.f12730c = new Matrix();
        this.f12736i = new float[9];
        this.f12728a = 1.0f;
        if (this.f12739l == null) {
            this.f12739l = ScaleType.FIT_CENTER;
        }
        this.f12732e = 1.0f;
        this.f12733f = 3.0f;
        this.f12734g = 0.75f * this.f12732e;
        this.f12735h = 1.25f * this.f12733f;
        setImageMatrix(this.f12729b);
        setScaleType(ScaleType.MATRIX);
        setState(State.NONE);
        this.f12741n = false;
        super.setOnTouchListener(new C2726f());
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.f12726A = onTouchListener;
    }

    public void setOnTouchImageViewListener(C2725e c2725e) {
        this.f12727B = c2725e;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.f12753z = onDoubleTapListener;
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        m13435c();
        m13441f();
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        m13435c();
        m13441f();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        m13435c();
        m13441f();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        m13435c();
        m13441f();
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType == ScaleType.FIT_START || scaleType == ScaleType.FIT_END) {
            throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
        } else if (scaleType == ScaleType.MATRIX) {
            super.setScaleType(ScaleType.MATRIX);
        } else {
            this.f12739l = scaleType;
            if (this.f12741n) {
                setZoom(this);
            }
        }
    }

    public ScaleType getScaleType() {
        return this.f12739l;
    }

    /* renamed from: a */
    public boolean m13457a() {
        return this.f12728a != 1.0f;
    }

    public RectF getZoomedRect() {
        if (this.f12739l == ScaleType.FIT_XY) {
            throw new UnsupportedOperationException("getZoomedRect() not supported with FIT_XY");
        }
        PointF a = m13419a(0.0f, 0.0f, true);
        PointF a2 = m13419a((float) this.f12743p, (float) this.f12744q, true);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        return new RectF(a.x / intrinsicWidth, a.y / intrinsicHeight, a2.x / intrinsicWidth, a2.y / intrinsicHeight);
    }

    /* renamed from: c */
    private void m13435c() {
        if (this.f12729b != null && this.f12744q != 0 && this.f12743p != 0) {
            this.f12729b.getValues(this.f12736i);
            this.f12730c.setValues(this.f12736i);
            this.f12750w = this.f12748u;
            this.f12749v = this.f12747t;
            this.f12746s = this.f12744q;
            this.f12745r = this.f12743p;
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.f12728a);
        bundle.putFloat("matchViewHeight", this.f12748u);
        bundle.putFloat("matchViewWidth", this.f12747t);
        bundle.putInt("viewWidth", this.f12743p);
        bundle.putInt("viewHeight", this.f12744q);
        this.f12729b.getValues(this.f12736i);
        bundle.putFloatArray("matrix", this.f12736i);
        bundle.putBoolean("imageRendered", this.f12740m);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f12728a = bundle.getFloat("saveScale");
            this.f12736i = bundle.getFloatArray("matrix");
            this.f12730c.setValues(this.f12736i);
            this.f12750w = bundle.getFloat("matchViewHeight");
            this.f12749v = bundle.getFloat("matchViewWidth");
            this.f12746s = bundle.getInt("viewHeight");
            this.f12745r = bundle.getInt("viewWidth");
            this.f12740m = bundle.getBoolean("imageRendered");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void onDraw(Canvas canvas) {
        this.f12741n = true;
        this.f12740m = true;
        if (this.f12742o != null) {
            m13456a(this.f12742o.f12721a, this.f12742o.f12722b, this.f12742o.f12723c, this.f12742o.f12724d);
            this.f12742o = null;
        }
        super.onDraw(canvas);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m13435c();
    }

    public float getMaxZoom() {
        return this.f12733f;
    }

    public void setMaxZoom(float f) {
        this.f12733f = f;
        this.f12735h = 1.25f * this.f12733f;
    }

    public float getMinZoom() {
        return this.f12732e;
    }

    public float getCurrentZoom() {
        return this.f12728a;
    }

    public void setMinZoom(float f) {
        this.f12732e = f;
        this.f12734g = 0.75f * this.f12732e;
    }

    /* renamed from: b */
    public void m13458b() {
        this.f12728a = 1.0f;
        m13441f();
    }

    public void setZoom(float f) {
        m13455a(f, 0.5f, 0.5f);
    }

    /* renamed from: a */
    public void m13455a(float f, float f2, float f3) {
        m13456a(f, f2, f3, this.f12739l);
    }

    /* renamed from: a */
    public void m13456a(float f, float f2, float f3, ScaleType scaleType) {
        if (this.f12741n) {
            if (scaleType != this.f12739l) {
                setScaleType(scaleType);
            }
            m13458b();
            m13424a((double) f, (float) (this.f12743p / 2), (float) (this.f12744q / 2), true);
            this.f12729b.getValues(this.f12736i);
            this.f12736i[2] = -((getImageWidth() * f2) - (((float) this.f12743p) * 0.5f));
            this.f12736i[5] = -((getImageHeight() * f3) - (((float) this.f12744q) * 0.5f));
            this.f12729b.setValues(this.f12736i);
            m13437d();
            setImageMatrix(this.f12729b);
            return;
        }
        this.f12742o = new C2728h(this, f, f2, f3, scaleType);
    }

    public void setZoom(TouchImageView touchImageView) {
        PointF scrollPosition = touchImageView.getScrollPosition();
        m13456a(touchImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, touchImageView.getScaleType());
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF a = m13419a((float) (this.f12743p / 2), (float) (this.f12744q / 2), true);
        a.x /= (float) intrinsicWidth;
        a.y /= (float) intrinsicHeight;
        return a;
    }

    /* renamed from: d */
    private void m13437d() {
        this.f12729b.getValues(this.f12736i);
        float f = this.f12736i[2];
        float f2 = this.f12736i[5];
        f = m13431b(f, (float) this.f12743p, getImageWidth());
        f2 = m13431b(f2, (float) this.f12744q, getImageHeight());
        if (f != 0.0f || f2 != 0.0f) {
            this.f12729b.postTranslate(f, f2);
        }
    }

    /* renamed from: e */
    private void m13439e() {
        m13437d();
        this.f12729b.getValues(this.f12736i);
        if (getImageWidth() < ((float) this.f12743p)) {
            this.f12736i[2] = (((float) this.f12743p) - getImageWidth()) / 2.0f;
        }
        if (getImageHeight() < ((float) this.f12744q)) {
            this.f12736i[5] = (((float) this.f12744q) - getImageHeight()) / 2.0f;
        }
        this.f12729b.setValues(this.f12736i);
    }

    /* renamed from: b */
    private float m13431b(float f, float f2, float f3) {
        float f4;
        float f5;
        if (f3 <= f2) {
            f4 = f2 - f3;
            f5 = 0.0f;
        } else {
            f5 = f2 - f3;
            f4 = 0.0f;
        }
        if (f < f5) {
            return (-f) + f5;
        }
        if (f > f4) {
            return (-f) + f4;
        }
        return 0.0f;
    }

    /* renamed from: c */
    private float m13433c(float f, float f2, float f3) {
        if (f3 <= f2) {
            return 0.0f;
        }
        return f;
    }

    private float getImageWidth() {
        return this.f12747t * this.f12728a;
    }

    private float getImageHeight() {
        return this.f12748u * this.f12728a;
    }

    protected void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        this.f12743p = m13417a(mode, size, intrinsicWidth);
        this.f12744q = m13417a(mode2, size2, intrinsicHeight);
        setMeasuredDimension(this.f12743p, this.f12744q);
        m13441f();
    }

    /* renamed from: f */
    private void m13441f() {
        Drawable drawable = getDrawable();
        if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0 && this.f12729b != null && this.f12730c != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            float f = ((float) this.f12743p) / ((float) intrinsicWidth);
            float f2 = ((float) this.f12744q) / ((float) intrinsicHeight);
            switch (C27201.f12698a[this.f12739l.ordinal()]) {
                case 1:
                    f2 = 1.0f;
                    f = 1.0f;
                    break;
                case 2:
                    f2 = Math.max(f, f2);
                    f = f2;
                    break;
                case 3:
                    f2 = Math.min(1.0f, Math.min(f, f2));
                    f = f2;
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
            }
            f2 = Math.min(f, f2);
            f = f2;
            float f3 = ((float) this.f12743p) - (((float) intrinsicWidth) * f);
            float f4 = ((float) this.f12744q) - (((float) intrinsicHeight) * f2);
            this.f12747t = ((float) this.f12743p) - f3;
            this.f12748u = ((float) this.f12744q) - f4;
            if (m13457a() || this.f12740m) {
                if (this.f12749v == 0.0f || this.f12750w == 0.0f) {
                    m13435c();
                }
                this.f12730c.getValues(this.f12736i);
                this.f12736i[0] = (this.f12747t / ((float) intrinsicWidth)) * this.f12728a;
                this.f12736i[4] = (this.f12748u / ((float) intrinsicHeight)) * this.f12728a;
                f = this.f12736i[2];
                float f5 = this.f12736i[5];
                m13425a(2, f, this.f12728a * this.f12749v, getImageWidth(), this.f12745r, this.f12743p, intrinsicWidth);
                m13425a(5, f5, this.f12750w * this.f12728a, getImageHeight(), this.f12746s, this.f12744q, intrinsicHeight);
                this.f12729b.setValues(this.f12736i);
            } else {
                this.f12729b.setScale(f, f2);
                this.f12729b.postTranslate(f3 / 2.0f, f4 / 2.0f);
                this.f12728a = 1.0f;
            }
            m13437d();
            setImageMatrix(this.f12729b);
        }
    }

    /* renamed from: a */
    private int m13417a(int i, int i2, int i3) {
        switch (i) {
            case Integer.MIN_VALUE:
                return Math.min(i3, i2);
            case 0:
                return i3;
            default:
                return i2;
        }
    }

    /* renamed from: a */
    private void m13425a(int i, float f, float f2, float f3, int i2, int i3, int i4) {
        if (f3 < ((float) i3)) {
            this.f12736i[i] = (((float) i3) - (((float) i4) * this.f12736i[0])) * 0.5f;
        } else if (f > 0.0f) {
            this.f12736i[i] = -((f3 - ((float) i3)) * 0.5f);
        } else {
            this.f12736i[i] = -((((Math.abs(f) + (((float) i2) * 0.5f)) / f2) * f3) - (((float) i3) * 0.5f));
        }
    }

    private void setState(State state) {
        this.f12731d = state;
    }

    public boolean canScrollHorizontally(int i) {
        this.f12729b.getValues(this.f12736i);
        float f = this.f12736i[2];
        if (getImageWidth() < ((float) this.f12743p)) {
            return false;
        }
        if (f >= -1.0f && i < 0) {
            return false;
        }
        if ((Math.abs(f) + ((float) this.f12743p)) + 1.0f < getImageWidth() || i <= 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private void m13424a(double d, float f, float f2, boolean z) {
        float f3;
        float f4;
        if (z) {
            f3 = this.f12734g;
            f4 = this.f12735h;
        } else {
            f3 = this.f12732e;
            f4 = this.f12733f;
        }
        float f5 = this.f12728a;
        this.f12728a = (float) (((double) this.f12728a) * d);
        if (this.f12728a > f4) {
            this.f12728a = f4;
            d = (double) (f4 / f5);
        } else if (this.f12728a < f3) {
            this.f12728a = f3;
            d = (double) (f3 / f5);
        }
        this.f12729b.postScale((float) d, (float) d, f, f2);
        m13439e();
    }

    /* renamed from: a */
    private PointF m13419a(float f, float f2, boolean z) {
        this.f12729b.getValues(this.f12736i);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        float f3 = this.f12736i[2];
        float imageWidth = ((f - f3) * intrinsicWidth) / getImageWidth();
        f3 = ((f2 - this.f12736i[5]) * intrinsicHeight) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            f3 = Math.min(Math.max(f3, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, f3);
    }

    /* renamed from: a */
    private PointF m13418a(float f, float f2) {
        this.f12729b.getValues(this.f12736i);
        float intrinsicWidth = f / ((float) getDrawable().getIntrinsicWidth());
        float intrinsicHeight = f2 / ((float) getDrawable().getIntrinsicHeight());
        return new PointF((intrinsicWidth * getImageWidth()) + this.f12736i[2], (intrinsicHeight * getImageHeight()) + this.f12736i[5]);
    }

    @TargetApi(16)
    /* renamed from: a */
    private void m13430a(Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            postOnAnimation(runnable);
        } else {
            postDelayed(runnable, 16);
        }
    }
}
