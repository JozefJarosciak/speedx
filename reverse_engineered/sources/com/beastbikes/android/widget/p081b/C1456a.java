package com.beastbikes.android.widget.p081b;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.android.p084c.p085a.C1458a;

/* compiled from: FooterViewHolder */
/* renamed from: com.beastbikes.android.widget.b.a */
public class C1456a extends C2613i<Boolean> {
    @C1458a(a = 2131756778)
    /* renamed from: a */
    private ProgressBar f6828a;
    @C1458a(a = 2131756384)
    /* renamed from: b */
    private TextView f6829b;

    public C1456a(View view) {
        super(view);
    }

    /* renamed from: a */
    public void m8022a(Boolean bool) {
        if (bool.booleanValue()) {
            this.f6828a.setVisibility(0);
            this.f6829b.setText(C1373R.string.loadmore);
            return;
        }
        this.f6828a.setVisibility(8);
        this.f6829b.setText(C1373R.string.nomoredate);
    }
}
