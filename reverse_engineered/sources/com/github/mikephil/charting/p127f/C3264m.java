package com.github.mikephil.charting.p127f;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.C3241o;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet.ValuePosition;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3223i;
import com.github.mikephil.charting.p121c.C3192f;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3283i;
import java.lang.ref.WeakReference;
import java.util.List;

/* compiled from: PieChartRenderer */
/* renamed from: com.github.mikephil.charting.f.m */
public class C3264m extends C3248g {
    /* renamed from: a */
    protected PieChart f14125a;
    /* renamed from: b */
    protected Paint f14126b;
    /* renamed from: c */
    protected Paint f14127c;
    /* renamed from: d */
    protected Paint f14128d;
    /* renamed from: e */
    protected WeakReference<Bitmap> f14129e;
    /* renamed from: f */
    protected Canvas f14130f;
    /* renamed from: l */
    protected Path f14131l = new Path();
    /* renamed from: m */
    protected RectF f14132m = new RectF();
    /* renamed from: n */
    private TextPaint f14133n;
    /* renamed from: p */
    private Paint f14134p;
    /* renamed from: q */
    private StaticLayout f14135q;
    /* renamed from: r */
    private CharSequence f14136r;
    /* renamed from: s */
    private RectF f14137s = new RectF();
    /* renamed from: t */
    private RectF[] f14138t = new RectF[]{new RectF(), new RectF(), new RectF()};
    /* renamed from: u */
    private Path f14139u = new Path();
    /* renamed from: v */
    private RectF f14140v = new RectF();
    /* renamed from: w */
    private Path f14141w = new Path();

    public C3264m(PieChart pieChart, C3185a c3185a, C3275j c3275j) {
        super(c3185a, c3275j);
        this.f14125a = pieChart;
        this.f14126b = new Paint(1);
        this.f14126b.setColor(-1);
        this.f14126b.setStyle(Style.FILL);
        this.f14127c = new Paint(1);
        this.f14127c.setColor(-1);
        this.f14127c.setStyle(Style.FILL);
        this.f14127c.setAlpha(105);
        this.f14133n = new TextPaint(1);
        this.f14133n.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f14133n.setTextSize(C3283i.m15928a(12.0f));
        this.k.setTextSize(C3283i.m15928a(13.0f));
        this.k.setColor(-1);
        this.k.setTextAlign(Align.CENTER);
        this.f14134p = new Paint(1);
        this.f14134p.setColor(-1);
        this.f14134p.setTextAlign(Align.CENTER);
        this.f14134p.setTextSize(C3283i.m15928a(13.0f));
        this.f14128d = new Paint(1);
        this.f14128d.setStyle(Style.STROKE);
    }

    /* renamed from: b */
    public Paint m15786b() {
        return this.f14126b;
    }

    /* renamed from: c */
    public Paint m15788c() {
        return this.f14127c;
    }

    /* renamed from: d */
    public TextPaint m15790d() {
        return this.f14133n;
    }

    /* renamed from: e */
    public Paint m15792e() {
        return this.f14134p;
    }

    /* renamed from: a */
    public void mo3994a() {
    }

    /* renamed from: a */
    public void mo3995a(Canvas canvas) {
        int n = (int) this.o.m15879n();
        int m = (int) this.o.m15878m();
        if (!(this.f14129e != null && ((Bitmap) this.f14129e.get()).getWidth() == n && ((Bitmap) this.f14129e.get()).getHeight() == m)) {
            if (n > 0 && m > 0) {
                this.f14129e = new WeakReference(Bitmap.createBitmap(n, m, Config.ARGB_4444));
                this.f14130f = new Canvas((Bitmap) this.f14129e.get());
            } else {
                return;
            }
        }
        ((Bitmap) this.f14129e.get()).eraseColor(0);
        for (C3223i c3223i : ((C3241o) this.f14125a.getData()).m15595i()) {
            if (c3223i.mo3936z() && c3223i.mo3938F() > 0) {
                m15783a(canvas, c3223i);
            }
        }
    }

