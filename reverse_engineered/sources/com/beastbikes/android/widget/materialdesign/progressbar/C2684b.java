package com.beastbikes.android.widget.materialdesign.progressbar;

import android.graphics.PorterDuff.Mode;

/* compiled from: DrawableCompat */
/* renamed from: com.beastbikes.android.widget.materialdesign.progressbar.b */
public class C2684b {
    /* renamed from: a */
    public static Mode m13331a(int i, Mode mode) {
        switch (i) {
            case 3:
                return Mode.SRC_OVER;
            case 5:
                return Mode.SRC_IN;
            case 9:
                return Mode.SRC_ATOP;
            case 14:
                return Mode.MULTIPLY;
            case 15:
                return Mode.SCREEN;
            case 16:
                return Mode.ADD;
            default:
                return mode;
        }
    }
}
