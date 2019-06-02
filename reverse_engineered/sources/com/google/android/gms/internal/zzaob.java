package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class zzaob<E> extends zzank<Object> {
    public static final zzanl bfE = new C33581();
    private final Class<E> bfF;
    private final zzank<E> bfG;

    /* renamed from: com.google.android.gms.internal.zzaob$1 */
    static class C33581 implements zzanl {
        C33581() {
        }

        public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
            Type t = zzaoo.m16037t();
            if (!(t instanceof GenericArrayType) && (!(t instanceof Class) || !((Class) t).isArray())) {
                return null;
            }
            t = zzanr.zzh(t);
            return new zzaob(zzams, zzams.zza(zzaoo.zzl(t)), zzanr.zzf(t));
        }
    }

    public zzaob(zzams zzams, zzank<E> zzank, Class<E> cls) {
        this.bfG = new zzaom(zzams, zzank, cls);
        this.bfF = cls;
    }

    public void zza(zzaor zzaor, Object obj) throws IOException {
        if (obj == null) {
            zzaor.mo4206r();
            return;
        }
        zzaor.mo4202n();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.bfG.zza(zzaor, Array.get(obj, i));
        }
        zzaor.mo4203o();
    }

    public Object zzb(zzaop zzaop) throws IOException {
        if (zzaop.mo4189h() == zzaoq.bhH) {
            zzaop.nextNull();
            return null;
        }
        List arrayList = new ArrayList();
        zzaop.beginArray();
        while (zzaop.hasNext()) {
            arrayList.add(this.bfG.zzb(zzaop));
        }
        zzaop.endArray();
        Object newInstance = Array.newInstance(this.bfF, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }
}
