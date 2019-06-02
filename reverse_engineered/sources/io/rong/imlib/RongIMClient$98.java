package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IRealTimeLocationListener.Stub;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationErrorCode;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationStatus;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIMClient$98 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ RongIMClient$RealTimeLocationListener val$listener;
    final /* synthetic */ String val$targetId;

    /* renamed from: io.rong.imlib.RongIMClient$98$1 */
    class C53411 extends Stub {
        C53411() {
        }

        public void onStatusChange(final int i) {
            if (RongIMClient$98.this.val$listener != null) {
                RongIMClient.access$1600().post(new Runnable() {
                    public void run() {
                        RongIMClient$98.this.val$listener.onStatusChange(RealTimeLocationStatus.valueOf(i));
                    }
                });
            }
        }

        public void onReceiveLocation(double d, double d2, String str) {
            if (RongIMClient$98.this.val$listener != null) {
                final double d3 = d;
                final double d4 = d2;
                final String str2 = str;
                RongIMClient.access$1600().post(new Runnable() {
                    public void run() {
                        RongIMClient$98.this.val$listener.onReceiveLocation(d3, d4, str2);
                    }
                });
            }
        }

        public void onParticipantsJoin(final String str) {
            if (RongIMClient$98.this.val$listener != null) {
                RongIMClient.access$1600().post(new Runnable() {
                    public void run() {
                        RongIMClient$98.this.val$listener.onParticipantsJoin(str);
                    }
                });
            }
        }

        public void onParticipantsQuit(final String str) {
            if (RongIMClient$98.this.val$listener != null) {
                RongIMClient.access$1600().post(new Runnable() {
                    public void run() {
                        RongIMClient$98.this.val$listener.onParticipantsQuit(str);
                    }
                });
            }
        }

        public void onError(final int i) {
            if (RongIMClient$98.this.val$listener != null) {
                RongIMClient.access$1600().post(new Runnable() {
                    public void run() {
                        RongIMClient$98.this.val$listener.onError(RealTimeLocationErrorCode.valueOf(i));
                    }
                });
            }
        }
    }

    RongIMClient$98(RongIMClient rongIMClient, ConversationType conversationType, String str, RongIMClient$RealTimeLocationListener rongIMClient$RealTimeLocationListener) {
        this.this$0 = rongIMClient;
        this.val$conversationType = conversationType;
        this.val$targetId = str;
        this.val$listener = rongIMClient$RealTimeLocationListener;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).addRealTimeLocationListener(this.val$conversationType.getValue(), this.val$targetId, new C53411());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
