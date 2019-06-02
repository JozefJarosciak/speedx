package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.C4580e;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;

interface OAuth2Service$OAuth2Api {
    @Headers({"Content-Type: application/x-www-form-urlencoded;charset=UTF-8"})
    @FormUrlEncoded
    @POST("/oauth2/token")
    void getAppAuthToken(@Header("Authorization") String str, @Field("grant_type") String str2, C4580e<AppAuthToken> c4580e);

    @POST("/1.1/guest/activate.json")
    void getGuestToken(@Header("Authorization") String str, @Body String str2, C4580e<C1501b> c4580e);
}
