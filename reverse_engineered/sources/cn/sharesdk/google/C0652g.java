package cn.sharesdk.google;

import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.google.C0648c.C0645a;
import com.alipay.sdk.packet.C0861d;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Hashon;
import java.io.File;
import java.util.HashMap;
import org.apache.http.HttpHost;
import org.apache.http.protocol.HTTP;

/* compiled from: ShareActivity */
/* renamed from: cn.sharesdk.google.g */
public class C0652g extends FakeActivity {
    /* renamed from: a */
    private Platform f1527a;
    /* renamed from: b */
    private PlatformActionListener f1528b;
    /* renamed from: c */
    private C0648c f1529c = null;
    /* renamed from: d */
    private int f1530d;
    /* renamed from: e */
    private PlatformDb f1531e;

    /* renamed from: a */
    public void m2490a(Platform platform, PlatformActionListener platformActionListener, PlatformDb platformDb) {
        this.f1527a = platform;
        this.f1528b = platformActionListener;
        this.f1531e = platformDb;
    }

    public void onCreate() {
        Bundle extras = this.activity.getIntent().getExtras();
        CharSequence string = extras.getString(DirectionsCriteria.INSTRUCTIONS_TEXT);
        String string2 = extras.getString("imageUrl");
        String string3 = extras.getString("imagePath");
        this.f1530d = extras.getInt(C0861d.f2143o);
        this.f1529c = null;
        this.f1529c = new C0645a(getContext()).m2420a(new String[]{"http://schemas.google.com/AddActivity", "http://schemas.google.com/BuyActivity"}).m2421a();
        this.f1529c.m2451a(this);
        if (this.f1530d == 1) {
            C0645a c0645a = new C0645a();
            c0645a.m2419a(HTTP.PLAIN_TEXT_TYPE);
            c0645a.m2418a(string);
            if (!string.contains(HttpHost.DEFAULT_SCHEME_NAME) && !TextUtils.isEmpty(string2) && string2.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                c0645a.m2417a(Uri.parse(string2));
            } else if (!TextUtils.isEmpty(string3)) {
                if (VERSION.SDK_INT >= 24) {
                    c0645a.m2417a(C4275R.pathToContentUri(this.activity, string3));
                } else {
                    c0645a.m2417a(Uri.fromFile(new File(string3)));
                }
            }
            startActivityForResult(c0645a.m2422b(), 3);
        } else if (this.f1530d == 0) {
            this.f1529c.m2450a(this.f1527a, this.f1528b, this.f1531e);
            this.f1529c.m2447a();
        } else if (this.f1530d == 2) {
            this.f1529c.m2454b(this);
        }
    }

    public void onDestroy() {
        if (this.f1529c == null) {
            return;
        }
        if (this.f1529c.m2456c() || this.f1529c.m2455b()) {
            this.f1529c.m2457d();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        finish();
        if (i == 3) {
            HashMap hashMap = new HashMap();
            if (!(intent == null || intent.getExtras() == null)) {
                for (String str : intent.getExtras().keySet()) {
                    hashMap.put(str, intent.getExtras().get(str));
                }
            }
            String str2 = new Hashon().fromHashMap(hashMap);
            if (i2 != -1) {
                this.f1528b.onError(this.f1527a, 9, new Throwable(str2));
            } else {
                this.f1528b.onComplete(this.f1527a, 9, hashMap);
            }
        } else if (i != 0) {
        } else {
            if (i2 == -1) {
                if (this.f1529c != null) {
                    this.f1529c.m2447a();
                }
            } else if (i2 == 0) {
                this.f1528b.onCancel(this.f1527a, 8);
            }
        }
    }
}
