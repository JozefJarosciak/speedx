package com.mapbox.services.mapmatching.v4;

import com.google.gson.GsonBuilder;
import com.mapbox.services.Constants;
import com.mapbox.services.commons.MapboxBuilder;
import com.mapbox.services.commons.MapboxService;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.commons.geojson.Feature;
import com.mapbox.services.commons.geojson.Geometry;
import com.mapbox.services.commons.geojson.LineString;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import com.mapbox.services.mapmatching.v4.gson.MapMatchingGeometryDeserializer;
import com.mapbox.services.mapmatching.v4.models.MapMatchingResponse;
import java.io.IOException;
import okhttp3.C5602x;
import okhttp3.C5608s;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapboxMapMatching extends MapboxService<MapMatchingResponse> {
    private String baseUrl;
    private Builder builder;
    private Call<MapMatchingResponse> call;
    private MapMatchingService service;

    public static class Builder extends MapboxBuilder {
        private String accessToken;
        private String geometry = "polyline";
        private Integer gpsPrecison;
        private String profile;
        private LineString trace;

        public Builder setAccessToken(String str) {
            this.accessToken = str;
            return this;
        }

        public String getAccessToken() {
            return this.accessToken;
        }

        public Builder setProfile(String str) {
            this.profile = str;
            return this;
        }

        public String getProfile() {
            return this.profile;
        }

        public String getGeometry() {
            return this.geometry;
        }

        public Builder setNoGeometry() {
            this.geometry = "false";
            return this;
        }

        public Integer getGpsPrecison() {
            return this.gpsPrecison;
        }

        public C5602x getTrace() {
            return C5602x.create(C5608s.a("application/json"), Feature.fromGeometry(this.trace).toJson());
        }

        public Builder setGpsPrecison(Integer num) {
            this.gpsPrecison = num;
            return this;
        }

        public Builder setTrace(LineString lineString) {
            this.trace = lineString;
            return this;
        }

        private void validateProfile() throws ServicesException {
            if (this.profile == null || !(this.profile.equals(DirectionsCriteria.PROFILE_CYCLING) || this.profile.equals(DirectionsCriteria.PROFILE_DRIVING) || this.profile.equals(DirectionsCriteria.PROFILE_WALKING))) {
                throw new ServicesException("Using Mapbox Map Matching requires setting a valid profile.");
            }
        }

        private void validateGpsPrecision() throws ServicesException {
            if (this.gpsPrecison == null) {
                return;
            }
            if (this.gpsPrecison.intValue() < 1 || this.gpsPrecison.intValue() > 10) {
                throw new ServicesException("Using Mapbox Map Matching requires setting a valid GPS precision.");
            }
        }

        private void validateTrace() throws ServicesException {
            if (this.trace == null || this.trace.getCoordinates() == null) {
                throw new ServicesException("Using Mapbox Map Matching requires to set some coordinates representing the trace.");
            } else if (this.trace.getCoordinates().size() > 100) {
                throw new ServicesException("The Map Matching API is limited to processing traces with up to 100 coordinates. If you need to process longer traces, you can split the trace and make multiple requests.");
            }
        }

        public MapboxMapMatching build() throws ServicesException {
            validateAccessToken(this.accessToken);
            validateProfile();
            validateGpsPrecision();
            validateTrace();
            return new MapboxMapMatching();
        }
    }

    private MapboxMapMatching(Builder builder) {
        this.builder = null;
        this.service = null;
        this.call = null;
        this.baseUrl = Constants.BASE_API_URL;
        this.builder = builder;
    }

    public void setBaseUrl(String str) {
        this.baseUrl = str;
    }

    private MapMatchingService getService() {
        if (this.service != null) {
            return this.service;
        }
        this.service = (MapMatchingService) new retrofit2.Retrofit.Builder().client(getOkHttpClient()).baseUrl(this.baseUrl).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(Geometry.class, new MapMatchingGeometryDeserializer()).create())).build().create(MapMatchingService.class);
        return this.service;
    }

    public Call<MapMatchingResponse> getCall() {
        if (this.call != null) {
            return this.call;
        }
        this.call = getService().getCall(MapboxService.getHeaderUserAgent(), this.builder.getProfile(), this.builder.getAccessToken(), this.builder.getGeometry(), this.builder.getGpsPrecison(), this.builder.getTrace());
        return this.call;
    }

    public Response<MapMatchingResponse> executeCall() throws IOException {
        return getCall().execute();
    }

    public void enqueueCall(Callback<MapMatchingResponse> callback) {
        getCall().enqueue(callback);
    }

    public void cancelCall() {
        getCall().cancel();
    }

    public Call<MapMatchingResponse> cloneCall() {
        return getCall().clone();
    }
}
