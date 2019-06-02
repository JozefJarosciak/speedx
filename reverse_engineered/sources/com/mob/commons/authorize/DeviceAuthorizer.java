package com.mob.commons.authorize;

import android.content.Context;
import com.mob.commons.C4226a;
import com.mob.commons.C4237b;
import com.mob.commons.MobProduct;

public final class DeviceAuthorizer {
    public static String authorize(Context context, final MobProduct mobProduct) {
        return authorize(context, new MobProduct() {
            public int getSdkver() {
                return mobProduct.getSdkver();
            }

            public String getProductTag() {
                return mobProduct.getProductTag();
            }

            public String getProductAppkey() {
                return mobProduct.getProductAppkey();
            }
        });
    }

    public static synchronized String authorize(Context context, MobProduct mobProduct) {
        String a;
        synchronized (DeviceAuthorizer.class) {
            C4237b.m16835a(context).m16838a(mobProduct);
            C4236a c4236a = new C4236a();
            if (mobProduct == null || !C4226a.m16774f(context)) {
                a = c4236a.m16833a(context);
            } else {
                a = c4236a.m16834a(context, mobProduct);
            }
        }
        return a;
    }
}
