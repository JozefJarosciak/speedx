package com.beastbikes.android.modules.user.view;

import android.view.View;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.widget.p081b.C2409c;
import com.beastbikes.android.widget.p081b.C2613i;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* compiled from: RecordGroupViewHolder */
/* renamed from: com.beastbikes.android.modules.user.view.d */
public class C1443d extends C2613i<C2409c> {
    @C1458a(a = 2131755901)
    /* renamed from: a */
    private TextView f6793a;
    @C1458a(a = 2131755904)
    /* renamed from: b */
    private TextView f6794b;
    @C1458a(a = 2131755903)
    /* renamed from: c */
    private TextView f6795c;
    @C1458a(a = 2131755902)
    /* renamed from: d */
    private TextView f6796d;
    /* renamed from: e */
    private boolean f6797e = C1849a.b(a());
    /* renamed from: f */
    private View f6798f;
    /* renamed from: g */
    private SimpleDateFormat f6799g;
    /* renamed from: h */
    private int f6800h = Calendar.getInstance().get(1);

    public C1443d(View view) {
        super(view);
        this.f6798f = view;
    }

    /* renamed from: a */
    public void m7972a(C2409c c2409c) {
        if (c2409c != null) {
            if (c2409c.getHeaderId() <= 0) {
                this.f6798f.setVisibility(8);
                return;
            }
            Calendar instance = Calendar.getInstance();
            instance.set(c2409c.getYear(), c2409c.getMonth() - 1, 1);
            if (c2409c.getYear() >= this.f6800h) {
                this.f6799g = new SimpleDateFormat("MMM", Locale.getDefault());
            } else if (C1849a.c()) {
                this.f6799g = new SimpleDateFormat("yyyy年MM月", Locale.getDefault());
            } else {
                this.f6799g = new SimpleDateFormat("MMM yyyy", Locale.getDefault());
            }
            this.f6793a.setText(this.f6799g.format(instance.getTime()));
            this.f6794b.setText(String.format(a().getString(C1373R.string.str_cycling_count), new Object[]{Integer.valueOf(c2409c.getTotalCount())}));
            double monthDistance = c2409c.getMonthDistance() / 1000.0d;
            String str = "KM";
            if (!this.f6797e) {
                monthDistance = C1849a.a(monthDistance);
                str = "MI";
            }
            if (Double.isNaN(monthDistance)) {
                monthDistance = 0.0d;
            }
            this.f6795c.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(monthDistance)}) + str);
            this.f6796d.setText(C2555d.b(c2409c.getTotalTime()));
        }
    }
}
