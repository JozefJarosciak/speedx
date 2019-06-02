package io.rong.imkit;

import io.rong.imkit.model.Event.ReadReceiptEvent;
import io.rong.imlib.RongIMClient$ReadReceiptListener;
import io.rong.imlib.model.Message;

class RongIM$5 implements RongIMClient$ReadReceiptListener {
    RongIM$5() {
    }

    public void onReadReceiptReceived(Message message) {
        RongContext.getInstance().getEventBus().post(new ReadReceiptEvent(message));
    }
}
