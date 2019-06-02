package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class AppInviteContent implements ShareModel {
    public static final Creator<AppInviteContent> CREATOR = new C31111();
    /* renamed from: a */
    private final String f13789a;
    /* renamed from: b */
    private final String f13790b;
    /* renamed from: c */
    private final String f13791c;
    /* renamed from: d */
    private final String f13792d;
    /* renamed from: e */
    private final Destination f13793e;

    /* renamed from: com.facebook.share.model.AppInviteContent$1 */
    static class C31111 implements Creator<AppInviteContent> {
        C31111() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15129a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15130a(i);
        }

        /* renamed from: a */
        public AppInviteContent m15129a(Parcel parcel) {
            return new AppInviteContent(parcel);
        }

        /* renamed from: a */
        public AppInviteContent[] m15130a(int i) {
            return new AppInviteContent[i];
        }
    }

    public static class Builder {

        public enum Destination {
            FACEBOOK("facebook"),
            MESSENGER("messenger");
            
            private final String name;

            private Destination(String str) {
                this.name = str;
            }

            public boolean equalsName(String str) {
                return str == null ? false : this.name.equals(str);
            }

            public String toString() {
                return this.name;
            }
        }
    }

    AppInviteContent(Parcel parcel) {
        this.f13789a = parcel.readString();
        this.f13790b = parcel.readString();
        this.f13792d = parcel.readString();
        this.f13791c = parcel.readString();
        String readString = parcel.readString();
        if (readString.length() > 0) {
            this.f13793e = Destination.valueOf(readString);
        } else {
            this.f13793e = Destination.FACEBOOK;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13789a);
        parcel.writeString(this.f13790b);
        parcel.writeString(this.f13792d);
        parcel.writeString(this.f13791c);
        parcel.writeString(this.f13793e.toString());
    }
}
