package cn.sharesdk.google;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface GoogleOutIinterface extends IInterface {

    /* renamed from: cn.sharesdk.google.GoogleOutIinterface$a */
    public static abstract class C0626a extends Binder implements GoogleOutIinterface {
        public C0626a() {
            attachInterface(this, "com.google.android.gms.common.internal.IGmsCallbacks");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    Bundle bundle;
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                    int readInt = parcel.readInt();
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    Callback(readInt, readStrongBinder, bundle);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.common.internal.IGmsCallbacks");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void Callback(int i, IBinder iBinder, Bundle bundle) throws RemoteException;
}
