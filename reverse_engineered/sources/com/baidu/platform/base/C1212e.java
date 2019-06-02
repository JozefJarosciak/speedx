package com.baidu.platform.base;

import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.base.e */
public abstract class C1212e {
    /* renamed from: a */
    protected C1211d f3583a;
    /* renamed from: b */
    protected SearchType f3584b;

    /* renamed from: a */
    public SearchType m4535a() {
        return this.f3584b;
    }

    /* renamed from: a */
    public void m4536a(SearchType searchType) {
        this.f3584b = searchType;
    }

    /* renamed from: a */
    public void m4537a(C1211d c1211d) {
        this.f3583a = c1211d;
    }

    /* renamed from: a */
    public abstract void mo2683a(String str);

    /* renamed from: a */
    protected boolean m4539a(String str, SearchResult searchResult, boolean z) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject == null) {
                        searchResult.error = ERRORNO.RESULT_NOT_FOUND;
                        return true;
                    }
                    int optInt = z ? jSONObject.optInt("status") : jSONObject.optInt("status_sp");
                    if (optInt == 0) {
                        return false;
                    }
                    switch (optInt) {
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                            searchResult.error = ERRORNO.PERMISSION_UNFINISHED;
                            return true;
                        case 200:
                        case 230:
                            searchResult.error = ERRORNO.KEY_ERROR;
                            return true;
                        default:
                            searchResult.error = ERRORNO.RESULT_NOT_FOUND;
                            return true;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                searchResult.error = ERRORNO.RESULT_NOT_FOUND;
                return true;
            }
        }
        searchResult.error = ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
        return true;
    }
}
