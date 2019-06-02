package com.tencent.wxop.stat;

import android.content.Context;
import android.content.IntentFilter;
import com.alipay.sdk.util.C0880h;
import com.tencent.wxop.stat.common.C4533e;
import com.tencent.wxop.stat.common.C4539k;
import com.tencent.wxop.stat.common.C4545q;
import com.tencent.wxop.stat.common.StatConstants;
import com.tencent.wxop.stat.common.StatLogger;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import org.apache.http.HttpHost;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a */
public class C4525a {
    /* renamed from: g */
    private static C4525a f15941g = null;
    /* renamed from: a */
    private List<String> f15942a = null;
    /* renamed from: b */
    private volatile int f15943b = 2;
    /* renamed from: c */
    private volatile String f15944c = "";
    /* renamed from: d */
    private volatile HttpHost f15945d = null;
    /* renamed from: e */
    private C4533e f15946e = null;
    /* renamed from: f */
    private int f15947f = 0;
    /* renamed from: h */
    private Context f15948h = null;
    /* renamed from: i */
    private StatLogger f15949i = null;

    private C4525a(Context context) {
        this.f15948h = context.getApplicationContext();
        this.f15946e = new C4533e();
        C4551i.m18120a(context);
        this.f15949i = C4539k.m18052b();
        m17940l();
        m17937i();
        m17948g();
    }

    /* renamed from: a */
    public static C4525a m17934a(Context context) {
        if (f15941g == null) {
            synchronized (C4525a.class) {
                if (f15941g == null) {
                    f15941g = new C4525a(context);
                }
            }
        }
        return f15941g;
    }

    /* renamed from: b */
    private boolean m17936b(String str) {
        return Pattern.compile("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})").matcher(str).matches();
    }

    /* renamed from: i */
    private void m17937i() {
        this.f15942a = new ArrayList(10);
        this.f15942a.add("117.135.169.101");
        this.f15942a.add("140.207.54.125");
        this.f15942a.add("180.153.8.53");
        this.f15942a.add("120.198.203.175");
        this.f15942a.add("14.17.43.18");
        this.f15942a.add("163.177.71.186");
        this.f15942a.add("111.30.131.31");
        this.f15942a.add("123.126.121.167");
        this.f15942a.add("123.151.152.111");
        this.f15942a.add("113.142.45.79");
        this.f15942a.add("123.138.162.90");
        this.f15942a.add("103.7.30.94");
    }

    /* renamed from: j */
    private String m17938j() {
        try {
            String str = StatConstants.MTA_SERVER_HOST;
            if (!m17936b(str)) {
                return InetAddress.getByName(str).getHostAddress();
            }
        } catch (Throwable e) {
            this.f15949i.m18011e(e);
        }
        return "";
    }

    /* renamed from: k */
    private void m17939k() {
        String j = m17938j();
        if (StatConfig.isDebugEnable()) {
            this.f15949i.m18012i("remoteIp ip is " + j);
        }
        if (C4539k.m18056c(j)) {
            String str;
            if (this.f15942a.contains(j)) {
                str = j;
            } else {
                str = (String) this.f15942a.get(this.f15947f);
                if (StatConfig.isDebugEnable()) {
                    this.f15949i.m18014w(j + " not in ip list, change to:" + str);
                }
            }
            StatConfig.setStatReportUrl("http://" + str + ":80/mstat/report");
        }
    }

    /* renamed from: l */
    private void m17940l() {
        this.f15943b = 0;
        this.f15945d = null;
        this.f15944c = null;
    }

    /* renamed from: a */
    public HttpHost m17941a() {
        return this.f15945d;
    }

    /* renamed from: a */
    public void m17942a(String str) {
        if (StatConfig.isDebugEnable()) {
            this.f15949i.m18012i("updateIpList " + str);
        }
        try {
            if (C4539k.m18056c(str)) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.length() > 0) {
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String string = jSONObject.getString((String) keys.next());
                        if (C4539k.m18056c(string)) {
                            for (String str2 : string.split(C0880h.f2220b)) {
                                String str22;
                                if (C4539k.m18056c(str22)) {
                                    String[] split = str22.split(":");
                                    if (split.length > 1) {
                                        str22 = split[0];
                                        if (m17936b(str22) && !this.f15942a.contains(str22)) {
                                            if (StatConfig.isDebugEnable()) {
                                                this.f15949i.m18012i("add new ip:" + str22);
                                            }
                                            this.f15942a.add(str22);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable e) {
            this.f15949i.m18011e(e);
        }
        this.f15947f = new Random().nextInt(this.f15942a.size());
    }

    /* renamed from: b */
    public String m17943b() {
        return this.f15944c;
    }

    /* renamed from: c */
    public int m17944c() {
        return this.f15943b;
    }

    /* renamed from: d */
    public void m17945d() {
        this.f15947f = (this.f15947f + 1) % this.f15942a.size();
    }

    /* renamed from: e */
    public boolean m17946e() {
        return this.f15943b == 1;
    }

    /* renamed from: f */
    public boolean m17947f() {
        return this.f15943b != 0;
    }

    /* renamed from: g */
    void m17948g() {
        if (C4545q.m18107f(this.f15948h)) {
            if (StatConfig.f15845g) {
                m17939k();
            }
            this.f15944c = C4539k.m18070l(this.f15948h);
            if (StatConfig.isDebugEnable()) {
                this.f15949i.m18012i("NETWORK name:" + this.f15944c);
            }
            if (C4539k.m18056c(this.f15944c)) {
                if ("WIFI".equalsIgnoreCase(this.f15944c)) {
                    this.f15943b = 1;
                } else {
                    this.f15943b = 2;
                }
                this.f15945d = C4539k.m18047a(this.f15948h);
            }
            if (StatServiceImpl.m17881a()) {
                StatServiceImpl.m17891d(this.f15948h);
                return;
            }
            return;
        }
        if (StatConfig.isDebugEnable()) {
            this.f15949i.m18012i("NETWORK TYPE: network is close.");
        }
        m17940l();
    }

    /* renamed from: h */
    public void m17949h() {
        this.f15948h.getApplicationContext().registerReceiver(new C4527b(this), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}
