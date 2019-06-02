package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.flags.ModuleDescriptor;
import com.google.android.gms.internal.zzug.zza;

public class zzuf {
    private zzug Qq = null;
    private boolean zzamr = false;

    public void initialize(Context context) {
        Throwable e;
        synchronized (this) {
            if (this.zzamr) {
                return;
            }
            try {
                this.Qq = zza.asInterface(zzsj.zza(context, zzsj.Mg, ModuleDescriptor.MODULE_ID).zziv("com.google.android.gms.flags.impl.FlagProviderImpl"));
                this.Qq.init(zze.zzae(context));
                this.zzamr = true;
            } catch (zzsj.zza e2) {
                e = e2;
                Log.w("FlagValueProvider", "Failed to initialize flags module.", e);
            } catch (RemoteException e3) {
                e = e3;
                Log.w("FlagValueProvider", "Failed to initialize flags module.", e);
            }
        }
    }

    public <T> T zzb(zzud<T> zzud) {
        synchronized (this) {
            if (this.zzamr) {
                return zzud.zza(this.Qq);
            }
            T zzjw = zzud.zzjw();
            return zzjw;
        }
    }
}
