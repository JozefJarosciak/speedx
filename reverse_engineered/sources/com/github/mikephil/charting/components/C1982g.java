package com.github.mikephil.charting.components;

import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.github.mikephil.charting.charts.C1475c;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3279e;
import java.lang.ref.WeakReference;

/* compiled from: MarkerView */
/* renamed from: com.github.mikephil.charting.components.g */
public class C1982g extends RelativeLayout implements C1981d {
    /* renamed from: a */
    private C3279e f8913a = new C3279e();
    /* renamed from: b */
    private C3279e f8914b = new C3279e();
    /* renamed from: c */
    private WeakReference<C1475c> f8915c;

    public C1982g(Context context, int i) {
        super(context);
        setupLayoutResource(i);
    }

    private void setupLayoutResource(int i) {
        View inflate = LayoutInflater.from(getContext()).inflate(i, this);
        inflate.setLayoutParams(new LayoutParams(-2, -2));
        inflate.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        inflate.layout(0, 0, inflate.getMeasuredWidth(), inflate.getMeasuredHeight());
    }

    public void setOffset(C3279e c3279e) {
        this.f8913a = c3279e;
        if (this.f8913a == null) {
            this.f8913a = new C3279e();
        }
    }

    public C3279e getOffset() {
        return this.f8913a;
    }

    public void setChartView(C1475c c1475c) {
        this.f8915c = new WeakReference(c1475c);
    }

    public C1475c getChartView() {
        return this.f8915c == null ? null : (C1475c) this.f8915c.get();
    }

    /* renamed from: a */
    public C3279e m10211a(float f, float f2) {
        C3279e offset = getOffset();
        this.f8914b.f14200a = offset.f14200a;
        this.f8914b.f14201b = offset.f14201b;
        C1475c chartView = getChartView();
        float width = (float) getWidth();
        float height = (float) getHeight();
        if (this.f8914b.f14200a + f < 0.0f) {
            this.f8914b.f14200a = -f;
        } else if (chartView != null && (f + width) + this.f8914b.f14200a > ((float) chartView.getWidth())) {
            this.f8914b.f14200a = (((float) chartView.getWidth()) - f) - width;
        }
        if (this.f8914b.f14201b + f2 < 0.0f) {
            this.f8914b.f14201b = -f2;
        } else if (chartView != null && (f2 + height) + this.f8914b.f14201b > ((float) chartView.getHeight())) {
            this.f8914b.f14201b = (((float) chartView.getHeight()) - f2) - height;
        }
        return this.f8914b;
    }

    /* renamed from: a */
    public void mo3336a(Entry entry, C3213d c3213d) {
        measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    /* renamed from: a */
    public void mo3335a(Canvas canvas, float f, float f2) {
        C3279e a = m10211a(f, f2);
        int save = canvas.save();
        canvas.translate(a.f14200a + f, a.f14201b + f2);
        draw(canvas);
        canvas.restoreToCount(save);
    }
}
