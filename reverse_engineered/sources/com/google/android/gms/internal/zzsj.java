package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.zzc;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import java.lang.reflect.Field;

public final class zzsj {
    private static zzsk Me;
    private static final zza Mf = new C34051();
    public static final zzb Mg = new C34062();
    public static final zzb Mh = new C34073();
    public static final zzb Mi = new C34084();
    public static final zzb Mj = new C34095();
    public static final zzb Mk = new C34106();
    private final Context Ml;

    /* renamed from: com.google.android.gms.internal.zzsj$1 */
    class C34051 implements zza {
        C34051() {
        }

        public int zzd(Context context, String str, boolean z) {
            return zzsj.zzd(context, str, z);
        }

        public int zzt(Context context, String str) {
            return zzsj.zzt(context, str);
        }
    }

    public interface zzb {

        public interface zza {
            int zzd(Context context, String str, boolean z);

            int zzt(Context context, String str);
        }

        public static class zzb {
            public int Mn = 0;
            public int Mo = 0;
            public int Mp = 0;
        }

        zzb zza(Context context, String str, zza zza);
    }

    /* renamed from: com.google.android.gms.internal.zzsj$2 */
    class C34062 implements zzb {
        C34062() {
        }

        public zzb zza(Context context, String str, zza zza) {
            zzb zzb = new zzb();
            zzb.Mo = zza.zzd(context, str, true);
            if (zzb.Mo != 0) {
                zzb.Mp = 1;
            } else {
                zzb.Mn = zza.zzt(context, str);
                if (zzb.Mn != 0) {
                    zzb.Mp = -1;
                }
            }
            return zzb;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzsj$3 */
    class C34073 implements zzb {
        C34073() {
        }

        public zzb zza(Context context, String str, zza zza) {
            zzb zzb = new zzb();
            zzb.Mn = zza.zzt(context, str);
            if (zzb.Mn != 0) {
                zzb.Mp = -1;
            } else {
                zzb.Mo = zza.zzd(context, str, true);
                if (zzb.Mo != 0) {
                    zzb.Mp = 1;
                }
            }
            return zzb;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzsj$4 */
    class C34084 implements zzb {
        C34084() {
        }

        public zzb zza(Context context, String str, zza zza) {
            zzb zzb = new zzb();
            zzb.Mn = zza.zzt(context, str);
            zzb.Mo = zza.zzd(context, str, true);
            if (zzb.Mn == 0 && zzb.Mo == 0) {
                zzb.Mp = 0;
            } else if (zzb.Mn >= zzb.Mo) {
                zzb.Mp = -1;
            } else {
                zzb.Mp = 1;
            }
            return zzb;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzsj$5 */
    class C34095 implements zzb {
        C34095() {
        }

        public zzb zza(Context context, String str, zza zza) {
            zzb zzb = new zzb();
            zzb.Mn = zza.zzt(context, str);
            zzb.Mo = zza.zzd(context, str, true);
            if (zzb.Mn == 0 && zzb.Mo == 0) {
                zzb.Mp = 0;
            } else if (zzb.Mo >= zzb.Mn) {
                zzb.Mp = 1;
            } else {
                zzb.Mp = -1;
            }
            return zzb;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzsj$6 */
    class C34106 implements zzb {
        C34106() {
        }

        public zzb zza(Context context, String str, zza zza) {
            zzb zzb = new zzb();
            zzb.Mn = zza.zzt(context, str);
            if (zzb.Mn != 0) {
                zzb.Mo = zza.zzd(context, str, false);
            } else {
                zzb.Mo = zza.zzd(context, str, true);
            }
            if (zzb.Mn == 0 && zzb.Mo == 0) {
                zzb.Mp = 0;
            } else if (zzb.Mo >= zzb.Mn) {
                zzb.Mp = 1;
            } else {
                zzb.Mp = -1;
            }
            return zzb;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzsj$7 */
    class C34117 implements zza {
        final /* synthetic */ int Mm;

        C34117(int i) {
            this.Mm = i;
        }

        public int zzd(Context context, String str, boolean z) {
            return 0;
        }

        public int zzt(Context context, String str) {
            return this.Mm;
        }
    }

    public static class zza extends Exception {
        private zza(String str) {
            super(str);
        }

        private zza(String str, Throwable th) {
            super(str, th);
        }
    }

    private zzsj(Context context) {
        this.Ml = (Context) zzab.zzaa(context);
    }

    public static zzsj zza(Context context, zzb zzb, String str) throws zza {
        zzb zza = zzb.zza(context, str, Mf);
        Log.i("DynamiteModule", new StringBuilder((String.valueOf(str).length() + 68) + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(zza.Mn).append(" and remote module ").append(str).append(":").append(zza.Mo).toString());
        if (zza.Mp == 0 || ((zza.Mp == -1 && zza.Mn == 0) || (zza.Mp == 1 && zza.Mo == 0))) {
            throw new zza("No acceptable module found. Local version is " + zza.Mn + " and remote version is " + zza.Mo + ".");
        } else if (zza.Mp == -1) {
            return zzv(context, str);
        } else {
            if (zza.Mp == 1) {
                try {
                    return zza(context, str, zza.Mo);
                } catch (Throwable e) {
                    Throwable th = e;
                    String str2 = "DynamiteModule";
                    String str3 = "Failed to load remote module: ";
                    String valueOf = String.valueOf(th.getMessage());
                    Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                    if (zza.Mn != 0 && zzb.zza(context, str, new C34117(zza.Mn)).Mp == -1) {
                        return zzv(context, str);
                    }
                    throw new zza("Remote load failed. No local fallback found.", th);
                }
            }
            throw new zza("VersionPolicy returned invalid code:" + zza.Mp);
        }
    }

    private static zzsj zza(Context context, String str, int i) throws zza {
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        zzsk zzcs = zzcs(context);
        if (zzcs == null) {
            throw new zza("Failed to create IDynamiteLoader.");
        }
        try {
            zzd zza = zzcs.zza(zze.zzae(context), str, i);
            if (zze.zzad(zza) != null) {
                return new zzsj((Context) zze.zzad(zza));
            }
            throw new zza("Failed to load remote module.");
        } catch (Throwable e) {
            throw new zza("Failed to load remote module.", e);
        }
    }

    private static zzsk zzcs(Context context) {
        synchronized (zzsj.class) {
            zzsk zzsk;
            if (Me != null) {
                zzsk = Me;
                return zzsk;
            } else if (zzc.zzand().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            } else {
                try {
                    zzsk = com.google.android.gms.internal.zzsk.zza.zzfd((IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance());
                    if (zzsk != null) {
                        Me = zzsk;
                        return zzsk;
                    }
                } catch (Exception e) {
                    String str = "DynamiteModule";
                    String str2 = "Failed to load IDynamiteLoader from GmsCore: ";
                    String valueOf = String.valueOf(e.getMessage());
                    Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    return null;
                }
            }
        }
    }

    public static int zzd(Context context, String str, boolean z) {
        zzsk zzcs = zzcs(context);
        if (zzcs == null) {
            return 0;
        }
        try {
            return zzcs.zza(zze.zzae(context), str, z);
        } catch (RemoteException e) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to retrieve remote module version: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return 0;
        }
    }

    public static int zzt(Context context, String str) {
        String valueOf;
        String valueOf2;
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            valueOf = String.valueOf("com.google.android.gms.dynamite.descriptors.");
            valueOf2 = String.valueOf("ModuleDescriptor");
            Class loadClass = classLoader.loadClass(new StringBuilder(((String.valueOf(valueOf).length() + 1) + String.valueOf(str).length()) + String.valueOf(valueOf2).length()).append(valueOf).append(str).append(".").append(valueOf2).toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            valueOf = String.valueOf(declaredField.get(null));
            Log.e("DynamiteModule", new StringBuilder((String.valueOf(valueOf).length() + 51) + String.valueOf(str).length()).append("Module descriptor id '").append(valueOf).append("' didn't match expected id '").append(str).append("'").toString());
            return 0;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.").toString());
            return 0;
        } catch (Exception e2) {
            valueOf = "DynamiteModule";
            valueOf2 = "Failed to load module descriptor class: ";
            String valueOf3 = String.valueOf(e2.getMessage());
            Log.e(valueOf, valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2));
            return 0;
        }
    }

    public static int zzu(Context context, String str) {
        return zzd(context, str, false);
    }

    private static zzsj zzv(Context context, String str) {
        String str2 = "DynamiteModule";
        String str3 = "Selected local version of ";
        String valueOf = String.valueOf(str);
        Log.i(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        return new zzsj(context.getApplicationContext());
    }

    public Context zzbcw() {
        return this.Ml;
    }

    public IBinder zziv(String str) throws zza {
        Throwable e;
        String str2;
        String valueOf;
        try {
            return (IBinder) this.Ml.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException e2) {
            e = e2;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            throw new zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        } catch (InstantiationException e3) {
            e = e3;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
            }
            throw new zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        } catch (IllegalAccessException e4) {
            e = e4;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
            }
            throw new zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        }
    }
}
