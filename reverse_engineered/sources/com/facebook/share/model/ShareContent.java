package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.support.annotation.Nullable;
import com.facebook.share.model.ShareHashtag.C3115a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ShareContent<P extends ShareContent, E extends C3113a> implements ShareModel {
    /* renamed from: a */
    private final Uri f13765a;
    /* renamed from: b */
    private final List<String> f13766b;
    /* renamed from: c */
    private final String f13767c;
    /* renamed from: d */
    private final String f13768d;
    /* renamed from: e */
    private final ShareHashtag f13769e;

    /* renamed from: com.facebook.share.model.ShareContent$a */
    public static abstract class C3113a<P extends ShareContent, E extends C3113a> {
        /* renamed from: a */
        private Uri f13802a;
        /* renamed from: b */
        private List<String> f13803b;
        /* renamed from: c */
        private String f13804c;
        /* renamed from: d */
        private String f13805d;
        /* renamed from: e */
        private ShareHashtag f13806e;

        /* renamed from: a */
        public E m15138a(@Nullable Uri uri) {
            this.f13802a = uri;
            return this;
        }
    }

    protected ShareContent(C3113a c3113a) {
        this.f13765a = c3113a.f13802a;
        this.f13766b = c3113a.f13803b;
        this.f13767c = c3113a.f13804c;
        this.f13768d = c3113a.f13805d;
        this.f13769e = c3113a.f13806e;
    }

    protected ShareContent(Parcel parcel) {
        this.f13765a = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.f13766b = m15025a(parcel);
        this.f13767c = parcel.readString();
        this.f13768d = parcel.readString();
        this.f13769e = new C3115a().m15142a(parcel).m15145a();
    }

    @Nullable
    /* renamed from: h */
    public Uri m15026h() {
        return this.f13765a;
    }

    @Nullable
    /* renamed from: i */
    public List<String> m15027i() {
        return this.f13766b;
    }

    @Nullable
    /* renamed from: j */
    public String m15028j() {
        return this.f13767c;
    }

    @Nullable
    /* renamed from: k */
    public String m15029k() {
        return this.f13768d;
    }

    @Nullable
    /* renamed from: l */
    public ShareHashtag m15030l() {
        return this.f13769e;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f13765a, 0);
        parcel.writeStringList(this.f13766b);
        parcel.writeString(this.f13767c);
        parcel.writeString(this.f13768d);
        parcel.writeParcelable(this.f13769e, 0);
    }

    /* renamed from: a */
    private List<String> m15025a(Parcel parcel) {
        List arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        return arrayList.size() == 0 ? null : Collections.unmodifiableList(arrayList);
    }
}
