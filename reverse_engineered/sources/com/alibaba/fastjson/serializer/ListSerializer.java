package com.alibaba.fastjson.serializer;

public final class ListSerializer implements ObjectSerializer {
    public static final ListSerializer instance = new ListSerializer();

    public final void write(com.alibaba.fastjson.serializer.JSONSerializer r11, java.lang.Object r12, java.lang.Object r13, java.lang.reflect.Type r14) throws java.io.IOException {
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
        r10 = this;
        r1 = 0;
        r0 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName;
        r4 = r11.isEnabled(r0);
        r5 = r11.getWriter();
        r0 = 0;
        if (r4 == 0) goto L_0x018b;
    L_0x000e:
        r2 = r14 instanceof java.lang.reflect.ParameterizedType;
        if (r2 == 0) goto L_0x018b;
    L_0x0012:
        r14 = (java.lang.reflect.ParameterizedType) r14;
        r0 = r14.getActualTypeArguments();
        r0 = r0[r1];
        r3 = r0;
    L_0x001b:
        if (r12 != 0) goto L_0x002f;
    L_0x001d:
        r0 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty;
        r0 = r5.isEnabled(r0);
        if (r0 == 0) goto L_0x002b;
    L_0x0025:
        r0 = "[]";
        r5.write(r0);
    L_0x002a:
        return;
    L_0x002b:
        r5.writeNull();
        goto L_0x002a;
    L_0x002f:
        r0 = r12;
        r0 = (java.util.List) r0;
        r2 = r0.size();
        r6 = r2 + -1;
        r7 = -1;
        if (r6 != r7) goto L_0x0041;
    L_0x003b:
        r0 = "[]";
        r5.append(r0);
        goto L_0x002a;
    L_0x0041:
        r7 = r11.getContext();
        r11.setContext(r7, r12, r13);
        r8 = 1;
        if (r2 <= r8) goto L_0x00ae;
    L_0x004b:
        r8 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat;	 Catch:{ all -> 0x0091 }
        r8 = r5.isEnabled(r8);	 Catch:{ all -> 0x0091 }
        if (r8 == 0) goto L_0x00ae;	 Catch:{ all -> 0x0091 }
    L_0x0053:
        r4 = 91;	 Catch:{ all -> 0x0091 }
        r5.append(r4);	 Catch:{ all -> 0x0091 }
        r11.incrementIndent();	 Catch:{ all -> 0x0091 }
    L_0x005b:
        if (r1 >= r2) goto L_0x009e;	 Catch:{ all -> 0x0091 }
    L_0x005d:
        if (r1 == 0) goto L_0x0064;	 Catch:{ all -> 0x0091 }
    L_0x005f:
        r4 = 44;	 Catch:{ all -> 0x0091 }
        r5.append(r4);	 Catch:{ all -> 0x0091 }
    L_0x0064:
        r11.println();	 Catch:{ all -> 0x0091 }
        r4 = r0.get(r1);	 Catch:{ all -> 0x0091 }
        if (r4 == 0) goto L_0x0096;	 Catch:{ all -> 0x0091 }
    L_0x006d:
        r6 = r11.containsReference(r4);	 Catch:{ all -> 0x0091 }
        if (r6 == 0) goto L_0x0079;	 Catch:{ all -> 0x0091 }
    L_0x0073:
        r11.writeReference(r4);	 Catch:{ all -> 0x0091 }
    L_0x0076:
        r1 = r1 + 1;	 Catch:{ all -> 0x0091 }
        goto L_0x005b;	 Catch:{ all -> 0x0091 }
    L_0x0079:
        r6 = r4.getClass();	 Catch:{ all -> 0x0091 }
        r6 = r11.getObjectWriter(r6);	 Catch:{ all -> 0x0091 }
        r8 = new com.alibaba.fastjson.serializer.SerialContext;	 Catch:{ all -> 0x0091 }
        r8.<init>(r7, r12, r13);	 Catch:{ all -> 0x0091 }
        r11.setContext(r8);	 Catch:{ all -> 0x0091 }
        r8 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x0091 }
        r6.write(r11, r4, r8, r3);	 Catch:{ all -> 0x0091 }
        goto L_0x0076;
    L_0x0091:
        r0 = move-exception;
        r11.setContext(r7);
        throw r0;
    L_0x0096:
        r4 = r11.getWriter();	 Catch:{ all -> 0x0091 }
        r4.writeNull();	 Catch:{ all -> 0x0091 }
        goto L_0x0076;	 Catch:{ all -> 0x0091 }
    L_0x009e:
        r11.decrementIdent();	 Catch:{ all -> 0x0091 }
        r11.println();	 Catch:{ all -> 0x0091 }
        r0 = 93;	 Catch:{ all -> 0x0091 }
        r5.append(r0);	 Catch:{ all -> 0x0091 }
        r11.setContext(r7);
        goto L_0x002a;
    L_0x00ae:
        r2 = 91;
        r5.append(r2);	 Catch:{ all -> 0x0091 }
        r2 = r1;	 Catch:{ all -> 0x0091 }
    L_0x00b4:
        if (r2 >= r6) goto L_0x011d;	 Catch:{ all -> 0x0091 }
    L_0x00b6:
        r1 = r0.get(r2);	 Catch:{ all -> 0x0091 }
        if (r1 != 0) goto L_0x00c5;	 Catch:{ all -> 0x0091 }
    L_0x00bc:
        r1 = "null,";	 Catch:{ all -> 0x0091 }
        r5.append(r1);	 Catch:{ all -> 0x0091 }
    L_0x00c1:
        r1 = r2 + 1;	 Catch:{ all -> 0x0091 }
        r2 = r1;	 Catch:{ all -> 0x0091 }
        goto L_0x00b4;	 Catch:{ all -> 0x0091 }
    L_0x00c5:
        r8 = r1.getClass();	 Catch:{ all -> 0x0091 }
        r9 = java.lang.Integer.class;	 Catch:{ all -> 0x0091 }
        if (r8 != r9) goto L_0x00d9;	 Catch:{ all -> 0x0091 }
    L_0x00cd:
        r1 = (java.lang.Integer) r1;	 Catch:{ all -> 0x0091 }
        r1 = r1.intValue();	 Catch:{ all -> 0x0091 }
        r8 = 44;	 Catch:{ all -> 0x0091 }
        r5.writeIntAndChar(r1, r8);	 Catch:{ all -> 0x0091 }
        goto L_0x00c1;	 Catch:{ all -> 0x0091 }
    L_0x00d9:
        r9 = java.lang.Long.class;	 Catch:{ all -> 0x0091 }
        if (r8 != r9) goto L_0x00f6;	 Catch:{ all -> 0x0091 }
    L_0x00dd:
        r1 = (java.lang.Long) r1;	 Catch:{ all -> 0x0091 }
        r8 = r1.longValue();	 Catch:{ all -> 0x0091 }
        if (r4 == 0) goto L_0x00f0;	 Catch:{ all -> 0x0091 }
    L_0x00e5:
        r1 = 76;	 Catch:{ all -> 0x0091 }
        r5.writeLongAndChar(r8, r1);	 Catch:{ all -> 0x0091 }
        r1 = 44;	 Catch:{ all -> 0x0091 }
        r5.write(r1);	 Catch:{ all -> 0x0091 }
        goto L_0x00c1;	 Catch:{ all -> 0x0091 }
    L_0x00f0:
        r1 = 44;	 Catch:{ all -> 0x0091 }
        r5.writeLongAndChar(r8, r1);	 Catch:{ all -> 0x0091 }
        goto L_0x00c1;	 Catch:{ all -> 0x0091 }
    L_0x00f6:
        r8 = new com.alibaba.fastjson.serializer.SerialContext;	 Catch:{ all -> 0x0091 }
        r8.<init>(r7, r12, r13);	 Catch:{ all -> 0x0091 }
        r11.setContext(r8);	 Catch:{ all -> 0x0091 }
        r8 = r11.containsReference(r1);	 Catch:{ all -> 0x0091 }
        if (r8 == 0) goto L_0x010d;	 Catch:{ all -> 0x0091 }
    L_0x0104:
        r11.writeReference(r1);	 Catch:{ all -> 0x0091 }
    L_0x0107:
        r1 = 44;	 Catch:{ all -> 0x0091 }
        r5.append(r1);	 Catch:{ all -> 0x0091 }
        goto L_0x00c1;	 Catch:{ all -> 0x0091 }
    L_0x010d:
        r8 = r1.getClass();	 Catch:{ all -> 0x0091 }
        r8 = r11.getObjectWriter(r8);	 Catch:{ all -> 0x0091 }
        r9 = java.lang.Integer.valueOf(r2);	 Catch:{ all -> 0x0091 }
        r8.write(r11, r1, r9, r3);	 Catch:{ all -> 0x0091 }
        goto L_0x0107;	 Catch:{ all -> 0x0091 }
    L_0x011d:
        r0 = r0.get(r6);	 Catch:{ all -> 0x0091 }
        if (r0 != 0) goto L_0x012d;	 Catch:{ all -> 0x0091 }
    L_0x0123:
        r0 = "null]";	 Catch:{ all -> 0x0091 }
        r5.append(r0);	 Catch:{ all -> 0x0091 }
    L_0x0128:
        r11.setContext(r7);
        goto L_0x002a;
    L_0x012d:
        r1 = r0.getClass();	 Catch:{ all -> 0x0091 }
        r2 = java.lang.Integer.class;	 Catch:{ all -> 0x0091 }
        if (r1 != r2) goto L_0x0141;	 Catch:{ all -> 0x0091 }
    L_0x0135:
        r0 = (java.lang.Integer) r0;	 Catch:{ all -> 0x0091 }
        r0 = r0.intValue();	 Catch:{ all -> 0x0091 }
        r1 = 93;	 Catch:{ all -> 0x0091 }
        r5.writeIntAndChar(r0, r1);	 Catch:{ all -> 0x0091 }
        goto L_0x0128;	 Catch:{ all -> 0x0091 }
    L_0x0141:
        r2 = java.lang.Long.class;	 Catch:{ all -> 0x0091 }
        if (r1 != r2) goto L_0x0164;	 Catch:{ all -> 0x0091 }
    L_0x0145:
        if (r4 == 0) goto L_0x0158;	 Catch:{ all -> 0x0091 }
    L_0x0147:
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x0091 }
        r0 = r0.longValue();	 Catch:{ all -> 0x0091 }
        r2 = 76;	 Catch:{ all -> 0x0091 }
        r5.writeLongAndChar(r0, r2);	 Catch:{ all -> 0x0091 }
        r0 = 93;	 Catch:{ all -> 0x0091 }
        r5.write(r0);	 Catch:{ all -> 0x0091 }
        goto L_0x0128;	 Catch:{ all -> 0x0091 }
    L_0x0158:
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x0091 }
        r0 = r0.longValue();	 Catch:{ all -> 0x0091 }
        r2 = 93;	 Catch:{ all -> 0x0091 }
        r5.writeLongAndChar(r0, r2);	 Catch:{ all -> 0x0091 }
        goto L_0x0128;	 Catch:{ all -> 0x0091 }
    L_0x0164:
        r1 = new com.alibaba.fastjson.serializer.SerialContext;	 Catch:{ all -> 0x0091 }
        r1.<init>(r7, r12, r13);	 Catch:{ all -> 0x0091 }
        r11.setContext(r1);	 Catch:{ all -> 0x0091 }
        r1 = r11.containsReference(r0);	 Catch:{ all -> 0x0091 }
        if (r1 == 0) goto L_0x017b;	 Catch:{ all -> 0x0091 }
    L_0x0172:
        r11.writeReference(r0);	 Catch:{ all -> 0x0091 }
    L_0x0175:
        r0 = 93;	 Catch:{ all -> 0x0091 }
        r5.append(r0);	 Catch:{ all -> 0x0091 }
        goto L_0x0128;	 Catch:{ all -> 0x0091 }
    L_0x017b:
        r1 = r0.getClass();	 Catch:{ all -> 0x0091 }
        r1 = r11.getObjectWriter(r1);	 Catch:{ all -> 0x0091 }
        r2 = java.lang.Integer.valueOf(r6);	 Catch:{ all -> 0x0091 }
        r1.write(r11, r0, r2, r3);	 Catch:{ all -> 0x0091 }
        goto L_0x0175;
    L_0x018b:
        r3 = r0;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.ListSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type):void");
    }
}
