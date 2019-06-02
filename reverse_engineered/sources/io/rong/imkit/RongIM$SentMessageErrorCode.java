package io.rong.imkit;

import io.rong.common.RLog;
import org.apache.http.HttpStatus;

public enum RongIM$SentMessageErrorCode {
    UNKNOWN(-1, "Unknown error."),
    NOT_IN_DISCUSSION(21406, "not_in_discussion"),
    NOT_IN_GROUP(22406, "not_in_group"),
    FORBIDDEN_IN_GROUP(22408, "forbidden_in_group"),
    NOT_IN_CHATROOM(23406, "not_in_chatroom"),
    REJECTED_BY_BLACKLIST(HttpStatus.SC_METHOD_NOT_ALLOWED, "rejected by blacklist"),
    NOT_FOLLOWED(29106, "not followed");
    
    private int code;
    private String msg;

    private RongIM$SentMessageErrorCode(int i, String str) {
        this.code = i;
        this.msg = str;
    }

    public int getValue() {
        return this.code;
    }

    public String getMessage() {
        return this.msg;
    }

    public static RongIM$SentMessageErrorCode setValue(int i) {
        for (RongIM$SentMessageErrorCode rongIM$SentMessageErrorCode : values()) {
            if (i == rongIM$SentMessageErrorCode.getValue()) {
                return rongIM$SentMessageErrorCode;
            }
        }
        RLog.m19419d("RongIMClient", "SentMessageErrorCode---ErrorCode---code:" + i);
        return UNKNOWN;
    }
}
