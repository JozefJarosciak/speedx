package com.beastbikes.android.modules.user.filter.p155b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.dto.C2412b;
import com.beastbikes.android.modules.user.dto.C2414c;
import com.beastbikes.android.modules.user.dto.C2415d;
import com.beastbikes.android.modules.user.dto.C2416e;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* compiled from: DynamicStickerView */
/* renamed from: com.beastbikes.android.modules.user.filter.b.a */
public class C2441a extends C2440b {
    /* renamed from: d */
    private static final SimpleDateFormat f11464d = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    /* renamed from: a */
    float f11465a;
    /* renamed from: b */
    float f11466b;
    /* renamed from: e */
    private final Rect f11467e;
    /* renamed from: f */
    private final Rect f11468f;
    /* renamed from: g */
    private C2412b f11469g;
    /* renamed from: h */
    private Date f11470h;
    /* renamed from: i */
    private Canvas f11471i;
    /* renamed from: j */
    private Paint f11472j;
    /* renamed from: k */
    private ActivityDTO f11473k;
    /* renamed from: l */
    private List<C2414c> f11474l;

    public C2441a(Context context) {
        this(context, null);
    }

    public C2441a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public C2441a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11467e = new Rect();
        this.f11468f = new Rect();
        this.f11470h = null;
        this.f11474l = new ArrayList();
    }

    public void setActivityDto(ActivityDTO activityDTO) {
        super.setActivityDto(activityDTO);
        if (activityDTO == null) {
            this.f11470h = new Date();
        } else {
            this.f11470h = new Date(activityDTO.getStartTime());
        }
    }

    protected void onDraw(Canvas canvas) {
        int i;
        int i2 = 0;
        super.onDraw(canvas);
        this.f11471i = canvas;
        this.f11472j = getPaint();
        this.f11465a = (float) getWidth();
        this.f11466b = (float) getHeight();
        if (this.f11474l.size() > 0) {
            for (i = 0; i < this.f11474l.size(); i++) {
                Bitmap a = m12303a(i);
                if (a != null) {
                    m12304a(a, (C2414c) this.f11474l.get(i));
                }
            }
        }
        this.f11473k = getActivityDTO();
        this.f11469g = getWaterMark();
        if (this.f11469g != null) {
            List d = this.f11469g.m12244d();
            if (d != null && d.size() > 0) {
                for (i = 0; i < d.size(); i++) {
                    m12309a((C2416e) d.get(i));
                }
            }
            List e = this.f11469g.m12245e();
            if (e != null && e.size() > 0) {
                while (i2 < e.size()) {
                    m12305a((C2415d) e.get(i2));
                    i2++;
                }
            }
        }
    }

    /* renamed from: a */
    private void m12305a(C2415d c2415d) {
        float a = this.f11465a / ((float) this.f11469g.m12241a());
        float b = this.f11466b / ((float) this.f11469g.m12242b());
        this.f11472j.setColor(m12300a() ? ViewCompat.MEASURED_STATE_MASK : -1);
        this.f11472j.setStrokeWidth(3.0f);
        switch (c2415d.m12252g()) {
            case 1:
                this.f11471i.drawLine(((float) c2415d.m12255i()) * a, ((float) c2415d.m12254h()) * b, a * ((float) (c2415d.m12255i() + c2415d.m12268a())), b * ((float) c2415d.m12254h()), this.f11472j);
                return;
            case 3:
                this.f11471i.drawLine((((float) getWidth()) - (((float) c2415d.m12256j()) * a)) - (((float) c2415d.m12268a()) * a), ((float) c2415d.m12254h()) * b, ((float) getWidth()) - (a * ((float) c2415d.m12256j())), b * ((float) c2415d.m12254h()), this.f11472j);
                return;
            case 7:
                this.f11471i.drawLine(((float) c2415d.m12255i()) * a, ((float) getHeight()) - (((float) c2415d.m12257k()) * b), a * ((float) (c2415d.m12255i() + c2415d.m12268a())), ((float) getHeight()) - (b * ((float) c2415d.m12257k())), this.f11472j);
                return;
            case 9:
                this.f11471i.drawLine((((float) getWidth()) - (((float) c2415d.m12256j()) * a)) - (((float) c2415d.m12268a()) * a), ((float) getHeight()) - (((float) c2415d.m12257k()) * b), ((float) getWidth()) - (a * ((float) c2415d.m12256j())), ((float) getHeight()) - (b * ((float) c2415d.m12257k())), this.f11472j);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m12304a(Bitmap bitmap, C2414c c2414c) {
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        this.f11467e.left = 0;
        this.f11467e.top = 0;
        this.f11467e.right = (int) width;
        this.f11467e.bottom = (int) height;
        this.f11471i.drawBitmap(bitmap, this.f11467e, m12306b(bitmap, c2414c), this.f11472j);
    }

    /* renamed from: a */
    public void m12309a(C2416e c2416e) {
        float a = this.f11465a / ((float) this.f11469g.m12241a());
        float b = this.f11466b / ((float) this.f11469g.m12242b());
        this.f11472j.setColor(m12300a() ? ViewCompat.MEASURED_STATE_MASK : -1);
        this.f11472j.setTextSize(((float) c2416e.m12273d()) * a);
        if (c2416e.m12275f().equals("AvenirNext-Bold")) {
            this.f11472j.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            this.f11472j.setTypeface(Typeface.DEFAULT);
        }
        int textXY = getTextXY();
        String b2 = m12307b(c2416e);
        if (!(TextUtils.isEmpty(c2416e.m12272c()) || C1849a.m9645b(this.c))) {
            if (c2416e.m12272c().equals("DISTANCE(KM)")) {
                c2416e.m12270a("DISTANCE(mi)");
            } else if (c2416e.m12272c().equals("SPEED(KM/H)")) {
                c2416e.m12270a("SPEED(MPH)");
            } else if (c2416e.m12272c().equals("里程(km)")) {
                c2416e.m12270a("里程(mi)");
            } else if (c2416e.m12272c().equals("速度(km/h)")) {
                c2416e.m12270a("速度(MPH)");
            }
        }
        int i;
        int e;
        int a2;
        switch (c2416e.m12252g()) {
            case 1:
                this.f11472j.setTextAlign(Align.LEFT);
                i = (int) (((float) c2416e.m12255i()) * a);
                e = (int) (b * ((float) (c2416e.m12274e() + c2416e.m12254h())));
                this.f11471i.drawText(b2, (float) i, (float) (e - textXY), this.f11472j);
                if (!TextUtils.isEmpty(c2416e.m12272c())) {
                    a2 = m12302a(this.f11472j, b2);
                    this.f11472j.setTextSize(a * 20.0f);
                    this.f11472j.setTextAlign(Align.CENTER);
                    this.f11471i.drawText(c2416e.m12272c(), (float) ((a2 / 2) + i), (float) (((e - textXY) + 10) + getTextHeight()), this.f11472j);
                    return;
                }
                return;
            case 2:
                this.f11472j.setTextAlign(Align.CENTER);
                i = getWidth() / 2;
                e = (int) (b * ((float) (c2416e.m12274e() + c2416e.m12254h())));
                this.f11471i.drawText(b2, (float) i, (float) (e - textXY), this.f11472j);
                if (!TextUtils.isEmpty(c2416e.m12272c())) {
                    this.f11472j.setTextSize(a * 20.0f);
                    this.f11471i.drawText(c2416e.m12272c(), (float) i, (float) (((e - textXY) + 10) + getTextHeight()), this.f11472j);
                    return;
                }
                return;
            case 3:
                this.f11472j.setTextAlign(Align.RIGHT);
                i = (int) (((float) getWidth()) - (((float) c2416e.m12256j()) * a));
                e = (int) (b * ((float) (c2416e.m12274e() + c2416e.m12254h())));
                this.f11471i.drawText(b2, (float) i, (float) (e - textXY), this.f11472j);
                this.f11472j.setTextAlign(Align.CENTER);
                if (!TextUtils.isEmpty(c2416e.m12272c())) {
                    a2 = m12302a(this.f11472j, b2);
                    this.f11472j.setTextSize(a * 20.0f);
                    this.f11472j.setTextAlign(Align.CENTER);
                    this.f11471i.drawText(c2416e.m12272c(), (float) (i - (a2 / 2)), (float) (((e - textXY) + 10) + getTextHeight()), this.f11472j);
                    return;
                }
                return;
            case 4:
                this.f11472j.setTextAlign(Align.LEFT);
                this.f11471i.drawText(b2, (float) ((int) (a * ((float) c2416e.m12255i()))), (float) (((getHeight() / 2) + ((this.f11472j.getFontMetricsInt().bottom - this.f11472j.getFontMetricsInt().top) / 2)) - textXY), this.f11472j);
                return;
            case 5:
                this.f11472j.setTextAlign(Align.CENTER);
                this.f11471i.drawText(b2, (float) (getWidth() / 2), (float) (((getHeight() / 2) + ((this.f11472j.getFontMetricsInt().bottom - this.f11472j.getFontMetricsInt().top) / 2)) - textXY), this.f11472j);
                return;
            case 6:
                this.f11472j.setTextAlign(Align.RIGHT);
                this.f11471i.drawText(b2, (float) ((int) (((float) getWidth()) - (a * ((float) c2416e.m12256j())))), (float) (((getHeight() / 2) + ((this.f11472j.getFontMetricsInt().bottom - this.f11472j.getFontMetricsInt().top) / 2)) - textXY), this.f11472j);
                return;
            case 7:
                this.f11472j.setTextAlign(Align.LEFT);
                i = (int) (((float) c2416e.m12255i()) * a);
                e = (int) (((float) getHeight()) - (b * ((float) c2416e.m12257k())));
                this.f11471i.drawText(b2, (float) i, (float) (e - textXY), this.f11472j);
                if (!TextUtils.isEmpty(c2416e.m12272c())) {
                    a2 = m12302a(this.f11472j, b2);
                    this.f11472j.setTextSize(a * 20.0f);
                    this.f11472j.setTextAlign(Align.CENTER);
                    this.f11471i.drawText(c2416e.m12272c(), (float) ((a2 / 2) + i), (float) (((e - textXY) + 10) + getTextHeight()), this.f11472j);
                    return;
                }
                return;
            case 8:
                this.f11472j.setTextAlign(Align.CENTER);
                i = getWidth() / 2;
                e = (int) (((float) getHeight()) - (b * ((float) c2416e.m12257k())));
                this.f11471i.drawText(b2, (float) i, (float) (e - textXY), this.f11472j);
                if (!TextUtils.isEmpty(c2416e.m12272c())) {
                    this.f11472j.setTextSize(a * 20.0f);
                    this.f11471i.drawText(c2416e.m12272c(), (float) i, (float) (((e - textXY) + 10) + getTextHeight()), this.f11472j);
                    return;
                }
                return;
            case 9:
                this.f11472j.setTextAlign(Align.RIGHT);
                i = (int) (((float) getWidth()) - (((float) c2416e.m12256j()) * a));
                e = (int) (((float) getHeight()) - (b * ((float) c2416e.m12257k())));
                this.f11471i.drawText(b2, (float) i, (float) (e - textXY), this.f11472j);
                if (!TextUtils.isEmpty(c2416e.m12272c())) {
                    a2 = m12302a(this.f11472j, b2);
                    this.f11472j.setTextSize(a * 20.0f);
                    this.f11472j.setTextAlign(Align.CENTER);
                    this.f11471i.drawText(c2416e.m12272c(), (float) (i - (a2 / 2)), (float) (((e - textXY) + 10) + getTextHeight()), this.f11472j);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    private String m12307b(C2416e c2416e) {
        int a = c2416e.m12269a();
        String str = "";
        if (this.f11473k == null) {
            return str;
        }
        switch (a) {
            case 1:
                if (C1849a.m9645b(this.c)) {
                    return String.format(Locale.getDefault(), "%.2f", new Object[]{Double.valueOf(this.f11473k.getTotalDistance())}) + c2416e.m12271b();
                } else if (c2416e.m12271b().equals("KM") || c2416e.m12271b().equals("km")) {
                    return String.format(Locale.getDefault(), "%.2f", new Object[]{Double.valueOf(C1849a.m9638a(this.f11473k.getTotalDistance()))}) + "mi";
                } else {
                    return String.format(Locale.getDefault(), "%.2f", new Object[]{Double.valueOf(C1849a.m9638a(this.f11473k.getTotalDistance()))}) + c2416e.m12271b();
                }
            case 2:
                long j;
                long j2;
                long elapsedTime = (long) this.f11473k.getElapsedTime();
                if (elapsedTime > 0) {
                    j = elapsedTime / 3600;
                    j2 = (elapsedTime % 3600) / 60;
                    elapsedTime = (elapsedTime % 3600) % 60;
                } else {
                    j = 0;
                    j2 = 0;
                    elapsedTime = 0;
                }
                return String.format(Locale.getDefault(), "%02d:%02d:%02d", new Object[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(elapsedTime)});
            case 3:
                if (C1849a.m9645b(this.c)) {
                    return String.format(Locale.getDefault(), "%.2f", new Object[]{Double.valueOf(this.f11473k.getVelocity())}) + c2416e.m12271b();
                } else if (c2416e.m12271b().equals("KM") || c2416e.m12271b().equals("km")) {
                    return String.format(Locale.getDefault(), "%.2f", new Object[]{Double.valueOf(C1849a.m9638a(this.f11473k.getVelocity()))}) + "mi";
                } else {
                    return String.format(Locale.getDefault(), "%.2f", new Object[]{Double.valueOf(C1849a.m9638a(this.f11473k.getVelocity()))}) + c2416e.m12271b();
                }
            case 4:
                return f11464d.format(this.f11470h);
            case 5:
                str = this.f11473k.getCityName();
                if (TextUtils.isEmpty(str)) {
                    return "";
                }
                return str;
            case 6:
                return this.f11473k.getNickname();
            default:
                return str;
        }
    }

    private int getTextXY() {
        return this.f11472j.getFontMetricsInt().descent;
    }

    private int getTextHeight() {
        FontMetricsInt fontMetricsInt = this.f11472j.getFontMetricsInt();
        return fontMetricsInt.descent - fontMetricsInt.ascent;
    }

    /* renamed from: b */
    private Rect m12306b(Bitmap bitmap, C2414c c2414c) {
        float width = (float) getWidth();
        float height = (float) getHeight();
        int c = c2414c.m12264c();
        int d = c2414c.m12265d();
        width /= (float) c2414c.m12258l();
        height /= (float) c2414c.m12259m();
        Rect rect = new Rect();
        switch (c2414c.m12252g()) {
            case 1:
                rect.left = (int) (((float) c2414c.m12255i()) * width);
                rect.right = ((int) (((float) c) * width)) + rect.left;
                rect.top = (int) (height * ((float) c2414c.m12254h()));
                rect.bottom = ((int) (width * ((float) c2414c.m12265d()))) + rect.top;
                break;
            case 2:
                rect.left = ((int) (((float) getWidth()) - (((float) c2414c.m12264c()) * width))) / 2;
                rect.right = ((int) (((float) getWidth()) + (((float) c2414c.m12264c()) * width))) / 2;
                rect.top = (int) (height * ((float) c2414c.m12254h()));
                rect.bottom = ((int) (width * ((float) c2414c.m12265d()))) + rect.top;
                break;
            case 3:
                rect.right = (int) (((float) getWidth()) - (((float) c2414c.m12256j()) * width));
                rect.left = rect.right - ((int) (((float) c2414c.m12264c()) * width));
                rect.top = (int) (height * ((float) c2414c.m12254h()));
                rect.bottom = ((int) (width * ((float) c2414c.m12265d()))) + rect.top;
                break;
            case 4:
                rect.left = (int) (((float) c2414c.m12255i()) * width);
                rect.right = ((int) (((float) c) * width)) + rect.left;
                rect.top = (int) (height * ((float) d));
                rect.bottom = ((int) (width * ((float) c2414c.m12265d()))) + rect.top;
                break;
            case 5:
                rect.left = ((int) (((float) getWidth()) - (((float) c2414c.m12264c()) * width))) / 2;
                rect.right = ((int) ((width * ((float) c2414c.m12264c())) + ((float) getWidth()))) / 2;
                rect.top = ((int) (((float) getHeight()) - (((float) c2414c.m12265d()) * height))) / 2;
                rect.bottom = ((int) (((float) getHeight()) + (height * ((float) c2414c.m12265d())))) / 2;
                break;
            case 6:
                rect.right = (int) (((float) getWidth()) - (((float) c2414c.m12256j()) * width));
                rect.left = rect.right - ((int) (width * ((float) c2414c.m12264c())));
                rect.top = ((int) (((float) getHeight()) - (((float) c2414c.m12265d()) * height))) / 2;
                rect.bottom = ((int) (((float) getHeight()) + (height * ((float) c2414c.m12265d())))) / 2;
                break;
            case 7:
                rect.left = (int) (((float) c2414c.m12255i()) * width);
                rect.bottom = (int) (((float) getHeight()) - (((float) c2414c.m12257k()) * height));
                rect.top = rect.bottom - ((int) (height * ((float) d)));
                rect.right = ((int) (width * ((float) c))) + rect.left;
                break;
            case 8:
                rect.left = ((int) (((float) getWidth()) - (((float) c2414c.m12264c()) * width))) / 2;
                rect.right = ((int) ((width * ((float) c2414c.m12264c())) + ((float) getWidth()))) / 2;
                rect.bottom = (int) (((float) getHeight()) - (((float) c2414c.m12257k()) * height));
                rect.top = rect.bottom - ((int) (height * ((float) d)));
                break;
            case 9:
                rect.right = (int) (((float) getWidth()) - (((float) c2414c.m12256j()) * width));
                rect.left = rect.right - ((int) (width * ((float) c2414c.m12264c())));
                rect.bottom = (int) (((float) getHeight()) - (((float) c2414c.m12257k()) * height));
                rect.top = rect.bottom - ((int) (height * ((float) d)));
                break;
        }
        return rect;
    }

    /* renamed from: a */
    private Bitmap m12303a(int i) {
        if (m12300a()) {
            return ((C2414c) this.f11474l.get(i)).m12267f();
        }
        return ((C2414c) this.f11474l.get(i)).m12266e();
    }

    /* renamed from: a */
    public void m12308a(C2414c c2414c) {
        this.f11474l.add(c2414c);
        invalidate();
    }

    /* renamed from: a */
    private int m12302a(Paint paint, String str) {
        int i = 0;
        if (str != null && str.length() > 0) {
            int length = str.length();
            float[] fArr = new float[length];
            paint.getTextWidths(str, fArr);
            int i2 = 0;
            while (i2 < length) {
                int ceil = ((int) Math.ceil((double) fArr[i2])) + i;
                i2++;
                i = ceil;
            }
        }
        return i;
    }
}
