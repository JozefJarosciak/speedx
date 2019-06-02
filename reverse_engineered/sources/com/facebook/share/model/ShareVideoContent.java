package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.share.model.SharePhoto.C3125a;
import com.facebook.share.model.ShareVideo.C3129a;

public final class ShareVideoContent extends ShareContent<ShareVideoContent, Object> implements ShareModel {
    public static final Creator<ShareVideoContent> CREATOR = new C31301();
    /* renamed from: a */
    private final String f13834a;
    /* renamed from: b */
    private final String f13835b;
    /* renamed from: c */
    private final SharePhoto f13836c;
    /* renamed from: d */
    private final ShareVideo f13837d;

    /* renamed from: com.facebook.share.model.ShareVideoContent$1 */
    static class C31301 implements Creator<ShareVideoContent> {
        C31301() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15223a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15224a(i);
        }

        /* renamed from: a */
        public ShareVideoContent m15223a(Parcel parcel) {
            return new ShareVideoContent(parcel);
        }

        /* renamed from: a */
        public ShareVideoContent[] m15224a(int i) {
            return new ShareVideoContent[i];
        }
    }

    ShareVideoContent(Parcel parcel) {
        super(parcel);
        this.f13834a = parcel.readString();
        this.f13835b = parcel.readString();
        C3125a b = new C3125a().m15200b(parcel);
        if (b.m15192a() == null && b.m15199b() == null) {
            this.f13836c = null;
        } else {
            this.f13836c = b.m15201c();
        }
        this.f13837d = new C3129a().m15220b(parcel).m15219a();
    }

    @Nullable
    /* renamed from: a */
    public String m15225a() {
        return this.f13834a;
    }

    @Nullable
    /* renamed from: b */
    public String m15226b() {
        return this.f13835b;
    }

    @Nullable
    /* renamed from: c */
    public SharePhoto m15227c() {
        return this.f13836c;
    }

    @Nullable
    /* renamed from: d */
    public ShareVideo m15228d() {
        return this.f13837d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f13834a);
        parcel.writeString(this.f13835b);
        parcel.writeParcelable(this.f13836c, 0);
        parcel.writeParcelable(this.f13837d, 0);
    }
}
