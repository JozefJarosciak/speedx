package com.beastbikes.android.ble.ui.painter.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.RectF;
import android.support.v4.view.InputDeviceCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.widget.ProgressBar;
import com.alibaba.fastjson.asm.Opcodes;
import com.beastbikes.android.R$styleable;
import com.google.common.primitives.Ints;

public class ArcProgress extends ProgressBar {
    /* renamed from: a */
    private final int f7983a;
    /* renamed from: b */
    private final int f7984b;
    /* renamed from: c */
    private final int f7985c;
    /* renamed from: d */
    private final int f7986d;
    /* renamed from: e */
    private final int f7987e;
    /* renamed from: f */
    private final int f7988f;
    /* renamed from: g */
    private final int f7989g;
    /* renamed from: h */
    private final int f7990h;
    /* renamed from: i */
    private final int f7991i;
    /* renamed from: j */
    private int f7992j;
    /* renamed from: k */
    private boolean f7993k;
    /* renamed from: l */
    private float f7994l;
    /* renamed from: m */
    private int f7995m;
    /* renamed from: n */
    private int f7996n;
    /* renamed from: o */
    private int f7997o;
    /* renamed from: p */
    private RectF f7998p;
    /* renamed from: q */
    private Paint f7999q;
    /* renamed from: r */
    private Paint f8000r;
    /* renamed from: s */
    private int f8001s;
    /* renamed from: t */
    private int f8002t;
    /* renamed from: u */
    private int f8003u;
    /* renamed from: v */
    private int f8004v;
    /* renamed from: w */
    private Bitmap f8005w;
    /* renamed from: x */
    private Canvas f8006x;
    /* renamed from: y */
    private C1757a f8007y;

    /* renamed from: com.beastbikes.android.ble.ui.painter.progress.ArcProgress$a */
    public interface C1757a {
        /* renamed from: a */
        void m9350a(Canvas canvas, RectF rectF, float f, float f2, float f3, int i);
    }

    public ArcProgress(Context context) {
        this(context, null);
    }

    public ArcProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ArcProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7983a = m9351a(15);
        this.f7984b = m9351a(2);
        this.f7985c = m9351a(72);
        this.f7986d = -1381654;
        this.f7987e = InputDeviceCompat.SOURCE_ANY;
        this.f7988f = 60;
        this.f7989g = 4;
        this.f7990h = 2;
        this.f7991i = 8;
        this.f7992j = 1;
        this.f7997o = 60;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ArcProgress);
        this.f7996n = obtainStyledAttributes.getDimensionPixelOffset(0, this.f7983a);
        this.f8001s = obtainStyledAttributes.getColor(1, -1381654);
        this.f8002t = obtainStyledAttributes.getColor(2, InputDeviceCompat.SOURCE_ANY);
        this.f8003u = obtainStyledAttributes.getDimensionPixelOffset(3, this.f7984b);
        this.f8004v = obtainStyledAttributes.getInt(4, 4);
        this.f7994l = (float) obtainStyledAttributes.getDimensionPixelOffset(6, this.f7985c);
        this.f7995m = obtainStyledAttributes.getColor(7, -1381654);
        this.f8004v = Math.max(Math.min(this.f8004v, 8), 2);
        this.f7993k = obtainStyledAttributes.getBoolean(5, false);
        this.f7997o = obtainStyledAttributes.getInt(8, 60);
        this.f7992j = obtainStyledAttributes.getInt(10, 1);
        boolean z = obtainStyledAttributes.getBoolean(9, false);
        this.f8000r = new Paint(1);
        this.f8000r.setColor(this.f7995m);
        if (z) {
            this.f8000r.setStrokeCap(Cap.ROUND);
        }
        this.f8000r.setStrokeWidth((float) this.f7996n);
        this.f8000r.setStyle(Style.STROKE);
        this.f7999q = new Paint(1);
        this.f7999q.setStrokeWidth((float) this.f8003u);
    }

    public void setOnCenterDraw(C1757a c1757a) {
        this.f8007y = c1757a;
    }

    protected synchronized void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        if (mode != Ints.MAX_POWER_OF_TWO) {
            i = MeasureSpec.makeMeasureSpec((int) ((this.f7994l * 2.0f) + ((float) (this.f7996n * 2))), Ints.MAX_POWER_OF_TWO);
        }
        if (mode2 != Ints.MAX_POWER_OF_TWO) {
            i2 = MeasureSpec.makeMeasureSpec((int) ((this.f7994l * 2.0f) + ((float) (this.f7996n * 2))), Ints.MAX_POWER_OF_TWO);
        }
        super.onMeasure(i, i2);
    }

    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        float progress = (((float) getProgress()) * 1.0f) / ((float) getMax());
        float f = (this.f7998p.right / 2.0f) + ((float) (this.f7996n / 2));
        float f2 = (this.f7998p.right / 2.0f) + ((float) (this.f7996n / 2));
        if (this.f8007y != null) {
            if (this.f8006x == null) {
                this.f8005w = Bitmap.createBitmap(((int) this.f7994l) * 2, ((int) this.f7994l) * 2, Config.ARGB_8888);
                this.f8006x = new Canvas(this.f8005w);
            }
            this.f8006x.drawColor(0, Mode.CLEAR);
            this.f8007y.m9350a(this.f8006x, this.f7998p, f, f2, (float) this.f7996n, getProgress());
            canvas.drawBitmap(this.f8005w, 0.0f, 0.0f, null);
        }
        int i = this.f7997o / 2;
        int i2 = (360 - this.f7997o) / this.f8004v;
        int i3 = (int) (((float) i2) * progress);
        if (this.f7992j == 0) {
            f = ((float) (360 - this.f7997o)) * progress;
            this.f8000r.setColor(this.f8002t);
            canvas.drawArc(this.f7998p, (float) (i + 90), f, false, this.f8000r);
            this.f8000r.setColor(this.f8001s);
            canvas.drawArc(this.f7998p, ((float) (i + 90)) + f, ((float) (360 - this.f7997o)) - f, false, this.f8000r);
        } else {
            if (this.f7993k) {
                canvas.drawArc(this.f7998p, (float) (i + 90), (float) (360 - this.f7997o), false, this.f8000r);
            }
            canvas.rotate((float) (i + Opcodes.GETFIELD), f, f2);
            for (int i4 = 0; i4 < i2; i4++) {
                if (i4 < i3) {
                    this.f7999q.setColor(this.f8002t);
                } else {
                    this.f7999q.setColor(this.f8001s);
                }
                canvas.drawLine(f, (float) (this.f7996n + (this.f7996n / 2)), f, (float) (this.f7996n - (this.f7996n / 2)), this.f7999q);
                canvas.rotate((float) this.f8004v, f, f2);
            }
        }
        canvas.restore();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f7998p = new RectF((float) this.f7996n, (float) this.f7996n, (this.f7994l * 2.0f) - ((float) this.f7996n), (this.f7994l * 2.0f) - ((float) this.f7996n));
    }

    /* renamed from: a */
    protected int m9351a(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f8005w != null) {
            this.f8005w.recycle();
            this.f8005w = null;
        }
    }
}
