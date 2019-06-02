package com.beastbikes.android.ble.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903203)
public class StartNavigationActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131756076)
    /* renamed from: a */
    private View f4400a;
    @C1458a(a = 2131756077)
    /* renamed from: b */
    private View f4401b;
    @C1458a(a = 2131756082)
    /* renamed from: c */
    private View f4402c;
    @C1458a(a = 2131756080)
    /* renamed from: d */
    private View f4403d;
    @C1458a(a = 2131756081)
    /* renamed from: e */
    private View f4404e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m5764a();
        this.f4402c.setOnClickListener(this);
        this.f4403d.setOnClickListener(this);
        this.f4404e.setOnClickListener(this);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
    }

    public void finish() {
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
        super.finish();
    }

    /* renamed from: a */
    private void m5764a() {
        this.f4400a.setVisibility(0);
        this.f4401b.setVisibility(8);
    }

    /* renamed from: b */
    private void m5765b() {
        this.f4400a.setVisibility(8);
        this.f4401b.setVisibility(0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.activity_start_navigation_delete:
                m5765b();
                return;
            case C1373R.id.activity_start_navigation_start:
                m5764a();
                return;
            case C1373R.id.activity_start_navigation_search:
                m5765b();
                return;
            default:
                return;
        }
    }
}
