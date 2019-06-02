package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import com.twitter.sdk.android.core.C4585h;
import com.twitter.sdk.android.core.models.C1513g;
import io.fabric.sdk.android.C1520c;
import org.apache.http.protocol.HTTP;

/* compiled from: ShareTweetAction */
/* renamed from: com.twitter.sdk.android.tweetui.e */
class C4699e implements OnClickListener {
    /* renamed from: a */
    final C1513g f16521a;
    /* renamed from: b */
    final C1518i f16522b;
    /* renamed from: c */
    final C4703g f16523c;

    C4699e(C1513g c1513g, C1518i c1518i) {
        this(c1513g, c1518i, new C4704h(c1518i));
    }

    C4699e(C1513g c1513g, C1518i c1518i, C4703g c4703g) {
        this.f16521a = c1513g;
        this.f16522b = c1518i;
        this.f16523c = c4703g;
    }

    public void onClick(View view) {
        onClick(view.getContext(), view.getResources());
    }

    /* renamed from: a */
    void m18508a() {
        this.f16523c.mo6163a(this.f16521a);
    }

    void onClick(Context context, Resources resources) {
        if (this.f16521a != null && this.f16521a.f7119z != null) {
            m18508a();
            m18509a(Intent.createChooser(m18506a(m18510b(resources), m18507a(resources)), resources.getString(C4689R.string.tw__share_tweet)), context);
        }
    }

    /* renamed from: a */
    String m18507a(Resources resources) {
        return resources.getString(C4689R.string.tw__share_content_format, new Object[]{this.f16521a.f7119z.screenName, Long.valueOf(this.f16521a.f7102i)});
    }

    /* renamed from: b */
    String m18510b(Resources resources) {
        return resources.getString(C4689R.string.tw__share_subject_format, new Object[]{this.f16521a.f7119z.name, this.f16521a.f7119z.screenName});
    }

    /* renamed from: a */
    void m18509a(Intent intent, Context context) {
        if (!C4585h.m18157b(context, intent)) {
            C1520c.h().mo6221d("TweetUi", "Activity cannot be found to handle share intent");
        }
    }

    /* renamed from: a */
    Intent m18506a(String str, String str2) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.putExtra("android.intent.extra.TEXT", str2);
        intent.setType(HTTP.PLAIN_TEXT_TYPE);
        return intent;
    }
}
