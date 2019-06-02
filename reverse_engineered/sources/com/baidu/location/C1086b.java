package com.baidu.location;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

/* renamed from: com.baidu.location.b */
class C1086b implements ServiceConnection {
    /* renamed from: a */
    final /* synthetic */ LocationClient f2650a;

    C1086b(LocationClient locationClient) {
        this.f2650a = locationClient;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f2650a.mServer = new Messenger(iBinder);
        if (this.f2650a.mServer != null) {
            this.f2650a.mIsStarted = true;
            Log.d("baidu_location_client", "baidu location connected ...");
            if (this.f2650a.isStop) {
                this.f2650a.mHandler.obtainMessage(2).sendToTarget();
                return;
            }
            try {
                Message obtain = Message.obtain(null, 11);
                obtain.replyTo = this.f2650a.mMessenger;
                obtain.setData(this.f2650a.getOptionBundle());
                this.f2650a.mServer.send(obtain);
                this.f2650a.mIsStarted = true;
                if (this.f2650a.mOption != null) {
                    if (this.f2650a.firstConnected.booleanValue()) {
                        this.f2650a.mHandler.obtainMessage(4).sendToTarget();
                    } else {
                        this.f2650a.mHandler.obtainMessage(4).sendToTarget();
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.f2650a.mServer = null;
        this.f2650a.mIsStarted = false;
    }
}
