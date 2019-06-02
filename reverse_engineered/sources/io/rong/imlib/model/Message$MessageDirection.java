package io.rong.imlib.model;

public enum Message$MessageDirection {
    SEND(1),
    RECEIVE(2);
    
    private int value;

    private Message$MessageDirection(int i) {
        this.value = 1;
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static Message$MessageDirection setValue(int i) {
        for (Message$MessageDirection message$MessageDirection : values()) {
            if (i == message$MessageDirection.getValue()) {
                return message$MessageDirection;
            }
        }
        return SEND;
    }
}
