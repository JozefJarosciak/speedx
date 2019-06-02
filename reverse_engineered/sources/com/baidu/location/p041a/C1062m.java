package com.baidu.location.p041a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.alipay.sdk.util.C0880h;
import com.baidu.location.C1102f;
import com.baidu.location.Jni;
import com.baidu.location.p042d.C1041e;
import com.baidu.location.p042d.C1091b;
import com.baidu.location.p042d.C1092c;
import com.baidu.location.p042d.C1099i;
import com.baidu.location.p042d.C1100j;
import com.baidu.location.p043b.C1074b;
import com.baidu.location.p043b.C1075c;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/* renamed from: com.baidu.location.a.m */
public class C1062m extends C1041e {
    /* renamed from: p */
    private static C1062m f2508p = null;
    /* renamed from: a */
    String f2509a;
    /* renamed from: b */
    String f2510b;
    /* renamed from: c */
    String f2511c;
    /* renamed from: d */
    String f2512d;
    /* renamed from: e */
    int f2513e;
    /* renamed from: f */
    Handler f2514f;

    private C1062m() {
        this.f2509a = null;
        this.f2510b = null;
        this.f2511c = null;
        this.f2512d = null;
        this.f2513e = 1;
        this.f2514f = null;
        this.f2514f = new Handler();
    }

