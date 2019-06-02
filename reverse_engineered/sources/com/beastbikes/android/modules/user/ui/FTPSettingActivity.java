package com.beastbikes.android.modules.user.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1794e;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.dialog.C1805k;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.train.dto.FtpDTO;
import com.beastbikes.android.modules.train.p076a.C2351d;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

@C1459b(a = 2130903137)
@C1460c(a = 2131820586)
public class FTPSettingActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131755763)
    /* renamed from: a */
    private ViewGroup f6350a;
    /* renamed from: b */
    private TextView f6351b;
    @C1458a(a = 2131755764)
    /* renamed from: c */
    private ViewGroup f6352c;
    /* renamed from: d */
    private TextView f6353d;
    /* renamed from: e */
    private int f6354e = -1;
    /* renamed from: f */
    private int f6355f = 200;
    /* renamed from: g */
    private long f6356g;
    /* renamed from: h */
    private C2351d f6357h;
    /* renamed from: i */
    private SimpleDateFormat f6358i;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f6358i = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
        if (C1849a.c()) {
            this.f6358i = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINESE);
        }
        this.f6356g = System.currentTimeMillis() / 1000;
        FtpDTO ftpDTO = (FtpDTO) getIntent().getSerializableExtra("FTP_OBJECT");
        if (ftpDTO != null) {
            this.f6354e = ftpDTO.getId();
            this.f6356g = ftpDTO.getDate();
            this.f6355f = ftpDTO.getFtp();
        }
        m7550a();
        this.f6357h = new C2351d(this);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.save_menu_item:
                m7556d();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.activity_ftp_setting_ftp_date:
                m7552b();
                return;
            case C1373R.id.activity_ftp_setting_ftp_value:
                m7554c();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m7550a() {
        this.f6350a.setOnClickListener(this);
        ((TextView) this.f6350a.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_date);
        this.f6351b = (TextView) this.f6350a.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f6351b.setText(this.f6358i.format(Calendar.getInstance().getTime()));
        this.f6352c.setOnClickListener(this);
        ((TextView) this.f6352c.findViewById(C1373R.id.speed_force_setting_item_label)).setText("FTP");
        this.f6353d = (TextView) this.f6352c.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f6353d.setText(this.f6355f + "W");
    }

    /* renamed from: b */
    private void m7552b() {
        new C1794e(this, 1, new FTPSettingActivity$1(this)).show();
    }

    /* renamed from: c */
    private void m7554c() {
        ArrayList arrayList = new ArrayList();
        for (int i = 50; i <= 500; i++) {
            arrayList.add(String.valueOf(i));
        }
        C1805k c1805k = new C1805k(this, new FTPSettingActivity$2(this));
        c1805k.show();
        c1805k.a(arrayList);
        c1805k.b(String.valueOf(this.f6355f));
    }

    /* renamed from: d */
    private void m7556d() {
        getAsyncTaskQueue().a(new FTPSettingActivity$3(this, new C1802i(this, getString(C1373R.string.empty), false)), new Object[0]);
    }
}
