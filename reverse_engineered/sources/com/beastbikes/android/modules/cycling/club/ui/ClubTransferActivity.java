package com.beastbikes.android.modules.cycling.club.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903264)
public class ClubTransferActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131756342)
    /* renamed from: a */
    private TextView f5295a;
    @C1458a(a = 2131756343)
    /* renamed from: b */
    private TextView f5296b;
    /* renamed from: c */
    private ClubManager f5297c;
    /* renamed from: d */
    private C1802i f5298d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5295a.setOnClickListener(this);
        this.f5296b.setOnClickListener(this);
        this.f5297c = new ClubManager(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.club_transfer_activity_cancel_transfer:
                m6602a();
                return;
            case C1373R.id.club_transfer_activity_tip_transfer:
                m6604b();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m6602a() {
        getAsyncTaskQueue().a(new ClubTransferActivity$1(this, this), new Void[0]);
    }

    /* renamed from: b */
    private void m6604b() {
        getAsyncTaskQueue().a(new ClubTransferActivity$2(this, this), new Void[0]);
    }
}
