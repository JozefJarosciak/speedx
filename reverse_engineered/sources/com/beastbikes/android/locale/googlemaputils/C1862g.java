package com.beastbikes.android.locale.googlemaputils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.beastbikes.android.modules.cycling.route.dto.C2188b;
import com.beastbikes.framework.android.p056e.C2794a;
import com.beastbikes.framework.android.p056e.C2796c;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: GooglePlaceAPIUtils */
/* renamed from: com.beastbikes.android.locale.googlemaputils.g */
public class C1862g implements ConnectionCallbacks, OnConnectionFailedListener, ResultCallback<PlaceBuffer> {
    /* renamed from: a */
    private boolean f8360a;
    /* renamed from: b */
    private GoogleApiClient f8361b;
    /* renamed from: c */
    private C2794a f8362c;
    /* renamed from: d */
    private Context f8363d;
    /* renamed from: e */
    private C2796c f8364e;
    /* renamed from: f */
    private C1859f f8365f;
    /* renamed from: g */
    private List<C2188b> f8366g;
    /* renamed from: h */
    private List<AutocompletePrediction> f8367h = new ArrayList();
    /* renamed from: i */
    private Logger f8368i = LoggerFactory.getLogger(C1862g.class);

    public /* synthetic */ void onResult(@NonNull Result result) {
        m9697a((PlaceBuffer) result);
    }

    public C1862g(Context context, C2794a c2794a, C2796c c2796c, C1859f c1859f) {
        this.f8363d = context;
        this.f8362c = c2794a;
        this.f8364e = c2796c;
        this.f8365f = c1859f;
        this.f8361b = new Builder(context).addApi(Places.GEO_DATA_API).addOnConnectionFailedListener(this).addConnectionCallbacks(this).build();
        this.f8361b.connect();
    }

    public void onConnected(@Nullable Bundle bundle) {
        this.f8360a = true;
    }

    public void onConnectionSuspended(int i) {
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (this.f8365f != null) {
            this.f8365f.m9684a();
        }
    }

    /* renamed from: a */
    public void m9698a(final String str) {
        if (this.f8360a && this.f8362c != null && !TextUtils.isEmpty(str)) {
            this.f8362c.m13740a(new AsyncTask<Void, Void, Void>(this) {
                /* renamed from: b */
                final /* synthetic */ C1862g f8356b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m9687a((Void[]) objArr);
                }

                /* renamed from: a */
                protected Void m9687a(Void... voidArr) {
                    this.f8356b.f8366g = new ArrayList();
                    AutocompletePredictionBuffer autocompletePredictionBuffer = (AutocompletePredictionBuffer) Places.GeoDataApi.getAutocompletePredictions(this.f8356b.f8361b, str, null, null).await(60, TimeUnit.SECONDS);
                    Status status = autocompletePredictionBuffer.getStatus();
                    if (status.isSuccess()) {
                        this.f8356b.f8367h.clear();
                        Iterator it = autocompletePredictionBuffer.iterator();
                        while (it.hasNext()) {
                            this.f8356b.f8367h.add((AutocompletePrediction) it.next());
                        }
                        for (int i = 0; i < this.f8356b.f8367h.size(); i++) {
                            Places.GeoDataApi.getPlaceById(this.f8356b.f8361b, ((AutocompletePrediction) this.f8356b.f8367h.get(i)).getPlaceId()).setResultCallback(this.f8356b);
                        }
                        if (this.f8356b.f8367h.size() != 0 || this.f8356b.f8365f == null) {
                            autocompletePredictionBuffer.release();
                        } else {
                            this.f8356b.f8365f.m9685a(status);
                        }
                    } else {
                        if (this.f8356b.f8365f != null) {
                            this.f8356b.f8365f.m9685a(status);
                        }
                        this.f8356b.f8368i.error("Error getting place predictions: " + status.toString());
                        autocompletePredictionBuffer.release();
                    }
                    return null;
                }
            }, new Void[0]);
        }
    }

    /* renamed from: a */
    public void m9697a(@NonNull final PlaceBuffer placeBuffer) {
        if (placeBuffer.getStatus().isSuccess()) {
            final Place place = placeBuffer.get(0);
            new C1855a().m9671a(this.f8363d, this.f8364e, place.getLatLng().latitude, place.getLatLng().longitude, new C1821c(this) {
                /* renamed from: c */
                final /* synthetic */ C1862g f8359c;

                /* renamed from: a */
                public void mo3252a(C1856b c1856b) {
                    C2188b c2188b = new C2188b(place);
                    c2188b.m11216a(c1856b.m9678d());
                    this.f8359c.m9693a(c2188b);
                    placeBuffer.release();
                }

                /* renamed from: a */
                public void mo3251a(VolleyError volleyError) {
                    this.f8359c.m9693a(new C2188b(place));
                    placeBuffer.release();
                }
            });
            return;
        }
        this.f8368i.error("Place query did not complete. Error: " + placeBuffer.getStatus().toString());
        placeBuffer.release();
    }

    /* renamed from: a */
    private void m9693a(C2188b c2188b) {
        if (c2188b != null) {
            this.f8366g.add(c2188b);
            if (this.f8366g.size() == this.f8367h.size() && this.f8366g.size() != 0 && this.f8365f != null) {
                this.f8365f.m9686a(this.f8366g);
            }
        }
    }
}
