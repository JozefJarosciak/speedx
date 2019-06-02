package cn.jpush.android;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: cn.jpush.android.c */
public abstract class C0427c extends Binder implements C0426b {
    /* renamed from: z */
    private static final String f588z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "y#[|oo>\u001d8~t)\u0007yv~c<R~n,&~~h(";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0027;
    L_0x000b:
        r3 = r0;
        r4 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x0010:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0035;
            case 1: goto L_0x0038;
            case 2: goto L_0x003b;
            case 3: goto L_0x003e;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 31;
    L_0x0019:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0010;
    L_0x0025:
        r1 = r0;
        r0 = r3;
    L_0x0027:
        if (r1 > r2) goto L_0x000b;
    L_0x0029:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f588z = r0;
        return;
    L_0x0035:
        r5 = 26;
        goto L_0x0019;
    L_0x0038:
        r5 = 77;
        goto L_0x0019;
    L_0x003b:
        r5 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        goto L_0x0019;
    L_0x003e:
        r5 = 22;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.c.<clinit>():void");
    }

    public C0427c() {
        attachInterface(this, f588z);
    }

    /* renamed from: a */
    public static C0426b m1255a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface(f588z);
        return (queryLocalInterface == null || !(queryLocalInterface instanceof C0426b)) ? new C0428d(iBinder) : (C0426b) queryLocalInterface;
    }

    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        int i3 = 0;
        boolean z;
        int a;
        boolean a2;
        String readString;
        switch (i) {
            case 1:
                parcel.enforceInterface(f588z);
                int readInt = parcel.readInt();
                long readLong = parcel.readLong();
                if (parcel.readInt() != 0) {
                    z = true;
                }
                mo2210a(readInt, readLong, z, parcel.readFloat(), parcel.readDouble(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 2:
                parcel.enforceInterface(f588z);
                a = mo2207a(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(a);
                return true;
            case 3:
                parcel.enforceInterface(f588z);
                mo2214b(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 4:
                parcel.enforceInterface(f588z);
                long a3 = mo2208a(parcel.readString(), parcel.readLong());
                parcel2.writeNoException();
                parcel2.writeLong(a3);
                return true;
            case 5:
                parcel.enforceInterface(f588z);
                mo2215b(parcel.readString(), parcel.readLong());
                parcel2.writeNoException();
                return true;
            case 6:
                parcel.enforceInterface(f588z);
                a2 = mo2212a(parcel.readString(), parcel.readInt() != 0);
                parcel2.writeNoException();
                if (a2) {
                    i3 = 1;
                }
                parcel2.writeInt(i3);
                return true;
            case 7:
                parcel.enforceInterface(f588z);
                readString = parcel.readString();
                if (parcel.readInt() != 0) {
                    z = true;
                }
                mo2217b(readString, z);
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface(f588z);
                readString = mo2209a(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(readString);
                return true;
            case 9:
                parcel.enforceInterface(f588z);
                mo2216b(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface(f588z);
                a = mo2206a();
                parcel2.writeNoException();
                parcel2.writeInt(a);
                return true;
            case 11:
                parcel.enforceInterface(f588z);
                a2 = mo2211a(parcel.readInt());
                parcel2.writeNoException();
                if (a2) {
                    i3 = 1;
                }
                parcel2.writeInt(i3);
                return true;
            case 12:
                parcel.enforceInterface(f588z);
                a2 = mo2218b(parcel.readInt());
                parcel2.writeNoException();
                if (a2) {
                    i3 = 1;
                }
                parcel2.writeInt(i3);
                return true;
            case 13:
                parcel.enforceInterface(f588z);
                a = mo2213b();
                parcel2.writeNoException();
                parcel2.writeInt(a);
                return true;
            case 1598968902:
                parcel2.writeString(f588z);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