    /* renamed from: a */
    protected float m15780a(C3279e c3279e, float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = (f6 / 2.0f) + f5;
        float cos = c3279e.f14200a + (((float) Math.cos((double) ((f5 + f6) * 0.017453292f))) * f);
        float sin = c3279e.f14201b + (((float) Math.sin((double) ((f5 + f6) * 0.017453292f))) * f);
        float cos2 = c3279e.f14200a + (((float) Math.cos((double) (0.017453292f * f7))) * f);
        f7 = (((float) Math.sin((double) (f7 * 0.017453292f))) * f) + c3279e.f14201b;
        return (float) (((double) (f - ((float) ((Math.sqrt(Math.pow((double) (cos - f3), 2.0d) + Math.pow((double) (sin - f4), 2.0d)) / 2.0d) * Math.tan(((180.0d - ((double) f2)) / 2.0d) * 0.017453292519943295d))))) - Math.sqrt(Math.pow((double) (f7 - ((sin + f4) / 2.0f)), 2.0d) + Math.pow((double) (cos2 - ((cos + f3) / 2.0f)), 2.0d)));
    }

    /* renamed from: a */
    protected float m15779a(C3223i c3223i) {
        if (c3223i.mo3954b()) {
            return c3223i.mo3952a() / this.o.m15880o() > (c3223i.mo3939H() / ((C3241o) this.f14125a.getData()).m15693m()) * 2.0f ? 0.0f : c3223i.mo3952a();
        } else {
            return c3223i.mo3952a();
        }
    }

    /* renamed from: a */
    protected void m15783a(Canvas canvas, C3223i c3223i) {
        float holeRadius;
        float f;
        float rotationAngle = this.f14125a.getRotationAngle();
        float b = this.g.m15293b();
        float a = this.g.m15292a();
        RectF circleBox = this.f14125a.getCircleBox();
        int F = c3223i.mo3938F();
        float[] drawAngles = this.f14125a.getDrawAngles();
        C3279e centerCircleBox = this.f14125a.getCenterCircleBox();
        float radius = this.f14125a.getRadius();
        Object obj = (!this.f14125a.d() || this.f14125a.c()) ? null : 1;
        if (obj != null) {
            holeRadius = (this.f14125a.getHoleRadius() / 100.0f) * radius;
        } else {
            holeRadius = 0.0f;
        }
        int i = 0;
        int i2 = 0;
        while (i2 < F) {
            int i3;
            if (Math.abs(((PieEntry) c3223i.mo3948g(i2)).b()) > C3283i.f14221b) {
                i3 = i + 1;
            } else {
                i3 = i;
            }
            i2++;
            i = i3;
        }
        if (i <= 1) {
            f = 0.0f;
        } else {
            f = m15779a(c3223i);
        }
        int i4 = 0;
        float f2 = 0.0f;
        while (i4 < F) {
            float f3 = drawAngles[i4];
            if (Math.abs(c3223i.mo3948g(i4).mo3912b()) > C3283i.f14221b && !this.f14125a.a(i4)) {
                float f4;
                Object obj2 = (f <= 0.0f || f3 > 180.0f) ? null : 1;
                this.h.setColor(c3223i.mo3917b(i4));
                if (i == 1) {
                    f4 = 0.0f;
                } else {
                    f4 = f / (0.017453292f * radius);
                }
                float f5 = rotationAngle + (((f4 / 2.0f) + f2) * a);
                float f6 = (f3 - f4) * a;
                if (f6 < 0.0f) {
                    f6 = 0.0f;
                }
                this.f14139u.reset();
                float cos = (((float) Math.cos((double) (0.017453292f * f5))) * radius) + centerCircleBox.f14200a;
                float sin = (((float) Math.sin((double) (0.017453292f * f5))) * radius) + centerCircleBox.f14201b;
                if (f6 < 360.0f || f6 % 360.0f > C3283i.f14221b) {
                    this.f14139u.moveTo(cos, sin);
                    this.f14139u.arcTo(circleBox, f5, f6);
                } else {
                    this.f14139u.addCircle(centerCircleBox.f14200a, centerCircleBox.f14201b, radius, Direction.CW);
                }
                this.f14140v.set(centerCircleBox.f14200a - holeRadius, centerCircleBox.f14201b - holeRadius, centerCircleBox.f14200a + holeRadius, centerCircleBox.f14201b + holeRadius);
                float a2;
                if (obj != null && (holeRadius > 0.0f || obj2 != null)) {
                    if (obj2 != null) {
                        a2 = m15780a(centerCircleBox, radius, f3 * a, cos, sin, f5, f6);
                        if (a2 < 0.0f) {
                            a2 = -a2;
                        }
                        f4 = Math.max(holeRadius, a2);
                    } else {
                        f4 = holeRadius;
                    }
                    if (i == 1 || f4 == 0.0f) {
                        a2 = 0.0f;
                    } else {
                        a2 = f / (0.017453292f * f4);
                    }
                    cos = (((a2 / 2.0f) + f2) * a) + rotationAngle;
                    a2 = (f3 - a2) * a;
                    if (a2 < 0.0f) {
                        a2 = 0.0f;
                    }
                    cos += a2;
                    if (f6 < 360.0f || f6 % 360.0f > C3283i.f14221b) {
                        this.f14139u.lineTo(centerCircleBox.f14200a + (((float) Math.cos((double) (0.017453292f * cos))) * f4), (f4 * ((float) Math.sin((double) (0.017453292f * cos)))) + centerCircleBox.f14201b);
                        this.f14139u.arcTo(this.f14140v, cos, -a2);
                    } else {
                        this.f14139u.addCircle(centerCircleBox.f14200a, centerCircleBox.f14201b, f4, Direction.CCW);
                    }
                } else if (f6 % 360.0f > C3283i.f14221b) {
                    if (obj2 != null) {
                        float f7 = f5 + (f6 / 2.0f);
                        a2 = m15780a(centerCircleBox, radius, f3 * a, cos, sin, f5, f6);
                        this.f14139u.lineTo(centerCircleBox.f14200a + (((float) Math.cos((double) (0.017453292f * f7))) * a2), (a2 * ((float) Math.sin((double) (0.017453292f * f7)))) + centerCircleBox.f14201b);
                    } else {
                        this.f14139u.lineTo(centerCircleBox.f14200a, centerCircleBox.f14201b);
                    }
                }
                this.f14139u.close();
                this.f14130f.drawPath(this.f14139u, this.h);
            }
            i4++;
            f2 += f3 * b;
        }
        C3279e.m15900b(centerCircleBox);
    }

