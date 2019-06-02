package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;

/* renamed from: cn.jpush.android.util.p */
public final class C0503p {
    /* renamed from: a */
    public static final String f1045a;
    /* renamed from: z */
    private static final String[] f1046z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 7;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u001bV";
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
            case 0: goto L_0x009f;
            case 1: goto L_0x00a3;
            case 2: goto L_0x00a7;
            case 3: goto L_0x00ab;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
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
            case 5: goto L_0x006b;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "j<egWi\u001cf7J";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u001bU";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u001b&;.\u001c[\u000e1e\nU\u00134e";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "P\u0006!+NP\u000e'pN";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "`\u000f0j\u001aU\u00152/\u001a\u0014\u0003<8T\u0014";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        r2 = 6;
        r1 = "P\u000e'";
        r0 = 5;
        r3 = r4;
        goto L_0x0008;
    L_0x006b:
        r3[r2] = r1;
        f1046z = r4;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = java.io.File.separator;
        r6 = r0.append(r1);
        r0 = "F\u000e6\"";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x00bd;
    L_0x0085:
        r3 = r0;
        r4 = r2;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x008a:
        r7 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x00af;
            case 1: goto L_0x00b2;
            case 2: goto L_0x00b5;
            case 3: goto L_0x00b8;
            default: goto L_0x0091;
        };
    L_0x0091:
        r5 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
    L_0x0093:
        r5 = r5 ^ r7;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x00bb;
    L_0x009b:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x008a;
    L_0x009f:
        r9 = 52;
        goto L_0x001f;
    L_0x00a3:
        r9 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        goto L_0x001f;
    L_0x00a7:
        r9 = 85;
        goto L_0x001f;
    L_0x00ab:
        r9 = 74;
        goto L_0x001f;
    L_0x00af:
        r5 = 52;
        goto L_0x0093;
    L_0x00b2:
        r5 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        goto L_0x0093;
    L_0x00b5:
        r5 = 85;
        goto L_0x0093;
    L_0x00b8:
        r5 = 74;
        goto L_0x0093;
    L_0x00bb:
        r1 = r0;
        r0 = r3;
    L_0x00bd:
        if (r1 > r2) goto L_0x0085;
    L_0x00bf:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        r0 = r6.append(r0);
        r1 = java.io.File.separator;
        r0 = r0.append(r1);
        r0 = r0.toString();
        f1045a = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.p.<clinit>():void");
    }

    /* renamed from: a */
    public static String m1778a(Context context) {
        if (!C0490b.m1681a()) {
            return "";
        }
        String str = C0503p.m1780a(Environment.getExternalStorageDirectory().getAbsolutePath()) + C0503p.m1787e(context) + f1046z[0];
        if (!new File(str).isDirectory()) {
            C0503p.m1786d(context);
        }
        return str + File.separator;
    }

    /* renamed from: a */
    public static String m1779a(Context context, String str) {
        String str2 = context.getFilesDir() + "/" + str;
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2 + "/";
    }

    /* renamed from: a */
    private static String m1780a(String str) {
        return TextUtils.isEmpty(str) ? "" : str.lastIndexOf(File.separator) != 0 ? str + File.separator : str;
    }

    /* renamed from: a */
    private static boolean m1781a(File file) {
        boolean z = false;
        try {
            if (!file.exists()) {
                return z;
            }
            if (file.isFile()) {
                return file.delete();
            }
            String[] list = file.list();
            int length = list.length;
            for (int i = z; i < length; i++) {
                File file2 = new File(file, list[i]);
                if (file2.isDirectory()) {
                    C0503p.m1781a(file2);
                } else {
                    file2.delete();
                }
            }
            return file.delete();
        } catch (Exception e) {
            ac.m1588e();
            return z;
        }
    }

    /* renamed from: b */
    public static String m1782b(Context context) {
        if (!C0490b.m1681a()) {
            return "";
        }
        String str = C0503p.m1780a(Environment.getExternalStorageDirectory().getAbsolutePath()) + C0503p.m1787e(context) + f1046z[2];
        if (!new File(str).isDirectory()) {
            C0503p.m1786d(context);
        }
        return str + "/";
    }

    /* renamed from: b */
    public static String m1783b(Context context, String str) {
        try {
            File file;
            if (C0490b.m1681a()) {
                String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + f1046z[3] + context.getPackageName() + File.separator + str + File.separator;
                file = new File(str2);
                if (file.exists()) {
                    return str2;
                }
                file.mkdirs();
                return str2;
            }
            file = new File(context.getFilesDir() + f1045a);
            if (file.exists() && file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles.length > 10) {
                    Arrays.sort(listFiles, new C0504q());
                    C0503p.m1781a(listFiles[listFiles.length - 1]);
                }
            }
            return C0503p.m1784c(context, str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: c */
    private static String m1784c(Context context, String str) {
        String str2 = context.getFilesDir() + f1045a + str;
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2 + "/";
    }

    /* renamed from: c */
    public static void m1785c(Context context) {
        try {
            for (File file : context.getFilesDir().listFiles()) {
                CharSequence name = file.getName();
                if (TextUtils.isEmpty(name) ? false : Pattern.compile(f1046z[1]).matcher(name).matches()) {
                    C0505r.m1790a(file.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: d */
    private static void m1786d(Context context) {
        try {
            if (C0490b.m1681a()) {
                String e = C0503p.m1787e(context);
                String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(absolutePath + e);
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
                file = new File(absolutePath + e + f1046z[0]);
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
                file = new File(absolutePath + e + f1046z[2]);
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
                file = new File(absolutePath + e + File.separator + context.getPackageName());
                if (!file.isDirectory()) {
                    file.mkdirs();
                    return;
                }
                return;
            }
            ac.m1588e();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: e */
    private static String m1787e(Context context) {
        Exception e;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Object string = defaultSharedPreferences.getString(f1046z[6], "");
        if (TextUtils.isEmpty(string)) {
            String str;
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String str2 = f1046z[3];
            File file = new File(absolutePath + str2);
            try {
                if (file.exists()) {
                    ArrayList arrayList = new ArrayList();
                    for (File file2 : file.listFiles()) {
                        if (file2.isDirectory()) {
                            arrayList.add(file2.getName());
                            new StringBuilder(f1046z[4]).append(file2.getName());
                            ac.m1576a();
                        }
                    }
                    int size = arrayList.size();
                    if (size > 0) {
                        try {
                            str = str2 + ((String) arrayList.get(size / 2));
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                            new StringBuilder(f1046z[5]).append(str);
                            ac.m1584c();
                            defaultSharedPreferences.edit().putString(f1046z[6], str).commit();
                            return string;
                        }
                    }
                    str = str2 + UUID.randomUUID().toString().substring(0, 5);
                } else {
                    file.mkdirs();
                    str = str2 + UUID.randomUUID().toString().substring(0, 5);
                }
            } catch (Exception e3) {
                Exception exception = e3;
                str = null;
                e = exception;
                e.printStackTrace();
                new StringBuilder(f1046z[5]).append(str);
                ac.m1584c();
                defaultSharedPreferences.edit().putString(f1046z[6], str).commit();
                return string;
            }
            new StringBuilder(f1046z[5]).append(str);
            ac.m1584c();
            defaultSharedPreferences.edit().putString(f1046z[6], str).commit();
        }
        return string;
    }
}