    /* renamed from: a */
    public static void m3806a(File file, File file2) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = null;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
            try {
                byte[] bArr = new byte[5120];
                while (true) {
                    int read = bufferedInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                bufferedOutputStream.flush();
                file.delete();
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedOutputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    private boolean m3807a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                String a = C1075c.m3887a(C1074b.m3866a().m3883e());
                if (a.equals("3G") || a.equals("4G")) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m3809a(String str, String str2) {
        File file = new File(C1100j.m4025g() + File.separator + "tmp");
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[4096];
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            httpURLConnection.disconnect();
            fileOutputStream.close();
            if (file.length() < 10240) {
                file.delete();
                return false;
            }
            file.renameTo(new File(C1100j.m4025g() + File.separator + str2));
            return true;
        } catch (Exception e) {
            file.delete();
            return false;
        }
    }

    /* renamed from: b */
    public static C1062m m3810b() {
        if (f2508p == null) {
            f2508p = new C1062m();
        }
        return f2508p;
    }

    /* renamed from: f */
    private Handler m3813f() {
        return this.f2514f;
    }

    /* renamed from: g */
    private void m3814g() {
        try {
            RandomAccessFile randomAccessFile;
            File file = new File(C1100j.m4025g() + "/grtcfrsa.dat");
            if (!file.exists()) {
                File file2 = new File(C1099i.f2702a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (file.createNewFile()) {
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(2);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.seek(8);
                    byte[] bytes = "1980_01_01:0".getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes);
                    randomAccessFile.seek(200);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.seek(800);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.close();
                } else {
                    return;
                }
            }
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(200);
            randomAccessFile.writeBoolean(true);
            if (this.f2513e == 1) {
                randomAccessFile.writeBoolean(true);
            } else {
                randomAccessFile.writeBoolean(false);
            }
            if (this.f2512d != null) {
                byte[] bytes2 = this.f2512d.getBytes();
                randomAccessFile.writeInt(bytes2.length);
                randomAccessFile.write(bytes2);
            } else if (Math.abs(C1102f.getFrameVersion() - 7.12f) < 1.0E-8f) {
                randomAccessFile.writeInt(0);
            }
            randomAccessFile.close();
        } catch (Exception e) {
        }
    }

    /* renamed from: h */
    private void m3815h() {
        if (this.f2509a != null) {
            new C1066q(this).start();
        }
    }

    /* renamed from: i */
    private boolean m3816i() {
        return (this.f2511c == null || new File(C1100j.m4025g() + File.separator + this.f2511c).exists()) ? true : C1062m.m3809a("http://" + this.f2509a + "/" + this.f2511c, this.f2511c);
    }

    /* renamed from: j */
    private void m3817j() {
        if (this.f2510b != null) {
            File file = new File(C1100j.m4025g() + File.separator + this.f2510b);
            if (!file.exists() && C1062m.m3809a("http://" + this.f2509a + "/" + this.f2510b, this.f2510b)) {
                String a = C1100j.m4012a(file, "SHA-256");
                if (this.f2512d != null && a != null && C1100j.m4018b(a, this.f2512d, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiP7BS5IjEOzrKGR9/Ww9oSDhdX1ir26VOsYjT1T6tk2XumRpkHRwZbrucDcNnvSB4QsqiEJnvTSRi7YMbh2H9sLMkcvHlMV5jAErNvnuskWfcvf7T2mq7EUZI/Hf4oVZhHV0hQJRFVdTcjWI6q2uaaKM3VMh+roDesiE7CR2biQIDAQAB")) {
                    File file2 = new File(C1100j.m4025g() + File.separator + C1102f.replaceFileName);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    try {
                        C1062m.m3806a(file, file2);
                    } catch (Exception e) {
                        file2.delete();
                    }
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public void mo2597a() {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&sdk=");
        stringBuffer.append(7.12f);
        stringBuffer.append("&fw=");
        stringBuffer.append(C1102f.getFrameVersion());
        stringBuffer.append("&suit=");
        stringBuffer.append(2);
        if (C1091b.m3989a().f2674b == null) {
            stringBuffer.append("&im=");
            stringBuffer.append(C1091b.m3989a().f2673a);
        } else {
            stringBuffer.append("&cu=");
            stringBuffer.append(C1091b.m3989a().f2674b);
        }
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        stringBuffer.append("&sv=");
        String str = VERSION.RELEASE;
        if (str != null && str.length() > 10) {
            str = str.substring(0, 10);
        }
        stringBuffer.append(str);
        try {
            if (VERSION.SDK_INT > 20) {
                String[] strArr = Build.SUPPORTED_ABIS;
                str = null;
                while (i < strArr.length) {
                    str = i == 0 ? strArr[i] + C0880h.f2220b : str + strArr[i] + C0880h.f2220b;
                    i++;
                }
            } else {
                str = Build.CPU_ABI2;
            }
        } catch (Error e) {
            str = null;
        } catch (Exception e2) {
            str = null;
        }
        if (str != null) {
            stringBuffer.append("&cpuabi=");
            stringBuffer.append(str);
        }
        stringBuffer.append("&pack=");
        stringBuffer.append(C1091b.f2668d);
        this.h = C1100j.m4021d() + "?&it=" + Jni.en1(stringBuffer.toString());
    }

    /* renamed from: a */
    public void mo2598a(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(this.j);
                if ("up".equals(jSONObject.getString("res"))) {
                    this.f2509a = jSONObject.getString("upath");
                    if (jSONObject.has("u1")) {
                        this.f2510b = jSONObject.getString("u1");
                    }
                    if (jSONObject.has("u2")) {
                        this.f2511c = jSONObject.getString("u2");
                    }
                    if (jSONObject.has("u1_rsa")) {
                        this.f2512d = jSONObject.getString("u1_rsa");
                    }
                    m3813f().post(new C1065p(this));
                }
                if (jSONObject.has("ison")) {
                    this.f2513e = jSONObject.getInt("ison");
                }
                m3814g();
            } catch (Exception e) {
            }
        }
        C1092c.m3997a().m3999a(System.currentTimeMillis());
    }

    /* renamed from: c */
    public void mo2602c() {
        if (System.currentTimeMillis() - C1092c.m3997a().m4000b() > 86400000) {
            m3813f().postDelayed(new C1063n(this), AbstractComponentTracker.LINGERING_TIMEOUT);
            m3813f().postDelayed(new C1064o(this), 5000);
        }
    }
}
