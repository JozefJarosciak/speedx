package com.beastbikes.android.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: ScreenObserver */
/* renamed from: com.beastbikes.android.utils.t */
public class C2577t {
    /* renamed from: a */
    private static final Logger f12054a = LoggerFactory.getLogger(C2577t.class);
    /* renamed from: b */
    private static String f12055b = "ScreenObserver";
    /* renamed from: c */
    private Context f12056c;
    /* renamed from: d */
    private C2576a f12057d = new C2576a();
    /* renamed from: e */
    private C1912b f12058e;
    /* renamed from: f */
    private Method f12059f;

    /* compiled from: ScreenObserver */
    /* renamed from: com.beastbikes.android.utils.t$b */
    public interface C1912b {
        /* renamed from: e */
        void mo3278e();

        /* renamed from: f */
        void mo3279f();
    }

    /* compiled from: ScreenObserver */
    /* renamed from: com.beastbikes.android.utils.t$a */
    private class C2576a extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ C2577t f12052a;
        /* renamed from: b */
        private String f12053b;

        private C2576a(C2577t c2577t) {
            this.f12052a = c2577t;
            this.f12053b = null;
        }

        public void onReceive(Context context, Intent intent) {
            this.f12053b = intent.getAction();
            if ("android.intent.action.SCREEN_ON".equals(this.f12053b)) {
                this.f12052a.f12058e.mo3278e();
            }
            if ("android.intent.action.SCREEN_OFF".equals(this.f12053b)) {
                this.f12052a.f12058e.mo3279f();
            }
        }
    }

    public C2577t(Context context) {
        this.f12056c = context;
        try {
            this.f12059f = PowerManager.class.getMethod("isScreenOn", new Class[0]);
        } catch (NoSuchMethodException e) {
            f12054a.error(f12055b + ": API < 7," + e);
        }
    }

    /* renamed from: a */
    public void m12901a(C1912b c1912b) {
        this.f12058e = c1912b;
        m12899c();
        m12898b();
    }

    /* renamed from: b */
    private void m12898b() {
        if (m12897a((PowerManager) this.f12056c.getSystemService("power"))) {
            if (this.f12058e != null) {
                this.f12058e.mo3278e();
            }
        } else if (this.f12058e != null) {
            this.f12058e.mo3279f();
        }
    }

    /* renamed from: a */
    public void m12900a() {
        this.f12056c.unregisterReceiver(this.f12057d);
    }

    /* renamed from: c */
    private void m12899c() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.f12056c.registerReceiver(this.f12057d, intentFilter);
    }

    /* renamed from: a */
    private boolean m12897a(PowerManager powerManager) {
        try {
            return ((Boolean) this.f12059f.invoke(powerManager, new Object[0])).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }
}
