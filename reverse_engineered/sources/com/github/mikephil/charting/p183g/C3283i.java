package com.github.mikephil.charting.p183g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.github.mikephil.charting.p121c.C3192f;
import com.github.mikephil.charting.p121c.C3193c;

/* compiled from: Utils */
/* renamed from: com.github.mikephil.charting.g.i */
public abstract class C3283i {
    /* renamed from: a */
    public static final double f14220a = Double.longBitsToDouble(1);
    /* renamed from: b */
    public static final float f14221b = Float.intBitsToFloat(1);
    /* renamed from: c */
    private static DisplayMetrics f14222c;
    /* renamed from: d */
    private static int f14223d = 50;
    /* renamed from: e */
    private static int f14224e = 8000;
    /* renamed from: f */
    private static Rect f14225f = new Rect();
    /* renamed from: g */
    private static FontMetrics f14226g = new FontMetrics();
    /* renamed from: h */
    private static Rect f14227h = new Rect();
    /* renamed from: i */
    private static final int[] f14228i = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
    /* renamed from: j */
    private static C3192f f14229j = C3283i.m15952e();
    /* renamed from: k */
    private static Rect f14230k = new Rect();
    /* renamed from: l */
    private static Rect f14231l = new Rect();
    /* renamed from: m */
    private static FontMetrics f14232m = new FontMetrics();

    /* renamed from: a */
    public static void m15934a(Context context) {
        if (context == null) {
            f14223d = ViewConfiguration.getMinimumFlingVelocity();
            f14224e = ViewConfiguration.getMaximumFlingVelocity();
            Log.e("MPChartLib-Utils", "Utils.init(...) PROVIDED CONTEXT OBJECT IS NULL");
            return;
        }
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        f14223d = viewConfiguration.getScaledMinimumFlingVelocity();
        f14224e = viewConfiguration.getScaledMaximumFlingVelocity();
        f14222c = context.getResources().getDisplayMetrics();
    }

    /* renamed from: a */
    public static float m15928a(float f) {
        if (f14222c != null) {
            return f * f14222c.density;
        }
        Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertDpToPixel(...). Otherwise conversion does not take place.");
        return f;
    }

    /* renamed from: a */
    public static int m15931a(Paint paint, String str) {
        return (int) paint.measureText(str);
    }

