package cn.jpush.android.service;

public class PushProtocol {
    /* renamed from: z */
    private static final String f804z;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r1 = 0;
        r2 = ";T:#/\u0005\u0003%8+\fa 58\t_0mp\u0002]<$\"Z\u001c~";
        r0 = -1;
    L_0x0004:
        r2 = r2.toCharArray();
        r3 = r2.length;
        r4 = 1;
        if (r3 > r4) goto L_0x0060;
    L_0x000c:
        r4 = r1;
    L_0x000d:
        r5 = r2;
        r6 = r4;
        r9 = r3;
        r3 = r2;
        r2 = r9;
    L_0x0012:
        r8 = r3[r4];
        r7 = r6 % 5;
        switch(r7) {
            case 0: goto L_0x003e;
            case 1: goto L_0x0041;
            case 2: goto L_0x0044;
            case 3: goto L_0x0047;
            default: goto L_0x0019;
        };
    L_0x0019:
        r7 = 74;
    L_0x001b:
        r7 = r7 ^ r8;
        r7 = (char) r7;
        r3[r4] = r7;
        r4 = r6 + 1;
        if (r2 != 0) goto L_0x0027;
    L_0x0023:
        r3 = r5;
        r6 = r4;
        r4 = r2;
        goto L_0x0012;
    L_0x0027:
        r3 = r2;
        r2 = r5;
    L_0x0029:
        if (r3 > r4) goto L_0x000d;
    L_0x002b:
        r3 = new java.lang.String;
        r3.<init>(r2);
        r2 = r3.intern();
        switch(r0) {
            case 0: goto L_0x004a;
            default: goto L_0x0037;
        };
    L_0x0037:
        f804z = r2;
        r0 = "\u0002]<$\"Z\u001c~";	 Catch:{ Throwable -> 0x004e }
        r2 = r0;	 Catch:{ Throwable -> 0x004e }
        r0 = r1;	 Catch:{ Throwable -> 0x004e }
        goto L_0x0004;	 Catch:{ Throwable -> 0x004e }
    L_0x003e:
        r7 = 104; // 0x68 float:1.46E-43 double:5.14E-322;	 Catch:{ Throwable -> 0x004e }
        goto L_0x001b;	 Catch:{ Throwable -> 0x004e }
    L_0x0041:
        r7 = 45;	 Catch:{ Throwable -> 0x004e }
        goto L_0x001b;	 Catch:{ Throwable -> 0x004e }
    L_0x0044:
        r7 = 73;	 Catch:{ Throwable -> 0x004e }
        goto L_0x001b;	 Catch:{ Throwable -> 0x004e }
    L_0x0047:
        r7 = 87;	 Catch:{ Throwable -> 0x004e }
        goto L_0x001b;	 Catch:{ Throwable -> 0x004e }
    L_0x004a:
        java.lang.System.loadLibrary(r2);	 Catch:{ Throwable -> 0x004e }
    L_0x004d:
        return;
    L_0x004e:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r2 = f804z;
        r1.<init>(r2);
        r1.append(r0);
        cn.jpush.android.util.ac.m1588e();
        r0.printStackTrace();
        goto L_0x004d;
    L_0x0060:
        r4 = r1;
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.PushProtocol.<clinit>():void");
    }

    public static native synchronized int Close(long j);

    public static native int CtrlResponse(long j, int i, long j2, long j3, long j4, int i2);

    public static native int GetSdkVersion();

    public static native int HbJPush(long j, long j2, int i, long j3, short s);

    public static native int IMProtocol(long j, byte[] bArr, int i);

    public static native synchronized long InitConn();

    public static native int InitPush(long j, String str, int i);

    public static native int LogPush(long j, long j2, byte[] bArr, long j3, String str, String str2, long j4, short s);

    public static native int MsgResponse(long j, int i, long j2, byte b, long j3, long j4, int i2);

    public static native int RecvPush(long j, byte[] bArr, int i);

    public static native int RegPush(long j, long j2, String str, String str2, String str3, String str4);

    public static native int Stop(long j);

    public static native int TagAlias(long j, long j2, int i, long j3, String str, String str2);
}
