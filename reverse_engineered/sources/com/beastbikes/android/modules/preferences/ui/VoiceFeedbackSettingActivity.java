package com.beastbikes.android.modules.preferences.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.ViewGroup;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;

@C1457a(a = "语音提示页")
@C1459b(a = 2130903731)
public class VoiceFeedbackSettingActivity extends BaseFragmentActivity implements C1371a {
    @C1458a(a = 2131757776)
    /* renamed from: a */
    private Switch f5962a;
    @C1458a(a = 2131757778)
    /* renamed from: b */
    private Switch f5963b;
    @C1458a(a = 2131757779)
    /* renamed from: c */
    private Switch f5964c;
    @C1458a(a = 2131757780)
    /* renamed from: d */
    private Switch f5965d;
    @C1458a(a = 2131757781)
    /* renamed from: e */
    private Switch f5966e;
    @C1458a(a = 2131757782)
    /* renamed from: f */
    private Switch f5967f;
    @C1458a(a = 2131757777)
    /* renamed from: g */
    private ViewGroup f5968g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        BeastBikes beastBikes = (BeastBikes) getApplication();
        this.f5962a.setOnCheckedChangeListener(new VoiceFeedbackSettingActivity$1(this, beastBikes));
        this.f5967f.setOnCheckedChangeListener(new VoiceFeedbackSettingActivity$2(this, beastBikes));
        this.f5966e.setOnCheckedChangeListener(new VoiceFeedbackSettingActivity$3(this, beastBikes));
        this.f5963b.setOnCheckedChangeListener(new VoiceFeedbackSettingActivity$4(this, beastBikes));
        this.f5964c.setOnCheckedChangeListener(new VoiceFeedbackSettingActivity$5(this, beastBikes));
        this.f5965d.setOnCheckedChangeListener(new VoiceFeedbackSettingActivity$6(this, beastBikes));
    }

    protected void onResume() {
        super.onResume();
        BeastBikes beastBikes = (BeastBikes) getApplication();
        if (beastBikes.m5250b(Integer.MIN_VALUE)) {
            this.f5962a.setChecked(true);
            this.f5968g.setVisibility(0);
        } else {
            this.f5962a.setChecked(false);
            this.f5968g.setVisibility(8);
        }
        this.f5967f.setChecked(beastBikes.m5250b(16));
        this.f5966e.setChecked(beastBikes.m5250b(8));
        this.f5963b.setChecked(beastBikes.m5250b(1));
        this.f5964c.setChecked(beastBikes.m5250b(2));
        this.f5965d.setChecked(beastBikes.m5250b(4));
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }
}
