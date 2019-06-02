package com.digits.sdk.android;

import com.twitter.sdk.android.core.C4580e;
import retrofit.http.GET;

public interface DigitsApiClient$AccountService {
    @GET("/1.1/sdk/account.json")
    void verifyAccount(C4580e<bz> c4580e);
}
