package com.beastbikes.android.modules.cycling.sections.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.sections.dto.C2224e;
import com.beastbikes.android.modules.cycling.sections.p069a.C2219a;
import com.beastbikes.android.widget.C2638d;
import com.beastbikes.android.widget.C2638d.C2631b;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903125)
public class FavorSegmentActivity extends SessionFragmentActivity implements C2534b, C2631b {
    @C1458a(a = 2131755705)
    /* renamed from: a */
    private LinearLayout f5658a;
    @C1458a(a = 2131755706)
    /* renamed from: b */
    private TextView f5659b;
    /* renamed from: c */
    private C2638d f5660c;
    /* renamed from: d */
    private C2229a f5661d;
    /* renamed from: e */
    private List<C2224e> f5662e = new ArrayList();
    /* renamed from: f */
    private C2219a f5663f;
    /* renamed from: g */
    private int f5664g = 1;
    /* renamed from: h */
    private int f5665h = 20;
    /* renamed from: i */
    private boolean f5666i;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5663f = new C2219a(this);
        this.f5661d = new C2229a(this, this);
        this.f5660c = new C2638d(this, this.f5658a, this.f5662e, 2);
        this.f5660c.setAdapter(this.f5661d);
        this.f5660c.setRecyclerCallBack(this);
        m6919c();
    }

    /* renamed from: a */
    public void m6925a() {
        this.f5664g = 1;
        this.f5666i = true;
        m6919c();
    }

    public void a_() {
        this.f5664g++;
        m6919c();
    }

    /* renamed from: a */
    public void m6926a(ViewHolder viewHolder, int i) {
        Intent intent = new Intent(this, SectionDetailActivity.class);
        intent.putExtra("speedx_section_id", ((C2224e) this.f5662e.get(i)).a());
        startActivity(intent);
    }

    /* renamed from: b */
    public void m6927b(ViewHolder viewHolder, int i) {
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: c */
    private void m6919c() {
        getAsyncTaskQueue().a(new FavorSegmentActivity$1(this), new Void[0]);
    }
}
