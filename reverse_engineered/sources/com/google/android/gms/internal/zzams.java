package com.google.android.gms.internal;

import com.alipay.sdk.util.C0880h;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzams {
    private final ThreadLocal<Map<zzaoo<?>, zza<?>>> bdY;
    private final Map<zzaoo<?>, zzank<?>> bdZ;
    private final List<zzanl> bea;
    private final zzans beb;
    private final boolean bec;
    private final boolean bed;
    private final boolean bee;
    private final boolean bef;
    final zzamw beg;
    final zzanf beh;

    /* renamed from: com.google.android.gms.internal.zzams$1 */
    class C33321 implements zzamw {
        final /* synthetic */ zzams bei;

        C33321(zzams zzams) {
            this.bei = zzams;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzams$2 */
    class C33332 implements zzanf {
        final /* synthetic */ zzams bei;

        C33332(zzams zzams) {
            this.bei = zzams;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzams$3 */
    class C33343 extends zzank<Number> {
        final /* synthetic */ zzams bei;

        C33343(zzams zzams) {
            this.bei = zzams;
        }

        public void zza(zzaor zzaor, Number number) throws IOException {
            if (number == null) {
                zzaor.mo4206r();
                return;
            }
            this.bei.zzn(number.doubleValue());
            zzaor.zza(number);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zze(zzaop);
        }

        public Double zze(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() != zzaoq.bhH) {
                return Double.valueOf(zzaop.nextDouble());
            }
            zzaop.nextNull();
            return null;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzams$4 */
    class C33354 extends zzank<Number> {
        final /* synthetic */ zzams bei;

        C33354(zzams zzams) {
            this.bei = zzams;
        }

        public void zza(zzaor zzaor, Number number) throws IOException {
            if (number == null) {
                zzaor.mo4206r();
                return;
            }
            this.bei.zzn((double) number.floatValue());
            zzaor.zza(number);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzf(zzaop);
        }

        public Float zzf(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() != zzaoq.bhH) {
                return Float.valueOf((float) zzaop.nextDouble());
            }
            zzaop.nextNull();
            return null;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzams$5 */
    class C33365 extends zzank<Number> {
        final /* synthetic */ zzams bei;

        C33365(zzams zzams) {
            this.bei = zzams;
        }

        public void zza(zzaor zzaor, Number number) throws IOException {
            if (number == null) {
                zzaor.mo4206r();
            } else {
                zzaor.zztb(number.toString());
            }
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzg(zzaop);
        }

        public Number zzg(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() != zzaoq.bhH) {
                return Long.valueOf(zzaop.nextLong());
            }
            zzaop.nextNull();
            return null;
        }
    }

    static class zza<T> extends zzank<T> {
        private zzank<T> bej;

        zza() {
        }

        public void zza(zzank<T> zzank) {
            if (this.bej != null) {
                throw new AssertionError();
            }
            this.bej = zzank;
        }

        public void zza(zzaor zzaor, T t) throws IOException {
            if (this.bej == null) {
                throw new IllegalStateException();
            }
            this.bej.zza(zzaor, t);
        }

        public T zzb(zzaop zzaop) throws IOException {
            if (this.bej != null) {
                return this.bej.zzb(zzaop);
            }
            throw new IllegalStateException();
        }
    }

    public zzams() {
        this(zzant.beU, zzamq.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, zzani.DEFAULT, Collections.emptyList());
    }

    zzams(zzant zzant, zzamr zzamr, Map<Type, zzamu<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, zzani zzani, List<zzanl> list) {
        this.bdY = new ThreadLocal();
        this.bdZ = Collections.synchronizedMap(new HashMap());
        this.beg = new C33321(this);
        this.beh = new C33332(this);
        this.beb = new zzans(map);
        this.bec = z;
        this.bee = z3;
        this.bed = z4;
        this.bef = z5;
        List arrayList = new ArrayList();
        arrayList.add(zzaon.bgX);
        arrayList.add(zzaoi.bfE);
        arrayList.add(zzant);
        arrayList.addAll(list);
        arrayList.add(zzaon.bgE);
        arrayList.add(zzaon.bgt);
        arrayList.add(zzaon.bgn);
        arrayList.add(zzaon.bgp);
        arrayList.add(zzaon.bgr);
        arrayList.add(zzaon.zza(Long.TYPE, Long.class, zza(zzani)));
        arrayList.add(zzaon.zza(Double.TYPE, Double.class, zzcx(z6)));
        arrayList.add(zzaon.zza(Float.TYPE, Float.class, zzcy(z6)));
        arrayList.add(zzaon.bgy);
        arrayList.add(zzaon.bgA);
        arrayList.add(zzaon.bgG);
        arrayList.add(zzaon.bgI);
        arrayList.add(zzaon.zza(BigDecimal.class, zzaon.bgC));
        arrayList.add(zzaon.zza(BigInteger.class, zzaon.bgD));
        arrayList.add(zzaon.bgK);
        arrayList.add(zzaon.bgM);
        arrayList.add(zzaon.bgQ);
        arrayList.add(zzaon.bgV);
        arrayList.add(zzaon.bgO);
        arrayList.add(zzaon.bgk);
        arrayList.add(zzaod.bfE);
        arrayList.add(zzaon.bgT);
        arrayList.add(zzaol.bfE);
        arrayList.add(zzaok.bfE);
        arrayList.add(zzaon.bgR);
        arrayList.add(zzaob.bfE);
        arrayList.add(zzaon.bgi);
        arrayList.add(new zzaoc(this.beb));
        arrayList.add(new zzaoh(this.beb, z2));
        arrayList.add(new zzaoe(this.beb));
        arrayList.add(zzaon.bgY);
        arrayList.add(new zzaoj(this.beb, zzamr, zzant));
        this.bea = Collections.unmodifiableList(arrayList);
    }

    private zzank<Number> zza(zzani zzani) {
        return zzani == zzani.DEFAULT ? zzaon.bgu : new C33365(this);
    }

    private static void zza(Object obj, zzaop zzaop) {
        if (obj != null) {
            try {
                if (zzaop.mo4189h() != zzaoq.END_DOCUMENT) {
                    throw new zzamz("JSON document was not fully consumed.");
                }
            } catch (Throwable e) {
                throw new zzanh(e);
            } catch (Throwable e2) {
                throw new zzamz(e2);
            }
        }
    }

    private zzank<Number> zzcx(boolean z) {
        return z ? zzaon.bgw : new C33343(this);
    }

    private zzank<Number> zzcy(boolean z) {
        return z ? zzaon.bgv : new C33354(this);
    }

    private void zzn(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.bec + "factories:" + this.bea + ",instanceCreators:" + this.beb + C0880h.f2222d;
    }

    public <T> zzank<T> zza(zzanl zzanl, zzaoo<T> zzaoo) {
        Object obj = null;
        if (!this.bea.contains(zzanl)) {
            obj = 1;
        }
        Object obj2 = obj;
        for (zzanl zzanl2 : this.bea) {
            if (obj2 != null) {
                zzank<T> zza = zzanl2.zza(this, zzaoo);
                if (zza != null) {
                    return zza;
                }
            } else if (zzanl2 == zzanl) {
                obj2 = 1;
            }
        }
        String valueOf = String.valueOf(zzaoo);
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 22).append("GSON cannot serialize ").append(valueOf).toString());
    }

    public <T> zzank<T> zza(zzaoo<T> zzaoo) {
        zzank<T> zzank = (zzank) this.bdZ.get(zzaoo);
        if (zzank == null) {
            Map map;
            Map map2 = (Map) this.bdY.get();
            Object obj = null;
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                this.bdY.set(hashMap);
                map = hashMap;
                obj = 1;
            } else {
                map = map2;
            }
            zza zza = (zza) map.get(zzaoo);
            if (zza == null) {
                try {
                    zza zza2 = new zza();
                    map.put(zzaoo, zza2);
                    for (zzanl zza3 : this.bea) {
                        zzank = zza3.zza(this, zzaoo);
                        if (zzank != null) {
                            zza2.zza(zzank);
                            this.bdZ.put(zzaoo, zzank);
                            map.remove(zzaoo);
                            if (obj != null) {
                                this.bdY.remove();
                            }
                        }
                    }
                    String valueOf = String.valueOf(zzaoo);
                    throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 19).append("GSON cannot handle ").append(valueOf).toString());
                } catch (Throwable th) {
                    map.remove(zzaoo);
                    if (obj != null) {
                        this.bdY.remove();
                    }
                }
            }
        }
        return zzank;
    }

    public zzaor zza(Writer writer) throws IOException {
        if (this.bee) {
            writer.write(")]}'\n");
        }
        zzaor zzaor = new zzaor(writer);
        if (this.bef) {
            zzaor.setIndent("  ");
        }
        zzaor.zzdc(this.bec);
        return zzaor;
    }

    public <T> T zza(zzamy zzamy, Class<T> cls) throws zzanh {
        return zzany.zzp(cls).cast(zza(zzamy, (Type) cls));
    }

    public <T> T zza(zzamy zzamy, Type type) throws zzanh {
        return zzamy == null ? null : zza(new zzaof(zzamy), type);
    }

    public <T> T zza(zzaop zzaop, Type type) throws zzamz, zzanh {
        boolean z = true;
        boolean isLenient = zzaop.isLenient();
        zzaop.setLenient(true);
        try {
            zzaop.mo4189h();
            z = false;
            T zzb = zza(zzaoo.zzl(type)).zzb(zzaop);
            zzaop.setLenient(isLenient);
            return zzb;
        } catch (Throwable e) {
            if (z) {
                zzaop.setLenient(isLenient);
                return null;
            }
            throw new zzanh(e);
        } catch (Throwable e2) {
            throw new zzanh(e2);
        } catch (Throwable e22) {
            throw new zzanh(e22);
        } catch (Throwable th) {
            zzaop.setLenient(isLenient);
        }
    }

    public <T> T zza(Reader reader, Type type) throws zzamz, zzanh {
        zzaop zzaop = new zzaop(reader);
        Object zza = zza(zzaop, type);
        zza(zza, zzaop);
        return zza;
    }

    public <T> T zza(String str, Type type) throws zzanh {
        return str == null ? null : zza(new StringReader(str), type);
    }

    public void zza(zzamy zzamy, zzaor zzaor) throws zzamz {
        boolean isLenient = zzaor.isLenient();
        zzaor.setLenient(true);
        boolean D = zzaor.m16022D();
        zzaor.zzdb(this.bed);
        boolean E = zzaor.m16023E();
        zzaor.zzdc(this.bec);
        try {
            zzanz.zzb(zzamy, zzaor);
            zzaor.setLenient(isLenient);
            zzaor.zzdb(D);
            zzaor.zzdc(E);
        } catch (Throwable e) {
            throw new zzamz(e);
        } catch (Throwable th) {
            zzaor.setLenient(isLenient);
            zzaor.zzdb(D);
            zzaor.zzdc(E);
        }
    }

    public void zza(zzamy zzamy, Appendable appendable) throws zzamz {
        try {
            zza(zzamy, zza(zzanz.zza(appendable)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void zza(Object obj, Type type, zzaor zzaor) throws zzamz {
        zzank zza = zza(zzaoo.zzl(type));
        boolean isLenient = zzaor.isLenient();
        zzaor.setLenient(true);
        boolean D = zzaor.m16022D();
        zzaor.zzdb(this.bed);
        boolean E = zzaor.m16023E();
        zzaor.zzdc(this.bec);
        try {
            zza.zza(zzaor, obj);
            zzaor.setLenient(isLenient);
            zzaor.zzdb(D);
            zzaor.zzdc(E);
        } catch (Throwable e) {
            throw new zzamz(e);
        } catch (Throwable th) {
            zzaor.setLenient(isLenient);
            zzaor.zzdb(D);
            zzaor.zzdc(E);
        }
    }

    public void zza(Object obj, Type type, Appendable appendable) throws zzamz {
        try {
            zza(obj, type, zza(zzanz.zza(appendable)));
        } catch (Throwable e) {
            throw new zzamz(e);
        }
    }

    public String zzb(zzamy zzamy) {
        Appendable stringWriter = new StringWriter();
        zza(zzamy, stringWriter);
        return stringWriter.toString();
    }

    public String zzc(Object obj, Type type) {
        Appendable stringWriter = new StringWriter();
        zza(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public String zzcj(Object obj) {
        return obj == null ? zzb(zzana.bes) : zzc(obj, obj.getClass());
    }

    public <T> T zzf(String str, Class<T> cls) throws zzanh {
        return zzany.zzp(cls).cast(zza(str, (Type) cls));
    }

    public <T> zzank<T> zzk(Class<T> cls) {
        return zza(zzaoo.zzr(cls));
    }
}
