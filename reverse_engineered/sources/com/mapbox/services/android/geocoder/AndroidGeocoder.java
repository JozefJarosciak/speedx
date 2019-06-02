package com.mapbox.services.android.geocoder;

import android.content.Context;
import android.location.Address;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.geocoding.v5.MapboxGeocoding.Builder;
import com.mapbox.services.geocoding.v5.models.CarmenFeature;
import com.mapbox.services.geocoding.v5.models.GeocodingResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import retrofit2.Response;

public class AndroidGeocoder {
    private static final String LOG_TAG = "MapboxGeocoding";
    private String accessToken;
    private Context context;
    private Locale locale;

    public AndroidGeocoder(Context context, Locale locale) {
        this.context = context;
        this.locale = locale;
    }

    public AndroidGeocoder(Context context) {
        this.context = context;
    }

    public static boolean isPresent() {
        return true;
    }

    public List<Address> getFromLocation(double d, double d2, int i) throws IOException, ServicesException {
        List<Address> arrayList = new ArrayList();
        Response executeCall = new Builder().setAccessToken(this.accessToken).setCoordinates(Position.fromCoordinates(d2, d)).build().executeCall();
        if (!executeCall.isSuccessful()) {
            return arrayList;
        }
        List features = ((GeocodingResponse) executeCall.body()).getFeatures();
        if (features.size() > i) {
            features = features.subList(0, i);
        }
        for (CarmenFeature featureToAddress : r0) {
            arrayList.add(GeocoderUtils.featureToAddress(featureToAddress, this.locale));
        }
        return arrayList;
    }

    public List<Address> getFromLocationName(String str, int i) throws IOException, ServicesException {
        List<Address> arrayList = new ArrayList();
        Response executeCall = new Builder().setAccessToken(this.accessToken).setLocation(str).build().executeCall();
        if (!executeCall.isSuccessful()) {
            return arrayList;
        }
        List features = ((GeocodingResponse) executeCall.body()).getFeatures();
        if (features.size() > i) {
            features = features.subList(0, i);
        }
        for (CarmenFeature featureToAddress : r0) {
            arrayList.add(GeocoderUtils.featureToAddress(featureToAddress, this.locale));
        }
        return arrayList;
    }

    public List<Address> getFromLocationName(String str, int i, double d, double d2, double d3, double d4) throws IOException, ServicesException {
        List<Address> arrayList = new ArrayList();
        Response executeCall = new Builder().setAccessToken(this.accessToken).setLocation(str).setProximity(Position.fromCoordinates((d2 + d4) / 2.0d, (d + d3) / 2.0d)).build().executeCall();
        if (!executeCall.isSuccessful()) {
            return arrayList;
        }
        List<CarmenFeature> features = ((GeocodingResponse) executeCall.body()).getFeatures();
        Collection arrayList2 = new ArrayList();
        for (CarmenFeature carmenFeature : features) {
            Position asPosition = carmenFeature.asPosition();
            if (asPosition.getLatitude() < d) {
                arrayList2.add(carmenFeature);
            } else if (asPosition.getLatitude() > d3) {
                arrayList2.add(carmenFeature);
            } else if (asPosition.getLongitude() < d2) {
                arrayList2.add(carmenFeature);
            } else if (asPosition.getLongitude() > d4) {
                arrayList2.add(carmenFeature);
            }
        }
        if (arrayList2.size() > 0) {
            features.removeAll(arrayList2);
        }
        List subList;
        if (features.size() > i) {
            subList = features.subList(0, i);
        } else {
            subList = features;
        }
        for (CarmenFeature carmenFeature2 : r0) {
            arrayList.add(GeocoderUtils.featureToAddress(carmenFeature2, this.locale));
        }
        return arrayList;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }
}
