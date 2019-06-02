package com.tencent.mm.sdk.p196a.p197a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.p198b.C4489b;
import com.tencent.mm.sdk.p198b.C4497h;

/* renamed from: com.tencent.mm.sdk.a.a.a */
public final class C4484a {

    /* renamed from: com.tencent.mm.sdk.a.a.a$a */
    public static class C4483a {
        /* renamed from: Y */
        public String f15802Y;
        /* renamed from: Z */
        public Bundle f15803Z;
        public String aa;
        public String ab;
    }

    /* renamed from: a */
    public static boolean m17817a(Context context, C4483a c4483a) {
        if (context == null) {
            C4489b.m17827b("MicroMsg.SDK.MMessage", "send fail, invalid argument");
            return false;
        } else if (C4497h.m17842h(c4483a.ab)) {
            C4489b.m17827b("MicroMsg.SDK.MMessage", "send fail, action is null");
            return false;
        } else {
            String str = null;
            if (!C4497h.m17842h(c4483a.aa)) {
                str = c4483a.aa + ".permission.MM_MESSAGE";
            }
            Intent intent = new Intent(c4483a.ab);
            if (c4483a.f15803Z != null) {
                intent.putExtras(c4483a.f15803Z);
            }
            String packageName = context.getPackageName();
            intent.putExtra(ConstantsAPI.SDK_VERSION, 587268097);
            intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
            intent.putExtra(ConstantsAPI.CONTENT, c4483a.f15802Y);
            intent.putExtra(ConstantsAPI.CHECK_SUM, C4485b.m17818a(c4483a.f15802Y, 587268097, packageName));
            context.sendBroadcast(intent, str);
            C4489b.m17830e("MicroMsg.SDK.MMessage", "send mm message, intent=" + intent + ", perm=" + str);
            return true;
        }
    }
}
