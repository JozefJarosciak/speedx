package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.share.model.ShareMedia.C1473a;

public final class ShareVideo extends ShareMedia {
    public static final Creator<ShareVideo> CREATOR = new C31281();
    /* renamed from: a */
    private final Uri f13833a;

    /* renamed from: com.facebook.share.model.ShareVideo$1 */
    static class C31281 implements Creator<ShareVideo> {
        C31281() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15213a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15214a(i);
        }

        /* renamed from: a */
        public ShareVideo m15213a(Parcel parcel) {
            return new ShareVideo(parcel);
        }

        /* renamed from: a */
        public ShareVideo[] m15214a(int i) {
            return new ShareVideo[i];
        }
    }

    /* renamed from: com.facebook.share.model.ShareVideo$a */
    public static final class C3129a extends C1473a<ShareVideo, C3129a> {
        /* renamed from: a */
        private Uri f13832a;

        /* renamed from: a */
        public C3129a m15217a(@Nullable Uri uri) {
            this.f13832a = uri;
            return this;
        }

        /* renamed from: a */
        public ShareVideo m15219a() {
            return new ShareVideo();
        }

        /* renamed from: a */
        public C3129a m15218a(ShareVideo shareVideo) {
            return shareVideo == null ? this : ((C3129a) super.a(shareVideo)).m15217a(shareVideo.m15222c());
        }

        /* renamed from: b */
        C3129a m15220b(Parcel parcel) {
            return m15218a((ShareVideo) parcel.readParcelable(ShareVideo.class.getClassLoader()));
        }
    }

    private ShareVideo(C3129a c3129a) {
        super(c3129a);
        this.f13833a = c3129a.f13832a;
    }

    ShareVideo(Parcel parcel) {
        super(parcel);
        this.f13833a = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    @Nullable
    /* renamed from: c */
    public Uri m15222c() {
        return this.f13833a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f13833a, 0);
    }

    /* renamed from: b */
    public ShareMedia$Type m15221b() {
        return ShareMedia$Type.VIDEO;
    }
}
