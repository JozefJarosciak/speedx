package com.avos.avoscloud;

class AnalyticsRequestControllerFactory {
    AnalyticsRequestControllerFactory() {
    }

    static AnalyticsRequestController getAnalyticsRequestController(String str, ReportPolicy reportPolicy, AnalyticsImpl analyticsImpl) {
        switch (reportPolicy) {
            case SEND_INTERVAL:
                return new IntervalRequestController(str, analyticsImpl, AnalyticsUtils.getRequestInterval());
            case REALTIME:
            case SENDWIFIONLY:
                return analyticsImpl.realTimeController;
            case SEND_ON_EXIT:
                return new BoosterRequestController(str, analyticsImpl);
            default:
                return new BatchRequestController(str, analyticsImpl, AnalyticsUtils.getRequestInterval());
        }
    }
}
