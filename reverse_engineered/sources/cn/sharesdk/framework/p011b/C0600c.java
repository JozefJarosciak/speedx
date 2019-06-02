package cn.sharesdk.framework.p011b;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import cn.sharesdk.framework.p011b.p012a.C0587c;
import cn.sharesdk.framework.p011b.p012a.C0588d;
import cn.sharesdk.framework.p011b.p012a.C0589e;
import cn.sharesdk.framework.p011b.p013b.C0591c;
import cn.sharesdk.framework.utils.C0621d;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.packet.C0861d;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: Protocols */
/* renamed from: cn.sharesdk.framework.b.c */
public class C0600c {
    /* renamed from: a */
    private String f1320a;
    /* renamed from: b */
    private Context f1321b;
    /* renamed from: c */
    private C0589e f1322c = C0589e.m2039a(this.f1321b);
    /* renamed from: d */
    private DeviceHelper f1323d = DeviceHelper.getInstance(this.f1321b);
    /* renamed from: e */
    private NetworkHelper f1324e = new NetworkHelper();
    /* renamed from: f */
    private Hashon f1325f = new Hashon();
    /* renamed from: g */
    private String f1326g;
    /* renamed from: h */
    private String f1327h;
    /* renamed from: i */
    private boolean f1328i;
    /* renamed from: j */
    private HashMap<String, String> f1329j;

    public C0600c(Context context, String str) {
        this.f1320a = str;
        this.f1321b = context.getApplicationContext();
        try {
            this.f1329j = (HashMap) this.f1322c.m2062h("buffered_server_paths");
        } catch (Throwable th) {
            this.f1329j = new HashMap();
        }
        m2140g();
    }

    /* renamed from: g */
    private void m2140g() {
        String str = this.f1323d.getPackageName() + "/" + this.f1323d.getAppVersionName();
        this.f1326g = str + " " + "ShareSDK/2.7.10" + " " + ("Android/" + this.f1323d.getOSVersionInt());
        this.f1327h = "http://api.share.mob.com:80";
        this.f1328i = true;
    }

    /* renamed from: a */
    public void m2151a(String str) {
        this.f1327h = str;
    }

    /* renamed from: a */
    public void m2153a(HashMap<String, String> hashMap) {
        this.f1329j = hashMap;
        this.f1322c.m2045a("buffered_server_paths", this.f1329j);
    }

    /* renamed from: h */
    private String m2141h() {
        return this.f1327h + "/conn";
    }

