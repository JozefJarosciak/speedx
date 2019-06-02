package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/* compiled from: PlatformServiceClient */
/* renamed from: com.facebook.internal.p */
public abstract class C3038p implements ServiceConnection {
    /* renamed from: a */
    private final Context f13601a;
    /* renamed from: b */
    private final Handler f13602b;
    /* renamed from: c */
    private C3037a f13603c;
    /* renamed from: d */
    private boolean f13604d;
    /* renamed from: e */
    private Messenger f13605e;
    /* renamed from: f */
    private int f13606f;
    /* renamed from: g */
    private int f13607g;
    /* renamed from: h */
    private final String f13608h;
    /* renamed from: i */
    private final int f13609i;

    /* compiled from: PlatformServiceClient */
    /* renamed from: com.facebook.internal.p$1 */
    class C30361 extends Handler {
        /* renamed from: a */
        final /* synthetic */ C3038p f13600a;

        C30361(C3038p c3038p) {
            this.f13600a = c3038p;
        }

        public void handleMessage(Message message) {
            this.f13600a.m14698a(message);
        }
    }

    /* compiled from: PlatformServiceClient */
    /* renamed from: com.facebook.internal.p$a */
    public interface C3037a {
        /* renamed from: a */
        void mo3706a(Bundle bundle);
    }

    /* renamed from: a */
    protected abstract void mo3713a(Bundle bundle);

    public C3038p(Context context, int i, int i2, int i3, String str) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.f13601a = context;
        this.f13606f = i;
        this.f13607g = i2;
        this.f13608h = str;
        this.f13609i = i3;
        this.f13602b = new C30361(this);
    }

    /* renamed from: a */
    public void m14699a(C3037a c3037a) {
        this.f13603c = c3037a;
    }

    /* renamed from: a */
    public boolean m14700a() {
        if (this.f13604d || C3035o.m14680b(this.f13609i) == -1) {
            return false;
        }
        Intent a = C3035o.m14668a(this.f13601a);
        if (a == null) {
            return false;
        }
        this.f13604d = true;
        this.f13601a.bindService(a, this, 1);
        return true;
    }

    /* renamed from: b */
    public void m14701b() {
        this.f13604d = false;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f13605e = new Messenger(iBinder);
        m14696c();
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.f13605e = null;
        try {
            this.f13601a.unbindService(this);
        } catch (IllegalArgumentException e) {
        }
        m14695b(null);
    }

    /* renamed from: c */
    private void m14696c() {
        Bundle bundle = new Bundle();
        bundle.putString("com.facebook.platform.extra.APPLICATION_ID", this.f13608h);
        mo3713a(bundle);
        Message obtain = Message.obtain(null, this.f13606f);
        obtain.arg1 = this.f13609i;
        obtain.setData(bundle);
        obtain.replyTo = new Messenger(this.f13602b);
        try {
            this.f13605e.send(obtain);
        } catch (RemoteException e) {
            m14695b(null);
        }
    }

    /* renamed from: a */
    protected void m14698a(Message message) {
        if (message.what == this.f13607g) {
            Bundle data = message.getData();
            if (data.getString("com.facebook.platform.status.ERROR_TYPE") != null) {
                m14695b(null);
            } else {
                m14695b(data);
            }
            this.f13601a.unbindService(this);
        }
    }

    /* renamed from: b */
    private void m14695b(Bundle bundle) {
        if (this.f13604d) {
            this.f13604d = false;
            C3037a c3037a = this.f13603c;
            if (c3037a != null) {
                c3037a.mo3706a(bundle);
            }
        }
    }
}
