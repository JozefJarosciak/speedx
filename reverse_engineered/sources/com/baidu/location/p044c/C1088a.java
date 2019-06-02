package com.baidu.location.p044c;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;
import ch.qos.logback.core.joran.action.Action;
import com.baidu.location.C1102f;
import com.baidu.location.LLSInterface;
import com.baidu.location.p041a.C1038a;
import com.baidu.location.p041a.C1046c;
import com.baidu.location.p041a.C1051g;
import com.baidu.location.p041a.C1055i;
import com.baidu.location.p041a.C1059j;
import com.baidu.location.p041a.C1062m;
import com.baidu.location.p041a.C1068s;
import com.baidu.location.p041a.C1070t;
import com.baidu.location.p042d.C1091b;
import com.baidu.location.p043b.C1074b;
import com.baidu.location.p043b.C1079d;
import com.baidu.location.p043b.C1085h;
import org.apache.http.HttpStatus;

/* renamed from: com.baidu.location.c.a */
public class C1088a extends Service implements LLSInterface {
    /* renamed from: a */
    static C1087a f2652a = null;
    /* renamed from: f */
    private static long f2653f = 0;
    /* renamed from: b */
    Messenger f2654b = null;
    /* renamed from: c */
    private Looper f2655c;
    /* renamed from: d */
    private HandlerThread f2656d;
    /* renamed from: e */
    private boolean f2657e = false;

    /* renamed from: com.baidu.location.c.a$a */
    public class C1087a extends Handler {
        /* renamed from: a */
        final /* synthetic */ C1088a f2651a;

        public C1087a(C1088a c1088a, Looper looper) {
            this.f2651a = c1088a;
            super(looper);
        }

        public void handleMessage(Message message) {
            if (C1102f.isServing) {
                switch (message.what) {
                    case 11:
                        this.f2651a.m3979a(message);
                        break;
                    case 12:
                        this.f2651a.m3983b(message);
                        break;
                    case 15:
                        this.f2651a.m3987c(message);
                        break;
                    case 22:
                        C1059j.m3769c().m3783b(message);
                        break;
                    case 41:
                        C1059j.m3769c().m3786f();
                        break;
                    case HttpStatus.SC_UNAUTHORIZED /*401*/:
                        try {
                            message.getData();
                            break;
                        } catch (Exception e) {
                            break;
                        }
                    case HttpStatus.SC_METHOD_NOT_ALLOWED /*405*/:
                        byte[] byteArray = message.getData().getByteArray("errorid");
                        if (byteArray != null && byteArray.length > 0) {
                            String str = new String(byteArray);
                            break;
                        }
                    case HttpStatus.SC_NOT_ACCEPTABLE /*406*/:
                        C1051g.m3735a().m3747e();
                        break;
                }
            }
            if (message.what == 1) {
                this.f2651a.m3986c();
            }
            if (message.what == 0) {
                this.f2651a.m3982b();
            }
            super.handleMessage(message);
        }
    }

    /* renamed from: a */
    public static Handler m3978a() {
        return f2652a;
    }

    /* renamed from: a */
    private void m3979a(Message message) {
        Log.d("baidu_location_service", "baidu location service register ...");
        C1038a.m3645a().m3650a(message);
        C1062m.m3810b().mo2602c();
    }

    /* renamed from: b */
    private void m3982b() {
        C1055i.m3757a().m3760a(C1102f.getServiceContext());
        C1051g.m3735a().m3744b();
        C1079d.m3897a().m3930b();
        C1074b.m3866a().m3880b();
        C1085h.m3959a().m3965b();
        C1091b.m3989a();
        C1059j.m3769c().m3784d();
    }

    /* renamed from: b */
    private void m3983b(Message message) {
        C1038a.m3645a().m3653b(message);
    }

    /* renamed from: c */
    private void m3986c() {
        C1085h.m3959a().m3966c();
        C1079d.m3897a().m3933e();
        C1074b.m3866a().m3881c();
        C1059j.m3769c().m3785e();
        C1051g.m3735a().m3745c();
        C1070t.m3849e();
        C1038a.m3645a().m3652b();
        C1046c.m3693a().m3721b();
        try {
            if (f2652a != null) {
                f2652a.removeCallbacksAndMessages(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        f2652a = null;
        Log.d("baidu_location_service", "baidu location service has stoped ...");
        if (!this.f2657e) {
            Process.killProcess(Process.myPid());
        }
    }

    /* renamed from: c */
    private void m3987c(Message message) {
        C1038a.m3645a().m3655c(message);
    }

    public double getVersion() {
        return 7.119999885559082d;
    }

    public IBinder onBind(Intent intent) {
        Bundle extras = intent.getExtras();
        boolean z = false;
        if (extras != null) {
            C1091b.f2671g = extras.getString(Action.KEY_ATTRIBUTE);
            C1091b.f2670f = extras.getString("sign");
            this.f2657e = extras.getBoolean("kill_process");
            z = extras.getBoolean("cache_exception");
        }
        if (z) {
        }
        return this.f2654b.getBinder();
    }

    public void onCreate(Context context) {
        f2653f = System.currentTimeMillis();
        this.f2656d = C1068s.m3829a();
        this.f2655c = this.f2656d.getLooper();
        f2652a = new C1087a(this, this.f2655c);
        this.f2654b = new Messenger(f2652a);
        f2652a.sendEmptyMessage(0);
        Log.d("baidu_location_service", "baidu location service start1 ..." + Process.myPid());
    }

    public void onDestroy() {
        try {
            f2652a.sendEmptyMessage(1);
        } catch (Exception e) {
            Log.d("baidu_location_service", "baidu location service stop exception...");
            Process.killProcess(Process.myPid());
        }
        Log.d("baidu_location_service", "baidu location service stop ...");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    public boolean onUnBind(Intent intent) {
        return false;
    }
}
