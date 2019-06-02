package com.beastbikes.android.authentication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.p055a.C1536a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;

@C1457a(a = "邮箱找回成功")
@C1459b(a = 2130903130)
@C1460c(a = 2131820569)
public class FindPasswordSuccessActivity extends SessionFragmentActivity {
    /* renamed from: a */
    public static String f4103a = "mail";
    @C1458a(a = 2131755734)
    /* renamed from: b */
    private TextView f4104b;
    @C1458a(a = 2131755735)
    /* renamed from: c */
    private TextView f4105c;
    /* renamed from: d */
    private C1536a f4106d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f4106d = new C1536a(this);
        this.f4104b.setText(getIntent().getStringExtra(f4103a));
        this.f4105c.setOnClickListener(new FindPasswordSuccessActivity$1(this));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C1373R.id.find_password_success_ok) {
            Intent intent = getIntent();
            intent.putExtra("extra_result_redirect", 550);
            intent.putExtra("extra_result_email", this.f4104b.getText().toString());
            setResult(-1, intent);
            finish();
        } else if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return true;
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }
}
