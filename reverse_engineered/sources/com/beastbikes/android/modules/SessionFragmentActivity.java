package com.beastbikes.android.modules;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.android.utils.p163b.C2552b;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;
import java.util.ArrayList;
import rx.C5720f;

public class SessionFragmentActivity extends BaseFragmentActivity {
    /* renamed from: a */
    private C5720f f4046a;
    /* renamed from: b */
    private ArrayList<Integer> f4047b;
    /* renamed from: c */
    private BroadcastReceiver f4048c = new SessionFragmentActivity$2(this);

    /* renamed from: p */
    protected String m5331p() {
        Intent intent = getIntent();
        if (intent == null) {
            return null;
        }
        CharSequence stringExtra = intent.getStringExtra("user_id");
        if (!TextUtils.isEmpty(stringExtra)) {
            return stringExtra;
        }
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            return currentUser.getObjectId();
        }
        return null;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("s605.dialog.type.wifi.password");
        intentFilter.addAction("s605.dialog.type.wifi.status");
        intentFilter.addAction("s605.dialog.update.success");
        registerReceiver(this.f4048c, intentFilter);
    }

    protected void onDestroy() {
        m5332q();
        super.onDestroy();
        unregisterReceiver(this.f4048c);
    }

    /* renamed from: a */
    protected void m5326a(C2552b c2552b) {
        C2574s.a().a(c2552b);
    }

    /* renamed from: c */
    protected void m5329c(int i, Object obj) {
        m5326a(new C2552b(i, obj));
    }

    /* renamed from: q */
    protected boolean m5332q() {
        if (this.f4046a == null || this.f4046a.isUnsubscribed()) {
            return false;
        }
        this.f4046a.unsubscribe();
        return true;
    }

    /* renamed from: a */
    protected void m5327a(int... iArr) {
        for (int e : iArr) {
            m5330e(e);
        }
    }

    /* renamed from: e */
    protected void m5330e(int i) {
        if (this.f4047b == null) {
            this.f4047b = new ArrayList();
        }
        if (!this.f4047b.contains(Integer.valueOf(i))) {
            this.f4047b.add(Integer.valueOf(i));
        }
        if (this.f4046a == null) {
            this.f4046a = C2574s.a().a(C2552b.class).a(new SessionFragmentActivity$1(this));
        }
    }

    /* renamed from: a */
    protected void m5325a(Intent intent, int i) {
        m5330e(i);
        startActivity(intent);
    }

    /* renamed from: b */
    protected void mo2739b(int i, Object obj) {
    }
}
