package com.tencent.bugly;

import com.tencent.bugly.crashreport.common.info.C4417a;

/* compiled from: BUGLY */
public class BuglyStrategy {
    /* renamed from: a */
    private String f7021a;
    /* renamed from: b */
    private String f7022b;
    /* renamed from: c */
    private String f7023c;
    /* renamed from: d */
    private long f7024d;
    /* renamed from: e */
    private String f7025e;
    /* renamed from: f */
    private String f7026f;
    /* renamed from: g */
    private boolean f7027g = true;
    /* renamed from: h */
    private boolean f7028h = true;
    /* renamed from: i */
    private boolean f7029i = true;
    /* renamed from: j */
    private Class<?> f7030j = null;
    /* renamed from: k */
    private boolean f7031k = true;
    /* renamed from: l */
    private boolean f7032l = true;
    /* renamed from: m */
    private boolean f7033m = true;
    /* renamed from: n */
    private boolean f7034n = false;
    /* renamed from: o */
    private BuglyStrategy$a f7035o;

    public synchronized BuglyStrategy setBuglyLogUpload(boolean z) {
        this.f7031k = z;
        return this;
    }

    public synchronized BuglyStrategy setRecordUserInfoOnceADay(boolean z) {
        this.f7034n = z;
        return this;
    }

    public synchronized BuglyStrategy setUploadProcess(boolean z) {
        this.f7033m = z;
        return this;
    }

    public synchronized boolean isUploadProcess() {
        return this.f7033m;
    }

    public synchronized boolean isBuglyLogUpload() {
        return this.f7031k;
    }

    public synchronized boolean recordUserInfoOnceADay() {
        return this.f7034n;
    }

    public boolean isReplaceOldChannel() {
        return this.f7032l;
    }

    public void setReplaceOldChannel(boolean z) {
        this.f7032l = z;
    }

    public synchronized String getAppVersion() {
        return this.f7021a == null ? C4417a.b().f15295j : this.f7021a;
    }

    public synchronized BuglyStrategy setAppVersion(String str) {
        this.f7021a = str;
        return this;
    }

    public synchronized BuglyStrategy setUserInfoActivity(Class<?> cls) {
        this.f7030j = cls;
        return this;
    }

    public synchronized Class<?> getUserInfoActivity() {
        return this.f7030j;
    }

    public synchronized String getAppChannel() {
        return this.f7022b == null ? C4417a.b().f15297l : this.f7022b;
    }

    public synchronized BuglyStrategy setAppChannel(String str) {
        this.f7022b = str;
        return this;
    }

    public synchronized String getAppPackageName() {
        return this.f7023c == null ? C4417a.b().f15288c : this.f7023c;
    }

    public synchronized BuglyStrategy setAppPackageName(String str) {
        this.f7023c = str;
        return this;
    }

    public synchronized long getAppReportDelay() {
        return this.f7024d;
    }

    public synchronized BuglyStrategy setAppReportDelay(long j) {
        this.f7024d = j;
        return this;
    }

    public synchronized String getLibBuglySOFilePath() {
        return this.f7025e;
    }

    public synchronized BuglyStrategy setLibBuglySOFilePath(String str) {
        this.f7025e = str;
        return this;
    }

    public synchronized String getDeviceID() {
        return this.f7026f;
    }

    public synchronized BuglyStrategy setDeviceID(String str) {
        this.f7026f = str;
        return this;
    }

    public synchronized boolean isEnableNativeCrashMonitor() {
        return this.f7027g;
    }

    public synchronized BuglyStrategy setEnableNativeCrashMonitor(boolean z) {
        this.f7027g = z;
        return this;
    }

    public synchronized BuglyStrategy setEnableUserInfo(boolean z) {
        this.f7029i = z;
        return this;
    }

    public synchronized boolean isEnableUserInfo() {
        return this.f7029i;
    }

    public synchronized boolean isEnableANRCrashMonitor() {
        return this.f7028h;
    }

    public synchronized BuglyStrategy setEnableANRCrashMonitor(boolean z) {
        this.f7028h = z;
        return this;
    }

    public synchronized BuglyStrategy$a getCrashHandleCallback() {
        return this.f7035o;
    }

    public synchronized BuglyStrategy setCrashHandleCallback(BuglyStrategy$a buglyStrategy$a) {
        this.f7035o = buglyStrategy$a;
        return this;
    }
}
