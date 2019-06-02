package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.share.model.ShareOpenGraphAction.C3121a;

public final class ShareOpenGraphContent extends ShareContent<ShareOpenGraphContent, Object> {
    public static final Creator<ShareOpenGraphContent> CREATOR = new C31221();
    /* renamed from: a */
    private final ShareOpenGraphAction f13820a;
    /* renamed from: b */
    private final String f13821b;

    /* renamed from: com.facebook.share.model.ShareOpenGraphContent$1 */
    static class C31221 implements Creator<ShareOpenGraphContent> {
        C31221() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15178a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15179a(i);
        }

        /* renamed from: a */
        public ShareOpenGraphContent m15178a(Parcel parcel) {
            return new ShareOpenGraphContent(parcel);
        }

        /* renamed from: a */
        public ShareOpenGraphContent[] m15179a(int i) {
            return new ShareOpenGraphContent[i];
        }
    }

    ShareOpenGraphContent(Parcel parcel) {
        super(parcel);
        this.f13820a = new C3121a().m15168a(parcel).m15171a();
        this.f13821b = parcel.readString();
    }

    @Nullable
    /* renamed from: a */
    public ShareOpenGraphAction m15180a() {
        return this.f13820a;
    }

    @Nullable
    /* renamed from: b */
    public String m15181b() {
        return this.f13821b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f13820a, 0);
        parcel.writeString(this.f13821b);
    }
}
