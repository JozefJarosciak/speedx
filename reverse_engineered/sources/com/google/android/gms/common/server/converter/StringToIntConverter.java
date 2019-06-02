package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class StringToIntConverter extends AbstractSafeParcelable implements zza<String, Integer> {
    public static final zzb CREATOR = new zzb();
    private final int mVersionCode;
    private final HashMap<String, Integer> zo;
    private final SparseArray<String> zp;
    private final ArrayList<Entry> zq;

    public static final class Entry extends AbstractSafeParcelable {
        public static final zzc CREATOR = new zzc();
        final int versionCode;
        final String zr;
        final int zs;

        Entry(int i, String str, int i2) {
            this.versionCode = i;
            this.zr = str;
            this.zs = i2;
        }

        Entry(String str, int i) {
            this.versionCode = 1;
            this.zr = str;
            this.zs = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzc zzc = CREATOR;
            zzc.zza(this, parcel, i);
        }
    }

    public StringToIntConverter() {
        this.mVersionCode = 1;
        this.zo = new HashMap();
        this.zp = new SparseArray();
        this.zq = null;
    }

    StringToIntConverter(int i, ArrayList<Entry> arrayList) {
        this.mVersionCode = i;
        this.zo = new HashMap();
        this.zp = new SparseArray();
        this.zq = null;
        zzh(arrayList);
    }

    private void zzh(ArrayList<Entry> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzi(entry.zr, entry.zs);
        }
    }

    public /* synthetic */ Object convertBack(Object obj) {
        return zzd((Integer) obj);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb zzb = CREATOR;
        zzb.zza(this, parcel, i);
    }

    ArrayList<Entry> zzato() {
        ArrayList<Entry> arrayList = new ArrayList();
        for (String str : this.zo.keySet()) {
            arrayList.add(new Entry(str, ((Integer) this.zo.get(str)).intValue()));
        }
        return arrayList;
    }

    public int zzatp() {
        return 7;
    }

    public int zzatq() {
        return 0;
    }

    public String zzd(Integer num) {
        String str = (String) this.zp.get(num.intValue());
        return (str == null && this.zo.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    public StringToIntConverter zzi(String str, int i) {
        this.zo.put(str, Integer.valueOf(i));
        this.zp.put(i, str);
        return this;
    }
}
