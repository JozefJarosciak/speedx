package com.google.zxing.client.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.journeyapps.barcodescanner.camera.C4141c;
import com.journeyapps.barcodescanner.camera.CameraSettings;

public final class AmbientLightManager implements SensorEventListener {
    private static final float BRIGHT_ENOUGH_LUX = 450.0f;
    private static final float TOO_DARK_LUX = 45.0f;
    private C4141c cameraManager;
    private CameraSettings cameraSettings;
    private Context context;
    private Handler handler = new Handler();
    private Sensor lightSensor;

    public AmbientLightManager(Context context, C4141c c4141c, CameraSettings cameraSettings) {
        this.context = context;
        this.cameraManager = c4141c;
        this.cameraSettings = cameraSettings;
    }

    public void start() {
        if (this.cameraSettings.m16561h()) {
            SensorManager sensorManager = (SensorManager) this.context.getSystemService("sensor");
            this.lightSensor = sensorManager.getDefaultSensor(5);
            if (this.lightSensor != null) {
                sensorManager.registerListener(this, this.lightSensor, 3);
            }
        }
    }

    public void stop() {
        if (this.lightSensor != null) {
            ((SensorManager) this.context.getSystemService("sensor")).unregisterListener(this);
            this.lightSensor = null;
        }
    }

    private void setTorch(final boolean z) {
        this.handler.post(new Runnable() {
            public void run() {
                AmbientLightManager.this.cameraManager.m16608a(z);
            }
        });
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        if (this.cameraManager == null) {
            return;
        }
        if (f <= TOO_DARK_LUX) {
            setTorch(true);
        } else if (f >= BRIGHT_ENOUGH_LUX) {
            setTorch(false);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
