package com.beastbikes.android.ble.biz;

import android.text.TextUtils;
import com.beastbikes.android.ble.protocol.v1.ProtocolParserImpl;
import com.beastbikes.android.utils.aa;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BCDFormat */
/* renamed from: com.beastbikes.android.ble.biz.a */
public class C1616a {
    /* renamed from: a */
    private static final Logger f7406a = LoggerFactory.getLogger(C1616a.class);

    /* renamed from: a */
    public static byte[] m8780a(double d) {
        byte[] bArr = new byte[5];
        if (d > 0.0d) {
            bArr[0] = C1616a.m8778a((byte) ((int) (d / 100.0d)));
        } else {
            d = Math.abs(d);
            bArr[0] = (byte) (C1616a.m8778a((byte) ((int) (d / 100.0d))) | 128);
        }
        bArr[1] = C1616a.m8778a((byte) ((int) (d % 100.0d)));
        bArr[2] = C1616a.m8778a((byte) ((int) ((d * 100.0d) % 100.0d)));
        bArr[3] = C1616a.m8778a((byte) ((int) ((10000.0d * d) % 100.0d)));
        bArr[4] = C1616a.m8778a((byte) ((int) ((1000000.0d * d) % 100.0d)));
        return bArr;
    }

    /* renamed from: a */
    private static byte m8778a(byte b) {
        int i = 0;
        while (b >= (byte) 10) {
            i++;
            b = (byte) (b - 10);
        }
        return (byte) ((i << 4) | b);
    }

    /* renamed from: a */
    public static byte[] m8781a(String str) {
        byte[] bArr = new byte[4];
        if (TextUtils.isEmpty(str)) {
            return bArr;
        }
        String[] split;
        if (str.contains(".")) {
            split = str.split("\\.");
        } else {
            split = str.split(HelpFormatter.DEFAULT_OPT_PREFIX);
        }
        int a = aa.m12768a(split[0]);
        int a2 = aa.m12768a(split[1]);
        int a3 = aa.m12768a(split[2]);
        bArr[0] = C1616a.m8778a((byte) (a / 100));
        bArr[1] = C1616a.m8778a((byte) (a % 100));
        bArr[2] = C1616a.m8778a((byte) a2);
        bArr[3] = C1616a.m8778a((byte) a3);
        return bArr;
    }

    /* renamed from: a */
    public static String m8779a(byte[] bArr, ProtocolParserImpl protocolParserImpl) {
        if (bArr.length != 4) {
            f7406a.error("Birthday BCD length is not 4");
            return "";
        }
        int bcdToByte = (protocolParserImpl.bcdToByte(bArr[0]) * 100) + protocolParserImpl.bcdToByte(bArr[1]);
        byte bcdToByte2 = protocolParserImpl.bcdToByte(bArr[2]);
        return bcdToByte + "." + bcdToByte2 + "." + protocolParserImpl.bcdToByte(bArr[3]);
    }
}
