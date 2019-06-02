package com.avos.avoscloud;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.avos.avoscloud.LogUtil.avlog;

class RealTimeRequestController extends BasicAnalyticsRequestDispatcher {
    static final int REQUEST_END_SEND = 20141010;
    static final int REQUEST_FOR_SEND = 19141010;
    private final Handler reportRequestDispatcher = new Handler(Looper.getMainLooper()) {
        boolean hasRequestForSend = false;
        boolean hasRequestSending = false;

        public void handleMessage(Message message) {
            switch (message.what) {
                case RealTimeRequestController.REQUEST_FOR_SEND /*19141010*/:
                    if (this.hasRequestSending) {
                        this.hasRequestForSend = true;
                        return;
                    }
                    RealTimeRequestController.this.asyncHandler.sendEmptyMessage(RealTimeRequestController.REQUEST_FOR_SEND);
                    this.hasRequestSending = true;
                    return;
                case RealTimeRequestController.REQUEST_END_SEND /*20141010*/:
                    if (this.hasRequestForSend) {
                        RealTimeRequestController.this.asyncHandler.sendEmptyMessage(RealTimeRequestController.REQUEST_FOR_SEND);
                        this.hasRequestForSend = false;
                        this.hasRequestSending = true;
                        return;
                    }
                    this.hasRequestSending = false;
                    return;
                default:
                    return;
            }
        }
    };

    public RealTimeRequestController(AnalyticsRequestDispatcher analyticsRequestDispatcher) {
        super(analyticsRequestDispatcher);
    }

    public void prepareRequest() {
        if (AVOSCloud.isDebugLogEnabled() && AnalyticsImpl.enableDebugLog) {
            avlog.m3506d("sent real time analytics request");
        }
    }

    public void requestToSend(String str) {
        this.reportRequestDispatcher.sendMessage(makeMessage());
    }

    public Message makeMessage() {
        Message message = new Message();
        message.what = REQUEST_FOR_SEND;
        return message;
    }

    public boolean requestValidate(Message message) {
        return super.requestValidate(message) && message.what == REQUEST_FOR_SEND;
    }

    public void onRequestDone() {
        this.reportRequestDispatcher.sendEmptyMessage(REQUEST_END_SEND);
    }

    public void quit() {
    }
}
