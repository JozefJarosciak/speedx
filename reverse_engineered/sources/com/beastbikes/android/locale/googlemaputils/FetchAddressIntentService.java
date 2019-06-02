package com.beastbikes.android.locale.googlemaputils;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.alipay.sdk.util.C0880h;
import com.beastbikes.android.C1373R;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FetchAddressIntentService extends IntentService {
    /* renamed from: a */
    protected ResultReceiver f8327a;
    /* renamed from: b */
    private String f8328b = "FetchAddressIntentService";
    /* renamed from: c */
    private Logger f8329c = LoggerFactory.getLogger(FetchAddressIntentService.class);

    public FetchAddressIntentService() {
        super("GoogleFetchAddressIntentService");
        this.f8329c.info("FetchAddressIntentService is constructed");
    }

    protected void onHandleIntent(Intent intent) {
        List fromLocation;
        String str;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String str2 = "";
        Location location = (Location) intent.getParcelableExtra("com.google.android.gms.location.sample.locationaddress.LOCATION_DATA_EXTRA");
        this.f8327a = (ResultReceiver) intent.getParcelableExtra("com.google.android.gms.location.sample.locationaddress.RECEIVER");
        try {
            fromLocation = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            str = str2;
        } catch (IOException e) {
            str = getString(C1373R.string.service_not_available);
            this.f8329c.error(str);
            fromLocation = null;
        } catch (IllegalArgumentException e2) {
            str = getString(C1373R.string.invalid_lat_long_used);
            this.f8329c.error(str);
            fromLocation = null;
        }
        if (fromLocation == null || fromLocation.size() == 0) {
            if (str.isEmpty()) {
                str = getString(C1373R.string.no_address_found);
            }
            m9657a(1, str);
            return;
        }
        Address address = (Address) fromLocation.get(0);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
            stringBuilder.append(address.getAddressLine(i));
        }
        m9657a(0, stringBuilder.toString() + C0880h.f2220b + address.getCountryCode());
    }

    /* renamed from: a */
    private void m9657a(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("com.google.android.gms.location.sample.locationaddress.RESULT_DATA_KEY", str);
        this.f8327a.send(i, bundle);
    }
}
