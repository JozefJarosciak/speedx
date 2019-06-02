package com.mob.commons;

import android.content.Context;
import cn.sharesdk.framework.ShareSDK;
import com.mob.tools.MobLog;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.ReflectHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.TimeZone;
import org.apache.commons.cli.HelpFormatter;

/* compiled from: CommonNetworkHelper */
/* renamed from: com.mob.commons.b */
public class C4237b extends NetworkHelper {
    /* renamed from: a */
    private static final String[] f14900a = new String[]{ShareSDK.SDK_TAG, "SMSSDK", "SHAREREC", "MOBAPI"};
    /* renamed from: b */
    private static C4237b f14901b;
    /* renamed from: c */
    private Context f14902c;
    /* renamed from: d */
    private HashMap<String, MobProduct> f14903d = new HashMap();

    private C4237b(Context context) {
        this.f14902c = context.getApplicationContext();
    }

    /* renamed from: a */
    public static C4237b m16835a(Context context) {
        if (f14901b == null) {
            f14901b = new C4237b(context);
        }
        return f14901b;
    }

    /* renamed from: a */
    public ArrayList<MobProduct> m16837a() {
        try {
            ReflectHelper.importClass("com.mob.commons.*");
            for (String newInstance : f14900a) {
                try {
                    MobProduct mobProduct = (MobProduct) ReflectHelper.newInstance(newInstance, new Object[0]);
                    if (mobProduct != null) {
                        this.f14903d.put(mobProduct.getProductTag(), mobProduct);
                    }
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
            MobLog.getInstance().m16946w(th2);
        }
        ArrayList<MobProduct> arrayList = new ArrayList();
        for (Entry value : this.f14903d.entrySet()) {
            arrayList.add(value.getValue());
        }
        return arrayList;
    }

    /* renamed from: a */
    public String m16836a(ArrayList<MobProduct> arrayList) {
        try {
            Object invokeStaticMethod = ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14902c);
            String str = ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getPackageName", new Object[0]) + "/" + ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getAppVersionName", new Object[0]);
            String str2 = "";
            int size = arrayList.size();
            String str3 = str2;
            for (int i = 0; i < size; i++) {
                try {
                    if (str3.length() > 0) {
                        str2 = str3 + " ";
                    } else {
                        str2 = str3;
                    }
                    try {
                        MobProduct mobProduct = (MobProduct) arrayList.get(i);
                        str3 = str2 + mobProduct.getProductTag() + "/" + mobProduct.getSdkver();
                    } catch (Throwable th) {
                        str3 = str2;
                    }
                } catch (Throwable th2) {
                }
            }
            str2 = "Android/" + ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getOSVersionInt", new Object[0]);
            return str + " " + str3 + (str3.length() > 0 ? " " : "") + str2 + " " + TimeZone.getDefault().getID() + " " + ("Lang/" + Locale.getDefault().toString().replace("-r", HelpFormatter.DEFAULT_OPT_PREFIX));
        } catch (Throwable th3) {
            MobLog.getInstance().m16946w(th3);
            return "";
        }
    }

    /* renamed from: a */
    public void m16838a(MobProduct mobProduct) {
        if (mobProduct != null && !this.f14903d.containsKey(mobProduct.getProductTag())) {
            this.f14903d.put(mobProduct.getProductTag(), mobProduct);
        }
    }
}
