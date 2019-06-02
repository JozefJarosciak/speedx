package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public final class zzaoc implements zzanl {
    private final zzans beb;

    private static final class zza<E> extends zzank<Collection<E>> {
        private final zzank<E> bfH;
        private final zzanx<? extends Collection<E>> bfI;

        public zza(zzams zzams, Type type, zzank<E> zzank, zzanx<? extends Collection<E>> zzanx) {
            this.bfH = new zzaom(zzams, zzank, type);
            this.bfI = zzanx;
        }

        public void zza(zzaor zzaor, Collection<E> collection) throws IOException {
            if (collection == null) {
                zzaor.mo4206r();
                return;
            }
            zzaor.mo4202n();
            for (E zza : collection) {
                this.bfH.zza(zzaor, zza);
            }
            zzaor.mo4203o();
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzj(zzaop);
        }

        public Collection<E> zzj(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            Collection<E> collection = (Collection) this.bfI.mo4182a();
            zzaop.beginArray();
            while (zzaop.hasNext()) {
                collection.add(this.bfH.zzb(zzaop));
            }
            zzaop.endArray();
            return collection;
        }
    }

    public zzaoc(zzans zzans) {
        this.beb = zzans;
    }

    public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
        Type t = zzaoo.m16037t();
        Class s = zzaoo.m16036s();
        if (!Collection.class.isAssignableFrom(s)) {
            return null;
        }
        Type zza = zzanr.zza(t, s);
        return new zza(zzams, zza, zzams.zza(zzaoo.zzl(zza)), this.beb.zzb(zzaoo));
    }
}
