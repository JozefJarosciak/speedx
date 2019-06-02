package com.journeyapps.barcodescanner;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.android.C4087R;
import com.journeyapps.barcodescanner.camera.C4139b;
import com.journeyapps.barcodescanner.camera.C4149j;

/* compiled from: DecoderThread */
/* renamed from: com.journeyapps.barcodescanner.h */
public class C4164h {
    /* renamed from: a */
    private static final String f14809a = C4164h.class.getSimpleName();
    /* renamed from: b */
    private C4139b f14810b;
    /* renamed from: c */
    private HandlerThread f14811c;
    /* renamed from: d */
    private Handler f14812d;
    /* renamed from: e */
    private C4159e f14813e;
    /* renamed from: f */
    private Handler f14814f;
    /* renamed from: g */
    private Rect f14815g;
    /* renamed from: h */
    private boolean f14816h = false;
    /* renamed from: i */
    private final Object f14817i = new Object();
    /* renamed from: j */
    private final Callback f14818j = new C41621(this);
    /* renamed from: k */
    private final C4149j f14819k = new C41632(this);

    /* compiled from: DecoderThread */
    /* renamed from: com.journeyapps.barcodescanner.h$1 */
    class C41621 implements Callback {
        /* renamed from: a */
        final /* synthetic */ C4164h f14807a;

        C41621(C4164h c4164h) {
            this.f14807a = c4164h;
        }

        public boolean handleMessage(Message message) {
            if (message.what == C4087R.id.zxing_decode) {
                this.f14807a.m16677b((C4169m) message.obj);
            }
            return true;
        }
    }

    /* compiled from: DecoderThread */
    /* renamed from: com.journeyapps.barcodescanner.h$2 */
    class C41632 implements C4149j {
        /* renamed from: a */
        final /* synthetic */ C4164h f14808a;

        C41632(C4164h c4164h) {
            this.f14808a = c4164h;
        }

        /* renamed from: a */
        public void mo5933a(C4169m c4169m) {
            synchronized (this.f14808a.f14817i) {
                if (this.f14808a.f14816h) {
                    this.f14808a.f14812d.obtainMessage(C4087R.id.zxing_decode, c4169m).sendToTarget();
                }
            }
        }
    }

    public C4164h(C4139b c4139b, C4159e c4159e, Handler handler) {
        C4170n.m16706a();
        this.f14810b = c4139b;
        this.f14813e = c4159e;
        this.f14814f = handler;
    }

    /* renamed from: a */
    public void m16684a(C4159e c4159e) {
        this.f14813e = c4159e;
    }

    /* renamed from: a */
    public void m16683a(Rect rect) {
        this.f14815g = rect;
    }

    /* renamed from: a */
    public void m16682a() {
        C4170n.m16706a();
        this.f14811c = new HandlerThread(f14809a);
        this.f14811c.start();
        this.f14812d = new Handler(this.f14811c.getLooper(), this.f14818j);
        this.f14816h = true;
        m16680c();
    }

    /* renamed from: b */
    public void m16685b() {
        C4170n.m16706a();
        synchronized (this.f14817i) {
            this.f14816h = false;
            this.f14812d.removeCallbacksAndMessages(null);
            this.f14811c.quit();
        }
    }

    /* renamed from: c */
    private void m16680c() {
        if (this.f14810b.m16593f()) {
            this.f14810b.m16587a(this.f14819k);
        }
    }

    /* renamed from: a */
    protected LuminanceSource m16681a(C4169m c4169m) {
        if (this.f14815g == null) {
            return null;
        }
        return c4169m.m16705b();
    }

    /* renamed from: b */
    private void m16677b(C4169m c4169m) {
        long currentTimeMillis = System.currentTimeMillis();
        Result result = null;
        c4169m.m16703a(this.f14815g);
        LuminanceSource a = m16681a(c4169m);
        if (a != null) {
            result = this.f14813e.m16669a(a);
        }
        if (result != null) {
            Log.d(f14809a, "Found barcode in " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            if (this.f14814f != null) {
                Message obtain = Message.obtain(this.f14814f, C4087R.id.zxing_decode_succeeded, new C4121b(result, c4169m));
                obtain.setData(new Bundle());
                obtain.sendToTarget();
            }
        } else if (this.f14814f != null) {
            Message.obtain(this.f14814f, C4087R.id.zxing_decode_failed).sendToTarget();
        }
        if (this.f14814f != null) {
            Message.obtain(this.f14814f, C4087R.id.zxing_possible_result_points, this.f14813e.m16670a()).sendToTarget();
        }
        m16680c();
    }
}
