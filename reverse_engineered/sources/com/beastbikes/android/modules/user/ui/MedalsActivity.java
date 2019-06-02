package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.user.dto.MedalDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2801d;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903151)
public class MedalsActivity extends SessionFragmentActivity implements OnClickListener, C2534b {
    @C1458a(a = 2131755809)
    /* renamed from: a */
    private RecyclerView f6439a;
    @C1458a(a = 2131755814)
    /* renamed from: b */
    private RecyclerView f6440b;
    @C1458a(a = 2131755808)
    /* renamed from: c */
    private LinearLayout f6441c;
    @C1458a(a = 2131755815)
    /* renamed from: d */
    private TextView f6442d;
    @C1458a(a = 2131755807)
    /* renamed from: e */
    private ViewGroup f6443e;
    @C1458a(a = 2131755812)
    /* renamed from: f */
    private ViewGroup f6444f;
    @C1458a(a = 2131755810)
    /* renamed from: g */
    private ViewGroup f6445g;
    /* renamed from: h */
    private int f6446h;
    /* renamed from: i */
    private C2389c f6447i;
    /* renamed from: j */
    private MedalsActivity$b f6448j;
    /* renamed from: k */
    private MedalsActivity$b f6449k;
    /* renamed from: l */
    private List<MedalDTO> f6450l = new ArrayList();
    /* renamed from: m */
    private List<MedalDTO> f6451m = new ArrayList();
    /* renamed from: n */
    private List<MedalDTO> f6452n = new ArrayList();
    /* renamed from: o */
    private MedalDTO f6453o;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        int intExtra = getIntent().getIntExtra("medal_count", 0);
        this.f6442d.setText(String.format(getString(C1373R.string.label_medals_count), new Object[]{Integer.valueOf(intExtra)}));
        this.f6445g.setOnClickListener(this);
        this.f6444f.setOnClickListener(this);
        this.f6447i = new C2389c(this);
        this.f6448j = new MedalsActivity$b(this, this, this.f6450l, true);
        this.f6439a.setHasFixedSize(true);
        this.f6439a.setAdapter(this.f6448j);
        this.f6439a.setLayoutManager(new GridLayoutManager(this, 3));
        this.f6449k = new MedalsActivity$b(this, this, this.f6451m, false);
        this.f6440b.setHasFixedSize(true);
        this.f6440b.setAdapter(this.f6449k);
        this.f6440b.setLayoutManager(new GridLayoutManager(this, 3));
        this.f6446h = (((WindowManager) getSystemService("window")).getDefaultDisplay().getHeight() - C2801d.a(this, 208.0f)) - m7660a();
        m7663a(0);
        m7663a(1);
        if (getIntent().getBooleanExtra("from_push", false)) {
            m7663a(2);
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.activity_medals_visible_view:
                m7667c();
                return;
            case C1373R.id.activity_medals_already_expired_view:
                m7669d();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m7676a(ViewHolder viewHolder, int i) {
        if (this.f6453o != null) {
            C2580w.a(this, "", "click_meadl_details");
            Intent intent = new Intent(this, MedalInfoActivity.class);
            intent.putExtra("user_id", m5331p());
            intent.putExtra("medal_id", this.f6453o.getId());
            intent.putExtra("medal_position", i);
            if (this.f6453o.getStatus() == 0 || this.f6453o.getStatus() == 2) {
                intent.putExtra("medal_list", (Serializable) this.f6450l);
            } else {
                intent.putExtra("medal_list", (Serializable) this.f6451m);
            }
            startActivity(intent);
        }
    }

    /* renamed from: b */
    public void m7677b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: a */
    private int m7660a() {
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            return getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return C2801d.a(this, 24.0f);
        }
    }

    /* renamed from: a */
    private void m7663a(int i) {
        getAsyncTaskQueue().a(new MedalsActivity$1(this, i), new String[0]);
    }

    /* renamed from: b */
    private void m7665b() {
        Intent intent = new Intent(this, MedalInfoActivity.class);
        intent.putExtra("medal_list", (Serializable) this.f6452n);
        intent.putExtra("from_push", true);
        startActivity(intent);
    }

    /* renamed from: c */
    private void m7667c() {
        this.f6444f.setVisibility(0);
        this.f6443e.setVisibility(8);
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (-this.f6446h));
        translateAnimation.setDuration(500);
        this.f6443e.setAnimation(translateAnimation);
        translateAnimation.start();
        translateAnimation.setAnimationListener(new MedalsActivity$2(this));
        translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (this.f6446h - C2801d.a(this, 57.0f)), 0.0f);
        translateAnimation.setDuration(500);
        this.f6444f.setAnimation(translateAnimation);
        translateAnimation.start();
    }

    /* renamed from: d */
    private void m7669d() {
        this.f6444f.setVisibility(8);
        this.f6443e.setVisibility(0);
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (-this.f6446h), 0.0f);
        translateAnimation.setDuration(500);
        this.f6443e.setAnimation(translateAnimation);
        translateAnimation.start();
        translateAnimation.setAnimationListener(new MedalsActivity$3(this));
        translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (this.f6446h - C2801d.a(this, 45.0f)));
        translateAnimation.setDuration(500);
        this.f6444f.setAnimation(translateAnimation);
        translateAnimation.start();
    }
}
