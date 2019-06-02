package com.mapbox.services.staticimage.v1;

import com.alipay.sdk.cons.C0845b;
import com.mapbox.services.commons.MapboxBuilder;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.commons.models.Position;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import okhttp3.HttpUrl;

public class MapboxStaticImage {
    private HttpUrl url;

    public static class Builder extends MapboxBuilder {
        private String accessToken;
        private boolean attribution = true;
        private double bearing = 0.0d;
        private Integer height;
        private Double lat;
        private boolean logo = true;
        private Double lon;
        private double pitch = 0.0d;
        private int precision = -1;
        private boolean retina = false;
        private String styleId;
        private String username = "mapbox";
        private Integer width;
        private Double zoom;

        public Builder setAccessToken(String str) {
            this.accessToken = str;
            return this;
        }

        public Builder setUsername(String str) {
            this.username = str;
            return this;
        }

        public Builder setStyleId(String str) {
            this.styleId = str;
            return this;
        }

        public Builder setLon(double d) {
            this.lon = Double.valueOf(d);
            return this;
        }

        public Builder setLat(double d) {
            this.lat = Double.valueOf(d);
            return this;
        }

        public Builder setLocation(Position position) {
            this.lat = Double.valueOf(position.getLatitude());
            this.lon = Double.valueOf(position.getLongitude());
            return this;
        }

        public Builder setZoom(double d) {
            this.zoom = Double.valueOf(d);
            return this;
        }

        public Builder setBearing(double d) {
            this.bearing = d;
            return this;
        }

        public Builder setPitch(double d) {
            this.pitch = d;
            return this;
        }

        public Builder setWidth(int i) {
            this.width = Integer.valueOf(i);
            return this;
        }

        public Builder setHeight(int i) {
            this.height = Integer.valueOf(i);
            return this;
        }

        public Builder setRetina(boolean z) {
            this.retina = z;
            return this;
        }

        public Builder setAttribution(boolean z) {
            this.attribution = z;
            return this;
        }

        public Builder setLogo(boolean z) {
            this.logo = z;
            return this;
        }

        public Builder setPrecision(int i) {
            this.precision = i;
            return this;
        }

        public String getAccessToken() {
            return this.accessToken;
        }

        public String getUsername() {
            return this.username;
        }

        public String getStyleId() {
            return this.styleId;
        }

        public String getLocationPathSegment() {
            if (this.precision > 0) {
                String str = "0." + new String(new char[this.precision]).replace("\u0000", "0");
                DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
                decimalFormat.applyPattern(str);
                decimalFormat.setRoundingMode(RoundingMode.FLOOR);
                return String.format(Locale.US, "%s,%s,%s,%s,%s", new Object[]{decimalFormat.format(this.lon), decimalFormat.format(this.lat), decimalFormat.format(this.zoom), decimalFormat.format(this.bearing), decimalFormat.format(this.pitch)});
            }
            return String.format(Locale.US, "%f,%f,%f,%f,%f", new Object[]{this.lon, this.lat, this.zoom, Double.valueOf(this.bearing), Double.valueOf(this.pitch)});
        }

        public String getSizePathSegment() {
            String str = this.retina ? "@2x" : "";
            return String.format(Locale.US, "%dx%d%s", new Object[]{this.width, this.height, str});
        }

        public boolean isAttribution() {
            return this.attribution;
        }

        public boolean isLogo() {
            return this.logo;
        }

        public int getPrecision() {
            return this.precision;
        }

        public MapboxStaticImage build() throws ServicesException {
            validateAccessToken(this.accessToken);
            if (this.styleId == null || this.styleId.isEmpty()) {
                throw new ServicesException("You need to set a map style.");
            } else if (this.lon == null || this.lat == null) {
                throw new ServicesException("You need to set the map lon/lat coordinates.");
            } else if (this.zoom == null) {
                throw new ServicesException("You need to set the map zoom level.");
            } else if (this.width == null || this.width.intValue() < 1 || this.width.intValue() > 1280) {
                throw new ServicesException("You need to set a valid image width (between 1 and 1280).");
            } else if (this.height != null && this.height.intValue() >= 1 && this.height.intValue() <= 1280) {
                return new MapboxStaticImage(this);
            } else {
                throw new ServicesException("You need to set a valid image height (between 1 and 1280).");
            }
        }
    }

    public MapboxStaticImage(Builder builder) {
        okhttp3.HttpUrl.Builder a = new okhttp3.HttpUrl.Builder().a(C0845b.f2060a).b("api.mapbox.com").c("styles").c("v1").c(builder.getUsername()).c(builder.getStyleId()).c("static").c(builder.getLocationPathSegment()).c(builder.getSizePathSegment()).a("access_token", builder.getAccessToken());
        if (!builder.isAttribution()) {
            a.a("attribution", "false");
        }
        if (!builder.isLogo()) {
            a.a("logo", "false");
        }
        this.url = a.c();
    }

    public HttpUrl getUrl() {
        return this.url;
    }
}
