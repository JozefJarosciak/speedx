package com.avos.avoscloud;

public enum ReportPolicy {
    REALTIME(0),
    BATCH(1),
    SENDDAILY(4),
    SENDWIFIONLY(5),
    SEND_INTERVAL(6),
    SEND_ON_EXIT(7);
    
    private int value;

    private ReportPolicy(int i) {
        this.value = 0;
        this.value = i;
    }

    public static ReportPolicy valueOf(int i) {
        switch (i) {
            case 0:
                return REALTIME;
            case 1:
                return BATCH;
            case 4:
                return SENDDAILY;
            case 5:
                return SENDWIFIONLY;
            case 6:
                return SEND_INTERVAL;
            case 7:
                return SEND_ON_EXIT;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }
}
