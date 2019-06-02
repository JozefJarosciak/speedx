package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.zzab;

public final class zzqs<L> {
    private volatile L mListener;
    private final zza vg;

    public interface zzb<L> {
        void zzapg();

        void zzu(L l);
    }

    private final class zza extends Handler {
        final /* synthetic */ zzqs vh;

        public zza(zzqs zzqs, Looper looper) {
            this.vh = zzqs;
            super(looper);
        }

        public void handleMessage(Message message) {
            boolean z = true;
            if (message.what != 1) {
                z = false;
            }
            zzab.zzbn(z);
            this.vh.zzb((zzb) message.obj);
        }
    }

    zzqs(Looper looper, L l) {
        this.vg = new zza(this, looper);
        this.mListener = zzab.zzb((Object) l, (Object) "Listener must not be null");
    }

    public void clear() {
        this.mListener = null;
    }

    public void zza(zzb<? super L> zzb) {
        zzab.zzb((Object) zzb, (Object) "Notifier must not be null");
        this.vg.sendMessage(this.vg.obtainMessage(1, zzb));
    }

    void zzb(zzb<? super L> zzb) {
        Object obj = this.mListener;
        if (obj == null) {
            zzb.zzapg();
            return;
        }
        try {
            zzb.zzu(obj);
        } catch (RuntimeException e) {
            zzb.zzapg();
            throw e;
        }
    }
}
