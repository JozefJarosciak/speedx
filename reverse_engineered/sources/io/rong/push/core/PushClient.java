package io.rong.push.core;

import com.baidu.mapapi.UIMsg.m_AppUI;
import io.rong.imlib.common.BuildVar;
import io.rong.push.common.RLog;
import io.rong.push.core.PushProtocalStack.ConnectMessage;
import io.rong.push.core.PushProtocalStack.Message;
import io.rong.push.core.PushProtocalStack.MessageInputStream;
import io.rong.push.core.PushProtocalStack.MessageOutputStream;
import io.rong.push.core.PushProtocalStack.PingReqMessage;
import io.rong.push.core.PushProtocalStack.PublishMessage;
import io.rong.push.core.PushProtocalStack.QueryAckMessage;
import io.rong.push.core.PushProtocalStack.QueryAckMessage.QueryStatus;
import io.rong.push.core.PushProtocalStack.QueryMessage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

class PushClient {
    private static final String TAG = "PushClient";
    private ConnectStatusCallback connectCallback;
    private String deviceInfo;
    private MessageInputStream in;
    private ClientListener listener;
    public OutputStream os;
    private MessageOutputStream out;
    private QueryCallback queryCallback;
    private PushReader reader;
    private boolean running;
    private Socket socket;

    public interface ClientListener {
        void onDisConnected();

        void onMessageArrived(PublishMessage publishMessage);

        void onPingFailure();

        void onPingSuccess();
    }

    public interface ConnectStatusCallback {
        void onConnected();

        void onError(IOException iOException);
    }

    private class PushReader extends Thread {
        private PushReader() {
        }

        public void run() {
            Message message = null;
            while (PushClient.this.running) {
                try {
                    Thread.sleep(100);
                    if (PushClient.this.in != null) {
                        message = PushClient.this.in.readMessage();
                    }
                    if (message != null) {
                        PushClient.this.handleMessage(message);
                    }
                } catch (Exception e) {
                    RLog.e(PushClient.TAG, "PushReader IOException. " + e.getMessage());
                    e.printStackTrace();
                    if (PushClient.this.listener != null) {
                        PushClient.this.listener.onDisConnected();
                        return;
                    }
                    return;
                }
            }
        }
    }

    public interface QueryCallback {
        void onFailure();

        void onSuccess(String str);
    }

    public enum QueryMethod {
        GET_PUSH_TYPE("getPushType"),
        SET_TOKEN("setToken");
        
        private String methodName;

        private QueryMethod(String str) {
            this.methodName = str;
        }

        public String getMethodName() {
            return this.methodName;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void disconnect() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0032 in list [B:10:0x002d]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = "PushClient";
        r1 = "disconnect";
        io.rong.push.common.RLog.d(r0, r1);
        r0 = r3.reader;	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        if (r0 == 0) goto L_0x0016;	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
    L_0x000b:
        r0 = r3.reader;	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r0.interrupt();	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r0 = 0;	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r3.running = r0;	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r0 = 0;	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r3.reader = r0;	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
    L_0x0016:
        r0 = r3.in;	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r0.close();	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r0 = r3.os;	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r0.close();	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r0 = r3.socket;	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        if (r0 == 0) goto L_0x0029;	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
    L_0x0024:
        r0 = r3.socket;	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r0.close();	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
    L_0x0029:
        r0 = r3.listener;
        if (r0 == 0) goto L_0x0032;
    L_0x002d:
        r0 = r3.listener;
        r0.onDisConnected();
    L_0x0032:
        return;
    L_0x0033:
        r0 = move-exception;
        r1 = "PushClient";	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r2 = "disconnect IOException";	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        io.rong.push.common.RLog.e(r1, r2);	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r0.printStackTrace();	 Catch:{ IOException -> 0x0033, all -> 0x0048 }
        r0 = r3.listener;
        if (r0 == 0) goto L_0x0032;
    L_0x0042:
        r0 = r3.listener;
        r0.onDisConnected();
        goto L_0x0032;
    L_0x0048:
        r0 = move-exception;
        r1 = r3.listener;
        if (r1 == 0) goto L_0x0052;
    L_0x004d:
        r1 = r3.listener;
        r1.onDisConnected();
    L_0x0052:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rong.push.core.PushClient.disconnect():void");
    }

