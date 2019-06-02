package com.avos.avoscloud;

import com.avos.avoscloud.LogUtil.avlog;

class IntervalRequestController extends BoosterRequestController {
    IntervalTimer timer;

    public IntervalRequestController(String str, AnalyticsRequestDispatcher analyticsRequestDispatcher, long j) {
        super(str, analyticsRequestDispatcher);
        final AnalyticsRequestDispatcher analyticsRequestDispatcher2 = analyticsRequestDispatcher;
        this.timer = new IntervalTimer(AnalyticsRequestController.controllerThread.getLooper(), j) {
            public void onTrigger() {
                if (analyticsRequestDispatcher2 != null) {
                    if (AVOSCloud.isDebugLogEnabled()) {
                        avlog.m3506d("send stats interval request");
                    }
                    analyticsRequestDispatcher2.sendRequest();
                }
            }
        };
        this.timer.start();
    }

    public void quit() {
        this.timer.cancel();
    }

    public final void skip() {
        this.timer.skip();
    }

    public void prepareRequest() {
        if (AVOSCloud.isDebugLogEnabled()) {
            avlog.m3506d("send stats interval request for new session");
        }
    }

    public void onRequestDone() {
        this.currentSessionId = this.tmpSessionId;
        skip();
    }
}
