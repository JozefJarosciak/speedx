package com.beastbikes.android.ble.ui;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.C1602a;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.biz.p097b.C1625i;
import com.beastbikes.android.ble.otadownload.C1677a;
import com.beastbikes.android.ble.otadownload.C1677a.C1675a;
import com.beastbikes.android.ble.ui.painter.PowerView;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.utils.C2557f;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903192)
public class SpeedXGpsUpdateActivity extends SessionFragmentActivity implements OnClickListener, C1625i, C1675a {
    /* renamed from: a */
    private Logger f4311a = LoggerFactory.getLogger(SpeedXGpsUpdateActivity.class);
    @C1458a(a = 2131756032)
    /* renamed from: b */
    private PowerView f4312b;
    @C1458a(a = 2131756030)
    /* renamed from: c */
    private TextView f4313c;
    @C1458a(a = 2131756031)
    /* renamed from: d */
    private TextView f4314d;
    @C1458a(a = 2131756033)
    /* renamed from: e */
    private Button f4315e;
    /* renamed from: f */
    private C1602a f4316f;
    /* renamed from: g */
    private boolean f4317g;
    /* renamed from: h */
    private boolean f4318h;
    /* renamed from: i */
    private C1614a f4319i;
    /* renamed from: j */
    private boolean f4320j;
    /* renamed from: k */
    private ServiceConnection f4321k = new SpeedXGpsUpdateActivity$8(this);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (getIntent() != null) {
            this.f4319i = C1661h.a().b();
        }
        getWindow().addFlags(128);
        this.f4312b.setChangeColor(false);
        this.f4315e.setOnClickListener(this);
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.setPackage(getPackageName());
        startService(intent);
        bindService(intent, this.f4321k, 1);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f4321k != null) {
            unbindService(this.f4321k);
        }
        if (this.f4316f != null) {
            this.f4316f.a(null);
        }
    }

    public void finish() {
        getWindow().clearFlags(128);
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: b */
    public void m5675b(int i) {
        this.f4317g = true;
        runOnUiThread(new SpeedXGpsUpdateActivity$1(this, (double) ((i + 1) * 190), this.f4319i.i().d()));
    }

    /* renamed from: c */
    public void m5677c(int i) {
        runOnUiThread(new SpeedXGpsUpdateActivity$2(this, i));
    }

    /* renamed from: c */
    public void m5676c() {
        runOnUiThread(new SpeedXGpsUpdateActivity$3(this));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332 || !this.f4317g) {
            return super.onOptionsItemSelected(menuItem);
        }
        m5662a();
        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.speedx_agps_update_cancel:
                if (this.f4317g) {
                    m5662a();
                    return;
                } else if (this.f4319i != null && this.f4316f != null) {
                    this.f4316f.a(this);
                    this.f4313c.setText(C1373R.string.label_speedx_agps_update_msg_1);
                    this.f4314d.setVisibility(0);
                    this.f4315e.setText(C1373R.string.label_cancel_update);
                    this.f4319i.i().a(false);
                    m5666b();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            return true;
        }
        if (keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (this.f4317g) {
            m5662a();
            return true;
        }
        finish();
        return super.dispatchKeyEvent(keyEvent);
    }

    /* renamed from: a */
    public void m5674a(int i, String str, String str2) {
        if (i == 4) {
            this.f4311a.info("Download A_GPS img is success");
            if (this.f4316f != null) {
                this.f4316f.a(i, str, str2);
            }
        }
    }

    /* renamed from: a */
    public void m5673a(int i) {
        if (i == 4) {
            this.f4311a.error("Download A_GPS img is error");
            Toasts.show(this, C1373R.string.str_ble_download_agps_file_error);
        }
    }

    /* renamed from: a */
    private void m5662a() {
        C2621c c2621c = new C2621c(this);
        c2621c.b(C1373R.string.msg_cancel_update_agps);
        c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new SpeedXGpsUpdateActivity$4(this, c2621c));
        c2621c.b(C1373R.string.cancel, new SpeedXGpsUpdateActivity$5(this, c2621c)).a();
    }

    /* renamed from: b */
    private void m5666b() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            C1677a c1677a = new C1677a(this, this);
            String str = "http://alp.u-blox.com/current_14d.alp";
            File a = c1677a.a(str);
            if (a != null && a.exists()) {
                C2557f.a(a.getAbsolutePath());
            }
            c1677a.a(4, "1.0.1", str, 0);
        }
    }

    /* renamed from: a */
    private void m5663a(boolean z) {
        if (!this.f4318h && !isFinishing() && !this.f4320j) {
            this.f4317g = false;
            m5668b(z);
        }
    }

    /* renamed from: b */
    private void m5668b(boolean z) {
        this.f4318h = true;
        C2621c c2621c = new C2621c(this);
        c2621c.b(C1373R.string.msg_ble_update_gps_error);
        c2621c.a(C1373R.string.label_i_know, new SpeedXGpsUpdateActivity$6(this, c2621c, z)).a();
    }

    /* renamed from: d */
    private void m5671d() {
        C1802i c1802i = new C1802i(this, C1373R.string.loading_msg, false);
        c1802i.show();
        this.f4320j = true;
        new Handler(getMainLooper()).postDelayed(new SpeedXGpsUpdateActivity$7(this, c1802i), 20000);
    }
}
