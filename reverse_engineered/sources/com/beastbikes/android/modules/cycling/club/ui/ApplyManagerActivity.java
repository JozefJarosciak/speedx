package com.beastbikes.android.modules.cycling.club.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.ApplyDTO;
import com.beastbikes.android.utils.C2570p;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

@C1457a(a = "入队申请")
@C1459b(a = 2130903074)
public class ApplyManagerActivity extends SessionFragmentActivity implements C1371a {
    @C1458a(a = 2131755332)
    /* renamed from: a */
    private ListView f4802a;
    @C1458a(a = 2131755333)
    /* renamed from: b */
    private TextView f4803b;
    /* renamed from: c */
    private ClubManager f4804c;
    /* renamed from: d */
    private ApplyManagerActivity$a f4805d;
    /* renamed from: e */
    private List<ApplyDTO> f4806e = new ArrayList();
    /* renamed from: f */
    private int f4807f = 1;
    /* renamed from: g */
    private int f4808g;
    /* renamed from: h */
    private int f4809h;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ApplyManagerActivity$b */
    private static final class C1405b extends ViewHolder<ApplyDTO> {
        @C1458a(a = 2131756186)
        /* renamed from: a */
        public View f4794a;
        /* renamed from: b */
        private ClubManager f4795b;
        @C1458a(a = 2131756179)
        /* renamed from: c */
        private CircleImageView f4796c;
        @C1458a(a = 2131756180)
        /* renamed from: d */
        private TextView f4797d;
        @C1458a(a = 2131756181)
        /* renamed from: e */
        private TextView f4798e;
        @C1458a(a = 2131756184)
        /* renamed from: f */
        private TextView f4799f;
        @C1458a(a = 2131756182)
        /* renamed from: g */
        private TextView f4800g;
        @C1458a(a = 2131756183)
        /* renamed from: h */
        private TextView f4801h;

        public /* synthetic */ void bind(Object obj) {
            m6175a((ApplyDTO) obj);
        }

        protected C1405b(View view, ClubManager clubManager) {
            super(view);
            this.f4795b = clubManager;
        }

        /* renamed from: a */
        private void m6169a() {
            C2621c c2621c = new C2621c(getContext());
            c2621c.b(C1373R.string.club_full_notice).a(C1373R.string.activity_alert_dialog_text_ok, new ApplyManagerActivity$b$1(this, c2621c));
            c2621c.a();
        }

        /* renamed from: a */
        public void m6175a(ApplyDTO applyDTO) {
            if (applyDTO != null) {
                if (TextUtils.isEmpty(applyDTO.getAvatarUrl())) {
                    this.f4796c.setImageResource(C1373R.drawable.ic_avatar);
                } else {
                    Picasso.with(getContext()).load(applyDTO.getAvatarUrl()).fit().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).centerCrop().into(this.f4796c);
                }
                this.f4797d.setText(C2570p.a(applyDTO.getNickname(), applyDTO.getRemarks()));
                CharSequence extra = applyDTO.getExtra();
                if (TextUtils.isEmpty(extra)) {
                    extra = getContext().getString(C1373R.string.activity_club_apply_default_extra);
                }
                this.f4798e.setText(extra);
                this.f4799f.setOnClickListener(new ApplyManagerActivity$b$2(this, applyDTO));
                this.f4800g.setOnClickListener(new ApplyManagerActivity$b$3(this, applyDTO));
                switch (applyDTO.getStatus()) {
                    case 0:
                        this.f4801h.setVisibility(8);
                        this.f4799f.setVisibility(0);
                        this.f4800g.setVisibility(0);
                        return;
                    case 1:
                        this.f4799f.setVisibility(8);
                        this.f4800g.setVisibility(8);
                        this.f4801h.setVisibility(0);
                        this.f4801h.setText(C1373R.string.activity_club_apply_item_status_agreed);
                        return;
                    case 2:
                        this.f4799f.setVisibility(8);
                        this.f4800g.setVisibility(8);
                        this.f4801h.setVisibility(0);
                        this.f4801h.setText(C1373R.string.activity_club_apply_item_status_canceled);
                        return;
                    case 3:
                        this.f4799f.setVisibility(8);
                        this.f4800g.setVisibility(8);
                        this.f4801h.setVisibility(0);
                        this.f4801h.setText(C1373R.string.activity_club_apply_item_status_refused);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f4804c = new ClubManager(this);
        this.f4805d = new ApplyManagerActivity$a(this, this.f4806e, this.f4804c);
        this.f4802a.setOnItemClickListener(new ApplyManagerActivity$1(this));
        this.f4802a.setAdapter(this.f4805d);
        SharedPreferences sharedPreferences = getSharedPreferences(m5331p(), 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt("beast.club.dot.more", 0).commit();
        }
    }

    protected void onResume() {
        super.onResume();
        m6181b();
        m6178a();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    private void m6178a() {
        getAsyncTaskQueue().a(new ApplyManagerActivity$2(this), new Void[0]);
    }

    /* renamed from: b */
    private void m6181b() {
        getAsyncTaskQueue().a(new ApplyManagerActivity$3(this, m5331p()), new Void[0]);
    }
}
