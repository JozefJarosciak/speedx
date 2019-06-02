package com.mapbox.mapboxsdk.exceptions;

public class InvalidAccessTokenException extends RuntimeException {
    public InvalidAccessTokenException() {
        super("\nUsing MapView requires setting a valid access token. Use setAccessToken on Mapview to provide one. \nPlease see https://www.mapbox.com/help/create-api-access-token/ to learn how to create one.\nMore information in this guide https://www.mapbox.com/help/first-steps-android-sdk/#access-tokens.");
    }
}
