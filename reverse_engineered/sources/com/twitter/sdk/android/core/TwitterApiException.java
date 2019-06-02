package com.twitter.sdk.android.core;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.twitter.sdk.android.core.models.C1511a;
import io.fabric.sdk.android.C1520c;
import org.apache.http.HttpStatus;
import retrofit.RetrofitError;
import retrofit.mime.TypedByteArray;

public class TwitterApiException extends TwitterException {
    public static final int DEFAULT_ERROR_CODE = 0;
    private final C1511a apiError;
    private final RetrofitError retrofitError;
    private final C4658p twitterRateLimit;

    TwitterApiException(RetrofitError retrofitError) {
        super(createExceptionMessage(retrofitError));
        setStackTrace(retrofitError.getStackTrace());
        this.retrofitError = retrofitError;
        this.twitterRateLimit = createRateLimit(retrofitError);
        this.apiError = readApiError(retrofitError);
    }

    private static String createExceptionMessage(RetrofitError retrofitError) {
        if (retrofitError.getMessage() != null) {
            return retrofitError.getMessage();
        }
        if (retrofitError.getResponse() != null) {
            return "Status: " + retrofitError.getResponse().getStatus();
        }
        return "unknown error";
    }

    private static C4658p createRateLimit(RetrofitError retrofitError) {
        if (retrofitError.getResponse() != null) {
            return new C4658p(retrofitError.getResponse().getHeaders());
        }
        return null;
    }

    public int getErrorCode() {
        return this.apiError == null ? 0 : this.apiError.b();
    }

    public String getErrorMessage() {
        return this.apiError == null ? null : this.apiError.a();
    }

    public boolean canRetry() {
        int status = this.retrofitError.getResponse().getStatus();
        return status < HttpStatus.SC_BAD_REQUEST || status > 499;
    }

    public RetrofitError getRetrofitError() {
        return this.retrofitError;
    }

    public C4658p getTwitterRateLimit() {
        return this.twitterRateLimit;
    }

    public static final TwitterApiException convert(RetrofitError retrofitError) {
        return new TwitterApiException(retrofitError);
    }

    public static C1511a readApiError(RetrofitError retrofitError) {
        if (retrofitError == null || retrofitError.getResponse() == null || retrofitError.getResponse().getBody() == null) {
            return null;
        }
        byte[] bytes = ((TypedByteArray) retrofitError.getResponse().getBody()).getBytes();
        if (bytes == null) {
            return null;
        }
        try {
            return m18131a(new String(bytes, "UTF-8"));
        } catch (Throwable e) {
            C1520c.h().mo6222d("Twitter", "Failed to convert to string", e);
            return null;
        }
    }

    /* renamed from: a */
    static C1511a m18131a(String str) {
        try {
            C1511a[] c1511aArr = (C1511a[]) new Gson().fromJson(new JsonParser().parse(str).getAsJsonObject().get("errors"), C1511a[].class);
            if (c1511aArr.length == 0) {
                return null;
            }
            return c1511aArr[0];
        } catch (Throwable e) {
            C1520c.h().mo6222d("Twitter", "Invalid json: " + str, e);
            return null;
        } catch (Throwable e2) {
            C1520c.h().mo6222d("Twitter", "Unexpected response: " + str, e2);
            return null;
        }
    }
}
