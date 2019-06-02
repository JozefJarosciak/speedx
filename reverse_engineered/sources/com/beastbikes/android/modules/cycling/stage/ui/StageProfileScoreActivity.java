package com.beastbikes.android.modules.cycling.stage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedActivity;
import com.beastbikes.android.modules.cycling.stage.dto.C2262a;
import com.beastbikes.android.modules.cycling.stage.p135c.C2239a;
import com.beastbikes.android.modules.cycling.stage.p137d.C2257a;
import com.beastbikes.android.modules.cycling.stage.ui.C2275b.C2273b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903201)
public class StageProfileScoreActivity extends SessionFragmentActivity implements OnClickListener, C2257a, C2273b {
    /* renamed from: a */
    private static final Logger f5843a = LoggerFactory.getLogger(StageProfileScoreActivity.class.getName());
    @C1458a(a = 2131756071)
    /* renamed from: b */
    private RecyclerView f5844b;
    @C1458a(a = 2131756072)
    /* renamed from: c */
    private TextView f5845c;
    /* renamed from: d */
    private C2275b f5846d;
    /* renamed from: e */
    private C2239a f5847e;
    /* renamed from: f */
    private C1802i f5848f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m7083g();
        m7084h();
        int intExtra = getIntent().getIntExtra("stage_id", -1);
        String stringExtra = getIntent().getStringExtra("user_id");
        if (intExtra <= 0) {
            Toasts.show(this, getString(C1373R.string.str_data_error));
            finish();
            return;
        }
        this.f5847e.a(intExtra, stringExtra);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* renamed from: a */
    public StageProfileScoreActivity m7085a() {
        return this;
    }

    /* renamed from: b */
    public void m7089b() {
        m7088a(true, "");
    }

    /* renamed from: c */
    public void m7090c() {
        m7093f();
    }

    /* renamed from: a */
    public void m7087a(ArrayList<C2262a> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            this.f5845c.setVisibility(0);
            f5843a.error("stageScoreDTOs is null or empty");
            return;
        }
        f5843a.error("stageScoreDTOs");
        this.f5845c.setVisibility(8);
        this.f5846d.a(arrayList);
    }

    /* renamed from: d */
    public void m7091d() {
    }

    /* renamed from: e */
    public void m7092e() {
    }

    public void onClick(View view) {
    }

    /* renamed from: a */
    public void m7086a(C2262a c2262a) {
        Intent intent = new Intent(this, CyclingCompletedActivity.class);
        intent.putExtra("sport_identify", c2262a.i());
        intent.putExtra("user_id", c2262a.j());
        startActivity(intent);
    }

    /* renamed from: g */
    private void m7083g() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5846d = new C2275b(this);
        this.f5846d.a(this);
        this.f5844b.setNestedScrollingEnabled(false);
        this.f5844b.setHasFixedSize(false);
        this.f5844b.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.f5844b.setAdapter(this.f5846d);
        this.f5847e = new C2239a(this);
    }

    /* renamed from: h */
    private void m7084h() {
    }

    /* renamed from: a */
    protected void m7088a(boolean z, String str) {
        if (getWindow() != null && !isFinishing()) {
            if (this.f5848f == null) {
                this.f5848f = new C1802i(this, str, z);
            }
            if (!this.f5848f.isShowing()) {
                this.f5848f.show();
            }
        }
    }

    /* renamed from: f */
    protected void m7093f() {
        if (this.f5848f != null && !isFinishing() && getWindow() != null) {
            if (this.f5848f.isShowing()) {
                this.f5848f.dismiss();
            }
            this.f5848f = null;
        }
    }
}
