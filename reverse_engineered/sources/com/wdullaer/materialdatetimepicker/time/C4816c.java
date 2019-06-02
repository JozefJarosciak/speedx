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
import android.util.Log;
import android.view.View;
import com.wdullaer.materialdatetimepicker.C4779R;

/* compiled from: RadialSelectorView */
/* renamed from: com.wdullaer.materialdatetimepicker.time.c */
public class C4816c extends View {
    /* renamed from: a */
    private final Paint f16946a = new Paint();
    /* renamed from: b */
    private boolean f16947b = false;
    /* renamed from: c */
    private boolean f16948c;
    /* renamed from: d */
    private float f16949d;
    /* renamed from: e */
    private float f16950e;
    /* renamed from: f */
    private float f16951f;
    /* renamed from: g */
    private float f16952g;
    /* renamed from: h */
    private float f16953h;
    /* renamed from: i */
    private float f16954i;
    /* renamed from: j */
    private float f16955j;
    /* renamed from: k */
    private boolean f16956k;
    /* renamed from: l */
    private boolean f16957l;
    /* renamed from: m */
    private int f16958m;
    /* renamed from: n */
    private int f16959n;
    /* renamed from: o */
    private int f16960o;
    /* renamed from: p */
    private int f16961p;
    /* renamed from: q */
    private float f16962q;
    /* renamed from: r */
    private float f16963r;
    /* renamed from: s */
    private int f16964s;
    /* renamed from: t */
    private int f16965t;
    /* renamed from: u */
    private C4815a f16966u;
    /* renamed from: v */
    private int f16967v;
    /* renamed from: w */
    private double f16968w;
    /* renamed from: x */
    private boolean f16969x;

    /* compiled from: RadialSelectorView */
    /* renamed from: com.wdullaer.materialdatetimepicker.time.c$a */
    private class C4815a implements AnimatorUpdateListener {
        /* renamed from: a */
        final /* synthetic */ C4816c f16945a;

        private C4815a(C4816c c4816c) {
            this.f16945a = c4816c;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f16945a.invalidate();
        }
    }

    public C4816c(Context context) {
        super(context);
    }

    /* renamed from: a */
    public void m18919a(Context context, C4820e c4820e, boolean z, boolean z2, int i, boolean z3) {
        int i2 = -1;
        if (this.f16947b) {
            Log.e("RadialSelectorView", "This RadialSelectorView may only be initialized once.");
            return;
        }
        int i3;
        Resources resources = context.getResources();
        this.f16946a.setColor(c4820e.mo6210d());
        this.f16946a.setAntiAlias(true);
        if (c4820e.mo6208b()) {
            this.f16958m = 255;
            this.f16956k = c4820e.mo6209c();
        } else {
            this.f16958m = 255;
            this.f16956k = c4820e.mo6209c();
        }
        if (this.f16956k) {
            this.f16949d = Float.parseFloat(resources.getString(C4779R.string.mdtp_circle_radius_multiplier_24HourMode));
        } else {
            this.f16949d = Float.parseFloat(resources.getString(C4779R.string.mdtp_circle_radius_multiplier));
            this.f16950e = Float.parseFloat(resources.getString(C4779R.string.mdtp_ampm_circle_radius_multiplier));
        }
        this.f16957l = z;
        if (z) {
            this.f16951f = Float.parseFloat(resources.getString(C4779R.string.mdtp_numbers_radius_multiplier_inner));
            this.f16952g = Float.parseFloat(resources.getString(C4779R.string.mdtp_numbers_radius_multiplier_outer));
        } else {
            this.f16953h = Float.parseFloat(resources.getString(C4779R.string.mdtp_numbers_radius_multiplier_normal));
        }
        this.f16954i = Float.parseFloat(resources.getString(C4779R.string.mdtp_selection_radius_multiplier));
        this.f16955j = 1.0f;
        if (z2) {
            i3 = -1;
        } else {
            i3 = 1;
        }
        this.f16962q = (((float) i3) * 0.05f) + 1.0f;
        if (z2) {
            i2 = 1;
        }
        this.f16963r = (0.3f * ((float) i2)) + 1.0f;
        this.f16966u = new C4815a();
        m18918a(i, z3, false);
        this.f16947b = true;
    }

