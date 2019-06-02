package com.mapbox.mapboxsdk.maps;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.R$drawable;
import com.mapbox.mapboxsdk.constants.MapboxConstants;
import com.mapbox.mapboxsdk.exceptions.InvalidAccessTokenException;

public class SupportMapFragment extends Fragment {
    private MapView map;
    private OnMapReadyCallback onMapReadyCallback;

    public static SupportMapFragment newInstance() {
        return new SupportMapFragment();
    }

    public static SupportMapFragment newInstance(@Nullable MapboxMapOptions mapboxMapOptions) {
        SupportMapFragment supportMapFragment = new SupportMapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MapboxConstants.FRAG_ARG_MAPBOXMAPOPTIONS, mapboxMapOptions);
        supportMapFragment.setArguments(bundle);
        return supportMapFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        MapboxMapOptions accessToken;
        Drawable myLocationForegroundDrawable;
        Drawable myLocationForegroundBearingDrawable;
        View mapView;
        super.onCreateView(layoutInflater, viewGroup, bundle);
        Context context = layoutInflater.getContext();
        MapboxMapOptions mapboxMapOptions = null;
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(MapboxConstants.FRAG_ARG_MAPBOXMAPOPTIONS)) {
            mapboxMapOptions = (MapboxMapOptions) arguments.getParcelable(MapboxConstants.FRAG_ARG_MAPBOXMAPOPTIONS);
        }
        if (mapboxMapOptions == null || mapboxMapOptions.getAccessToken() == null) {
            String accessToken2;
            if (MapboxAccountManager.getInstance() != null) {
                accessToken2 = MapboxAccountManager.getInstance().getAccessToken();
            } else {
                accessToken2 = getToken(layoutInflater.getContext());
            }
            if (TextUtils.isEmpty(accessToken2)) {
                throw new InvalidAccessTokenException();
            } else if (mapboxMapOptions == null) {
                accessToken = new MapboxMapOptions().accessToken(accessToken2);
                myLocationForegroundDrawable = accessToken.getMyLocationForegroundDrawable();
                myLocationForegroundBearingDrawable = accessToken.getMyLocationForegroundBearingDrawable();
                if (myLocationForegroundDrawable == null || myLocationForegroundBearingDrawable == null) {
                    if (myLocationForegroundDrawable == null) {
                        myLocationForegroundDrawable = ContextCompat.getDrawable(context, R$drawable.ic_mylocationview_normal);
                    }
                    if (myLocationForegroundBearingDrawable == null) {
                        myLocationForegroundBearingDrawable = ContextCompat.getDrawable(context, R$drawable.ic_mylocationview_bearing);
                    }
                    accessToken.myLocationForegroundDrawables(myLocationForegroundDrawable, myLocationForegroundBearingDrawable);
                }
                if (accessToken.getMyLocationBackgroundDrawable() == null) {
                    accessToken.myLocationBackgroundDrawable(ContextCompat.getDrawable(context, R$drawable.ic_mylocationview_background));
                }
                mapView = new MapView(layoutInflater.getContext(), accessToken);
                this.map = mapView;
                return mapView;
            } else {
                mapboxMapOptions.accessToken(accessToken2);
            }
        }
        accessToken = mapboxMapOptions;
        myLocationForegroundDrawable = accessToken.getMyLocationForegroundDrawable();
        myLocationForegroundBearingDrawable = accessToken.getMyLocationForegroundBearingDrawable();
        if (myLocationForegroundDrawable == null) {
            myLocationForegroundDrawable = ContextCompat.getDrawable(context, R$drawable.ic_mylocationview_normal);
        }
        if (myLocationForegroundBearingDrawable == null) {
            myLocationForegroundBearingDrawable = ContextCompat.getDrawable(context, R$drawable.ic_mylocationview_bearing);
        }
        accessToken.myLocationForegroundDrawables(myLocationForegroundDrawable, myLocationForegroundBearingDrawable);
        if (accessToken.getMyLocationBackgroundDrawable() == null) {
            accessToken.myLocationBackgroundDrawable(ContextCompat.getDrawable(context, R$drawable.ic_mylocationview_background));
        }
        mapView = new MapView(layoutInflater.getContext(), accessToken);
        this.map = mapView;
        return mapView;
    }

    @Deprecated
    private String getToken(@NonNull Context context) {
        try {
            String string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(MapboxConstants.KEY_META_DATA_MANIFEST);
            if (string != null && !string.isEmpty()) {
                return string;
            }
            throw new IllegalArgumentException();
        } catch (Exception e) {
            int identifier = context.getResources().getIdentifier("mapbox_access_token", "string", context.getPackageName());
            return identifier != 0 ? context.getString(identifier) : null;
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.map.onCreate(bundle);
    }

    public void onStart() {
        super.onStart();
        this.map.getMapAsync(this.onMapReadyCallback);
    }

    public void onResume() {
        super.onResume();
        this.map.onResume();
    }

    public void onPause() {
        super.onPause();
        this.map.onPause();
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.map.onSaveInstanceState(bundle);
    }

    public void onStop() {
        super.onStop();
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.map.onLowMemory();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.map.onDestroy();
    }

    public void getMapAsync(@NonNull OnMapReadyCallback onMapReadyCallback) {
        this.onMapReadyCallback = onMapReadyCallback;
    }
}
