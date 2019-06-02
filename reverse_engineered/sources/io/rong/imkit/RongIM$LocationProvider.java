package io.rong.imkit;

import android.content.Context;
import io.rong.message.LocationMessage;

public interface RongIM$LocationProvider {

    /* renamed from: io.rong.imkit.RongIM$LocationProvider$LocationCallback */
    public interface LocationCallback {
        void onFailure(String str);

        void onSuccess(LocationMessage locationMessage);
    }

    void onStartLocation(Context context, LocationCallback locationCallback);
}
