package cn.jpush.android.util;

import android.content.Context;
import org.json.JSONArray;

final class ai implements Runnable {
    /* renamed from: a */
    Context f982a;
    /* renamed from: b */
    JSONArray f983b;

    public ai(Context context, JSONArray jSONArray) {
        this.f982a = context;
        this.f983b = jSONArray;
    }

    public final void run() {
        ah.m1629b(this.f982a, this.f983b);
    }
}
