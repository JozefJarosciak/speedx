package com.beastbikes.android.modules.preferences.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.p055a.C1536a;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.preferences.ui.p140a.C2314a;
import com.beastbikes.android.modules.strava.ui.StravaAuthWebActivity;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.utils.p080a.C2549c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903677)
public class ServiceManagerActivity extends SessionFragmentActivity implements OnClickListener {
    /* renamed from: a */
    private static final Logger f5903a = LoggerFactory.getLogger("ServiceManagerActivity");
    @C1458a(a = 2131757557)
    /* renamed from: b */
    private TextView f5904b;
    /* renamed from: c */
    private C1802i f5905c;
    /* renamed from: d */
    private C1536a f5906d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5904b.setOnClickListener(this);
        this.f5906d = new C1536a(this);
        String a = C2549c.a().a(this, "com.beastbikes.starva_auth_key", null);
        Object a2 = C2549c.a().a(this, "com.beastbikes.starva_token", null);
        if (TextUtils.isEmpty(a2)) {
            m7150b();
        }
        m7152c(a, a2);
    }

    public void onClick(View view) {
        if (view == this.f5904b) {
            f5903a.info("CLient id: 13697");
            if (view.getTag(C1373R.string.str_tag_key_authToken) == null) {
                StringBuilder stringBuilder = new StringBuilder("https://www.strava.com/oauth/authorize?client_id=13697&response_type=code&redirect_uri=speedx://strava_callback_url_for_speedx&scope=write&state=mystate&approval_prompt=force");
                f5903a.info("strava url sb: " + stringBuilder.toString());
                Uri parse = Uri.parse(stringBuilder.toString());
                Intent intent = new Intent(this, StravaAuthWebActivity.class);
                intent.putExtra("clear_cookie", true);
                intent.setData(parse);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setPackage(getPackageName());
                startActivityForResult(intent, 1);
                return;
            }
            new C2314a(this, new ServiceManagerActivity$1(this)).show();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && i == 1) {
            String str = "";
            str = intent.getStringExtra("authkey");
            String stringExtra = intent.getStringExtra("token");
            if (i2 == -1) {
                m7153a(str, stringExtra);
            } else {
                Toasts.show(this, intent.getStringExtra("error_msg"));
            }
        }
    }

    /* renamed from: c */
    private void m7152c(String str, String str2) {
        this.f5904b.setTag(C1373R.string.str_tag_key_authkey, str);
        this.f5904b.setTag(C1373R.string.str_tag_key_authToken, str2);
        if (TextUtils.isEmpty(str2)) {
            this.f5904b.setText(C1373R.string.service_manager_contact);
        } else {
            this.f5904b.setText(C1373R.string.service_manager_discontact);
        }
    }

    /* renamed from: a */
    public void m7153a(String str, String str2) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str2)) {
            getAsyncTaskQueue().a(new ServiceManagerActivity$2(this, str, str2), new Void[0]);
        }
    }

    /* renamed from: b */
    public void m7154b(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            getAsyncTaskQueue().a(new ServiceManagerActivity$3(this, str, str2), new Void[0]);
        }
    }

    /* renamed from: b */
    private void m7150b() {
        getAsyncTaskQueue().a(new ServiceManagerActivity$4(this), new Void[0]);
    }

    /* renamed from: a */
    private AccountDTO m7146a(List<AccountDTO> list) {
        if (list == null) {
            return null;
        }
        for (AccountDTO accountDTO : list) {
            if (accountDTO.getAuthType() == 256) {
                return accountDTO;
            }
        }
        return null;
    }
}
