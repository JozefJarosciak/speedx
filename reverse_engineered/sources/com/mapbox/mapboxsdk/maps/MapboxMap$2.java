package com.mapbox.mapboxsdk.maps;

import com.mapbox.mapboxsdk.maps.MapboxMap.CancelableCallback;

class MapboxMap$2 implements CancelableCallback {
    final /* synthetic */ MapboxMap this$0;
    final /* synthetic */ CancelableCallback val$callback;

    MapboxMap$2(MapboxMap mapboxMap, CancelableCallback cancelableCallback) {
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
        if (MapboxMap.access$200(this.this$0) != null) {
            MapboxMap.access$200(this.this$0).onCameraChange(MapboxMap.access$300(this.this$0));
        }
        if (this.val$callback != null) {
            this.val$callback.onFinish();
        }
        MapboxMap.access$100(this.this$0);
    }
}
