package com.mapbox.services.android.geocoder;

import android.location.Address;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.geocoding.v5.models.CarmenFeature;
import java.util.Locale;

public class GeocoderUtils {
    public static Address featureToAddress(CarmenFeature carmenFeature, Locale locale) {
        Address address = new Address(locale);
        address.setAddressLine(0, carmenFeature.getPlaceName());
        address.setFeatureName(carmenFeature.getText());
        Position asPosition = carmenFeature.asPosition();
        address.setLongitude(asPosition.getLongitude());
        address.setLatitude(asPosition.getLatitude());
        return address;
    }
}
