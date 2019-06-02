package com.mapbox.mapboxsdk.telemetry;

import java.util.TimerTask;

class MapboxEventManager$FlushEventsTimerTask extends TimerTask {
    final /* synthetic */ MapboxEventManager this$0;

    private MapboxEventManager$FlushEventsTimerTask(MapboxEventManager mapboxEventManager) {
        this.this$0 = mapboxEventManager;
    }

    public void run() {
        new MapboxEventManager$FlushTheEventsTask(this.this$0).execute(new Void[0]);
    }
}
