package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import com.github.mikephil.charting.p183g.C3274b;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.ArrayList;
import java.util.List;

public class Legend extends C3206b {
    /* renamed from: B */
    private boolean f13904B;
    /* renamed from: C */
    private List<C3274b> f13905C;
    /* renamed from: D */
    private List<Boolean> f13906D;
    /* renamed from: E */
    private List<C3274b> f13907E;
    /* renamed from: a */
    public float f13908a;
    /* renamed from: b */
    public float f13909b;
    /* renamed from: c */
    public float f13910c;
    /* renamed from: d */
    public float f13911d;
    /* renamed from: e */
    private C3208e[] f13912e;
    /* renamed from: f */
    private C3208e[] f13913f;
    /* renamed from: g */
    private boolean f13914g;
    /* renamed from: h */
    private LegendHorizontalAlignment f13915h;
    /* renamed from: i */
    private LegendVerticalAlignment f13916i;
    /* renamed from: j */
    private LegendOrientation f13917j;
    /* renamed from: k */
    private boolean f13918k;
    /* renamed from: l */
    private LegendDirection f13919l;
    /* renamed from: m */
    private LegendForm f13920m;
    /* renamed from: n */
    private float f13921n;
    /* renamed from: o */
    private float f13922o;
    /* renamed from: p */
    private DashPathEffect f13923p;
    /* renamed from: q */
    private float f13924q;
    /* renamed from: r */
    private float f13925r;
    /* renamed from: s */
    private float f13926s;
    /* renamed from: t */
    private float f13927t;
    /* renamed from: u */
    private float f13928u;

