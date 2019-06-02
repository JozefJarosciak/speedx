package com.baidu.mapapi.search.sug;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.sug.SuggestionResult.SuggestionInfo;

/* renamed from: com.baidu.mapapi.search.sug.b */
final class C1190b implements Creator<SuggestionInfo> {
    C1190b() {
    }

    /* renamed from: a */
    public SuggestionInfo m4461a(Parcel parcel) {
        return new SuggestionInfo(parcel);
    }

    /* renamed from: a */
    public SuggestionInfo[] m4462a(int i) {
        return new SuggestionInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4461a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4462a(i);
    }
}