    /* renamed from: a */
    public HashMap<String, Object> m2148a() throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appkey", this.f1320a));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", this.f1326g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        C0621d.m2279a().i(" isConnectToServer response == %s", new Object[]{this.f1324e.httpPost(m2141h(), arrayList, null, arrayList2, networkTimeOut)});
        return this.f1325f.fromJson(this.f1324e.httpPost(m2141h(), arrayList, null, arrayList2, networkTimeOut));
    }

    /* renamed from: i */
    private String m2142i() {
        if (this.f1329j == null || !this.f1329j.containsKey("/date")) {
            return this.f1327h + "/date";
        }
        return ((String) this.f1329j.get("/date")) + "/date";
    }

    /* renamed from: b */
    public long m2155b() throws Throwable {
        if (!this.f1322c.m2060g()) {
            return 0;
        }
        String str = "{}";
        try {
            str = this.f1324e.httpGet(m2142i(), null, null, null);
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
        HashMap fromJson = this.f1325f.fromJson(str);
        if (!fromJson.containsKey("timestamp")) {
            return this.f1322c.m2040a();
        }
        try {
            long currentTimeMillis = System.currentTimeMillis() - C4275R.parseLong(String.valueOf(fromJson.get("timestamp")));
            this.f1322c.m2044a("service_time", Long.valueOf(currentTimeMillis));
            return currentTimeMillis;
        } catch (Throwable th2) {
            C0621d.m2279a().d(th2);
            return this.f1322c.m2040a();
        }
    }

    /* renamed from: j */
    private String m2143j() {
        return this.f1327h + "/conf5";
    }

    /* renamed from: c */
    public HashMap<String, Object> m2158c() throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appkey", this.f1320a));
        arrayList.add(new KVPair(C0861d.f2142n, this.f1323d.getDeviceKey()));
        arrayList.add(new KVPair("plat", String.valueOf(this.f1323d.getPlatformCode())));
        arrayList.add(new KVPair("apppkg", this.f1323d.getPackageName()));
        arrayList.add(new KVPair("appver", String.valueOf(this.f1323d.getAppVersion())));
        arrayList.add(new KVPair("sdkver", String.valueOf(60068)));
        arrayList.add(new KVPair("networktype", this.f1323d.getDetailNetworkTypeForStatic()));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", this.f1326g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 10000;
        networkTimeOut.connectionTimeout = 10000;
        C0621d.m2279a().i(" get server config response == %s", new Object[]{this.f1324e.httpPost(m2143j(), arrayList, null, arrayList2, networkTimeOut)});
        return this.f1325f.fromJson(this.f1324e.httpPost(m2143j(), arrayList, null, arrayList2, networkTimeOut));
    }

    /* renamed from: k */
    private String m2144k() {
        return "http://up.sharesdk.cn/upload/image";
    }

    /* renamed from: b */
    public HashMap<String, Object> m2156b(String str) throws Throwable {
        KVPair kVPair = new KVPair(Action.FILE_ATTRIBUTE, str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("User-Agent", this.f1326g));
        C0621d.m2279a().i("upload file response == %s", new Object[]{this.f1324e.httpPost(m2144k(), null, kVPair, arrayList, null)});
        return this.f1325f.fromJson(this.f1324e.httpPost(m2144k(), null, kVPair, arrayList, null));
    }

    /* renamed from: l */
    private String m2145l() {
        if (this.f1329j == null || !this.f1329j.containsKey("/log4")) {
            return this.f1327h + "/log4";
        }
        return ((String) this.f1329j.get("/log4")) + "/log4";
    }

    /* renamed from: a */
    public boolean m2154a(String str, boolean z) {
        try {
            if ("none".equals(this.f1323d.getDetailNetworkTypeForStatic())) {
                throw new IllegalStateException("network is disconnected!");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair(ANSIConstants.ESC_END, str));
            arrayList.add(new KVPair("t", z ? C0844a.f2048d : "0"));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("User-Agent", this.f1326g));
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = 30000;
            Object httpPost = this.f1324e.httpPost(m2145l(), arrayList, null, arrayList2, networkTimeOut);
            C0621d.m2279a().i("> Upload All Log  resp: %s", new Object[]{httpPost});
            if (TextUtils.isEmpty(httpPost) || ((Integer) this.f1325f.fromJson(httpPost).get("status")).intValue() == 200) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return false;
        }
    }

    /* renamed from: m */
    private String m2146m() {
        return "http://l.mob.com/url/ShareSdkMapping.do";
    }

    /* renamed from: a */
    public HashMap<String, Object> m2149a(String str, ArrayList<String> arrayList, int i, String str2) throws Throwable {
        if (!this.f1328i) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(Action.KEY_ATTRIBUTE, this.f1320a));
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList2.add(new KVPair("urls", ((String) arrayList.get(i2)).toString()));
        }
        arrayList2.add(new KVPair("deviceid", this.f1323d.getDeviceKey()));
        arrayList2.add(new KVPair("snsplat", String.valueOf(i)));
        CharSequence d = m2139d(str2);
        if (TextUtils.isEmpty(d)) {
            return null;
        }
        arrayList2.add(new KVPair(ANSIConstants.ESC_END, d));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new KVPair("User-Agent", this.f1326g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 5000;
        networkTimeOut.connectionTimeout = 5000;
        Object httpPost = this.f1324e.httpPost(m2146m(), arrayList2, null, arrayList3, networkTimeOut);
        C0621d.m2279a().i("> SERVER_SHORT_LINK_URL  resp: %s", new Object[]{httpPost});
        if (TextUtils.isEmpty(httpPost)) {
            this.f1328i = false;
            return null;
        }
        HashMap<String, Object> fromJson = this.f1325f.fromJson(httpPost);
        if (((Integer) fromJson.get("status")).intValue() == 200) {
            return fromJson;
        }
        return null;
    }

    /* renamed from: d */
    private String m2139d(String str) throws Throwable {
        boolean b = this.f1322c.m2050b();
        boolean c = this.f1322c.m2052c();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Data.urlEncode(this.f1323d.getPackageName(), "utf-8")).append("|");
        stringBuilder.append(Data.urlEncode(this.f1323d.getAppVersionName(), "utf-8")).append("|");
        stringBuilder.append(Data.urlEncode(String.valueOf(60068), "utf-8")).append("|");
        stringBuilder.append(Data.urlEncode(String.valueOf(this.f1323d.getPlatformCode()), "utf-8")).append("|");
        stringBuilder.append(Data.urlEncode(this.f1323d.getDetailNetworkTypeForStatic(), "utf-8")).append("|");
        if (b) {
            stringBuilder.append(Data.urlEncode(String.valueOf(this.f1323d.getOSVersionInt()), "utf-8")).append("|");
            stringBuilder.append(Data.urlEncode(this.f1323d.getScreenSize(), "utf-8")).append("|");
            stringBuilder.append(Data.urlEncode(this.f1323d.getManufacturer(), "utf-8")).append("|");
            stringBuilder.append(Data.urlEncode(this.f1323d.getModel(), "utf-8")).append("|");
            stringBuilder.append(Data.urlEncode(this.f1323d.getCarrier(), "utf-8")).append("|");
        } else {
            stringBuilder.append("|||||");
        }
        if (c) {
            stringBuilder.append(str);
        } else {
            stringBuilder.append(str.split("\\|")[0]);
            stringBuilder.append("|||||");
        }
        C0621d.m2279a().i("shorLinkMsg ===>>>>", new Object[]{stringBuilder.toString()});
        return Base64.encodeToString(Data.AES128Encode(Data.rawMD5(String.format("%s:%s", new Object[]{this.f1323d.getDeviceKey(), this.f1320a})), stringBuilder.toString()), 2);
    }

    /* renamed from: n */
    private String m2147n() {
        if (this.f1329j == null || !this.f1329j.containsKey("/snsconf")) {
            return this.f1327h + "/snsconf";
        }
        return ((String) this.f1329j.get("/snsconf")) + "/snsconf";
    }

    /* renamed from: d */
    public HashMap<String, Object> m2160d() throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appkey", this.f1320a));
        arrayList.add(new KVPair(C0861d.f2142n, this.f1323d.getDeviceKey()));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", this.f1326g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 10000;
        networkTimeOut.connectionTimeout = 10000;
        return this.f1325f.fromJson(this.f1324e.httpPost(m2147n(), arrayList, null, arrayList2, networkTimeOut));
    }

    /* renamed from: a */
    public void m2150a(C0591c c0591c) throws Throwable {
        C0588d.m2035a(this.f1321b, c0591c.toString(), c0591c.f1273e);
    }

    /* renamed from: e */
    public ArrayList<C0587c> m2161e() throws Throwable {
        ArrayList<C0587c> a = C0588d.m2037a(this.f1321b);
        if (a == null) {
            return new ArrayList();
        }
        return a;
    }

    /* renamed from: a */
    public void m2152a(ArrayList<String> arrayList) throws Throwable {
        C0588d.m2036a(this.f1321b, arrayList);
    }

    /* renamed from: f */
    public HashMap<String, Object> m2162f() throws Throwable {
        return this.f1325f.fromJson(this.f1322c.m2056e(this.f1320a));
    }

    /* renamed from: b */
    public void m2157b(HashMap<String, Object> hashMap) throws Throwable {
        this.f1322c.m2046a(this.f1320a, this.f1325f.fromHashMap(hashMap));
    }

    /* renamed from: c */
    public HashMap<String, Object> m2159c(String str) throws Throwable {
        return this.f1325f.fromJson(new String(Data.AES128Decode(Data.rawMD5(this.f1320a + ":" + this.f1323d.getDeviceKey()), Base64.decode(str, 2)), "UTF-8").trim());
    }
}
