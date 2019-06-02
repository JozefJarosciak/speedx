package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.alipay.sdk.packet.C0861d;
import com.google.android.gms.common.data.DataHolder.zza;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzd<T extends SafeParcelable> extends AbstractDataBuffer<T> {
    private static final String[] vM = new String[]{C0861d.f2139k};
    private final Creator<T> vN;

    public zzd(DataHolder dataHolder, Creator<T> creator) {
        super(dataHolder);
        this.vN = creator;
    }

    public static <T extends SafeParcelable> void zza(zza zza, T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        ContentValues contentValues = new ContentValues();
        contentValues.put(C0861d.f2139k, obtain.marshall());
        zza.zza(contentValues);
        obtain.recycle();
    }

    public static zza zzarc() {
        return DataHolder.zzb(vM);
    }

    public /* synthetic */ Object get(int i) {
        return zzfn(i);
    }

    public T zzfn(int i) {
        byte[] zzg = this.tk.zzg(C0861d.f2139k, i, this.tk.zzfo(i));
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        SafeParcelable safeParcelable = (SafeParcelable) this.vN.createFromParcel(obtain);
        obtain.recycle();
        return safeParcelable;
    }
}
