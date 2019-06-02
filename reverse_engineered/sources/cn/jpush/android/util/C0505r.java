package cn.jpush.android.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* renamed from: cn.jpush.android.util.r */
public final class C0505r {
    /* renamed from: z */
    private static final String[] f1047z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 6;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "j*m!'\u001f";
        r0 = -1;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
    L_0x0011:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0016:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0068;
            case 1: goto L_0x006b;
            case 2: goto L_0x006e;
            case 3: goto L_0x0071;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 22;
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
        goto L_0x0016;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0011;
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
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0015Pj+I\u001c";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "c=\u0013^6N\u00071fsU\u001c";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "L\u000b+{yCR<`sL\u001c:Zb@\u0004\u0019{zHHr2pD\u0004:BwY\u0000e";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "x<\u0019?.";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0001H<}xY\r1f,";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        f1047z = r4;
        return;
    L_0x0068:
        r9 = 45;
        goto L_0x001f;
    L_0x006b:
        r9 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        goto L_0x001f;
    L_0x006e:
        r9 = 95;
        goto L_0x001f;
    L_0x0071:
        r9 = 18;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.r.<clinit>():void");
    }

    /* renamed from: a */
    public static ArrayList<String> m1788a(InputStream inputStream) {
        ArrayList<String> arrayList = new ArrayList();
        try {
            Reader inputStreamReader = new InputStreamReader(inputStream, f1047z[4]);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 2048);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                readLine = readLine.trim();
                if (!"".equals(readLine)) {
                    arrayList.add(readLine);
                }
            }
            inputStreamReader.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.getMessage();
            ac.m1588e();
        }
        return arrayList;
    }

    /* renamed from: a */
    private static void m1789a(File file, ZipOutputStream zipOutputStream, String str) {
        String str2 = new String((str + (str.trim().length() == 0 ? "" : File.separator) + file.getName()).getBytes(f1047z[1]), f1047z[0]);
        if (file.isDirectory()) {
            for (File a : file.listFiles()) {
                C0505r.m1789a(a, zipOutputStream, str2);
            }
            return;
        }
        byte[] bArr = new byte[1048576];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1048576);
        zipOutputStream.putNextEntry(new ZipEntry(str2));
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                zipOutputStream.write(bArr, 0, read);
            } else {
                bufferedInputStream.close();
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                return;
            }
        }
    }

    /* renamed from: a */
    public static void m1790a(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    C0505r.m1790a(file2.getAbsolutePath());
                    file2.delete();
                }
            }
            file.delete();
        }
    }

    /* renamed from: a */
    public static void m1791a(Collection<File> collection, File file) {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        for (File a : collection) {
            C0505r.m1789a(a, zipOutputStream, "");
        }
        zipOutputStream.close();
    }

    /* renamed from: a */
    public static boolean m1792a(String str, String str2, Context context) {
        FileOutputStream fileOutputStream;
        Throwable th;
        if (context == null) {
            throw new IllegalArgumentException(f1047z[2]);
        }
        new StringBuilder(f1047z[3]).append(str).append(f1047z[5]).append(str2);
        ac.m1576a();
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.createNewFile();
                }
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(str2.getBytes(f1047z[4]));
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        return true;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e) {
                ac.m1591g();
            }
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m1793a(String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        Throwable th;
        if (TextUtils.isEmpty(str) || bArr.length <= 0) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    /* renamed from: b */
    public static String m1794b(String str) {
        String str2 = ".";
        if (!an.m1657a(str)) {
            int lastIndexOf = str.lastIndexOf(str2);
            int length = str.length();
            if (lastIndexOf > 0 && lastIndexOf + 1 != length) {
                return str.substring(lastIndexOf, str.length());
            }
        }
        return "";
    }

    /* renamed from: c */
    public static String m1795c(String str) {
        return an.m1657a(str) ? "" : str.substring(str.lastIndexOf("/") + 1, str.length());
    }
}
