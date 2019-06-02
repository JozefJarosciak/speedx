package cn.jpush.android.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class aj {
    /* renamed from: a */
    private static final String f984a = aj.class.getSimpleName();
    /* renamed from: z */
    private static final String[] f985z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 8;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0012\u000fS\u000bj";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
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
            case 0: goto L_0x0081;
            case 1: goto L_0x0084;
            case 2: goto L_0x0087;
            case 3: goto L_0x008a;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 82;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "M'\u0018Z_M'\u001f+.N";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "70ry<&6p";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "&+ey<&6p";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "4>gP;$>JJ;4/";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "+2cC\r4>vI<#(";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "$7tU!\u00185tK7";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "&8aO$./l";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        f985z = r4;
        r0 = cn.jpush.android.util.aj.class;
        r0 = r0.getSimpleName();
        f984a = r0;
        return;
    L_0x0081:
        r9 = 71;
        goto L_0x0020;
    L_0x0084:
        r9 = 91;
        goto L_0x0020;
    L_0x0087:
        r9 = 21;
        goto L_0x0020;
    L_0x008a:
        r9 = 38;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.aj.<clinit>():void");
    }

    /* renamed from: a */
    private static String m1636a(String str, int i) {
        if (str == null) {
            return str;
        }
        String replaceAll = Pattern.compile(f985z[1]).matcher(str).replaceAll("");
        try {
            byte[] bytes = replaceAll.getBytes();
            return bytes.length > 30 ? replaceAll.substring(0, new String(bytes, 0, 30, f985z[0]).length()) : replaceAll;
        } catch (UnsupportedEncodingException e) {
            e.getMessage();
            ac.m1588e();
            return replaceAll;
        }
    }

    /* renamed from: a */
    private static Set<String> m1637a(ActivityManager activityManager) {
        Set<String> hashSet = new HashSet();
        for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            String[] strArr = runningAppProcessInfo.pkgList;
            for (Object add : strArr) {
                hashSet.add(add);
            }
        }
        return hashSet;
    }

    /* renamed from: a */
    private static JSONArray m1638a(ActivityManager activityManager, PackageManager packageManager) {
        JSONArray jSONArray = new JSONArray();
        Set a = m1637a(activityManager);
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(8192);
        List<RunningServiceInfo> runningServices = activityManager.getRunningServices(200);
        Collections.sort(installedApplications, new DisplayNameComparator(packageManager));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        for (ApplicationInfo applicationInfo : installedApplications) {
            String a2 = m1636a(applicationInfo.loadLabel(packageManager).toString(), 30);
            if (a.contains(applicationInfo.packageName)) {
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                for (RunningServiceInfo runningServiceInfo : runningServices) {
                    if (runningServiceInfo.service.getPackageName().equals(applicationInfo.packageName)) {
                        JSONObject jSONObject2 = new JSONObject();
                        long round = (long) Math.round((float) ((elapsedRealtime - runningServiceInfo.activeSince) / 1000));
                        try {
                            jSONObject2.put(f985z[6], runningServiceInfo.service.getShortClassName());
                            jSONObject2.put(f985z[5], round);
                            jSONArray2.put(jSONObject2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    jSONObject.put(f985z[3], a2);
                    jSONObject.put(f985z[2], applicationInfo.packageName);
                    jSONObject.put(f985z[4], jSONArray2);
                    jSONArray.put(jSONObject);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return jSONArray;
    }

    /* renamed from: a */
    public static void m1639a(Context context) {
        ac.m1581b();
        JSONArray a = m1638a((ActivityManager) context.getSystemService(f985z[7]), context.getPackageManager());
        if (a != null && a.length() != 0) {
            ah.m1620a(context, a);
        }
    }
}
