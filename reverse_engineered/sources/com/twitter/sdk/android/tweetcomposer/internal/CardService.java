package com.twitter.sdk.android.tweetcomposer.internal;

import com.twitter.sdk.android.core.C4580e;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface CardService {
    @FormUrlEncoded
    @POST("/v2/cards/create.json")
    void create(@Field("card_data") C1516b c1516b, C4580e<C1515a> c4580e);
}
