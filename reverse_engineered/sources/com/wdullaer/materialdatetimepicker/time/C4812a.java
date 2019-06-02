package com.wdullaer.materialdatetimepicker.time;

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
import com.wdullaer.materialdatetimepicker.C4783c;
import java.text.DateFormatSymbols;

/* compiled from: AmPmCirclesView */
/* renamed from: com.wdullaer.materialdatetimepicker.time.a */
public class C4812a extends View {
    /* renamed from: a */
    private final Paint f16912a = new Paint();
    /* renamed from: b */
    private int f16913b;
    /* renamed from: c */
    private int f16914c;
    /* renamed from: d */
    private int f16915d;
    /* renamed from: e */
    private int f16916e;
    /* renamed from: f */
    private int f16917f;
    /* renamed from: g */
    private int f16918g;
    /* renamed from: h */
    private int f16919h;
    /* renamed from: i */
    private float f16920i;
    /* renamed from: j */
    private float f16921j;
    /* renamed from: k */
    private String f16922k;
    /* renamed from: l */
    private String f16923l;
    /* renamed from: m */
    private boolean f16924m;
    /* renamed from: n */
    private boolean f16925n;
    /* renamed from: o */
    private boolean f16926o = false;
    /* renamed from: p */
    private boolean f16927p;
    /* renamed from: q */
    private int f16928q;
    /* renamed from: r */
    private int f16929r;
    /* renamed from: s */
    private int f16930s;
    /* renamed from: t */
    private int f16931t;
    /* renamed from: u */
    private int f16932u;
    /* renamed from: v */
    private int f16933v;

    public C4812a(Context context) {
        super(context);
    }

    /* renamed from: a */
    public void m18915a(Context context, C4820e c4820e, int i) {
        if (this.f16926o) {
            Log.e("AmPmCirclesView", "AmPmCirclesView may only be initialized once.");
            return;
        }
        Resources resources = context.getResources();
        if (c4820e.mo6208b()) {
            this.f16915d = ContextCompat.getColor(context, C4779R.color.mdtp_circle_background_dark_theme);
            this.f16916e = ContextCompat.getColor(context, C4779R.color.mdtp_white);
            this.f16918g = ContextCompat.getColor(context, C4779R.color.mdtp_date_picker_text_disabled_dark_theme);
            this.f16913b = 255;
        } else {
            this.f16915d = ContextCompat.getColor(context, C4779R.color.mdtp_white);
            this.f16916e = ContextCompat.getColor(context, C4779R.color.mdtp_ampm_text_color);
            this.f16918g = ContextCompat.getColor(context, C4779R.color.mdtp_date_picker_text_disabled);
            this.f16913b = 255;
        }
        this.f16919h = c4820e.mo6210d();
        this.f16914c = C4783c.m18765a(this.f16919h);
        this.f16917f = ContextCompat.getColor(context, C4779R.color.mdtp_white);
        this.f16912a.setTypeface(Typeface.create(resources.getString(C4779R.string.mdtp_sans_serif), 0));
        this.f16912a.setAntiAlias(true);
        this.f16912a.setTextAlign(Align.CENTER);
        this.f16920i = Float.parseFloat(resources.getString(C4779R.string.mdtp_circle_radius_multiplier));
        this.f16921j = Float.parseFloat(resources.getString(C4779R.string.mdtp_ampm_circle_radius_multiplier));
        String[] amPmStrings = new DateFormatSymbols().getAmPmStrings();
        this.f16922k = amPmStrings[0];
        this.f16923l = amPmStrings[1];
        this.f16924m = c4820e.mo6212f();
        this.f16925n = c4820e.mo6213g();
        setAmOrPm(i);
        this.f16933v = -1;
        this.f16926o = true;
    }

    public void setAmOrPm(int i) {
        this.f16932u = i;
    }

    public void setAmOrPmPressed(int i) {
        this.f16933v = i;
    }

    /* renamed from: a */
    public int m18914a(float f, float f2) {
        if (!this.f16927p) {
            return -1;
        }
        int i = (int) ((f2 - ((float) this.f16931t)) * (f2 - ((float) this.f16931t)));
        if (((int) Math.sqrt((double) (((f - ((float) this.f16929r)) * (f - ((float) this.f16929r))) + ((float) i)))) <= this.f16928q && !this.f16924m) {
            return 0;
        }
        if (((int) Math.sqrt((double) (((float) i) + ((f - ((float) this.f16930s)) * (f - ((float) this.f16930s)))))) > this.f16928q || this.f16925n) {
            return -1;
        }
        return 1;
    }

    public void onDraw(Canvas canvas) {
        int i = 255;
        if (getWidth() != 0 && this.f16926o) {
            int width;
            int height;
            int min;
            if (!this.f16927p) {
                width = getWidth() / 2;
                height = getHeight() / 2;
                min = (int) (((float) Math.min(width, height)) * this.f16920i);
                this.f16928q = (int) (((float) min) * this.f16921j);
                height = (int) (((double) height) + (((double) this.f16928q) * 0.75d));
                this.f16912a.setTextSize((float) ((this.f16928q * 3) / 4));
                this.f16931t = (height - (this.f16928q / 2)) + min;
                this.f16929r = (width - min) + this.f16928q;
                this.f16930s = (width + min) - this.f16928q;
                this.f16927p = true;
            }
            int i2 = this.f16915d;
            int i3 = this.f16916e;
            height = this.f16915d;
            width = this.f16916e;
            if (this.f16932u == 0) {
                i2 = this.f16919h;
                i3 = this.f16913b;
                min = this.f16917f;
            } else if (this.f16932u == 1) {
                min = this.f16919h;
                height = this.f16913b;
                width = this.f16917f;
                int i4 = height;
                height = min;
                min = i3;
                i3 = 255;
                i = i4;
            } else {
                min = i3;
                i3 = 255;
            }
            if (this.f16933v == 0) {
                i2 = this.f16914c;
                i3 = this.f16913b;
            } else if (this.f16933v == 1) {
                height = this.f16914c;
                i = this.f16913b;
            }
            if (this.f16924m) {
                i2 = this.f16915d;
                min = this.f16918g;
            }
            if (this.f16925n) {
                height = this.f16915d;
                width = this.f16918g;
            }
            this.f16912a.setColor(i2);
            this.f16912a.setAlpha(i3);
            canvas.drawCircle((float) this.f16929r, (float) this.f16931t, (float) this.f16928q, this.f16912a);
            this.f16912a.setColor(height);
            this.f16912a.setAlpha(i);
            canvas.drawCircle((float) this.f16930s, (float) this.f16931t, (float) this.f16928q, this.f16912a);
            this.f16912a.setColor(min);
            i = this.f16931t - (((int) (this.f16912a.descent() + this.f16912a.ascent())) / 2);
            canvas.drawText(this.f16922k, (float) this.f16929r, (float) i, this.f16912a);
            this.f16912a.setColor(width);
            canvas.drawText(this.f16923l, (float) this.f16930s, (float) i, this.f16912a);
        }
    }
}