    /* renamed from: b */
    public void mo3997b(Canvas canvas) {
        C3279e centerCircleBox = this.f14125a.getCenterCircleBox();
        float radius = this.f14125a.getRadius();
        float rotationAngle = this.f14125a.getRotationAngle();
        float[] drawAngles = this.f14125a.getDrawAngles();
        float[] absoluteAngles = this.f14125a.getAbsoluteAngles();
        float b = this.g.m15293b();
        float a = this.g.m15292a();
        float holeRadius = this.f14125a.getHoleRadius() / 100.0f;
        float f = (radius / 10.0f) * 3.6f;
        if (this.f14125a.d()) {
            f = (radius - (radius * holeRadius)) / 2.0f;
        }
        float f2 = radius - f;
        C3241o c3241o = (C3241o) this.f14125a.getData();
        List i = c3241o.m15595i();
        float m = c3241o.m15693m();
        boolean f3 = this.f14125a.f();
        int i2 = 0;
        canvas.save();
        float a2 = C3283i.m15928a(5.0f);
        for (int i3 = 0; i3 < i.size(); i3++) {
            C3223i c3223i = (C3223i) i.get(i3);
            boolean w = c3223i.mo3933w();
            if (w || f3) {
                ValuePosition d = c3223i.mo3956d();
                ValuePosition e = c3223i.mo3957e();
                m15714b((C3220e) c3223i);
                float b2 = ((float) C3283i.m15946b(this.k, "Q")) + C3283i.m15928a(4.0f);
                C3192f o = c3223i.mo3925o();
                int F = c3223i.mo3938F();
                this.f14128d.setColor(c3223i.mo3958f());
                this.f14128d.setStrokeWidth(C3283i.m15928a(c3223i.mo3959g()));
                float a3 = m15779a(c3223i);
                C3279e a4 = C3279e.m15898a(c3223i.mo3935y());
                a4.f14200a = C3283i.m15928a(a4.f14200a);
                a4.f14201b = C3283i.m15928a(a4.f14201b);
                int i4 = i2;
                for (int i5 = 0; i5 < F; i5++) {
                    float b3;
                    Object obj;
                    Entry entry = (PieEntry) c3223i.mo3948g(i5);
                    if (i4 == 0) {
                        f = 0.0f;
                    } else {
                        f = absoluteAngles[i4 - 1] * b;
                    }
                    float f4 = rotationAngle + ((f + ((drawAngles[i4] - ((a3 / (0.017453292f * f2)) / 2.0f)) / 2.0f)) * a);
                    if (this.f14125a.g()) {
                        b3 = (entry.b() / m) * 100.0f;
                    } else {
                        b3 = entry.b();
                    }
                    float cos = (float) Math.cos((double) (0.017453292f * f4));
                    float sin = (float) Math.sin((double) (0.017453292f * f4));
                    Object obj2 = (f3 && d == ValuePosition.OUTSIDE_SLICE) ? 1 : null;
                    Object obj3 = (w && e == ValuePosition.OUTSIDE_SLICE) ? 1 : null;
                    Object obj4 = (f3 && d == ValuePosition.INSIDE_SLICE) ? 1 : null;
                    if (w && e == ValuePosition.INSIDE_SLICE) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (!(obj2 == null && obj3 == null)) {
                        float f5;
                        float f6;
                        float f7;
                        float B = c3223i.mo3949B();
                        float C = c3223i.mo3950C();
                        f = c3223i.mo3960h() / 100.0f;
                        if (this.f14125a.d()) {
                            f = (f * (radius - (radius * holeRadius))) + (radius * holeRadius);
                        } else {
                            f *= radius;
                        }
                        float abs = c3223i.mo3951D() ? (C * f2) * ((float) Math.abs(Math.sin((double) (0.017453292f * f4)))) : C * f2;
                        C = (f * cos) + centerCircleBox.f14200a;
                        float f8 = centerCircleBox.f14201b + (f * sin);
                        float f9 = centerCircleBox.f14200a + (((1.0f + B) * f2) * cos);
                        B = centerCircleBox.f14201b + (((1.0f + B) * f2) * sin);
                        if (((double) f4) % 360.0d < 90.0d || ((double) f4) % 360.0d > 270.0d) {
                            abs += f9;
                            this.k.setTextAlign(Align.LEFT);
                            if (obj2 != null) {
                                this.f14134p.setTextAlign(Align.LEFT);
                            }
                            f5 = B;
                            f6 = abs + a2;
                            f4 = B;
                            f7 = abs;
                        } else {
                            abs = f9 - abs;
                            this.k.setTextAlign(Align.RIGHT);
                            if (obj2 != null) {
                                this.f14134p.setTextAlign(Align.RIGHT);
                            }
                            f5 = B;
                            f6 = abs - a2;
                            f4 = B;
                            f7 = abs;
                        }
                        if (c3223i.mo3958f() != 1122867) {
                            canvas.drawLine(C, f8, f9, B, this.f14128d);
                            canvas.drawLine(f9, B, f7, f4, this.f14128d);
                        }
                        if (obj2 != null && obj3 != null) {
                            m15710a(canvas, o, b3, entry, 0, f6, f5, c3223i.mo3920e(i5));
                            if (i5 < c3241o.m15597k() && entry.a() != null) {
                                m15784a(canvas, entry.a(), f6, f5 + b2);
                            }
                        } else if (obj2 != null) {
                            if (i5 < c3241o.m15597k() && entry.a() != null) {
                                m15784a(canvas, entry.a(), f6, (b2 / 2.0f) + f5);
                            }
                        } else if (obj3 != null) {
                            m15710a(canvas, o, b3, entry, 0, f6, f5 + (b2 / 2.0f), c3223i.mo3920e(i5));
                        }
                    }
                    if (!(obj4 == null && obj == null)) {
                        f4 = (f2 * cos) + centerCircleBox.f14200a;
                        float f10 = (f2 * sin) + centerCircleBox.f14201b;
                        this.k.setTextAlign(Align.CENTER);
                        if (obj4 != null && obj != null) {
                            m15710a(canvas, o, b3, entry, 0, f4, f10, c3223i.mo3920e(i5));
                            if (i5 < c3241o.m15597k() && entry.a() != null) {
                                m15784a(canvas, entry.a(), f4, f10 + b2);
                            }
                        } else if (obj4 != null) {
                            if (i5 < c3241o.m15597k() && entry.a() != null) {
                                m15784a(canvas, entry.a(), f4, (b2 / 2.0f) + f10);
                            }
                        } else if (obj != null) {
                            m15710a(canvas, o, b3, entry, 0, f4, f10 + (b2 / 2.0f), c3223i.mo3920e(i5));
                        }
                    }
                    if (entry.g() != null && c3223i.mo3934x()) {
                        Drawable g = entry.g();
                        C3283i.m15935a(canvas, g, (int) (((a4.f14201b + f2) * cos) + centerCircleBox.f14200a), (int) (a4.f14200a + (((a4.f14201b + f2) * sin) + centerCircleBox.f14201b)), g.getIntrinsicWidth(), g.getIntrinsicHeight());
                    }
                    i4++;
                }
                C3279e.m15900b(a4);
                i2 = i4;
            }
        }
        C3279e.m15900b(centerCircleBox);
        canvas.restore();
    }

