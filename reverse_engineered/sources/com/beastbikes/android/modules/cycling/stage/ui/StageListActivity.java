package com.beastbikes.android.modules.cycling.stage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.stage.dto.StageDTO;
import com.beastbikes.android.modules.cycling.stage.ui.C2270a.C2268a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903199)
public class StageListActivity extends SessionFragmentActivity implements C2268a {
    /* renamed from: a */
    private static final Logger f5786a = LoggerFactory.getLogger(StageListActivity.class.getName());
    @C1458a(a = 2131756068)
    /* renamed from: b */
    private RecyclerView f5787b;
    /* renamed from: c */
    private C2270a f5788c;
    @C1458a(a = 2131756069)
    /* renamed from: d */
    private TextView f5789d;
    /* renamed from: e */
    private ArrayList<StageDTO> f5790e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m7021a();
    }

    /* renamed from: a */
    private void m7021a() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator((int) C1373R.drawable.ic_back_icon);
        }
        this.f5787b.setLayoutManager(new LinearLayoutManager(this));
        this.f5787b.setHasFixedSize(true);
        this.f5788c = new C2270a(this);
        this.f5788c.a(this);
        this.f5787b.setAdapter(this.f5788c);
        this.f5790e = (ArrayList) getIntent().getSerializableExtra("extra_stage_list");
        if (this.f5790e == null || this.f5790e.isEmpty()) {
            f5786a.error("stage list is null or empty");
            this.f5789d.setVisibility(0);
            return;
        }
        this.f5788c.a(this.f5790e);
        this.f5789d.setVisibility(8);
    }

    /* renamed from: a */
    public void m7022a(StageDTO stageDTO) {
        Intent intent = new Intent(this, StageMapListBaseActivity.class);
        intent.putExtra("intent_type", 18);
        intent.putExtra("stage_info", stageDTO);
        startActivity(intent);
    }
}
