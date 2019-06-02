package com.wdullaer.materialdatetimepicker.time;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import com.wdullaer.materialdatetimepicker.C4779R;

/* compiled from: RadialTextsView */
/* renamed from: com.wdullaer.materialdatetimepicker.time.d */
public class C4819d extends View {
    /* renamed from: A */
    private float f16971A;
    /* renamed from: B */
    private float[] f16972B;
    /* renamed from: C */
    private float[] f16973C;
    /* renamed from: D */
    private float[] f16974D;
    /* renamed from: E */
    private float[] f16975E;
    /* renamed from: F */
    private float f16976F;
    /* renamed from: G */
    private float f16977G;
    /* renamed from: H */
    private float f16978H;
    /* renamed from: I */
    private C4818a f16979I;
    /* renamed from: a */
    ObjectAnimator f16980a;
    /* renamed from: b */
    ObjectAnimator f16981b;
    /* renamed from: c */
    private final Paint f16982c = new Paint();
    /* renamed from: d */
    private final Paint f16983d = new Paint();
    /* renamed from: e */
    private final Paint f16984e = new Paint();
    /* renamed from: f */
    private boolean f16985f;
    /* renamed from: g */
    private boolean f16986g = false;
    /* renamed from: h */
    private int f16987h = -1;
    /* renamed from: i */
    private C4804b f16988i;
    /* renamed from: j */
    private Typeface f16989j;
    /* renamed from: k */
    private Typeface f16990k;
    /* renamed from: l */
    private String[] f16991l;
    /* renamed from: m */
    private String[] f16992m;
    /* renamed from: n */
    private boolean f16993n;
    /* renamed from: o */
    private boolean f16994o;
    /* renamed from: p */
    private float f16995p;
    /* renamed from: q */
    private float f16996q;
    /* renamed from: r */
    private float f16997r;
    /* renamed from: s */
    private float f16998s;
    /* renamed from: t */
    private float f16999t;
    /* renamed from: u */
    private float f17000u;
    /* renamed from: v */
    private int f17001v;
    /* renamed from: w */
    private int f17002w;
    /* renamed from: x */
    private float f17003x;
    /* renamed from: y */
    private boolean f17004y;
    /* renamed from: z */
    private float f17005z;

    /* compiled from: RadialTextsView */
    /* renamed from: com.wdullaer.materialdatetimepicker.time.d$b */
    interface C4804b {
        /* renamed from: a */
        boolean mo6202a(int i);
    }

    /* compiled from: RadialTextsView */
    /* renamed from: com.wdullaer.materialdatetimepicker.time.d$a */
    private class C4818a implements AnimatorUpdateListener {
        /* renamed from: a */
        final /* synthetic */ C4819d f16970a;

        private C4818a(C4819d c4819d) {
            this.f16970a = c4819d;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f16970a.invalidate();
        }
    }

    public C4819d(Context context) {
        super(context);
    }

