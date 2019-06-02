package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.twitter.sdk.android.core.C4573R;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.identity.C4593a.C4588a;
import com.twitter.sdk.android.core.internal.C4611f;
import com.twitter.sdk.android.core.internal.oauth.OAuth1aService;

public class OAuthActivity extends Activity implements C4588a {
    /* renamed from: a */
    C4593a f16223a;
    /* renamed from: b */
    private ProgressBar f16224b;
    /* renamed from: c */
    private WebView f16225c;

    protected void onCreate(Bundle bundle) {
        boolean z;
        super.onCreate(bundle);
        setContentView(C4573R.layout.tw__activity_oauth);
        this.f16224b = (ProgressBar) findViewById(C4573R.id.tw__spinner);
        this.f16225c = (WebView) findViewById(C4573R.id.tw__web_view);
        if (bundle != null) {
            z = bundle.getBoolean("progress", false);
        } else {
            z = true;
        }
        this.f16224b.setVisibility(z ? 0 : 8);
        C4655n a = C4655n.m18381a();
        this.f16223a = new C4593a(this.f16224b, this.f16225c, (TwitterAuthConfig) getIntent().getParcelableExtra("auth_config"), new OAuth1aService(a, a.m18390e(), new C4611f()), this);
        this.f16223a.m18200a();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        if (this.f16224b.getVisibility() == 0) {
            bundle.putBoolean("progress", true);
        }
        super.onSaveInstanceState(bundle);
    }

    public void onBackPressed() {
        this.f16223a.m18201a(0, new TwitterAuthException("Authorization failed, request was canceled."));
    }

    /* renamed from: a */
    public void mo6134a(int i, Intent intent) {
        setResult(i, intent);
        finish();
    }
}
