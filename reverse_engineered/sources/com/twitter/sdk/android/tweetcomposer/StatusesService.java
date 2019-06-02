package com.twitter.sdk.android.tweetcomposer;

import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.models.C1513g;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface StatusesService {
    @FormUrlEncoded
    @POST("/1.1/statuses/update.json")
    void update(@Field("status") String str, @Field("card_uri") String str2, C4580e<C1513g> c4580e);
}
