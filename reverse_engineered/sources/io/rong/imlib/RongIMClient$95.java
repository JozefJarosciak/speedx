package io.rong.imlib;

import io.rong.imlib.OnReceiveMessageListener.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.CustomServiceMode;
import io.rong.imlib.model.Message;

class RongIMClient$95 extends Stub {
    final /* synthetic */ RongIMClient this$0;

    /* renamed from: io.rong.imlib.RongIMClient$95$5 */
    class C53315 extends ResultCallback<Message> {
        C53315() {
        }

        public void onSuccess(Message message) {
            if (RongIMClient.access$3300() != null) {
                RongIMClient.access$3300().onReceived(message, 0);
            }
        }

        public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        }
    }

    /* renamed from: io.rong.imlib.RongIMClient$95$8 */
    class C53348 implements Runnable {
        C53348() {
        }

        public void run() {
            if (RongIMClient.access$3000(RongIMClient$95.this.this$0) != null) {
                RongIMClient.access$3000(RongIMClient$95.this.this$0).onModeChanged(CustomServiceMode.CUSTOM_SERVICE_MODE_HUMAN);
            }
        }
    }

    /* renamed from: io.rong.imlib.RongIMClient$95$9 */
    class C53359 implements Runnable {
        C53359() {
        }

        public void run() {
            if (RongIMClient.access$3000(RongIMClient$95.this.this$0) != null) {
                RongIMClient.access$3000(RongIMClient$95.this.this$0).onModeChanged(CustomServiceMode.CUSTOM_SERVICE_MODE_NO_SERVICE);
            }
        }
    }

    RongIMClient$95(RongIMClient rongIMClient) {
        this.this$0 = rongIMClient;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onReceived(final io.rong.imlib.model.Message r10, int r11, boolean r12) throws android.os.RemoteException {
        /*
        r9 = this;
        r7 = 1;
        r8 = 0;
        r0 = "RongIMClient";
        r1 = "initMessageReceiver : setOnReceiveMessageListener onReceived";
        io.rong.common.RLog.m19419d(r0, r1);
        r0 = io.rong.imlib.TypingMessage.TypingMessageManager.getInstance();
        r0 = r0.onReceiveMessage(r10);
        if (r0 == 0) goto L_0x0015;
    L_0x0013:
        r0 = r7;
    L_0x0014:
        return r0;
    L_0x0015:
        r0 = io.rong.message.ReadReceiptMessage.class;
        r1 = io.rong.imlib.MessageTag.class;
        r0 = r0.getAnnotation(r1);
        r0 = (io.rong.imlib.MessageTag) r0;
        r1 = r10.getObjectName();
        r0 = r0.value();
        r0 = r1.equals(r0);
        if (r0 == 0) goto L_0x0053;
    L_0x002d:
        r0 = r9.this$0;
        r0 = r0.getReadReceipt();
        if (r0 == 0) goto L_0x0053;
    L_0x0035:
        r0 = r10.getContent();
        r0 = (io.rong.message.ReadReceiptMessage) r0;
        r1 = r9.this$0;
        r2 = r10.getConversationType();
        r3 = r10.getTargetId();
        r4 = r0.getLastMessageSendTime();
        r6 = new io.rong.imlib.RongIMClient$95$1;
        r6.<init>(r10);
        r1.updateMessageReceiptStatus(r2, r3, r4, r6);
        r0 = r7;
        goto L_0x0014;
    L_0x0053:
        r0 = io.rong.message.RecallCommandMessage.class;
        r1 = io.rong.imlib.MessageTag.class;
        r0 = r0.getAnnotation(r1);
        r0 = (io.rong.imlib.MessageTag) r0;
        r1 = r10.getObjectName();
        r0 = r0.value();
        r0 = r1.equals(r0);
        if (r0 == 0) goto L_0x007f;
    L_0x006b:
        r0 = r10.getContent();
        r0 = (io.rong.message.RecallCommandMessage) r0;
        r1 = r9.this$0;
        r0 = r0.getMessageUId();
        r2 = new io.rong.imlib.RongIMClient$95$2;
        r2.<init>(r10);
        r1.getMessageByUid(r0, r2);
    L_0x007f:
        r0 = io.rong.message.CSHandShakeResponseMessage.class;
        r1 = io.rong.imlib.MessageTag.class;
        r0 = r0.getAnnotation(r1);
        r0 = (io.rong.imlib.MessageTag) r0;
        r1 = io.rong.message.CSChangeModeResponseMessage.class;
        r2 = io.rong.imlib.MessageTag.class;
        r1 = r1.getAnnotation(r2);
        r1 = (io.rong.imlib.MessageTag) r1;
        r2 = io.rong.message.CSTerminateMessage.class;
        r3 = io.rong.imlib.MessageTag.class;
        r2 = r2.getAnnotation(r3);
        r2 = (io.rong.imlib.MessageTag) r2;
        r3 = io.rong.message.CSUpdateMessage.class;
        r4 = io.rong.imlib.MessageTag.class;
        r3 = r3.getAnnotation(r4);
        r3 = (io.rong.imlib.MessageTag) r3;
        r4 = io.rong.message.CSPullEvaluateMessage.class;
        r5 = io.rong.imlib.MessageTag.class;
        r4 = r4.getAnnotation(r5);
        r4 = (io.rong.imlib.MessageTag) r4;
        r5 = r10.getObjectName();
        r0 = r0.value();
        r0 = r5.equals(r0);
        if (r0 == 0) goto L_0x0211;
    L_0x00bf:
        r0 = r10.getContent();
        r6 = r0;
        r6 = (io.rong.message.CSHandShakeResponseMessage) r6;
        r0 = r6.getCode();
        r1 = r6.getMsg();
        if (r0 != 0) goto L_0x0111;
    L_0x00d0:
        r2 = r9.this$0;
        r2 = io.rong.imlib.RongIMClient.access$3000(r2);
        if (r2 == 0) goto L_0x0111;
    L_0x00d8:
        r2 = r9.this$0;
        r2 = io.rong.imlib.RongIMClient.access$200(r2);
        r2 = r2.getResources();
        r3 = r9.this$0;
        r3 = io.rong.imlib.RongIMClient.access$200(r3);
        r3 = r3.getResources();
        r4 = "rc_init_failed";
        r5 = "string";
        r6 = r9.this$0;
        r6 = io.rong.imlib.RongIMClient.access$200(r6);
        r6 = r6.getPackageName();
        r3 = r3.getIdentifier(r4, r5, r6);
        r2 = r2.getString(r3);
        r3 = io.rong.imlib.RongIMClient.access$1600();
        r4 = new io.rong.imlib.RongIMClient$95$3;
        r4.<init>(r0, r1, r2);
        r3.post(r4);
        r0 = r8;
        goto L_0x0014;
    L_0x0111:
        r0 = new io.rong.imlib.RongIMClient$CustomServiceProfile;
        r1 = r9.this$0;
        r2 = 0;
        r0.<init>(r1);
        r1 = r6.getMode();
        r0.mode = r1;
        r1 = r6.getSid();
        r0.sid = r1;
        r1 = r6.getUid();
        r0.uid = r1;
        r1 = r6.getPid();
        r0.pid = r1;
        r1 = r6.getGroupList();
        r0.groupList = r1;
        r2 = r10.getTargetId();
        r1 = r9.this$0;
        r1 = io.rong.imlib.RongIMClient.access$3200(r1);
        r1.put(r2, r0);
        r1 = r9.this$0;
        r1 = io.rong.imlib.RongIMClient.access$3000(r1);
        if (r1 == 0) goto L_0x018f;
    L_0x014c:
        r1 = new io.rong.imlib.CustomServiceConfig;
        r1.<init>();
        r3 = r6.getCompanyName();
        r1.companyName = r3;
        r3 = r6.isBlack();
        r1.isBlack = r3;
        r3 = r6.getMsg();
        r1.msg = r3;
        r3 = r6.getCompanyIcon();
        r1.companyIcon = r3;
        r3 = r6.getRobotSessionNoEva();
        if (r3 == 0) goto L_0x01f5;
    L_0x016f:
        r3 = r6.getRobotSessionNoEva();
        r4 = "1";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x01f5;
    L_0x017b:
        r1.robotSessionNoEva = r7;
    L_0x017d:
        r3 = r6.getHumanEvaluateList();
        r1.humanEvaluateList = r3;
        r3 = io.rong.imlib.RongIMClient.access$1600();
        r4 = new io.rong.imlib.RongIMClient$95$4;
        r4.<init>(r1);
        r3.post(r4);
    L_0x018f:
        r1 = r6.getRobotLogo();
        r3 = r6.getRobotName();
        r4 = r6.getRobotHelloWord();
        r0.welcome = r4;
        r0.name = r3;
        r0.portrait = r1;
        r0 = r6.getMode();
        r5 = io.rong.imlib.model.CustomServiceMode.CUSTOM_SERVICE_MODE_ROBOT;
        r0 = r0.equals(r5);
        if (r0 != 0) goto L_0x01b9;
    L_0x01ad:
        r0 = r6.getMode();
        r5 = io.rong.imlib.model.CustomServiceMode.CUSTOM_SERVICE_MODE_ROBOT_FIRST;
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x01f8;
    L_0x01b9:
        r0 = android.text.TextUtils.isEmpty(r4);
        if (r0 != 0) goto L_0x01de;
    L_0x01bf:
        r4 = io.rong.message.TextMessage.obtain(r4);
        if (r1 == 0) goto L_0x01d1;
    L_0x01c5:
        r0 = new io.rong.imlib.model.UserInfo;
        r1 = android.net.Uri.parse(r1);
        r0.<init>(r2, r3, r1);
        r4.setUserInfo(r0);
    L_0x01d1:
        r0 = r9.this$0;
        r1 = io.rong.imlib.model.Conversation.ConversationType.CUSTOMER_SERVICE;
        r5 = new io.rong.imlib.RongIMClient$95$5;
        r5.<init>();
        r3 = r2;
        r0.insertMessage(r1, r2, r3, r4, r5);
    L_0x01de:
        r0 = r9.this$0;
        r0 = io.rong.imlib.RongIMClient.access$3000(r0);
        if (r0 == 0) goto L_0x01f2;
    L_0x01e6:
        r0 = io.rong.imlib.RongIMClient.access$1600();
        r1 = new io.rong.imlib.RongIMClient$95$6;
        r1.<init>(r6);
        r0.post(r1);
    L_0x01f2:
        r0 = r8;
        goto L_0x0014;
    L_0x01f5:
        r1.robotSessionNoEva = r8;
        goto L_0x017d;
    L_0x01f8:
        r0 = r6.isRequiredChangMode();
        if (r0 == 0) goto L_0x0204;
    L_0x01fe:
        r0 = r9.this$0;
        r0.switchToHumanMode(r2);
        goto L_0x01f2;
    L_0x0204:
        r0 = io.rong.imlib.RongIMClient.access$1600();
        r1 = new io.rong.imlib.RongIMClient$95$7;
        r1.<init>(r6);
        r0.post(r1);
        goto L_0x01f2;
    L_0x0211:
        r0 = r10.getObjectName();
        r1 = r1.value();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x02e3;
    L_0x021f:
        r0 = r10.getContent();
        r0 = (io.rong.message.CSChangeModeResponseMessage) r0;
        r1 = r9.this$0;
        r1 = io.rong.imlib.RongIMClient.access$3200(r1);
        r2 = r10.getTargetId();
        r1 = r1.get(r2);
        r1 = (io.rong.imlib.RongIMClient$CustomServiceProfile) r1;
        if (r1 == 0) goto L_0x024c;
    L_0x0237:
        r2 = r9.this$0;
        r2 = io.rong.imlib.RongIMClient.access$3000(r2);
        if (r2 == 0) goto L_0x024c;
    L_0x023f:
        r2 = r0.getResult();
        if (r2 != r7) goto L_0x024c;
    L_0x0245:
        r2 = r0.getStatus();
        switch(r2) {
            case 1: goto L_0x024f;
            case 2: goto L_0x0260;
            case 3: goto L_0x02d5;
            default: goto L_0x024c;
        };
    L_0x024c:
        r0 = r8;
        goto L_0x0014;
    L_0x024f:
        r0 = io.rong.imlib.model.CustomServiceMode.CUSTOM_SERVICE_MODE_HUMAN;
        r1.mode = r0;
        r0 = io.rong.imlib.RongIMClient.access$1600();
        r1 = new io.rong.imlib.RongIMClient$95$8;
        r1.<init>();
        r0.post(r1);
        goto L_0x024c;
    L_0x0260:
        r0 = r1.mode;
        if (r0 == 0) goto L_0x024c;
    L_0x0264:
        r0 = r1.mode;
        r2 = io.rong.imlib.model.CustomServiceMode.CUSTOM_SERVICE_MODE_HUMAN;
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x027f;
    L_0x026e:
        r0 = io.rong.imlib.model.CustomServiceMode.CUSTOM_SERVICE_MODE_NO_SERVICE;
        r1.mode = r0;
        r0 = io.rong.imlib.RongIMClient.access$1600();
        r1 = new io.rong.imlib.RongIMClient$95$9;
        r1.<init>();
        r0.post(r1);
        goto L_0x024c;
    L_0x027f:
        r0 = r1.mode;
        r2 = io.rong.imlib.model.CustomServiceMode.CUSTOM_SERVICE_MODE_HUMAN_FIRST;
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x024c;
    L_0x0289:
        r0 = io.rong.imlib.model.CustomServiceMode.CUSTOM_SERVICE_MODE_ROBOT_FIRST;
        r1.mode = r0;
        r0 = io.rong.imlib.RongIMClient.access$1600();
        r2 = new io.rong.imlib.RongIMClient$95$10;
        r2.<init>();
        r0.post(r2);
        r0 = r1.welcome;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x024c;
    L_0x02a1:
        r0 = r1.welcome;
        r4 = io.rong.message.TextMessage.obtain(r0);
        r0 = r1.portrait;
        if (r0 == 0) goto L_0x02bf;
    L_0x02ab:
        r0 = new io.rong.imlib.model.UserInfo;
        r2 = r10.getTargetId();
        r3 = r1.name;
        r1 = r1.portrait;
        r1 = android.net.Uri.parse(r1);
        r0.<init>(r2, r3, r1);
        r4.setUserInfo(r0);
    L_0x02bf:
        r0 = r9.this$0;
        r1 = io.rong.imlib.model.Conversation.ConversationType.CUSTOMER_SERVICE;
        r2 = r10.getTargetId();
        r3 = r10.getTargetId();
        r5 = new io.rong.imlib.RongIMClient$95$11;
        r5.<init>();
        r0.insertMessage(r1, r2, r3, r4, r5);
        goto L_0x024c;
    L_0x02d5:
        r1 = io.rong.imlib.RongIMClient.access$1600();
        r2 = new io.rong.imlib.RongIMClient$95$12;
        r2.<init>(r0);
        r1.post(r2);
        goto L_0x024c;
    L_0x02e3:
        r0 = r10.getObjectName();
        r1 = r2.value();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0371;
    L_0x02f1:
        r0 = r10.getContent();
        r0 = (io.rong.message.CSTerminateMessage) r0;
        r1 = r9.this$0;
        r1 = io.rong.imlib.RongIMClient.access$3200(r1);
        r2 = r10.getTargetId();
        r1 = r1.get(r2);
        r1 = (io.rong.imlib.RongIMClient$CustomServiceProfile) r1;
        r2 = r9.this$0;
        r2 = io.rong.imlib.RongIMClient.access$3000(r2);
        if (r2 == 0) goto L_0x035d;
    L_0x030f:
        if (r1 == 0) goto L_0x035d;
    L_0x0311:
        r2 = r0.getsid();
        r3 = r1.sid;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x035d;
    L_0x031d:
        r2 = r0.getCode();
        if (r2 != 0) goto L_0x0360;
    L_0x0323:
        r0 = r0.getMsg();
        r1 = r9.this$0;
        r1 = io.rong.imlib.RongIMClient.access$200(r1);
        r1 = r1.getResources();
        r2 = r9.this$0;
        r2 = io.rong.imlib.RongIMClient.access$200(r2);
        r2 = r2.getResources();
        r3 = "rc_quit_custom_service";
        r4 = "string";
        r5 = r9.this$0;
        r5 = io.rong.imlib.RongIMClient.access$200(r5);
        r5 = r5.getPackageName();
        r2 = r2.getIdentifier(r3, r4, r5);
        r1 = r1.getString(r2);
        r2 = io.rong.imlib.RongIMClient.access$1600();
        r3 = new io.rong.imlib.RongIMClient$95$13;
        r3.<init>(r0, r1);
        r2.post(r3);
    L_0x035d:
        r0 = r8;
        goto L_0x0014;
    L_0x0360:
        r0 = io.rong.imlib.model.CustomServiceMode.CUSTOM_SERVICE_MODE_ROBOT_FIRST;
        r1.mode = r0;
        r0 = io.rong.imlib.RongIMClient.access$1600();
        r1 = new io.rong.imlib.RongIMClient$95$14;
        r1.<init>();
        r0.post(r1);
        goto L_0x035d;
    L_0x0371:
        r0 = r10.getObjectName();
        r1 = r3.value();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0400;
    L_0x037f:
        r0 = r10.getContent();
        r0 = (io.rong.message.CSUpdateMessage) r0;
        r1 = r9.this$0;
        r1 = io.rong.imlib.RongIMClient.access$3200(r1);
        r2 = r10.getTargetId();
        r1 = r1.get(r2);
        r1 = (io.rong.imlib.RongIMClient$CustomServiceProfile) r1;
        if (r1 == 0) goto L_0x03ad;
    L_0x0397:
        r2 = r0.getSid();
        r1.sid = r2;
        r2 = r0.getServiceStatus();
        r0 = -1;
        r3 = r2.hashCode();
        switch(r3) {
            case 49: goto L_0x03b0;
            case 50: goto L_0x03ba;
            case 51: goto L_0x03c3;
            default: goto L_0x03a9;
        };
    L_0x03a9:
        r7 = r0;
    L_0x03aa:
        switch(r7) {
            case 0: goto L_0x03cd;
            case 1: goto L_0x03de;
            case 2: goto L_0x03ef;
            default: goto L_0x03ad;
        };
    L_0x03ad:
        r0 = r8;
        goto L_0x0014;
    L_0x03b0:
        r3 = "1";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x03a9;
    L_0x03b8:
        r7 = r8;
        goto L_0x03aa;
    L_0x03ba:
        r3 = "2";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x03a9;
    L_0x03c2:
        goto L_0x03aa;
    L_0x03c3:
        r3 = "3";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x03a9;
    L_0x03cb:
        r7 = 2;
        goto L_0x03aa;
    L_0x03cd:
        r0 = io.rong.imlib.model.CustomServiceMode.CUSTOM_SERVICE_MODE_ROBOT;
        r1.mode = r0;
        r0 = io.rong.imlib.RongIMClient.access$1600();
        r1 = new io.rong.imlib.RongIMClient$95$15;
        r1.<init>();
        r0.post(r1);
        goto L_0x03ad;
    L_0x03de:
        r0 = io.rong.imlib.model.CustomServiceMode.CUSTOM_SERVICE_MODE_HUMAN;
        r1.mode = r0;
        r0 = io.rong.imlib.RongIMClient.access$1600();
        r1 = new io.rong.imlib.RongIMClient$95$16;
        r1.<init>();
        r0.post(r1);
        goto L_0x03ad;
    L_0x03ef:
        r0 = io.rong.imlib.model.CustomServiceMode.CUSTOM_SERVICE_MODE_NO_SERVICE;
        r1.mode = r0;
        r0 = io.rong.imlib.RongIMClient.access$1600();
        r1 = new io.rong.imlib.RongIMClient$95$17;
        r1.<init>();
        r0.post(r1);
        goto L_0x03ad;
    L_0x0400:
        r0 = r10.getObjectName();
        r1 = r4.value();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0439;
    L_0x040e:
        r0 = r10.getContent();
        r0 = (io.rong.message.CSPullEvaluateMessage) r0;
        r1 = r9.this$0;
        r1 = io.rong.imlib.RongIMClient.access$3200(r1);
        r2 = r10.getTargetId();
        r1 = r1.get(r2);
        r1 = (io.rong.imlib.RongIMClient$CustomServiceProfile) r1;
        r2 = r0.getMsgId();
        r1.sid = r2;
        r1 = io.rong.imlib.RongIMClient.access$1600();
        r2 = new io.rong.imlib.RongIMClient$95$18;
        r2.<init>(r0);
        r1.post(r2);
        r0 = r8;
        goto L_0x0014;
    L_0x0439:
        r0 = io.rong.imlib.ModuleManager.handleReceivedMessage(r10, r11, r12);
        if (r0 != 0) goto L_0x044f;
    L_0x043f:
        r0 = io.rong.imlib.RongIMClient.access$3300();
        if (r0 == 0) goto L_0x044f;
    L_0x0445:
        r0 = io.rong.imlib.RongIMClient.access$3300();
        r0 = r0.onReceived(r10, r11);
        goto L_0x0014;
    L_0x044f:
        r0 = r8;
        goto L_0x0014;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rong.imlib.RongIMClient$95.onReceived(io.rong.imlib.model.Message, int, boolean):boolean");
    }
}
