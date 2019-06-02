package com.beastbikes.framework.android.p103h;

import android.webkit.WebResourceResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: JSONResponse */
/* renamed from: com.beastbikes.framework.android.h.a */
public class C2804a extends WebResourceResponse {
    public C2804a(InputStream inputStream) {
        super("application/json", "utf-8", inputStream);
    }

    public C2804a(String str) {
        this(new ByteArrayInputStream(str.getBytes()));
    }

    public C2804a(JSONObject jSONObject) {
        this(jSONObject.toString());
    }

    public C2804a(Map<?, ?> map) {
        this(new JSONObject(map));
    }
}
