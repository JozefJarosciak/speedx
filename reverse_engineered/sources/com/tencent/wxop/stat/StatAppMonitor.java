package com.tencent.wxop.stat;

public class StatAppMonitor implements Cloneable {
    public static final int FAILURE_RESULT_TYPE = 1;
    public static final int LOGIC_FAILURE_RESULT_TYPE = 2;
    public static final int SUCCESS_RESULT_TYPE = 0;
    /* renamed from: a */
    private String f15813a = null;
    /* renamed from: b */
    private long f15814b = 0;
    /* renamed from: c */
    private long f15815c = 0;
    /* renamed from: d */
    private int f15816d = 0;
    /* renamed from: e */
    private long f15817e = 0;
    /* renamed from: f */
    private int f15818f = 0;
    /* renamed from: g */
    private int f15819g = 1;

    public StatAppMonitor(String str) {
        this.f15813a = str;
    }

    public StatAppMonitor(String str, int i, int i2, long j, long j2, long j3, int i3) {
        this.f15813a = str;
        this.f15814b = j;
        this.f15815c = j2;
        this.f15816d = i;
        this.f15817e = j3;
        this.f15818f = i2;
        this.f15819g = i3;
    }

    public StatAppMonitor clone() {
        try {
            return (StatAppMonitor) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getInterfaceName() {
        return this.f15813a;
    }

    public long getMillisecondsConsume() {
        return this.f15817e;
    }

    public long getReqSize() {
        return this.f15814b;
    }

    public long getRespSize() {
        return this.f15815c;
    }

    public int getResultType() {
        return this.f15816d;
    }

    public int getReturnCode() {
        return this.f15818f;
    }

    public int getSampling() {
        return this.f15819g;
    }

    public void setInterfaceName(String str) {
        this.f15813a = str;
    }

    public void setMillisecondsConsume(long j) {
        this.f15817e = j;
    }

    public void setReqSize(long j) {
        this.f15814b = j;
    }

    public void setRespSize(long j) {
        this.f15815c = j;
    }

    public void setResultType(int i) {
        this.f15816d = i;
    }

    public void setReturnCode(int i) {
        this.f15818f = i;
    }

    public void setSampling(int i) {
        if (i <= 0) {
            i = 1;
        }
        this.f15819g = i;
    }
}
