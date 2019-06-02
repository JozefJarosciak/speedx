package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.models.C1513g;
import java.util.List;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface StatusesService {
    @FormUrlEncoded
    @POST("/1.1/statuses/destroy/{id}.json")
    void destroy(@Path("id") Long l, @Field("trim_user") Boolean bool, C4580e<C1513g> c4580e);

    @GET("/1.1/statuses/home_timeline.json")
    void homeTimeline(@Query("count") Integer num, @Query("since_id") Long l, @Query("max_id") Long l2, @Query("trim_user") Boolean bool, @Query("exclude_replies") Boolean bool2, @Query("contributor_details") Boolean bool3, @Query("include_entities") Boolean bool4, C4580e<List<C1513g>> c4580e);

    @GET("/1.1/statuses/lookup.json")
    void lookup(@Query("id") String str, @Query("include_entities") Boolean bool, @Query("trim_user") Boolean bool2, @Query("map") Boolean bool3, C4580e<List<C1513g>> c4580e);

    @GET("/1.1/statuses/mentions_timeline.json")
    void mentionsTimeline(@Query("count") Integer num, @Query("since_id") Long l, @Query("max_id") Long l2, @Query("trim_user") Boolean bool, @Query("contributor_details") Boolean bool2, @Query("include_entities") Boolean bool3, C4580e<List<C1513g>> c4580e);

    @FormUrlEncoded
    @POST("/1.1/statuses/retweet/{id}.json")
    void retweet(@Path("id") Long l, @Field("trim_user") Boolean bool, C4580e<C1513g> c4580e);

    @GET("/1.1/statuses/retweets_of_me.json")
    void retweetsOfMe(@Query("count") Integer num, @Query("since_id") Long l, @Query("max_id") Long l2, @Query("trim_user") Boolean bool, @Query("include_entities") Boolean bool2, @Query("include_user_entities") Boolean bool3, C4580e<List<C1513g>> c4580e);

    @GET("/1.1/statuses/show.json")
    void show(@Query("id") Long l, @Query("trim_user") Boolean bool, @Query("include_my_retweet") Boolean bool2, @Query("include_entities") Boolean bool3, C4580e<C1513g> c4580e);

    @FormUrlEncoded
    @POST("/1.1/statuses/unretweet/{id}.json")
    void unretweet(@Path("id") Long l, @Field("trim_user") Boolean bool, C4580e<C1513g> c4580e);

    @Deprecated
    @FormUrlEncoded
    @POST("/1.1/statuses/update.json")
    void update(@Field("status") String str, @Field("in_reply_to_status_id") Long l, @Field("possibly_sensitive") Boolean bool, @Field("lat") Double d, @Field("long") Double d2, @Field("place_id") String str2, @Field("display_cooridnates") Boolean bool2, @Field("trim_user") Boolean bool3, C4580e<C1513g> c4580e);

    @FormUrlEncoded
    @POST("/1.1/statuses/update.json")
    void update(@Field("status") String str, @Field("in_reply_to_status_id") Long l, @Field("possibly_sensitive") Boolean bool, @Field("lat") Double d, @Field("long") Double d2, @Field("place_id") String str2, @Field("display_cooridnates") Boolean bool2, @Field("trim_user") Boolean bool3, @Field("media_ids") String str3, C4580e<C1513g> c4580e);

    @GET("/1.1/statuses/user_timeline.json")
    void userTimeline(@Query("user_id") Long l, @Query("screen_name") String str, @Query("count") Integer num, @Query("since_id") Long l2, @Query("max_id") Long l3, @Query("trim_user") Boolean bool, @Query("exclude_replies") Boolean bool2, @Query("contributor_details") Boolean bool3, @Query("include_rts") Boolean bool4, C4580e<List<C1513g>> c4580e);
}
