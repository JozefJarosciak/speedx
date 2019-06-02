package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import java.util.Set;

public abstract class ShareOpenGraphValueContainer<P extends ShareOpenGraphValueContainer, E extends C3120a> implements ShareModel {
    /* renamed from: a */
    private final Bundle f13819a;

    /* renamed from: com.facebook.share.model.ShareOpenGraphValueContainer$a */
    public static abstract class C3120a<P extends ShareOpenGraphValueContainer, E extends C3120a> {
        /* renamed from: a */
        private Bundle f13818a = new Bundle();

        /* renamed from: a */
        public E m15167a(String str, @Nullable String str2) {
            this.f13818a.putString(str, str2);
            return this;
        }

        /* renamed from: a */
        public E mo3724a(P p) {
            if (p != null) {
                this.f13818a.putAll(p.m15174b());
            }
            return this;
        }
    }

    protected ShareOpenGraphValueContainer(C3120a<P, E> c3120a) {
        this.f13819a = (Bundle) c3120a.f13818a.clone();
    }

    ShareOpenGraphValueContainer(Parcel parcel) {
        this.f13819a = parcel.readBundle(C3120a.class.getClassLoader());
    }

    @Nullable
    /* renamed from: a */
    public Object m15173a(String str) {
        return this.f13819a.get(str);
    }

    @Nullable
    /* renamed from: b */
    public String m15175b(String str) {
        return this.f13819a.getString(str);
    }

    /* renamed from: b */
    public Bundle m15174b() {
        return (Bundle) this.f13819a.clone();
    }

    /* renamed from: c */
    public Set<String> m15176c() {
        return this.f13819a.keySet();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.f13819a);
    }
}
