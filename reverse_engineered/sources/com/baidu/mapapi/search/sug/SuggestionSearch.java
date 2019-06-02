package com.baidu.mapapi.search.sug;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.C1146i;
import com.baidu.platform.core.p051f.C1356a;
import com.baidu.platform.core.p051f.C1357b;

public class SuggestionSearch extends C1146i {
    /* renamed from: a */
    C1356a f3507a = new C1357b();
    /* renamed from: b */
    private boolean f3508b = false;

    private SuggestionSearch() {
    }

    public static SuggestionSearch newInstance() {
        BMapManager.init();
        return new SuggestionSearch();
    }

    public void destroy() {
        if (!this.f3508b) {
            this.f3508b = true;
            this.f3507a.mo2684a();
            BMapManager.destroy();
        }
    }

    public boolean requestSuggestion(SuggestionSearchOption suggestionSearchOption) {
        if (this.f3507a == null) {
            throw new IllegalStateException("suggestionsearch is null, please call newInstance() first.");
        } else if (suggestionSearchOption != null && suggestionSearchOption.mKeyword != null && suggestionSearchOption.mCity != null) {
            return this.f3507a.mo2711a(suggestionSearchOption);
        } else {
            throw new IllegalArgumentException("option or keyword or city can not be null");
        }
    }

    public void setOnGetSuggestionResultListener(OnGetSuggestionResultListener onGetSuggestionResultListener) {
        if (this.f3507a == null) {
            throw new IllegalStateException("suggestionsearch is null, please call newInstance() first.");
        } else if (onGetSuggestionResultListener == null) {
            throw new IllegalArgumentException("listener can not be null");
        } else {
            this.f3507a.mo2710a(onGetSuggestionResultListener);
        }
    }
}
