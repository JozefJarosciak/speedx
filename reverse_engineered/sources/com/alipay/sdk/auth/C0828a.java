package com.alipay.sdk.auth;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

/* renamed from: com.alipay.sdk.auth.a */
final class C0828a implements DownloadListener {
    /* renamed from: a */
    final /* synthetic */ AuthActivity f2002a;

    C0828a(AuthActivity authActivity) {
        this.f2002a = authActivity;
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            this.f2002a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Throwable th) {
        }
    }
}
