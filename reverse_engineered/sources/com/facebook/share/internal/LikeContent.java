package com.facebook.share.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareModel;

public class LikeContent implements ShareModel {
    public static final Creator<LikeContent> CREATOR = new C30921();
    /* renamed from: a */
    private final String f13763a;
    /* renamed from: b */
    private final String f13764b;

    /* renamed from: com.facebook.share.internal.LikeContent$1 */
    static class C30921 implements Creator<LikeContent> {
        C30921() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15021a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15022a(i);
        }

        /* renamed from: a */
        public LikeContent m15021a(Parcel parcel) {
            return new LikeContent(parcel);
        }

        /* renamed from: a */
        public LikeContent[] m15022a(int i) {
            return new LikeContent[i];
        }
    }

    LikeContent(Parcel parcel) {
        this.f13763a = parcel.readString();
        this.f13764b = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13763a);
        parcel.writeString(this.f13764b);
    }
}
