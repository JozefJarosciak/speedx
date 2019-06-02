package com.mapbox.services.directions.v4;

import com.alipay.sdk.util.C0880h;
import com.mapbox.services.Constants;
import com.mapbox.services.commons.MapboxBuilder;
import com.mapbox.services.commons.MapboxService;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.commons.utils.TextUtils;
import com.mapbox.services.directions.v4.models.DirectionsResponse;
import com.mapbox.services.directions.v4.models.Waypoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapboxDirections extends MapboxService<DirectionsResponse> {
    private String baseUrl = Constants.BASE_API_URL;
    private Builder builder = null;
    private Call<DirectionsResponse> call = null;
    private DirectionsService service = null;

    public static class Builder extends MapboxBuilder {
        private String accessToken;
        private Boolean alternatives;
        private Waypoint destination;
        private String geometry;
        private String instructions;
        private Waypoint origin;
        private String profile;
        private Boolean steps;
        private List<Waypoint> waypoints;

        public Builder setAccessToken(String str) {
            this.accessToken = str;
            return this;
        }

        public Builder setProfile(String str) {
            this.profile = str;
            return this;
        }

        public Builder setWaypoints(List<Waypoint> list) {
            this.waypoints = list;
            return this;
        }

        public Builder setOrigin(Waypoint waypoint) {
            this.origin = waypoint;
            return this;
        }

        public Builder setDestination(Waypoint waypoint) {
            this.destination = waypoint;
            return this;
        }

        public Builder setAlternatives(Boolean bool) {
            this.alternatives = bool;
            return this;
        }

        public Builder setInstructions(String str) {
            this.instructions = str;
            return this;
        }

        public Builder setGeometry(String str) {
            this.geometry = str;
            return this;
        }

        public Builder setSteps(Boolean bool) {
            this.steps = bool;
            return this;
        }

        public String getAccessToken() {
            return this.accessToken;
        }

        public String getProfile() {
            return this.profile;
        }

        public String getWaypoints() {
            String str = "";
            if (!(this.origin == null || this.destination == null)) {
                this.waypoints = new ArrayList(Arrays.asList(new Waypoint[]{this.origin, this.destination}));
            }
            if (this.waypoints == null || this.waypoints.size() == 0) {
                return str;
            }
            List arrayList = new ArrayList();
            for (Waypoint waypoint : this.waypoints) {
                arrayList.add(String.format(Locale.US, "%f,%f", new Object[]{Double.valueOf(waypoint.getLongitude()), Double.valueOf(waypoint.getLatitude())}));
            }
            return TextUtils.join(C0880h.f2220b, arrayList.toArray());
        }

        public Waypoint getOrigin() {
            return this.origin;
        }

        public Waypoint getDestination() {
            return this.destination;
        }

        public Boolean isAlternatives() {
            return this.alternatives;
        }

        public String getInstructions() {
            return this.instructions;
        }

        public String getGeometry() {
            return this.geometry;
        }

        public Boolean isSteps() {
            return this.steps;
        }

        public MapboxDirections build() throws ServicesException {
            validateAccessToken(this.accessToken);
            this.geometry = "polyline";
            return new MapboxDirections(this);
        }
    }

    public MapboxDirections(Builder builder) {
        this.builder = builder;
    }

    public void setBaseUrl(String str) {
        this.baseUrl = str;
    }

    public DirectionsService getService() {
        if (this.service != null) {
            return this.service;
        }
        this.service = (DirectionsService) new retrofit2.Retrofit.Builder().client(getOkHttpClient()).baseUrl(this.baseUrl).addConverterFactory(GsonConverterFactory.create()).build().create(DirectionsService.class);
        return this.service;
    }

    public Call<DirectionsResponse> getCall() {
        if (this.call != null) {
            return this.call;
        }
        this.call = getService().getCall(MapboxService.getHeaderUserAgent(), this.builder.getProfile(), this.builder.getWaypoints(), this.builder.getAccessToken(), this.builder.isAlternatives(), this.builder.getInstructions(), this.builder.getGeometry(), this.builder.isSteps());
        return this.call;
    }

    public Response<DirectionsResponse> executeCall() throws IOException {
        return getCall().execute();
    }

    public void enqueueCall(Callback<DirectionsResponse> callback) {
        getCall().enqueue(callback);
    }

    public void cancelCall() {
        getCall().cancel();
    }

    public Call<DirectionsResponse> cloneCall() {
        return getCall().clone();
    }
}
