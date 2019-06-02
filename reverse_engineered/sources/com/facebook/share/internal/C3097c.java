package com.facebook.share.internal;

import android.support.annotation.Nullable;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OpenGraphJSONUtility */
/* renamed from: com.facebook.share.internal.c */
public final class C3097c {

    /* compiled from: OpenGraphJSONUtility */
    /* renamed from: com.facebook.share.internal.c$a */
    public interface C3096a {
        /* renamed from: a */
        JSONObject mo3723a(SharePhoto sharePhoto);
    }

    /* renamed from: a */
    public static JSONObject m15054a(ShareOpenGraphAction shareOpenGraphAction, C3096a c3096a) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : shareOpenGraphAction.m15176c()) {
            jSONObject.put(str, C3097c.m15052a(shareOpenGraphAction.m15173a(str), c3096a));
        }
        return jSONObject;
    }

    /* renamed from: a */
    private static JSONObject m15055a(ShareOpenGraphObject shareOpenGraphObject, C3096a c3096a) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : shareOpenGraphObject.m15176c()) {
            jSONObject.put(str, C3097c.m15052a(shareOpenGraphObject.m15173a(str), c3096a));
        }
        return jSONObject;
    }

    /* renamed from: a */
    private static JSONArray m15053a(List list, C3096a c3096a) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object a : list) {
            jSONArray.put(C3097c.m15052a(a, c3096a));
        }
        return jSONArray;
    }

    /* renamed from: a */
    public static Object m15052a(@Nullable Object obj, C3096a c3096a) throws JSONException {
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long)) {
            return obj;
        }
        if (obj instanceof SharePhoto) {
            if (c3096a != null) {
                return c3096a.mo3723a((SharePhoto) obj);
            }
            return null;
        } else if (obj instanceof ShareOpenGraphObject) {
            return C3097c.m15055a((ShareOpenGraphObject) obj, c3096a);
        } else {
            if (obj instanceof List) {
                return C3097c.m15053a((List) obj, c3096a);
            }
            throw new IllegalArgumentException("Invalid object found for JSON serialization: " + obj.toString());
        }
    }
}
