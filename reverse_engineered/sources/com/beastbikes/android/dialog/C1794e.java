package com.beastbikes.android.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.PickerView.C1777b;
import com.beastbikes.android.utils.C2554c;
import java.util.ArrayList;
import java.util.Calendar;

/* compiled from: DatePickerDialog */
/* renamed from: com.beastbikes.android.dialog.e */
public class C1794e extends C1788c implements C1371a, C1777b {
    /* renamed from: b */
    private PickerView f8177b;
    /* renamed from: c */
    private PickerView f8178c;
    /* renamed from: d */
    private PickerView f8179d;
    /* renamed from: e */
    private TextView f8180e;
    /* renamed from: f */
    private int f8181f;
    /* renamed from: g */
    private int f8182g;
    /* renamed from: h */
    private int f8183h;
    /* renamed from: i */
    private int f8184i;
    /* renamed from: j */
    private Handler f8185j = new Handler(Looper.getMainLooper());
    /* renamed from: k */
    private C1793a f8186k;
    /* renamed from: l */
    private int f8187l;

    /* compiled from: DatePickerDialog */
    /* renamed from: com.beastbikes.android.dialog.e$1 */
    class C17901 implements C1777b {
        /* renamed from: a */
        final /* synthetic */ C1794e f8174a;

        C17901(C1794e c1794e) {
            this.f8174a = c1794e;
        }

        /* renamed from: a */
        public void mo3242a(int i, String str) {
            this.f8174a.f8181f = Integer.parseInt(str);
            this.f8174a.m9490c();
            this.f8174a.m9493e();
        }

        /* renamed from: b */
        public void mo3243b(int i, String str) {
        }
    }

    /* compiled from: DatePickerDialog */
    /* renamed from: com.beastbikes.android.dialog.e$2 */
    class C17912 implements C1777b {
        /* renamed from: a */
        final /* synthetic */ C1794e f8175a;

        C17912(C1794e c1794e) {
            this.f8175a = c1794e;
        }

        /* renamed from: a */
        public void mo3242a(int i, String str) {
            this.f8175a.f8183h = Integer.parseInt(str);
            this.f8175a.m9493e();
        }

        /* renamed from: b */
        public void mo3243b(int i, String str) {
        }
    }

    /* compiled from: DatePickerDialog */
    /* renamed from: com.beastbikes.android.dialog.e$3 */
    class C17923 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1794e f8176a;

        C17923(C1794e c1794e) {
            this.f8176a = c1794e;
        }

        public void run() {
            this.f8176a.f8180e.setText(String.valueOf(this.f8176a.f8184i));
        }
    }

    /* compiled from: DatePickerDialog */
    /* renamed from: com.beastbikes.android.dialog.e$a */
    public interface C1793a {
        /* renamed from: a */
        void mo3505a(int i, int i2, int i3, int i4);
    }

    public C1794e(Context context, int i, C1793a c1793a) {
        super(context);
        this.f8186k = c1793a;
        Calendar instance = Calendar.getInstance();
        this.f8181f = instance.get(1);
        this.f8182g = instance.get(2) + 1;
        this.f8183h = instance.get(5);
        this.f8187l = i;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = LayoutInflater.from(getContext()).inflate(C1373R.layout.dialog_date_picker, null);
        this.f8177b = (PickerView) inflate.findViewById(C1373R.id.date_picker_view_left);
        this.f8178c = (PickerView) inflate.findViewById(C1373R.id.date_picker_view_center);
        this.f8179d = (PickerView) inflate.findViewById(C1373R.id.date_picker_view_right);
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(C1373R.id.dialog_date_picker_age_view);
        this.f8180e = (TextView) inflate.findViewById(C1373R.id.dialog_date_picker_age);
        frameLayout.setVisibility(this.f8187l == 0 ? 0 : 8);
        this.f8178c.setOnSelectListener(this);
        this.f8177b.setOnSelectListener(new C17901(this));
        this.f8179d.setOnSelectListener(new C17912(this));
        m9478a(inflate);
        m9487b();
    }

    /* renamed from: a */
    public void mo3242a(int i, String str) {
        this.f8182g = Integer.parseInt(str);
        m9492d();
        m9493e();
    }

    /* renamed from: b */
    public void mo3243b(int i, String str) {
    }

    /* renamed from: a */
    public void mo3244a() {
        super.mo3244a();
        if (this.f8186k != null) {
            this.f8186k.mo3505a(this.f8181f, this.f8182g, this.f8183h, this.f8184i);
        }
        dismiss();
    }

    /* renamed from: b */
    private void m9487b() {
        int i = Calendar.getInstance().get(1);
        ArrayList arrayList = new ArrayList();
        for (int i2 = i - 100; i2 <= i; i2++) {
            arrayList.add(String.valueOf(i2));
        }
        this.f8177b.setData(arrayList);
        this.f8177b.setUnit(getContext().getString(C1373R.string.year));
        this.f8177b.setDefault(String.valueOf(this.f8181f));
        m9490c();
        this.f8178c.setUnit(getContext().getString(C1373R.string.month));
        this.f8178c.setDefault(String.valueOf(this.f8182g));
        m9492d();
        this.f8179d.setUnit(getContext().getString(C1373R.string.str_calendar_day));
        this.f8179d.setDefault(String.valueOf(this.f8183h));
    }

    /* renamed from: a */
    public void m9495a(int i, int i2, int i3) {
        this.f8181f = i;
        this.f8182g = i2;
        this.f8183h = i3;
        this.f8177b.setDefault(String.valueOf(i));
        this.f8178c.setDefault(String.valueOf(i2));
        this.f8179d.setDefault(String.valueOf(i3));
        this.f8184i = C2554c.m12789a(this.f8181f, this.f8182g, this.f8183h);
        this.f8180e.setText(String.valueOf(this.f8184i));
    }

    /* renamed from: c */
    private void m9490c() {
        int i = 1;
        Calendar instance = Calendar.getInstance();
        int i2 = instance.get(1);
        int i3 = 12;
        if ((this.f8187l == 1 || this.f8187l == 0) && this.f8181f == i2) {
            i3 = instance.get(2) + 1;
        }
        ArrayList arrayList = new ArrayList();
        while (i <= i3) {
            arrayList.add(String.valueOf(i));
            i++;
        }
        this.f8178c.setData(arrayList);
    }

    /* renamed from: d */
    private void m9492d() {
        int i = 1;
        Calendar instance = Calendar.getInstance();
        int i2 = instance.get(1);
        int i3 = instance.get(2) + 1;
        int a = C2554c.m12788a(this.f8181f, this.f8182g - 1);
        if ((this.f8187l == 1 || this.f8187l == 0) && this.f8181f == i2 && this.f8182g == i3) {
            a = instance.get(5);
        }
        ArrayList arrayList = new ArrayList();
        while (i <= a) {
            arrayList.add(String.valueOf(i));
            i++;
        }
        this.f8179d.setData(arrayList);
        this.f8179d.setDefault(String.valueOf(this.f8183h));
    }

    /* renamed from: e */
    private void m9493e() {
        this.f8184i = C2554c.m12789a(this.f8181f, this.f8182g, this.f8183h);
        this.f8185j.post(new C17923(this));
    }
}
