package com.beastbikes.android.ble.ui;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.C1602a;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.ui.p098a.C1719h;
import com.beastbikes.android.ble.ui.p098a.C1743q;
import com.beastbikes.android.ble.ui.p098a.C1743q.C1742a;
import com.beastbikes.android.ble.ui.widget.HeartRateIntervalItemView;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.widget.C2657e;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903193)
@C1460c(a = 2131820552)
public class SpeedXHeartRateSettingActivity extends SessionFragmentActivity implements OnClickListener, C1742a {
    /* renamed from: a */
    private final Logger f4322a = LoggerFactory.getLogger(SpeedXHeartRateSettingActivity.class);
    @C1458a(a = 2131756037)
    /* renamed from: b */
    private HeartRateIntervalItemView f4323b;
    @C1458a(a = 2131756038)
    /* renamed from: c */
    private HeartRateIntervalItemView f4324c;
    @C1458a(a = 2131756039)
    /* renamed from: d */
    private HeartRateIntervalItemView f4325d;
    @C1458a(a = 2131756040)
    /* renamed from: e */
    private HeartRateIntervalItemView f4326e;
    @C1458a(a = 2131756041)
    /* renamed from: f */
    private HeartRateIntervalItemView f4327f;
    @C1458a(a = 2131756035)
    /* renamed from: g */
    private TextView f4328g;
    @C1458a(a = 2131756036)
    /* renamed from: h */
    private Button f4329h;
    /* renamed from: i */
    private int f4330i;
    /* renamed from: j */
    private C2389c f4331j;
    /* renamed from: k */
    private C1602a f4332k;
    /* renamed from: l */
    private int f4333l;
    /* renamed from: m */
    private ServiceConnection f4334m = new SpeedXHeartRateSettingActivity$4(this);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.setPackage(getPackageName());
        bindService(intent, this.f4334m, 1);
        this.f4331j = new C2389c(this);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f4330i = getIntent().getIntExtra("heart_rate_value", 0);
        this.f4333l = getIntent().getIntExtra("hardware_type", 1);
        if (this.f4330i == 0) {
            m5681a();
        }
        this.f4329h.setOnClickListener(this);
        m5688c(this.f4330i);
        SharedPreferences sharedPreferences = getSharedPreferences(m5331p(), 0);
        if (!sharedPreferences.contains("beast.ble.heart.guide.dialog")) {
            new C1719h(this).show();
            sharedPreferences.edit().putBoolean("beast.ble.heart.guide.dialog", true).apply();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f4332k != null) {
            unbindService(this.f4334m);
        }
    }

    public void finish() {
        getIntent().putExtra("heart_rate_value", this.f4330i);
        setResult(-1, getIntent());
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_item_heart_rate_help:
                Intent intent = new Intent(this, BrowserActivity.class);
                intent.setData(Uri.parse("https://hybrid.speedx.com/hr-notice"));
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.heart_rate_setting_btn:
                String str = "dialog_fragment";
                FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
                if (getFragmentManager().findFragmentByTag(str) == null) {
                    Fragment c1743q = new C1743q();
                    c1743q.a(this);
                    beginTransaction.add(c1743q, str).commitAllowingStateLoss();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m5695a(int i) {
        if (i == -1) {
            m5684b();
            return;
        }
        this.f4330i = i;
        m5688c(i);
        m5690d(this.f4330i);
    }

    /* renamed from: a */
    private void m5681a() {
        getAsyncTaskQueue().a(new SpeedXHeartRateSettingActivity$1(this), new String[0]);
    }

    /* renamed from: b */
    private int m5682b(int i) {
        if (i < 1) {
            return 205;
        }
        return 205 - (i / 2);
    }

    @TargetApi(17)
    /* renamed from: b */
    private void m5684b() {
        if (!isDestroyed() && !isFinishing()) {
            String string = getString(C1373R.string.title_heart_rate_setting_by_age);
            ArrayList arrayList = new ArrayList();
            for (int i = 1; i <= 100; i++) {
                arrayList.add(String.valueOf(i));
            }
            new C2657e(this, string, arrayList, 23, new SpeedXHeartRateSettingActivity$2(this)).showAtLocation(findViewById(C1373R.id.activity_speedx_heart_rate_setting), 80, 0, 0);
        }
    }

    /* renamed from: c */
    private void m5688c(int i) {
        this.f4328g.setVisibility(0);
        this.f4328g.setText(String.valueOf(i));
        String string = getString(C1373R.string.label_heart_rate_recovery_default);
        String string2 = getString(C1373R.string.label_heart_rate_burning_fat_default);
        String string3 = getString(C1373R.string.label_heart_rate_target_default);
        String string4 = getString(C1373R.string.label_heart_rate_anaerobic_default);
        String string5 = getString(C1373R.string.label_heart_rate_limit_default);
        if (i > 0) {
            string = ((int) Math.ceil(((double) i) * 0.5d)) + HelpFormatter.DEFAULT_OPT_PREFIX + ((int) Math.ceil(((double) i) * 0.6d)) + "BPM";
            string2 = (((int) Math.ceil(((double) i) * 0.6d)) + 1) + HelpFormatter.DEFAULT_OPT_PREFIX + ((int) Math.ceil(((double) i) * 0.7d)) + "BPM";
            string3 = (((int) Math.ceil(((double) i) * 0.7d)) + 1) + HelpFormatter.DEFAULT_OPT_PREFIX + ((int) Math.ceil(((double) i) * 0.8d)) + "BPM";
            string4 = (((int) Math.ceil(((double) i) * 0.8d)) + 1) + HelpFormatter.DEFAULT_OPT_PREFIX + ((int) Math.ceil(((double) i) * 0.9d)) + "BPM";
            string5 = (((int) Math.ceil(((double) i) * 0.9d)) + 1) + HelpFormatter.DEFAULT_OPT_PREFIX + i + "BPM";
        }
        this.f4323b.setHeartRateValue(string);
        this.f4324c.setHeartRateValue(string2);
        this.f4325d.setHeartRateValue(string3);
        this.f4326e.setHeartRateValue(string4);
        this.f4327f.setHeartRateValue(string5);
    }

    /* renamed from: d */
    private void m5690d(int i) {
        if (C1661h.a().b() == null) {
            Toasts.show(this, getString(C1373R.string.str_ble_retry_after_connect_to_device));
        } else {
            getAsyncTaskQueue().a(new SpeedXHeartRateSettingActivity$3(this, i), new String[0]);
        }
    }
}
