package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.EditText;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;

@C1459b(a = 2130903097)
@C1460c(a = 2131820562)
public class ClubWriteInforActivity extends SessionFragmentActivity {
    @C1458a(a = 2131755518)
    /* renamed from: a */
    private EditText f5299a;
    @C1458a(a = 2131755519)
    /* renamed from: b */
    private EditText f5300b;
    @C1458a(a = 2131755517)
    /* renamed from: c */
    private EditText f5301c;
    /* renamed from: d */
    private boolean f5302d = true;
    /* renamed from: e */
    private String f5303e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle(getString(C1373R.string.club_write_infor_title));
        }
        m6605a();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C1373R.id.club_write_info_ok) {
            Intent intent = new Intent(this, ClubActivityReleaseActivity.class);
            if (this.f5303e.equals("phonenumber")) {
                intent.putExtra("ms", this.f5299a.getText().toString());
            } else if (this.f5303e.equals("description")) {
                intent.putExtra("ms", this.f5300b.getText().toString());
            } else {
                intent.putExtra("ms", this.f5301c.getText().toString());
            }
            setResult(-1, intent);
            finish();
        }
        if (menuItem.getItemId() == 16908332) {
            m6607c();
        }
        return false;
    }

    /* renamed from: a */
    private void m6605a() {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("type");
        CharSequence stringExtra2 = intent.getStringExtra("ms");
        if (stringExtra.equals("phonenumber")) {
            this.f5299a.setVisibility(0);
            this.f5300b.setVisibility(8);
            this.f5301c.setVisibility(8);
            if (!(TextUtils.isEmpty(stringExtra2) || stringExtra2.equals(getResources().getString(C1373R.string.activity_club_release_activities_activity_Phone_Number)))) {
                this.f5299a.setText(stringExtra2);
            }
            this.f5303e = "phonenumber";
        } else if (stringExtra.equals("description")) {
            this.f5299a.setVisibility(8);
            this.f5300b.setVisibility(0);
            this.f5301c.setVisibility(8);
            if (!(TextUtils.isEmpty(stringExtra2) || stringExtra2.equals(getResources().getString(C1373R.string.activity_club_release_activities_activity_Phone_activity_description)))) {
                this.f5300b.setText(stringExtra2);
            }
            this.f5303e = "description";
        } else if (stringExtra.equals("ms")) {
            this.f5299a.setVisibility(8);
            this.f5300b.setVisibility(8);
            this.f5301c.setVisibility(0);
            this.f5301c.setText(stringExtra2);
            this.f5303e = "ms";
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: b */
    private boolean m6606b() {
        if (TextUtils.isEmpty(this.f5299a.getText().toString()) && TextUtils.isEmpty(this.f5300b.getText().toString()) && TextUtils.isEmpty(this.f5301c.getText().toString())) {
            return true;
        }
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            m6607c();
        }
        return false;
    }

    /* renamed from: c */
    private void m6607c() {
        if (m6606b()) {
            finish();
            return;
        }
        C2621c c2621c = new C2621c(this);
        c2621c.b(C1373R.string.club_release_activites_dialog_ms);
        c2621c.a(C1373R.string.club_release_activites_dialog_ok, new ClubWriteInforActivity$1(this, c2621c));
        c2621c.b(C1373R.string.club_release_activites_dialog_cencle, new ClubWriteInforActivity$2(this, c2621c));
        c2621c.a();
    }
}
