package com.mapbox.services.geocoding.v5;

import com.mapbox.services.geocoding.v5.models.GeocodingResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GeocodingService {
    @GET("/geocoding/v5/{mode}/{query}.json")
    Call<GeocodingResponse> getCall(@Header("User-Agent") String str, @Path("mode") String str2, @Path("query") String str3, @Query("access_token") String str4, @Query("country") String str5, @Query("proximity") String str6, @Query("types") String str7, @Query("autocomplete") Boolean bool, @Query("bbox") String str8);
}
