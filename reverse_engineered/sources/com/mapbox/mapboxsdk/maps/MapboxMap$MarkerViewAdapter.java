package com.mapbox.mapboxsdk.maps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools.SimplePool;
import android.view.View;
import android.view.ViewGroup;
import com.mapbox.mapboxsdk.annotations.MarkerView;
import java.lang.reflect.ParameterizedType;

public abstract class MapboxMap$MarkerViewAdapter<U extends MarkerView> {
    private Context context;
    private final Class<U> persistentClass = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    private final SimplePool<View> viewReusePool = new SimplePool(10000);

    @Nullable
    public abstract View getView(@NonNull U u, @Nullable View view, @NonNull ViewGroup viewGroup);

    public MapboxMap$MarkerViewAdapter(Context context) {
        this.context = context;
    }

    public boolean prepareViewForReuse(@NonNull MarkerView markerView, @NonNull View view) {
        return true;
    }

    public boolean onSelect(@NonNull U u, @NonNull View view, boolean z) {
        return true;
    }

    public void onDeselect(@NonNull U u, @NonNull View view) {
    }

    public final Class<U> getMarkerClass() {
        return this.persistentClass;
    }

    public final SimplePool<View> getViewReusePool() {
        return this.viewReusePool;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void releaseView(View view) {
        view.setVisibility(8);
        this.viewReusePool.release(view);
    }
}
