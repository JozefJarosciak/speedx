package com.baidu.platform.core.p051f;

import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.f.c */
class C1358c implements C1211d<SuggestionResult> {
    /* renamed from: a */
    final /* synthetic */ C1357b f3978a;

    C1358c(C1357b c1357b) {
        this.f3978a = c1357b;
    }

    /* renamed from: a */
    public void m5190a(SuggestionResult suggestionResult) {
        if (this.f3978a.f3977b != null) {
            this.f3978a.f3977b.onGetSuggestionResult(suggestionResult);
        }
    }
}
