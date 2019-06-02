package com.wdullaer.materialdatetimepicker.time;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.widget.FrameLayout;
import com.wdullaer.materialdatetimepicker.C4779R;
import com.wdullaer.materialdatetimepicker.time.C4819d.C4804b;
import com.wdullaer.materialdatetimepicker.time.Timepoint.TYPE;
import java.util.Calendar;

public class RadialPickerLayout extends FrameLayout implements OnTouchListener {
    /* renamed from: A */
    private AccessibilityManager f16880A;
    /* renamed from: B */
    private AnimatorSet f16881B;
    /* renamed from: C */
    private Handler f16882C = new Handler();
    /* renamed from: a */
    private final int f16883a;
    /* renamed from: b */
    private final int f16884b;
    /* renamed from: c */
    private Timepoint f16885c;
    /* renamed from: d */
    private C4820e f16886d;
    /* renamed from: e */
    private C4810a f16887e;
    /* renamed from: f */
    private boolean f16888f;
    /* renamed from: g */
    private Timepoint f16889g;
    /* renamed from: h */
    private boolean f16890h;
    /* renamed from: i */
    private int f16891i;
    /* renamed from: j */
    private C4813b f16892j;
    /* renamed from: k */
    private C4812a f16893k;
    /* renamed from: l */
    private C4819d f16894l;
    /* renamed from: m */
    private C4819d f16895m;
    /* renamed from: n */
    private C4819d f16896n;
    /* renamed from: o */
    private C4816c f16897o;
    /* renamed from: p */
    private C4816c f16898p;
    /* renamed from: q */
    private C4816c f16899q;
    /* renamed from: r */
    private View f16900r;
    /* renamed from: s */
    private int[] f16901s;
    /* renamed from: t */
    private boolean f16902t;
    /* renamed from: u */
    private int f16903u = -1;
    /* renamed from: v */
    private boolean f16904v;
    /* renamed from: w */
    private boolean f16905w;
    /* renamed from: x */
    private int f16906x;
    /* renamed from: y */
    private float f16907y;
    /* renamed from: z */
    private float f16908z;

    /* renamed from: com.wdullaer.materialdatetimepicker.time.RadialPickerLayout$1 */
    class C48051 implements C4804b {
        /* renamed from: a */
        final /* synthetic */ RadialPickerLayout f16874a;

        C48051(RadialPickerLayout radialPickerLayout) {
            this.f16874a = radialPickerLayout;
        }

        /* renamed from: a */
        public boolean mo6202a(int i) {
            return !this.f16874a.f16886d.mo6207a(new Timepoint(this.f16874a.f16889g.m18906a(), this.f16874a.f16889g.m18908b(), i), 2);
        }
    }

    /* renamed from: com.wdullaer.materialdatetimepicker.time.RadialPickerLayout$2 */
    class C48062 implements C4804b {
        /* renamed from: a */
        final /* synthetic */ RadialPickerLayout f16875a;

        C48062(RadialPickerLayout radialPickerLayout) {
            this.f16875a = radialPickerLayout;
        }

