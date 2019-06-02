package com.alibaba.fastjson.parser.deserializer;

public class StackTraceElementDeserializer implements ObjectDeserializer {
    public static final StackTraceElementDeserializer instance = new StackTraceElementDeserializer();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r9, java.lang.reflect.Type r10, java.lang.Object r11) {
        /*
        r8 = this;
        r4 = r9.getLexer();
        r0 = r4.token();
        r1 = 8;
        if (r0 != r1) goto L_0x0011;
    L_0x000c:
        r4.nextToken();
        r0 = 0;
    L_0x0010:
        return r0;
    L_0x0011:
        r0 = r4.token();
        r1 = 12;
        if (r0 == r1) goto L_0x0042;
    L_0x0019:
        r0 = r4.token();
        r1 = 16;
        if (r0 == r1) goto L_0x0042;
    L_0x0021:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error: ";
        r1 = r1.append(r2);
        r2 = r4.token();
        r2 = com.alibaba.fastjson.parser.JSONToken.name(r2);
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0042:
        r3 = 0;
        r2 = 0;
        r1 = 0;
        r0 = 0;
    L_0x0046:
        r5 = r9.getSymbolTable();
        r5 = r4.scanSymbol(r5);
        if (r5 != 0) goto L_0x0074;
    L_0x0050:
        r6 = r4.token();
        r7 = 13;
        if (r6 != r7) goto L_0x0064;
    L_0x0058:
        r5 = 16;
        r4.nextToken(r5);
    L_0x005d:
        r4 = new java.lang.StackTraceElement;
        r4.<init>(r3, r2, r1, r0);
        r0 = r4;
        goto L_0x0010;
    L_0x0064:
        r6 = r4.token();
        r7 = 16;
        if (r6 != r7) goto L_0x0074;
    L_0x006c:
        r6 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas;
        r6 = r4.isEnabled(r6);
        if (r6 != 0) goto L_0x0046;
    L_0x0074:
        r6 = 4;
        r4.nextTokenWithColon(r6);
        r6 = "className";
        if (r5 != r6) goto L_0x00a7;
    L_0x007c:
        r3 = r4.token();
        r5 = 8;
        if (r3 != r5) goto L_0x0093;
    L_0x0084:
        r3 = 0;
    L_0x0085:
        r5 = r4.token();
        r6 = 13;
        if (r5 != r6) goto L_0x0046;
    L_0x008d:
        r5 = 16;
        r4.nextToken(r5);
        goto L_0x005d;
    L_0x0093:
        r3 = r4.token();
        r5 = 4;
        if (r3 != r5) goto L_0x009f;
    L_0x009a:
        r3 = r4.stringVal();
        goto L_0x0085;
    L_0x009f:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x00a7:
        r6 = "methodName";
        if (r5 != r6) goto L_0x00c9;
    L_0x00ab:
        r2 = r4.token();
        r5 = 8;
        if (r2 != r5) goto L_0x00b5;
    L_0x00b3:
        r2 = 0;
        goto L_0x0085;
    L_0x00b5:
        r2 = r4.token();
        r5 = 4;
        if (r2 != r5) goto L_0x00c1;
    L_0x00bc:
        r2 = r4.stringVal();
        goto L_0x0085;
    L_0x00c1:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x00c9:
        r6 = "fileName";
        if (r5 != r6) goto L_0x00eb;
    L_0x00cd:
        r1 = r4.token();
        r5 = 8;
        if (r1 != r5) goto L_0x00d7;
    L_0x00d5:
        r1 = 0;
        goto L_0x0085;
    L_0x00d7:
        r1 = r4.token();
        r5 = 4;
        if (r1 != r5) goto L_0x00e3;
    L_0x00de:
        r1 = r4.stringVal();
        goto L_0x0085;
    L_0x00e3:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x00eb:
        r6 = "lineNumber";
        if (r5 != r6) goto L_0x010d;
    L_0x00ef:
        r0 = r4.token();
        r5 = 8;
        if (r0 != r5) goto L_0x00f9;
    L_0x00f7:
        r0 = 0;
        goto L_0x0085;
    L_0x00f9:
        r0 = r4.token();
        r5 = 2;
        if (r0 != r5) goto L_0x0105;
    L_0x0100:
        r0 = r4.intValue();
        goto L_0x0085;
    L_0x0105:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x010d:
        r6 = "nativeMethod";
        if (r5 != r6) goto L_0x0144;
    L_0x0111:
        r5 = r4.token();
        r6 = 8;
        if (r5 != r6) goto L_0x0120;
    L_0x0119:
        r5 = 16;
        r4.nextToken(r5);
        goto L_0x0085;
    L_0x0120:
        r5 = r4.token();
        r6 = 6;
        if (r5 != r6) goto L_0x012e;
    L_0x0127:
        r5 = 16;
        r4.nextToken(r5);
        goto L_0x0085;
    L_0x012e:
        r5 = r4.token();
        r6 = 7;
        if (r5 != r6) goto L_0x013c;
    L_0x0135:
        r5 = 16;
        r4.nextToken(r5);
        goto L_0x0085;
    L_0x013c:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x0144:
        r6 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY;
        if (r5 != r6) goto L_0x0184;
    L_0x0148:
        r5 = r4.token();
        r6 = 4;
        if (r5 != r6) goto L_0x0174;
    L_0x014f:
        r5 = r4.stringVal();
        r6 = "java.lang.StackTraceElement";
        r6 = r5.equals(r6);
        if (r6 != 0) goto L_0x0085;
    L_0x015b:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error : ";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0174:
        r5 = r4.token();
        r6 = 8;
        if (r5 == r6) goto L_0x0085;
    L_0x017c:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = "syntax error";
        r0.<init>(r1);
        throw r0;
    L_0x0184:
        r0 = new com.alibaba.fastjson.JSONException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "syntax error : ";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.StackTraceElementDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object):T");
    }

    public int getFastMatchToken() {
        return 12;
    }
}
