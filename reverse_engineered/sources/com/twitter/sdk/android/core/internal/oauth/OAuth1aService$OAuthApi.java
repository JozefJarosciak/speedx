package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.C4580e;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

interface OAuth1aService$OAuthApi {
    @POST("/oauth/access_token")
    void getAccessToken(@Header("Authorization") String str, @Query("oauth_verifier") String str2, @Body String str3, C4580e<Response> c4580e);

    @POST("/oauth/request_token")
    void getTempToken(@Header("Authorization") String str, @Body String str2, C4580e<Response> c4580e);
}
