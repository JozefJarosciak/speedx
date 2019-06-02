package com.mapbox.services.geocoding.v5;

import com.google.gson.GsonBuilder;
import com.mapbox.services.Constants;
import com.mapbox.services.commons.MapboxBuilder;
import com.mapbox.services.commons.MapboxService;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.commons.geojson.Geometry;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.commons.utils.TextUtils;
import com.mapbox.services.geocoding.v5.gson.CarmenGeometryDeserializer;
import com.mapbox.services.geocoding.v5.models.GeocodingResponse;
import java.io.IOException;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapboxGeocoding extends MapboxService<GeocodingResponse> {
    private String baseUrl = Constants.BASE_API_URL;
    private Builder builder = null;
    private Call<GeocodingResponse> call = null;
    private GeocodingService service = null;

    public static class Builder extends MapboxBuilder {
        private String accessToken;
        private Boolean autocomplete = null;
        private String bbox = null;
        private String country = null;
        private String geocodingTypes = null;
        private String mode = GeocodingCriteria.MODE_PLACES;
        private String proximity = null;
        private String query;

        public Builder setAccessToken(String str) {
            this.accessToken = str;
            return this;
        }

        public Builder setLocation(String str) {
            this.query = str;
            return this;
        }

        public Builder setCoordinates(Position position) {
            if (position != null) {
                this.query = String.format(Locale.US, "%f,%f", new Object[]{Double.valueOf(position.getLongitude()), Double.valueOf(position.getLatitude())});
            }
            return this;
        }

        public Builder setMode(String str) {
            this.mode = str;
            return this;
        }

        public Builder setCountry(String str) {
            this.country = str;
            return this;
        }

        public Builder setCountries(String[] strArr) {
            this.country = TextUtils.join(",", strArr);
            return this;
        }

        public Builder setProximity(Position position) {
            if (position != null) {
                this.proximity = String.format(Locale.US, "%f,%f", new Object[]{Double.valueOf(position.getLongitude()), Double.valueOf(position.getLatitude())});
            }
            return this;
        }

        public Builder setGeocodingType(String str) {
            this.geocodingTypes = str;
            return this;
        }

        public Builder setGeocodingTypes(String[] strArr) {
            this.geocodingTypes = TextUtils.join(",", strArr);
            return this;
        }

        public Builder setAutocomplete(boolean z) {
            this.autocomplete = Boolean.valueOf(z);
            return this;
        }

        public Builder setBbox(Position position, Position position2) throws ServicesException {
            return setBbox(position2.getLongitude(), position2.getLatitude(), position.getLongitude(), position.getLatitude());
        }

        public Builder setBbox(double d, double d2, double d3, double d4) throws ServicesException {
            if (d == 0.0d && d2 == 0.0d && d3 == 0.0d && d4 == 0.0d) {
                throw new ServicesException("You provided an empty bounding box");
            }
            this.bbox = String.format(Locale.US, "%f,%f,%f,%f", new Object[]{Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(d4)});
            return this;
        }

        public String getAccessToken() {
            return this.accessToken;
        }

        public String getQuery() {
            return this.query;
        }

        public String getMode() {
            return this.mode;
        }

        public String getCountry() {
            return this.country;
        }

        public String getProximity() {
            return this.proximity;
        }

        public String getGeocodingTypes() {
            return this.geocodingTypes;
        }

        public Boolean getAutocomplete() {
            return this.autocomplete;
        }

        public String getBbox() {
            return this.bbox;
        }

        public MapboxGeocoding build() throws ServicesException {
            validateAccessToken(this.accessToken);
            return new MapboxGeocoding(this);
        }
    }

    public MapboxGeocoding(Builder builder) {
        this.builder = builder;
    }

    public void setBaseUrl(String str) {
        this.baseUrl = str;
    }

    public GeocodingService getService() {
        if (this.service != null) {
            return this.service;
        }
        this.service = (GeocodingService) new retrofit2.Retrofit.Builder().client(getOkHttpClient()).baseUrl(this.baseUrl).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(Geometry.class, new CarmenGeometryDeserializer()).create())).build().create(GeocodingService.class);
        return this.service;
    }

    public Call<GeocodingResponse> getCall() {
        if (this.call != null) {
            return this.call;
        }
        this.call = getService().getCall(MapboxService.getHeaderUserAgent(), this.builder.getMode(), this.builder.getQuery(), this.builder.getAccessToken(), this.builder.getCountry(), this.builder.getProximity(), this.builder.getGeocodingTypes(), this.builder.getAutocomplete(), this.builder.getBbox());
        return this.call;
    }

    public Response<GeocodingResponse> executeCall() throws IOException {
        return getCall().execute();
    }

    public void enqueueCall(Callback<GeocodingResponse> callback) {
        getCall().enqueue(callback);
    }

    public void cancelCall() {
        getCall().cancel();
    }

    public Call<GeocodingResponse> cloneCall() {
        return getCall().clone();
    }
}
