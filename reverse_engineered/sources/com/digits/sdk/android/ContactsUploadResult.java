package com.digits.sdk.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ContactsUploadResult implements Parcelable {
    public static final Creator<ContactsUploadResult> CREATOR = new C28711();
    /* renamed from: a */
    public final int f13078a;
    /* renamed from: b */
    public final int f13079b;

    /* renamed from: com.digits.sdk.android.ContactsUploadResult$1 */
    static class C28711 implements Creator<ContactsUploadResult> {
        C28711() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m13844a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m13845a(i);
        }

        /* renamed from: a */
        public ContactsUploadResult m13844a(Parcel parcel) {
            return new ContactsUploadResult(parcel);
        }

        /* renamed from: a */
        public ContactsUploadResult[] m13845a(int i) {
            return new ContactsUploadResult[i];
        }
    }

    ContactsUploadResult(int i, int i2) {
        this.f13078a = i;
        this.f13079b = i2;
    }

    ContactsUploadResult(Parcel parcel) {
        this.f13078a = parcel.readInt();
        this.f13079b = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f13078a);
        parcel.writeInt(this.f13079b);
    }
}
