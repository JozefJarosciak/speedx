package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzab;

public abstract class zzc {
    protected final DataHolder tk;
    protected int vK;
    private int vL;

    public zzc(DataHolder dataHolder, int i) {
        this.tk = (DataHolder) zzab.zzaa(dataHolder);
        zzfm(i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzc = (zzc) obj;
        return zzaa.equal(Integer.valueOf(zzc.vK), Integer.valueOf(this.vK)) && zzaa.equal(Integer.valueOf(zzc.vL), Integer.valueOf(this.vL)) && zzc.tk == this.tk;
    }

    protected boolean getBoolean(String str) {
        return this.tk.zze(str, this.vK, this.vL);
    }

    protected byte[] getByteArray(String str) {
        return this.tk.zzg(str, this.vK, this.vL);
    }

    protected float getFloat(String str) {
        return this.tk.zzf(str, this.vK, this.vL);
    }

    protected int getInteger(String str) {
        return this.tk.zzc(str, this.vK, this.vL);
    }

    protected long getLong(String str) {
        return this.tk.zzb(str, this.vK, this.vL);
    }

    protected String getString(String str) {
        return this.tk.zzd(str, this.vK, this.vL);
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.vK), Integer.valueOf(this.vL), this.tk);
    }

    public boolean isDataValid() {
        return !this.tk.isClosed();
    }

    protected void zza(String str, CharArrayBuffer charArrayBuffer) {
        this.tk.zza(str, this.vK, this.vL, charArrayBuffer);
    }

    protected int zzarb() {
        return this.vK;
    }

    protected void zzfm(int i) {
        boolean z = i >= 0 && i < this.tk.getCount();
        zzab.zzbm(z);
        this.vK = i;
        this.vL = this.tk.zzfo(this.vK);
    }

    public boolean zzhf(String str) {
        return this.tk.zzhf(str);
    }

    protected Uri zzhg(String str) {
        return this.tk.zzh(str, this.vK, this.vL);
    }

    protected boolean zzhh(String str) {
        return this.tk.zzi(str, this.vK, this.vL);
    }
}
