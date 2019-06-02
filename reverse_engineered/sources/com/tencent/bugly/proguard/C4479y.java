package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Process;
import ch.qos.logback.core.joran.action.ActionConst;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.HttpHost;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.y */
public class C4479y {
    /* renamed from: a */
    private static Map<String, String> f15797a = null;

    /* renamed from: a */
    public static String m17781a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            Writer stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (!C4475w.m17748a(th2)) {
                th2.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: a */
    public static String m17777a() {
        return C4479y.m17778a(System.currentTimeMillis());
    }

    /* renamed from: a */
    public static String m17778a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(j));
        } catch (Exception e) {
            return new Date().toString();
        }
    }

    /* renamed from: a */
    public static String m17782a(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        } catch (Exception e) {
            return new Date().toString();
        }
    }

    /* renamed from: a */
    private static byte[] m17799a(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        C4475w.m17751c("rqdp{  enD:} %d %d", Integer.valueOf(bArr.length), Integer.valueOf(i));
        try {
            af a = C4446a.m17522a(i);
            if (a == null) {
                return null;
            }
            a.mo6067a(str);
            return a.mo6069b(bArr);
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    private static byte[] m17811b(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        try {
            af a = C4446a.m17522a(i);
            if (a == null) {
                return null;
            }
            a.mo6067a(str);
            return a.mo6068a(bArr);
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
            C4475w.m17752d("encrytype %d %s", Integer.valueOf(i), str);
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m17796a(File file, String str) {
        FileInputStream fileInputStream;
        ByteArrayInputStream byteArrayInputStream;
        OutputStream byteArrayOutputStream;
        ZipOutputStream zipOutputStream;
        byte[] bArr;
        int read;
        Throwable th;
        Throwable th2;
        byte[] bArr2 = null;
        C4475w.m17751c("rqdp{  ZF start}", new Object[0]);
        String str2 = "buglyCacheLog.txt";
        if (file != null) {
            try {
                if (file.exists() && file.canRead()) {
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        str2 = file.getName();
                        fileInputStream = fileInputStream2;
                        byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                        try {
                            zipOutputStream.setMethod(8);
                            zipOutputStream.putNextEntry(new ZipEntry(str2));
                            bArr = new byte[1024];
                            if (fileInputStream != null) {
                                while (true) {
                                    read = fileInputStream.read(bArr);
                                    if (read > 0) {
                                        zipOutputStream.write(bArr, 0, read);
                                    }
                                }
                                read = byteArrayInputStream.read(bArr);
                                if (read <= 0) {
                                    zipOutputStream.write(bArr, 0, read);
                                } else {
                                    zipOutputStream.closeEntry();
                                    zipOutputStream.flush();
                                    zipOutputStream.finish();
                                    bArr2 = byteArrayOutputStream.toByteArray();
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    zipOutputStream.close();
                                    C4475w.m17751c("rqdp{  ZF end}", new Object[0]);
                                    return bArr2;
                                }
                            }
                            while (true) {
                                read = byteArrayInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                zipOutputStream.write(bArr, 0, read);
                            }
                            zipOutputStream.closeEntry();
                            zipOutputStream.flush();
                            zipOutputStream.finish();
                            bArr2 = byteArrayOutputStream.toByteArray();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            zipOutputStream.close();
                            C4475w.m17751c("rqdp{  ZF end}", new Object[0]);
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        fileInputStream = fileInputStream2;
                        zipOutputStream = bArr2;
                        th2 = th4;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (zipOutputStream != null) {
                            zipOutputStream.close();
                        }
                        C4475w.m17751c("rqdp{  ZF end}", new Object[0]);
                        throw th2;
                    }
                    return bArr2;
                }
            } catch (Throwable th42) {
                zipOutputStream = bArr2;
                fileInputStream = bArr2;
                th2 = th42;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                C4475w.m17751c("rqdp{  ZF end}", new Object[0]);
                throw th2;
            }
        }
        fileInputStream = bArr2;
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
            byteArrayOutputStream = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            zipOutputStream.setMethod(8);
            zipOutputStream.putNextEntry(new ZipEntry(str2));
            bArr = new byte[1024];
            if (fileInputStream != null) {
                while (true) {
                    read = fileInputStream.read(bArr);
                    if (read > 0) {
                        zipOutputStream.write(bArr, 0, read);
                    }
                }
                read = byteArrayInputStream.read(bArr);
                if (read <= 0) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    zipOutputStream.closeEntry();
                    zipOutputStream.flush();
                    zipOutputStream.finish();
                    bArr2 = byteArrayOutputStream.toByteArray();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    zipOutputStream.close();
                    C4475w.m17751c("rqdp{  ZF end}", new Object[0]);
                    return bArr2;
                }
            }
            while (true) {
                read = byteArrayInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                zipOutputStream.write(bArr, 0, read);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.flush();
            zipOutputStream.finish();
            bArr2 = byteArrayOutputStream.toByteArray();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            try {
                zipOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            C4475w.m17751c("rqdp{  ZF end}", new Object[0]);
        } catch (Throwable th422) {
            zipOutputStream = bArr2;
            th2 = th422;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            C4475w.m17751c("rqdp{  ZF end}", new Object[0]);
            throw th2;
        }
        return bArr2;
    }

    /* renamed from: a */
    public static byte[] m17797a(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        String str = "[Util] Zip %d bytes data with type %s";
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(bArr.length);
        objArr[1] = i == 2 ? "Gzip" : "zip";
        C4475w.m17751c(str, objArr);
        try {
            aa a = C4480z.m17815a(i);
            if (a == null) {
                return null;
            }
            return a.mo6065a(bArr);
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m17809b(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        String str = "[Util] Unzip %d bytes data with type %s";
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(bArr.length);
        objArr[1] = i == 2 ? "Gzip" : "zip";
        C4475w.m17751c(str, objArr);
        try {
            aa a = C4480z.m17815a(i);
            if (a == null) {
                return null;
            }
            return a.mo6066b(bArr);
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m17798a(byte[] bArr, int i, int i2, String str) {
        byte[] bArr2 = null;
        if (bArr != null) {
            try {
                bArr2 = C4479y.m17799a(C4479y.m17797a(bArr, 2), 1, str);
            } catch (Throwable th) {
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return bArr2;
    }

    /* renamed from: b */
    public static byte[] m17810b(byte[] bArr, int i, int i2, String str) {
        try {
            return C4479y.m17809b(C4479y.m17811b(bArr, 1, str), 2);
        } catch (Throwable e) {
            if (!C4475w.m17748a(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    public static long m17800b() {
        try {
            return (((System.currentTimeMillis() + ((long) TimeZone.getDefault().getRawOffset())) / 86400000) * 86400000) - ((long) TimeZone.getDefault().getRawOffset());
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    /* renamed from: a */
    public static String m17783a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(toHexString);
        }
        return stringBuffer.toString().toUpperCase();
    }

    /* renamed from: b */
    public static String m17802b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return ActionConst.NULL;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(bArr);
            return C4479y.m17783a(instance.digest());
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m17791a(File file, File file2, int i) {
        Throwable th;
        FileInputStream fileInputStream;
        ZipOutputStream zipOutputStream;
        FileInputStream fileInputStream2 = null;
        C4475w.m17751c("rqdp{  ZF start}", new Object[0]);
        if (file == null || file2 == null || file.equals(file2)) {
            C4475w.m17752d("rqdp{  err ZF 1R!}", new Object[0]);
            return false;
        } else if (file.exists() && file.canRead()) {
            try {
                if (!(file2.getParentFile() == null || file2.getParentFile().exists())) {
                    file2.getParentFile().mkdirs();
                }
                if (!file2.exists()) {
                    file2.createNewFile();
                }
            } catch (Throwable th2) {
                if (!C4475w.m17748a(th2)) {
                    th2.printStackTrace();
                }
            }
            if (!file2.exists() || !file2.canRead()) {
                return false;
            }
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file2)));
                    try {
                        zipOutputStream.setMethod(8);
                        zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                        byte[] bArr = new byte[5000];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            zipOutputStream.write(bArr, 0, read);
                        }
                        zipOutputStream.flush();
                        zipOutputStream.closeEntry();
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            zipOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        C4475w.m17751c("rqdp{  ZF end}", new Object[0]);
                        return true;
                    } catch (Throwable th3) {
                        th2 = th3;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (zipOutputStream != null) {
                            zipOutputStream.close();
                        }
                        C4475w.m17751c("rqdp{  ZF end}", new Object[0]);
                        throw th2;
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    zipOutputStream = null;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    C4475w.m17751c("rqdp{  ZF end}", new Object[0]);
                    throw th2;
                }
            } catch (Throwable th5) {
                th2 = th5;
                zipOutputStream = null;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                C4475w.m17751c("rqdp{  ZF end}", new Object[0]);
                throw th2;
            }
        } else {
            C4475w.m17752d("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}", new Object[0]);
            return false;
        }
    }

    /* renamed from: a */
    public static ArrayList<String> m17785a(Context context, String[] strArr) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        ArrayList<String> arrayList = new ArrayList();
        if (C4417a.m17303a(context).m17313H()) {
            arrayList = new ArrayList();
            arrayList.add(new String("unknown(low memory)"));
            return arrayList;
        }
        BufferedReader bufferedReader3;
        try {
            Process exec = Runtime.getRuntime().exec(strArr);
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    arrayList.add(readLine);
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            bufferedReader3 = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            while (true) {
                try {
                    String readLine2 = bufferedReader3.readLine();
                    if (readLine2 != null) {
                        arrayList.add(readLine2);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            bufferedReader.close();
            try {
                bufferedReader3.close();
                return arrayList;
            } catch (IOException e2) {
                e2.printStackTrace();
                return arrayList;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static String m17780a(Context context, String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        if (f15797a == null) {
            f15797a = new HashMap();
            List<String> a = C4479y.m17785a(context, new String[]{"/system/bin/sh", "-c", "getprop"});
            if (a != null && a.size() > 0) {
                C4475w.m17746a(C4479y.class, "Successfully get 'getprop' list.", new Object[0]);
                Pattern compile = Pattern.compile("\\[(.+)\\]: \\[(.*)\\]");
                for (String matcher : a) {
                    Matcher matcher2 = compile.matcher(matcher);
                    if (matcher2.find()) {
                        f15797a.put(matcher2.group(1), matcher2.group(2));
                    }
                }
                C4475w.m17746a(C4479y.class, "System properties number: %dffffdsfsdfff.", Integer.valueOf(f15797a.size()));
            }
        }
        if (f15797a.containsKey(str)) {
            return (String) f15797a.get(str);
        }
        return "fail";
    }

    /* renamed from: b */
    public static void m17804b(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static boolean m17792a(String str) {
        if (str == null || str.trim().length() <= 0) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public static void m17806b(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.isFile() && file.exists() && file.canWrite()) {
                file.delete();
            }
        }
    }

    /* renamed from: c */
    public static byte[] m17814c(long j) {
        try {
            return (j).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static long m17812c(byte[] bArr) {
        long j = -1;
        if (bArr != null) {
            try {
                j = Long.parseLong(new String(bArr, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return j;
    }

    /* renamed from: a */
    public static Context m17772a(Context context) {
        if (context == null) {
            return context;
        }
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    /* renamed from: b */
    public static String m17801b(Throwable th) {
        if (th == null) {
            return "";
        }
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    /* renamed from: a */
    public static void m17789a(Class<?> cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(null, obj);
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public static Object m17775a(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        Object obj2 = null;
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            obj2 = declaredMethod.invoke(null, objArr);
        } catch (Exception e) {
        }
        return obj2;
    }

    /* renamed from: a */
    public static void m17788a(Parcel parcel, Map<String, PlugInBean> map) {
        int i = 0;
        if (map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Entry entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginKey" + i2, (String) arrayList.get(i2));
        }
        while (i < arrayList.size()) {
            bundle.putString("pluginVal" + i + "plugInId", ((PlugInBean) arrayList2.get(i)).f15257a);
            bundle.putString("pluginVal" + i + "plugInUUID", ((PlugInBean) arrayList2.get(i)).f15259c);
            bundle.putString("pluginVal" + i + "plugInVersion", ((PlugInBean) arrayList2.get(i)).f15258b);
            i++;
        }
        parcel.writeBundle(bundle);
    }

    /* renamed from: a */
    public static Map<String, PlugInBean> m17787a(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        if (readBundle == null) {
            return null;
        }
        int i;
        HashMap hashMap;
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        int intValue = ((Integer) readBundle.get("pluginNum")).intValue();
        for (i = 0; i < intValue; i++) {
            arrayList.add(readBundle.getString("pluginKey" + i));
        }
        for (i = 0; i < intValue; i++) {
            arrayList2.add(new PlugInBean(readBundle.getString("pluginVal" + i + "plugInId"), readBundle.getString("pluginVal" + i + "plugInVersion"), readBundle.getString("pluginVal" + i + "plugInUUID")));
        }
        if (arrayList.size() == arrayList2.size()) {
            HashMap hashMap2 = new HashMap(arrayList.size());
            for (i = 0; i < arrayList.size(); i++) {
                hashMap2.put(arrayList.get(i), PlugInBean.class.cast(arrayList2.get(i)));
            }
            hashMap = hashMap2;
        } else {
            C4475w.m17753e("map plugin parcel error!", new Object[0]);
            Map map = null;
        }
        return hashMap;
    }

    /* renamed from: b */
    public static void m17805b(Parcel parcel, Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Entry entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("keys", arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    /* renamed from: b */
    public static Map<String, String> m17803b(Parcel parcel) {
        int i = 0;
        Bundle readBundle = parcel.readBundle();
        if (readBundle == null) {
            return null;
        }
        HashMap hashMap;
        List stringArrayList = readBundle.getStringArrayList("keys");
        List stringArrayList2 = readBundle.getStringArrayList("values");
        if (stringArrayList == null || stringArrayList2 == null || stringArrayList.size() != stringArrayList2.size()) {
            C4475w.m17753e("map parcel error!", new Object[0]);
            Map map = null;
        } else {
            HashMap hashMap2 = new HashMap(stringArrayList.size());
            while (i < stringArrayList.size()) {
                hashMap2.put(stringArrayList.get(i), stringArrayList2.get(i));
                i++;
            }
            hashMap = hashMap2;
        }
        return hashMap;
    }

    /* renamed from: a */
    public static byte[] m17795a(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    /* renamed from: a */
    public static <T> T m17776a(byte[] bArr, Creator<T> creator) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        try {
            T createFromParcel = creator.createFromParcel(obtain);
            if (obtain == null) {
                return createFromParcel;
            }
            obtain.recycle();
            return createFromParcel;
        } catch (Throwable th) {
            if (obtain != null) {
                obtain.recycle();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static String m17779a(Context context, int i, String str) {
        String stringBuilder;
        Throwable th;
        if (AppInfo.m17296a(context, "android.permission.READ_LOGS")) {
            String[] strArr = str == null ? new String[]{"logcat", "-d", "-v", "threadtime"} : new String[]{"logcat", "-d", "-v", "threadtime", "-s", str};
            Process process = null;
            StringBuilder stringBuilder2 = new StringBuilder();
            try {
                Process exec = Runtime.getRuntime().exec(strArr);
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder2.append(readLine).append("\n");
                        if (i > 0 && stringBuilder2.length() > i) {
                            stringBuilder2.delete(0, stringBuilder2.length() - i);
                        }
                    }
                    stringBuilder = stringBuilder2.toString();
                    if (exec == null) {
                        return stringBuilder;
                    }
                    try {
                        exec.getOutputStream().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        exec.getInputStream().close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        exec.getErrorStream().close();
                        return stringBuilder;
                    } catch (IOException e22) {
                        e22.printStackTrace();
                        return stringBuilder;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    process = exec;
                    if (process != null) {
                        process.getOutputStream().close();
                        process.getInputStream().close();
                        process.getErrorStream().close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
                stringBuilder = stringBuilder2.append("\n[error:" + th.toString() + "]").toString();
                if (process != null) {
                    return stringBuilder;
                }
                process.getOutputStream().close();
                process.getInputStream().close();
                process.getErrorStream().close();
                return stringBuilder;
            }
        }
        C4475w.m17752d("no read_log permission!", new Object[0]);
        return null;
    }

    /* renamed from: a */
    public static Map<String, String> m17786a(int i, boolean z) {
        Map<String, String> hashMap = new HashMap(12);
        Map allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Thread.currentThread().getId();
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : allStackTraces.entrySet()) {
            stringBuilder.setLength(0);
            if (!(entry.getValue() == null || ((StackTraceElement[]) entry.getValue()).length == 0)) {
                for (StackTraceElement stackTraceElement : (StackTraceElement[]) entry.getValue()) {
                    if (i > 0 && stringBuilder.length() >= i) {
                        stringBuilder.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                        break;
                    }
                    stringBuilder.append(stackTraceElement.toString()).append("\n");
                }
                hashMap.put(((Thread) entry.getKey()).getName() + "(" + ((Thread) entry.getKey()).getId() + ")", stringBuilder.toString());
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public static synchronized byte[] m17793a(int i) {
        byte[] bArr;
        DataInputStream dataInputStream;
        Throwable e;
        Exception e2;
        KeyGenerator instance;
        synchronized (C4479y.class) {
            try {
                bArr = new byte[16];
                dataInputStream = new DataInputStream(new FileInputStream(new File("/dev/urandom")));
                try {
                    dataInputStream.readFully(bArr);
                    try {
                        dataInputStream.close();
                    } catch (Throwable e3) {
                        if (!C4475w.m17750b(e3)) {
                            e3.printStackTrace();
                        }
                        bArr = null;
                    }
                } catch (Exception e4) {
                    e2 = e4;
                    try {
                        C4475w.m17753e("Failed to read from /dev/urandom : %s", e2);
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        instance = KeyGenerator.getInstance("AES");
                        instance.init(128, new SecureRandom());
                        bArr = instance.generateKey().getEncoded();
                        return bArr;
                    } catch (Throwable th) {
                        e3 = th;
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        throw e3;
                    }
                }
            } catch (Exception e5) {
                e2 = e5;
                dataInputStream = null;
                C4475w.m17753e("Failed to read from /dev/urandom : %s", e2);
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                instance = KeyGenerator.getInstance("AES");
                instance.init(128, new SecureRandom());
                bArr = instance.generateKey().getEncoded();
                return bArr;
            } catch (Throwable th2) {
                e3 = th2;
                dataInputStream = null;
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                throw e3;
            }
        }
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m17794a(int i, byte[] bArr, byte[] bArr2) {
        try {
            Key secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            instance.init(i, secretKeySpec, new IvParameterSpec(bArr2));
            return instance.doFinal(bArr);
        } catch (Throwable e) {
            if (!C4475w.m17750b(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m17808b(int i, byte[] bArr, byte[] bArr2) {
        try {
            Key generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr2));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, generatePublic);
            return instance.doFinal(bArr);
        } catch (Throwable e) {
            if (!C4475w.m17750b(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m17790a(Context context, String str, long j) {
        C4475w.m17751c("[Util] try to lock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < j) {
                    return false;
                }
                C4475w.m17751c("[Util] lock file(%s) is expired, unlock it", str);
                C4479y.m17807b(context, str);
            }
            if (file.createNewFile()) {
                C4475w.m17751c("[Util] successfully locked file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                return true;
            }
            C4475w.m17751c("[Util] Failed to locked file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return false;
        } catch (Throwable th) {
            C4475w.m17748a(th);
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m17807b(Context context, String str) {
        C4475w.m17751c("[Util] try to unlock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            C4475w.m17751c("[Util] successfully unlocked file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            C4475w.m17748a(th);
            return false;
        }
    }

    /* renamed from: a */
    private static BufferedReader m17773a(File file) {
        if (file == null || !file.exists() || !file.canRead()) {
            return null;
        }
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
        } catch (Throwable th) {
            C4475w.m17748a(th);
            return null;
        }
    }

    /* renamed from: a */
    public static BufferedReader m17774a(String str, String str2) {
        BufferedReader bufferedReader = null;
        if (str != null) {
            try {
                File file = new File(str, str2);
                if (file.exists() && file.canRead()) {
                    bufferedReader = C4479y.m17773a(file);
                }
            } catch (Throwable e) {
                C4475w.m17748a(e);
            }
        }
        return bufferedReader;
    }

    /* renamed from: a */
    public static Thread m17784a(Runnable runnable, String str) {
        try {
            Thread thread = new Thread(runnable);
            thread.setName(str);
            thread.start();
            return thread;
        } catch (Throwable th) {
            C4475w.m17753e("[Util] Failed to start a thread to execute task with message: %s", th.getMessage());
            return null;
        }
    }

    /* renamed from: c */
    public static boolean m17813c(String str) {
        boolean z;
        if (str == null || str.trim().length() <= 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return false;
        }
        if (str.length() > 255) {
            C4475w.m17747a("URL's length is larger than 255.", new Object[0]);
            return false;
        } else if (!str.toLowerCase().startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            C4475w.m17747a("URL is not start with \"http\".", new Object[0]);
            return false;
        } else if (str.toLowerCase().contains("qq.com")) {
            return true;
        } else {
            C4475w.m17747a("URL does not contain \"qq.com\".", new Object[0]);
            return false;
        }
    }
}
