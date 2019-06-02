package com.google.zxing.client.android.camera;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.hardware.Camera.Area;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.util.C0880h;
import com.journeyapps.barcodescanner.camera.CameraSettings.FocusMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class CameraConfigurationUtils {
    private static final int AREA_PER_1000 = 400;
    private static final float MAX_EXPOSURE_COMPENSATION = 1.5f;
    private static final int MAX_FPS = 20;
    private static final float MIN_EXPOSURE_COMPENSATION = 0.0f;
    private static final int MIN_FPS = 10;
    private static final Pattern SEMICOLON = Pattern.compile(C0880h.f2220b);
    private static final String TAG = "CameraConfiguration";

    private CameraConfigurationUtils() {
    }

    public static void setFocus(Parameters parameters, FocusMode focusMode, boolean z) {
        Collection supportedFocusModes = parameters.getSupportedFocusModes();
        String str = null;
        if (z || focusMode == FocusMode.AUTO) {
            str = findSettableValue("focus mode", supportedFocusModes, "auto");
        } else if (focusMode == FocusMode.CONTINUOUS) {
            str = findSettableValue("focus mode", supportedFocusModes, "continuous-picture", "continuous-video", "auto");
        } else if (focusMode == FocusMode.INFINITY) {
            str = findSettableValue("focus mode", supportedFocusModes, "infinity");
        } else if (focusMode == FocusMode.MACRO) {
            str = findSettableValue("focus mode", supportedFocusModes, "macro");
        }
        if (!z && r0 == null) {
            str = findSettableValue("focus mode", supportedFocusModes, "macro", "edof");
        }
        if (str == null) {
            return;
        }
        if (str.equals(parameters.getFocusMode())) {
            Log.i(TAG, "Focus mode already set to " + str);
        } else {
            parameters.setFocusMode(str);
        }
    }

    public static void setTorch(Parameters parameters, boolean z) {
        String findSettableValue;
        Collection supportedFlashModes = parameters.getSupportedFlashModes();
        if (z) {
            findSettableValue = findSettableValue("flash mode", supportedFlashModes, "torch", "on");
        } else {
            findSettableValue = findSettableValue("flash mode", supportedFlashModes, "off");
        }
        if (findSettableValue == null) {
            return;
        }
        if (findSettableValue.equals(parameters.getFlashMode())) {
            Log.i(TAG, "Flash mode already set to " + findSettableValue);
            return;
        }
        Log.i(TAG, "Setting flash mode to " + findSettableValue);
        parameters.setFlashMode(findSettableValue);
    }

    public static void setBestExposure(Parameters parameters, boolean z) {
        float f = 0.0f;
        int minExposureCompensation = parameters.getMinExposureCompensation();
        int maxExposureCompensation = parameters.getMaxExposureCompensation();
        float exposureCompensationStep = parameters.getExposureCompensationStep();
        if (!(minExposureCompensation == 0 && maxExposureCompensation == 0) && exposureCompensationStep > 0.0f) {
            if (!z) {
                f = MAX_EXPOSURE_COMPENSATION;
            }
            int round = Math.round(f / exposureCompensationStep);
            exposureCompensationStep *= (float) round;
            round = Math.max(Math.min(round, maxExposureCompensation), minExposureCompensation);
            if (parameters.getExposureCompensation() == round) {
                Log.i(TAG, "Exposure compensation already set to " + round + " / " + exposureCompensationStep);
                return;
            }
            Log.i(TAG, "Setting exposure compensation to " + round + " / " + exposureCompensationStep);
            parameters.setExposureCompensation(round);
            return;
        }
        Log.i(TAG, "Camera does not support exposure compensation");
    }

    public static void setBestPreviewFPS(Parameters parameters) {
        setBestPreviewFPS(parameters, 10, 20);
    }

    public static void setBestPreviewFPS(Parameters parameters, int i, int i2) {
        Collection<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        Log.i(TAG, "Supported FPS ranges: " + toString((Collection) supportedPreviewFpsRange));
        if (supportedPreviewFpsRange != null && !supportedPreviewFpsRange.isEmpty()) {
            for (int[] iArr : supportedPreviewFpsRange) {
                int i3 = iArr[0];
                int i4 = iArr[1];
                if (i3 >= i * 1000 && i4 <= i2 * 1000) {
                    break;
                }
            }
            int[] iArr2 = null;
            if (iArr2 == null) {
                Log.i(TAG, "No suitable FPS range?");
                return;
            }
            int[] iArr3 = new int[2];
            parameters.getPreviewFpsRange(iArr3);
            if (Arrays.equals(iArr3, iArr2)) {
                Log.i(TAG, "FPS range already set to " + Arrays.toString(iArr2));
                return;
            }
            Log.i(TAG, "Setting FPS range to " + Arrays.toString(iArr2));
            parameters.setPreviewFpsRange(iArr2[0], iArr2[1]);
        }
    }

    @TargetApi(15)
    public static void setFocusArea(Parameters parameters) {
        if (parameters.getMaxNumFocusAreas() > 0) {
            Log.i(TAG, "Old focus areas: " + toString(parameters.getFocusAreas()));
            Iterable buildMiddleArea = buildMiddleArea(400);
            Log.i(TAG, "Setting focus area to : " + toString(buildMiddleArea));
            parameters.setFocusAreas(buildMiddleArea);
            return;
        }
        Log.i(TAG, "Device does not support focus areas");
    }

    @TargetApi(15)
    public static void setMetering(Parameters parameters) {
        if (parameters.getMaxNumMeteringAreas() > 0) {
            Log.i(TAG, "Old metering areas: " + parameters.getMeteringAreas());
            Iterable buildMiddleArea = buildMiddleArea(400);
            Log.i(TAG, "Setting metering area to : " + toString(buildMiddleArea));
            parameters.setMeteringAreas(buildMiddleArea);
            return;
        }
        Log.i(TAG, "Device does not support metering areas");
    }

    @TargetApi(15)
    private static List<Area> buildMiddleArea(int i) {
        return Collections.singletonList(new Area(new Rect(-i, -i, i, i), 1));
    }

    @TargetApi(15)
    public static void setVideoStabilization(Parameters parameters) {
        if (!parameters.isVideoStabilizationSupported()) {
            Log.i(TAG, "This device does not support video stabilization");
        } else if (parameters.getVideoStabilization()) {
            Log.i(TAG, "Video stabilization already enabled");
        } else {
            Log.i(TAG, "Enabling video stabilization...");
            parameters.setVideoStabilization(true);
        }
    }

    public static void setBarcodeSceneMode(Parameters parameters) {
        if ("barcode".equals(parameters.getSceneMode())) {
            Log.i(TAG, "Barcode scene mode already set");
            return;
        }
        String findSettableValue = findSettableValue("scene mode", parameters.getSupportedSceneModes(), "barcode");
        if (findSettableValue != null) {
            parameters.setSceneMode(findSettableValue);
        }
    }

    public static void setZoom(Parameters parameters, double d) {
        if (parameters.isZoomSupported()) {
            Integer indexOfClosestZoom = indexOfClosestZoom(parameters, d);
            if (indexOfClosestZoom != null) {
                if (parameters.getZoom() == indexOfClosestZoom.intValue()) {
                    Log.i(TAG, "Zoom is already set to " + indexOfClosestZoom);
                    return;
                }
                Log.i(TAG, "Setting zoom to " + indexOfClosestZoom);
                parameters.setZoom(indexOfClosestZoom.intValue());
                return;
            }
            return;
        }
        Log.i(TAG, "Zoom is not supported");
    }

    private static Integer indexOfClosestZoom(Parameters parameters, double d) {
        List zoomRatios = parameters.getZoomRatios();
        Log.i(TAG, "Zoom ratios: " + zoomRatios);
        int maxZoom = parameters.getMaxZoom();
        if (zoomRatios == null || zoomRatios.isEmpty() || zoomRatios.size() != maxZoom + 1) {
            Log.w(TAG, "Invalid zoom ratios!");
            return null;
        }
        double d2 = 100.0d * d;
        double d3 = Double.POSITIVE_INFINITY;
        int i = 0;
        for (int i2 = 0; i2 < zoomRatios.size(); i2++) {
            double abs = Math.abs(((double) ((Integer) zoomRatios.get(i2)).intValue()) - d2);
            if (abs < d3) {
                i = i2;
                d3 = abs;
            }
        }
        Log.i(TAG, "Chose zoom ratio of " + (((double) ((Integer) zoomRatios.get(i)).intValue()) / 100.0d));
        return Integer.valueOf(i);
    }

    public static void setInvertColor(Parameters parameters) {
        if ("negative".equals(parameters.getColorEffect())) {
            Log.i(TAG, "Negative effect already set");
            return;
        }
        String findSettableValue = findSettableValue("color effect", parameters.getSupportedColorEffects(), "negative");
        if (findSettableValue != null) {
            parameters.setColorEffect(findSettableValue);
        }
    }

    private static String findSettableValue(String str, Collection<String> collection, String... strArr) {
        Log.i(TAG, "Requesting " + str + " value from among: " + Arrays.toString(strArr));
        Log.i(TAG, "Supported " + str + " values: " + collection);
        if (collection != null) {
            for (String str2 : strArr) {
                if (collection.contains(str2)) {
                    Log.i(TAG, "Can set " + str + " to: " + str2);
                    return str2;
                }
            }
        }
        Log.i(TAG, "No supported values match");
        return null;
    }

    private static String toString(Collection<int[]> collection) {
        if (collection == null || collection.isEmpty()) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            stringBuilder.append(Arrays.toString((int[]) it.next()));
            if (it.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    @TargetApi(15)
    private static String toString(Iterable<Area> iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Area area : iterable) {
            stringBuilder.append(area.rect).append(CoreConstants.COLON_CHAR).append(area.weight).append(' ');
        }
        return stringBuilder.toString();
    }

    public static String collectStats(Parameters parameters) {
        return collectStats(parameters.flatten());
    }

    public static String collectStats(CharSequence charSequence) {
        StringBuilder stringBuilder = new StringBuilder(1000);
        stringBuilder.append("BOARD=").append(Build.BOARD).append('\n');
        stringBuilder.append("BRAND=").append(Build.BRAND).append('\n');
        stringBuilder.append("CPU_ABI=").append(Build.CPU_ABI).append('\n');
        stringBuilder.append("DEVICE=").append(Build.DEVICE).append('\n');
        stringBuilder.append("DISPLAY=").append(Build.DISPLAY).append('\n');
        stringBuilder.append("FINGERPRINT=").append(Build.FINGERPRINT).append('\n');
        stringBuilder.append("HOST=").append(Build.HOST).append('\n');
        stringBuilder.append("ID=").append(Build.ID).append('\n');
        stringBuilder.append("MANUFACTURER=").append(Build.MANUFACTURER).append('\n');
        stringBuilder.append("MODEL=").append(Build.MODEL).append('\n');
        stringBuilder.append("PRODUCT=").append(Build.PRODUCT).append('\n');
        stringBuilder.append("TAGS=").append(Build.TAGS).append('\n');
        stringBuilder.append("TIME=").append(Build.TIME).append('\n');
        stringBuilder.append("TYPE=").append(Build.TYPE).append('\n');
        stringBuilder.append("USER=").append(Build.USER).append('\n');
        stringBuilder.append("VERSION.CODENAME=").append(VERSION.CODENAME).append('\n');
        stringBuilder.append("VERSION.INCREMENTAL=").append(VERSION.INCREMENTAL).append('\n');
        stringBuilder.append("VERSION.RELEASE=").append(VERSION.RELEASE).append('\n');
        stringBuilder.append("VERSION.SDK_INT=").append(VERSION.SDK_INT).append('\n');
        if (charSequence != null) {
            String[] split = SEMICOLON.split(charSequence);
            Arrays.sort(split);
            for (String append : split) {
                stringBuilder.append(append).append('\n');
            }
        }
        return stringBuilder.toString();
    }
}
