package com.digits.sdk.android;

import com.twitter.sdk.android.core.C4580e;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

protected interface DigitsApiClient$SdkService {
    @FormUrlEncoded
    @POST("/1.1/sdk/account.json")
    void account(@Field("phone_number") String str, @Field("numeric_pin") String str2, C4580e<as> c4580e);

    @FormUrlEncoded
    @POST("/1/sdk/login")
    void auth(@Field("x_auth_phone_number") String str, @Field("verification_type") String str2, @Field("lang") String str3, C4580e<C1470g> c4580e);

    @FormUrlEncoded
    @POST("/1.1/sdk/account/email")
    void email(@Field("email_address") String str, C4580e<aq> c4580e);

    @FormUrlEncoded
    @POST("/auth/1/xauth_challenge.json")
    void login(@Field("login_verification_request_id") String str, @Field("login_verification_user_id") long j, @Field("login_verification_challenge_response") String str2, C4580e<aq> c4580e);

    @FormUrlEncoded
    @POST("/auth/1/xauth_pin.json")
    void verifyPin(@Field("login_verification_request_id") String str, @Field("login_verification_user_id") long j, @Field("pin") String str2, C4580e<aq> c4580e);
}
