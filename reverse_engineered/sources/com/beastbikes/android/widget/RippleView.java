package com.beastbikes.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;
import org.apache.http.HttpStatus;

public class RippleView extends RelativeLayout {
    /* renamed from: a */
    private int f12151a;
    /* renamed from: b */
    private int f12152b;
    /* renamed from: c */
    private int f12153c = 10;
    /* renamed from: d */
    private int f12154d = HttpStatus.SC_BAD_REQUEST;
    /* renamed from: e */
    private int f12155e = 90;
    /* renamed from: f */
    private Handler f12156f;
    /* renamed from: g */
    private float f12157g = 0.0f;
    /* renamed from: h */
    private boolean f12158h = false;
    /* renamed from: i */
    private int f12159i = 0;
    /* renamed from: j */
    private int f12160j = 0;
    /* renamed from: k */
    private int f12161k = -1;
    /* renamed from: l */
    private float f12162l = -1.0f;
    /* renamed from: m */
    private float f12163m = -1.0f;
    /* renamed from: n */
    private int f12164n;
    /* renamed from: o */
    private float f12165o;
    /* renamed from: p */
    private ScaleAnimation f12166p;
    /* renamed from: q */
    private Boolean f12167q;
    /* renamed from: r */
    private Boolean f12168r;
    /* renamed from: s */
    private Integer f12169s;
    /* renamed from: t */
    private Paint f12170t;
    /* renamed from: u */
    private Bitmap f12171u;
    /* renamed from: v */
    private int f12172v;
    /* renamed from: w */
    private int f12173w;
    /* renamed from: x */
    private GestureDetector f12174x;
    /* renamed from: y */
    private final Runnable f12175y = new C25981(this);
    /* renamed from: z */
    private C2600a f12176z;

    /* renamed from: com.beastbikes.android.widget.RippleView$1 */
    class C25981 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ RippleView f12148a;

        C25981(RippleView rippleView) {
            this.f12148a = rippleView;
        }

