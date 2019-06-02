package com.wdullaer.materialdatetimepicker.date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import com.wdullaer.materialdatetimepicker.date.C4797e.C4794b;
import java.util.Calendar;
import java.util.HashMap;

/* compiled from: MonthAdapter */
/* renamed from: com.wdullaer.materialdatetimepicker.date.d */
public abstract class C4795d extends BaseAdapter implements C4794b {
    /* renamed from: b */
    protected static int f16810b = 7;
    /* renamed from: a */
    protected final C4784a f16811a;
    /* renamed from: c */
    private final Context f16812c;
    /* renamed from: d */
    private C4793a f16813d;

    /* compiled from: MonthAdapter */
    /* renamed from: com.wdullaer.materialdatetimepicker.date.d$a */
    public static class C4793a {
        /* renamed from: a */
        int f16806a;
        /* renamed from: b */
        int f16807b;
        /* renamed from: c */
        int f16808c;
        /* renamed from: d */
        private Calendar f16809d;

        public C4793a() {
            m18830a(System.currentTimeMillis());
        }

        public C4793a(long j) {
            m18830a(j);
        }

        public C4793a(Calendar calendar) {
            this.f16806a = calendar.get(1);
            this.f16807b = calendar.get(2);
            this.f16808c = calendar.get(5);
        }

        public C4793a(int i, int i2, int i3) {
            m18831a(i, i2, i3);
        }

        /* renamed from: a */
        public void m18832a(C4793a c4793a) {
            this.f16806a = c4793a.f16806a;
            this.f16807b = c4793a.f16807b;
            this.f16808c = c4793a.f16808c;
        }

        /* renamed from: a */
        public void m18831a(int i, int i2, int i3) {
            this.f16806a = i;
            this.f16807b = i2;
            this.f16808c = i3;
        }

        /* renamed from: a */
        private void m18830a(long j) {
            if (this.f16809d == null) {
                this.f16809d = Calendar.getInstance();
            }
            this.f16809d.setTimeInMillis(j);
            this.f16807b = this.f16809d.get(2);
            this.f16806a = this.f16809d.get(1);
            this.f16808c = this.f16809d.get(5);
        }
    }

    /* renamed from: a */
    public abstract C4797e mo6200a(Context context);

    public C4795d(Context context, C4784a c4784a) {
        this.f16812c = context;
        this.f16811a = c4784a;
        m18836a();
        m18837a(this.f16811a.mo6185a());
    }

    /* renamed from: a */
    public void m18837a(C4793a c4793a) {
        this.f16813d = c4793a;
        notifyDataSetChanged();
    }

    /* renamed from: a */
    protected void m18836a() {
        this.f16813d = new C4793a(System.currentTimeMillis());
    }

    public int getCount() {
        return ((this.f16811a.mo6195g() - this.f16811a.mo6194f()) + 1) * 12;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public boolean hasStableIds() {
        return true;
    }

    @SuppressLint({"NewApi"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = -1;
        HashMap hashMap = null;
        if (view != null) {
            view = (C4797e) view;
            hashMap = (HashMap) view.getTag();
        } else {
            view = mo6200a(this.f16812c);
            view.setLayoutParams(new LayoutParams(-1, -1));
            view.setClickable(true);
            view.setOnDayClickListener(this);
        }
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        hashMap.clear();
        int i3 = i % 12;
        int f = (i / 12) + this.f16811a.mo6194f();
        if (m18834a(f, i3)) {
            i2 = this.f16813d.f16808c;
        }
        view.m18856b();
        hashMap.put("selected_day", Integer.valueOf(i2));
        hashMap.put("year", Integer.valueOf(f));
        hashMap.put("month", Integer.valueOf(i3));
        hashMap.put("week_start", Integer.valueOf(this.f16811a.mo6193e()));
        view.setMonthParams(hashMap);
        view.invalidate();
        return view;
    }

    /* renamed from: a */
    private boolean m18834a(int i, int i2) {
        return this.f16813d.f16806a == i && this.f16813d.f16807b == i2;
    }

    /* renamed from: a */
    public void mo6198a(C4797e c4797e, C4793a c4793a) {
        if (c4793a != null) {
            m18839b(c4793a);
        }
    }

    /* renamed from: b */
    protected void m18839b(C4793a c4793a) {
        this.f16811a.mo6196h();
        this.f16811a.mo6187a(c4793a.f16806a, c4793a.f16807b, c4793a.f16808c);
        m18837a(c4793a);
    }
}
