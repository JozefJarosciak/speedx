package com.beastbikes.android.ble.ui;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.ui.widget.TextViewWithBoardAndCorners;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903124)
public class DisclaimerActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131755702)
    /* renamed from: a */
    TextViewWithBoardAndCorners f4144a;
    @C1458a(a = 2131755703)
    /* renamed from: b */
    TextViewWithBoardAndCorners f4145b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(-1);
        }
        m5463a();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.textView_refuse_disclaimer:
                setResult(0);
                finish();
                return;
            case C1373R.id.textView_accept_disclaimer:
                setResult(-1);
                finish();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m5463a() {
        this.f4144a.setOnClickListener(this);
        this.f4145b.setOnClickListener(this);
    }
}
