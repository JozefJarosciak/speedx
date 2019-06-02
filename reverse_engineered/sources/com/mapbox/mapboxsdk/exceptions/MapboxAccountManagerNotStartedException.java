package com.mapbox.mapboxsdk.exceptions;

public class MapboxAccountManagerNotStartedException extends RuntimeException {
    public MapboxAccountManagerNotStartedException() {
        super("\nMapboxAccountManager was not started correctly. Use MapboxAccountManager#start(Context, String) to initialise. \nMore information in this guide https://www.mapbox.com/help/first-steps-android-sdk/#access-tokens.");
    }
}
