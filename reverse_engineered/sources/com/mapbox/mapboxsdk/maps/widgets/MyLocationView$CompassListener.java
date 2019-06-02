package com.mapbox.mapboxsdk.maps.widgets;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import com.mapbox.mapboxsdk.camera.CameraPosition.Builder;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;

class MyLocationView$CompassListener implements SensorEventListener {
    private long compassUpdateNextTimestamp = 0;
    private int currentDegree = 0;
    float[] matrix = new float[9];
    float[] orientation = new float[3];
    private Sensor rotationVectorSensor;
    private final SensorManager sensorManager;
    final /* synthetic */ MyLocationView this$0;

    MyLocationView$CompassListener(MyLocationView myLocationView, Context context) {
        this.this$0 = myLocationView;
        this.sensorManager = (SensorManager) context.getSystemService("sensor");
        this.rotationVectorSensor = this.sensorManager.getDefaultSensor(11);
    }

    public void onResume() {
        this.sensorManager.registerListener(this, this.rotationVectorSensor, 1);
    }

    public void onPause() {
        this.sensorManager.unregisterListener(this, this.rotationVectorSensor);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime >= this.compassUpdateNextTimestamp && sensorEvent.sensor.getType() == 11) {
            SensorManager.getRotationMatrixFromVector(this.matrix, sensorEvent.values);
            SensorManager.getOrientation(this.matrix, this.orientation);
            this.currentDegree = (int) ((float) Math.toDegrees((double) SensorManager.getOrientation(this.matrix, this.orientation)[0]));
            MyLocationView.access$100(this.this$0, (float) this.currentDegree);
            if (MyLocationView.access$200(this.this$0) == 4) {
                rotateCamera();
            }
            this.compassUpdateNextTimestamp = elapsedRealtime + 500;
        }
    }

    private void rotateCamera() {
        Builder builder = new Builder();
        builder.bearing((double) this.currentDegree);
        MyLocationView.access$300(this.this$0).easeCamera(CameraUpdateFactory.newCameraPosition(builder.build()), 500, false, false, null);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
