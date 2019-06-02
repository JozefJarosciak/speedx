package com.mapbox.mapboxsdk.maps;

import com.mapbox.mapboxsdk.maps.MapboxMap.CancelableCallback;

class MapboxMap$1 implements CancelableCallback {
    final /* synthetic */ MapboxMap this$0;
    final /* synthetic */ CancelableCallback val$callback;

    MapboxMap$1(MapboxMap mapboxMap, CancelableCallback cancelableCallback) {
        this.this$0 = mapboxMap;
        this.val$callback = cancelableCallback;
    }

    public void onCancel() {
        if (this.val$callback != null) {
            this.val$callback.onCancel();
        }
        MapboxMap.access$100(this.this$0);
    }

    public void onFinish() {
        if (this.val$callback != null) {
            this.val$callback.onFinish();
        }
        MapboxMap.access$100(this.this$0);
    }
}
