package com.beastbikes.android.update.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.update.p162b.C2548a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903212)
public class VersionUpdateActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131756108)
    /* renamed from: a */
    TextView f6801a;
    @C1458a(a = 2131756109)
    /* renamed from: b */
    TextView f6802b;
    @C1458a(a = 2131756110)
    /* renamed from: c */
    TextView f6803c;
    @C1458a(a = 2131756111)
    /* renamed from: d */
    TextView f6804d;
    /* renamed from: e */
    private C2548a f6805e;
    /* renamed from: f */
    private C1802i f6806f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m7987a();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        defaultSharedPreferences.edit().putBoolean("beast.version.update.guide" + defaultSharedPreferences.getInt("beast.version.update", 0), false).apply();
    }

    /* renamed from: a */
    public void m7987a() {
        getAsyncTaskQueue().a(new VersionUpdateActivity$1(this), new Void[0]);
    }

    public void onClick(View view) {
        if (view == this.f6803c) {
            m7988b();
        }
    }

    /* renamed from: b */
    public void m7988b() {
        if (this.f6805e != null) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(this.f6805e.e()));
            startActivity(intent);
        }
    }
}
