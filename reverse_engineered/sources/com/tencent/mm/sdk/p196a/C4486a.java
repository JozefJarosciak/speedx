package com.tencent.mm.sdk.p196a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.p196a.p197a.C4485b;
import com.tencent.mm.sdk.p198b.C4489b;
import com.tencent.mm.sdk.p198b.C4497h;

/* renamed from: com.tencent.mm.sdk.a.a */
public final class C4486a {

    /* renamed from: com.tencent.mm.sdk.a.a$a */
    public static class C4482a {
        /* renamed from: W */
        public String f15798W;
        /* renamed from: X */
        public String f15799X;
        /* renamed from: Y */
        public String f15800Y;
        /* renamed from: Z */
        public Bundle f15801Z;
        public int flags = -1;

        public final String toString() {
            return "targetPkgName:" + this.f15798W + ", targetClassName:" + this.f15799X + ", content:" + this.f15800Y + ", flags:" + this.flags + ", bundle:" + this.f15801Z;
        }
    }

    /* renamed from: a */
    public static boolean m17819a(Context context, C4482a c4482a) {
        if (context == null) {
            C4489b.m17827b("MicroMsg.SDK.MMessageAct", "send fail, invalid argument");
            return false;
        } else if (C4497h.m17842h(c4482a.f15798W)) {
            C4489b.m17827b("MicroMsg.SDK.MMessageAct", "send fail, invalid targetPkgName, targetPkgName = " + c4482a.f15798W);
            return false;
        } else {
            if (C4497h.m17842h(c4482a.f15799X)) {
                c4482a.f15799X = c4482a.f15798W + ".wxapi.WXEntryActivity";
            }
            C4489b.m17830e("MicroMsg.SDK.MMessageAct", "send, targetPkgName = " + c4482a.f15798W + ", targetClassName = " + c4482a.f15799X);
            Intent intent = new Intent();
            intent.setClassName(c4482a.f15798W, c4482a.f15799X);
            if (c4482a.f15801Z != null) {
                intent.putExtras(c4482a.f15801Z);
            }
            String packageName = context.getPackageName();
            intent.putExtra(ConstantsAPI.SDK_VERSION, 587268097);
            intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
            intent.putExtra(ConstantsAPI.CONTENT, c4482a.f15800Y);
            intent.putExtra(ConstantsAPI.CHECK_SUM, C4485b.m17818a(c4482a.f15800Y, 587268097, packageName));
            if (c4482a.flags == -1) {
                intent.addFlags(268435456).addFlags(134217728);
            } else {
                intent.setFlags(c4482a.flags);
            }
            try {
                context.startActivity(intent);
                C4489b.m17830e("MicroMsg.SDK.MMessageAct", "send mm message, intent=" + intent);
                return true;
            } catch (Exception e) {
                C4489b.m17826a("MicroMsg.SDK.MMessageAct", "send fail, ex = %s", e.getMessage());
                return false;
            }
        }
    }
}
