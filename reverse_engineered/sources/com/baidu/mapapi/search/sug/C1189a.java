package com.baidu.mapapi.search.sug;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.sug.a */
final class C1189a implements Creator<SuggestionResult> {
    C1189a() {
    }

    /* renamed from: a */
    public SuggestionResult m4459a(Parcel parcel) {
        return new SuggestionResult(parcel);
    }

    /* renamed from: a */
    public SuggestionResult[] m4460a(int i) {
        return new SuggestionResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4459a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4460a(i);
    }
}
