package com.mapbox.services.directions.v5;

import com.alipay.sdk.util.C0880h;
import com.mapbox.services.Constants;
import com.mapbox.services.commons.MapboxBuilder;
import com.mapbox.services.commons.MapboxService;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.commons.utils.TextUtils;
import com.mapbox.services.directions.v5.models.DirectionsResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapboxDirections extends MapboxService<DirectionsResponse> {
    private String baseUrl;
    private Builder builder;
    private Call<DirectionsResponse> call;
    private DirectionsService service;

    public static class Builder extends MapboxBuilder {
        private String accessToken;
        private Boolean alternatives;
        private Boolean continueStraight;
        private ArrayList<Position> coordinates;
        private String geometries;
        private String overview;
        private String profile;
        private double[] radiuses;
        private Boolean steps;
        private String user;

        public Builder() {
            this.user = null;
            this.profile = null;
            this.coordinates = null;
            this.accessToken = null;
            this.alternatives = null;
            this.geometries = null;
            this.overview = null;
            this.radiuses = null;
            this.steps = null;
            this.continueStraight = null;
            this.user = "mapbox";
            this.geometries = "polyline";
        }

        public Builder setUser(String str) {
            this.user = str;
            return this;
        }

        public Builder setProfile(String str) {
            this.profile = str;
            return this;
        }

        public Builder setCoordinates(ArrayList<Position> arrayList) {
            this.coordinates = arrayList;
            return this;
        }

        public Builder setOrigin(Position position) {
            if (this.coordinates == null) {
                this.coordinates = new ArrayList();
            }
            this.coordinates.add(0, position);
            return this;
        }

        public Builder setDestination(Position position) {
            if (this.coordinates == null) {
                this.coordinates = new ArrayList();
            }
            this.coordinates.add(position);
            return this;
        }

        public Builder setAccessToken(String str) {
            this.accessToken = str;
            return this;
        }

        public Builder setAlternatives(Boolean bool) {
            this.alternatives = bool;
            return this;
        }

        public Builder setOverview(String str) {
            this.overview = str;
            return this;
        }

        public Builder setRadiuses(double[] dArr) {
            this.radiuses = dArr;
            return this;
        }

        public Builder setSteps(Boolean bool) {
            this.steps = bool;
            return this;
        }

        public Builder setContinueStraight(Boolean bool) {
            this.continueStraight = bool;
            return this;
        }

        public String getUser() {
            return this.user;
        }

        public String getProfile() {
            return this.profile;
        }

        public String getCoordinates() {
            List arrayList = new ArrayList();
            Iterator it = this.coordinates.iterator();
            while (it.hasNext()) {
                Position position = (Position) it.next();
                arrayList.add(String.format(Locale.US, "%f,%f", new Object[]{Double.valueOf(position.getLongitude()), Double.valueOf(position.getLatitude())}));
            }
            return TextUtils.join(C0880h.f2220b, arrayList.toArray());
        }

        public String getAccessToken() {
            return this.accessToken;
        }

        public Boolean isAlternatives() {
            return this.alternatives;
        }

        public String getGeometries() {
            return this.geometries;
        }

        public String getOverview() {
            return this.overview;
        }

        public String getRadiuses() {
            if (this.radiuses == null || this.radiuses.length == 0) {
                return null;
            }
            String[] strArr = new String[this.radiuses.length];
            for (int i = 0; i < this.radiuses.length; i++) {
                strArr[i] = String.format(Locale.US, "%f", new Object[]{Double.valueOf(this.radiuses[i])});
            }
            return TextUtils.join(C0880h.f2220b, strArr);
        }

        public Boolean isSteps() {
            return this.steps;
        }

        public Boolean isContinueStraight() {
            return this.continueStraight;
        }

        public MapboxDirections build() throws ServicesException {
            validateAccessToken(this.accessToken);
            if (this.coordinates == null || this.coordinates.size() < 2) {
                throw new ServicesException("You should provide at least two coordinates (from/to).");
            } else if (this.radiuses == null || this.radiuses.length == this.coordinates.size()) {
                return new MapboxDirections();
            } else {
                throw new ServicesException("There must be as many radiuses as there are coordinates.");
            }
        }
    }

    private MapboxDirections(Builder builder) {
        this.builder = null;
        this.service = null;
        this.call = null;
        this.baseUrl = Constants.BASE_API_URL;
        this.builder = builder;
    }

    public void setBaseUrl(String str) {
        this.baseUrl = str;
    }

    private DirectionsService getService() {
        if (this.service != null) {
            return this.service;
        }
        this.service = (DirectionsService) new retrofit2.Retrofit.Builder().client(getOkHttpClient()).baseUrl(this.baseUrl).addConverterFactory(GsonConverterFactory.create()).build().create(DirectionsService.class);
        return this.service;
    }

    private Call<DirectionsResponse> getCall() {
        if (this.call != null) {
            return this.call;
        }
        this.call = getService().getCall(MapboxService.getHeaderUserAgent(), this.builder.getUser(), this.builder.getProfile(), this.builder.getCoordinates(), this.builder.getAccessToken(), this.builder.isAlternatives(), this.builder.getGeometries(), this.builder.getOverview(), this.builder.getRadiuses(), this.builder.isSteps(), this.builder.isContinueStraight());
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
