package com.digits.sdk.android;

import android.support.annotation.NonNull;
import com.twitter.sdk.android.core.TwitterApiException;
import com.twitter.sdk.android.core.TwitterException;

public class DigitsException extends RuntimeException {
    private final AuthConfig config;
    private final int errorCode;

    DigitsException(String str) {
        this(str, -1, new AuthConfig());
    }

    DigitsException(String str, int i, @NonNull AuthConfig authConfig) {
        super(str);
        this.errorCode = i;
        this.config = authConfig;
    }

    /* renamed from: a */
    static DigitsException m13853a(az azVar, TwitterException twitterException) {
        if (!(twitterException instanceof TwitterApiException)) {
            return new DigitsException(azVar.mo3645a());
        }
        TwitterApiException twitterApiException = (TwitterApiException) twitterException;
        return createException(twitterApiException.getErrorCode(), getMessageForApiError(azVar, twitterApiException), (AuthConfig) twitterApiException.getRetrofitError().getBodyAs(AuthConfig.class));
    }

    private static DigitsException createException(int i, String str, AuthConfig authConfig) {
        if (i == 32) {
            return new CouldNotAuthenticateException(str, i, authConfig);
        }
        if (i == 286) {
            return new OperatorUnsupportedException(str, i, authConfig);
        }
        if (isUnrecoverable(i)) {
            return new UnrecoverableException(str, i, authConfig);
        }
        return new DigitsException(str, i, authConfig);
    }

    private static boolean isUnrecoverable(int i) {
        return i == 269 || i == 235 || i == 237 || i == 299 || i == 284;
    }

    private static String getMessageForApiError(az azVar, TwitterApiException twitterApiException) {
        if (twitterApiException.getRetrofitError().isNetworkError()) {
            return azVar.mo3647b();
        }
        return azVar.mo3646a(twitterApiException.getErrorCode());
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    @NonNull
    public AuthConfig getConfig() {
        return this.config;
    }
}
