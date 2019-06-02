package com.beastbikes.android.modules.cycling.stage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.stage.dto.StageRankDTO;
import com.beastbikes.android.modules.cycling.stage.p135c.C2254d;
import com.beastbikes.android.modules.cycling.stage.p137d.C2260d;
import com.beastbikes.android.modules.cycling.stage.ui.C2280c.C2279b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;

@C1459b(a = 2130903200)
public class StageRankListActivity extends SessionFragmentActivity implements C2260d, C2279b {
    @C1458a(a = 2131755786)
    /* renamed from: a */
    private Toolbar f5849a;
    @C1458a(a = 2131756070)
    /* renamed from: b */
    private RecyclerView f5850b;
    /* renamed from: c */
    private C2280c f5851c;
    /* renamed from: d */
    private C2254d f5852d;
    /* renamed from: e */
    private C1802i f5853e;
    /* renamed from: f */
    private int f5854f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m7096e();
        m7097f();
        this.f5854f = getIntent().getIntExtra("stage_id", -1);
        this.f5852d.a(this.f5854f);
    }

    /* renamed from: a */
    public StageRankListActivity m7098a() {
        return this;
    }

    /* renamed from: b */
    public void m7103b() {
        m7102a(true, "");
    }

    /* renamed from: c */
    public void m7105c() {
        m7106d();
    }

    /* renamed from: a */
    public void m7100a(ArrayList<StageRankDTO> arrayList, boolean z) {
        if (arrayList != null && !arrayList.isEmpty()) {
            this.f5851c.a(arrayList, z);
        }
    }

    /* renamed from: a */
    public void m7101a(boolean z) {
    }

    /* renamed from: b */
    public void m7104b(boolean z) {
    }

    /* renamed from: a */
    public void m7099a(StageRankDTO stageRankDTO) {
        Intent intent = new Intent(this, StageProfileScoreActivity.class);
        intent.putExtra("stage_id", this.f5854f);
        intent.putExtra("user_id", stageRankDTO.getUserId());
        startActivity(intent);
    }

    /* renamed from: e */
    private void m7096e() {
        setSupportActionBar(this.f5849a);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator((int) C1373R.drawable.ic_back_icon);
        }
        this.f5851c = new C2280c(this, false);
        this.f5850b.setLayoutManager(new LinearLayoutManager(this));
        this.f5850b.setHasFixedSize(true);
        this.f5850b.setAdapter(this.f5851c);
        this.f5852d = new C2254d(this);
    }

    /* renamed from: f */
    private void m7097f() {
        this.f5851c.a(this);
        this.f5850b.addOnScrollListener(new StageRankListActivity$1(this, (LinearLayoutManager) this.f5850b.getLayoutManager()));
    }

    /* renamed from: a */
    protected void m7102a(boolean z, String str) {
        if (getWindow() != null && !isFinishing()) {
            if (this.f5853e == null) {
                this.f5853e = new C1802i(this, str, z);
            }
            if (!this.f5853e.isShowing()) {
                this.f5853e.show();
            }
        }
    }

    /* renamed from: d */
    protected void m7106d() {
        if (this.f5853e != null && !isFinishing() && getWindow() != null) {
            if (this.f5853e.isShowing()) {
                this.f5853e.dismiss();
            }
            this.f5853e = null;
        }
    }
}
