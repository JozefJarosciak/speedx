package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public final class ShareMediaContent extends ShareContent<ShareMediaContent, Object> {
    public static final Creator<ShareMediaContent> CREATOR = new C31181();
    /* renamed from: a */
    private final List<ShareMedia> f13817a;

    /* renamed from: com.facebook.share.model.ShareMediaContent$1 */
    static class C31181 implements Creator<ShareMediaContent> {
        C31181() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15160a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15161a(i);
        }

        /* renamed from: a */
        public ShareMediaContent m15160a(Parcel parcel) {
            return new ShareMediaContent(parcel);
        }

        /* renamed from: a */
        public ShareMediaContent[] m15161a(int i) {
            return new ShareMediaContent[i];
        }
    }

    ShareMediaContent(Parcel parcel) {
        super(parcel);
        this.f13817a = Arrays.asList((ShareMedia[]) parcel.readParcelableArray(ShareMedia.class.getClassLoader()));
    }

    @Nullable
    /* renamed from: a */
    public List<ShareMedia> m15162a() {
        return this.f13817a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelableArray((ShareMedia[]) this.f13817a.toArray(), i);
    }
}
