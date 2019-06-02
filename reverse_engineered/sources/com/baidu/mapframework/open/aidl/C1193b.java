package com.baidu.mapframework.open.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.baidu.mapframework.open.aidl.b */
public interface C1193b extends IInterface {

    /* renamed from: com.baidu.mapframework.open.aidl.b$a */
    public static abstract class C1194a extends Binder implements C1193b {

        /* renamed from: com.baidu.mapframework.open.aidl.b$a$a */
        private static class C1207a implements C1193b {
            /* renamed from: a */
            private IBinder f3556a;

            C1207a(IBinder iBinder) {
                this.f3556a = iBinder;
            }

            /* renamed from: a */
            public void mo2661a(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IOpenClientCallback");
                    obtain.writeStrongBinder(iBinder);
                    this.f3556a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f3556a;
            }
        }

        public C1194a() {
            attachInterface(this, "com.baidu.mapframework.open.aidl.IOpenClientCallback");
        }

        /* renamed from: b */
        public static C1193b m4506b(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IOpenClientCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C1193b)) ? new C1207a(iBinder) : (C1193b) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.baidu.mapframework.open.aidl.IOpenClientCallback");
                    mo2661a(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.baidu.mapframework.open.aidl.IOpenClientCallback");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* renamed from: a */
    void mo2661a(IBinder iBinder) throws RemoteException;
}
