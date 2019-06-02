package com.facebook.appevents;

import android.content.Context;
import android.os.Bundle;
import com.facebook.GraphRequest;
import com.facebook.internal.AppEventsLoggerUtility;
import com.facebook.internal.AppEventsLoggerUtility.GraphAPIActivityType;
import com.facebook.internal.C3000b;
import com.facebook.internal.C3048s;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SessionEventsState */
/* renamed from: com.facebook.appevents.e */
class C2976e {
    /* renamed from: a */
    private List<AppEvent> f13465a = new ArrayList();
    /* renamed from: b */
    private List<AppEvent> f13466b = new ArrayList();
    /* renamed from: c */
    private int f13467c;
    /* renamed from: d */
    private C3000b f13468d;
    /* renamed from: e */
    private String f13469e;
    /* renamed from: f */
    private final int f13470f = 1000;

    public C2976e(C3000b c3000b, String str) {
        this.f13468d = c3000b;
        this.f13469e = str;
    }

    /* renamed from: a */
    public synchronized void m14449a(AppEvent appEvent) {
        if (this.f13465a.size() + this.f13466b.size() >= 1000) {
            this.f13467c++;
        } else {
            this.f13465a.add(appEvent);
        }
    }

    /* renamed from: a */
    public synchronized int m14447a() {
        return this.f13465a.size();
    }

    /* renamed from: a */
    public synchronized void m14450a(boolean z) {
        if (z) {
            this.f13465a.addAll(this.f13466b);
        }
        this.f13466b.clear();
        this.f13467c = 0;
    }

    /* renamed from: a */
    public int m14448a(GraphRequest graphRequest, Context context, boolean z, boolean z2) {
        synchronized (this) {
            int i = this.f13467c;
            this.f13466b.addAll(this.f13465a);
            this.f13465a.clear();
            JSONArray jSONArray = new JSONArray();
            for (AppEvent appEvent : this.f13466b) {
                if (z || !appEvent.getIsImplicit()) {
                    jSONArray.put(appEvent.getJSONObject());
                }
            }
            if (jSONArray.length() == 0) {
                return 0;
            }
            m14445a(graphRequest, context, i, jSONArray, z2);
            return jSONArray.length();
        }
    }

    /* renamed from: b */
    public synchronized List<AppEvent> m14451b() {
        List<AppEvent> list;
        list = this.f13465a;
        this.f13465a = new ArrayList();
        return list;
    }

    /* renamed from: a */
    private void m14445a(GraphRequest graphRequest, Context context, int i, JSONArray jSONArray, boolean z) {
        JSONObject a;
        try {
            a = AppEventsLoggerUtility.m14518a(GraphAPIActivityType.CUSTOM_APP_EVENTS, this.f13468d, this.f13469e, z, context);
            if (this.f13467c > 0) {
                a.put("num_skipped_events", i);
            }
        } catch (JSONException e) {
            a = new JSONObject();
        }
        graphRequest.m14375a(a);
        Bundle e2 = graphRequest.m14380e();
        if (e2 == null) {
            e2 = new Bundle();
        }
        Object jSONArray2 = jSONArray.toString();
        if (jSONArray2 != null) {
            e2.putByteArray("custom_events_file", m14446a((String) jSONArray2));
            graphRequest.m14374a(jSONArray2);
        }
        graphRequest.m14371a(e2);
    }

    /* renamed from: a */
    private byte[] m14446a(String str) {
        byte[] bArr = null;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (Exception e) {
            C3048s.m14753a("Encoding exception: ", e);
        }
        return bArr;
    }
}
