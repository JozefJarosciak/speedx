package com.baidu.platform.core.p050e;

import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.share.ShareUrlResult;
import com.baidu.platform.base.C1212e;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.e.f */
public class C1351f extends C1212e {
    /* renamed from: a */
    public void mo2683a(String str) {
        ShareUrlResult shareUrlResult = new ShareUrlResult();
        if (str == null) {
            shareUrlResult.error = ERRORNO.RESULT_NOT_FOUND;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject != null) {
                    if (jSONObject.optString("state").equals("success")) {
                        shareUrlResult.setUrl(jSONObject.optString("url"));
                        shareUrlResult.setType(m4535a().ordinal());
                        shareUrlResult.error = ERRORNO.NO_ERROR;
                    } else {
                        shareUrlResult.error = ERRORNO.RESULT_NOT_FOUND;
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
