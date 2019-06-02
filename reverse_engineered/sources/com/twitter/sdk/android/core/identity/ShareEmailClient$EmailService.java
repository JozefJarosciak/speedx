package com.twitter.sdk.android.core.identity;

import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.models.User;
import retrofit.http.GET;
import retrofit.http.Query;

interface ShareEmailClient$EmailService {
    @GET("/1.1/account/verify_credentials.json?include_email=true")
    void verifyCredentials(@Query("include_entities") Boolean bool, @Query("skip_status") Boolean bool2, C4580e<User> c4580e);
}