    /* renamed from: a */
    protected void m15784a(Canvas canvas, String str, float f, float f2) {
        canvas.drawText(str, f, f2, this.f14134p);
    }

    /* renamed from: c */
    public void mo3998c(Canvas canvas) {
        m15791d(canvas);
        canvas.drawBitmap((Bitmap) this.f14129e.get(), 0.0f, 0.0f, null);
        m15793e(canvas);
    }

    /* renamed from: d */
    protected void m15791d(Canvas canvas) {
        if (this.f14125a.d() && this.f14130f != null) {
            float radius = this.f14125a.getRadius();
            float holeRadius = (this.f14125a.getHoleRadius() / 100.0f) * radius;
            C3279e centerCircleBox = this.f14125a.getCenterCircleBox();
            if (Color.alpha(this.f14126b.getColor()) > 0) {
                this.f14130f.drawCircle(centerCircleBox.f14200a, centerCircleBox.f14201b, holeRadius, this.f14126b);
            }
            if (Color.alpha(this.f14127c.getColor()) > 0 && this.f14125a.getTransparentCircleRadius() > this.f14125a.getHoleRadius()) {
                int alpha = this.f14127c.getAlpha();
                radius *= this.f14125a.getTransparentCircleRadius() / 100.0f;
                this.f14127c.setAlpha((int) ((((float) alpha) * this.g.m15293b()) * this.g.m15292a()));
                this.f14141w.reset();
                this.f14141w.addCircle(centerCircleBox.f14200a, centerCircleBox.f14201b, radius, Direction.CW);
                this.f14141w.addCircle(centerCircleBox.f14200a, centerCircleBox.f14201b, holeRadius, Direction.CCW);
                this.f14130f.drawPath(this.f14141w, this.f14127c);
                this.f14127c.setAlpha(alpha);
            }
            C3279e.m15900b(centerCircleBox);
        }
    }

