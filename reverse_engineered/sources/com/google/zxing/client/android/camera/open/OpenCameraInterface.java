package com.google.zxing.client.android.camera.open;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.Log;

public final class OpenCameraInterface {
    public static final int NO_REQUESTED_CAMERA = -1;
    private static final String TAG = OpenCameraInterface.class.getName();

    private OpenCameraInterface() {
    }

    public static int getCameraId(int i) {
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras == 0) {
            Log.w(TAG, "No cameras!");
            return -1;
        }
        int i2;
        Object obj = i >= 0 ? 1 : null;
        if (obj == null) {
            i2 = 0;
            while (i2 < numberOfCameras) {
                CameraInfo cameraInfo = new CameraInfo();
                Camera.getCameraInfo(i2, cameraInfo);
                if (cameraInfo.facing == 0) {
                    break;
                }
                i2++;
            }
        } else {
            i2 = i;
        }
        if (i2 < numberOfCameras) {
            return i2;
        }
        if (obj == null) {
            return 0;
        }
        return -1;
    }

    public static Camera open(int i) {
        int cameraId = getCameraId(i);
        if (cameraId == -1) {
            return null;
        }
        return Camera.open(cameraId);
    }
}
