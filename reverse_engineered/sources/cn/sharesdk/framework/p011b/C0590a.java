package cn.sharesdk.framework.p011b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.p011b.p012a.C0587c;
import cn.sharesdk.framework.p011b.p012a.C0589e;
import cn.sharesdk.framework.p011b.p013b.C0591c;
import cn.sharesdk.framework.p011b.p013b.C0593b;
import cn.sharesdk.framework.p011b.p013b.C0597f;
import cn.sharesdk.framework.p011b.p013b.C0597f.C0596a;
import cn.sharesdk.framework.utils.C0621d;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.cons.C0846c;
import com.alipay.sdk.packet.C0861d;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

/* compiled from: EventManager */
/* renamed from: cn.sharesdk.framework.b.a */
public class C0590a {
    /* renamed from: a */
    private static C0590a f1268a;
    /* renamed from: b */
    private C0600c f1269b;
    /* renamed from: c */
    private DeviceHelper f1270c;
    /* renamed from: d */
    private C0589e f1271d;
    /* renamed from: e */
    private boolean f1272e;

    /* renamed from: a */
    public static C0590a m2063a(Context context, String str) {
        if (f1268a == null) {
            f1268a = new C0590a(context, str);
        }
        return f1268a;
    }

    private C0590a(Context context, String str) {
        this.f1269b = new C0600c(context, str);
        this.f1271d = C0589e.m2039a(context);
        this.f1270c = DeviceHelper.getInstance(context);
    }