    /* renamed from: e */
    protected void m15793e(Canvas canvas) {
        CharSequence centerText = this.f14125a.getCenterText();
        if (this.f14125a.e() && centerText != null) {
            float radius;
            C3279e centerCircleBox = this.f14125a.getCenterCircleBox();
            C3279e centerTextOffset = this.f14125a.getCenterTextOffset();
            float f = centerTextOffset.f14200a + centerCircleBox.f14200a;
            float f2 = centerTextOffset.f14201b + centerCircleBox.f14201b;
            if (!this.f14125a.d() || this.f14125a.c()) {
                radius = this.f14125a.getRadius();
            } else {
                radius = this.f14125a.getRadius() * (this.f14125a.getHoleRadius() / 100.0f);
            }
            RectF rectF = this.f14138t[0];
            rectF.left = f - radius;
            rectF.top = f2 - radius;
            rectF.right = f + radius;
            rectF.bottom = radius + f2;
            RectF rectF2 = this.f14138t[1];
            rectF2.set(rectF);
            radius = this.f14125a.getCenterTextRadiusPercent() / 100.0f;
            if (((double) radius) > 0.0d) {
                rectF2.inset((rectF2.width() - (rectF2.width() * radius)) / 2.0f, (rectF2.height() - (radius * rectF2.height())) / 2.0f);
            }
            if (!(centerText.equals(this.f14136r) && rectF2.equals(this.f14137s))) {
                this.f14137s.set(rectF2);
                this.f14136r = centerText;
                this.f14135q = new StaticLayout(centerText, 0, centerText.length(), this.f14133n, (int) Math.max(Math.ceil((double) this.f14137s.width()), 1.0d), Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            }
            radius = (float) this.f14135q.getHeight();
            canvas.save();
            if (VERSION.SDK_INT >= 18) {
                Path path = this.f14131l;
                path.reset();
                path.addOval(rectF, Direction.CW);
                canvas.clipPath(path);
            }
            canvas.translate(rectF2.left, ((rectF2.height() - radius) / 2.0f) + rectF2.top);
            this.f14135q.draw(canvas);
            canvas.restore();
            C3279e.m15900b(centerCircleBox);
            C3279e.m15900b(centerTextOffset);
        }
    }

    /* renamed from: a */
    public void mo3996a(Canvas canvas, C3213d[] c3213dArr) {
        float holeRadius;
        float b = this.g.m15293b();
        float a = this.g.m15292a();
        float rotationAngle = this.f14125a.getRotationAngle();
        float[] drawAngles = this.f14125a.getDrawAngles();
        float[] absoluteAngles = this.f14125a.getAbsoluteAngles();
        C3279e centerCircleBox = this.f14125a.getCenterCircleBox();
        float radius = this.f14125a.getRadius();
        Object obj = (!this.f14125a.d() || this.f14125a.c()) ? null : 1;
        if (obj != null) {
            holeRadius = (this.f14125a.getHoleRadius() / 100.0f) * radius;
        } else {
            holeRadius = 0.0f;
        }
        RectF rectF = this.f14132m;
        rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int i = 0; i < c3213dArr.length; i++) {
            int a2 = (int) c3213dArr[i].m15423a();
            if (a2 < drawAngles.length) {
                C3223i c = ((C3241o) this.f14125a.getData()).m15692c(c3213dArr[i].m15431f());
                if (c != null && c.mo3924n()) {
                    float f;
                    int F = c.mo3938F();
                    int i2 = 0;
                    int i3 = 0;
                    while (i3 < F) {
                        int i4;
                        if (Math.abs(((PieEntry) c.mo3948g(i3)).b()) > C3283i.f14221b) {
                            i4 = i2 + 1;
                        } else {
                            i4 = i2;
                        }
                        i3++;
                        i2 = i4;
                    }
                    if (a2 == 0) {
                        f = 0.0f;
                    } else {
                        f = absoluteAngles[a2 - 1] * b;
                    }
                    float a3 = i2 <= 1 ? 0.0f : c.mo3952a();
                    float f2 = drawAngles[a2];
                    float c2 = c.mo3955c();
                    float f3 = radius + c2;
                    rectF.set(this.f14125a.getCircleBox());
                    rectF.inset(-c2, -c2);
                    Object obj2 = (a3 <= 0.0f || f2 > 180.0f) ? null : 1;
                    this.h.setColor(c.mo3917b(a2));
                    float f4 = i2 == 1 ? 0.0f : a3 / (0.017453292f * radius);
                    if (i2 == 1) {
                        c2 = 0.0f;
                    } else {
                        c2 = a3 / (0.017453292f * f3);
                    }
                    float f5 = rotationAngle + (((f4 / 2.0f) + f) * a);
                    float f6 = (f2 - f4) * a;
                    if (f6 < 0.0f) {
                        f6 = 0.0f;
                    }
                    f4 = (((c2 / 2.0f) + f) * a) + rotationAngle;
                    c2 = (f2 - c2) * a;
                    if (c2 < 0.0f) {
                        c2 = 0.0f;
                    }
                    this.f14139u.reset();
                    if (f6 < 360.0f || f6 % 360.0f > C3283i.f14221b) {
                        this.f14139u.moveTo(centerCircleBox.f14200a + (((float) Math.cos((double) (0.017453292f * f4))) * f3), (f3 * ((float) Math.sin((double) (0.017453292f * f4)))) + centerCircleBox.f14201b);
                        this.f14139u.arcTo(rectF, f4, c2);
                    } else {
                        this.f14139u.addCircle(centerCircleBox.f14200a, centerCircleBox.f14201b, f3, Direction.CW);
                    }
                    c2 = 0.0f;
                    if (obj2 != null) {
                        c2 = m15780a(centerCircleBox, radius, f2 * a, (((float) Math.cos((double) (0.017453292f * f5))) * radius) + centerCircleBox.f14200a, (((float) Math.sin((double) (0.017453292f * f5))) * radius) + centerCircleBox.f14201b, f5, f6);
                    }
                    this.f14140v.set(centerCircleBox.f14200a - holeRadius, centerCircleBox.f14201b - holeRadius, centerCircleBox.f14200a + holeRadius, centerCircleBox.f14201b + holeRadius);
                    if (obj != null && (holeRadius > 0.0f || obj2 != null)) {
                        if (obj2 != null) {
                            if (c2 < 0.0f) {
                                c2 = -c2;
                            }
                            f4 = Math.max(holeRadius, c2);
                        } else {
                            f4 = holeRadius;
                        }
                        if (i2 == 1 || f4 == 0.0f) {
                            c2 = 0.0f;
                        } else {
                            c2 = a3 / (0.017453292f * f4);
                        }
                        float f7 = (((c2 / 2.0f) + f) * a) + rotationAngle;
                        c2 = (f2 - c2) * a;
                        if (c2 < 0.0f) {
                            c2 = 0.0f;
                        }
                        f7 += c2;
                        if (f6 < 360.0f || f6 % 360.0f > C3283i.f14221b) {
                            this.f14139u.lineTo(centerCircleBox.f14200a + (((float) Math.cos((double) (0.017453292f * f7))) * f4), (f4 * ((float) Math.sin((double) (0.017453292f * f7)))) + centerCircleBox.f14201b);
                            this.f14139u.arcTo(this.f14140v, f7, -c2);
                        } else {
                            this.f14139u.addCircle(centerCircleBox.f14200a, centerCircleBox.f14201b, f4, Direction.CCW);
                        }
                    } else if (f6 % 360.0f > C3283i.f14221b) {
                        if (obj2 != null) {
                            f4 = (f6 / 2.0f) + f5;
                            this.f14139u.lineTo(centerCircleBox.f14200a + (((float) Math.cos((double) (0.017453292f * f4))) * c2), (c2 * ((float) Math.sin((double) (f4 * 0.017453292f)))) + centerCircleBox.f14201b);
                        } else {
                            this.f14139u.lineTo(centerCircleBox.f14200a, centerCircleBox.f14201b);
                        }
                    }
                    this.f14139u.close();
                    this.f14130f.drawPath(this.f14139u, this.h);
                }
            }
        }
        C3279e.m15900b(centerCircleBox);
    }

    /* renamed from: f */
    public void m15794f() {
        if (this.f14130f != null) {
            this.f14130f.setBitmap(null);
            this.f14130f = null;
        }
        if (this.f14129e != null) {
            ((Bitmap) this.f14129e.get()).recycle();
            this.f14129e.clear();
            this.f14129e = null;
        }
    }
}
