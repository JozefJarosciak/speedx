package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IResultCallback.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.ChatRoomInfo;
import io.rong.imlib.model.ChatRoomInfo.ChatRoomMemberOrder;
import io.rong.imlib.model.RemoteModelWrap;

class RongIMClient$73 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ String val$chatRoomId;
    final /* synthetic */ int val$defMemberCount;
    final /* synthetic */ ChatRoomMemberOrder val$order;

    /* renamed from: io.rong.imlib.RongIMClient$73$1 */
    class C53081 extends Stub {
        C53081() {
        }

        public void onComplete(RemoteModelWrap remoteModelWrap) throws RemoteException {
            Object obj = null;
            if (remoteModelWrap != null) {
                obj = (ChatRoomInfo) remoteModelWrap.getContent();
                obj.setMemberOrder(RongIMClient$73.this.val$order);
            }
            if (RongIMClient$73.this.val$callback != null) {
                RongIMClient$73.this.val$callback.onCallback(obj);
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$73.this.val$callback != null) {
                RongIMClient$73.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$73(RongIMClient rongIMClient, ResultCallback resultCallback, String str, int i, ChatRoomMemberOrder chatRoomMemberOrder) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$chatRoomId = str;
        this.val$defMemberCount = i;
        this.val$order = chatRoomMemberOrder;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).getChatRoomInfo(this.val$chatRoomId, this.val$defMemberCount, this.val$order.getValue(), new C53081());
            } catch (RemoteException e) {
                e.printStackTrace();
                if (this.val$callback != null) {
                    this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
