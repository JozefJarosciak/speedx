package com.twitter.sdk.android.tweetcomposer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import io.fabric.sdk.android.services.network.C4929h;
import java.net.URL;
import org.apache.http.protocol.HTTP;

/* compiled from: TweetComposer */
public class m$a {
    /* renamed from: a */
    private final Context f16489a;
    /* renamed from: b */
    private String f16490b;
    /* renamed from: c */
    private URL f16491c;
    /* renamed from: d */
    private Uri f16492d;

    public m$a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        this.f16489a = context;
    }

    /* renamed from: a */
    public m$a m18485a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("text must not be null.");
        } else if (this.f16490b != null) {
            throw new IllegalStateException("text already set.");
        } else {
            this.f16490b = str;
            return this;
        }
    }

    /* renamed from: a */
    public m$a m18486a(URL url) {
        if (url == null) {
            throw new IllegalArgumentException("url must not be null.");
        } else if (this.f16491c != null) {
            throw new IllegalStateException("url already set.");
        } else {
            this.f16491c = url;
            return this;
        }
    }

    /* renamed from: a */
    public m$a m18484a(Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("imageUri must not be null.");
        } else if (this.f16492d != null) {
            throw new IllegalStateException("imageUri already set.");
        } else {
            this.f16492d = uri;
            return this;
        }
    }

    /* renamed from: a */
    public Intent m18483a() {
        Intent b = m18487b();
        if (b == null) {
            return m18488c();
        }
        return b;
    }

    /* renamed from: b */
    Intent m18487b() {
        Intent intent = new Intent("android.intent.action.SEND");
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(this.f16490b)) {
            stringBuilder.append(this.f16490b);
        }
        if (this.f16491c != null) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(this.f16491c.toString());
        }
        intent.putExtra("android.intent.extra.TEXT", stringBuilder.toString());
        intent.setType(HTTP.PLAIN_TEXT_TYPE);
        if (this.f16492d != null) {
            intent.putExtra("android.intent.extra.STREAM", this.f16492d);
            intent.setType("image/jpeg");
        }
        for (ResolveInfo resolveInfo : this.f16489a.getPackageManager().queryIntentActivities(intent, 65536)) {
            if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                return intent;
            }
        }
        return null;
    }

    /* renamed from: c */
    Intent m18488c() {
        String url = this.f16491c == null ? "" : this.f16491c.toString();
        return new Intent("android.intent.action.VIEW", Uri.parse(String.format("https://twitter.com/intent/tweet?text=%s&url=%s", new Object[]{C4929h.m19366a(this.f16490b), C4929h.m19366a(url)})));
    }

    /* renamed from: d */
    public void m18489d() {
        this.f16489a.startActivity(m18483a());
    }
}
