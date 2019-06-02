package com.wdullaer.materialdatetimepicker.time;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import com.wdullaer.materialdatetimepicker.C4779R;

/* compiled from: CircleView */
/* renamed from: com.wdullaer.materialdatetimepicker.time.b */
public class C4813b extends View {
    /* renamed from: a */
    private final Paint f16934a = new Paint();
    /* renamed from: b */
    private boolean f16935b;
    /* renamed from: c */
    private int f16936c;
    /* renamed from: d */
    private int f16937d;
    /* renamed from: e */
    private float f16938e;
    /* renamed from: f */
    private float f16939f;
    /* renamed from: g */
    private boolean f16940g = false;
    /* renamed from: h */
    private boolean f16941h;
    /* renamed from: i */
    private int f16942i;
    /* renamed from: j */
    private int f16943j;
    /* renamed from: k */
    private int f16944k;

    public C4813b(Context context) {
        super(context);
    }

    /* renamed from: a */
    public void m18916a(Context context, C4820e c4820e) {
        if (this.f16940g) {
            Log.e("CircleView", "CircleView may only be initialized once.");
            return;
        }
        Resources resources = context.getResources();
        this.f16936c = ContextCompat.getColor(context, c4820e.mo6208b() ? C4779R.color.mdtp_circle_background_dark_theme : C4779R.color.mdtp_circle_color);
        this.f16937d = c4820e.mo6210d();
        this.f16934a.setAntiAlias(true);
        this.f16935b = c4820e.mo6209c();
        if (this.f16935b) {
            this.f16938e = Float.parseFloat(resources.getString(C4779R.string.mdtp_circle_radius_multiplier_24HourMode));
        } else {
            this.f16938e = Float.parseFloat(resources.getString(C4779R.string.mdtp_circle_radius_multiplier));
            this.f16939f = Float.parseFloat(resources.getString(C4779R.string.mdtp_ampm_circle_radius_multiplier));
        }
        this.f16940g = true;
    }

    public void onDraw(Canvas canvas) {
        if (getWidth() != 0 && this.f16940g) {
            if (!this.f16941h) {
                this.f16942i = getWidth() / 2;
                this.f16943j = getHeight() / 2;
                this.f16944k = (int) (((float) Math.min(this.f16942i, this.f16943j)) * this.f16938e);
                if (!this.f16935b) {
                    this.f16943j = (int) (((double) this.f16943j) - (((double) ((int) (((float) this.f16944k) * this.f16939f))) * 0.75d));
                }
                this.f16941h = true;
            }
            this.f16934a.setColor(this.f16936c);
            canvas.drawCircle((float) this.f16942i, (float) this.f16943j, (float) this.f16944k, this.f16934a);
            this.f16934a.setColor(this.f16937d);
            canvas.drawCircle((float) this.f16942i, (float) this.f16943j, 8.0f, this.f16934a);
        }
    }
}
