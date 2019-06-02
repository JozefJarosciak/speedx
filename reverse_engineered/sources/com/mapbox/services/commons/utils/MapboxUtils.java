package com.mapbox.services.commons.utils;

public class MapboxUtils {
    public static boolean isAccessTokenValid(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith("pk.") || str.startsWith("sk.")) {
            return true;
        }
        return false;
    }
}
