package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.utils.Toasts;

@C1457a(a = "发布公告")
@C1459b(a = 2130903261)
@C1460c(a = 2131820581)
public class ClubPostNoticeActivity extends SessionFragmentActivity {
    /* renamed from: a */
    public static String f5260a;
    @C1458a(a = 2131756340)
    /* renamed from: b */
    private EditText f5261b;
    /* renamed from: c */
    private ClubManager f5262c;
    /* renamed from: d */
    private C1802i f5263d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5262c = new ClubManager(this);
        Intent intent = getIntent();
        if (intent != null) {
            this.f5261b.setOnEditorActionListener(new ClubPostNoticeActivity$1(this));
            if (TextUtils.isEmpty(f5260a)) {
                f5260a = intent.getStringExtra("notice");
            }
            if (!TextUtils.isEmpty(f5260a)) {
                this.f5261b.setText(f5260a);
            }
        }
    }

    public void finish() {
        Object obj = this.f5261b.getText().toString();
        setResult(-1);
        if (TextUtils.isEmpty(obj)) {
            super.finish();
            super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
        } else if (obj.equals(f5260a)) {
            super.finish();
            super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
        } else {
            m6576a();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.friends_add_menu_item:
                m6578b();
                C2580w.a(this, "发布公告", null);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* renamed from: a */
    private void m6576a() {
        C2621c c2621c = new C2621c(this);
        c2621c.b(C1373R.string.notice_change_notrealse);
        c2621c.a(C1373R.string.quit, new ClubPostNoticeActivity$3(this, c2621c)).b(C1373R.string.cancel, new ClubPostNoticeActivity$2(this, c2621c)).a();
    }

    /* renamed from: b */
    private void m6578b() {
        String obj = this.f5261b.getText().toString();
        if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj.trim())) {
            Toasts.show(this, C1373R.string.club_info_item_club_notice_cannot_null);
            return;
        }
        this.f5263d = new C1802i(this, "", true);
        this.f5263d.show();
        getAsyncTaskQueue().a(new ClubPostNoticeActivity$4(this, obj), new String[]{obj});
    }
}
