package io.rong.imlib;

public enum RongIMClient$ConnectionStatusListener$ConnectionStatus {
    NETWORK_UNAVAILABLE(-1, "Network is unavailable."),
    CONNECTED(0, "Connect Success."),
    CONNECTING(1, "Connecting"),
    DISCONNECTED(2, "Disconnected"),
    KICKED_OFFLINE_BY_OTHER_CLIENT(3, "Login on the other device, and be kicked offline."),
    TOKEN_INCORRECT(4, "Token incorrect."),
    SERVER_INVALID(5, "Server invalid.");
    
    private int code;
    private String msg;

    private RongIMClient$ConnectionStatusListener$ConnectionStatus(int i, String str) {
        this.code = i;
        this.msg = str;
    }

    public int getValue() {
        return this.code;
    }

    public String getMessage() {
        return this.msg;
    }
}
