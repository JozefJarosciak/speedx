package com.tencent.mm.sdk.p196a.p197a;

import com.tencent.mm.p195a.C4481a;

/* renamed from: com.tencent.mm.sdk.a.a.b */
public final class C4485b {
    /* renamed from: a */
    public static byte[] m17818a(String str, int i, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(i);
        stringBuffer.append(str2);
        stringBuffer.append("mMcShCsTr");
        return C4481a.m17816c(stringBuffer.toString().substring(1, 9).getBytes()).getBytes();
    }
}
