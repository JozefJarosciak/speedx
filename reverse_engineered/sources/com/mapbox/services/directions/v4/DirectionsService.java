package com.mapbox.services.directions.v4;

import com.mapbox.services.directions.v4.models.DirectionsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DirectionsService {
    @GET("v4/directions/{profile}/{waypoints}.json")
    Call<DirectionsResponse> getCall(@Header("User-Agent") String str, @Path("profile") String str2, @Path("waypoints") String str3, @Query("access_token") String str4, @Query("alternatives") Boolean bool, @Query("instructions") String str5, @Query("geometry") String str6, @Query("steps") Boolean bool2);
}
