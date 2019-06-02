package io.rong.photoview.gestures;

import android.content.Context;
import android.os.Build.VERSION;

public final class VersionedGestureDetector {
    public static GestureDetector newInstance(Context context, OnGestureListener onGestureListener) {
        GestureDetector cupcakeGestureDetector;
        int i = VERSION.SDK_INT;
        if (i < 5) {
            cupcakeGestureDetector = new CupcakeGestureDetector(context);
        } else if (i < 8) {
            cupcakeGestureDetector = new EclairGestureDetector(context);
        } else {
            cupcakeGestureDetector = new FroyoGestureDetector(context);
        }
        cupcakeGestureDetector.setOnGestureListener(onGestureListener);
        return cupcakeGestureDetector;
    }
}