    /* renamed from: b */
    public static int m15946b(Paint paint, String str) {
        Rect rect = f14225f;
        rect.set(0, 0, 0, 0);
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    /* renamed from: a */
    public static float m15929a(Paint paint) {
        return C3283i.m15930a(paint, f14226g);
    }

    /* renamed from: a */
    public static float m15930a(Paint paint, FontMetrics fontMetrics) {
        paint.getFontMetrics(fontMetrics);
        return fontMetrics.descent - fontMetrics.ascent;
    }

    /* renamed from: b */
    public static float m15942b(Paint paint) {
        return C3283i.m15943b(paint, f14226g);
    }

    /* renamed from: b */
    public static float m15943b(Paint paint, FontMetrics fontMetrics) {
        paint.getFontMetrics(fontMetrics);
        return (fontMetrics.ascent - fontMetrics.top) + fontMetrics.bottom;
    }

    /* renamed from: c */
    public static C3274b m15950c(Paint paint, String str) {
        C3274b a = C3274b.m15844a(0.0f, 0.0f);
        C3283i.m15937a(paint, str, a);
        return a;
    }

    /* renamed from: a */
    public static void m15937a(Paint paint, String str, C3274b c3274b) {
        Rect rect = f14227h;
        rect.set(0, 0, 0, 0);
        paint.getTextBounds(str, 0, str.length(), rect);
        c3274b.f14176a = (float) rect.width();
        c3274b.f14177b = (float) rect.height();
    }

    /* renamed from: e */
    private static C3192f m15952e() {
        return new C3193c(1);
    }

    /* renamed from: a */
    public static C3192f m15932a() {
        return f14229j;
    }

    /* renamed from: a */
    public static float m15927a(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d) || d == 0.0d) {
            return 0.0f;
        }
        double d2;
        if (d < 0.0d) {
            d2 = -d;
        } else {
            d2 = d;
        }
        float pow = (float) Math.pow(10.0d, (double) (1 - ((int) ((float) Math.ceil((double) ((float) Math.log10(d2)))))));
        return ((float) Math.round(((double) pow) * d)) / pow;
    }

    /* renamed from: b */
    public static int m15945b(float f) {
        float a = C3283i.m15927a((double) f);
        if (Float.isInfinite(a)) {
            return 0;
        }
        return ((int) Math.ceil(-Math.log10((double) a))) + 2;
    }

    /* renamed from: b */
    public static double m15941b(double d) {
        if (d == Double.POSITIVE_INFINITY) {
            return d;
        }
        double d2 = d + 0.0d;
        return Double.longBitsToDouble((d2 >= 0.0d ? 1 : -1) + Double.doubleToRawLongBits(d2));
    }

    /* renamed from: a */
    public static void m15940a(C3279e c3279e, float f, float f2, C3279e c3279e2) {
        c3279e2.f14200a = (float) (((double) c3279e.f14200a) + (((double) f) * Math.cos(Math.toRadians((double) f2))));
        c3279e2.f14201b = (float) (((double) c3279e.f14201b) + (((double) f) * Math.sin(Math.toRadians((double) f2))));
    }

    /* renamed from: a */
    public static void m15938a(MotionEvent motionEvent, VelocityTracker velocityTracker) {
        velocityTracker.computeCurrentVelocity(1000, (float) f14224e);
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        float xVelocity = velocityTracker.getXVelocity(pointerId);
        float yVelocity = velocityTracker.getYVelocity(pointerId);
        int pointerCount = motionEvent.getPointerCount();
        for (pointerId = 0; pointerId < pointerCount; pointerId++) {
            if (pointerId != actionIndex) {
                int pointerId2 = motionEvent.getPointerId(pointerId);
                if ((velocityTracker.getYVelocity(pointerId2) * yVelocity) + (velocityTracker.getXVelocity(pointerId2) * xVelocity) < 0.0f) {
                    velocityTracker.clear();
                    return;
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static void m15939a(View view) {
        if (VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidateDelayed(10);
        }
    }

    /* renamed from: b */
    public static int m15944b() {
        return f14223d;
    }

    /* renamed from: c */
    public static int m15949c() {
        return f14224e;
    }

    /* renamed from: c */
    public static float m15948c(float f) {
        while (f < 0.0f) {
            f += 360.0f;
        }
        return f % 360.0f;
    }

    /* renamed from: a */
    public static void m15935a(Canvas canvas, Drawable drawable, int i, int i2, int i3, int i4) {
        C3279e b = C3279e.m15899b();
        b.f14200a = (float) (i - (i3 / 2));
        b.f14201b = (float) (i2 - (i4 / 2));
        drawable.copyBounds(f14230k);
        drawable.setBounds(f14230k.left, f14230k.top, f14230k.left + i3, f14230k.top + i3);
        int save = canvas.save();
        canvas.translate(b.f14200a, b.f14201b);
        drawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    /* renamed from: a */
    public static void m15936a(Canvas canvas, String str, float f, float f2, Paint paint, C3279e c3279e, float f3) {
        float fontMetrics = paint.getFontMetrics(f14232m);
        paint.getTextBounds(str, 0, str.length(), f14231l);
        float f4 = 0.0f - ((float) f14231l.left);
        float f5 = (-f14232m.ascent) + 0.0f;
        Align textAlign = paint.getTextAlign();
        paint.setTextAlign(Align.LEFT);
        if (f3 != 0.0f) {
            f4 -= ((float) f14231l.width()) * 0.5f;
            f5 -= fontMetrics * 0.5f;
            if (!(c3279e.f14200a == 0.5f && c3279e.f14201b == 0.5f)) {
                C3274b a = C3283i.m15933a((float) f14231l.width(), fontMetrics, f3);
                f -= a.f14176a * (c3279e.f14200a - 0.5f);
                f2 -= a.f14177b * (c3279e.f14201b - 0.5f);
                C3274b.m15845a(a);
            }
            canvas.save();
            canvas.translate(f, f2);
            canvas.rotate(f3);
            canvas.drawText(str, f4, f5, paint);
            canvas.restore();
        } else {
            if (!(c3279e.f14200a == 0.0f && c3279e.f14201b == 0.0f)) {
                f4 -= ((float) f14231l.width()) * c3279e.f14200a;
                f5 -= fontMetrics * c3279e.f14201b;
            }
            canvas.drawText(str, f4 + f, f5 + f2, paint);
        }
        paint.setTextAlign(textAlign);
    }

    /* renamed from: a */
    public static C3274b m15933a(float f, float f2, float f3) {
        return C3283i.m15947b(f, f2, 0.017453292f * f3);
    }

    /* renamed from: b */
    public static C3274b m15947b(float f, float f2, float f3) {
        return C3274b.m15844a(Math.abs(((float) Math.cos((double) f3)) * f) + Math.abs(((float) Math.sin((double) f3)) * f2), Math.abs(((float) Math.sin((double) f3)) * f) + Math.abs(((float) Math.cos((double) f3)) * f2));
    }

    /* renamed from: d */
    public static int m15951d() {
        return VERSION.SDK_INT;
    }
}