    /* renamed from: a */
    public void m18918a(int i, boolean z, boolean z2) {
        this.f16967v = i;
        this.f16968w = (((double) i) * 3.141592653589793d) / 180.0d;
        this.f16969x = z2;
        if (!this.f16957l) {
            return;
        }
        if (z) {
            this.f16953h = this.f16951f;
        } else {
            this.f16953h = this.f16952g;
        }
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    public void setAnimationRadiusMultiplier(float f) {
        this.f16955j = f;
    }

    /* renamed from: a */
    public int m18917a(float f, float f2, boolean z, Boolean[] boolArr) {
        boolean z2 = true;
        if (!this.f16948c) {
            return -1;
        }
        boolean z3;
        double sqrt = Math.sqrt((double) (((f2 - ((float) this.f16960o)) * (f2 - ((float) this.f16960o))) + ((f - ((float) this.f16959n)) * (f - ((float) this.f16959n)))));
        if (this.f16957l) {
            if (z) {
                if (((int) Math.abs(sqrt - ((double) ((int) (((float) this.f16961p) * this.f16951f))))) <= ((int) Math.abs(sqrt - ((double) ((int) (((float) this.f16961p) * this.f16952g)))))) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolArr[0] = Boolean.valueOf(z3);
            } else {
                int i = ((int) (((float) this.f16961p) * this.f16952g)) + this.f16965t;
                int i2 = (int) (((float) this.f16961p) * ((this.f16952g + this.f16951f) / 2.0f));
                if (sqrt >= ((double) (((int) (((float) this.f16961p) * this.f16951f)) - this.f16965t)) && sqrt <= ((double) i2)) {
                    boolArr[0] = Boolean.valueOf(true);
                } else if (sqrt > ((double) i) || sqrt < ((double) i2)) {
                    return -1;
                } else {
                    boolArr[0] = Boolean.valueOf(false);
                }
            }
        } else if (!z && ((int) Math.abs(sqrt - ((double) this.f16964s))) > ((int) (((float) this.f16961p) * (1.0f - this.f16953h)))) {
            return -1;
        }
        int asin = (int) ((Math.asin(((double) Math.abs(f2 - ((float) this.f16960o))) / sqrt) * 180.0d) / 3.141592653589793d);
        z3 = f > ((float) this.f16959n);
        if (f2 >= ((float) this.f16960o)) {
            z2 = false;
        }
        if (z3 && z2) {
            return 90 - asin;
        }
        if (z3 && !z2) {
            return asin + 90;
        }
        if (z3 || z2) {
            return (z3 || !z2) ? asin : asin + 270;
        } else {
            return 270 - asin;
        }
    }

    public void onDraw(Canvas canvas) {
        int i = 1;
        if (getWidth() != 0 && this.f16947b) {
            int i2;
            if (!this.f16948c) {
                this.f16959n = getWidth() / 2;
                this.f16960o = getHeight() / 2;
                this.f16961p = (int) (((float) Math.min(this.f16959n, this.f16960o)) * this.f16949d);
                if (!this.f16956k) {
                    this.f16960o = (int) (((double) this.f16960o) - (((double) ((int) (((float) this.f16961p) * this.f16950e))) * 0.75d));
                }
                this.f16965t = (int) (((float) this.f16961p) * this.f16954i);
                this.f16948c = true;
            }
            this.f16964s = (int) ((((float) this.f16961p) * this.f16953h) * this.f16955j);
            int sin = ((int) (((double) this.f16964s) * Math.sin(this.f16968w))) + this.f16959n;
            int cos = this.f16960o - ((int) (((double) this.f16964s) * Math.cos(this.f16968w)));
            this.f16946a.setAlpha(this.f16958m);
            canvas.drawCircle((float) sin, (float) cos, (float) this.f16965t, this.f16946a);
            boolean z = this.f16969x;
            if (this.f16967v % 30 == 0) {
                i = 0;
            }
            if ((i | z) != 0) {
                this.f16946a.setAlpha(255);
                canvas.drawCircle((float) sin, (float) cos, (float) ((this.f16965t * 2) / 7), this.f16946a);
                i2 = sin;
            } else {
                cos = this.f16964s - this.f16965t;
                sin = this.f16959n + ((int) (((double) cos) * Math.sin(this.f16968w)));
                cos = this.f16960o - ((int) (((double) cos) * Math.cos(this.f16968w)));
                i2 = sin;
            }
            this.f16946a.setAlpha(255);
            this.f16946a.setStrokeWidth(3.0f);
            canvas.drawLine((float) this.f16959n, (float) this.f16960o, (float) i2, (float) cos, this.f16946a);
        }
    }

    public ObjectAnimator getDisappearAnimator() {
        if (this.f16947b && this.f16948c) {
            Keyframe ofFloat = Keyframe.ofFloat(0.0f, 1.0f);
            Keyframe ofFloat2 = Keyframe.ofFloat(0.2f, this.f16962q);
            Keyframe ofFloat3 = Keyframe.ofFloat(1.0f, this.f16963r);
            PropertyValuesHolder ofKeyframe = PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[]{ofFloat, ofFloat2, ofFloat3});
            ofFloat = Keyframe.ofFloat(0.0f, 1.0f);
            ofFloat3 = Keyframe.ofFloat(1.0f, 0.0f);
            PropertyValuesHolder ofKeyframe2 = PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[]{ofFloat, ofFloat3});
            ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{ofKeyframe, ofKeyframe2}).setDuration((long) 500);
            duration.addUpdateListener(this.f16966u);
            return duration;
        }
        Log.e("RadialSelectorView", "RadialSelectorView was not ready for animation.");
        return null;
    }

    public ObjectAnimator getReappearAnimator() {
        if (this.f16947b && this.f16948c) {
            int i = (int) ((1.0f + 0.25f) * ((float) 500));
            float f = (((float) 500) * 0.25f) / ((float) i);
            float f2 = 1.0f - (0.2f * (1.0f - f));
            Keyframe ofFloat = Keyframe.ofFloat(0.0f, this.f16963r);
            Keyframe ofFloat2 = Keyframe.ofFloat(f, this.f16963r);
            Keyframe ofFloat3 = Keyframe.ofFloat(f2, this.f16962q);
            Keyframe ofFloat4 = Keyframe.ofFloat(1.0f, 1.0f);
            PropertyValuesHolder ofKeyframe = PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
            ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
            Keyframe ofFloat5 = Keyframe.ofFloat(f, 0.0f);
            ofFloat2 = Keyframe.ofFloat(1.0f, 1.0f);
            PropertyValuesHolder ofKeyframe2 = PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[]{ofFloat, ofFloat5, ofFloat2});
            ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{ofKeyframe, ofKeyframe2}).setDuration((long) i);
            duration.addUpdateListener(this.f16966u);
            return duration;
        }
        Log.e("RadialSelectorView", "RadialSelectorView was not ready for animation.");
        return null;
    }
}
