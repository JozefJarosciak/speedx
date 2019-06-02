package cn.jpush.android.helpers;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.C0506s;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.helpers.g */
final class C0457g extends Thread {
    /* renamed from: a */
    final /* synthetic */ String f778a;
    /* renamed from: b */
    final /* synthetic */ Context f779b;
    /* renamed from: c */
    final /* synthetic */ String f780c;

    C0457g(String str, Context context, String str2) {
        this.f778a = str;
        this.f779b = context;
        this.f780c = str2;
    }

    public final void run() {
        Object obj = null;
        String str = null;
        int i = 0;
        while (i < 4) {
            i++;
            str = C0506s.m1797a(this.f778a, 5, 8000);
            if (!C0506s.m1800a(str)) {
                obj = 1;
                break;
            }
        }
        if (obj == null || TextUtils.isEmpty(str)) {
            C0459i.m1416a(this.f780c, 1021, C0490b.m1685b(this.f779b, this.f778a), this.f779b);
            C0459i.m1415a(this.f780c, 996, this.f779b);
            ac.m1581b();
            return;
        }
        C0456f.m1401a(this.f779b, str);
    }
}
