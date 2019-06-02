package com.avos.avoscloud;

import android.os.HandlerThread;
import android.os.Message;

abstract class AnalyticsRequestController {
    static HandlerThread controllerThread = new HandlerThread("com.avos.avoscloud.AnalyticsRequestController");

    interface AnalyticsRequestDispatcher {
        void sendRequest();
    }

    AnalyticsRequestController() {
    }

    static {
        controllerThread.start();
    }

    public void requestToSend(String str) {
    }

    public void quit() {
    }

    public boolean requestValidate(Message message) {
        return true;
    }

    public void onRequestDone() {
    }

    public void appraisalSession(AnalyticsSession analyticsSession) {
    }
}
