package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareOpenGraphObject extends ShareOpenGraphValueContainer<ShareOpenGraphObject, Object> {
    public static final Creator<ShareOpenGraphObject> CREATOR = new C31231();

    /* renamed from: com.facebook.share.model.ShareOpenGraphObject$1 */
    static class C31231 implements Creator<ShareOpenGraphObject> {
        C31231() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m15182a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m15183a(i);
        }

        /* renamed from: a */
        public ShareOpenGraphObject m15182a(Parcel parcel) {
            return new ShareOpenGraphObject(parcel);
        }

        /* renamed from: a */
        public ShareOpenGraphObject[] m15183a(int i) {
            return new ShareOpenGraphObject[i];
        }
    }

    ShareOpenGraphObject(Parcel parcel) {
        super(parcel);
    }
}
