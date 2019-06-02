package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ShareHashtag implements ShareModel {
    public static final Creator<ShareHashtag> CREATOR = new C31141();
    /* renamed from: a */
    private final String f13808a;

    /* renamed from: com.facebook.share.model.ShareHashtag$1 */
    static class C31141 implements Creator<ShareHashtag> {
        C31141() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15139a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15140a(i);
        }

        /* renamed from: a */
        public ShareHashtag m15139a(Parcel parcel) {
            return new ShareHashtag(parcel);
        }

        /* renamed from: a */
        public ShareHashtag[] m15140a(int i) {
            return new ShareHashtag[i];
        }
    }

    /* renamed from: com.facebook.share.model.ShareHashtag$a */
    public static class C3115a {
        /* renamed from: a */
        private String f13807a;

        /* renamed from: a */
        public C3115a m15144a(String str) {
            this.f13807a = str;
            return this;
        }

        /* renamed from: a */
        public C3115a m15143a(ShareHashtag shareHashtag) {
            return shareHashtag == null ? this : m15144a(shareHashtag.m15146a());
        }

        /* renamed from: a */
        C3115a m15142a(Parcel parcel) {
            return m15143a((ShareHashtag) parcel.readParcelable(ShareHashtag.class.getClassLoader()));
        }

        /* renamed from: a */
        public ShareHashtag m15145a() {
            return new ShareHashtag();
        }
    }

    private ShareHashtag(C3115a c3115a) {
        this.f13808a = c3115a.f13807a;
    }

    ShareHashtag(Parcel parcel) {
        this.f13808a = parcel.readString();
    }

    /* renamed from: a */
    public String m15146a() {
        return this.f13808a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13808a);
    }
}
