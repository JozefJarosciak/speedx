package com.beastbikes.framework.ui.android.lib.pulltorefresh;

public interface OnFlingListener {
    boolean onFlingToLeft(float f, float f2, float f3, float f4);

    boolean onFlingToRight(float f, float f2, float f3, float f4);

    boolean onTouchedAfterFlinged(float f, float f2);
}
