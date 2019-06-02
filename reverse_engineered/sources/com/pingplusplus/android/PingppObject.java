package com.pingplusplus.android;

public class PingppObject {
    public String currentChannel;
    public PingppDataCollection dataCollection;
    public boolean isOne;
    public C4293j pingppQPayHandler;
    public C4307x pingppWxHandler;
    public String qpayScheme;
    public C4289f sdkType;
    public String wxAppId;
    public int wxErrCode;

    private PingppObject() {
        this.wxAppId = null;
        this.currentChannel = null;
        this.pingppWxHandler = null;
        this.pingppQPayHandler = null;
        this.wxErrCode = -10;
        this.sdkType = C4289f.SDK;
        this.qpayScheme = null;
    }

    public static PingppObject getInstance() {
        return C4292i.f14968a;
    }
}
