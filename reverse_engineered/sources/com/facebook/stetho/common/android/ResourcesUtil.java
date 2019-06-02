package com.facebook.stetho.common.android;

import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import com.facebook.stetho.common.LogUtil;

public class ResourcesUtil {
    private ResourcesUtil() {
    }

    public static String getIdStringQuietly(Object obj, Resources resources, int i) {
        try {
            return getIdString(resources, i);
        } catch (NotFoundException e) {
            String fallbackIdString = getFallbackIdString(i);
            LogUtil.m15288w("Unknown identifier encountered on " + obj + ": " + fallbackIdString);
            return fallbackIdString;
        }
    }

    public static String getIdString(Resources resources, int i) throws NotFoundException {
        if (resources == null) {
            return getFallbackIdString(i);
        }
        String str;
        String str2;
        switch (getResourcePackageId(i)) {
            case 127:
                str = "";
                str2 = "";
                break;
            default:
                str = resources.getResourcePackageName(i);
                str2 = ":";
                break;
        }
        String resourceTypeName = resources.getResourceTypeName(i);
        String resourceEntryName = resources.getResourceEntryName(i);
        StringBuilder stringBuilder = new StringBuilder(((((str.length() + 1) + str2.length()) + resourceTypeName.length()) + 1) + resourceEntryName.length());
        stringBuilder.append("@");
        stringBuilder.append(str);
        stringBuilder.append(str2);
        stringBuilder.append(resourceTypeName);
        stringBuilder.append("/");
        stringBuilder.append(resourceEntryName);
        return stringBuilder.toString();
    }

    private static String getFallbackIdString(int i) {
        return "#" + Integer.toHexString(i);
    }

    private static int getResourcePackageId(int i) {
        return (i >>> 24) & 255;
    }
}
