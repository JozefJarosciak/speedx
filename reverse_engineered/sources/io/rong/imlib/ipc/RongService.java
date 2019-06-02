package io.rong.imlib.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import io.rong.common.RLog;
import io.rong.imlib.LibHandlerStub;
import io.rong.imlib.common.RongLibConst;

public class RongService extends Service {
    private final String TAG = RongService.class.getSimpleName();

    public void onCreate() {
        super.onCreate();
        RLog.m19419d(this.TAG, "onCreate, pid=" + Process.myPid());
    }

    public IBinder onBind(Intent intent) {
        RLog.m19419d(this.TAG, "onBind, pid=" + Process.myPid());
        return new LibHandlerStub(this, intent.getStringExtra(RongLibConst.KEY_APPKEY), intent.getStringExtra("deviceId"));
    }

    public boolean onUnbind(Intent intent) {
        RLog.m19419d(this.TAG, "onUnbind, pid=" + Process.myPid());
        return super.onUnbind(intent);
    }

    public void onDestroy() {
        RLog.m19419d(this.TAG, "onDestroy, pid=" + Process.myPid());
        Process.killProcess(Process.myPid());
    }
}
