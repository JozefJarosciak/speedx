package com.baidu.mapframework.open.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.baidu.mapframework.open.aidl.C1193b.C1194a;

/* renamed from: com.baidu.mapframework.open.aidl.a */
public interface C1204a extends IInterface {

    /* renamed from: com.baidu.mapframework.open.aidl.a$a */
    public static abstract class C1206a extends Binder implements C1204a {

        /* renamed from: com.baidu.mapframework.open.aidl.a$a$a */
        private static class C1205a implements C1204a {
            /* renamed from: a */
            private IBinder f3555a;

            C1205a(IBinder iBinder) {
                this.f3555a = iBinder;
            }

            /* renamed from: a */
            public void mo2666a(C1193b c1193b) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IMapOpenService");
                    obtain.writeStrongBinder(c1193b != null ? c1193b.asBinder() : null);
                    this.f3555a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f3555a;
            }
        }

        /* renamed from: a */
        public static C1204a m4526a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IMapOpenService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C1204a)) ? new C1205a(iBinder) : (C1204a) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.baidu.mapframework.open.aidl.IMapOpenService");
                    mo2666a(C1194a.m4506b(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.baidu.mapframework.open.aidl.IMapOpenService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* renamed from: a */
    void mo2666a(C1193b c1193b) throws RemoteException;
}
