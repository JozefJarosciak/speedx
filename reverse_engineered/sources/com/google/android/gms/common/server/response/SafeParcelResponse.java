package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.util.SparseArray;
import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.util.C0880h;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.common.util.zzq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SafeParcelResponse extends FastSafeParcelableJsonResponse {
    public static final zze CREATOR = new zze();
    private final String mClassName;
    private final int mVersionCode;
    private final FieldMappingDictionary zB;
    private final Parcel zI;
    private final int zJ = 2;
    private int zK;
    private int zL;

    SafeParcelResponse(int i, Parcel parcel, FieldMappingDictionary fieldMappingDictionary) {
        this.mVersionCode = i;
        this.zI = (Parcel) zzab.zzaa(parcel);
        this.zB = fieldMappingDictionary;
        if (this.zB == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.zB.zzauf();
        }
        this.zK = 2;
    }

    private void zza(StringBuilder stringBuilder, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                stringBuilder.append(obj);
                return;
            case 7:
                stringBuilder.append("\"").append(zzp.zzib(obj.toString())).append("\"");
                return;
            case 8:
                stringBuilder.append("\"").append(zzc.zzp((byte[]) obj)).append("\"");
                return;
            case 9:
                stringBuilder.append("\"").append(zzc.zzq((byte[]) obj));
                stringBuilder.append("\"");
                return;
            case 10:
                zzq.zza(stringBuilder, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private void zza(StringBuilder stringBuilder, Field<?, ?> field, Parcel parcel, int i) {
        switch (field.zzatq()) {
            case 0:
                zzb(stringBuilder, (Field) field, zza(field, Integer.valueOf(zza.zzg(parcel, i))));
                return;
            case 1:
                zzb(stringBuilder, (Field) field, zza(field, zza.zzk(parcel, i)));
                return;
            case 2:
                zzb(stringBuilder, (Field) field, zza(field, Long.valueOf(zza.zzi(parcel, i))));
                return;
            case 3:
                zzb(stringBuilder, (Field) field, zza(field, Float.valueOf(zza.zzl(parcel, i))));
                return;
            case 4:
                zzb(stringBuilder, (Field) field, zza(field, Double.valueOf(zza.zzn(parcel, i))));
                return;
            case 5:
                zzb(stringBuilder, (Field) field, zza(field, zza.zzp(parcel, i)));
                return;
            case 6:
                zzb(stringBuilder, (Field) field, zza(field, Boolean.valueOf(zza.zzc(parcel, i))));
                return;
            case 7:
                zzb(stringBuilder, (Field) field, zza(field, zza.zzq(parcel, i)));
                return;
            case 8:
            case 9:
                zzb(stringBuilder, (Field) field, zza(field, zza.zzt(parcel, i)));
                return;
            case 10:
                zzb(stringBuilder, (Field) field, zza(field, zzp(zza.zzs(parcel, i))));
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown field out type = " + field.zzatq());
        }
    }

    private void zza(StringBuilder stringBuilder, String str, Field<?, ?> field, Parcel parcel, int i) {
        stringBuilder.append("\"").append(str).append("\":");
        if (field.zzaua()) {
            zza(stringBuilder, field, parcel, i);
        } else {
            zzb(stringBuilder, field, parcel, i);
        }
    }

    private void zza(StringBuilder stringBuilder, Map<String, Field<?, ?>> map, Parcel parcel) {
        SparseArray zzau = zzau(map);
        stringBuilder.append(CoreConstants.CURLY_LEFT);
        int zzcl = zza.zzcl(parcel);
        Object obj = null;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            Entry entry = (Entry) zzau.get(zza.zzgi(zzck));
            if (entry != null) {
                if (obj != null) {
                    stringBuilder.append(",");
                }
                zza(stringBuilder, (String) entry.getKey(), (Field) entry.getValue(), parcel, zzck);
                obj = 1;
            }
        }
        if (parcel.dataPosition() != zzcl) {
            throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
        }
        stringBuilder.append(CoreConstants.CURLY_RIGHT);
    }

    private static SparseArray<Entry<String, Field<?, ?>>> zzau(Map<String, Field<?, ?>> map) {
        SparseArray<Entry<String, Field<?, ?>>> sparseArray = new SparseArray();
        for (Entry entry : map.entrySet()) {
            sparseArray.put(((Field) entry.getValue()).zzatx(), entry);
        }
        return sparseArray;
    }

    private void zzb(StringBuilder stringBuilder, Field<?, ?> field, Parcel parcel, int i) {
        if (field.zzatv()) {
            stringBuilder.append("[");
            switch (field.zzatq()) {
                case 0:
                    zzb.zza(stringBuilder, zza.zzw(parcel, i));
                    break;
                case 1:
                    zzb.zza(stringBuilder, zza.zzy(parcel, i));
                    break;
                case 2:
                    zzb.zza(stringBuilder, zza.zzx(parcel, i));
                    break;
                case 3:
                    zzb.zza(stringBuilder, zza.zzz(parcel, i));
                    break;
                case 4:
                    zzb.zza(stringBuilder, zza.zzaa(parcel, i));
                    break;
                case 5:
                    zzb.zza(stringBuilder, zza.zzab(parcel, i));
                    break;
                case 6:
                    zzb.zza(stringBuilder, zza.zzv(parcel, i));
                    break;
                case 7:
                    zzb.zza(stringBuilder, zza.zzac(parcel, i));
                    break;
                case 8:
                case 9:
                case 10:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case 11:
                    Parcel[] zzag = zza.zzag(parcel, i);
                    int length = zzag.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 > 0) {
                            stringBuilder.append(",");
                        }
                        zzag[i2].setDataPosition(0);
                        zza(stringBuilder, field.zzauc(), zzag[i2]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            stringBuilder.append("]");
            return;
        }
        switch (field.zzatq()) {
            case 0:
                stringBuilder.append(zza.zzg(parcel, i));
                return;
            case 1:
                stringBuilder.append(zza.zzk(parcel, i));
                return;
            case 2:
                stringBuilder.append(zza.zzi(parcel, i));
                return;
            case 3:
                stringBuilder.append(zza.zzl(parcel, i));
                return;
            case 4:
                stringBuilder.append(zza.zzn(parcel, i));
                return;
            case 5:
                stringBuilder.append(zza.zzp(parcel, i));
                return;
            case 6:
                stringBuilder.append(zza.zzc(parcel, i));
                return;
            case 7:
                stringBuilder.append("\"").append(zzp.zzib(zza.zzq(parcel, i))).append("\"");
                return;
            case 8:
                stringBuilder.append("\"").append(zzc.zzp(zza.zzt(parcel, i))).append("\"");
                return;
            case 9:
                stringBuilder.append("\"").append(zzc.zzq(zza.zzt(parcel, i)));
                stringBuilder.append("\"");
                return;
            case 10:
                Bundle zzs = zza.zzs(parcel, i);
                Set<String> keySet = zzs.keySet();
                keySet.size();
                stringBuilder.append("{");
                int i3 = 1;
                for (String str : keySet) {
                    if (i3 == 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append("\"").append(str).append("\"");
                    stringBuilder.append(":");
                    stringBuilder.append("\"").append(zzp.zzib(zzs.getString(str))).append("\"");
                    i3 = 0;
                }
                stringBuilder.append(C0880h.f2222d);
                return;
            case 11:
                Parcel zzaf = zza.zzaf(parcel, i);
                zzaf.setDataPosition(0);
                zza(stringBuilder, field.zzauc(), zzaf);
                return;
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    private void zzb(StringBuilder stringBuilder, Field<?, ?> field, Object obj) {
        if (field.zzatu()) {
            zzb(stringBuilder, (Field) field, (ArrayList) obj);
        } else {
            zza(stringBuilder, field.zzatp(), obj);
        }
    }

    private void zzb(StringBuilder stringBuilder, Field<?, ?> field, ArrayList<?> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            zza(stringBuilder, field.zzatp(), arrayList.get(i));
        }
        stringBuilder.append("]");
    }

    public static HashMap<String, String> zzp(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        zzab.zzb(this.zB, (Object) "Cannot convert to JSON on client side.");
        Parcel zzauh = zzauh();
        zzauh.setDataPosition(0);
        StringBuilder stringBuilder = new StringBuilder(100);
        zza(stringBuilder, this.zB.zzhx(this.mClassName), zzauh);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze zze = CREATOR;
        zze.zza(this, parcel, i);
    }

    public Map<String, Field<?, ?>> zzatr() {
        return this.zB == null ? null : this.zB.zzhx(this.mClassName);
    }

    public Parcel zzauh() {
        switch (this.zK) {
            case 0:
                this.zL = com.google.android.gms.common.internal.safeparcel.zzb.zzcm(this.zI);
                com.google.android.gms.common.internal.safeparcel.zzb.zzaj(this.zI, this.zL);
                this.zK = 2;
                break;
            case 1:
                com.google.android.gms.common.internal.safeparcel.zzb.zzaj(this.zI, this.zL);
                this.zK = 2;
                break;
        }
        return this.zI;
    }

    FieldMappingDictionary zzaui() {
        switch (this.zJ) {
            case 0:
                return null;
            case 1:
                return this.zB;
            case 2:
                return this.zB;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.zJ);
        }
    }

    public Object zzht(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public boolean zzhu(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
}
