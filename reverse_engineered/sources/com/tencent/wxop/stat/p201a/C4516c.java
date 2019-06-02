package com.tencent.wxop.stat.p201a;

import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.c */
public class C4516c {
    /* renamed from: a */
    public String f15910a;
    /* renamed from: b */
    public JSONArray f15911b;
    /* renamed from: c */
    public JSONObject f15912c = null;

    public C4516c(String str, String[] strArr, Properties properties) {
        this.f15910a = str;
        if (properties != null) {
            this.f15912c = new JSONObject(properties);
        } else if (strArr != null) {
            this.f15911b = new JSONArray();
            for (Object put : strArr) {
                this.f15911b.put(put);
            }
        } else {
            this.f15912c = new JSONObject();
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C4516c)) {
            return false;
        }
        return toString().equals(((C4516c) obj).toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append(this.f15910a).append(",");
        if (this.f15911b != null) {
            stringBuilder.append(this.f15911b.toString());
        }
        if (this.f15912c != null) {
            stringBuilder.append(this.f15912c.toString());
        }
        return stringBuilder.toString();
    }
}
