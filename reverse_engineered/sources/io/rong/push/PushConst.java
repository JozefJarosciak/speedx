package io.rong.push;

public class PushConst {
    public static final String ACTION_HEARTBEAT = "io.rong.push.intent.action.HEART_BEAT";
    public static final String ACTION_INIT_PUSH = "io.rong.push.intent.action.INIT";
    public static final String ACTION_MI_PUSH_MESSAGE_ARRIVED = "io.rong.push.intent.MI_MESSAGE_ARRIVED";
    public static final String ACTION_MI_PUSH_MESSAGE_CLICKED = "io.rong.push.intent.MI_MESSAGE_CLICKED";
    public static final String ACTION_PUSH_MESSAGE_CLICKED = "io.rong.push.intent.MESSAGE_CLICKED";
    public static final String ACTION_RONG_PUSH_MESSAGE_ARRIVED = "io.rong.push.intent.MESSAGE_ARRIVED";
    public static final String ACTION_SEND_REGISTRATION_INFO = "io.rong.push.intent.action.REGISTRATION_INFO";
    public static final String ACTION_STOP_PUSH = "io.rong.push.intent.action.STOP_PUSH";
    public static final String APP_PUSH_INFORMATION = "RongPushAppConfig";
    public static final int HEARTBEAT_INTERVAL = 240000;
    public static final int PING_ACTION_INTERVAL = 10000;
    public static final String PING_STRING_EXTRA = "PING";
    public static final String PUSH_SHARE_PREFERENCE = "RongPush";
    public static final String URL_PUSH_SERVER = "http://nav.cn.ronghub.com/navipush.json";
}