    /* renamed from: a */
    public void m2075a() {
        try {
            CharSequence networkType = this.f1270c.getNetworkType();
            if (!"none".equals(networkType) && !TextUtils.isEmpty(networkType)) {
                long longValue = this.f1271d.m2061h().longValue();
                long currentTimeMillis = System.currentTimeMillis();
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(longValue);
                int i = instance.get(5);
                instance.setTimeInMillis(currentTimeMillis);
                int i2 = instance.get(5);
                if (currentTimeMillis - longValue >= 86400000 || i != i2) {
                    HashMap a = this.f1269b.m2148a();
                    this.f1271d.m2047a(a.containsKey("res") ? "true".equals(String.valueOf(a.get("res"))) : true);
                    if (a != null && a.size() > 0) {
                        this.f1271d.m2048b(System.currentTimeMillis());
                    }
                }
            }
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
    }

    /* renamed from: b */
    public void m2080b() {
        try {
            CharSequence networkType = this.f1270c.getNetworkType();
            if (!"none".equals(networkType) && !TextUtils.isEmpty(networkType) && this.f1271d.m2060g()) {
                this.f1271d.m2041a(System.currentTimeMillis());
                HashMap c = this.f1269b.m2158c();
                if (c.containsKey("status") && C4275R.parseInt(String.valueOf(c.get("status"))) == -200) {
                    C0621d.m2279a().d((String) c.get("error"), new Object[0]);
                    return;
                }
                HashMap hashMap;
                if (c.containsKey("timestamp")) {
                    this.f1271d.m2044a("service_time", Long.valueOf(System.currentTimeMillis() - C4275R.parseLong(String.valueOf(c.get("timestamp")))));
                }
                if (c.containsKey("switchs")) {
                    hashMap = (HashMap) c.get("switchs");
                    if (hashMap != null) {
                        String valueOf = String.valueOf(hashMap.get(C0861d.f2142n));
                        String valueOf2 = String.valueOf(hashMap.get("share"));
                        String valueOf3 = String.valueOf(hashMap.get(C0825c.f1954d));
                        String valueOf4 = String.valueOf(hashMap.get("backflow"));
                        this.f1271d.m2049b(valueOf);
                        this.f1271d.m2053d(valueOf2);
                        this.f1271d.m2051c(valueOf3);
                        this.f1271d.m2042a(valueOf4);
                    }
                }
                if (c.containsKey("serpaths")) {
                    hashMap = (HashMap) c.get("serpaths");
                    if (hashMap != null) {
                        Object valueOf5 = String.valueOf(hashMap.get("defhost"));
                        Object valueOf6 = String.valueOf(hashMap.get("defport"));
                        if (!(TextUtils.isEmpty(valueOf5) || TextUtils.isEmpty(valueOf6))) {
                            this.f1269b.m2151a("http://" + valueOf5 + ":" + valueOf6);
                        }
                        HashMap hashMap2 = new HashMap();
                        if (hashMap.containsKey("assigns")) {
                            hashMap = (HashMap) hashMap.get("assigns");
                            if (hashMap == null || hashMap.size() == 0) {
                                this.f1269b.m2153a(null);
                                return;
                            }
                            for (String str : hashMap.keySet()) {
                                HashMap hashMap3 = (HashMap) hashMap.get(str);
                                Object valueOf7 = String.valueOf(hashMap3.get(C0846c.f2075f));
                                valueOf6 = String.valueOf(hashMap3.get("port"));
                                if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(valueOf7) || TextUtils.isEmpty(valueOf6))) {
                                    hashMap2.put(str, "http://" + valueOf7 + ":" + valueOf6);
                                }
                            }
                            this.f1269b.m2153a(hashMap2);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
    }

    /* renamed from: a */
    public void m2078a(boolean z) {
        this.f1272e = z;
    }

    /* renamed from: a */
    public void m2076a(C0591c c0591c) {
        try {
            if (this.f1271d.m2060g()) {
                if (c0591c instanceof C0593b) {
                    m2067a((C0593b) c0591c);
                } else if (c0591c instanceof C0597f) {
                    m2068a((C0597f) c0591c);
                }
                if (!this.f1271d.m2050b()) {
                    c0591c.f1281m = null;
                }
                long a = this.f1271d.m2040a();
                if (a == 0) {
                    a = this.f1269b.m2155b();
                }
                c0591c.f1273e = System.currentTimeMillis() - a;
                this.f1269b.m2150a(c0591c);
            }
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
    }

    /* renamed from: a */
    private void m2067a(C0593b c0593b) throws Throwable {
        boolean c = this.f1271d.m2052c();
        Object obj = c0593b.f1290c;
        if (!c || TextUtils.isEmpty(obj)) {
            c0593b.f1291d = null;
            c0593b.f1290c = null;
            return;
        }
        c0593b.f1290c = Data.Base64AES(obj, c0593b.f.substring(0, 16));
    }

    /* renamed from: a */
    private void m2068a(C0597f c0597f) throws Throwable {
        int i = 0;
        int e = this.f1271d.m2055e();
        boolean c = this.f1271d.m2052c();
        C0596a c0596a = c0597f.f1312d;
        if (e == 1 || (e == 0 && this.f1272e)) {
            CharSequence a;
            int size = (c0596a == null || c0596a.f1304e == null) ? 0 : c0596a.f1304e.size();
            for (int i2 = 0; i2 < size; i2++) {
                a = m2065a((String) c0596a.f1304e.get(i2), C0599b.FINISH_SHARE);
                if (!TextUtils.isEmpty(a)) {
                    c0596a.f1303d.add(a);
                }
            }
            size = (c0596a == null || c0596a.f1305f == null) ? 0 : c0596a.f1305f.size();
            while (i < size) {
                a = m2064a((Bitmap) c0596a.f1305f.get(i), C0599b.FINISH_SHARE);
                if (!TextUtils.isEmpty(a)) {
                    c0596a.f1303d.add(a);
                }
                i++;
            }
        } else {
            c0597f.f1312d = null;
        }
        if (!c) {
            c0597f.f1313n = null;
        }
    }

    /* renamed from: a */
    private String m2065a(String str, C0599b c0599b) throws Throwable {
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return null;
        }
        CharSequence networkType = this.f1270c.getNetworkType();
        if ("none".equals(networkType) || TextUtils.isEmpty(networkType)) {
            return null;
        }
        int ceil;
        CompressFormat bmpFormat = BitmapHelper.getBmpFormat(str);
        float f = 200.0f;
        if (c0599b == C0599b.BEFORE_SHARE) {
            f = 600.0f;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (i >= i2 && ((float) i2) > f) {
            ceil = (int) Math.ceil((double) (((float) options.outHeight) / f));
        } else if (i >= i2 || ((float) i) <= f) {
            return m2070c(str);
        } else {
            ceil = (int) Math.ceil((double) (((float) options.outWidth) / f));
        }
        if (ceil <= 0) {
            ceil = 1;
        }
        options = new Options();
        options.inSampleSize = ceil;
        options.inPurgeable = true;
        options.inInputShareable = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        decodeFile.getHeight();
        decodeFile.getWidth();
        File createTempFile = File.createTempFile("bm_tmp2", "." + bmpFormat.name().toLowerCase());
        OutputStream fileOutputStream = new FileOutputStream(createTempFile);
        decodeFile.compress(bmpFormat, 80, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return m2070c(createTempFile.getAbsolutePath());
    }

    /* renamed from: c */
    private String m2070c(String str) throws Throwable {
        HashMap b = this.f1269b.m2156b(str);
        if (b != null && b.size() > 0 && b.containsKey("status") && C4275R.parseInt(String.valueOf(b.get("status"))) == 200 && b.containsKey("url")) {
            return (String) b.get("url");
        }
        return null;
    }

    /* renamed from: a */
    private String m2064a(Bitmap bitmap, C0599b c0599b) throws Throwable {
        File createTempFile = File.createTempFile("bm_tmp", ".png");
        OutputStream fileOutputStream = new FileOutputStream(createTempFile);
        bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return m2065a(createTempFile.getAbsolutePath(), c0599b);
    }

    /* renamed from: c */
    public void m2081c() {
        try {
            CharSequence networkType = this.f1270c.getNetworkType();
            if (!"none".equals(networkType) && !TextUtils.isEmpty(networkType) && this.f1271d.m2060g()) {
                ArrayList e = this.f1269b.m2161e();
                for (int i = 0; i < e.size(); i++) {
                    boolean a;
                    C0587c c0587c = (C0587c) e.get(i);
                    if (c0587c.f1261b.size() == 1) {
                        a = m2069a(c0587c.f1260a, false);
                    } else {
                        a = m2069a(m2071d(c0587c.f1260a), true);
                    }
                    if (a) {
                        this.f1269b.m2152a(c0587c.f1261b);
                    }
                }
            }
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
    }

    /* renamed from: d */
    private String m2071d(String str) throws Throwable {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = byteArrayInputStream.read(bArr, 0, 1024);
            if (read != -1) {
                gZIPOutputStream.write(bArr, 0, read);
            } else {
                gZIPOutputStream.flush();
                gZIPOutputStream.close();
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                byteArrayInputStream.close();
                return Base64.encodeToString(toByteArray, 2);
            }
        }
    }

    /* renamed from: a */
    private boolean m2069a(String str, boolean z) throws Throwable {
        return this.f1269b.m2154a(str, z);
    }

    /* renamed from: a */
    public String m2074a(String str, int i, boolean z, String str2) {
        try {
            if (!this.f1271d.m2060g() || !this.f1271d.m2054d()) {
                return str;
            }
            CharSequence networkType = this.f1270c.getNetworkType();
            if ("none".equals(networkType) || TextUtils.isEmpty(networkType)) {
                return str;
            }
            String a;
            if (z) {
                a = m2066a(str, "<a[^>]*?href[\\s]*=[\\s]*[\"']?([^'\">]+?)['\"]?>", i, str2);
                if (!(a == null || a.equals(str))) {
                    return a;
                }
            }
            a = m2066a(str, "(http://|https://){1}[\\w\\.\\-/:\\?&%=,;\\[\\]\\{\\}`~!@#\\$\\^\\*\\(\\)_\\+\\\\\\|]+", i, str2);
            if (a == null || a.equals(str)) {
                return str;
            }
            return a;
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return str;
        }
    }

    /* renamed from: a */
    private String m2066a(String str, String str2, int i, String str3) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        ArrayList arrayList = new ArrayList();
        Pattern compile = Pattern.compile(str2);
        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            if (group != null && group.length() > 0) {
                arrayList.add(group);
            }
        }
        if (arrayList.size() == 0) {
            return str;
        }
        HashMap a = this.f1269b.m2149a(str, arrayList, i, str3);
        if (a == null || a.size() <= 0 || !a.containsKey(C0861d.f2139k)) {
            return str;
        }
        arrayList = (ArrayList) a.get(C0861d.f2139k);
        HashMap hashMap = new HashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a = (HashMap) it.next();
            hashMap.put(String.valueOf(a.get(MapboxEvent.ATTRIBUTE_SOURCE)), String.valueOf(a.get("surl")));
        }
        Matcher matcher2 = compile.matcher(str);
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = 0;
        while (matcher2.find()) {
            stringBuilder.append(str.substring(i2, matcher2.start()));
            stringBuilder.append((String) hashMap.get(matcher2.group()));
            i2 = matcher2.end();
        }
        stringBuilder.append(str.substring(i2, str.length()));
        C0621d.m2279a().i("> SERVER_SHORT_LINK_URL content after replace link ===  %s", new Object[]{stringBuilder.toString()});
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public String m2073a(String str) {
        String str2 = null;
        if (this.f1271d.m2060g()) {
            try {
                str2 = m2065a(str, C0599b.BEFORE_SHARE);
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
        return str2;
    }

    /* renamed from: a */
    public String m2072a(Bitmap bitmap) {
        String str = null;
        if (this.f1271d.m2060g()) {
            try {
                str = m2064a(bitmap, C0599b.BEFORE_SHARE);
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
        return str;
    }

    /* renamed from: d */
    public HashMap<String, Object> m2082d() {
        try {
            return this.f1269b.m2162f();
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return new HashMap();
        }
    }

    /* renamed from: e */
    public HashMap<String, Object> m2083e() {
        if (!this.f1271d.m2060g()) {
            return new HashMap();
        }
        try {
            return this.f1269b.m2160d();
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return new HashMap();
        }
    }

    /* renamed from: a */
    public void m2077a(HashMap<String, Object> hashMap) {
        try {
            this.f1269b.m2157b((HashMap) hashMap);
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
    }

    /* renamed from: b */
    public HashMap<String, Object> m2079b(String str) {
        try {
            return this.f1269b.m2159c(str);
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return new HashMap();
        }
    }
}
