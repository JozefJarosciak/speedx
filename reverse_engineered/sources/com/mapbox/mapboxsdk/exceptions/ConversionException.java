package com.mapbox.mapboxsdk.exceptions;

public class ConversionException extends RuntimeException {
    public ConversionException(String str) {
        super(str);
    }

    public ConversionException(String str, Throwable th) {
        super(str, th);
    }

    public ConversionException(Throwable th) {
        super(th);
    }
}
