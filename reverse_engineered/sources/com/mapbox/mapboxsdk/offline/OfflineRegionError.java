package com.mapbox.mapboxsdk.offline;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class OfflineRegionError {
    public static final String REASON_CONNECTION = "REASON_CONNECTION";
    public static final String REASON_NOT_FOUND = "REASON_NOT_FOUND";
    public static final String REASON_OTHER = "REASON_OTHER";
    public static final String REASON_SERVER = "REASON_SERVER";
    public static final String REASON_SUCCESS = "REASON_SUCCESS";
    private String message;
    private String reason;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorReason {
    }

    private OfflineRegionError() {
    }

    public String getReason() {
        return this.reason;
    }

    public String getMessage() {
        return this.message;
    }
}
