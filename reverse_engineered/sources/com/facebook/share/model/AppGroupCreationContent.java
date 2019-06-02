package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class AppGroupCreationContent implements ShareModel {
    public static final Creator<AppGroupCreationContent> CREATOR = new C31101();
    /* renamed from: a */
    private final String f13786a;
    /* renamed from: b */
    private final String f13787b;
    /* renamed from: c */
    private AppGroupPrivacy f13788c;

    /* renamed from: com.facebook.share.model.AppGroupCreationContent$1 */
    static class C31101 implements Creator<AppGroupCreationContent> {
        C31101() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15127a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15128a(i);
        }

        /* renamed from: a */
        public AppGroupCreationContent m15127a(Parcel parcel) {
            return new AppGroupCreationContent(parcel);
        }

        /* renamed from: a */
        public AppGroupCreationContent[] m15128a(int i) {
            return new AppGroupCreationContent[i];
        }
    }

    public enum AppGroupPrivacy {
        Open,
        Closed
    }

    AppGroupCreationContent(Parcel parcel) {
        this.f13786a = parcel.readString();
        this.f13787b = parcel.readString();
        this.f13788c = (AppGroupPrivacy) parcel.readSerializable();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13786a);
        parcel.writeString(this.f13787b);
        parcel.writeSerializable(this.f13788c);
    }
}
