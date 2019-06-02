package com.tencent.wxop.stat;

import android.content.Context;
import com.alipay.sdk.util.C0880h;
import com.tencent.wxop.stat.p201a.C4513e;
import com.tencent.wxop.stat.p201a.C4522j;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;

class ap implements Runnable {
    /* renamed from: a */
    private Context f15982a = null;
    /* renamed from: b */
    private Map<String, Integer> f15983b = null;
    /* renamed from: c */
    private StatSpecifyReportedInfo f15984c = null;

    public ap(Context context, Map<String, Integer> map, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f15982a = context;
        this.f15984c = statSpecifyReportedInfo;
        if (map != null) {
            this.f15983b = map;
        }
    }

    /* renamed from: a */
    private NetworkMonitor m17950a(String str, int i) {
        Throwable th;
        NetworkMonitor networkMonitor = new NetworkMonitor();
        Socket socket = new Socket();
        int i2 = 0;
        try {
            networkMonitor.setDomain(str);
            networkMonitor.setPort(i);
            long currentTimeMillis = System.currentTimeMillis();
            SocketAddress inetSocketAddress = new InetSocketAddress(str, i);
            socket.connect(inetSocketAddress, 30000);
            networkMonitor.setMillisecondsConsume(System.currentTimeMillis() - currentTimeMillis);
            networkMonitor.setRemoteIp(inetSocketAddress.getAddress().getHostAddress());
            socket.close();
            try {
                socket.close();
            } catch (Throwable th2) {
                StatServiceImpl.f15886q.m18011e(th2);
            }
        } catch (Throwable e) {
            th2 = e;
            i2 = -1;
            StatServiceImpl.f15886q.m18011e(th2);
            socket.close();
        } catch (Throwable th22) {
            StatServiceImpl.f15886q.m18011e(th22);
        }
        networkMonitor.setStatusCode(i2);
        return networkMonitor;
    }

    /* renamed from: a */
    private Map<String, Integer> m17951a() {
        Map<String, Integer> hashMap = new HashMap();
        String a = StatConfig.m17857a("__MTA_TEST_SPEED__", null);
        if (!(a == null || a.trim().length() == 0)) {
            for (String a2 : a2.split(C0880h.f2220b)) {
                String[] split = a2.split(",");
                if (split != null && split.length == 2) {
                    String str = split[0];
                    if (!(str == null || str.trim().length() == 0)) {
                        try {
                            hashMap.put(str, Integer.valueOf(Integer.valueOf(split[1]).intValue()));
                        } catch (Throwable e) {
                            StatServiceImpl.f15886q.m18011e(e);
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    public void run() {
        try {
            if (this.f15983b == null) {
                this.f15983b = m17951a();
            }
            if (this.f15983b == null || this.f15983b.size() == 0) {
                StatServiceImpl.f15886q.m18012i("empty domain list.");
                return;
            }
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : this.f15983b.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null || str.length() == 0) {
                    StatServiceImpl.f15886q.m18014w("empty domain name.");
                } else if (((Integer) entry.getValue()) == null) {
                    StatServiceImpl.f15886q.m18014w("port is null for " + str);
                } else {
                    jSONArray.put(m17950a((String) entry.getKey(), ((Integer) entry.getValue()).intValue()).toJSONObject());
                }
            }
            if (jSONArray.length() != 0) {
                C4513e c4522j = new C4522j(this.f15982a, StatServiceImpl.m17874a(this.f15982a, false, this.f15984c), this.f15984c);
                c4522j.m17928a(jSONArray.toString());
                new aq(c4522j).m17959a();
            }
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
        }
    }
}
