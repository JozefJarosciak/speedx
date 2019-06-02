package com.beastbikes.android.embapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.ValueCallback;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.p109b.C1871a;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.ui.MedalInfoActivity;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.p168e.C2655d;
import com.beastbikes.android.widget.p168e.p169a.C2642c;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.android.p103h.C1809c;
import com.beastbikes.framework.ui.android.WebActivity;
import com.pingplusplus.android.Pingpp;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1460c(a = 2131820590)
public class BrowserActivity extends WebActivity implements C1371a {
    /* renamed from: k */
    private static final Logger f4405k = LoggerFactory.getLogger(BrowserActivity.class);
    /* renamed from: l */
    private static final Map<String, C1809c> f4406l = new HashMap();
    /* renamed from: a */
    public ValueCallback<Uri> f4407a;
    /* renamed from: b */
    public ValueCallback<Uri[]> f4408b;
    /* renamed from: c */
    protected boolean f4409c = false;
    /* renamed from: d */
    protected String f4410d;
    /* renamed from: e */
    protected String f4411e;
    /* renamed from: f */
    protected String f4412f;
    /* renamed from: g */
    protected String f4413g;
    /* renamed from: h */
    protected C1871a f4414h;
    /* renamed from: i */
    protected C2655d f4415i;
    /* renamed from: j */
    protected C2642c f4416j;
    /* renamed from: m */
    private C1802i f4417m;

    static {
        f4406l.put("http://hybrid.beastbikes.com/1.0/hybrid.js", new C1812c());
        f4406l.put("http://hybrid.beastbikes.com/1.0/agent", new C1810a());
        f4406l.put("http://hybrid.beastbikes.com/1.0/device", new C1811b());
        f4406l.put("http://hybrid.beastbikes.com/1.0/auth", new C1816f());
        f4406l.put("http://hybrid.beastbikes.com/1.0/connectivity/network", new C1814d());
    }

    protected void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null) {
            this.f4409c = intent.getBooleanExtra("menu_status", false);
            if ("hybrid.speedx.com".equals(intent.getData().getHost())) {
                ActionBar supportActionBar = getSupportActionBar();
                if (supportActionBar != null) {
                    supportActionBar.hide();
                }
                this.canGoBack = true;
            }
        }
        this.f4417m = new C1802i(this, getString(C1373R.string.str_loading), true);
        super.setWebViewClient(new BrowserActivity$1(this, this));
        super.setWebChromeClient(new BrowserActivity$2(this, this));
        super.onCreate(bundle);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            if (this.f4407a != null) {
                Object data = (intent == null || i2 != -1) ? null : intent.getData();
                this.f4407a.onReceiveValue(data);
                this.f4407a = null;
            }
        } else if (i == 2) {
            if (this.f4408b != null) {
                Uri data2 = (intent == null || i2 != -1) ? null : intent.getData();
                if (data2 != null) {
                    this.f4408b.onReceiveValue(new Uri[]{data2});
                } else {
                    this.f4408b.onReceiveValue(new Uri[0]);
                }
                this.f4408b = null;
            }
        } else if (i == Pingpp.REQUEST_CODE_PAYMENT) {
            m5772a(intent.getExtras().getString("pay_result"), intent.getExtras().getString("error_msg"), intent.getExtras().getString("extra_msg"));
        }
    }

    public void finish() {
        if (this.f4417m != null && this.f4417m.isShowing()) {
            this.f4417m.dismiss();
        }
        super.finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return this.f4409c ? super.onCreateOptionsMenu(menu) : this.f4409c;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.task_info_browser_menu_share:
                C2580w.a(this, "", "click_cycling_event_event_share");
                if (getBrowser() != null) {
                    getBrowser().loadUrl("javascript:getShareInfo('android')");
                    break;
                }
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* renamed from: a */
    private void m5767a(ValueCallback<Uri[]> valueCallback) {
        this.f4408b = valueCallback;
        Parcelable intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        Intent intent2 = new Intent("android.intent.action.CHOOSER");
        intent2.putExtra("android.intent.extra.INTENT", intent);
        intent2.putExtra("android.intent.extra.TITLE", "Image Chooser");
        startActivityForResult(intent2, 2);
    }

    /* renamed from: a */
    public void m5771a() {
        if (this.f4415i == null) {
            this.f4416j = new C2642c(this.f4413g, this.f4410d, this.f4412f, this.f4411e, this.f4412f, this.f4412f);
            this.f4415i = new C2655d(this, this.f4416j, getTitle().toString());
        }
        this.f4415i.showAtLocation(getBrowser(), 81, 0, 0);
    }

    protected void receiveAwardSuccess(int i) {
        super.receiveAwardSuccess(i);
        Intent intent = getIntent();
        intent.putExtra("award_status", i);
        setResult(-1, intent);
    }

    public int onJsMethodGetCountryCode(int[] iArr) {
        iArr[0] = C1849a.a(this);
        return iArr[0];
    }

    public void onJsMethodSpeedxBridge(String str) {
        super.onJsMethodSpeedxBridge(str);
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.f4410d = jSONObject.optString(WebActivity.EXTRA_TITLE);
                this.f4412f = jSONObject.optString("url");
                this.f4411e = jSONObject.optString("desc");
                this.f4413g = jSONObject.optString("shareLogo");
                m5771a();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void onJsMethodLightMedal(int i) {
        super.onJsMethodLightMedal(i);
        Intent intent = new Intent(this, MedalInfoActivity.class);
        intent.putExtra("medal_id", i);
        startActivity(intent);
        new C2389c(this).b();
    }

    public void onJsMethodFinishClubTransfer() {
        super.onJsMethodFinishClubTransfer();
        startActivity(new Intent(this, HomeActivity.class));
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            SharedPreferences sharedPreferences = getSharedPreferences(currentUser.getObjectId(), 0);
            Editor edit = sharedPreferences.edit();
            edit.putLong("beast.club.notify.transfer.master", System.currentTimeMillis());
            edit.putInt("beast.club.feed.dot.total.count", sharedPreferences.getInt("beast.club.feed.dot.total.count", 0) - 1);
            edit.apply();
        }
        finish();
    }

    public void onJsMethodCreatePayment(String str) {
        super.onJsMethodCreatePayment(str);
        if (this.f4414h == null) {
            this.f4414h = new C1871a(this);
        }
        this.f4414h.a(str);
    }

    /* renamed from: a */
    public void m5772a(String str, String str2, String str3) {
        if (getBrowser() != null) {
            getBrowser().loadUrl("javascript:createPaymentResponse('" + str + "','" + str2 + "','" + str3 + "')");
        }
    }
}