    public PushClient(String str, ClientListener clientListener) {
        this.listener = clientListener;
        this.deviceInfo = str;
    }

    public void connect(String str, int i, String str2, ConnectStatusCallback connectStatusCallback) {
        RLog.d(TAG, "connect, deviceId = " + str2 + ", host = " + str + ", port = " + i);
        try {
            this.socket = new Socket();
            this.socket.connect(new InetSocketAddress(str, i), m_AppUI.MSG_APP_SAVESCREEN);
            this.in = new MessageInputStream(this.socket.getInputStream());
            this.os = this.socket.getOutputStream();
            this.out = new MessageOutputStream(this.os);
            this.connectCallback = connectStatusCallback;
            new ConnectMessage(str2, true, 300).setWill("clientInfo", String.format("%s-%s-%s", new Object[]{"AndroidPush", this.deviceInfo, BuildVar.SDK_VERSION}));
            this.out.writeMessage(new ConnectMessage(str2, true, 300));
            this.reader = new PushReader();
            this.running = true;
            this.reader.start();
        } catch (Exception e) {
            RLog.e(TAG, "connect IOException");
            e.printStackTrace();
            if (connectStatusCallback != null) {
                connectStatusCallback.onError(null);
            }
        }
    }

    public void ping() {
        try {
            if (this.socket != null && this.socket.isConnected() && this.out != null) {
                this.out.writeMessage(new PingReqMessage());
            } else if (this.listener != null) {
                this.listener.onPingFailure();
            }
        } catch (IOException e) {
            RLog.e(TAG, "ping IOException");
            e.printStackTrace();
            if (this.listener != null) {
                this.listener.onPingFailure();
            }
        }
    }

    public void query(QueryMethod queryMethod, String str, String str2, QueryCallback queryCallback) {
        RLog.d(TAG, "query. topic:" + queryMethod.getMethodName() + ", queryInfo:" + str);
        this.queryCallback = queryCallback;
        try {
            if (this.socket != null && this.socket.isConnected() && this.out != null) {
                this.out.writeMessage(new QueryMessage(queryMethod.getMethodName(), str, str2));
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.queryCallback.onFailure();
        }
    }

    public void reset() {
        RLog.d(TAG, "reset");
        try {
            if (this.reader != null) {
                this.reader.interrupt();
                this.running = false;
                this.reader = null;
            }
            if (this.socket != null) {
                this.socket.close();
                this.socket = null;
            }
        } catch (IOException e) {
            RLog.e(TAG, "reset IOException");
            e.printStackTrace();
        }
    }

    private void handleMessage(Message message) throws IOException {
        if (message != null) {
            RLog.d(TAG, "handleMessage, msg type = " + message.getType());
            switch (message.getType()) {
                case CONNACK:
                    if (this.connectCallback != null) {
                        this.connectCallback.onConnected();
                        return;
                    }
                    return;
                case PINGRESP:
                    if (this.listener != null) {
                        this.listener.onPingSuccess();
                        return;
                    }
                    return;
                case QUERYACK:
                    QueryAckMessage queryAckMessage = (QueryAckMessage) message;
                    int status = queryAckMessage.getStatus();
                    RLog.d(TAG, "queryAck status:" + status + "content:" + queryAckMessage.getDataAsString());
                    if (this.queryCallback == null) {
                        return;
                    }
                    if (status == QueryStatus.STATUS_OK.get()) {
                        this.queryCallback.onSuccess(queryAckMessage.getDataAsString());
                        return;
                    } else {
                        this.queryCallback.onFailure();
                        return;
                    }
                case PUBLISH:
                    if (this.listener != null) {
                        this.listener.onMessageArrived((PublishMessage) message);
                        return;
                    }
                    return;
                case DISCONNECT:
                    if (this.listener != null) {
                        this.listener.onDisConnected();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
