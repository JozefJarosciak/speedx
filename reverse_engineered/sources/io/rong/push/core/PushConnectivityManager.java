package io.rong.push.core;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import io.rong.imlib.common.DeviceUtils;
import io.rong.push.PushConst;
import io.rong.push.PushReceiver;
import io.rong.push.common.RLog;
import io.rong.push.common.stateMachine.StateMachine;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class PushConnectivityManager extends StateMachine {
    private static final int EVENT_CONNECT = 1;
    private static final int EVENT_CONNECTED = 2;
    private static final int EVENT_DISCONNECT = 3;
    private static final int EVENT_DISCONNECTED = 4;
    private static final int EVENT_HEART_BEAT = 5;
    private static final int EVENT_PING_FAILURE = 6;
    private static final int EVENT_PING_SUCCESS = 7;
    private static final int EVENT_REGET_NAVI = 10;
    private static final int EVENT_SEND_REGISTRATION_INFO = 9;
    private static final int EVENT_USER_OPERATION = 8;
    private static final long IP_EXPIRE_TIME = 7200000;
    private static final String TAG = "PushConnectivityManager";
    private int ALARM_PING_REQUEST_CODE = 102;
    private int ALARM_REQUEST_CODE = 101;
    private String appKey;
    private PushConnectivityManager$ConnectedState connectedState = new PushConnectivityManager$ConnectedState(this, null);
    private PushConnectivityManager$ConnectingState connectingState = new PushConnectivityManager$ConnectingState(this, null);
    private String deviceId;
    private PushConnectivityManager$DisconnectedState disconnectedState = new PushConnectivityManager$DisconnectedState(this, null);
    private String enabledPushTypes;
    private boolean initialized = false;
    private Context mContext;
    private int mNavigationRetryTimes = 1;
    private int mReconnectTimes = 1;
    private NetworkType networkType = NetworkType.NONE;
    PushConnectivityManager$PingState pingState = new PushConnectivityManager$PingState(this, null);
    private PushClient pushClient;
    private String serverDomain;

    public enum NetworkType {
        NONE,
        WIFI,
        MOBILE,
        ERROR
    }

    public static PushConnectivityManager getInstance() {
        return PushConnectivityManager$Singleton.sInstance;
    }

    protected PushConnectivityManager() {
        super(TAG);
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public void init(Context context, String str, String str2, String str3) {
        RLog.m8380d(TAG, "init, initialized = " + this.initialized + ", deviceId = " + str + ", appKey = " + str2 + ",enabledPushTypes:" + str3);
        this.mContext = context;
        this.initialized = true;
        this.enabledPushTypes = str3;
        this.appKey = str2;
        this.deviceId = str;
        this.pushClient = new PushClient(DeviceUtils.getPhoneInformation(context), new PushConnectivityManager$1(this, context));
        addState(this.disconnectedState);
        addState(this.connectingState, this.disconnectedState);
        addState(this.connectedState, this.disconnectedState);
        addState(this.pingState, this.disconnectedState);
        setInitialState(this.disconnectedState);
        start();
    }

    public void setServerDomain(String str) {
        RLog.m8382i(TAG, "setServerDomain " + str);
        this.serverDomain = str;
    }

    public void connect() {
        if (isInitialized()) {
            getHandler().sendEmptyMessage(1);
        } else {
            RLog.m8381e(TAG, "connect does not init.");
        }
    }

    public void ping() {
        if (isInitialized()) {
            getHandler().sendEmptyMessage(5);
        } else {
            RLog.m8381e(TAG, "ping: does not init.");
        }
    }

    public void onPingTimeout() {
        if (isInitialized()) {
            getHandler().sendEmptyMessage(6);
        } else {
            RLog.m8381e(TAG, "onPingTimeout: does not init.");
        }
    }

    public void sendRegistrationIDToServer(String str) {
        if (isInitialized()) {
            Message message = new Message();
            message.what = 9;
            message.obj = str;
            getHandler().sendMessage(message);
            return;
        }
        RLog.m8381e(TAG, "sendRegistrationIDToServer: does not init.");
    }

    public void disconnect() {
        if (isInitialized()) {
            cancelHeartbeat();
            getHandler().sendEmptyMessage(3);
            return;
        }
        RLog.m8381e(TAG, "disconnect does not init.");
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    public NetworkType getNetworkType() {
        return this.networkType;
    }

    private void internalConnect() {
        Object navigationAddress = getNavigationAddress();
        if (!TextUtils.isEmpty(navigationAddress)) {
            String[] split = navigationAddress.split(":");
            this.pushClient.connect(split[0], Integer.parseInt(split[1]), this.deviceId, new PushConnectivityManager$2(this));
        }
    }

    private String getNavigationAddress() {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(PushConst.PUSH_SHARE_PREFERENCE, 0);
        String string = sharedPreferences.getString("navigation_ip_value", "");
        String string2 = sharedPreferences.getString("deviceId", "");
        long j = sharedPreferences.getLong("navigation_time", -1);
        long currentTimeMillis = System.currentTimeMillis();
        if (TextUtils.isEmpty(string) || currentTimeMillis > j + IP_EXPIRE_TIME) {
            return getNavigationAddress(string2);
        }
        return string;
    }

    private String getNavigationAddress(String str) {
        HttpURLConnection httpURLConnection;
        String str2;
        BufferedInputStream bufferedInputStream;
        Throwable th;
        HttpURLConnection httpURLConnection2;
        Throwable th2;
        BufferedInputStream bufferedInputStream2 = null;
        String str3 = "";
        try {
            URL url;
            if (TextUtils.isEmpty(this.serverDomain)) {
                url = new URL(PushConst.URL_PUSH_SERVER);
            } else {
                url = new URL("http://" + this.serverDomain + "/navipush.json");
            }
            RLog.m8382i(TAG, "navigation url : " + url);
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) url.openConnection();
            try {
                httpURLConnection3.setConnectTimeout(30000);
                httpURLConnection3.setReadTimeout(30000);
                httpURLConnection3.setUseCaches(false);
                httpURLConnection3.setDoInput(true);
                httpURLConnection3.setDoOutput(true);
                httpURLConnection3.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection3.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write("deviceId=" + str);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                httpURLConnection3.connect();
                int responseCode = httpURLConnection3.getResponseCode();
                if (responseCode >= 100 && responseCode <= 300) {
                    BufferedInputStream bufferedInputStream3 = new BufferedInputStream(httpURLConnection3.getInputStream());
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(256);
                        while (true) {
                            int read = bufferedInputStream3.read();
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(read);
                        }
                        JSONObject jSONObject = new JSONObject(byteArrayOutputStream.toString("UTF-8"));
                        if (jSONObject.optString("code").equalsIgnoreCase("200")) {
                            str3 = jSONObject.optString("server");
                            RLog.m8382i(TAG, "getNavigationAddress.address:" + str3);
                            saveNavigationInfo(str3, System.currentTimeMillis());
                        }
                        bufferedInputStream2 = bufferedInputStream3;
                    } catch (Exception e) {
                        httpURLConnection = httpURLConnection3;
                        str2 = str3;
                        bufferedInputStream = bufferedInputStream3;
                        try {
                            RLog.m8381e(TAG, "Exception when get navigation address.Retry again.");
                            getHandler().sendEmptyMessage(3);
                            if (this.mNavigationRetryTimes > 0) {
                                getHandler().sendEmptyMessageDelayed(10, 2000);
                                this.mNavigationRetryTimes--;
                            }
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2) {
                                }
                            }
                            if (httpURLConnection != null && (httpURLConnection instanceof HttpURLConnection)) {
                                httpURLConnection.disconnect();
                                return str2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            BufferedInputStream bufferedInputStream4 = bufferedInputStream;
                            httpURLConnection2 = httpURLConnection;
                            bufferedInputStream2 = bufferedInputStream4;
                            if (bufferedInputStream2 != null) {
                                try {
                                    bufferedInputStream2.close();
                                } catch (IOException e3) {
                                }
                            }
                            httpURLConnection2.disconnect();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        bufferedInputStream2 = bufferedInputStream3;
                        th2 = th4;
                        httpURLConnection2 = httpURLConnection3;
                        th = th2;
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        if (httpURLConnection2 != null && (httpURLConnection2 instanceof HttpURLConnection)) {
                            httpURLConnection2.disconnect();
                        }
                        throw th;
                    }
                } else if (this.mNavigationRetryTimes > 0) {
                    getHandler().sendEmptyMessageDelayed(10, 2000);
                    this.mNavigationRetryTimes--;
                }
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e4) {
                    }
                }
                if (httpURLConnection3 == null || !(httpURLConnection3 instanceof HttpURLConnection)) {
                    return str3;
                }
                httpURLConnection3.disconnect();
                return str3;
            } catch (Exception e5) {
                String str4 = str3;
                bufferedInputStream = null;
                httpURLConnection = httpURLConnection3;
                str2 = str4;
                RLog.m8381e(TAG, "Exception when get navigation address.Retry again.");
                getHandler().sendEmptyMessage(3);
                if (this.mNavigationRetryTimes > 0) {
                    getHandler().sendEmptyMessageDelayed(10, 2000);
                    this.mNavigationRetryTimes--;
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                return httpURLConnection != null ? str2 : str2;
            } catch (Throwable th42) {
                th2 = th42;
                httpURLConnection2 = httpURLConnection3;
                th = th2;
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                httpURLConnection2.disconnect();
                throw th;
            }
        } catch (Exception e6) {
            str2 = str3;
            bufferedInputStream = null;
            RLog.m8381e(TAG, "Exception when get navigation address.Retry again.");
            getHandler().sendEmptyMessage(3);
            if (this.mNavigationRetryTimes > 0) {
                getHandler().sendEmptyMessageDelayed(10, 2000);
                this.mNavigationRetryTimes--;
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (httpURLConnection != null) {
            }
        } catch (Throwable th5) {
            th = th5;
            httpURLConnection2 = null;
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            httpURLConnection2.disconnect();
            throw th;
        }
    }

    private void saveNavigationInfo(String str, long j) {
        Editor edit = this.mContext.getSharedPreferences(PushConst.PUSH_SHARE_PREFERENCE, 0).edit();
        edit.putString("navigation_ip_value", str);
        edit.putLong("navigation_time", j);
        edit.apply();
    }

    public void startPingTimer() {
        RLog.m8382i(TAG, "startPingTimer, 10s");
        if (isInitialized()) {
            Intent intent = new Intent(this.mContext, PushReceiver.class);
            intent.setAction(PushConst.ACTION_HEARTBEAT);
            intent.putExtra(PushConst.PING_STRING_EXTRA, PushConst.PING_STRING_EXTRA);
            PendingIntent broadcast = PendingIntent.getBroadcast(this.mContext, this.ALARM_PING_REQUEST_CODE, intent, 1);
            AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService("alarm");
            alarmManager.cancel(broadcast);
            alarmManager.set(2, SystemClock.elapsedRealtime() + AbstractComponentTracker.LINGERING_TIMEOUT, broadcast);
            return;
        }
        RLog.m8381e(TAG, "startPingTimer. does not init.");
    }

    public void stopPingTimer() {
        RLog.m8382i(TAG, "stopPingTimer");
        if (isInitialized()) {
            Intent intent = new Intent(this.mContext, PushReceiver.class);
            intent.setAction(PushConst.ACTION_HEARTBEAT);
            intent.putExtra(PushConst.PING_STRING_EXTRA, PushConst.PING_STRING_EXTRA);
            ((AlarmManager) this.mContext.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(this.mContext, this.ALARM_PING_REQUEST_CODE, intent, 1));
            return;
        }
        RLog.m8381e(TAG, "stopPingTimer. does not init.");
    }

    @TargetApi(23)
    public void setNextHeartbeat() {
        RLog.m8382i(TAG, "startHeartbeat");
        if (isInitialized()) {
            Intent intent = new Intent(this.mContext, PushReceiver.class);
            intent.setAction(PushConst.ACTION_HEARTBEAT);
            PendingIntent broadcast = PendingIntent.getBroadcast(this.mContext, this.ALARM_REQUEST_CODE, intent, 1);
            AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService("alarm");
            alarmManager.cancel(broadcast);
            if (VERSION.SDK_INT < 23) {
                alarmManager.set(2, SystemClock.elapsedRealtime() + 240000, broadcast);
                return;
            } else {
                alarmManager.setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + 240000, broadcast);
                return;
            }
        }
        RLog.m8381e(TAG, "setNextHeartbeat. does not init.");
    }

    public void cancelHeartbeat() {
        RLog.m8382i(TAG, "cancelHeartbeat");
        if (isInitialized()) {
            Intent intent = new Intent(this.mContext, PushReceiver.class);
            intent.setAction(PushConst.ACTION_HEARTBEAT);
            ((AlarmManager) this.mContext.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(this.mContext, this.ALARM_REQUEST_CODE, intent, 1));
            stopPingTimer();
            return;
        }
        RLog.m8381e(TAG, "cancelHeartbeat. does not init.");
    }
}
