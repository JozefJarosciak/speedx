package com.beastbikes.android.modules.cycling.sections.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.sections.dto.C2224e;
import com.beastbikes.android.modules.cycling.sections.p069a.C2219a;
import com.beastbikes.android.widget.C2638d;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903174)
public class RecordSegmentActivity extends SessionFragmentActivity implements C2534b {
    @C1458a(a = 2131755914)
    /* renamed from: a */
    private LinearLayout f5667a;
    /* renamed from: b */
    private String f5668b;
    /* renamed from: c */
    private C2638d f5669c;
    /* renamed from: d */
    private C2229a f5670d;
    /* renamed from: e */
    private List<C2224e> f5671e;
    /* renamed from: f */
    private C2219a f5672f;
    @C1458a(a = 2131755915)
    /* renamed from: g */
    private LinearLayout f5673g;
    @C1458a(a = 2131755916)
    /* renamed from: h */
    private TextView f5674h;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5668b = getIntent().getStringExtra("activity_id");
        if (TextUtils.isEmpty(this.f5668b)) {
            finish();
        }
        this.f5671e = new ArrayList();
        this.f5672f = new C2219a(this);
        this.f5670d = new C2229a(this, this);
        this.f5669c = new C2638d(this, this.f5667a, this.f5671e, 2);
        this.f5669c.setAdapter(this.f5670d);
        this.f5669c.setHasFooter(false);
        this.f5669c.setRefreshEnable(false);
        m6929a();
    }

    /* renamed from: a */
    public void m6935a(ViewHolder viewHolder, int i) {
        Intent intent = new Intent(this, SectionDetailActivity.class);
        intent.putExtra("speedx_section_id", ((C2224e) this.f5671e.get(i)).a());
        startActivity(intent);
    }

    /* renamed from: b */
    public void m6936b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: a */
    private void m6929a() {
        getAsyncTaskQueue().a(new RecordSegmentActivity$1(this), new Void[0]);
    }
}
