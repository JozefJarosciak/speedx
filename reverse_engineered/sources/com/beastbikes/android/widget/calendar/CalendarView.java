package com.beastbikes.android.widget.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.CalendarDto;
import java.util.HashMap;

public class CalendarView extends LinearLayout implements OnClickListener {
    /* renamed from: a */
    private DisplayMetrics f12252a;
    /* renamed from: b */
    private TextView f12253b;
    /* renamed from: c */
    private MonthView f12254c;
    /* renamed from: d */
    private C2380a f12255d;
    /* renamed from: e */
    private boolean f12256e;

    /* renamed from: com.beastbikes.android.widget.calendar.CalendarView$a */
    public interface C2380a {
        /* renamed from: a */
        void mo3481a(int i, int i2);
    }

    public CalendarView(Context context) {
        super(context);
        m13070a(context);
    }

    public CalendarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m13070a(context);
    }

    public CalendarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13070a(context);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.calendar_view_last_month:
                this.f12254c.m13083a();
                this.f12253b.setText(this.f12254c.getDate());
                break;
            case C1373R.id.calendar_view_next_month:
                this.f12254c.m13084b();
                this.f12253b.setText(this.f12254c.getDate());
                break;
        }
        if (this.f12255d != null) {
            this.f12255d.mo3481a(this.f12254c.getCurrentYear(), this.f12254c.getCurrentMonth());
        }
        setLayoutParams(new LayoutParams(-1, (int) ((110.0f * this.f12252a.scaledDensity) + ((float) this.f12254c.getViewHeight()))));
    }

    /* renamed from: a */
    private void m13070a(Context context) {
        LayoutInflater.from(context).inflate(C1373R.layout.calendar_view, this);
        ImageView imageView = (ImageView) findViewById(C1373R.id.calendar_view_next_month);
        ((ImageView) findViewById(C1373R.id.calendar_view_last_month)).setOnClickListener(this);
        imageView.setOnClickListener(this);
        this.f12253b = (TextView) findViewById(C1373R.id.calendar_view_month_value_view);
        this.f12254c = (MonthView) findViewById(C1373R.id.calendar_view_month_view);
        this.f12253b.setText(this.f12254c.getDate());
        this.f12252a = getResources().getDisplayMetrics();
        this.f12256e = C1849a.m9645b(context);
        this.f12254c.setDisplayKm(this.f12256e);
    }

    public void setMonthClickListener(C2381a c2381a) {
        this.f12254c.setMonthClickListener(c2381a);
    }

    public void setCalendars(HashMap<String, CalendarDto> hashMap) {
        this.f12254c.setCalendars(hashMap);
    }

    public void setCalendarListener(C2380a c2380a) {
        this.f12255d = c2380a;
    }

    public void setDisplayKm(boolean z) {
        this.f12254c.setDisplayKm(z);
    }
}
