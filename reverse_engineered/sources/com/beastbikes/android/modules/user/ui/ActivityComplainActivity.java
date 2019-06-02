package com.beastbikes.android.modules.user.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.utils.Toasts;

@C1459b(a = 2130903104)
@C1460c(a = 2131820545)
public class ActivityComplainActivity extends SessionFragmentActivity {
    @C1458a(a = 2131755564)
    /* renamed from: a */
    private EditText f6287a;
    @C1458a(a = 2131755565)
    /* renamed from: b */
    private EditText f6288b;
    /* renamed from: c */
    private C1398a f6289c;
    /* renamed from: d */
    private String f6290d;
    /* renamed from: e */
    private boolean f6291e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f6289c = new C1398a((Activity) this);
        Intent intent = getIntent();
        if (intent != null) {
            this.f6290d = intent.getStringExtra("activity_id");
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.activity_complain_menu_commit:
                if (!this.f6291e) {
                    this.f6291e = true;
                    m7482a();
                    break;
                }
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    private void m7482a() {
        if (TextUtils.isEmpty(this.f6287a.getText().toString())) {
            this.f6291e = false;
            Toasts.show(this, C1373R.string.activity_complain_contact_empty);
            return;
        }
        if (TextUtils.isEmpty(this.f6288b.getText().toString())) {
            this.f6291e = false;
            Toasts.show(this, C1373R.string.activity_complain_question_empty);
            return;
        }
        getAsyncTaskQueue().a(new ActivityComplainActivity$1(this), new String[]{r0, r1});
    }
}
