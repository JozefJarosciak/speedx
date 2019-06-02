package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.C4580e;
import retrofit.http.GET;
import retrofit.http.Query;

public interface CollectionService {
    @GET("/1.1/collections/entries.json")
    void collection(@Query("id") String str, @Query("count") Integer num, @Query("max_position") Long l, @Query("min_position") Long l2, C4580e<Object> c4580e);
}
