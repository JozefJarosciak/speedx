package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class zzaoj implements zzanl {
    private final zzans beb;
    private final zzant bek;
    private final zzamr bem;

    static abstract class zzb {
        final boolean bgc;
        final boolean bgd;
        final String name;

        protected zzb(String str, boolean z, boolean z2) {
            this.name = str;
            this.bgc = z;
            this.bgd = z2;
        }

        abstract void zza(zzaop zzaop, Object obj) throws IOException, IllegalAccessException;

        abstract void zza(zzaor zzaor, Object obj) throws IOException, IllegalAccessException;

        abstract boolean zzcq(Object obj) throws IOException, IllegalAccessException;
    }

    public static final class zza<T> extends zzank<T> {
        private final zzanx<T> bfI;
        private final Map<String, zzb> bgb;

        private zza(zzanx<T> zzanx, Map<String, zzb> map) {
            this.bfI = zzanx;
            this.bgb = map;
        }

        public void zza(zzaor zzaor, T t) throws IOException {
            if (t == null) {
                zzaor.mo4206r();
                return;
            }
            zzaor.mo4204p();
            try {
                for (zzb zzb : this.bgb.values()) {
                    if (zzb.zzcq(t)) {
                        zzaor.zzta(zzb.name);
                        zzb.zza(zzaor, (Object) t);
                    }
                }
                zzaor.mo4205q();
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            }
        }

        public T zzb(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            T a = this.bfI.mo4182a();
            try {
                zzaop.beginObject();
                while (zzaop.hasNext()) {
                    zzb zzb = (zzb) this.bgb.get(zzaop.nextName());
                    if (zzb == null || !zzb.bgd) {
                        zzaop.skipValue();
                    } else {
                        zzb.zza(zzaop, (Object) a);
                    }
                }
                zzaop.endObject();
                return a;
            } catch (Throwable e) {
                throw new zzanh(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    public zzaoj(zzans zzans, zzamr zzamr, zzant zzant) {
        this.beb = zzans;
        this.bem = zzamr;
        this.bek = zzant;
    }

    private zzank<?> zza(zzams zzams, Field field, zzaoo<?> zzaoo) {
        zzanm zzanm = (zzanm) field.getAnnotation(zzanm.class);
        if (zzanm != null) {
            zzank<?> zza = zzaoe.zza(this.beb, zzams, zzaoo, zzanm);
            if (zza != null) {
                return zza;
            }
        }
        return zzams.zza((zzaoo) zzaoo);
    }

    private zzb zza(zzams zzams, Field field, String str, zzaoo<?> zzaoo, boolean z, boolean z2) {
        final boolean zzk = zzany.zzk(zzaoo.m16036s());
        final zzams zzams2 = zzams;
        final Field field2 = field;
        final zzaoo<?> zzaoo2 = zzaoo;
        return new zzb(this, str, z, z2) {
            final zzank<?> bfV = this.bga.zza(zzams2, field2, zzaoo2);
            final /* synthetic */ zzaoj bga;

            void zza(zzaop zzaop, Object obj) throws IOException, IllegalAccessException {
                Object zzb = this.bfV.zzb(zzaop);
                if (zzb != null || !zzk) {
                    field2.set(obj, zzb);
                }
            }

            void zza(zzaor zzaor, Object obj) throws IOException, IllegalAccessException {
                new zzaom(zzams2, this.bfV, zzaoo2.m16037t()).zza(zzaor, field2.get(obj));
            }

            public boolean zzcq(Object obj) throws IOException, IllegalAccessException {
                return this.bgc && field2.get(obj) != obj;
            }
        };
    }

    static List<String> zza(zzamr zzamr, Field field) {
        zzann zzann = (zzann) field.getAnnotation(zzann.class);
        List<String> linkedList = new LinkedList();
        if (zzann == null) {
            linkedList.add(zzamr.zzc(field));
        } else {
            linkedList.add(zzann.value());
            for (Object add : zzann.zzczy()) {
                linkedList.add(add);
            }
        }
        return linkedList;
    }

    private Map<String, zzb> zza(zzams zzams, zzaoo<?> zzaoo, Class<?> cls) {
        Map<String, zzb> linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type t = zzaoo.m16037t();
        Class s;
        while (s != Object.class) {
            for (Field field : s.getDeclaredFields()) {
                boolean zza = zza(field, true);
                boolean zza2 = zza(field, false);
                if (zza || zza2) {
                    field.setAccessible(true);
                    Type zza3 = zzanr.zza(r19.m16037t(), s, field.getGenericType());
                    List zzd = zzd(field);
                    zzb zzb = null;
                    int i = 0;
                    while (i < zzd.size()) {
                        String str = (String) zzd.get(i);
                        if (i != 0) {
                            zza = false;
                        }
                        zzb zzb2 = (zzb) linkedHashMap.put(str, zza(zzams, field, str, zzaoo.zzl(zza3), zza, zza2));
                        if (zzb != null) {
                            zzb2 = zzb;
                        }
                        i++;
                        zzb = zzb2;
                    }
                    if (zzb != null) {
                        String valueOf = String.valueOf(t);
                        String str2 = zzb.name;
                        throw new IllegalArgumentException(new StringBuilder((String.valueOf(valueOf).length() + 37) + String.valueOf(str2).length()).append(valueOf).append(" declares multiple JSON fields named ").append(str2).toString());
                    }
                }
            }
            zzaoo zzl = zzaoo.zzl(zzanr.zza(zzl.m16037t(), s, s.getGenericSuperclass()));
            s = zzl.m16036s();
        }
        return linkedHashMap;
    }

    static boolean zza(Field field, boolean z, zzant zzant) {
        return (zzant.zza(field.getType(), z) || zzant.zza(field, z)) ? false : true;
    }

    private List<String> zzd(Field field) {
        return zza(this.bem, field);
    }

    public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
        Class s = zzaoo.m16036s();
        return !Object.class.isAssignableFrom(s) ? null : new zza(this.beb.zzb(zzaoo), zza(zzams, (zzaoo) zzaoo, s));
    }

    public boolean zza(Field field, boolean z) {
        return zza(field, z, this.bek);
    }
}
