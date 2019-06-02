package com.mapbox.services.mapmatching.v4;

import com.mapbox.services.mapmatching.v4.models.MapMatchingResponse;
import okhttp3.C5602x;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MapMatchingService {
    @POST("matching/v4/{profile}.json")
    Call<MapMatchingResponse> getCall(@Header("User-Agent") String str, @Path("profile") String str2, @Query("access_token") String str3, @Query("geometry") String str4, @Query("gps_precision") Integer num, @Body C5602x c5602x);
}
