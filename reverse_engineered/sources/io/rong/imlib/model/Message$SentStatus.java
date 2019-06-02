package io.rong.imlib.model;

public enum Message$SentStatus {
    SENDING(10),
    FAILED(20),
    SENT(30),
    RECEIVED(40),
    READ(50),
    DESTROYED(60);
    
    private int value;

    private Message$SentStatus(int i) {
        this.value = 1;
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static Message$SentStatus setValue(int i) {
        for (Message$SentStatus message$SentStatus : values()) {
            if (i == message$SentStatus.getValue()) {
                return message$SentStatus;
            }
        }
        return SENDING;
    }
}
