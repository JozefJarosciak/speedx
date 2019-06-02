package com.alipay.apmobilesecuritysdk.p028g;

import android.content.Context;
import android.os.Environment;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.p029b.p030a.p031a.p032a.p033a.C0788c;
import com.alipay.p029b.p030a.p031a.p038d.C0804a;
import com.alipay.p029b.p030a.p031a.p038d.C0805b;
import com.alipay.p029b.p030a.p031a.p038d.C0806c;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.alipay.apmobilesecuritysdk.g.a */
public final class C0783a {
    /* renamed from: a */
    public static String m3002a(Context context, String str, String str2) {
        String str3 = null;
        if (!(context == null || C0789a.m3020a(str) || C0789a.m3020a(str2))) {
            try {
                String a = C0806c.m3129a(context, str, str2, "");
                if (!C0789a.m3020a(a)) {
                    str3 = C0788c.m3015b(C0788c.m3012a(), a);
                }
            } catch (Exception e) {
            }
        }
        return str3;
    }

    /* renamed from: a */
    public static String m3003a(String str, String str2) {
        String str3 = null;
        if (!(C0789a.m3020a(str) || C0789a.m3020a(str2))) {
            try {
                String a = C0804a.m3126a(str);
                if (!C0789a.m3020a(a)) {
                    a = new JSONObject(a).getString(str2);
                    if (!C0789a.m3020a(a)) {
                        str3 = C0788c.m3015b(C0788c.m3012a(), a);
                    }
                }
            } catch (Exception e) {
            }
        }
        return str3;
    }

    /* renamed from: a */
    public static void m3004a(Context context, String str, String str2, String str3) {
        if (!C0789a.m3020a(str) && !C0789a.m3020a(str2) && context != null) {
            try {
                String a = C0788c.m3013a(C0788c.m3012a(), str3);
                Map hashMap = new HashMap();
                hashMap.put(str2, a);
                C0806c.m3130a(context, str, hashMap);
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    public static void m3005a(String str, String str2, String str3) {
        FileWriter fileWriter;
        Throwable th;
        if (!C0789a.m3020a(str) && !C0789a.m3020a(str2)) {
            try {
                String a = C0788c.m3013a(C0788c.m3012a(), str3);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(str2, a);
                String jSONObject2 = jSONObject.toString();
                try {
                    if (!C0789a.m3020a(jSONObject2)) {
                        System.setProperty(str, jSONObject2);
                    }
                } catch (Throwable th2) {
                }
                if (C0805b.m3128a()) {
                    a = ".SystemConfig" + File.separator + str;
                    try {
                        if (C0805b.m3128a()) {
                            File file = new File(Environment.getExternalStorageDirectory(), a);
                            if (!file.exists()) {
                                file.getParentFile().mkdirs();
                            }
                            File file2 = new File(file.getAbsolutePath());
                            FileWriter fileWriter2 = null;
                            try {
                                fileWriter = new FileWriter(file2, false);
                                try {
                                    fileWriter.write(jSONObject2);
                                    try {
                                        fileWriter.close();
                                    } catch (IOException e) {
                                    }
                                } catch (Exception e2) {
                                    if (fileWriter != null) {
                                        try {
                                            fileWriter.close();
                                        } catch (IOException e3) {
                                        }
                                    }
                                } catch (Throwable th3) {
                                    Throwable th4 = th3;
                                    fileWriter2 = fileWriter;
                                    th = th4;
                                    if (fileWriter2 != null) {
                                        try {
                                            fileWriter2.close();
                                        } catch (IOException e4) {
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Exception e5) {
                                fileWriter = null;
                                if (fileWriter != null) {
                                    fileWriter.close();
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                if (fileWriter2 != null) {
                                    fileWriter2.close();
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e6) {
                    }
                }
            } catch (Exception e7) {
            }
        }
    }
}
