package com.baidu.platform.core.p050e;

import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.share.ShareUrlResult;
import com.baidu.platform.base.C1212e;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.e.d */
public class C1349d extends C1212e {
    /* renamed from: a */
    public void mo2683a(String str) {
        SearchResult shareUrlResult = new ShareUrlResult();
        if (!m4539a(str, shareUrlResult, false)) {
            if (str == null) {
                shareUrlResult.error = ERRORNO.RESULT_NOT_FOUND;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (str != null) {
                    if (jSONObject.optInt("status_sdk") != 0) {
                        shareUrlResult.error = ERRORNO.RESULT_NOT_FOUND;
                    } else {
                        shareUrlResult.setUrl(jSONObject.optString("shorturl"));
                        shareUrlResult.setType(m4535a().ordinal());
                        shareUrlResult.error = ERRORNO.NO_ERROR;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                shareUrlResult.error = ERRORNO.RESULT_NOT_FOUND;
            }
        }
        this.a.mo2687a(shareUrlResult);
    }
}
