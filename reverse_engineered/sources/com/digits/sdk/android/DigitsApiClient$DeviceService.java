package com.digits.sdk.android;

import com.twitter.sdk.android.core.C4580e;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

protected interface DigitsApiClient$DeviceService {
    @FormUrlEncoded
    @POST("/1.1/device/register.json")
    void register(@Field("raw_phone_number") String str, @Field("text_key") String str2, @Field("send_numeric_pin") Boolean bool, @Field("lang") String str3, @Field("client_identifier_string") String str4, @Field("verification_type") String str5, C4580e<C1471z> c4580e);
}
