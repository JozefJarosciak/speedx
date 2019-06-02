package com.mapbox.services.directions.v5;

import com.mapbox.services.directions.v5.models.DirectionsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DirectionsService {
    @GET("directions/v5/{user}/{profile}/{coordinates}")
    Call<DirectionsResponse> getCall(@Header("User-Agent") String str, @Path("user") String str2, @Path("profile") String str3, @Path("coordinates") String str4, @Query("access_token") String str5, @Query("alternatives") Boolean bool, @Query("geometries") String str6, @Query("overview") String str7, @Query("radiuses") String str8, @Query("steps") Boolean bool2, @Query("continue_straight") Boolean bool3);
}
