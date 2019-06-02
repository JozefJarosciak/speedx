package cn.jpush.android.service;

/* renamed from: cn.jpush.android.service.j */
final class C0471j implements Runnable {
    /* renamed from: a */
    final /* synthetic */ long f863a;
    /* renamed from: b */
    final /* synthetic */ C0469h f864b;

    C0471j(C0469h c0469h, long j) {
        this.f864b = c0469h;
        this.f863a = j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r13 = this;
        r5 = 1;
        r0 = cn.jpush.android.service.C0469h.f854f;
        if (r0 != 0) goto L_0x001d;
    L_0x0007:
        r0 = r13.f864b;
        r0 = r0.f859d;
        if (r0 == 0) goto L_0x001d;
    L_0x000f:
        r0 = new cn.jpush.android.data.f;
        r1 = r13.f864b;
        r1 = r1.f859d;
        r0.<init>(r1);
        cn.jpush.android.service.C0469h.f854f = r0;
    L_0x001d:
        r0 = 0;
        r1 = cn.jpush.android.service.C0469h.f854f;	 Catch:{ Exception -> 0x00c1, all -> 0x013f }
        r2 = 1;
        r1.m1296a(r2);	 Catch:{ Exception -> 0x00c1, all -> 0x013f }
        r1 = cn.jpush.android.service.C0469h.f854f;	 Catch:{ Exception -> 0x00c1, all -> 0x013f }
        r2 = r13.f863a;	 Catch:{ Exception -> 0x00c1, all -> 0x013f }
        r4 = 0;
        r0 = r1.m1292a(r2, r4);	 Catch:{ Exception -> 0x00c1, all -> 0x013f }
        r1 = cn.jpush.android.service.C0469h.f854f;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r2 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1.m1295a(r0, r2);	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1 = r1.m1306c();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        if (r5 != r1) goto L_0x0080;
    L_0x0046:
        cn.jpush.android.util.ac.m1581b();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1 = cn.jpush.android.service.C0469h.f854f;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r2 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r2 = r2.m1299a();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r4 = 0;
        r5 = 1;
        r6 = 0;
        r7 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r7 = r7.m1309d();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r8 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r8 = r8.m1311f();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r10 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r10 = r10.m1310e();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1.m1298b(r2, r4, r5, r6, r7, r8, r10);	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
    L_0x0073:
        r1 = cn.jpush.android.service.C0469h.f854f;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1.m1294a();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        if (r0 == 0) goto L_0x007f;
    L_0x007c:
        r0.close();
    L_0x007f:
        return;
    L_0x0080:
        r1 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1 = r1.m1303b();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        if (r1 <= r5) goto L_0x00c8;
    L_0x008a:
        cn.jpush.android.util.ac.m1581b();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1 = cn.jpush.android.service.C0469h.f854f;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r2 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r2 = r2.m1299a();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r4 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r4 = r4.m1303b();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r4 = r4 + -1;
        r5 = 0;
        r6 = 0;
        r7 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r7 = r7.m1309d();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r8 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r8 = r8.m1311f();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r10 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r10 = r10.m1310e();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1.m1298b(r2, r4, r5, r6, r7, r8, r10);	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        goto L_0x0073;
    L_0x00c1:
        r1 = move-exception;
        if (r0 == 0) goto L_0x007f;
    L_0x00c4:
        r0.close();
        goto L_0x007f;
    L_0x00c8:
        r1 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1 = r1.m1303b();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        if (r1 != r5) goto L_0x013a;
    L_0x00d2:
        cn.jpush.android.util.ac.m1581b();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r4 = r1.m1311f();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r1 <= 0) goto L_0x00f3;
    L_0x00e5:
        cn.jpush.android.util.ac.m1581b();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        goto L_0x0073;
    L_0x00e9:
        r1 = move-exception;
        r12 = r1;
        r1 = r0;
        r0 = r12;
    L_0x00ed:
        if (r1 == 0) goto L_0x00f2;
    L_0x00ef:
        r1.close();
    L_0x00f2:
        throw r0;
    L_0x00f3:
        r1 = r13.f864b;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r2 = r13.f864b;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r2 = r2.f859d;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r3 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r3 = r3.m1309d();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r4 = r13.f864b;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r4 = r4.f860e;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r5 = "";
        r1.m1500a(r2, r3, r4, r5);	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1 = cn.jpush.android.service.C0469h.f854f;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r2 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r2 = r2.m1299a();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r7 = r7.m1309d();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r8 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r8 = r8.m1311f();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r10 = cn.jpush.android.service.C0469h.f855g;	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r10 = r10.m1310e();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        r1.m1298b(r2, r4, r5, r6, r7, r8, r10);	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        goto L_0x0073;
    L_0x013a:
        cn.jpush.android.util.ac.m1581b();	 Catch:{ Exception -> 0x00c1, all -> 0x00e9 }
        goto L_0x0073;
    L_0x013f:
        r1 = move-exception;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x00ed;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.j.run():void");
    }
}
