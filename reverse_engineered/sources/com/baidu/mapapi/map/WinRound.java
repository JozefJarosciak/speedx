package com.baidu.mapapi.map;

import ch.qos.logback.core.CoreConstants;

public class WinRound {
    public int bottom = 0;
    public int left = 0;
    public int right = 0;
    public int top = 0;

    public String toString() {
        return "WinRound{left=" + this.left + ", right=" + this.right + ", top=" + this.top + ", bottom=" + this.bottom + CoreConstants.CURLY_RIGHT;
    }
}
