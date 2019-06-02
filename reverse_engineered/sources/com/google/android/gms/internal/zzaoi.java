package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzaoi extends zzank<Object> {
    public static final zzanl bfE = new C33621();
    private final zzams beA;

    /* renamed from: com.google.android.gms.internal.zzaoi$1 */
    static class C33621 implements zzanl {
        C33621() {
        }

        public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
            return zzaoo.m16036s() == Object.class ? new zzaoi(zzams) : null;
        }
    }

    private zzaoi(zzams zzams) {
        this.beA = zzams;
    }

    public void zza(zzaor zzaor, Object obj) throws IOException {
        if (obj == null) {
            zzaor.mo4206r();
            return;
        }
        zzank zzk = this.beA.zzk(obj.getClass());
        if (zzk instanceof zzaoi) {
            zzaor.mo4204p();
            zzaor.mo4205q();
            return;
        }
        zzk.zza(zzaor, obj);
    }

    public Object zzb(zzaop zzaop) throws IOException {
        switch (zzaop.mo4189h()) {
            case BEGIN_ARRAY:
                List arrayList = new ArrayList();
                zzaop.beginArray();
                while (zzaop.hasNext()) {
                    arrayList.add(zzb(zzaop));
                }
                zzaop.endArray();
                return arrayList;
            case BEGIN_OBJECT:
                Map zzanw = new zzanw();
                zzaop.beginObject();
                while (zzaop.hasNext()) {
                    zzanw.put(zzaop.nextName(), zzb(zzaop));
                }
                zzaop.endObject();
                return zzanw;
            case STRING:
                return zzaop.nextString();
            case NUMBER:
                return Double.valueOf(zzaop.nextDouble());
            case BOOLEAN:
                return Boolean.valueOf(zzaop.nextBoolean());
            case bhH:
                zzaop.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }
}