        /* renamed from: a */
        public boolean mo6202a(int i) {
            if (this.f16875a.f16886d.mo6207a(new Timepoint(this.f16875a.f16889g.m18906a(), i, this.f16875a.f16889g.m18909c()), 1)) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: com.wdullaer.materialdatetimepicker.time.RadialPickerLayout$3 */
    class C48073 implements C4804b {
        /* renamed from: a */
        final /* synthetic */ RadialPickerLayout f16876a;

        C48073(RadialPickerLayout radialPickerLayout) {
            this.f16876a = radialPickerLayout;
        }

        /* renamed from: a */
        public boolean mo6202a(int i) {
            if (!this.f16876a.f16890h && this.f16876a.getIsCurrentlyAmOrPm() == 1) {
                i = (i + 12) % 24;
            }
            if (!this.f16876a.f16890h && this.f16876a.getIsCurrentlyAmOrPm() == 0) {
                i %= 12;
            }
            if (this.f16876a.f16886d.mo6207a(new Timepoint(i, this.f16876a.f16889g.m18908b(), this.f16876a.f16889g.m18909c()), 0)) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: com.wdullaer.materialdatetimepicker.time.RadialPickerLayout$4 */
    class C48084 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ RadialPickerLayout f16877a;

        C48084(RadialPickerLayout radialPickerLayout) {
            this.f16877a = radialPickerLayout;
        }

        public void run() {
            this.f16877a.f16893k.setAmOrPmPressed(this.f16877a.f16903u);
            this.f16877a.f16893k.invalidate();
        }
    }

    /* renamed from: com.wdullaer.materialdatetimepicker.time.RadialPickerLayout$a */
    public interface C4810a {
        /* renamed from: a */
        void mo6204a();

        /* renamed from: a */
        void mo6205a(int i);

        /* renamed from: a */
        void mo6206a(Timepoint timepoint);
    }

    public RadialPickerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnTouchListener(this);
        this.f16883a = ViewConfiguration.get(context).getScaledTouchSlop();
        this.f16884b = ViewConfiguration.getTapTimeout();
        this.f16904v = false;
        this.f16892j = new C4813b(context);
        addView(this.f16892j);
        this.f16893k = new C4812a(context);
        addView(this.f16893k);
        this.f16897o = new C4816c(context);
        addView(this.f16897o);
        this.f16898p = new C4816c(context);
        addView(this.f16898p);
        this.f16899q = new C4816c(context);
        addView(this.f16899q);
        this.f16894l = new C4819d(context);
        addView(this.f16894l);
        this.f16895m = new C4819d(context);
        addView(this.f16895m);
        this.f16896n = new C4819d(context);
        addView(this.f16896n);
        m18887a();
        this.f16885c = null;
        this.f16902t = true;
        this.f16900r = new View(context);
        this.f16900r.setLayoutParams(new LayoutParams(-1, -1));
        this.f16900r.setBackgroundColor(ContextCompat.getColor(context, C4779R.color.mdtp_transparent_black));
        this.f16900r.setVisibility(4);
        addView(this.f16900r);
        this.f16880A = (AccessibilityManager) context.getSystemService("accessibility");
        this.f16888f = false;
    }

    public void setOnValueSelectedListener(C4810a c4810a) {
        this.f16887e = c4810a;
    }

    /* renamed from: a */
    public void m18902a(Context context, C4830f c4830f, Timepoint timepoint, boolean z) {
        if (this.f16888f) {
            Log.e("RadialPickerLayout", "Time has already been initialized.");
            return;
        }
        int a;
        this.f16886d = c4830f;
        boolean z2 = this.f16880A.isTouchExplorationEnabled() || z;
        this.f16890h = z2;
        this.f16892j.m18916a(context, this.f16886d);
        this.f16892j.invalidate();
        if (!this.f16890h) {
            this.f16893k.m18915a(context, this.f16886d, timepoint.m18910d() ? 0 : 1);
            this.f16893k.invalidate();
        }
        C48051 c48051 = new C48051(this);
        C48062 c48062 = new C48062(this);
        C4804b c48073 = new C48073(this);
        context.getResources();
        int[] iArr = new int[]{12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] iArr2 = new int[]{0, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        int[] iArr3 = new int[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
        int[] iArr4 = new int[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
        String[] strArr = new String[12];
        String[] strArr2 = new String[12];
        String[] strArr3 = new String[12];
        String[] strArr4 = new String[12];
        for (int i = 0; i < 12; i++) {
            strArr[i] = z ? String.format("%02d", new Object[]{Integer.valueOf(iArr2[i])}) : String.format("%d", new Object[]{Integer.valueOf(iArr[i])});
            strArr2[i] = String.format("%d", new Object[]{Integer.valueOf(iArr[i])});
            strArr3[i] = String.format("%02d", new Object[]{Integer.valueOf(iArr3[i])});
            strArr4[i] = String.format("%02d", new Object[]{Integer.valueOf(iArr4[i])});
        }
        C4819d c4819d = this.f16894l;
        if (!z) {
            strArr2 = null;
        }
        c4819d.m18924a(context, strArr, strArr2, this.f16886d, c48073, true);
        C4819d c4819d2 = this.f16894l;
        if (z) {
            a = timepoint.m18906a();
        } else {
            a = iArr[timepoint.m18906a() % 12];
        }
        c4819d2.setSelection(a);
        this.f16894l.invalidate();
        this.f16895m.m18924a(context, strArr3, null, this.f16886d, (C4804b) c48062, false);
        this.f16895m.setSelection(timepoint.m18908b());
        this.f16895m.invalidate();
        this.f16896n.m18924a(context, strArr4, null, this.f16886d, (C4804b) c48051, false);
        this.f16896n.setSelection(timepoint.m18909c());
        this.f16896n.invalidate();
        this.f16889g = timepoint;
        this.f16897o.m18919a(context, this.f16886d, z, true, (timepoint.m18906a() % 12) * 30, m18891a(timepoint.m18906a()));
        this.f16898p.m18919a(context, this.f16886d, false, false, timepoint.m18908b() * 6, false);
        this.f16899q.m18919a(context, this.f16886d, false, false, timepoint.m18909c() * 6, false);
        this.f16888f = true;
    }

    public void setTime(Timepoint timepoint) {
        m18888a(0, timepoint);
    }

    /* renamed from: a */
    private void m18888a(int i, Timepoint timepoint) {
        Timepoint a = m18886a(timepoint, i);
        this.f16889g = a;
        m18890a(a, false, i);
    }

    /* renamed from: a */
    private boolean m18891a(int i) {
        return this.f16890h && i <= 12 && i != 0;
    }

    public int getHours() {
        return this.f16889g.m18906a();
    }

    public int getMinutes() {
        return this.f16889g.m18908b();
    }

    public int getSeconds() {
        return this.f16889g.m18909c();
    }

    public Timepoint getTime() {
        return this.f16889g;
    }

    private int getCurrentlyShowingValue() {
        switch (getCurrentItemShowing()) {
            case 0:
                return this.f16889g.m18906a();
            case 1:
                return this.f16889g.m18908b();
            case 2:
                return this.f16889g.m18909c();
            default:
                return -1;
        }
    }

    public int getIsCurrentlyAmOrPm() {
        if (this.f16889g.m18910d()) {
            return 0;
        }
        if (this.f16889g.m18911e()) {
            return 1;
        }
        return -1;
    }

    public void setAmOrPm(int i) {
        this.f16893k.setAmOrPm(i);
        this.f16893k.invalidate();
        Timepoint timepoint = new Timepoint(this.f16889g);
        if (i == 0) {
            timepoint.m18912f();
        } else if (i == 1) {
            timepoint.m18913g();
        }
        timepoint = m18886a(timepoint, 0);
        m18890a(timepoint, false, 0);
        this.f16889g = timepoint;
        this.f16887e.mo6206a(timepoint);
    }

    /* renamed from: a */
    private void m18887a() {
        this.f16901s = new int[361];
        int i = 0;
        int i2 = 8;
        int i3 = 1;
        for (int i4 = 0; i4 < 361; i4++) {
            this.f16901s[i4] = i;
            if (i3 == i2) {
                i3 = i + 6;
                if (i3 == 360) {
                    i2 = 7;
                } else if (i3 % 30 == 0) {
                    i2 = 14;
                } else {
                    i2 = 4;
                }
                i = i3;
                i3 = 1;
            } else {
                i3++;
            }
        }
    }

    /* renamed from: b */
    private int m18893b(int i) {
        if (this.f16901s == null) {
            return -1;
        }
        return this.f16901s[i];
    }

    /* renamed from: a */
    private static int m18880a(int i, int i2) {
        int i3 = (i / 30) * 30;
        int i4 = i3 + 30;
        if (i2 == 1) {
            return i4;
        }
        if (i2 == -1) {
            if (i == i3) {
                return i3 - 30;
            }
            return i3;
        } else if (i - i3 >= i4 - i) {
            return i4;
        } else {
            return i3;
        }
    }

    /* renamed from: a */
    private Timepoint m18886a(Timepoint timepoint, int i) {
        switch (i) {
            case 0:
                return this.f16886d.mo6203a(timepoint, TYPE.HOUR);
            case 1:
                return this.f16886d.mo6203a(timepoint, TYPE.MINUTE);
            case 2:
                return this.f16886d.mo6203a(timepoint, TYPE.SECOND);
            default:
                return this.f16889g;
        }
    }

    /* renamed from: a */
    private void m18890a(Timepoint timepoint, boolean z, int i) {
        switch (i) {
            case 0:
                int a = timepoint.m18906a();
                boolean a2 = m18891a(a);
                int i2 = ((a % 12) * 360) / 12;
                if (!this.f16890h) {
                    a %= 12;
                }
                if (!this.f16890h && a == 0) {
                    a += 12;
                }
                this.f16897o.m18918a(i2, a2, z);
                this.f16894l.setSelection(a);
                if (timepoint.m18908b() != this.f16889g.m18908b()) {
                    this.f16898p.m18918a((timepoint.m18908b() * 360) / 60, a2, z);
                    this.f16895m.setSelection(timepoint.m18908b());
                }
                if (timepoint.m18909c() != this.f16889g.m18909c()) {
                    this.f16899q.m18918a((timepoint.m18909c() * 360) / 60, a2, z);
                    this.f16896n.setSelection(timepoint.m18909c());
                    break;
                }
                break;
            case 1:
                this.f16898p.m18918a((timepoint.m18908b() * 360) / 60, false, z);
                this.f16895m.setSelection(timepoint.m18908b());
                if (timepoint.m18909c() != this.f16889g.m18909c()) {
                    this.f16899q.m18918a((timepoint.m18909c() * 360) / 60, false, z);
                    this.f16896n.setSelection(timepoint.m18909c());
                    break;
                }
                break;
            case 2:
                this.f16899q.m18918a((timepoint.m18909c() * 360) / 60, false, z);
                this.f16896n.setSelection(timepoint.m18909c());
                break;
        }
        switch (getCurrentItemShowing()) {
            case 0:
                this.f16897o.invalidate();
                this.f16894l.invalidate();
                return;
            case 1:
                this.f16898p.invalidate();
                this.f16895m.invalidate();
                return;
            case 2:
                this.f16899q.invalidate();
                this.f16896n.invalidate();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private Timepoint m18881a(int i, boolean z, boolean z2) {
        int i2 = 6;
        if (i == -1) {
            return null;
        }
        int currentItemShowing = getCurrentItemShowing();
        int i3 = (z2 || !(currentItemShowing == 1 || currentItemShowing == 2)) ? 0 : 1;
        if (i3 != 0) {
            i3 = m18893b(i);
        } else {
            i3 = m18880a(i, 0);
        }
        switch (currentItemShowing) {
            case 0:
                i2 = 30;
                break;
        }
        if (currentItemShowing == 0) {
            if (this.f16890h) {
                if (i3 == 0 && z) {
                    i3 = 360;
                } else if (i3 == 360 && !z) {
                    i3 = 0;
                }
            } else if (i3 == 0) {
                i3 = 360;
            }
        } else if (i3 == 360 && (currentItemShowing == 1 || currentItemShowing == 2)) {
            i3 = 0;
        }
        i2 = i3 / i2;
        if (currentItemShowing == 0 && this.f16890h && !z && i3 != 0) {
            i2 += 12;
        }
        switch (currentItemShowing) {
            case 0:
                if (!(this.f16890h || getIsCurrentlyAmOrPm() != 1 || i3 == 360)) {
                    i2 += 12;
                }
                if (!this.f16890h && getIsCurrentlyAmOrPm() == 0 && i3 == 360) {
                    i2 = 0;
                }
                return new Timepoint(i2, this.f16889g.m18908b(), this.f16889g.m18909c());
            case 1:
                return new Timepoint(this.f16889g.m18906a(), i2, this.f16889g.m18909c());
            case 2:
                return new Timepoint(this.f16889g.m18906a(), this.f16889g.m18908b(), i2);
            default:
                return this.f16889g;
        }
    }

    /* renamed from: a */
    private int m18879a(float f, float f2, boolean z, Boolean[] boolArr) {
        switch (getCurrentItemShowing()) {
            case 0:
                return this.f16897o.m18917a(f, f2, z, boolArr);
            case 1:
                return this.f16898p.m18917a(f, f2, z, boolArr);
            case 2:
                return this.f16899q.m18917a(f, f2, z, boolArr);
            default:
                return -1;
        }
    }

    public int getCurrentItemShowing() {
        if (this.f16891i == 0 || this.f16891i == 1 || this.f16891i == 2) {
            return this.f16891i;
        }
        Log.e("RadialPickerLayout", "Current item showing was unfortunately set to " + this.f16891i);
        return -1;
    }

    /* renamed from: a */
    public void m18901a(int i, boolean z) {
        int i2 = 1;
        if (i == 0 || i == 1 || i == 2) {
            int currentItemShowing = getCurrentItemShowing();
            this.f16891i = i;
            if (!z || i == currentItemShowing) {
                int i3 = i == 0 ? 1 : 0;
                if (i == 1) {
                    currentItemShowing = 1;
                } else {
                    currentItemShowing = 0;
                }
                if (i != 2) {
                    i2 = 0;
                }
                this.f16894l.setAlpha((float) i3);
                this.f16897o.setAlpha((float) i3);
                this.f16895m.setAlpha((float) currentItemShowing);
                this.f16898p.setAlpha((float) currentItemShowing);
                this.f16896n.setAlpha((float) i2);
                this.f16899q.setAlpha((float) i2);
                return;
            }
            Animator[] animatorArr = new ObjectAnimator[4];
            if (i == 1 && currentItemShowing == 0) {
                animatorArr[0] = this.f16894l.getDisappearAnimator();
                animatorArr[1] = this.f16897o.getDisappearAnimator();
                animatorArr[2] = this.f16895m.getReappearAnimator();
                animatorArr[3] = this.f16898p.getReappearAnimator();
            } else if (i == 0 && currentItemShowing == 1) {
                animatorArr[0] = this.f16894l.getReappearAnimator();
                animatorArr[1] = this.f16897o.getReappearAnimator();
                animatorArr[2] = this.f16895m.getDisappearAnimator();
                animatorArr[3] = this.f16898p.getDisappearAnimator();
            } else if (i == 1 && currentItemShowing == 2) {
                animatorArr[0] = this.f16896n.getDisappearAnimator();
                animatorArr[1] = this.f16899q.getDisappearAnimator();
                animatorArr[2] = this.f16895m.getReappearAnimator();
                animatorArr[3] = this.f16898p.getReappearAnimator();
            } else if (i == 0 && currentItemShowing == 2) {
                animatorArr[0] = this.f16896n.getDisappearAnimator();
                animatorArr[1] = this.f16899q.getDisappearAnimator();
                animatorArr[2] = this.f16894l.getReappearAnimator();
                animatorArr[3] = this.f16897o.getReappearAnimator();
            } else if (i == 2 && currentItemShowing == 1) {
                animatorArr[0] = this.f16896n.getReappearAnimator();
                animatorArr[1] = this.f16899q.getReappearAnimator();
                animatorArr[2] = this.f16895m.getDisappearAnimator();
                animatorArr[3] = this.f16898p.getDisappearAnimator();
            } else if (i == 2 && currentItemShowing == 0) {
                animatorArr[0] = this.f16896n.getReappearAnimator();
                animatorArr[1] = this.f16899q.getReappearAnimator();
                animatorArr[2] = this.f16894l.getDisappearAnimator();
                animatorArr[3] = this.f16897o.getDisappearAnimator();
            }
            if (this.f16881B != null && this.f16881B.isRunning()) {
                this.f16881B.end();
            }
            this.f16881B = new AnimatorSet();
            this.f16881B.playTogether(animatorArr);
            this.f16881B.start();
            return;
        }
        Log.e("RadialPickerLayout", "TimePicker does not support view at index " + i);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        final Boolean[] boolArr = new Boolean[]{Boolean.valueOf(false)};
        int a;
        Timepoint timepoint;
        switch (motionEvent.getAction()) {
            case 0:
                if (!this.f16902t) {
                    return true;
                }
                this.f16907y = x;
                this.f16908z = y;
                this.f16885c = null;
                this.f16904v = false;
                this.f16905w = true;
                if (this.f16890h) {
                    this.f16903u = -1;
                } else {
                    this.f16903u = this.f16893k.m18914a(x, y);
                }
                if (this.f16903u == 0 || this.f16903u == 1) {
                    this.f16886d.mo6211e();
                    this.f16906x = -1;
                    this.f16882C.postDelayed(new C48084(this), (long) this.f16884b);
                    return true;
                }
                this.f16906x = m18879a(x, y, this.f16880A.isTouchExplorationEnabled(), boolArr);
                if (this.f16886d.mo6207a(m18881a(this.f16906x, boolArr[0].booleanValue(), false), getCurrentItemShowing())) {
                    this.f16906x = -1;
                }
                if (this.f16906x == -1) {
                    return true;
                }
                this.f16886d.mo6211e();
                this.f16882C.postDelayed(new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ RadialPickerLayout f16879b;

                    public void run() {
                        this.f16879b.f16904v = true;
                        this.f16879b.f16885c = this.f16879b.m18881a(this.f16879b.f16906x, boolArr[0].booleanValue(), false);
                        this.f16879b.f16885c = this.f16879b.m18886a(this.f16879b.f16885c, this.f16879b.getCurrentItemShowing());
                        this.f16879b.m18890a(this.f16879b.f16885c, true, this.f16879b.getCurrentItemShowing());
                        this.f16879b.f16887e.mo6206a(this.f16879b.f16885c);
                    }
                }, (long) this.f16884b);
                return true;
            case 1:
                if (this.f16902t) {
                    this.f16882C.removeCallbacksAndMessages(null);
                    this.f16905w = false;
                    if (this.f16903u == 0 || this.f16903u == 1) {
                        a = this.f16893k.m18914a(x, y);
                        this.f16893k.setAmOrPmPressed(-1);
                        this.f16893k.invalidate();
                        if (a == this.f16903u) {
                            this.f16893k.setAmOrPm(a);
                            if (getIsCurrentlyAmOrPm() != a) {
                                timepoint = new Timepoint(this.f16889g);
                                if (this.f16903u == 0) {
                                    timepoint.m18912f();
                                } else if (this.f16903u == 1) {
                                    timepoint.m18913g();
                                }
                                timepoint = m18886a(timepoint, 0);
                                m18890a(timepoint, false, 0);
                                this.f16889g = timepoint;
                                this.f16887e.mo6206a(timepoint);
                            }
                        }
                        this.f16903u = -1;
                        break;
                    }
                    if (this.f16906x != -1) {
                        int a2 = m18879a(x, y, this.f16904v, boolArr);
                        if (a2 != -1) {
                            timepoint = m18886a(m18881a(a2, boolArr[0].booleanValue(), !this.f16904v), getCurrentItemShowing());
                            m18890a(timepoint, false, getCurrentItemShowing());
                            this.f16889g = timepoint;
                            this.f16887e.mo6206a(timepoint);
                            this.f16887e.mo6205a(getCurrentItemShowing());
                        }
                    }
                    this.f16904v = false;
                    return true;
                }
                Log.d("RadialPickerLayout", "Input was disabled, but received ACTION_UP.");
                this.f16887e.mo6204a();
                return true;
                break;
            case 2:
                if (this.f16902t) {
                    float abs = Math.abs(y - this.f16908z);
                    float abs2 = Math.abs(x - this.f16907y);
                    if (this.f16904v || abs2 > ((float) this.f16883a) || abs > ((float) this.f16883a)) {
                        if (this.f16903u == 0 || this.f16903u == 1) {
                            this.f16882C.removeCallbacksAndMessages(null);
                            if (this.f16893k.m18914a(x, y) != this.f16903u) {
                                this.f16893k.setAmOrPmPressed(-1);
                                this.f16893k.invalidate();
                                this.f16903u = -1;
                                break;
                            }
                        } else if (this.f16906x != -1) {
                            this.f16904v = true;
                            this.f16882C.removeCallbacksAndMessages(null);
                            a = m18879a(x, y, true, boolArr);
                            if (a == -1) {
                                return true;
                            }
                            timepoint = m18886a(m18881a(a, boolArr[0].booleanValue(), false), getCurrentItemShowing());
                            m18890a(timepoint, true, getCurrentItemShowing());
                            if (timepoint == null || this.f16885c == null || this.f16885c.equals(timepoint)) {
                                return true;
                            }
                            this.f16886d.mo6211e();
                            this.f16885c = timepoint;
                            this.f16887e.mo6206a(timepoint);
                            return true;
                        }
                    }
                }
                Log.e("RadialPickerLayout", "Input was disabled, but received ACTION_MOVE.");
                return true;
                break;
        }
        return false;
    }

    /* renamed from: a */
    public boolean m18903a(boolean z) {
        int i = 0;
        if (this.f16905w && !z) {
            return false;
        }
        this.f16902t = z;
        View view = this.f16900r;
        if (z) {
            i = 4;
        }
        view.setVisibility(i);
        return true;
    }

    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (VERSION.SDK_INT >= 21) {
            accessibilityNodeInfo.addAction(AccessibilityAction.ACTION_SCROLL_BACKWARD);
            accessibilityNodeInfo.addAction(AccessibilityAction.ACTION_SCROLL_FORWARD);
            return;
        }
        accessibilityNodeInfo.addAction(4096);
        accessibilityNodeInfo.addAction(8192);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() != 32) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int i;
        accessibilityEvent.getText().clear();
        Calendar instance = Calendar.getInstance();
        instance.set(10, getHours());
        instance.set(12, getMinutes());
        instance.set(13, getSeconds());
        long timeInMillis = instance.getTimeInMillis();
        if (this.f16890h) {
            i = 129;
        } else {
            i = 1;
        }
        accessibilityEvent.getText().add(DateUtils.formatDateTime(getContext(), timeInMillis, i));
        return true;
    }

    @SuppressLint({"NewApi"})
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        int i2 = 6;
        if (super.performAccessibilityAction(i, bundle)) {
            return true;
        }
        int i3;
        if (i == 4096) {
            i3 = 1;
        } else if (i == 8192) {
            i3 = -1;
        } else {
            i3 = 0;
        }
        if (i3 == 0) {
            return false;
        }
        int i4;
        boolean z;
        Timepoint timepoint;
        int currentlyShowingValue = getCurrentlyShowingValue();
        int currentItemShowing = getCurrentItemShowing();
        if (currentItemShowing == 0) {
            i2 = 30;
            currentlyShowingValue %= 12;
        } else if (!(currentItemShowing == 1 || currentItemShowing == 2)) {
            i2 = 0;
        }
        boolean a = m18880a(currentlyShowingValue * i2, i3) / i2;
        if (currentItemShowing != 0) {
            i4 = 55;
            z = false;
        } else if (this.f16890h) {
            i4 = 23;
            z = false;
        } else {
            i4 = 12;
            z = true;
        }
        if (a > i4) {
            i4 = z;
        } else if (a >= z) {
            boolean z2 = a;
        }
        switch (currentItemShowing) {
            case 0:
                timepoint = new Timepoint(i4, this.f16889g.m18908b(), this.f16889g.m18909c());
                break;
            case 1:
                timepoint = new Timepoint(this.f16889g.m18906a(), i4, this.f16889g.m18909c());
                break;
            case 2:
                timepoint = new Timepoint(this.f16889g.m18906a(), this.f16889g.m18908b(), i4);
                break;
            default:
                timepoint = this.f16889g;
                break;
        }
        m18888a(currentItemShowing, timepoint);
        this.f16887e.mo6206a(timepoint);
        return true;
    }
}
