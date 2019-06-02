package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.MenuItem;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.train.dto.FtpDTO;
import com.beastbikes.android.modules.train.p076a.C2351d;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import java.util.ArrayList;
import java.util.Collections;

@C1459b(a = 2130903136)
@C1460c(a = 2131820553)
public class FTPListActivity extends SessionFragmentActivity implements C2534b {
    @C1458a(a = 2131755761)
    /* renamed from: a */
    private RecyclerView f6343a;
    /* renamed from: b */
    private C2351d f6344b;
    /* renamed from: c */
    private ArrayList<FtpDTO> f6345c = new ArrayList();
    /* renamed from: d */
    private FTPListActivity$b f6346d;
    /* renamed from: e */
    private FtpDTO f6347e;
    /* renamed from: f */
    private FTPListActivity$a f6348f;
    /* renamed from: g */
    private int f6349g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f6348f = new FTPListActivity$a(this, null);
        this.f6346d = new FTPListActivity$b(this.f6345c, this);
        this.f6343a.setLayoutManager(new LinearLayoutManager(this));
        this.f6343a.setAdapter(this.f6346d);
        this.f6344b = new C2351d(this);
        m7541a();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.add_ftp_menu_item:
                startActivityForResult(new Intent(this, FTPSettingActivity.class), 35);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void finish() {
        if (this.f6349g >= 50) {
            Intent intent = getIntent();
            intent.putExtra("FTP_VALUE", this.f6349g);
            setResult(-1, intent);
        }
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i2) {
            case -1:
                if (35 == i) {
                    this.f6349g = ((FtpDTO) intent.getSerializableExtra("FTP")).getFtp();
                    m7541a();
                }
                if (36 == i) {
                    if (this.f6347e != null) {
                        this.f6345c.remove(this.f6347e);
                    }
                    this.f6347e = (FtpDTO) intent.getSerializableExtra("FTP");
                    this.f6345c.add(this.f6347e);
                    Collections.sort(this.f6345c, this.f6348f);
                    this.f6346d.notifyDataSetChanged();
                    this.f6349g = this.f6347e.getFtp();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m7545a(ViewHolder viewHolder, int i) {
        this.f6347e = (FtpDTO) this.f6345c.get(i);
        if (this.f6347e != null) {
            Intent intent = new Intent(this, FTPSettingActivity.class);
            intent.putExtra("FTP_OBJECT", this.f6347e);
            startActivityForResult(intent, 36);
        }
    }

    /* renamed from: b */
    public void m7546b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: a */
    private void m7541a() {
        getAsyncTaskQueue().a(new FTPListActivity$1(this), new Void[0]);
    }
}
