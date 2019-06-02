package com.avos.avoscloud;

import android.os.Message;
import com.avos.avoscloud.LogUtil.avlog;

class BoosterRequestController extends BasicAnalyticsRequestDispatcher {
    String currentSessionId;
    String tmpSessionId;

    public BoosterRequestController(String str, AnalyticsRequestDispatcher analyticsRequestDispatcher) {
        super(analyticsRequestDispatcher);
        this.currentSessionId = str;
    }

    private Message makeMessage(String str) {
        Message message = new Message();
        message.obj = str;
        return message;
    }

    public void requestToSend(String str) {
        this.asyncHandler.sendMessage(makeMessage(str));
    }

    public void quit() {
    }

    public void prepareRequest() {
        if (AVOSCloud.isDebugLogEnabled() && AnalyticsImpl.enableDebugLog) {
            avlog.m3506d("sent analytics request on booster");
        }
    }

    public boolean requestValidate(Message message) {
        this.tmpSessionId = (String) message.obj;
        return (AVUtils.isBlankString(this.currentSessionId) || this.currentSessionId.equals(this.tmpSessionId) || !super.requestValidate(message)) ? false : true;
    }

    public void onRequestDone() {
        this.currentSessionId = this.tmpSessionId;
    }
}
