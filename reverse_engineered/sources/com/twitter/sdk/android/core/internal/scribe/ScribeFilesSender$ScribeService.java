package com.twitter.sdk.android.core.internal.scribe;

import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

interface ScribeFilesSender$ScribeService {
    @Headers({"Content-Type: application/x-www-form-urlencoded;charset=UTF-8"})
    @FormUrlEncoded
    @POST("/{version}/jot/{type}")
    Response upload(@Path("version") String str, @Path("type") String str2, @Field("log[]") String str3);

    @Headers({"Content-Type: application/x-www-form-urlencoded;charset=UTF-8"})
    @FormUrlEncoded
    @POST("/scribe/{sequence}")
    Response uploadSequence(@Path("sequence") String str, @Field("log[]") String str2);
}
