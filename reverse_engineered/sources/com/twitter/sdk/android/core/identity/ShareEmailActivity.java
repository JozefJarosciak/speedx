package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.TextView;
import com.twitter.sdk.android.core.C1514q;
import com.twitter.sdk.android.core.C4573R;
import com.twitter.sdk.android.core.C4655n;
import io.fabric.sdk.android.C1520c;

public class ShareEmailActivity extends Activity {
    /* renamed from: a */
    C4597d f16226a;
    /* renamed from: b */
    private C1514q f16227b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4573R.layout.tw__activity_share_email);
        try {
            Intent intent = getIntent();
            ResultReceiver a = m18177a(intent);
            this.f16227b = m18178b(intent);
            this.f16226a = new C4597d(new ShareEmailClient(this.f16227b), a);
            m18179a(this, (TextView) findViewById(C4573R.id.tw__share_email_desc));
        } catch (Throwable e) {
            C1520c.h().mo6222d("Twitter", "Failed to create ShareEmailActivity.", e);
            finish();
        }
    }

    /* renamed from: a */
    private ResultReceiver m18177a(Intent intent) {
        ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra("result_receiver");
        if (resultReceiver != null) {
            return resultReceiver;
        }
        throw new IllegalArgumentException("ResultReceiver must not be null. This activity should not be started directly.");
    }

    /* renamed from: b */
    private C1514q m18178b(Intent intent) {
        long longExtra = intent.getLongExtra("session_id", -1);
        C1514q c1514q = (C1514q) C4655n.m18381a().m18393h().mo6129a(longExtra);
        if (c1514q != null) {
            return c1514q;
        }
        throw new IllegalArgumentException("No TwitterSession for id:" + longExtra);
    }

    /* renamed from: a */
    void m18179a(Context context, TextView textView) {
        PackageManager packageManager = context.getPackageManager();
        textView.setText(getResources().getString(C4573R.string.tw__share_email_desc, new Object[]{packageManager.getApplicationLabel(context.getApplicationInfo()), this.f16227b.a()}));
    }

    public void onClickNotNow(View view) {
        this.f16226a.m18215c();
        finish();
    }

    public void onClickAllow(View view) {
        this.f16226a.m18210a();
        finish();
    }

    public void onBackPressed() {
        this.f16226a.m18215c();
        super.onBackPressed();
    }
}
