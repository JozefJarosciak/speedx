package com.beastbikes.android.modules.cycling;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import java.util.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncService extends Service {
    /* renamed from: a */
    public static boolean f4475a;
    /* renamed from: b */
    private static final Logger f4476b = LoggerFactory.getLogger(SyncService.class);
    /* renamed from: c */
    private final Timer f4477c = new Timer("SampleSynchronizer", true);
    /* renamed from: d */
    private final SyncService$b f4478d = new SyncService$b(this, null);
    /* renamed from: e */
    private final BroadcastReceiver f4479e = new SyncService$a(this, null);
    /* renamed from: f */
    private C1398a f4480f;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        f4476b.info("SyncService onCreate");
        this.f4480f = new C1398a((Context) this);
        this.f4477c.scheduleAtFixedRate(this.f4478d, 0, 2500000);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        registerReceiver(this.f4479e, intentFilter);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (i2 > 0) {
            new Thread(this.f4478d).start();
        }
        f4476b.info("SyncService onStartCommand");
        return super.onStartCommand(intent, i, i2);
    }

    public void onDestroy() {
        this.f4477c.cancel();
        unregisterReceiver(this.f4479e);
        super.onDestroy();
        startService(new Intent(this, getClass()));
    }
}
