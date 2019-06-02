package com.baidu.mapapi.search.share;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.share.a */
final class C1188a implements Creator<ShareUrlResult> {
    C1188a() {
    }

    /* renamed from: a */
    public ShareUrlResult m4457a(Parcel parcel) {
        return new ShareUrlResult(parcel);
    }

    /* renamed from: a */
    public ShareUrlResult[] m4458a(int i) {
        return new ShareUrlResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4457a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4458a(i);
    }
}
