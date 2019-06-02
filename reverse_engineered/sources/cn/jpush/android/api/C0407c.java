package cn.jpush.android.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import cn.jpush.android.C0404a;
import cn.jpush.android.util.C0490b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.api.c */
public final class C0407c implements UncaughtExceptionHandler {
    /* renamed from: b */
    private static C0407c f510b = new C0407c();
    /* renamed from: z */
    private static final String[] f511z;
    /* renamed from: a */
    public boolean f512a = false;
    /* renamed from: c */
    private UncaughtExceptionHandler f513c = null;
    /* renamed from: d */
    private Context f514d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 16;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "h;n,l]>u<ew,s+az(~/pk$u\u0000bk'~";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x00d4;
            case 1: goto L_0x00d7;
            case 2: goto L_0x00db;
            case 3: goto L_0x00df;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 4;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0012;
    L_0x002f:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            case 5: goto L_0x006b;
            case 6: goto L_0x0073;
            case 7: goto L_0x007c;
            case 8: goto L_0x0086;
            case 9: goto L_0x0091;
            case 10: goto L_0x009c;
            case 11: goto L_0x00a7;
            case 12: goto L_0x00b2;
            case 13: goto L_0x00bd;
            case 14: goto L_0x00c8;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "k?r2a";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "l.o(kp D+}r.";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "a9z,l]'t8";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "v2k:";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "a9z,ln$|,";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0063:
        r3[r2] = r1;
        r2 = 6;
        r1 = "l.o(kp o&tg";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006b:
        r3[r2] = r1;
        r2 = 7;
        r1 = "o.h,ee.";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0073:
        r3[r2] = r1;
        r2 = 8;
        r1 = "G3x:tv\"t1";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007c:
        r3[r2] = r1;
        r2 = 9;
        r1 = "q?z<ov9z<a";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0086:
        r3[r2] = r1;
        r2 = 10;
        r1 = "a9z,lv\"v:";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0091:
        r3[r2] = r1;
        r2 = 11;
        r1 = "a$n1p";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009c:
        r3[r2] = r1;
        r2 = 12;
        r1 = "t.i,mm%u>ig";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a7:
        r3[r2] = r1;
        r2 = 13;
        r1 = "G9i0v";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b2:
        r3[r2] = r1;
        r2 = 14;
        r1 = "t.i,mm%x0`g";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00bd:
        r3[r2] = r1;
        r2 = 15;
        r1 = "l>w3";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c8:
        r3[r2] = r1;
        f511z = r4;
        r0 = new cn.jpush.android.api.c;
        r0.<init>();
        f510b = r0;
        return;
    L_0x00d4:
        r9 = 2;
        goto L_0x001f;
    L_0x00d7:
        r9 = 75;
        goto L_0x001f;
    L_0x00db:
        r9 = 27;
        goto L_0x001f;
    L_0x00df:
        r9 = 95;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.c.<clinit>():void");
    }

    private C0407c() {
    }

    /* renamed from: a */
    public static C0407c m1169a() {
        return f510b;
    }

    /* renamed from: a */
    private JSONArray m1170a(Context context, Throwable th, String str) {
        return m1171a(context, C0407c.m1175d(context), th, str);
    }

    /* renamed from: a */
    private JSONArray m1171a(Context context, JSONArray jSONArray, Throwable th, String str) {
        int length;
        Object obj;
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        if (jSONArray == null) {
            jSONArray = new JSONArray();
        }
        Object obj2 = str + th.toString();
        try {
            String[] split = obj2.split(":");
            if (split.length > 1) {
                length = split.length - 1;
                while (length >= 0) {
                    if (split[length].endsWith(f511z[8]) || split[length].endsWith(f511z[13])) {
                        obj2 = split[length];
                        break;
                    }
                    length--;
                }
            }
        } catch (NullPointerException e) {
        } catch (PatternSyntaxException e2) {
        }
        Object obj3 = null;
        length = 0;
        while (length < jSONArray.length()) {
            try {
                JSONObject optJSONObject = jSONArray.optJSONObject(length);
                if (optJSONObject != null && stringWriter2.equals(optJSONObject.getString(f511z[9]))) {
                    optJSONObject.put(f511z[11], optJSONObject.getInt(f511z[11]) + 1);
                    optJSONObject.put(f511z[10], System.currentTimeMillis());
                    obj = optJSONObject;
                    break;
                }
                obj3 = null;
                length++;
            } catch (JSONException e3) {
                return jSONArray;
            } catch (NameNotFoundException e4) {
                return jSONArray;
            }
        }
        length = 0;
        obj = obj3;
        if (obj != null) {
            jSONArray = C0407c.m1172a(jSONArray, length);
            jSONArray.put(obj);
            return jSONArray;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(f511z[10], System.currentTimeMillis());
        jSONObject.put(f511z[9], stringWriter2);
        jSONObject.put(f511z[7], obj2);
        jSONObject.put(f511z[11], 1);
        if (!(this.f514d == null && context == null)) {
            jSONObject.put(f511z[6], C0490b.m1696c(context));
        }
        if (this.f514d != null) {
            PackageInfo packageInfo = this.f514d.getPackageManager().getPackageInfo(this.f514d.getPackageName(), 1);
            if (packageInfo != null) {
                obj2 = packageInfo.versionName == null ? f511z[15] : packageInfo.versionName;
                String str2 = packageInfo.versionCode;
                jSONObject.put(f511z[12], obj2);
                jSONObject.put(f511z[14], str2);
            }
        }
        jSONArray.put(jSONObject);
        return jSONArray;
    }

    /* renamed from: a */
    private static JSONArray m1172a(JSONArray jSONArray, int i) {
        if (jSONArray == null) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            if (i2 != i) {
                try {
                    jSONArray2.put(jSONArray.get(i2));
                } catch (JSONException e) {
                }
            }
        }
        return jSONArray2;
    }

    /* renamed from: a */
    private static void m1173a(Context context, JSONArray jSONArray) {
        String jSONArray2 = jSONArray.toString();
        if (jSONArray2 != null && jSONArray2.length() > 0 && context != null) {
            try {
                FileOutputStream openFileOutput = context.openFileOutput(f511z[0], 0);
                openFileOutput.write(jSONArray2.getBytes());
                openFileOutput.flush();
                openFileOutput.close();
            } catch (FileNotFoundException e) {
            } catch (IOException e2) {
            }
        }
    }

    /* renamed from: b */
    public static void m1174b(Context context) {
        if (context != null) {
            File file = new File(context.getFilesDir(), f511z[0]);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /* renamed from: d */
    private static JSONArray m1175d(Context context) {
        if (!new File(context.getFilesDir(), f511z[0]).exists()) {
            return null;
        }
        JSONArray jSONArray;
        try {
            FileInputStream openFileInput = context.openFileInput(f511z[0]);
            byte[] bArr = new byte[1024];
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                int read = openFileInput.read(bArr);
                if (read == -1) {
                    break;
                }
                stringBuffer.append(new String(bArr, 0, read));
            }
            if (stringBuffer.toString().length() > 0) {
                jSONArray = new JSONArray(stringBuffer.toString());
                return jSONArray;
            }
        } catch (Exception e) {
        }
        jSONArray = null;
        return jSONArray;
    }

    /* renamed from: a */
    public final void m1176a(Context context) {
        this.f514d = context;
        if (this.f513c == null) {
            this.f513c = Thread.getDefaultUncaughtExceptionHandler();
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.f512a = true;
    }

    /* renamed from: a */
    public final void m1177a(Throwable th, String str) {
        if (this.f512a && this.f514d != null) {
            JSONArray a = m1170a(this.f514d, th, str);
            C0407c.m1174b(this.f514d);
            C0407c.m1173a(this.f514d, a);
        }
    }

    /* renamed from: c */
    public final JSONObject m1178c(Context context) {
        JSONObject jSONObject = null;
        if (this.f512a) {
            JSONArray d = C0407c.m1175d(context);
            if (d != null) {
                jSONObject = new JSONObject();
                try {
                    jSONObject.put(f511z[5], d);
                    jSONObject.put(f511z[1], C0404a.m1126m());
                    jSONObject.put(f511z[4], f511z[3]);
                    jSONObject.put(f511z[2], C0490b.m1696c(context));
                } catch (JSONException e) {
                }
            }
        }
        return jSONObject;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        JSONArray a = m1170a(this.f514d, th, "");
        C0407c.m1174b(this.f514d);
        C0407c.m1173a(this.f514d, a);
        if (this.f513c != this) {
            this.f513c.uncaughtException(thread, th);
        }
        throw new RuntimeException(th);
    }
}
