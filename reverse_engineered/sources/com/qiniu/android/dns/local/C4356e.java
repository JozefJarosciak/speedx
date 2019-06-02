package com.qiniu.android.dns.local;

import com.qiniu.android.dns.C4346b;
import com.qiniu.android.dns.C4347c;
import com.qiniu.android.dns.C4349f;
import com.qiniu.android.dns.DnsException;
import com.qiniu.android.dns.NetworkInfo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/* compiled from: Resolver */
/* renamed from: com.qiniu.android.dns.local.e */
public final class C4356e implements C4347c {
    /* renamed from: b */
    private static final Random f15133b = new Random();
    /* renamed from: a */
    final InetAddress f15134a;

    public C4356e(InetAddress inetAddress) {
        this.f15134a = inetAddress;
    }

    /* renamed from: a */
    public C4349f[] mo6027a(C4346b c4346b, NetworkInfo networkInfo) throws IOException {
        int nextInt;
        synchronized (f15133b) {
            nextInt = f15133b.nextInt() & 255;
        }
        byte[] a = m17174a(C4352b.m17163a(c4346b.f15120a, nextInt));
        if (a != null) {
            return C4352b.m17164a(a, nextInt, c4346b.f15120a);
        }
        throw new DnsException(c4346b.f15120a, "cant get answer");
    }

    /* renamed from: a */
    private byte[] m17174a(byte[] bArr) throws IOException {
        Throwable th;
        DatagramSocket datagramSocket;
        try {
            datagramSocket = new DatagramSocket();
            try {
                DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, this.f15134a, 53);
                datagramSocket.setSoTimeout(10000);
                datagramSocket.send(datagramPacket);
                datagramPacket = new DatagramPacket(new byte[1500], 1500);
                datagramSocket.receive(datagramPacket);
                byte[] data = datagramPacket.getData();
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
                return data;
            } catch (Throwable th2) {
                th = th2;
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            datagramSocket = null;
            if (datagramSocket != null) {
                datagramSocket.close();
            }
            throw th;
        }
    }
}
