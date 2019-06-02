package com.baidu.platform.core.p051f;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.baidu.platform.base.C1208a;
import com.baidu.platform.base.SearchType;

/* renamed from: com.baidu.platform.core.f.b */
public class C1357b extends C1208a implements C1356a {
    /* renamed from: b */
    private OnGetSuggestionResultListener f3977b = null;

    /* renamed from: a */
    public void mo2684a() {
        this.f3977b = null;
    }

    /* renamed from: a */
    public void mo2710a(OnGetSuggestionResultListener onGetSuggestionResultListener) {
        this.f3977b = onGetSuggestionResultListener;
    }

    /* renamed from: a */
    public boolean mo2711a(SuggestionSearchOption suggestionSearchOption) {
        this.a = new C1359d();
        this.a.m4537a(new C1358c(this));
        this.a.m4536a(SearchType.SUGGESTION_SEARCH_TYPE);
        return m4533a(new C1360e(suggestionSearchOption));
    }
}