        public void run() {
            this.f12148a.invalidate();
        }
    }

    /* renamed from: com.beastbikes.android.widget.RippleView$2 */
    class C25992 extends SimpleOnGestureListener {
        /* renamed from: a */
        final /* synthetic */ RippleView f12149a;

        C25992(RippleView rippleView) {
            this.f12149a = rippleView;
        }

        public void onLongPress(MotionEvent motionEvent) {
            super.onLongPress(motionEvent);
            this.f12149a.m12985a(motionEvent);
            this.f12149a.m12984a(Boolean.valueOf(true));
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return true;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return true;
        }
    }

    public enum RippleType {
        SIMPLE(0),
        DOUBLE(1),
        RECTANGLE(2);
        
        /* renamed from: a */
        int f12150a;

        private RippleType(int i) {
            this.f12150a = i;
        }
    }

    /* renamed from: com.beastbikes.android.widget.RippleView$a */
    public interface C2600a {
        /* renamed from: a */
        void m12979a(RippleView rippleView);
    }

    public RippleView(Context context) {
        super(context);
    }

    public RippleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m12982a(context, attributeSet);
    }

    public RippleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12982a(context, attributeSet);
    }

    /* renamed from: a */
    private void m12982a(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RippleView);
            this.f12172v = obtainStyledAttributes.getColor(4, getResources().getColor(C1373R.color.rippelColor));
            this.f12169s = Integer.valueOf(obtainStyledAttributes.getInt(6, 0));
            this.f12167q = Boolean.valueOf(obtainStyledAttributes.getBoolean(8, false));
            this.f12168r = Boolean.valueOf(obtainStyledAttributes.getBoolean(5, false));
            this.f12154d = obtainStyledAttributes.getInteger(2, this.f12154d);
            this.f12153c = obtainStyledAttributes.getInteger(1, this.f12153c);
            this.f12155e = obtainStyledAttributes.getInteger(0, this.f12155e);
            this.f12173w = obtainStyledAttributes.getDimensionPixelSize(7, 0);
            this.f12156f = new Handler();
            this.f12165o = obtainStyledAttributes.getFloat(9, 1.03f);
            this.f12164n = obtainStyledAttributes.getInt(3, 200);
            obtainStyledAttributes.recycle();
            this.f12170t = new Paint();
            this.f12170t.setAntiAlias(true);
            this.f12170t.setStyle(Style.FILL);
            this.f12170t.setColor(this.f12172v);
            this.f12170t.setAlpha(this.f12155e);
            setWillNotDraw(false);
            this.f12174x = new GestureDetector(context, new C25992(this));
            setDrawingCacheEnabled(true);
            setClickable(true);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f12158h) {
            canvas.save();
            if (this.f12154d <= this.f12159i * this.f12153c) {
                this.f12158h = false;
                this.f12159i = 0;
                this.f12161k = -1;
                this.f12160j = 0;
                if (VERSION.SDK_INT != 23) {
                    canvas.restore();
                }
                invalidate();
                if (this.f12176z != null) {
                    this.f12176z.m12979a(this);
                    return;
                }
                return;
            }
            this.f12156f.postDelayed(this.f12175y, (long) this.f12153c);
            if (this.f12159i == 0) {
                canvas.save();
            }
            canvas.drawCircle(this.f12162l, this.f12163m, this.f12157g * ((((float) this.f12159i) * ((float) this.f12153c)) / ((float) this.f12154d)), this.f12170t);
            this.f12170t.setColor(Color.parseColor("#ffff4444"));
            if (this.f12169s.intValue() == 1 && this.f12171u != null && (((float) this.f12159i) * ((float) this.f12153c)) / ((float) this.f12154d) > 0.4f) {
                if (this.f12161k == -1) {
                    this.f12161k = this.f12154d - (this.f12159i * this.f12153c);
                }
                this.f12160j++;
                Bitmap a = m12980a((int) (this.f12157g * ((((float) this.f12160j) * ((float) this.f12153c)) / ((float) this.f12161k))));
                canvas.drawBitmap(a, 0.0f, 0.0f, this.f12170t);
                a.recycle();
            }
            this.f12170t.setColor(this.f12172v);
            if (this.f12169s.intValue() != 1) {
                this.f12170t.setAlpha((int) (((float) this.f12155e) - (((float) this.f12155e) * ((((float) this.f12159i) * ((float) this.f12153c)) / ((float) this.f12154d)))));
            } else if ((((float) this.f12159i) * ((float) this.f12153c)) / ((float) this.f12154d) > 0.6f) {
                this.f12170t.setAlpha((int) (((float) this.f12155e) - (((float) this.f12155e) * ((((float) this.f12160j) * ((float) this.f12153c)) / ((float) this.f12161k)))));
            } else {
                this.f12170t.setAlpha(this.f12155e);
            }
            this.f12159i++;
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f12151a = i;
        this.f12152b = i2;
        float f = 1.0f;
        this.f12166p = new ScaleAnimation(1.0f, this.f12165o, f, this.f12165o, (float) (i / 2), (float) (i2 / 2));
        this.f12166p.setDuration((long) this.f12164n);
        this.f12166p.setRepeatMode(2);
        this.f12166p.setRepeatCount(1);
    }

    /* renamed from: a */
    public void m12985a(MotionEvent motionEvent) {
        m12981a(motionEvent.getX(), motionEvent.getY());
    }

    /* renamed from: a */
    private void m12981a(float f, float f2) {
        if (isEnabled() && !this.f12158h) {
            if (this.f12167q.booleanValue()) {
                startAnimation(this.f12166p);
            }
            this.f12157g = (float) Math.max(this.f12151a, this.f12152b);
            if (this.f12169s.intValue() != 2) {
                this.f12157g /= 2.0f;
            }
            this.f12157g -= (float) this.f12173w;
            if (this.f12168r.booleanValue() || this.f12169s.intValue() == 1) {
                this.f12162l = (float) (getMeasuredWidth() / 2);
                this.f12163m = (float) (getMeasuredHeight() / 2);
            } else {
                this.f12162l = f;
                this.f12163m = f2;
            }
            this.f12158h = true;
            if (this.f12169s.intValue() == 1 && this.f12171u == null) {
                this.f12171u = getDrawingCache(true);
            }
            invalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f12174x.onTouchEvent(motionEvent)) {
            m12985a(motionEvent);
            m12984a(Boolean.valueOf(false));
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        onTouchEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    /* renamed from: a */
    private void m12984a(Boolean bool) {
        if (getParent() instanceof AdapterView) {
            AdapterView adapterView = (AdapterView) getParent();
            int positionForView = adapterView.getPositionForView(this);
            long itemIdAtPosition = adapterView.getItemIdAtPosition(positionForView);
            if (bool.booleanValue()) {
                if (adapterView.getOnItemLongClickListener() != null) {
                    adapterView.getOnItemLongClickListener().onItemLongClick(adapterView, this, positionForView, itemIdAtPosition);
                }
            } else if (adapterView.getOnItemClickListener() != null) {
                adapterView.getOnItemClickListener().onItemClick(adapterView, this, positionForView, itemIdAtPosition);
            }
        }
    }

    /* renamed from: a */
    private Bitmap m12980a(int i) {
        Bitmap createBitmap = Bitmap.createBitmap(this.f12171u.getWidth(), this.f12171u.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect((int) (this.f12162l - ((float) i)), (int) (this.f12163m - ((float) i)), (int) (this.f12162l + ((float) i)), (int) (this.f12163m + ((float) i)));
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(this.f12162l, this.f12163m, (float) i, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(this.f12171u, rect, rect, paint);
        return createBitmap;
    }

    @ColorRes
    public void setRippleColor(int i) {
        this.f12172v = getResources().getColor(i);
    }

    public int getRippleColor() {
        return this.f12172v;
    }

    public RippleType getRippleType() {
        return RippleType.values()[this.f12169s.intValue()];
    }

    public void setRippleType(RippleType rippleType) {
        this.f12169s = Integer.valueOf(rippleType.ordinal());
    }

    public void setCentered(Boolean bool) {
        this.f12168r = bool;
    }

    public int getRipplePadding() {
        return this.f12173w;
    }

    public void setRipplePadding(int i) {
        this.f12173w = i;
    }

    public void setZooming(Boolean bool) {
        this.f12167q = bool;
    }

    public float getZoomScale() {
        return this.f12165o;
    }

    public void setZoomScale(float f) {
        this.f12165o = f;
    }

    public int getZoomDuration() {
        return this.f12164n;
    }

    public void setZoomDuration(int i) {
        this.f12164n = i;
    }

    public int getRippleDuration() {
        return this.f12154d;
    }

    public void setRippleDuration(int i) {
        this.f12154d = i;
    }

    public int getFrameRate() {
        return this.f12153c;
    }

    public void setFrameRate(int i) {
        this.f12153c = i;
    }

    public int getRippleAlpha() {
        return this.f12155e;
    }

    public void setRippleAlpha(int i) {
        this.f12155e = i;
    }

    public void setOnRippleCompleteListener(C2600a c2600a) {
        this.f12176z = c2600a;
    }
}
