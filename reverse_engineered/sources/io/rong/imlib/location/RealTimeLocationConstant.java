package io.rong.imlib.location;

public class RealTimeLocationConstant {

    public enum RealTimeLocationErrorCode {
        RC_REAL_TIME_LOCATION_NOT_INIT(-1, "Not init"),
        RC_REAL_TIME_LOCATION_SUCCESS(0, "Success"),
        RC_REAL_TIME_LOCATION_GPS_DISABLED(1, "GPS disabled"),
        RC_REAL_TIME_LOCATION_CONVERSATION_NOT_SUPPORT(2, "Conversation not support"),
        RC_REAL_TIME_LOCATION_IS_ON_GOING(3, "Real-Time location is on going"),
        RC_REAL_TIME_LOCATION_EXCEED_MAX_PARTICIPANT(4, "Exceed max participants"),
        RC_REAL_TIME_LOCATION_JOIN_FAILURE(5, "Join fail"),
        RC_REAL_TIME_LOCATION_START_FAILURE(6, "Start fail"),
        RC_REAL_TIME_LOCATION_NETWORK_UNAVAILABLE(7, "Network unavailable.");
        
        int code;
        String msg;

        private RealTimeLocationErrorCode(int i, String str) {
            this.code = i;
            this.msg = str;
        }

        public String getMessage() {
            return this.msg;
        }

        public int getValue() {
            return this.code;
        }

        public static RealTimeLocationErrorCode valueOf(int i) {
            for (RealTimeLocationErrorCode realTimeLocationErrorCode : values()) {
                if (realTimeLocationErrorCode.getValue() == i) {
                    return realTimeLocationErrorCode;
                }
            }
            return RC_REAL_TIME_LOCATION_CONVERSATION_NOT_SUPPORT;
        }
    }

    public enum RealTimeLocationStatus {
        RC_REAL_TIME_LOCATION_STATUS_IDLE(0, "Idle state"),
        RC_REAL_TIME_LOCATION_STATUS_INCOMING(1, "Incoming state"),
        RC_REAL_TIME_LOCATION_STATUS_OUTGOING(2, "Outgoing state"),
        RC_REAL_TIME_LOCATION_STATUS_CONNECTED(3, "Connected state");
        
        int code;
        String msg;

        private RealTimeLocationStatus(int i, String str) {
            this.code = i;
            this.msg = str;
        }

        public String getMessage() {
            return this.msg;
        }

        public int getValue() {
            return this.code;
        }

        public static RealTimeLocationStatus valueOf(int i) {
            for (RealTimeLocationStatus realTimeLocationStatus : values()) {
                if (realTimeLocationStatus.getValue() == i) {
                    return realTimeLocationStatus;
                }
            }
            return RC_REAL_TIME_LOCATION_STATUS_IDLE;
        }
    }
}
