package com.tencent.wxop.stat;

import com.tencent.wxop.stat.common.C4539k;
import com.tencent.wxop.stat.common.C4545q;
import org.json.JSONException;
import org.json.JSONObject;

public class StatAccount {
    public static final int CUSTOM_TYPE = 7;
    public static final int DEFAULT_TYPE = 0;
    public static final int EMAIL_TYPE = 6;
    public static final int PHONE_NUM_TYPE = 5;
    public static final int QQ_NUM_TYPE = 1;
    public static final int QQ_OPENID_TYPE = 3;
    public static final int WECHAT_ID_TYPE = 2;
    public static final int WECHAT_OPENID_TYPE = 4;
    /* renamed from: a */
    private String f15809a = "";
    /* renamed from: b */
    private int f15810b = 0;
    /* renamed from: c */
    private String f15811c = "";
    /* renamed from: d */
    private String f15812d = "";

    public StatAccount(String str) {
        this.f15809a = str;
    }

    public StatAccount(String str, int i) {
        this.f15809a = str;
        this.f15810b = i;
    }

    public String getAccount() {
        return this.f15809a;
    }

    public int getAccountType() {
        return this.f15810b;
    }

    public String getExt() {
        return this.f15811c;
    }

    public String getExt1() {
        return this.f15812d;
    }

    public void setAccount(String str) {
        this.f15809a = str;
    }

    public void setAccountType(int i) {
        this.f15810b = i;
    }

    public void setExt(String str) {
        this.f15811c = str;
    }

    public void setExt1(String str) {
        this.f15812d = str;
    }

    public String toJsonString() {
        JSONObject jSONObject = new JSONObject();
        if (C4539k.m18056c(this.f15809a)) {
            try {
                C4545q.m18100a(jSONObject, "a", this.f15809a);
                jSONObject.put("t", this.f15810b);
                C4545q.m18100a(jSONObject, "e", this.f15811c);
                C4545q.m18100a(jSONObject, "e1", this.f15812d);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    public String toString() {
        return "StatAccount [account=" + this.f15809a + ", accountType=" + this.f15810b + ", ext=" + this.f15811c + ", ext1=" + this.f15812d + "]";
    }
}
