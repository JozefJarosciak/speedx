package com.tencent.wxop.stat;

import org.json.JSONException;
import org.json.JSONObject;

public class NetworkMonitor {
    /* renamed from: a */
    private long f15804a = 0;
    /* renamed from: b */
    private int f15805b = 0;
    /* renamed from: c */
    private String f15806c = "";
    /* renamed from: d */
    private int f15807d = 0;
    /* renamed from: e */
    private String f15808e = "";

    public String getDomain() {
        return this.f15806c;
    }

    public long getMillisecondsConsume() {
        return this.f15804a;
    }

    public int getPort() {
        return this.f15807d;
    }

    public String getRemoteIp() {
        return this.f15808e;
    }

    public int getStatusCode() {
        return this.f15805b;
    }

    public void setDomain(String str) {
        this.f15806c = str;
    }

    public void setMillisecondsConsume(long j) {
        this.f15804a = j;
    }

    public void setPort(int i) {
        this.f15807d = i;
    }

    public void setRemoteIp(String str) {
        this.f15808e = str;
    }

    public void setStatusCode(int i) {
        this.f15805b = i;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tm", this.f15804a);
            jSONObject.put("st", this.f15805b);
            if (this.f15806c != null) {
                jSONObject.put("dm", this.f15806c);
            }
            jSONObject.put("pt", this.f15807d);
            if (this.f15808e != null) {
                jSONObject.put("rip", this.f15808e);
            }
            jSONObject.put("ts", System.currentTimeMillis() / 1000);
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
