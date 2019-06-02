package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.net.Uri;
import android.widget.ImageView;

public final class zzrg extends ImageView {
    private Uri wV;
    private int wW;

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public int zzaro() {
        return this.wW;
    }

    public void zzfw(int i) {
        this.wW = i;
    }

    public void zzp(Uri uri) {
        this.wV = uri;
    }
}
