package com.google.android.gms.internal;

import android.os.Binder;

public abstract class zzre<T> {
    private static zza vA = null;
    private static int vB = 0;
    private static String vC = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    private static final Object zzamp = new Object();
    private T vD = null;
    protected final String zzaxn;
    protected final T zzaxo;

    /* renamed from: com.google.android.gms.internal.zzre$1 */
    class C33971 extends zzre<Boolean> {
        C33971(String str, Boolean bool) {
            super(str, bool);
        }

        protected /* synthetic */ Object zzgz(String str) {
            return zzha(str);
        }

        protected Boolean zzha(String str) {
            return null.zza(this.zzaxn, (Boolean) this.zzaxo);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzre$2 */
    class C33982 extends zzre<Long> {
        C33982(String str, Long l) {
            super(str, l);
        }

        protected /* synthetic */ Object zzgz(String str) {
            return zzhb(str);
        }

        protected Long zzhb(String str) {
            return null.getLong(this.zzaxn, (Long) this.zzaxo);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzre$3 */
    class C33993 extends zzre<Integer> {
        C33993(String str, Integer num) {
            super(str, num);
        }

        protected /* synthetic */ Object zzgz(String str) {
            return zzhc(str);
        }

        protected Integer zzhc(String str) {
            return null.zzb(this.zzaxn, (Integer) this.zzaxo);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzre$4 */
    class C34004 extends zzre<Float> {
        C34004(String str, Float f) {
            super(str, f);
        }

        protected /* synthetic */ Object zzgz(String str) {
            return zzhd(str);
        }

        protected Float zzhd(String str) {
            return null.zzb(this.zzaxn, (Float) this.zzaxo);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzre$5 */
    class C34015 extends zzre<String> {
        C34015(String str, String str2) {
            super(str, str2);
        }

        protected /* synthetic */ Object zzgz(String str) {
            return zzhe(str);
        }

        protected String zzhe(String str) {
            return null.getString(this.zzaxn, (String) this.zzaxo);
        }
    }

    private interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zza(String str, Boolean bool);

        Float zzb(String str, Float f);

        Integer zzb(String str, Integer num);
    }

    protected zzre(String str, T t) {
        this.zzaxn = str;
        this.zzaxo = t;
    }

    public static zzre<Float> zza(String str, Float f) {
        return new C34004(str, f);
    }

    public static zzre<Integer> zza(String str, Integer num) {
        return new C33993(str, num);
    }

    public static zzre<Long> zza(String str, Long l) {
        return new C33982(str, l);
    }

    public static zzre<String> zzab(String str, String str2) {
        return new C34015(str, str2);
    }

    public static zzre<Boolean> zzm(String str, boolean z) {
        return new C33971(str, Boolean.valueOf(z));
    }

    public final T get() {
        T zzgz;
        long clearCallingIdentity;
        try {
            zzgz = zzgz(this.zzaxn);
        } catch (SecurityException e) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            zzgz = zzgz(this.zzaxn);
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
        return zzgz;
    }

    protected abstract T zzgz(String str);
}
