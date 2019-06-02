package com.google.android.gms.internal;

import android.os.RemoteException;

public abstract class zzud<T> {
    private final int zzaxm;
    private final String zzaxn;
    private final T zzaxo;

    public static class zza extends zzud<Boolean> {
        public zza(int i, String str, Boolean bool) {
            super(i, str, bool);
        }

        public /* synthetic */ Object zza(zzug zzug) {
            return zzb(zzug);
        }

        public Boolean zzb(zzug zzug) {
            try {
                return Boolean.valueOf(zzug.getBooleanFlagValue(getKey(), ((Boolean) zzjw()).booleanValue(), getSource()));
            } catch (RemoteException e) {
                return (Boolean) zzjw();
            }
        }
    }

    public static class zzb extends zzud<Integer> {
        public zzb(int i, String str, Integer num) {
            super(i, str, num);
        }

        public /* synthetic */ Object zza(zzug zzug) {
            return zzc(zzug);
        }

        public Integer zzc(zzug zzug) {
            try {
                return Integer.valueOf(zzug.getIntFlagValue(getKey(), ((Integer) zzjw()).intValue(), getSource()));
            } catch (RemoteException e) {
                return (Integer) zzjw();
            }
        }
    }

    public static class zzc extends zzud<Long> {
        public zzc(int i, String str, Long l) {
            super(i, str, l);
        }

        public /* synthetic */ Object zza(zzug zzug) {
            return zzd(zzug);
        }

        public Long zzd(zzug zzug) {
            try {
                return Long.valueOf(zzug.getLongFlagValue(getKey(), ((Long) zzjw()).longValue(), getSource()));
            } catch (RemoteException e) {
                return (Long) zzjw();
            }
        }
    }

    public static class zzd extends zzud<String> {
        public zzd(int i, String str, String str2) {
            super(i, str, str2);
        }

        public /* synthetic */ Object zza(zzug zzug) {
            return zze(zzug);
        }

        public String zze(zzug zzug) {
            try {
                return zzug.getStringFlagValue(getKey(), (String) zzjw(), getSource());
            } catch (RemoteException e) {
                return (String) zzjw();
            }
        }
    }

    private zzud(int i, String str, T t) {
        this.zzaxm = i;
        this.zzaxn = str;
        this.zzaxo = t;
        zzuh.zzbfr().zza(this);
    }

    public static zza zzb(int i, String str, Boolean bool) {
        return new zza(i, str, bool);
    }

    public static zzb zzb(int i, String str, int i2) {
        return new zzb(i, str, Integer.valueOf(i2));
    }

    public static zzc zzb(int i, String str, long j) {
        return new zzc(i, str, Long.valueOf(j));
    }

    public static zzd zzc(int i, String str, String str2) {
        return new zzd(i, str, str2);
    }

    public T get() {
        return zzuh.zzbfs().zzb(this);
    }

    public String getKey() {
        return this.zzaxn;
    }

    public int getSource() {
        return this.zzaxm;
    }

    protected abstract T zza(zzug zzug);

    public T zzjw() {
        return this.zzaxo;
    }
}
