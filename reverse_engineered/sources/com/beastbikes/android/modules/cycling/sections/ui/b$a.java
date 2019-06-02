package com.beastbikes.android.modules.cycling.sections.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.sections.dto.C2223d;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;

/* compiled from: SectionDetailAdapter */
public final class b$a extends ViewHolder<C2223d> {
    /* renamed from: a */
    final /* synthetic */ C2230b f5779a;
    @C1458a(a = 2131756769)
    /* renamed from: b */
    private TextView f5780b;
    @C1458a(a = 2131756770)
    /* renamed from: c */
    private CircleImageView f5781c;
    @C1458a(a = 2131756771)
    /* renamed from: d */
    private TextView f5782d;
    @C1458a(a = 2131756772)
    /* renamed from: e */
    private TextView f5783e;

    public /* synthetic */ void bind(Object obj) {
        m7011a((C2223d) obj);
    }

    protected b$a(C2230b c2230b, View view) {
        this.f5779a = c2230b;
        super(view);
    }

    /* renamed from: a */
    public void m7011a(C2223d c2223d) {
        if (c2223d != null) {
            String str;
            String str2;
            String str3;
            if (c2223d == null || TextUtils.isEmpty(c2223d.d())) {
                this.f5781c.setImageResource(C1373R.drawable.ic_avatar);
            } else {
                Picasso.with(C2230b.a(this.f5779a)).load(c2223d.d()).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f5781c);
            }
            this.f5782d.setText(c2223d.b());
            double a = c2223d.a();
            int i = ((int) a) / 3600;
            int i2 = ((int) (a - ((double) (i * 3600)))) / 60;
            int i3 = (int) ((a - ((double) (i * 3600))) - ((double) (i2 * 60)));
            if (i < 10) {
                str = "0" + i;
            } else {
                str = "" + i;
            }
            if (i2 < 10) {
                str2 = "0" + i2;
            } else {
                str2 = "" + i2;
            }
            if (i3 < 10) {
                str3 = "0" + i3;
            } else {
                str3 = "" + i3;
            }
            this.f5783e.setText(str + ":" + str2 + ":" + str3);
        }
    }
}
