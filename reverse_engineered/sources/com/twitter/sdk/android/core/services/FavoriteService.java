package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.models.C1513g;
import java.util.List;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface FavoriteService {
    @FormUrlEncoded
    @POST("/1.1/favorites/create.json")
    void create(@Field("id") Long l, @Field("include_entities") Boolean bool, C4580e<C1513g> c4580e);

    @FormUrlEncoded
    @POST("/1.1/favorites/destroy.json")
    void destroy(@Field("id") Long l, @Field("include_entities") Boolean bool, C4580e<C1513g> c4580e);

    @GET("/1.1/favorites/list.json")
    void list(@Query("user_id") Long l, @Query("screen_name") String str, @Query("count") Integer num, @Query("since_id") String str2, @Query("max_id") String str3, @Query("include_entities") Boolean bool, C4580e<List<C1513g>> c4580e);
}
