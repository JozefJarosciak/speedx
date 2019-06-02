package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class zzpt$zza<R extends Result> extends Handler {
    public zzpt$zza() {
        this(Looper.getMainLooper());
    }

    public zzpt$zza(Looper looper) {
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                Pair pair = (Pair) message.obj;
                zzb((ResultCallback) pair.first, (Result) pair.second);
                return;
            case 2:
                ((zzpt) message.obj).zzaa(Status.sj);
                return;
            default:
                Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, new Exception());
                return;
        }
    }

    public void zza(ResultCallback<? super R> resultCallback, R r) {
        sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
    }

    public void zza(zzpt<R> zzpt, long j) {
        sendMessageDelayed(obtainMessage(2, zzpt), j);
    }

    public void zzaow() {
        removeMessages(2);
    }

    protected void zzb(ResultCallback<? super R> resultCallback, R r) {
        try {
            resultCallback.onResult(r);
        } catch (RuntimeException e) {
            zzpt.zze(r);
            throw e;
        }
    }
}
