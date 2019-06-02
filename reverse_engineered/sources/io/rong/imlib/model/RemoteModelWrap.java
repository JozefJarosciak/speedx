package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;

public class RemoteModelWrap implements Parcelable {
    public static final Creator<RemoteModelWrap> CREATOR = new C53821();
    Parcelable model;

    /* renamed from: io.rong.imlib.model.RemoteModelWrap$1 */
    static class C53821 implements Creator<RemoteModelWrap> {
        C53821() {
        }

        public RemoteModelWrap createFromParcel(Parcel parcel) {
            return new RemoteModelWrap(parcel);
        }

        public RemoteModelWrap[] newArray(int i) {
            return new RemoteModelWrap[i];
        }
    }

    public RemoteModelWrap(Parcelable parcelable) {
        this.model = parcelable;
    }

    public RemoteModelWrap(Parcel parcel) {
        Class cls = null;
        try {
            cls = Class.forName(ParcelUtils.readFromParcel(parcel));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.model = ParcelUtils.readFromParcel(parcel, cls);
    }

    public <T extends Parcelable> T getContent() {
        return this.model;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.model.getClass().getName());
        ParcelUtils.writeToParcel(parcel, this.model);
    }
}
