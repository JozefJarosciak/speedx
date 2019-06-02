package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public abstract class ShareMedia implements ShareModel {
    /* renamed from: a */
    private final Bundle f6916a;

    /* renamed from: com.facebook.share.model.ShareMedia$a */
    public static abstract class C1473a<M extends ShareMedia, B extends C1473a> {
        /* renamed from: a */
        private Bundle f6915a = new Bundle();

        @Deprecated
        /* renamed from: a */
        public B m8135a(Bundle bundle) {
            this.f6915a.putAll(bundle);
            return this;
        }

        /* renamed from: a */
        public B m8136a(M m) {
            return m == null ? this : m8135a(m.m8137a());
        }

        /* renamed from: a */
        static List<ShareMedia> m8134a(Parcel parcel) {
            Parcelable[] readParcelableArray = parcel.readParcelableArray(ShareMedia.class.getClassLoader());
            List<ShareMedia> arrayList = new ArrayList(readParcelableArray.length);
            for (Parcelable parcelable : readParcelableArray) {
                arrayList.add((ShareMedia) parcelable);
            }
            return arrayList;
        }
    }

    /* renamed from: b */
    public abstract ShareMedia$Type m8138b();

    protected ShareMedia(C1473a c1473a) {
        this.f6916a = new Bundle(c1473a.f6915a);
    }

    ShareMedia(Parcel parcel) {
        this.f6916a = parcel.readBundle();
    }

    @Deprecated
    /* renamed from: a */
    public Bundle m8137a() {
        return new Bundle(this.f6916a);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.f6916a);
    }
}
