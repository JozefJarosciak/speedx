package com.beastbikes.android.modules.setting.ui;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.framework.android.ApplicationContext;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903407)
public class LaboratoryActivity extends SessionFragmentActivity implements OnClickListener, C1371a {
    @C1458a(a = 2131756260)
    /* renamed from: a */
    private Switch f6013a;
    @C1458a(a = 2131756788)
    /* renamed from: b */
    private Switch f6014b;
    @C1458a(a = 2131756787)
    /* renamed from: c */
    private Switch f6015c;
    @C1458a(a = 2131756789)
    /* renamed from: d */
    private View f6016d;
    @C1458a(a = 2131756790)
    /* renamed from: e */
    private View f6017e;
    @C1458a(a = 2131756791)
    /* renamed from: f */
    private Switch f6018f;
    /* renamed from: g */
    private BeastBikes f6019g;
    /* renamed from: h */
    private SharedPreferences f6020h;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            int i;
            this.f6020h = getSharedPreferences(currentUser.getObjectId(), 0);
            this.f6019g = (BeastBikes) ApplicationContext.m5242j();
            this.f6013a.setChecked(this.f6019g.m5258f());
            this.f6013a.setOnCheckedChangeListener(new LaboratoryActivity$1(this));
            this.f6014b.setChecked(this.f6019g.m5259g());
            this.f6014b.setOnCheckedChangeListener(new LaboratoryActivity$2(this));
            this.f6015c.setChecked(m7230a());
            this.f6015c.setOnCheckedChangeListener(new LaboratoryActivity$3(this));
            if (C1849a.a()) {
                this.f6016d.setVisibility(0);
                this.f6016d.setOnClickListener(this);
            } else {
                this.f6016d.setVisibility(8);
            }
            View view = this.f6017e;
            if (this.f6019g.m5261i()) {
                i = 0;
            } else {
                i = 8;
            }
            view.setVisibility(i);
            this.f6018f.setChecked(this.f6019g.m5260h());
            this.f6018f.setOnCheckedChangeListener(new LaboratoryActivity$4(this));
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        if (view.getId() == C1373R.id.laboratory_activity_cycling_keepalive_suggest) {
            startActivity(new Intent(this, KeepAliveHelperActivity.class));
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i != 78) {
            return;
        }
        if (m7230a()) {
            this.f6015c.setChecked(true);
            this.f6019g.m5248b(true);
            return;
        }
        this.f6015c.setChecked(false);
        this.f6019g.m5248b(false);
    }

    /* renamed from: a */
    private boolean m7230a() {
        CharSequence packageName = getPackageName();
        Object string = Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        for (String unflattenFromString : string.split(":")) {
            ComponentName unflattenFromString2 = ComponentName.unflattenFromString(unflattenFromString);
            if (unflattenFromString2 != null && TextUtils.equals(packageName, unflattenFromString2.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private void m7231b() {
        if (!m7230a()) {
            try {
                startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 78);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