    /* renamed from: com.github.mikephil.charting.components.Legend$1 */
    static /* synthetic */ class C32051 {
        /* renamed from: a */
        static final /* synthetic */ int[] f13896a = new int[Legend$LegendPosition.values().length];

        static {
            f13897b = new int[LegendOrientation.values().length];
            try {
                f13897b[LegendOrientation.VERTICAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f13897b[LegendOrientation.HORIZONTAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f13896a[Legend$LegendPosition.LEFT_OF_CHART.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f13896a[Legend$LegendPosition.LEFT_OF_CHART_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f13896a[Legend$LegendPosition.LEFT_OF_CHART_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f13896a[Legend$LegendPosition.RIGHT_OF_CHART.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f13896a[Legend$LegendPosition.RIGHT_OF_CHART_INSIDE.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f13896a[Legend$LegendPosition.RIGHT_OF_CHART_CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f13896a[Legend$LegendPosition.ABOVE_CHART_LEFT.ordinal()] = 7;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f13896a[Legend$LegendPosition.ABOVE_CHART_CENTER.ordinal()] = 8;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f13896a[Legend$LegendPosition.ABOVE_CHART_RIGHT.ordinal()] = 9;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f13896a[Legend$LegendPosition.BELOW_CHART_LEFT.ordinal()] = 10;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f13896a[Legend$LegendPosition.BELOW_CHART_CENTER.ordinal()] = 11;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f13896a[Legend$LegendPosition.BELOW_CHART_RIGHT.ordinal()] = 12;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f13896a[Legend$LegendPosition.PIECHART_CENTER.ordinal()] = 13;
            } catch (NoSuchFieldError e15) {
            }
        }
    }

    public enum LegendDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public enum LegendForm {
        NONE,
        EMPTY,
        DEFAULT,
        SQUARE,
        CIRCLE,
        LINE
    }

    public enum LegendHorizontalAlignment {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum LegendOrientation {
        HORIZONTAL,
        VERTICAL
    }

    public enum LegendVerticalAlignment {
        TOP,
        CENTER,
        BOTTOM
    }

    public Legend() {
        this.f13912e = new C3208e[0];
        this.f13914g = false;
        this.f13915h = LegendHorizontalAlignment.LEFT;
        this.f13916i = LegendVerticalAlignment.BOTTOM;
        this.f13917j = LegendOrientation.HORIZONTAL;
        this.f13918k = false;
        this.f13919l = LegendDirection.LEFT_TO_RIGHT;
        this.f13920m = LegendForm.SQUARE;
        this.f13921n = 8.0f;
        this.f13922o = 3.0f;
        this.f13923p = null;
        this.f13924q = 6.0f;
        this.f13925r = 0.0f;
        this.f13926s = 5.0f;
        this.f13927t = 3.0f;
        this.f13928u = 0.95f;
        this.f13908a = 0.0f;
        this.f13909b = 0.0f;
        this.f13910c = 0.0f;
        this.f13911d = 0.0f;
        this.f13904B = false;
        this.f13905C = new ArrayList(16);
        this.f13906D = new ArrayList(16);
        this.f13907E = new ArrayList(16);
        this.z = C3283i.m15928a(10.0f);
        this.w = C3283i.m15928a(5.0f);
        this.x = C3283i.m15928a(3.0f);
    }

    /* renamed from: a */
    public void m15348a(List<C3208e> list) {
        this.f13912e = (C3208e[]) list.toArray(new C3208e[list.size()]);
    }

    /* renamed from: a */
    public C3208e[] m15349a() {
        return this.f13912e;
    }

    /* renamed from: a */
    public float m15346a(Paint paint) {
        float f = 0.0f;
        float a = C3283i.m15928a(this.f13926s);
        C3208e[] c3208eArr = this.f13912e;
        int length = c3208eArr.length;
        int i = 0;
        float f2 = 0.0f;
        while (i < length) {
            C3208e c3208e = c3208eArr[i];
            float a2 = C3283i.m15928a(Float.isNaN(c3208e.f13960c) ? this.f13921n : c3208e.f13960c);
            if (a2 <= f) {
                a2 = f;
            }
            String str = c3208e.f13958a;
            if (str == null) {
                f = f2;
            } else {
                f = (float) C3283i.m15931a(paint, str);
                if (f <= f2) {
                    f = f2;
                }
            }
            i++;
            f2 = f;
            f = a2;
        }
        return (f2 + f) + a;
    }

    /* renamed from: b */
    public float m15350b(Paint paint) {
        float f = 0.0f;
        for (C3208e c3208e : this.f13912e) {
            String str = c3208e.f13958a;
            if (str != null) {
                float b = (float) C3283i.m15946b(paint, str);
                if (b > f) {
                    f = b;
                }
            }
        }
        return f;
    }

    /* renamed from: b */
    public C3208e[] m15351b() {
        return this.f13913f;
    }

    /* renamed from: c */
    public boolean m15352c() {
        return this.f13914g;
    }

    /* renamed from: d */
    public LegendHorizontalAlignment m15353d() {
        return this.f13915h;
    }

    /* renamed from: e */
    public LegendVerticalAlignment m15354e() {
        return this.f13916i;
    }

    /* renamed from: f */
    public LegendOrientation m15355f() {
        return this.f13917j;
    }

    /* renamed from: g */
    public boolean m15356g() {
        return this.f13918k;
    }

    /* renamed from: h */
    public LegendDirection m15357h() {
        return this.f13919l;
    }

    /* renamed from: i */
    public LegendForm m15358i() {
        return this.f13920m;
    }

    /* renamed from: j */
    public float m15359j() {
        return this.f13921n;
    }

    /* renamed from: k */
    public float m15360k() {
        return this.f13922o;
    }

    /* renamed from: l */
    public DashPathEffect m15361l() {
        return this.f13923p;
    }

    /* renamed from: m */
    public float m15362m() {
        return this.f13924q;
    }

    /* renamed from: n */
    public float m15363n() {
        return this.f13925r;
    }

    /* renamed from: o */
    public float m15364o() {
        return this.f13926s;
    }

    /* renamed from: p */
    public float m15365p() {
        return this.f13927t;
    }

    /* renamed from: q */
    public float m15366q() {
        return this.f13928u;
    }

    /* renamed from: r */
    public List<C3274b> m15367r() {
        return this.f13905C;
    }

    /* renamed from: s */
    public List<Boolean> m15368s() {
        return this.f13906D;
    }

    /* renamed from: t */
    public List<C3274b> m15369t() {
        return this.f13907E;
    }

    /* renamed from: a */
    public void m15347a(Paint paint, C3275j c3275j) {
        float a = C3283i.m15928a(this.f13921n);
        float a2 = C3283i.m15928a(this.f13927t);
        float a3 = C3283i.m15928a(this.f13926s);
        float a4 = C3283i.m15928a(this.f13924q);
        float a5 = C3283i.m15928a(this.f13925r);
        boolean z = this.f13904B;
        C3208e[] c3208eArr = this.f13912e;
        int length = c3208eArr.length;
        this.f13911d = m15346a(paint);
        this.f13910c = m15350b(paint);
        float f;
        float f2;
        Object obj;
        float f3;
        float f4;
        switch (this.f13917j) {
            case VERTICAL:
                f = 0.0f;
                f2 = 0.0f;
                float f5 = 0.0f;
                float a6 = C3283i.m15929a(paint);
                Object obj2 = null;
                int i = 0;
                while (i < length) {
                    C3208e c3208e = c3208eArr[i];
                    obj = c3208e.f13959b != LegendForm.NONE ? 1 : null;
                    if (Float.isNaN(c3208e.f13960c)) {
                        f3 = a;
                    } else {
                        f3 = C3283i.m15928a(c3208e.f13960c);
                    }
                    String str = c3208e.f13958a;
                    if (obj2 == null) {
                        f5 = 0.0f;
                    }
                    if (obj != null) {
                        if (obj2 != null) {
                            f5 += a2;
                        }
                        f5 += f3;
                    }
                    if (str != null) {
                        if (obj != null && obj2 == null) {
                            f3 = f5 + a3;
                            f5 = f2;
                            obj = obj2;
                            f4 = f;
                        } else if (obj2 != null) {
                            f4 = Math.max(f, f5);
                            f5 = f2 + (a6 + a5);
                            f3 = 0.0f;
                            obj = null;
                        } else {
                            obj = obj2;
                            f3 = f5;
                            f5 = f2;
                            f4 = f;
                        }
                        f3 += (float) C3283i.m15931a(paint, str);
                        if (i < length - 1) {
                            f2 = (a6 + a5) + f5;
                        } else {
                            f2 = f5;
                        }
                    } else {
                        obj = 1;
                        f3 += f5;
                        if (i < length - 1) {
                            f3 += a2;
                            f4 = f;
                        } else {
                            f4 = f;
                        }
                    }
                    f = Math.max(f4, f3);
                    i++;
                    obj2 = obj;
                    f5 = f3;
                }
                this.f13908a = f;
                this.f13909b = f2;
                break;
            case HORIZONTAL:
                int i2;
                float a7 = C3283i.m15929a(paint);
                float b = C3283i.m15942b(paint) + a5;
                float i3 = c3275j.m15870i() * this.f13928u;
                f2 = 0.0f;
                f4 = 0.0f;
                int i4 = -1;
                this.f13906D.clear();
                this.f13905C.clear();
                this.f13907E.clear();
                int i5 = 0;
                a5 = 0.0f;
                while (i5 < length) {
                    float f6;
                    int i6;
                    C3208e c3208e2 = c3208eArr[i5];
                    obj = c3208e2.f13959b != LegendForm.NONE ? 1 : null;
                    if (Float.isNaN(c3208e2.f13960c)) {
                        f3 = a;
                    } else {
                        f3 = C3283i.m15928a(c3208e2.f13960c);
                    }
                    String str2 = c3208e2.f13958a;
                    this.f13906D.add(Boolean.valueOf(false));
                    if (i4 == -1) {
                        a5 = 0.0f;
                    } else {
                        a5 += a2;
                    }
                    if (str2 != null) {
                        this.f13905C.add(C3283i.m15950c(paint, str2));
                        f6 = obj != null ? a3 + f3 : 0.0f;
                        i6 = i4;
                        f = ((C3274b) this.f13905C.get(i5)).f14176a + (a5 + f6);
                    } else {
                        this.f13905C.add(C3274b.m15844a(0.0f, 0.0f));
                        if (obj == null) {
                            f3 = 0.0f;
                        }
                        f6 = a5 + f3;
                        if (i4 == -1) {
                            i6 = i5;
                            f = f6;
                        } else {
                            i6 = i4;
                            f = f6;
                        }
                    }
                    if (str2 != null || i5 == length - 1) {
                        if (f4 == 0.0f) {
                            f6 = 0.0f;
                        } else {
                            f6 = a4;
                        }
                        if (!z || f4 == 0.0f || i3 - f4 >= f6 + f) {
                            f6 = (f6 + f) + f4;
                            f4 = f2;
                        } else {
                            this.f13907E.add(C3274b.m15844a(f4, a7));
                            f4 = Math.max(f2, f4);
                            List list = this.f13906D;
                            if (i6 > -1) {
                                i2 = i6;
                            } else {
                                i2 = i5;
                            }
                            list.set(i2, Boolean.valueOf(true));
                            f6 = f;
                        }
                        if (i5 == length - 1) {
                            this.f13907E.add(C3274b.m15844a(f6, a7));
                            f4 = Math.max(f4, f6);
                        }
                    } else {
                        f6 = f4;
                        f4 = f2;
                    }
                    if (str2 != null) {
                        i6 = -1;
                    }
                    i5++;
                    a5 = f;
                    f2 = f4;
                    f4 = f6;
                    i4 = i6;
                }
                this.f13908a = f2;
                f3 = a7 * ((float) this.f13907E.size());
                if (this.f13907E.size() == 0) {
                    i2 = 0;
                } else {
                    i2 = this.f13907E.size() - 1;
                }
                this.f13909b = (((float) i2) * b) + f3;
                break;
        }
        this.f13909b += this.x;
        this.f13908a += this.w;
    }
}
