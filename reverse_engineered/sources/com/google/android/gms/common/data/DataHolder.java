package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@KeepName
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public static final Creator<DataHolder> CREATOR = new zze();
    private static final zza vW = new C32931(new String[0], null);
    boolean mClosed;
    private final int mVersionCode;
    private final int ob;
    private final String[] vO;
    Bundle vP;
    private final CursorWindow[] vQ;
    private final Bundle vR;
    int[] vS;
    int vT;
    private Object vU;
    private boolean vV;

    public static class zza {
        private final String[] vO;
        private final ArrayList<HashMap<String, Object>> vX;
        private final String vY;
        private final HashMap<Object, Integer> vZ;
        private boolean wa;
        private String wb;

        private zza(String[] strArr, String str) {
            this.vO = (String[]) zzab.zzaa(strArr);
            this.vX = new ArrayList();
            this.vY = str;
            this.vZ = new HashMap();
            this.wa = false;
            this.wb = null;
        }

        private int zzb(HashMap<String, Object> hashMap) {
            if (this.vY == null) {
                return -1;
            }
            Object obj = hashMap.get(this.vY);
            if (obj == null) {
                return -1;
            }
            Integer num = (Integer) this.vZ.get(obj);
            if (num != null) {
                return num.intValue();
            }
            this.vZ.put(obj, Integer.valueOf(this.vX.size()));
            return -1;
        }

        public zza zza(ContentValues contentValues) {
            com.google.android.gms.common.internal.zzb.zzw(contentValues);
            HashMap hashMap = new HashMap(contentValues.size());
            for (Entry entry : contentValues.valueSet()) {
                hashMap.put((String) entry.getKey(), entry.getValue());
            }
            return zza(hashMap);
        }

        public zza zza(HashMap<String, Object> hashMap) {
            com.google.android.gms.common.internal.zzb.zzw(hashMap);
            int zzb = zzb((HashMap) hashMap);
            if (zzb == -1) {
                this.vX.add(hashMap);
            } else {
                this.vX.remove(zzb);
                this.vX.add(zzb, hashMap);
            }
            this.wa = false;
            return this;
        }

        public DataHolder zzfq(int i) {
            return new DataHolder(this, i, null);
        }
    }

    /* renamed from: com.google.android.gms.common.data.DataHolder$1 */
    class C32931 extends zza {
        C32931(String[] strArr, String str) {
            super(strArr, str);
        }

        public zza zza(ContentValues contentValues) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }

        public zza zza(HashMap<String, Object> hashMap) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }
    }

    public static class zzb extends RuntimeException {
        public zzb(String str) {
            super(str);
        }
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mClosed = false;
        this.vV = true;
        this.mVersionCode = i;
        this.vO = strArr;
        this.vQ = cursorWindowArr;
        this.ob = i2;
        this.vR = bundle;
    }

    private DataHolder(zza zza, int i, Bundle bundle) {
        this(zza.vO, zza(zza, -1), i, bundle);
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.vV = true;
        this.mVersionCode = 1;
        this.vO = (String[]) zzab.zzaa(strArr);
        this.vQ = (CursorWindow[]) zzab.zzaa(cursorWindowArr);
        this.ob = i;
        this.vR = bundle;
        zzard();
    }

    public static DataHolder zza(int i, Bundle bundle) {
        return new DataHolder(vW, i, bundle);
    }

    private static CursorWindow[] zza(zza zza, int i) {
        int i2 = 0;
        if (zza.vO.length == 0) {
            return new CursorWindow[0];
        }
        List zzb = (i < 0 || i >= zza.vX.size()) ? zza.vX : zza.vX.subList(0, i);
        int size = zzb.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow);
        cursorWindow.setNumColumns(zza.vO.length);
        int i3 = 0;
        int i4 = 0;
        while (i3 < size) {
            try {
                int i5;
                int i6;
                CursorWindow cursorWindow2;
                if (!cursorWindow.allocRow()) {
                    Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + i3 + ")");
                    cursorWindow = new CursorWindow(false);
                    cursorWindow.setStartPosition(i3);
                    cursorWindow.setNumColumns(zza.vO.length);
                    arrayList.add(cursorWindow);
                    if (!cursorWindow.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) zzb.get(i3);
                boolean z = true;
                for (int i7 = 0; i7 < zza.vO.length && z; i7++) {
                    String str = zza.vO[i7];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z = cursorWindow.putNull(i3, i7);
                    } else if (obj instanceof String) {
                        z = cursorWindow.putString((String) obj, i3, i7);
                    } else if (obj instanceof Long) {
                        z = cursorWindow.putLong(((Long) obj).longValue(), i3, i7);
                    } else if (obj instanceof Integer) {
                        z = cursorWindow.putLong((long) ((Integer) obj).intValue(), i3, i7);
                    } else if (obj instanceof Boolean) {
                        z = cursorWindow.putLong(((Boolean) obj).booleanValue() ? 1 : 0, i3, i7);
                    } else if (obj instanceof byte[]) {
                        z = cursorWindow.putBlob((byte[]) obj, i3, i7);
                    } else if (obj instanceof Double) {
                        z = cursorWindow.putDouble(((Double) obj).doubleValue(), i3, i7);
                    } else if (obj instanceof Float) {
                        z = cursorWindow.putDouble((double) ((Float) obj).floatValue(), i3, i7);
                    } else {
                        String valueOf = String.valueOf(obj);
                        throw new IllegalArgumentException(new StringBuilder((String.valueOf(str).length() + 32) + String.valueOf(valueOf).length()).append("Unsupported object for column ").append(str).append(": ").append(valueOf).toString());
                    }
                }
                if (z) {
                    i5 = i3;
                    i6 = 0;
                    cursorWindow2 = cursorWindow;
                } else if (i4 != 0) {
                    throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                } else {
                    Log.d("DataHolder", "Couldn't populate window data for row " + i3 + " - allocating new window.");
                    cursorWindow.freeLastRow();
                    CursorWindow cursorWindow3 = new CursorWindow(false);
                    cursorWindow3.setStartPosition(i3);
                    cursorWindow3.setNumColumns(zza.vO.length);
                    arrayList.add(cursorWindow3);
                    i5 = i3 - 1;
                    cursorWindow2 = cursorWindow3;
                    i6 = 1;
                }
                i4 = i6;
                cursorWindow = cursorWindow2;
                i3 = i5 + 1;
            } catch (RuntimeException e) {
                RuntimeException runtimeException = e;
                int size2 = arrayList.size();
                while (i2 < size2) {
                    ((CursorWindow) arrayList.get(i2)).close();
                    i2++;
                }
                throw runtimeException;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    public static zza zzb(String[] strArr) {
        return new zza(strArr, null);
    }

    public static DataHolder zzfp(int i) {
        return zza(i, null);
    }

    private void zzh(String str, int i) {
        if (this.vP == null || !this.vP.containsKey(str)) {
            String str2 = "No such column: ";
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i < 0 || i >= this.vT) {
            throw new CursorIndexOutOfBoundsException(i, this.vT);
        }
    }

    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (CursorWindow close : this.vQ) {
                    close.close();
                }
            }
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (this.vV && this.vQ.length > 0 && !isClosed()) {
                String valueOf;
                if (this.vU == null) {
                    String str = "internal object: ";
                    valueOf = String.valueOf(toString());
                    valueOf = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
                } else {
                    valueOf = this.vU.toString();
                }
                Log.e("DataBuffer", new StringBuilder(String.valueOf(valueOf).length() + 161).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (").append(valueOf).append(")").toString());
                close();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public int getCount() {
        return this.vT;
    }

    public int getStatusCode() {
        return this.ob;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }

    public void zza(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zzh(str, i);
        this.vQ[i2].copyStringToBuffer(i, this.vP.getInt(str), charArrayBuffer);
    }

    public Bundle zzaqy() {
        return this.vR;
    }

    public void zzard() {
        int i;
        int i2 = 0;
        this.vP = new Bundle();
        for (i = 0; i < this.vO.length; i++) {
            this.vP.putInt(this.vO[i], i);
        }
        this.vS = new int[this.vQ.length];
        i = 0;
        while (i2 < this.vQ.length) {
            this.vS[i2] = i;
            i += this.vQ[i2].getNumRows() - (i - this.vQ[i2].getStartPosition());
            i2++;
        }
        this.vT = i;
    }

    String[] zzare() {
        return this.vO;
    }

    CursorWindow[] zzarf() {
        return this.vQ;
    }

    public long zzb(String str, int i, int i2) {
        zzh(str, i);
        return this.vQ[i2].getLong(i, this.vP.getInt(str));
    }

    public int zzc(String str, int i, int i2) {
        zzh(str, i);
        return this.vQ[i2].getInt(i, this.vP.getInt(str));
    }

    public String zzd(String str, int i, int i2) {
        zzh(str, i);
        return this.vQ[i2].getString(i, this.vP.getInt(str));
    }

    public boolean zze(String str, int i, int i2) {
        zzh(str, i);
        return Long.valueOf(this.vQ[i2].getLong(i, this.vP.getInt(str))).longValue() == 1;
    }

    public float zzf(String str, int i, int i2) {
        zzh(str, i);
        return this.vQ[i2].getFloat(i, this.vP.getInt(str));
    }

    public int zzfo(int i) {
        int i2 = 0;
        boolean z = i >= 0 && i < this.vT;
        zzab.zzbm(z);
        while (i2 < this.vS.length) {
            if (i < this.vS[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == this.vS.length ? i2 - 1 : i2;
    }

    public byte[] zzg(String str, int i, int i2) {
        zzh(str, i);
        return this.vQ[i2].getBlob(i, this.vP.getInt(str));
    }

    public Uri zzh(String str, int i, int i2) {
        String zzd = zzd(str, i, i2);
        return zzd == null ? null : Uri.parse(zzd);
    }

    public boolean zzhf(String str) {
        return this.vP.containsKey(str);
    }

    public boolean zzi(String str, int i, int i2) {
        zzh(str, i);
        return this.vQ[i2].isNull(i, this.vP.getInt(str));
    }

    public void zzv(Object obj) {
        this.vU = obj;
    }
}
