package cn.jpush.android.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: cn.jpush.android.util.v */
public final class C0508v {
    /* renamed from: a */
    public static ArrayList<ad> m1809a(Context context, boolean z) {
        ArrayList<ad> arrayList = new ArrayList();
        try {
            List installedPackages = context.getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < installedPackages.size(); i++) {
                PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
                ad adVar = new ad();
                adVar.f948a = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
                adVar.f949b = packageInfo.packageName;
                adVar.f950c = packageInfo.versionName;
                adVar.f951d = packageInfo.versionCode;
                arrayList.add(adVar);
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            ac.m1581b();
        } catch (Exception e2) {
            e2.printStackTrace();
            ac.m1581b();
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String[] m1810a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
        } catch (NameNotFoundException e) {
            ac.m1593i();
            return null;
        }
    }
}
