package com.beastbikes.android.modules.train.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.alipay.sdk.cons.C0844a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.train.dto.TrainResultDTO;
import com.beastbikes.android.modules.train.p076a.C2350b;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;

@C1459b(a = 2130903109)
public class CreateTrainLongTimePlanActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131755591)
    /* renamed from: a */
    private LinearLayout f6200a;
    @C1458a(a = 2131755594)
    /* renamed from: b */
    private ListView f6201b;
    @C1458a(a = 2131755592)
    /* renamed from: c */
    private TextView f6202c;
    @C1458a(a = 2131755593)
    /* renamed from: d */
    private TextView f6203d;
    @C1458a(a = 2131755595)
    /* renamed from: e */
    private LinearLayout f6204e;
    @C1458a(a = 2131755598)
    /* renamed from: f */
    private ListView f6205f;
    @C1458a(a = 2131755596)
    /* renamed from: g */
    private TextView f6206g;
    @C1458a(a = 2131755597)
    /* renamed from: h */
    private TextView f6207h;
    @C1458a(a = 2131755599)
    /* renamed from: i */
    private Button f6208i;
    /* renamed from: j */
    private int f6209j = -1;
    /* renamed from: k */
    private int f6210k = -1;
    /* renamed from: l */
    private int f6211l = 0;
    /* renamed from: m */
    private C2350b f6212m;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator((int) C1373R.drawable.ic_close_icon);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent != null) {
            this.f6211l = intent.getIntExtra("cycle", 1);
        }
        this.f6212m = new C2350b(this);
        this.f6204e.setOnClickListener(this);
        this.f6200a.setOnClickListener(this);
        this.f6208i.setOnClickListener(this);
        m7398a();
        m7405b();
        m7409c();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.train_plan_riding_times_view:
                m7411d();
                return;
            case C1373R.id.train_plan_cycling_time_view:
                m7413e();
                return;
            case C1373R.id.train_plan_complete_btn:
                m7415g();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m7398a() {
        getAsyncTaskQueue().a(new CreateTrainLongTimePlanActivity$1(this, new C2389c(this)), new String[0]);
    }

    /* renamed from: b */
    private void m7405b() {
        String[] stringArray = getResources().getStringArray(C1373R.array.riding_times_desc);
        this.f6201b.setAdapter(new CreateTrainLongTimePlanActivity$a(this, stringArray));
        this.f6201b.setOnItemClickListener(new CreateTrainLongTimePlanActivity$2(this, stringArray));
    }

    /* renamed from: c */
    private void m7409c() {
        String[] stringArray = getResources().getStringArray(C1373R.array.cycling_time_value);
        this.f6205f.setAdapter(new CreateTrainLongTimePlanActivity$a(this, stringArray));
        this.f6205f.setOnItemClickListener(new CreateTrainLongTimePlanActivity$3(this, stringArray));
    }

    /* renamed from: a */
    private void m7402a(String str) {
        this.f6201b.setVisibility(8);
        this.f6203d.setText(getString(C1373R.string.str_riding_faw_times_a_week) + " :  " + str);
        this.f6202c.setText(C1373R.string.empty);
        this.f6202c.setBackgroundResource(C1373R.drawable.ic_train_plan_success);
        m7414f();
    }

    /* renamed from: d */
    private void m7411d() {
        this.f6201b.setVisibility(0);
        this.f6202c.setText(C0844a.f2048d);
        this.f6202c.setBackgroundResource(C1373R.drawable.dot_white);
        this.f6208i.setVisibility(8);
    }

    /* renamed from: b */
    private void m7407b(String str) {
        this.f6205f.setVisibility(8);
        this.f6207h.setText(getString(C1373R.string.str_avg_how_many_hours_fo_cycling) + " :  " + str);
        this.f6206g.setText(C1373R.string.empty);
        this.f6206g.setBackgroundResource(C1373R.drawable.ic_train_plan_success);
        m7414f();
    }

    /* renamed from: e */
    private void m7413e() {
        this.f6205f.setVisibility(0);
        this.f6206g.setText("2");
        this.f6206g.setBackgroundResource(C1373R.drawable.dot_white);
        this.f6208i.setVisibility(8);
    }

    /* renamed from: f */
    private void m7414f() {
        if (this.f6209j < 0 || this.f6210k < 0) {
            this.f6208i.setVisibility(8);
        } else {
            this.f6208i.setVisibility(0);
        }
    }

    /* renamed from: g */
    private void m7415g() {
        getAsyncTaskQueue().a(new CreateTrainLongTimePlanActivity$4(this, new C1802i(this, C1373R.string.str_train_plan_create_loading_msg, false)), new Integer[0]);
    }

    /* renamed from: a */
    private void m7399a(TrainResultDTO trainResultDTO) {
        if (trainResultDTO != null) {
            Toasts.show(this, C1373R.string.str_train_plan_create_surcess);
            Intent intent = new Intent(this, CreateTrainPlanSuccessActivity.class);
            intent.putExtra("train_result", trainResultDTO);
            startActivity(intent);
            finish();
        }
    }
}
