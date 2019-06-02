package com.baidu.platform.comapi.map;

import android.os.Message;
import com.baidu.mapapi.UIMsg.m_AppUI;

/* renamed from: com.baidu.platform.comapi.map.z */
class C1268z {
    /* renamed from: a */
    private static final String f3842a = C1268z.class.getSimpleName();
    /* renamed from: b */
    private C1131y f3843b;

    C1268z() {
    }

    /* renamed from: a */
    void m4803a(Message message) {
        if (message.what == m_AppUI.V_WM_VDATAENGINE) {
            switch (message.arg1) {
                case -1:
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 12:
                case 101:
                case 102:
                    if (this.f3843b != null) {
                        this.f3843b.mo2647a(message.arg1, message.arg2);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    void m4804a(C1131y c1131y) {
        this.f3843b = c1131y;
    }

    /* renamed from: b */
    void m4805b(C1131y c1131y) {
        this.f3843b = null;
    }
}
