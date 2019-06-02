package com.beastbikes.android.ble.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.dto.NavigationLocation;
import com.beastbikes.android.ble.ui.C1744a.C1695b;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.utils.C2558g;
import com.mapbox.mapboxsdk.geometry.LatLng;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TargetApi(18)
public class SelectOnMapActivity extends NavigationBaseActivity implements OnGestureListener, OnTouchListener, C1695b {
    /* renamed from: d */
    private static final Logger f7663d = LoggerFactory.getLogger("SelectOnMapActivity");
    /* renamed from: e */
    private C1744a f7664e;
    /* renamed from: f */
    private ArrayList<NavigationLocation> f7665f;
    /* renamed from: g */
    private GestureDetector f7666g;
    /* renamed from: h */
    private ImageView f7667h;
    /* renamed from: i */
    private boolean f7668i;
    /* renamed from: j */
    private C1651c f7669j;
    /* renamed from: k */
    private AsyncTask<String, Void, ArrayList<NavigationLocation>> f7670k;
    /* renamed from: l */
    private int f7671l = 0;
    /* renamed from: m */
    private int f7672m;
    /* renamed from: n */
    private int f7673n;

    /* renamed from: com.beastbikes.android.ble.ui.SelectOnMapActivity$1 */
    class C16941 extends AsyncTask<String, Void, ArrayList<NavigationLocation>> {
        /* renamed from: a */
        final /* synthetic */ SelectOnMapActivity f7662a;

        C16941(SelectOnMapActivity selectOnMapActivity) {
            this.f7662a = selectOnMapActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m9159a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m9160a((ArrayList) obj);
        }

        /* renamed from: a */
        protected ArrayList<NavigationLocation> m9159a(String... strArr) {
            try {
                return this.f7662a.f7669j.m8899d(strArr[0], strArr[1]);
            } catch (Exception e) {
                SelectOnMapActivity.f7663d.error("getGeocodeInfo error e: " + e);
                return null;
            }
        }

        /* renamed from: a */
        protected void m9160a(ArrayList<NavigationLocation> arrayList) {
            if (arrayList != null) {
                this.f7662a.f7665f.clear();
                this.f7662a.f7665f.addAll(arrayList);
                this.f7662a.f7664e.m9310a(this.f7662a.f7665f);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* renamed from: a */
    protected void mo3201a(Bundle bundle) {
        super.mo3201a(bundle);
        this.f7672m = getIntent().getIntExtra("search_event_id", 6);
        this.f7673n = getIntent().getIntExtra("way_point_position", -1);
        m9168d();
        this.a.setOnTouchListener(this);
        this.f7667h = new ImageView(this);
        this.f7667h.setImageResource(C1373R.drawable.ic_location_red);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.f7667h.setLayoutParams(layoutParams);
        this.a.addView(this.f7667h);
        m9166b();
        this.f7669j = new C1651c((Activity) this);
        this.f7666g = new GestureDetector(this, this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && this.f7668i) {
            this.f7668i = false;
            m9169e();
        }
        return this.f7666g.onTouchEvent(motionEvent);
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.f7668i = true;
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    /* renamed from: a */
    public void mo3214a(NavigationLocation navigationLocation) {
        Intent intent = new Intent();
        intent.putExtra("mapboxlocation", navigationLocation);
        intent.putExtra("way_point_position", this.f7673n);
        c(this.f7672m, intent);
        finish();
    }

    /* renamed from: a */
    public void mo3200a(double d, double d2) {
    }

    /* renamed from: b */
    public void mo3204b(double d, double d2) {
        m9169e();
    }

    /* renamed from: b */
    private void m9166b() {
        this.a.setOnMapStatusChangeListener(this);
    }

    /* renamed from: d */
    private void m9168d() {
        ((ViewStub) findViewById(C1373R.id.viewStub_select_location)).inflate();
        RecyclerView recyclerView = (RecyclerView) findViewById(C1373R.id.recyclerView_bottom_location);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.f7665f = new ArrayList();
        int i = C1373R.string.str_navigation_select_as_start;
        if (this.f7672m == 7) {
            i = C1373R.string.str_navigation_select_as_destination;
        } else if (this.f7672m == 8) {
            i = C1373R.string.str_navigation_select_as_transit_point;
        }
        this.f7664e = new C1744a(i);
        recyclerView.setAdapter(this.f7664e);
        this.f7664e.m9309a((C1695b) this);
    }

    /* renamed from: e */
    private void m9169e() {
        LatLng b;
        boolean a = C1849a.m9641a();
        Point point = new Point(this.f7667h.getLeft() + (this.f7667h.getWidth() / 2), this.f7667h.getBottom());
        if (a) {
            com.baidu.mapapi.model.LatLng latLng = (com.baidu.mapapi.model.LatLng) this.a.m11653a(point);
            if (latLng != null) {
                b = C2558g.m12845b(latLng);
            }
            b = null;
        } else {
            com.google.android.gms.maps.model.LatLng latLng2 = (com.google.android.gms.maps.model.LatLng) this.a.m11653a(point);
            if (latLng2 != null) {
                b = C2558g.m12842a(latLng2);
            }
            b = null;
        }
        m9164a(b);
    }

    /* renamed from: a */
    private void m9164a(LatLng latLng) {
        if (latLng != null) {
            if (this.f7670k != null) {
                this.f7670k.cancel(true);
            }
            this.f7670k = new C16941(this);
            if (C1849a.m9641a()) {
                getAsyncTaskQueue().m13740a(this.f7670k, latLng.getLatitude() + "," + latLng.getLongitude(), "bd");
                return;
            }
            com.google.android.gms.maps.model.LatLng a = C2558g.m12841a(latLng.getLatitude(), latLng.getLongitude());
            getAsyncTaskQueue().m13740a(this.f7670k, a.latitude + "," + a.longitude, "google");
        }
    }
}
