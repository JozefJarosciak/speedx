package com.beastbikes.android.modules.cycling.stage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.stage.dto.C2264c;
import com.beastbikes.android.modules.cycling.stage.p135c.C2256e;
import com.beastbikes.android.modules.cycling.stage.p137d.C2261e;
import com.beastbikes.android.modules.cycling.stage.ui.C2284d.C2282a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903202)
public class StageScoreListActivity extends SessionFragmentActivity implements C2261e, C2282a {
    /* renamed from: a */
    private static final Logger f5855a = LoggerFactory.getLogger(StageScoreListActivity.class.getName());
    @C1458a(a = 2131756073)
    /* renamed from: b */
    private RecyclerView f5856b;
    @C1458a(a = 2131756074)
    /* renamed from: c */
    private TextView f5857c;
    /* renamed from: d */
    private C2284d f5858d;
    /* renamed from: e */
    private C2256e f5859e;
    /* renamed from: f */
    private C1802i f5860f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m7107e();
    }

    /* renamed from: a */
    public StageScoreListActivity m7108a() {
        return this;
    }

    /* renamed from: a */
    public void m7110a(String str) {
        f5855a.error("onGetStageScoreListFailed msg: " + str);
        this.f5857c.setVisibility(0);
    }

    /* renamed from: a */
    public void m7111a(ArrayList<C2264c> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            this.f5857c.setVisibility(0);
            return;
        }
        this.f5857c.setVisibility(8);
        this.f5858d.a(arrayList);
    }

    /* renamed from: a */
    public void m7109a(C2264c c2264c) {
        if (c2264c != null) {
            Intent intent = new Intent(this, StageMapListBaseActivity.class);
            intent.putExtra("intent_type", 18);
            intent.putExtra("stage_id", c2264c.f());
            startActivity(intent);
        }
    }

    /* renamed from: b */
    public void m7113b() {
        m7112a(true, "");
    }

    /* renamed from: c */
    public void m7114c() {
        m7115d();
    }

    /* renamed from: e */
    private void m7107e() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator((int) C1373R.drawable.ic_back_icon);
        }
        this.f5856b.setLayoutManager(new LinearLayoutManager(this));
        this.f5856b.setHasFixedSize(true);
        this.f5858d = new C2284d(this);
        this.f5856b.setAdapter(this.f5858d);
        this.f5858d.a(this);
        this.f5859e = new C2256e(this);
        Object stringExtra = getIntent().getStringExtra("user_id");
        String stringExtra2 = getIntent().getStringExtra("activity_id");
        if (TextUtils.isEmpty(stringExtra)) {
            this.f5859e.a(m5331p(), stringExtra2);
        } else {
            this.f5859e.a(stringExtra, stringExtra2);
        }
    }

    /* renamed from: a */
    protected void m7112a(boolean z, String str) {
        if (getWindow() != null && !isFinishing()) {
            if (this.f5860f == null) {
                this.f5860f = new C1802i(this, str, z);
            }
            if (!this.f5860f.isShowing()) {
                this.f5860f.show();
            }
        }
    }

    /* renamed from: d */
    protected void m7115d() {
        if (this.f5860f != null && !isFinishing() && getWindow() != null) {
            if (this.f5860f.isShowing()) {
                this.f5860f.dismiss();
            }
            this.f5860f = null;
        }
    }
}
