package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.alipay.sdk.util.C0880h;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.common.util.zzq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class FastJsonResponse {

    public interface zza<I, O> {
        I convertBack(O o);

        int zzatp();

        int zzatq();
    }

    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zza CREATOR = new zza();
        private final int mVersionCode;
        protected final String zA;
        private FieldMappingDictionary zB;
        private zza<I, O> zC;
        protected final int zt;
        protected final boolean zu;
        protected final int zv;
        protected final boolean zw;
        protected final String zx;
        protected final int zy;
        protected final Class<? extends FastJsonResponse> zz;

        Field(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, ConverterWrapper converterWrapper) {
            this.mVersionCode = i;
            this.zt = i2;
            this.zu = z;
            this.zv = i3;
            this.zw = z2;
            this.zx = str;
            this.zy = i4;
            if (str2 == null) {
                this.zz = null;
                this.zA = null;
            } else {
                this.zz = SafeParcelResponse.class;
                this.zA = str2;
            }
            if (converterWrapper == null) {
                this.zC = null;
            } else {
                this.zC = converterWrapper.zzatn();
            }
        }

        protected Field(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends FastJsonResponse> cls, zza<I, O> zza) {
            this.mVersionCode = 1;
            this.zt = i;
            this.zu = z;
            this.zv = i2;
            this.zw = z2;
            this.zx = str;
            this.zy = i3;
            this.zz = cls;
            if (cls == null) {
                this.zA = null;
            } else {
                this.zA = cls.getCanonicalName();
            }
            this.zC = zza;
        }

        public static Field zza(String str, int i, zza<?, ?> zza, boolean z) {
            return new Field(zza.zzatp(), z, zza.zzatq(), false, str, i, null, zza);
        }

        public static <T extends FastJsonResponse> Field<T, T> zza(String str, int i, Class<T> cls) {
            return new Field(11, false, 11, false, str, i, cls, null);
        }

        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> zzb(String str, int i, Class<T> cls) {
            return new Field(11, true, 11, true, str, i, cls, null);
        }

        public static Field<Integer, Integer> zzj(String str, int i) {
            return new Field(0, false, 0, false, str, i, null, null);
        }

        public static Field<Boolean, Boolean> zzk(String str, int i) {
            return new Field(6, false, 6, false, str, i, null, null);
        }

        public static Field<String, String> zzl(String str, int i) {
            return new Field(7, false, 7, false, str, i, null, null);
        }

        public I convertBack(O o) {
            return this.zC.convertBack(o);
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Field\n");
            stringBuilder.append("            versionCode=").append(this.mVersionCode).append('\n');
            stringBuilder.append("                 typeIn=").append(this.zt).append('\n');
            stringBuilder.append("            typeInArray=").append(this.zu).append('\n');
            stringBuilder.append("                typeOut=").append(this.zv).append('\n');
            stringBuilder.append("           typeOutArray=").append(this.zw).append('\n');
            stringBuilder.append("        outputFieldName=").append(this.zx).append('\n');
            stringBuilder.append("      safeParcelFieldId=").append(this.zy).append('\n');
            stringBuilder.append("       concreteTypeName=").append(zzatz()).append('\n');
            if (zzaty() != null) {
                stringBuilder.append("     concreteType.class=").append(zzaty().getCanonicalName()).append('\n');
            }
            stringBuilder.append("          converterName=").append(this.zC == null ? "null" : this.zC.getClass().getCanonicalName()).append('\n');
            return stringBuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zza zza = CREATOR;
            zza.zza(this, parcel, i);
        }

        public void zza(FieldMappingDictionary fieldMappingDictionary) {
            this.zB = fieldMappingDictionary;
        }

        public int zzatp() {
            return this.zt;
        }

        public int zzatq() {
            return this.zv;
        }

        public boolean zzatu() {
            return this.zu;
        }

        public boolean zzatv() {
            return this.zw;
        }

        public String zzatw() {
            return this.zx;
        }

        public int zzatx() {
            return this.zy;
        }

        public Class<? extends FastJsonResponse> zzaty() {
            return this.zz;
        }

        String zzatz() {
            return this.zA == null ? null : this.zA;
        }

        public boolean zzaua() {
            return this.zC != null;
        }

        ConverterWrapper zzaub() {
            return this.zC == null ? null : ConverterWrapper.zza(this.zC);
        }

        public Map<String, Field<?, ?>> zzauc() {
            zzab.zzaa(this.zA);
            zzab.zzaa(this.zB);
            return this.zB.zzhx(this.zA);
        }
    }

    private void zza(StringBuilder stringBuilder, Field field, Object obj) {
        if (field.zzatp() == 11) {
            stringBuilder.append(((FastJsonResponse) field.zzaty().cast(obj)).toString());
        } else if (field.zzatp() == 7) {
            stringBuilder.append("\"");
            stringBuilder.append(zzp.zzib((String) obj));
            stringBuilder.append("\"");
        } else {
            stringBuilder.append(obj);
        }
    }

    private void zza(StringBuilder stringBuilder, Field field, ArrayList<Object> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                zza(stringBuilder, field, obj);
            }
        }
        stringBuilder.append("]");
    }

    public String toString() {
        Map zzatr = zzatr();
        StringBuilder stringBuilder = new StringBuilder(100);
        for (String str : zzatr.keySet()) {
            Field field = (Field) zzatr.get(str);
            if (zza(field)) {
                Object zza = zza(field, zzb(field));
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("{");
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("\"").append(str).append("\":");
                if (zza != null) {
                    switch (field.zzatq()) {
                        case 8:
                            stringBuilder.append("\"").append(zzc.zzp((byte[]) zza)).append("\"");
                            break;
                        case 9:
                            stringBuilder.append("\"").append(zzc.zzq((byte[]) zza)).append("\"");
                            break;
                        case 10:
                            zzq.zza(stringBuilder, (HashMap) zza);
                            break;
                        default:
                            if (!field.zzatu()) {
                                zza(stringBuilder, field, zza);
                                break;
                            }
                            zza(stringBuilder, field, (ArrayList) zza);
                            break;
                    }
                }
                stringBuilder.append("null");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append(C0880h.f2222d);
        } else {
            stringBuilder.append("{}");
        }
        return stringBuilder.toString();
    }

    protected <O, I> I zza(Field<I, O> field, Object obj) {
        return field.zC != null ? field.convertBack(obj) : obj;
    }

    protected boolean zza(Field field) {
        return field.zzatq() == 11 ? field.zzatv() ? zzhw(field.zzatw()) : zzhv(field.zzatw()) : zzhu(field.zzatw());
    }

    public abstract Map<String, Field<?, ?>> zzatr();

    public HashMap<String, Object> zzats() {
        return null;
    }

    public HashMap<String, Object> zzatt() {
        return null;
    }

    protected Object zzb(Field field) {
        String zzatw = field.zzatw();
        if (field.zzaty() == null) {
            return zzht(field.zzatw());
        }
        zzab.zza(zzht(field.zzatw()) == null, "Concrete field shouldn't be value object: %s", field.zzatw());
        Map zzatt = field.zzatv() ? zzatt() : zzats();
        if (zzatt != null) {
            return zzatt.get(zzatw);
        }
        try {
            char toUpperCase = Character.toUpperCase(zzatw.charAt(0));
            String valueOf = String.valueOf(zzatw.substring(1));
            return getClass().getMethod(new StringBuilder(String.valueOf(valueOf).length() + 4).append("get").append(toUpperCase).append(valueOf).toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract Object zzht(String str);

    protected abstract boolean zzhu(String str);

    protected boolean zzhv(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    protected boolean zzhw(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }
}