    /* renamed from: a */
    public void m18924a(Context context, String[] strArr, String[] strArr2, C4820e c4820e, C4804b c4804b, boolean z) {
        int i = -1;
        if (this.f16986g) {
            Log.e("RadialTextsView", "This RadialTextsView may only be initialized once.");
            return;
        }
        boolean z2;
        int i2;
        Resources resources = context.getResources();
        this.f16982c.setColor(ContextCompat.getColor(context, c4820e.mo6208b() ? C4779R.color.mdtp_white : C4779R.color.mdtp_numbers_text_color));
        this.f16989j = Typeface.create(resources.getString(C4779R.string.mdtp_radial_numbers_typeface), 0);
        this.f16990k = Typeface.create(resources.getString(C4779R.string.mdtp_sans_serif), 0);
        this.f16982c.setAntiAlias(true);
        this.f16982c.setTextAlign(Align.CENTER);
        this.f16983d.setColor(ContextCompat.getColor(context, C4779R.color.mdtp_white));
        this.f16983d.setAntiAlias(true);
        this.f16983d.setTextAlign(Align.CENTER);
        this.f16984e.setColor(ContextCompat.getColor(context, c4820e.mo6208b() ? C4779R.color.mdtp_date_picker_text_disabled_dark_theme : C4779R.color.mdtp_date_picker_text_disabled));
        this.f16984e.setAntiAlias(true);
        this.f16984e.setTextAlign(Align.CENTER);
        this.f16991l = strArr;
        this.f16992m = strArr2;
        this.f16993n = c4820e.mo6209c();
        if (strArr2 != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f16994o = z2;
        if (this.f16993n) {
            this.f16995p = Float.parseFloat(resources.getString(C4779R.string.mdtp_circle_radius_multiplier_24HourMode));
        } else {
            this.f16995p = Float.parseFloat(resources.getString(C4779R.string.mdtp_circle_radius_multiplier));
            this.f16996q = Float.parseFloat(resources.getString(C4779R.string.mdtp_ampm_circle_radius_multiplier));
        }
        this.f16972B = new float[7];
        this.f16973C = new float[7];
        if (this.f16994o) {
            this.f16997r = Float.parseFloat(resources.getString(C4779R.string.mdtp_numbers_radius_multiplier_outer));
            this.f16999t = Float.parseFloat(resources.getString(C4779R.string.mdtp_text_size_multiplier_outer));
            this.f16998s = Float.parseFloat(resources.getString(C4779R.string.mdtp_numbers_radius_multiplier_inner));
            this.f17000u = Float.parseFloat(resources.getString(C4779R.string.mdtp_text_size_multiplier_inner));
            this.f16974D = new float[7];
            this.f16975E = new float[7];
        } else {
            this.f16997r = Float.parseFloat(resources.getString(C4779R.string.mdtp_numbers_radius_multiplier_normal));
            this.f16999t = Float.parseFloat(resources.getString(C4779R.string.mdtp_text_size_multiplier_normal));
        }
        this.f16976F = 1.0f;
        if (z) {
            i2 = -1;
        } else {
            i2 = 1;
        }
        this.f16977G = (((float) i2) * 0.05f) + 1.0f;
        if (z) {
            i = 1;
        }
        this.f16978H = (0.3f * ((float) i)) + 1.0f;
        this.f16979I = new C4818a();
        this.f16988i = c4804b;
        this.f17004y = true;
        this.f16986g = true;
    }

    protected void setSelection(int i) {
        this.f16987h = i;
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    public void setAnimationRadiusMultiplier(float f) {
        this.f16976F = f;
        this.f17004y = true;
    }

    public void onDraw(Canvas canvas) {
        if (getWidth() != 0 && this.f16986g) {
            if (!this.f16985f) {
                this.f17001v = getWidth() / 2;
                this.f17002w = getHeight() / 2;
                this.f17003x = ((float) Math.min(this.f17001v, this.f17002w)) * this.f16995p;
                if (!this.f16993n) {
                    this.f17002w = (int) (((double) this.f17002w) - (((double) (this.f17003x * this.f16996q)) * 0.75d));
                }
                this.f17005z = this.f17003x * this.f16999t;
                if (this.f16994o) {
                    this.f16971A = this.f17003x * this.f17000u;
                }
                m18920a();
                this.f17004y = true;
                this.f16985f = true;
            }
            if (this.f17004y) {
                m18921a(this.f16976F * (this.f17003x * this.f16997r), (float) this.f17001v, (float) this.f17002w, this.f17005z, this.f16972B, this.f16973C);
                if (this.f16994o) {
                    m18921a(this.f16976F * (this.f17003x * this.f16998s), (float) this.f17001v, (float) this.f17002w, this.f16971A, this.f16974D, this.f16975E);
                }
                this.f17004y = false;
            }
            m18922a(canvas, this.f17005z, this.f16989j, this.f16991l, this.f16973C, this.f16972B);
            if (this.f16994o) {
                m18922a(canvas, this.f16971A, this.f16990k, this.f16992m, this.f16975E, this.f16974D);
            }
        }
    }

    /* renamed from: a */
    private void m18921a(float f, float f2, float f3, float f4, float[] fArr, float[] fArr2) {
        float sqrt = (((float) Math.sqrt(3.0d)) * f) / 2.0f;
        float f5 = f / 2.0f;
        this.f16982c.setTextSize(f4);
        this.f16983d.setTextSize(f4);
        this.f16984e.setTextSize(f4);
        float descent = f3 - ((this.f16982c.descent() + this.f16982c.ascent()) / 2.0f);
        fArr[0] = descent - f;
        fArr2[0] = f2 - f;
        fArr[1] = descent - sqrt;
        fArr2[1] = f2 - sqrt;
        fArr[2] = descent - f5;
        fArr2[2] = f2 - f5;
        fArr[3] = descent;
        fArr2[3] = f2;
        fArr[4] = descent + f5;
        fArr2[4] = f5 + f2;
        fArr[5] = descent + sqrt;
        fArr2[5] = sqrt + f2;
        fArr[6] = descent + f;
        fArr2[6] = f2 + f;
    }

    /* renamed from: a */
    private Paint[] m18923a(String[] strArr) {
        Paint[] paintArr = new Paint[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            int parseInt = Integer.parseInt(strArr[i]);
            if (parseInt == this.f16987h) {
                paintArr[i] = this.f16983d;
            } else if (this.f16988i.mo6202a(parseInt)) {
                paintArr[i] = this.f16982c;
            } else {
                paintArr[i] = this.f16984e;
            }
        }
        return paintArr;
    }

    /* renamed from: a */
    private void m18922a(Canvas canvas, float f, Typeface typeface, String[] strArr, float[] fArr, float[] fArr2) {
        this.f16982c.setTextSize(f);
        this.f16982c.setTypeface(typeface);
        Paint[] a = m18923a(strArr);
        canvas.drawText(strArr[0], fArr[3], fArr2[0], a[0]);
        canvas.drawText(strArr[1], fArr[4], fArr2[1], a[1]);
        canvas.drawText(strArr[2], fArr[5], fArr2[2], a[2]);
        canvas.drawText(strArr[3], fArr[6], fArr2[3], a[3]);
        canvas.drawText(strArr[4], fArr[5], fArr2[4], a[4]);
        canvas.drawText(strArr[5], fArr[4], fArr2[5], a[5]);
        canvas.drawText(strArr[6], fArr[3], fArr2[6], a[6]);
        canvas.drawText(strArr[7], fArr[2], fArr2[5], a[7]);
        canvas.drawText(strArr[8], fArr[1], fArr2[4], a[8]);
        canvas.drawText(strArr[9], fArr[0], fArr2[3], a[9]);
        canvas.drawText(strArr[10], fArr[1], fArr2[2], a[10]);
        canvas.drawText(strArr[11], fArr[2], fArr2[1], a[11]);
    }

    /* renamed from: a */
    private void m18920a() {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 1.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.2f, this.f16977G);
        Keyframe ofFloat3 = Keyframe.ofFloat(1.0f, this.f16978H);
        PropertyValuesHolder ofKeyframe = PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[]{ofFloat, ofFloat2, ofFloat3});
        ofFloat2 = Keyframe.ofFloat(0.0f, 1.0f);
        ofFloat3 = Keyframe.ofFloat(1.0f, 0.0f);
        PropertyValuesHolder ofKeyframe2 = PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[]{ofFloat2, ofFloat3});
        this.f16980a = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{ofKeyframe, ofKeyframe2}).setDuration((long) 500);
        this.f16980a.addUpdateListener(this.f16979I);
        int i = (int) ((1.0f + 0.25f) * ((float) 500));
        float f = (((float) 500) * 0.25f) / ((float) i);
        float f2 = 1.0f - (0.2f * (1.0f - f));
        ofFloat = Keyframe.ofFloat(0.0f, this.f16978H);
        ofFloat3 = Keyframe.ofFloat(f, this.f16978H);
        Keyframe ofFloat4 = Keyframe.ofFloat(f2, this.f16977G);
        Keyframe ofFloat5 = Keyframe.ofFloat(1.0f, 1.0f);
        PropertyValuesHolder ofKeyframe3 = PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[]{ofFloat, ofFloat3, ofFloat4, ofFloat5});
        ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat6 = Keyframe.ofFloat(f, 0.0f);
        ofFloat3 = Keyframe.ofFloat(1.0f, 1.0f);
        PropertyValuesHolder ofKeyframe4 = PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[]{ofFloat, ofFloat6, ofFloat3});
        this.f16981b = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{ofKeyframe3, ofKeyframe4}).setDuration((long) i);
        this.f16981b.addUpdateListener(this.f16979I);
    }

    public ObjectAnimator getDisappearAnimator() {
        if (this.f16986g && this.f16985f && this.f16980a != null) {
            return this.f16980a;
        }
        Log.e("RadialTextsView", "RadialTextView was not ready for animation.");
        return null;
    }

    public ObjectAnimator getReappearAnimator() {
        if (this.f16986g && this.f16985f && this.f16981b != null) {
            return this.f16981b;
        }
        Log.e("RadialTextsView", "RadialTextView was not ready for animation.");
        return null;
    }
}
