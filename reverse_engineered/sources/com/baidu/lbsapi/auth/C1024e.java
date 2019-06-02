package com.baidu.lbsapi.auth;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.lbsapi.auth.e */
class C1024e {
    /* renamed from: a */
    private Context f2279a;
    /* renamed from: b */
    private List<HashMap<String, String>> f2280b = null;
    /* renamed from: c */
    private C1023a<String> f2281c = null;

    /* renamed from: com.baidu.lbsapi.auth.e$a */
    interface C1023a<Result> {
        /* renamed from: a */
        void mo2595a(Result result);
    }

    protected C1024e(Context context) {
        this.f2279a = context;
    }

    /* renamed from: a */
    private List<HashMap<String, String>> m3612a(HashMap<String, String> hashMap, String[] strArr) {
        List<HashMap<String, String>> arrayList = new ArrayList();
        String str;
        if (strArr == null || strArr.length <= 0) {
            HashMap hashMap2 = new HashMap();
            for (String str2 : hashMap.keySet()) {
                str2 = str2.toString();
                hashMap2.put(str2, hashMap.get(str2));
            }
            arrayList.add(hashMap2);
        } else {
            for (Object put : strArr) {
                HashMap hashMap3 = new HashMap();
                for (String str22 : hashMap.keySet()) {
                    str22 = str22.toString();
                    hashMap3.put(str22, hashMap.get(str22));
                }
                hashMap3.put("mcode", put);
                arrayList.add(hashMap3);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m3614a(String str) {
        JSONObject jSONObject;
        if (str == null) {
            str = "";
        }
        try {
            jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                jSONObject.put("status", -1);
            }
        } catch (JSONException e) {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("status", -1);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (this.f2281c != null) {
            this.f2281c.mo2595a(jSONObject != null ? jSONObject.toString() : new JSONObject().toString());
        }
    }

    /* renamed from: a */
    private void m3615a(List<HashMap<String, String>> list) {
        C1017a.m3589a("syncConnect start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        if (list == null || list.size() == 0) {
            C1017a.m3591c("syncConnect failed,params list is null or size is 0");
            return;
        }
        JSONObject jSONObject;
        List arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            C1017a.m3589a("syncConnect resuest " + i + "  start!!!");
            HashMap hashMap = (HashMap) list.get(i);
            C1026g c1026g = new C1026g(this.f2279a);
            if (c1026g.m3623a()) {
                String a = c1026g.m3622a(hashMap);
                if (a == null) {
                    a = "";
                }
                C1017a.m3589a("syncConnect resuest " + i + "  result:" + a);
                arrayList.add(a);
                try {
                    jSONObject = new JSONObject(a);
                    if (jSONObject.has("status") && jSONObject.getInt("status") == 0) {
                        C1017a.m3589a("auth end and break");
                        m3614a(a);
                        return;
                    }
                } catch (JSONException e) {
                    C1017a.m3589a("continue-------------------------------");
                }
            } else {
                C1017a.m3589a("Current network is not available.");
                arrayList.add(ErrorMessage.m3569a("Current network is not available."));
            }
            C1017a.m3589a("syncConnect end");
            i++;
        }
        C1017a.m3589a("--iiiiii:" + i + "<><>paramList.size():" + list.size() + "<><>authResults.size():" + arrayList.size());
        if (list.size() > 0 && i == list.size() && arrayList.size() > 0 && i == arrayList.size() && i - 1 > 0) {
            try {
                jSONObject = new JSONObject((String) arrayList.get(i - 1));
                if (jSONObject.has("status") && jSONObject.getInt("status") != 0) {
                    C1017a.m3589a("i-1 result is not 0,return first result");
                    m3614a((String) arrayList.get(0));
                }
            } catch (JSONException e2) {
                m3614a(ErrorMessage.m3569a("JSONException:" + e2.getMessage()));
            }
        }
    }

    /* renamed from: a */
    protected void m3616a(HashMap<String, String> hashMap, String[] strArr, C1023a<String> c1023a) {
        this.f2280b = m3612a((HashMap) hashMap, strArr);
        this.f2281c = c1023a;
        new Thread(new C1025f(this)).start();
    }
}
