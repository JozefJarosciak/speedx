package com.avos.avoscloud;

import android.os.Handler;
import android.os.Message;

abstract class BasicAnalyticsRequestDispatcher extends AnalyticsRequestController {
    Handler asyncHandler;

    BasicAnalyticsRequestDispatcher(final AnalyticsRequestDispatcher analyticsRequestDispatcher) {
        this.asyncHandler = new Handler(controllerThread.getLooper()) {
            public void handleMessage(Message message) {
                if (analyticsRequestDispatcher != null && BasicAnalyticsRequestDispatcher.this.requestValidate(message)) {
                    BasicAnalyticsRequestDispatcher.this.prepareRequest();
                    analyticsRequestDispatcher.sendRequest();
                }
                BasicAnalyticsRequestDispatcher.this.onRequestDone();
            }
        };
    }

    public void prepareRequest() {
    }

    public boolean requestValidate(Message message) {
        return true;
    }

    public void onRequestDone() {
    }
}
