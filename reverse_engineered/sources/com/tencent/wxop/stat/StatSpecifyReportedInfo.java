package com.tencent.wxop.stat;

public class StatSpecifyReportedInfo {
    /* renamed from: a */
    private String f15890a = null;
    /* renamed from: b */
    private String f15891b = null;
    /* renamed from: c */
    private String f15892c = null;
    /* renamed from: d */
    private boolean f15893d = false;
    /* renamed from: e */
    private boolean f15894e = false;

    public String getAppKey() {
        return this.f15890a;
    }

    public String getInstallChannel() {
        return this.f15891b;
    }

    public String getVersion() {
        return this.f15892c;
    }

    public boolean isImportant() {
        return this.f15894e;
    }

    public boolean isSendImmediately() {
        return this.f15893d;
    }

    public void setAppKey(String str) {
        this.f15890a = str;
    }

    public void setImportant(boolean z) {
        this.f15894e = z;
    }

    public void setInstallChannel(String str) {
        this.f15891b = str;
    }

    public void setSendImmediately(boolean z) {
        this.f15893d = z;
    }

    public void setVersion(String str) {
        this.f15892c = str;
    }

    public String toString() {
        return "StatSpecifyReportedInfo [appKey=" + this.f15890a + ", installChannel=" + this.f15891b + ", version=" + this.f15892c + ", sendImmediately=" + this.f15893d + ", isImportant=" + this.f15894e + "]";
    }
}
