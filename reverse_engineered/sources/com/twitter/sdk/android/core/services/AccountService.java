package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.models.User;
import retrofit.http.GET;
import retrofit.http.Query;

public interface AccountService {
    @GET("/1.1/account/verify_credentials.json")
    User verifyCredentials(@Query("include_entities") Boolean bool, @Query("skip_status") Boolean bool2);

    @GET("/1.1/account/verify_credentials.json")
    void verifyCredentials(@Query("include_entities") Boolean bool, @Query("skip_status") Boolean bool2, C4580e<User> c4580e);
}
