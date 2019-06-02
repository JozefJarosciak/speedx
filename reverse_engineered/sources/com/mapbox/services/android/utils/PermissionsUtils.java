package com.mapbox.services.android.utils;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class PermissionsUtils {
    private static final int LOCATION_PERMISSIONS_REQUEST = 0;
    private static final String MESSAGE_FALLBACK = "Access to your GPS location has been disabled.";
    private static final String MESSAGE_RATIONALE = "This app needs access to your GPS location to locate you on the map.";
    private static final String permission = "android.permission.ACCESS_FINE_LOCATION";

    public static boolean isLocationGranted(Activity activity) {
        return ContextCompat.checkSelfPermission(activity, permission) == 0;
    }

    public static void startPermissionFlow(Activity activity) {
        startPermissionFlow(activity, MESSAGE_RATIONALE);
    }

    public static void startPermissionFlow(Activity activity, String str) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            Toast.makeText(activity, str, 1).show();
            requestPermissions(activity);
            return;
        }
        requestPermissions(activity);
    }

    private static void requestPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, 0);
    }

    public static boolean isRequestSuccessful(int i, String[] strArr, int[] iArr) {
        if (i == 0 && iArr.length > 0 && iArr[0] == 0) {
            return true;
        }
        return false;
    }

    public static void explainFallback(Activity activity) {
        explainFallback(activity, MESSAGE_FALLBACK);
    }

    public static void explainFallback(Activity activity, String str) {
        Toast.makeText(activity, str, 1).show();
    }
}
