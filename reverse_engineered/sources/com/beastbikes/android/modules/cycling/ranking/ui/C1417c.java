package com.beastbikes.android.modules.cycling.ranking.ui;

import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.ranking.dto.C2173a;
import com.beastbikes.android.utils.C2570p;
import com.beastbikes.framework.android.p056e.C1376d;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;

/* compiled from: RankViewHolder2 */
/* renamed from: com.beastbikes.android.modules.cycling.ranking.ui.c */
public final class C1417c extends ViewHolder<C2173a> {
    @C1458a(a = 2131757338)
    /* renamed from: a */
    public RelativeLayout f5422a;
    /* renamed from: b */
    private C1376d f5423b;
    /* renamed from: c */
    private View f5424c;
    @C1458a(a = 2131757339)
    /* renamed from: d */
    private TextView f5425d;
    @C1458a(a = 2131756762)
    /* renamed from: e */
    private CircleImageView f5426e;
    @C1458a(a = 2131757340)
    /* renamed from: f */
    private TextView f5427f;
    @C1458a(a = 2131757341)
    /* renamed from: g */
    private TextView f5428g;
    @C1458a(a = 2131757342)
    /* renamed from: h */
    private TextView f5429h;
    @C1458a(a = 2131757343)
    /* renamed from: i */
    private TextView f5430i;
    @C1458a(a = 2131757344)
    /* renamed from: j */
    private TextView f5431j;
    @C1458a(a = 2131757346)
    /* renamed from: k */
    private TextView f5432k;
    @C1458a(a = 2131757345)
    /* renamed from: l */
    private TextView f5433l;
    /* renamed from: m */
    private boolean f5434m = true;

    public /* synthetic */ void bind(Object obj) {
        m6731a((C2173a) obj);
    }

    public C1417c(C1376d c1376d, View view, boolean z) {
        super(view);
        this.f5423b = c1376d;
        this.f5424c = view;
        this.f5434m = z;
    }

    /* renamed from: a */
    public void m6731a(C2173a c2173a) {
        if (c2173a != null) {
            switch (c2173a.i()) {
                case 1:
                    this.f5425d.setBackgroundResource(C1373R.drawable.ordinal_bg_1);
                    break;
                case 2:
                    this.f5425d.setBackgroundResource(C1373R.drawable.ordinal_bg_2);
                    break;
                case 3:
                    this.f5425d.setBackgroundResource(C1373R.drawable.ordinal_bg_3);
                    break;
                default:
                    this.f5425d.setBackgroundResource(C1373R.drawable.transparent);
                    break;
            }
            String e = c2173a.e();
            if (TextUtils.isEmpty(e)) {
                this.f5426e.setImageResource(C1373R.drawable.ic_avatar);
            } else {
                Picasso.with(getContext()).load(e).fit().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).centerCrop().into(this.f5426e);
            }
            this.f5425d.setText(String.valueOf(c2173a.i()));
            e = c2173a.g();
            if (TextUtils.isEmpty(e)) {
                e = c2173a.g();
            }
            if (e == null) {
                e = "";
            }
            this.f5427f.setText(C2570p.a(e, c2173a.j()));
            CharSequence c = c2173a.c();
            if (TextUtils.isEmpty(c) || c.equals("null") || c.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                this.f5429h.setText("");
            } else {
                this.f5429h.setText(c);
            }
            c = c2173a.d();
            if (TextUtils.isEmpty(c) || c.equals("null") || c.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                this.f5430i.setText("");
            } else {
                this.f5430i.setText(c);
            }
            e = c2173a.d();
            if (TextUtils.isEmpty(e) || e.equals("null") || e.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                this.f5431j.setText("");
            } else {
                this.f5431j.setText(c2173a.d());
            }
            double h = c2173a.h();
            if (h < 0.0d) {
                h = 0.0d;
            }
            if (this.f5434m) {
                this.f5432k.setText(String.format("%.0f", new Object[]{Double.valueOf(h / 1000.0d)}));
            } else {
                this.f5432k.setText(String.format("%.0f", new Object[]{Double.valueOf(C1849a.a(h / 1000.0d))}));
                this.f5433l.setText("mi");
            }
            if (c2173a.a() == 128) {
                this.f5428g.setVisibility(0);
            } else {
                this.f5428g.setVisibility(8);
            }
        }
    }
}
