package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1805k;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.train.p076a.C2351d;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch.C1706a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import java.util.ArrayList;

@C1459b(a = 2130903139)
@C1460c(a = 2131820586)
public class HeartRateSetActivity extends SessionFragmentActivity implements OnClickListener, C1706a {
    @C1458a(a = 2131755779)
    /* renamed from: a */
    private Switch f6419a;
    @C1458a(a = 2131755780)
    /* renamed from: b */
    private ViewGroup f6420b;
    /* renamed from: c */
    private TextView f6421c;
    @C1458a(a = 2131755781)
    /* renamed from: d */
    private View f6422d;
    /* renamed from: e */
    private int f6423e;
    /* renamed from: f */
    private int f6424f = 200;
    /* renamed from: g */
    private SharedPreferences f6425g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f6425g = getSharedPreferences(m5331p(), 0);
        this.f6423e = getIntent().getIntExtra("AGE", 0);
        this.f6424f = getIntent().getIntExtra("MAX_HEART_RATE", 200);
        if (this.f6424f <= 100) {
            this.f6424f = 200;
        }
        ((TextView) this.f6420b.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_max_heart_rate);
        this.f6421c = (TextView) this.f6420b.findViewById(C1373R.id.speed_force_setting_item_value);
        CharSequence charSequence = getString(C1373R.string.str_default_value) + this.f6424f;
        if (this.f6424f != 200) {
            charSequence = String.valueOf(this.f6424f);
        }
        this.f6421c.setText(charSequence);
        this.f6419a.setChecked(this.f6425g.getBoolean("PREF.USER.MAX.HEART.RATE.BY.BIRTHDAY", true));
        this.f6420b.setOnClickListener(this);
        this.f6419a.setOnCheckedChangeListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.save_menu_item:
                m7648b();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void finish() {
        Intent intent = getIntent();
        intent.putExtra("MAX_HEART_RATE", this.f6424f);
        setResult(-1, intent);
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.heart_rate_set_view:
                m7646a();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m7650a(Switch switchR, boolean z) {
        this.f6422d.setVisibility(z ? 0 : 8);
        if (z) {
            if (this.f6423e <= 0) {
                this.f6424f = 200;
            } else {
                this.f6424f = (int) (208.754d - (((double) this.f6423e) * 0.734d));
            }
        }
        this.f6421c.setText(String.valueOf(this.f6424f));
        this.f6425g.edit().putBoolean("PREF.USER.MAX.HEART.RATE.BY.BIRTHDAY", z).apply();
    }

    /* renamed from: a */
    private void m7646a() {
        ArrayList arrayList = new ArrayList();
        for (int i = 150; i <= 230; i++) {
            arrayList.add(String.valueOf(i));
        }
        C1805k c1805k = new C1805k(this, new HeartRateSetActivity$1(this));
        c1805k.show();
        c1805k.a(arrayList);
        c1805k.b(String.valueOf(this.f6424f));
    }

    /* renamed from: b */
    private void m7648b() {
        getAsyncTaskQueue().a(new HeartRateSetActivity$2(this, new C2351d(this)), new Void[0]);
    }
}
