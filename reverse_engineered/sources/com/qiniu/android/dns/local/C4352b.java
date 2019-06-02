package com.qiniu.android.dns.local;

import com.alibaba.fastjson.asm.Opcodes;
import com.qiniu.android.dns.C4349f;
import com.qiniu.android.dns.DnsException;
import com.qiniu.android.dns.p190a.C4343a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.IDN;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;

/* compiled from: DnsMessage */
/* renamed from: com.qiniu.android.dns.local.b */
public final class C4352b {
    /* renamed from: a */
    public static byte[] m17163a(String str, int i) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        C4343a c4343a = new C4343a();
        c4343a.m17136a(8);
        try {
            dataOutputStream.writeShort((short) i);
            dataOutputStream.writeShort((short) c4343a.m17135a());
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            dataOutputStream.flush();
            C4352b.m17166b(byteArrayOutputStream, str);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    private static void m17162a(OutputStream outputStream, String str) throws IOException {
        for (String toASCII : str.split("[.。．｡]")) {
            byte[] bytes = IDN.toASCII(toASCII).getBytes();
            outputStream.write(bytes.length);
            outputStream.write(bytes, 0, bytes.length);
        }
        outputStream.write(0);
    }

    /* renamed from: b */
    private static void m17166b(OutputStream outputStream, String str) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        C4352b.m17162a(outputStream, str);
        dataOutputStream.writeShort(1);
        dataOutputStream.writeShort(1);
    }

    /* renamed from: a */
    public static C4349f[] m17164a(byte[] bArr, int i, String str) throws IOException {
        Object obj = 1;
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        if (readUnsignedShort != i) {
            throw new DnsException(str, "the answer id " + readUnsignedShort + " is not match " + i);
        }
        int readUnsignedShort2 = dataInputStream.readUnsignedShort();
        Object obj2;
        if (((readUnsignedShort2 >> 8) & 1) == 1) {
            obj2 = 1;
        } else {
            obj2 = null;
        }
        if (((readUnsignedShort2 >> 7) & 1) != 1) {
            obj = null;
        }
        if (obj == null || r2 == null) {
            throw new DnsException(str, "the dns server cant support recursion ");
        }
        int readUnsignedShort3 = dataInputStream.readUnsignedShort();
        int readUnsignedShort4 = dataInputStream.readUnsignedShort();
        dataInputStream.readUnsignedShort();
        dataInputStream.readUnsignedShort();
        C4352b.m17161a(dataInputStream, bArr, readUnsignedShort3);
        return C4352b.m17167b(dataInputStream, bArr, readUnsignedShort4);
    }

    /* renamed from: a */
    private static String m17159a(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        if ((readUnsignedByte & Opcodes.CHECKCAST) == Opcodes.CHECKCAST) {
            readUnsignedByte = ((readUnsignedByte & 63) << 8) + dataInputStream.readUnsignedByte();
            HashSet hashSet = new HashSet();
            hashSet.add(Integer.valueOf(readUnsignedByte));
            return C4352b.m17160a(bArr, readUnsignedByte, hashSet);
        } else if (readUnsignedByte == 0) {
            return "";
        } else {
            byte[] bArr2 = new byte[readUnsignedByte];
            dataInputStream.readFully(bArr2);
            String toUnicode = IDN.toUnicode(new String(bArr2));
            String a = C4352b.m17159a(dataInputStream, bArr);
            if (a.length() > 0) {
                return toUnicode + "." + a;
            }
            return toUnicode;
        }
    }

    /* renamed from: a */
    private static String m17160a(byte[] bArr, int i, HashSet<Integer> hashSet) throws IOException {
        int i2 = bArr[i] & 255;
        if ((i2 & Opcodes.CHECKCAST) == Opcodes.CHECKCAST) {
            int i3 = ((i2 & 63) << 8) + (bArr[i + 1] & 255);
            if (hashSet.contains(Integer.valueOf(i3))) {
                throw new DnsException("", "Cyclic offsets detected.");
            }
            hashSet.add(Integer.valueOf(i3));
            return C4352b.m17160a(bArr, i3, (HashSet) hashSet);
        } else if (i2 == 0) {
            return "";
        } else {
            String str = new String(bArr, i + 1, i2);
            String a = C4352b.m17160a(bArr, i2 + (i + 1), (HashSet) hashSet);
            if (a.length() > 0) {
                return str + "." + a;
            }
            return str;
        }
    }

    /* renamed from: a */
    private static void m17161a(DataInputStream dataInputStream, byte[] bArr, int i) throws IOException {
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                C4352b.m17159a(dataInputStream, bArr);
                dataInputStream.readUnsignedShort();
                dataInputStream.readUnsignedShort();
                i = i2;
            } else {
                return;
            }
        }
    }

    /* renamed from: b */
    private static C4349f[] m17167b(DataInputStream dataInputStream, byte[] bArr, int i) throws IOException {
        int i2 = 0;
        C4349f[] c4349fArr = new C4349f[i];
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                return c4349fArr;
            }
            int i4 = i2 + 1;
            c4349fArr[i2] = C4352b.m17165b(dataInputStream, bArr);
            i2 = i4;
            i = i3;
        }
    }

    /* renamed from: b */
    private static C4349f m17165b(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        String hostAddress;
        C4352b.m17159a(dataInputStream, bArr);
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        dataInputStream.readUnsignedShort();
        long readUnsignedShort2 = ((long) dataInputStream.readUnsignedShort()) + (((long) dataInputStream.readUnsignedShort()) << 16);
        int readUnsignedShort3 = dataInputStream.readUnsignedShort();
        switch (readUnsignedShort) {
            case 1:
                byte[] bArr2 = new byte[4];
                dataInputStream.readFully(bArr2);
                hostAddress = InetAddress.getByAddress(bArr2).getHostAddress();
                break;
            case 5:
                hostAddress = C4352b.m17159a(dataInputStream, bArr);
                break;
            default:
                hostAddress = null;
                for (int i = 0; i < readUnsignedShort3; i++) {
                    dataInputStream.readByte();
                }
                break;
        }
        if (hostAddress != null) {
            return new C4349f(hostAddress, readUnsignedShort, (int) readUnsignedShort2, System.currentTimeMillis() / 1000);
        }
        throw new UnknownHostException("no record");
    }
}
