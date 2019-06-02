package com.mapbox.mapboxsdk.maps.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.mapbox.mapboxsdk.R$drawable;
import com.mapbox.mapboxsdk.R$string;
import com.mapbox.mapboxsdk.maps.MapboxMap;

public final class CompassView extends ImageView implements Runnable {
    private static final long TIME_FADE_ANIMATION = 500;
    private static final long TIME_MAP_NORTH_ANIMATION = 150;
    private static final long TIME_WAIT_IDLE = 500;
    private double direction = 0.0d;
    private ViewPropertyAnimatorCompat fadeAnimator;
    private boolean fadeCompassViewFacingNorth = true;

    public CompassView(Context context) {
        super(context);
        initialize(context);
    }

    public CompassView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context);
    }

    public CompassView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context);
    }

    private void initialize(Context context) {
        setImageDrawable(ContextCompat.getDrawable(getContext(), R$drawable.compass));
        setContentDescription(getResources().getString(R$string.compassContentDescription));
        setEnabled(false);
        float f = context.getResources().getDisplayMetrics().density;
        setLayoutParams(new LayoutParams((int) (48.0f * f), (int) (f * 48.0f)));
    }

    public void setMapboxMap(@NonNull MapboxMap mapboxMap) {
        setOnClickListener(new CompassView$CompassClickListener(mapboxMap, this));
    }

    private void resetAnimation() {
        if (this.fadeAnimator != null) {
            this.fadeAnimator.cancel();
        }
        this.fadeAnimator = null;
    }

    public boolean isHidden() {
        return this.fadeCompassViewFacingNorth && isFacingNorth();
    }

    public boolean isFacingNorth() {
        return this.direction >= 359.0d || this.direction <= 1.0d;
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!z || isHidden()) {
            resetAnimation();
            setAlpha(0.0f);
            setVisibility(4);
            return;
        }
        resetAnimation();
        setAlpha(1.0f);
        setVisibility(0);
    }

    public void update(double d) {
        this.direction = d;
        if (!isEnabled()) {
            return;
        }
        if (!isHidden()) {
            resetAnimation();
            setAlpha(1.0f);
            setVisibility(0);
            setRotation((float) d);
        } else if (getVisibility() != 4 && this.fadeAnimator == null) {
            postDelayed(this, 500);
        }
    }

    public void fadeCompassViewFacingNorth(boolean z) {
        this.fadeCompassViewFacingNorth = z;
    }

    public void run() {
        if (isFacingNorth() && this.fadeCompassViewFacingNorth) {
            resetAnimation();
            this.fadeAnimator = ViewCompat.animate(this).alpha(0.0f).setDuration(500).withLayer();
            this.fadeAnimator.setListener(new CompassView$1(this));
        }
    }
}
