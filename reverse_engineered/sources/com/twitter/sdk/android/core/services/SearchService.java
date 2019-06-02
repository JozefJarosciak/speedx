package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.services.params.Geocode;
import retrofit.http.EncodedQuery;
import retrofit.http.GET;
import retrofit.http.Query;

public interface SearchService {
    @GET("/1.1/search/tweets.json")
    void tweets(@Query("q") String str, @EncodedQuery("geocode") Geocode geocode, @Query("lang") String str2, @Query("locale") String str3, @Query("result_type") String str4, @Query("count") Integer num, @Query("until") String str5, @Query("since_id") Long l, @Query("max_id") Long l2, @Query("include_entities") Boolean bool, C4580e<Object> c4580e);
}
