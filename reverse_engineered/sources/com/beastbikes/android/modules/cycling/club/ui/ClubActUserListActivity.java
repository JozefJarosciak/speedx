package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.LinearLayout;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2049a;
import com.beastbikes.android.modules.cycling.club.dto.ClubActUserList;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityUser;
import com.beastbikes.android.widget.C2638d;
import com.beastbikes.android.widget.C2638d.C2631b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903087)
public class ClubActUserListActivity extends SessionFragmentActivity implements C2631b {
    @C1458a(a = 2131755444)
    /* renamed from: a */
    private LinearLayout f4842a;
    /* renamed from: b */
    private C2638d f4843b;
    /* renamed from: c */
    private List<ClubActivityUser> f4844c = new ArrayList();
    /* renamed from: d */
    private ClubActUserListActivity$a f4845d;
    /* renamed from: e */
    private int f4846e = 1;
    /* renamed from: f */
    private int f4847f = 20;
    /* renamed from: g */
    private boolean f4848g;
    /* renamed from: h */
    private String f4849h;
    /* renamed from: i */
    private C2049a f4850i;
    /* renamed from: j */
    private boolean f4851j;
    /* renamed from: k */
    private int f4852k = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle(getString(C1373R.string.club_activity_user_list_tittle));
        }
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getSerializableExtra(C0861d.f2139k) != null) {
                ClubActUserList clubActUserList = (ClubActUserList) intent.getSerializableExtra(C0861d.f2139k);
                if (!(clubActUserList == null || clubActUserList.getUsers() == null)) {
                    this.f4844c.addAll(clubActUserList.getUsers());
                }
            }
            this.f4849h = intent.getStringExtra("CLUB_ACT_ID");
            this.f4850i = new C2049a(this);
            this.f4848g = intent.getBooleanExtra("IS_CLUB_ACT_LIST_CAN_LOAD_MORE", false);
            this.f4852k = intent.getIntExtra("CLUB_ACT_MEMBERS", 0);
            this.f4845d = new ClubActUserListActivity$a(this, this);
            if (this.f4848g) {
                this.f4843b = new C2638d(this, this.f4842a, this.f4844c, 2);
            } else {
                this.f4843b = new C2638d(this, this.f4842a, this.f4844c, 0);
            }
            this.f4843b.setAdapter(this.f4845d);
            this.f4843b.setRecyclerCallBack(this);
            if (this.f4844c == null || this.f4844c.size() == 0) {
                m6233a();
            }
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    public void m6233a() {
        this.f4846e = 1;
        this.f4843b.setCanLoadMore(true);
        this.f4851j = true;
        this.f4843b.setHasFooter(true);
        m6227c();
    }

    public void a_() {
        if (this.f4848g) {
            this.f4846e++;
            this.f4843b.setHasFooter(true);
            m6227c();
        }
    }

    /* renamed from: c */
    private void m6227c() {
        getAsyncTaskQueue().a(new ClubActUserListActivity$1(this), new Void[0]);
    }
}
