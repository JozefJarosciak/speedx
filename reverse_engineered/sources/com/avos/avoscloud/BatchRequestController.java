package com.avos.avoscloud;

import android.os.Message;
import com.avos.avoscloud.LogUtil.avlog;
import java.util.concurrent.atomic.AtomicInteger;

class BatchRequestController extends IntervalRequestController {
    private static int messageCountThreshold = 60;
    private final AtomicInteger messageCount = new AtomicInteger(0);

    BatchRequestController(String str, AnalyticsRequestDispatcher analyticsRequestDispatcher, long j) {
        super(str, analyticsRequestDispatcher, j);
    }

    public int getMessageCount() {
        return this.messageCount.get();
    }

    public int incMessageCount() {
        return this.messageCount.incrementAndGet();
    }

    protected void resetMessageCount() {
        resetMessageCount(0);
    }

    protected void resetMessageCount(int i) {
        this.messageCount.set(i);
    }

    public void prepareRequest() {
        if (AVOSCloud.isDebugLogEnabled() && AnalyticsImpl.enableDebugLog) {
            avlog.m3506d("send stats batch request");
        }
    }

    public void requestToSend(String str) {
        int incMessageCount = incMessageCount();
        Message message = new Message();
        message.obj = str;
        message.what = incMessageCount;
        this.asyncHandler.sendMessage(message);
    }

    public boolean requestValidate(Message message) {
        return super.requestValidate(message) || message.what >= messageCountThreshold;
    }

    public void appraisalSession(AnalyticsSession analyticsSession) {
        if (analyticsSession == null) {
            resetMessageCount();
        } else {
            resetMessageCount(analyticsSession.getMessageCount());
        }
    }
}
