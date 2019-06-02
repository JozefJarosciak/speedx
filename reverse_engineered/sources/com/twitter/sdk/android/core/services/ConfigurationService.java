package com.twitter.sdk.android.core.services;

import com.twitter.sdk.android.core.C4580e;
import retrofit.http.GET;

public interface ConfigurationService {
    @GET("/1.1/help/configuration.json")
    void configuration(C4580e<Object> c4580e);
}
