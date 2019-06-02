package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzaoh implements zzanl {
    private final zzans beb;
    private final boolean bfQ;

    private final class zza<K, V> extends zzank<Map<K, V>> {
        private final zzanx<? extends Map<K, V>> bfI;
        private final zzank<K> bfR;
        private final zzank<V> bfS;
        final /* synthetic */ zzaoh bfT;

        public zza(zzaoh zzaoh, zzams zzams, Type type, zzank<K> zzank, Type type2, zzank<V> zzank2, zzanx<? extends Map<K, V>> zzanx) {
            this.bfT = zzaoh;
            this.bfR = new zzaom(zzams, zzank, type);
            this.bfS = new zzaom(zzams, zzank2, type2);
            this.bfI = zzanx;
        }

        private String zze(zzamy zzamy) {
            if (zzamy.zzczo()) {
                zzane zzczs = zzamy.zzczs();
                if (zzczs.zzczv()) {
                    return String.valueOf(zzczs.zzczg());
                }
                if (zzczs.zzczu()) {
                    return Boolean.toString(zzczs.zzczl());
                }
                if (zzczs.zzczw()) {
                    return zzczs.zzczh();
                }
                throw new AssertionError();
            } else if (zzamy.zzczp()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }

        public void zza(zzaor zzaor, Map<K, V> map) throws IOException {
            int i = 0;
            if (map == null) {
                zzaor.mo4206r();
            } else if (this.bfT.bfQ) {
                List arrayList = new ArrayList(map.size());
                List arrayList2 = new ArrayList(map.size());
                int i2 = 0;
                for (Entry entry : map.entrySet()) {
                    zzamy zzcl = this.bfR.zzcl(entry.getKey());
                    arrayList.add(zzcl);
                    arrayList2.add(entry.getValue());
                    int i3 = (zzcl.zzczm() || zzcl.zzczn()) ? 1 : 0;
                    i2 = i3 | i2;
                }
                if (i2 != 0) {
                    zzaor.mo4202n();
                    while (i < arrayList.size()) {
                        zzaor.mo4202n();
                        zzanz.zzb((zzamy) arrayList.get(i), zzaor);
                        this.bfS.zza(zzaor, arrayList2.get(i));
                        zzaor.mo4203o();
                        i++;
                    }
                    zzaor.mo4203o();
                    return;
                }
                zzaor.mo4204p();
                while (i < arrayList.size()) {
                    zzaor.zzta(zze((zzamy) arrayList.get(i)));
                    this.bfS.zza(zzaor, arrayList2.get(i));
                    i++;
                }
                zzaor.mo4205q();
            } else {
                zzaor.mo4204p();
                for (Entry entry2 : map.entrySet()) {
                    zzaor.zzta(String.valueOf(entry2.getKey()));
                    this.bfS.zza(zzaor, entry2.getValue());
                }
                zzaor.mo4205q();
            }
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzl(zzaop);
        }

        public Map<K, V> zzl(zzaop zzaop) throws IOException {
            zzaoq h = zzaop.mo4189h();
            if (h == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            Map<K, V> map = (Map) this.bfI.mo4182a();
            Object zzb;
            if (h == zzaoq.BEGIN_ARRAY) {
                zzaop.beginArray();
                while (zzaop.hasNext()) {
                    zzaop.beginArray();
                    zzb = this.bfR.zzb(zzaop);
                    if (map.put(zzb, this.bfS.zzb(zzaop)) != null) {
                        String valueOf = String.valueOf(zzb);
                        throw new zzanh(new StringBuilder(String.valueOf(valueOf).length() + 15).append("duplicate key: ").append(valueOf).toString());
                    }
                    zzaop.endArray();
                }
                zzaop.endArray();
                return map;
            }
            zzaop.beginObject();
            while (zzaop.hasNext()) {
                zzanu.bff.zzi(zzaop);
                zzb = this.bfR.zzb(zzaop);
                if (map.put(zzb, this.bfS.zzb(zzaop)) != null) {
                    valueOf = String.valueOf(zzb);
                    throw new zzanh(new StringBuilder(String.valueOf(valueOf).length() + 15).append("duplicate key: ").append(valueOf).toString());
                }
            }
            zzaop.endObject();
            return map;
        }
    }

    public zzaoh(zzans zzans, boolean z) {
        this.beb = zzans;
        this.bfQ = z;
    }

    private zzank<?> zza(zzams zzams, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? zzaon.bgm : zzams.zza(zzaoo.zzl(type));
    }

    public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
        Type t = zzaoo.m16037t();
        if (!Map.class.isAssignableFrom(zzaoo.m16036s())) {
            return null;
        }
        Type[] zzb = zzanr.zzb(t, zzanr.zzf(t));
        zzank zza = zza(zzams, zzb[0]);
        zzank zza2 = zzams.zza(zzaoo.zzl(zzb[1]));
        zzanx zzb2 = this.beb.zzb(zzaoo);
        return new zza(this, zzams, zzb[0], zza, zzb[1], zza2, zzb2);
    }
}
