package com.alipay.sdk.auth;

import android.app.Activity;

/* renamed from: com.alipay.sdk.auth.i */
final class C0837i implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Activity f2015a;
    /* renamed from: b */
    final /* synthetic */ StringBuilder f2016b;
    /* renamed from: c */
    final /* synthetic */ APAuthInfo f2017c;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0057 in list [B:38:0x0106]
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
        r5 = this;
        r0 = 0;
        r2 = new com.alipay.sdk.packet.impl.a;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2.<init>();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = 0;
        r3 = r5.f2015a;	 Catch:{ Throwable -> 0x011e }
        r4 = r5.f2016b;	 Catch:{ Throwable -> 0x011e }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x011e }
        r1 = r2.mo2428a(r3, r4);	 Catch:{ Throwable -> 0x011e }
    L_0x0013:
        r2 = com.alipay.sdk.auth.C0836h.f2013c;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        if (r2 == 0) goto L_0x0023;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x0019:
        r2 = com.alipay.sdk.auth.C0836h.f2013c;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2.m3499b();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        com.alipay.sdk.auth.C0836h.f2013c = null;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x0023:
        if (r1 != 0) goto L_0x0058;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x0025:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0.<init>();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r5.f2017c;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r1.getRedirectUri();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = "?resultCode=202";	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        com.alipay.sdk.auth.C0836h.f2014d = r0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r5.f2015a;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = com.alipay.sdk.auth.C0836h.f2014d;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        com.alipay.sdk.auth.C0836h.m3225a(r0, r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = com.alipay.sdk.auth.C0836h.f2013c;
        if (r0 == 0) goto L_0x0057;
    L_0x0050:
        r0 = com.alipay.sdk.auth.C0836h.f2013c;
        r0.m3499b();
    L_0x0057:
        return;
    L_0x0058:
        r1 = r1.m3332a();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = "form";	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r1.optJSONObject(r2);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = "onload";	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r1.optJSONObject(r2);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = com.alipay.sdk.protocol.C0868b.m3372a(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x006d:
        r0 = r2.size();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        if (r1 >= r0) goto L_0x008d;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x0073:
        r0 = r2.get(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = (com.alipay.sdk.protocol.C0868b) r0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.f2157a;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r3 = com.alipay.sdk.protocol.C0867a.WapPay;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        if (r0 != r3) goto L_0x00ca;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x007f:
        r0 = r2.get(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = (com.alipay.sdk.protocol.C0868b) r0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.f2158b;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = 0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0[r1];	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        com.alipay.sdk.auth.C0836h.f2014d = r0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x008d:
        r0 = com.alipay.sdk.auth.C0836h.f2014d;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        if (r0 == 0) goto L_0x00ce;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x0097:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0.<init>();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r5.f2017c;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r1.getRedirectUri();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = "?resultCode=202";	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        com.alipay.sdk.auth.C0836h.f2014d = r0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r5.f2015a;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = com.alipay.sdk.auth.C0836h.f2014d;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        com.alipay.sdk.auth.C0836h.m3225a(r0, r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = com.alipay.sdk.auth.C0836h.f2013c;
        if (r0 == 0) goto L_0x0057;
    L_0x00c2:
        r0 = com.alipay.sdk.auth.C0836h.f2013c;
        r0.m3499b();
        goto L_0x0057;
    L_0x00ca:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x006d;
    L_0x00ce:
        r0 = new android.content.Intent;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r5.f2015a;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = com.alipay.sdk.auth.AuthActivity.class;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0.<init>(r1, r2);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = "params";	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = com.alipay.sdk.auth.C0836h.f2014d;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0.putExtra(r1, r2);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = "redirectUri";	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = r5.f2017c;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = r2.getRedirectUri();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0.putExtra(r1, r2);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r5.f2015a;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1.startActivity(r0);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = com.alipay.sdk.auth.C0836h.f2013c;
        if (r0 == 0) goto L_0x0057;
    L_0x00f6:
        r0 = com.alipay.sdk.auth.C0836h.f2013c;
        r0.m3499b();
        goto L_0x0057;
    L_0x00ff:
        r0 = move-exception;
        r0 = com.alipay.sdk.auth.C0836h.f2013c;
        if (r0 == 0) goto L_0x0057;
    L_0x0106:
        r0 = com.alipay.sdk.auth.C0836h.f2013c;
        r0.m3499b();
        goto L_0x0057;
    L_0x010f:
        r0 = move-exception;
        r1 = com.alipay.sdk.auth.C0836h.f2013c;
        if (r1 == 0) goto L_0x011d;
    L_0x0116:
        r1 = com.alipay.sdk.auth.C0836h.f2013c;
        r1.m3499b();
    L_0x011d:
        throw r0;
    L_0x011e:
        r2 = move-exception;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.auth.i.run():void");
    }

    C0837i(Activity activity, StringBuilder stringBuilder, APAuthInfo aPAuthInfo) {
        this.f2015a = activity;
        this.f2016b = stringBuilder;
        this.f2017c = aPAuthInfo;
    }
}
