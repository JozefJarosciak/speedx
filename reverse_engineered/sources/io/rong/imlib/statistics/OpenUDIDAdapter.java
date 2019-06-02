package io.rong.imlib.statistics;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;

public class OpenUDIDAdapter {
    private static final String OPEN_UDID_MANAGER_CLASS_NAME = "org.openudid.OpenUDID_manager";

    public static boolean isOpenUDIDAvailable() {
        try {
            Class.forName(OPEN_UDID_MANAGER_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isInitialized() {
        try {
            boolean booleanValue;
            Object invoke = Class.forName(OPEN_UDID_MANAGER_CLASS_NAME).getMethod("isInitialized", (Class[]) null).invoke(null, (Object[]) null);
            if (invoke instanceof Boolean) {
                booleanValue = ((Boolean) invoke).booleanValue();
            } else {
                booleanValue = false;
            }
            return booleanValue;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (NoSuchMethodException e2) {
            return false;
        } catch (InvocationTargetException e3) {
            return false;
        } catch (IllegalAccessException e4) {
            return false;
        }
    }

    public static void sync(Context context) {
        try {
            Class.forName(OPEN_UDID_MANAGER_CLASS_NAME).getMethod("sync", new Class[]{Context.class}).invoke(null, new Object[]{context});
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e2) {
        } catch (InvocationTargetException e3) {
        } catch (IllegalAccessException e4) {
        }
    }

    public static String getOpenUDID() {
        try {
            String str;
            Object invoke = Class.forName(OPEN_UDID_MANAGER_CLASS_NAME).getMethod("getOpenUDID", (Class[]) null).invoke(null, (Object[]) null);
            if (invoke instanceof String) {
                str = (String) invoke;
            } else {
                str = null;
            }
            return str;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (NoSuchMethodException e2) {
            return null;
        } catch (InvocationTargetException e3) {
            return null;
        } catch (IllegalAccessException e4) {
            return null;
        }
    }
}
