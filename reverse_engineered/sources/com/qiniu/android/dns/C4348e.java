package com.qiniu.android.dns;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

/* compiled from: Network */
/* renamed from: com.qiniu.android.dns.e */
public final class C4348e {
    /* renamed from: a */
    private static String f15124a = "";

    /* renamed from: a */
    public static String m17150a() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.connect(InetAddress.getByName("114.114.114.114"), 53);
            InetAddress localAddress = datagramSocket.getLocalAddress();
            datagramSocket.close();
            return localAddress.getHostAddress();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public static synchronized boolean m17151b() {
        boolean z;
        synchronized (C4348e.class) {
            String a = C4348e.m17150a();
            if (a.equals(f15124a)) {
                z = false;
            } else {
                f15124a = a;
                z = true;
            }
        }
        return z;
    }
}
