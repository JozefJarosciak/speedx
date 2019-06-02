package com.beastbikes.android.modules.social.im.p074a;

import android.os.Handler.Callback;
import android.os.Message;
import io.rong.imlib.model.MessageContent;

/* compiled from: RongCloudManager */
/* renamed from: com.beastbikes.android.modules.social.im.a.c$4 */
class c$4 implements Callback {
    /* renamed from: a */
    final /* synthetic */ C1434c f11132a;

    c$4(C1434c c1434c) {
        this.f11132a = c1434c;
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            MessageContent messageContent = (MessageContent) message.obj;
            String str = "";
            if (messageContent.getUserInfo() != null) {
                str = messageContent.getUserInfo().getName();
            }
            C1434c.a(this.f11132a, str, messageContent);
        }
        return false;
    }
}
