package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.share.model.ShareContent.C3113a;

public final class ShareLinkContent extends ShareContent<ShareLinkContent, C3117a> {
    public static final Creator<ShareLinkContent> CREATOR = new C31161();
    /* renamed from: a */
    private final String f13813a;
    /* renamed from: b */
    private final String f13814b;
    /* renamed from: c */
    private final Uri f13815c;
    /* renamed from: d */
    private final String f13816d;

    /* renamed from: com.facebook.share.model.ShareLinkContent$1 */
    static class C31161 implements Creator<ShareLinkContent> {
        C31161() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15147a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15148a(i);
        }

        /* renamed from: a */
        public ShareLinkContent m15147a(Parcel parcel) {
            return new ShareLinkContent(parcel);
        }

        /* renamed from: a */
        public ShareLinkContent[] m15148a(int i) {
            return new ShareLinkContent[i];
        }
    }

    /* renamed from: com.facebook.share.model.ShareLinkContent$a */
    public static final class C3117a extends C3113a<ShareLinkContent, C3117a> {
        /* renamed from: a */
        private String f13809a;
        /* renamed from: b */
        private String f13810b;
        /* renamed from: c */
        private Uri f13811c;
        /* renamed from: d */
        private String f13812d;

        /* renamed from: a */
        public C3117a m15153a(@Nullable String str) {
            this.f13810b = str;
            return this;
        }

        /* renamed from: b */
        public C3117a m15155b(@Nullable Uri uri) {
            this.f13811c = uri;
            return this;
        }

        /* renamed from: a */
        public ShareLinkContent m15154a() {
            return new ShareLinkContent();
        }
    }

    private ShareLinkContent(C3117a c3117a) {
        super((C3113a) c3117a);
        this.f13813a = c3117a.f13809a;
        this.f13814b = c3117a.f13810b;
        this.f13815c = c3117a.f13811c;
        this.f13816d = c3117a.f13812d;
    }

    ShareLinkContent(Parcel parcel) {
        super(parcel);
        this.f13813a = parcel.readString();
        this.f13814b = parcel.readString();
        this.f13815c = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.f13816d = parcel.readString();
    }

    /* renamed from: a */
    public String m15156a() {
        return this.f13813a;
    }

    @Nullable
    /* renamed from: b */
    public String m15157b() {
        return this.f13814b;
    }

    @Nullable
    /* renamed from: c */
    public Uri m15158c() {
        return this.f13815c;
    }

    @Nullable
    /* renamed from: d */
    public String m15159d() {
        return this.f13816d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f13813a);
        parcel.writeString(this.f13814b);
        parcel.writeParcelable(this.f13815c, 0);
        parcel.writeString(this.f13816d);
    }
}
