package com.mapbox.mapboxsdk.exceptions;

public class TelemetryServiceNotConfiguredException extends RuntimeException {
    public TelemetryServiceNotConfiguredException() {
        super("\nTelemetryService is not configured in your applications AndroidManifest.xml. \nPlease add \"com.mapbox.mapboxsdk.telemetry.TelemetryService\" service in your applications AndroidManifest.xml\nFor an example visit http://goo.gl/cET0Jn. For more information visit https://www.mapbox.com/android-sdk/.");
    }
}
