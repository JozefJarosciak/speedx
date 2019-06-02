package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.mapbox.services.directions.v5.DirectionsCriteria;

@C1459b(a = 2130903290)
public class CyclingDataActivity extends SessionFragmentActivity {
    @C1458a(a = 2131756429)
    /* renamed from: a */
    private TextView f4595a;
    @C1458a(a = 2131756431)
    /* renamed from: b */
    private TextView f4596b;
    @C1458a(a = 2131756433)
    /* renamed from: c */
    private TextView f4597c;
    @C1458a(a = 2131756436)
    /* renamed from: d */
    private TextView f4598d;
    @C1458a(a = 2131756438)
    /* renamed from: e */
    private TextView f4599e;
    @C1458a(a = 2131756443)
    /* renamed from: f */
    private TextView f4600f;
    @C1458a(a = 2131756444)
    /* renamed from: g */
    private TextView f4601g;
    @C1458a(a = 2131756447)
    /* renamed from: h */
    private TextView f4602h;
    @C1458a(a = 2131756428)
    /* renamed from: i */
    private TextView f4603i;
    @C1458a(a = 2131756430)
    /* renamed from: j */
    private TextView f4604j;
    @C1458a(a = 2131756432)
    /* renamed from: k */
    private TextView f4605k;
    @C1458a(a = 2131756435)
    /* renamed from: l */
    private TextView f4606l;
    @C1458a(a = 2131756437)
    /* renamed from: m */
    private TextView f4607m;
    @C1458a(a = 2131756434)
    /* renamed from: n */
    private TextView f4608n;
    @C1458a(a = 2131756448)
    /* renamed from: o */
    private TextView f4609o;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        ActivityDTO activityDTO = (ActivityDTO) intent.getSerializableExtra(DirectionsCriteria.PROFILE_CYCLING);
        if (activityDTO != null) {
            m5984a(activityDTO);
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    private void m5984a(ActivityDTO activityDTO) {
        double d = 0.0d;
        if (C1849a.b(this)) {
            this.f4595a.setText(String.format("%.2f", new Object[]{Double.valueOf(activityDTO.getMaxAltitude())}));
            this.f4596b.setText(String.format("%.2f", new Object[]{Double.valueOf(activityDTO.getRiseTotal())}));
            this.f4597c.setText(String.format("%.2f", new Object[]{Double.valueOf(activityDTO.getUphillDistance())}));
            this.f4598d.setText(String.format("%.2f", new Object[]{Double.valueOf(activityDTO.getMaxVelocity())}));
            this.f4599e.setText(String.format("%.2f", new Object[]{Double.valueOf(activityDTO.getVelocity())}));
            this.f4600f.setText(C2555d.g(activityDTO.getStartTime()));
            this.f4601g.setText(C2555d.g(activityDTO.getStopTime()));
            this.f4602h.setText(String.format("%.2f", new Object[]{Double.valueOf(activityDTO.getCalories())}));
        } else {
            this.f4595a.setText(String.format("%.2f", new Object[]{Double.valueOf(C1849a.c(activityDTO.getMaxAltitude()))}));
            this.f4603i.setText("ft");
            this.f4596b.setText(String.format("%.2f", new Object[]{Double.valueOf(C1849a.c(activityDTO.getRiseTotal()))}));
            this.f4604j.setText("ft");
            this.f4597c.setText(String.format("%.2f", new Object[]{Double.valueOf(C1849a.c(activityDTO.getUphillDistance()))}));
            this.f4605k.setText("ft");
            this.f4598d.setText(String.format("%.2f", new Object[]{Double.valueOf(C1849a.d(activityDTO.getMaxVelocity()))}));
            this.f4606l.setText("MPH");
            this.f4599e.setText(String.format("%.2f", new Object[]{Double.valueOf(C1849a.d(activityDTO.getVelocity()))}));
            this.f4607m.setText("MPH");
            this.f4600f.setText(C2555d.g(activityDTO.getStartTime()));
            this.f4601g.setText(C2555d.g(activityDTO.getStopTime()));
            this.f4602h.setText(String.format("%.2f", new Object[]{Double.valueOf(activityDTO.getCalories())}));
        }
        double maxCadence = Double.isNaN(activityDTO.getMaxCadence()) ? 0.0d : activityDTO.getMaxCadence();
        if (!Double.isNaN(activityDTO.getMaxCardiacRate())) {
            d = activityDTO.getMaxCardiacRate();
        }
        this.f4608n.setText(maxCadence + "  RPM");
        this.f4609o.setText(d + "  BPM");
    }
}
