package io.rong.common;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.util.SparseArray;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;

public abstract class WakefulRongReceiver extends BroadcastReceiver {
    private static final String EXTRA_WAKE_LOCK_ID = "android.support.content.wakelockid";
    private static final SparseArray<WakeLock> mActiveWakeLocks = new SparseArray();
    private static int mNextId = 1;

    public static ComponentName startWakefulService(Context context, Intent intent) {
        synchronized (mActiveWakeLocks) {
            int i = mNextId;
            mNextId++;
            if (mNextId <= 0) {
                mNextId = 1;
            }
            intent.putExtra(EXTRA_WAKE_LOCK_ID, i);
            try {
                ComponentName startService = context.startService(intent);
            } catch (SecurityException e) {
                e.printStackTrace();
                startService = null;
            }
            if (startService == null) {
                return null;
            }
            WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "wake:" + startService.flattenToShortString());
            newWakeLock.setReferenceCounted(false);
            newWakeLock.acquire(ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD);
            mActiveWakeLocks.put(i, newWakeLock);
            Log.i("WakefulRongReceiver", "require wakelock. id:" + i);
            return startService;
        }
    }

    public static boolean completeWakefulIntent(Intent intent) {
        int intExtra = intent.getIntExtra(EXTRA_WAKE_LOCK_ID, 0);
        Log.w("WakefulRongReceiver", "completeWakefulIntent id #" + intExtra);
        if (intExtra == 0) {
            return false;
        }
        synchronized (mActiveWakeLocks) {
            WakeLock wakeLock = (WakeLock) mActiveWakeLocks.get(intExtra);
            if (wakeLock != null) {
                wakeLock.release();
                mActiveWakeLocks.remove(intExtra);
                return true;
            }
            Log.w("WakefulRongReceiver", "No active wake lock id #" + intExtra);
            return true;
        }
    }
}