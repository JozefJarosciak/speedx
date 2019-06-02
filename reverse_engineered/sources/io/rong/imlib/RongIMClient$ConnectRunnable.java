package io.rong.imlib;

import io.rong.common.RLog;
import io.rong.imlib.RongIMClient.ConnectCallback;

class RongIMClient$ConnectRunnable implements Runnable {
    ConnectCallback connectCallback;
    String token;

    public RongIMClient$ConnectRunnable(String str, ConnectCallback connectCallback) {
        this.token = str;
        this.connectCallback = connectCallback;
    }

    public void run() {
        RLog.m19419d("RongIMClient", "ConnectRunnable do connect!");
        RongIMClient.connect(this.token, this.connectCallback);
    }
}
