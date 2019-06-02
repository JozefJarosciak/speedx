package com.beastbikes.android.locale.googlemaputils;

import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.alipay.sdk.util.C0880h;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import java.lang.ref.WeakReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleMapManager {
    /* renamed from: b */
    private static final Logger f8333b = LoggerFactory.getLogger(GoogleMapManager.class);
    /* renamed from: a */
    private GoogleApiClient f8334a;
    /* renamed from: c */
    private C1851a f8335c = new C1851a();
    /* renamed from: d */
    private C1852b f8336d = new C1852b();
    /* renamed from: e */
    private WeakReference<C1857d> f8337e;
    /* renamed from: f */
    private WeakReference<C1858e> f8338f;

    class AddressResultReceiver extends ResultReceiver {
        /* renamed from: a */
        final /* synthetic */ GoogleMapManager f8330a;

        protected void onReceiveResult(int i, Bundle bundle) {
            if (bundle != null) {
                C1858e c1858e = (C1858e) this.f8330a.f8338f.get();
                if (c1858e != null) {
                    Object string = bundle.getString("com.google.android.gms.location.sample.locationaddress.RESULT_DATA_KEY");
                    if (!TextUtils.isEmpty(string) && i == 0) {
                        String[] split = string.split(C0880h.f2220b);
                        if (split.length >= 2) {
                            c1858e.m9683a(split[0], split[1]);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: com.beastbikes.android.locale.googlemaputils.GoogleMapManager$a */
    private class C1851a implements ConnectionCallbacks {
        /* renamed from: a */
        final /* synthetic */ GoogleMapManager f8331a;

        private C1851a(GoogleMapManager googleMapManager) {
            this.f8331a = googleMapManager;
        }

        public void onConnected(Bundle bundle) {
            if (bundle != null) {
                C1857d c1857d = (C1857d) this.f8331a.f8337e.get();
                if (c1857d != null) {
                    c1857d.mo3432a(LocationServices.FusedLocationApi.getLastLocation(this.f8331a.f8334a), bundle);
                }
            }
        }

        public void onConnectionSuspended(int i) {
            C1857d c1857d = (C1857d) this.f8331a.f8337e.get();
            if (c1857d != null) {
                c1857d.mo3431a(i);
            }
        }
    }

    /* renamed from: com.beastbikes.android.locale.googlemaputils.GoogleMapManager$b */
    private class C1852b implements OnConnectionFailedListener {
        /* renamed from: a */
        final /* synthetic */ GoogleMapManager f8332a;

        private C1852b(GoogleMapManager googleMapManager) {
            this.f8332a = googleMapManager;
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            if (this.f8332a.f8337e.get() != null) {
                ((C1857d) this.f8332a.f8337e.get()).mo3433a(connectionResult);
            }
        }
    }

    /* renamed from: a */
    public synchronized void m9663a(Context context, C1857d c1857d) {
        this.f8337e = new WeakReference(c1857d);
        if (context != null) {
            this.f8334a = new Builder(context).addConnectionCallbacks(this.f8335c).addOnConnectionFailedListener(this.f8336d).addApi(LocationServices.API).build();
        }
    }

    /* renamed from: a */
    public static boolean m9659a(Context context) {
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable == 1 || isGooglePlayServicesAvailable == 9 || isGooglePlayServicesAvailable == 3) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public synchronized void m9662a() {
        if (!(this.f8334a == null || this.f8334a.isConnected())) {
            this.f8334a.connect();
        }
    }

    /* renamed from: b */
    public synchronized void m9664b() {
        if (this.f8334a != null && this.f8334a.isConnected()) {
            this.f8334a.disconnect();
        }
    }

    /* renamed from: c */
    public GoogleApiClient m9665c() {
        return this.f8334a;
    }
}
