package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;
import com.google.android.gms.common.util.zzs;

public final class zzrf extends Drawable implements Callback {
    private int mFrom;
    private int wE;
    private int wF;
    private int wG;
    private int wH;
    private int wI;
    private boolean wJ;
    private zzb wK;
    private Drawable wL;
    private Drawable wM;
    private boolean wN;
    private boolean wO;
    private boolean wP;
    private int wQ;
    private boolean wy;
    private long zzczk;

    private static final class zza extends Drawable {
        private static final zza wR = new zza();
        private static final zza wS = new zza();

        private static final class zza extends ConstantState {
            private zza() {
            }

            public int getChangingConfigurations() {
                return 0;
            }

            public Drawable newDrawable() {
                return zza.wR;
            }
        }

        private zza() {
        }

        public void draw(Canvas canvas) {
        }

        public ConstantState getConstantState() {
            return wS;
        }

        public int getOpacity() {
            return -2;
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }
    }

    static final class zzb extends ConstantState {
        int wT;
        int wU;

        zzb(zzb zzb) {
            if (zzb != null) {
                this.wT = zzb.wT;
                this.wU = zzb.wU;
            }
        }

        public int getChangingConfigurations() {
            return this.wT;
        }

        public Drawable newDrawable() {
            return new zzrf(this);
        }
    }

    public zzrf(Drawable drawable, Drawable drawable2) {
        this(null);
        if (drawable == null) {
            drawable = zza.wR;
        }
        this.wL = drawable;
        drawable.setCallback(this);
        zzb zzb = this.wK;
        zzb.wU |= drawable.getChangingConfigurations();
        if (drawable2 == null) {
            drawable2 = zza.wR;
        }
        this.wM = drawable2;
        drawable2.setCallback(this);
        zzb = this.wK;
        zzb.wU |= drawable2.getChangingConfigurations();
    }

    zzrf(zzb zzb) {
        this.wE = 0;
        this.wG = 255;
        this.wI = 0;
        this.wy = true;
        this.wK = new zzb(zzb);
    }

    public boolean canConstantState() {
        if (!this.wN) {
            boolean z = (this.wL.getConstantState() == null || this.wM.getConstantState() == null) ? false : true;
            this.wO = z;
            this.wN = true;
        }
        return this.wO;
    }

    public void draw(Canvas canvas) {
        int i = 1;
        int i2 = 0;
        switch (this.wE) {
            case 1:
                this.zzczk = SystemClock.uptimeMillis();
                this.wE = 2;
                break;
            case 2:
                if (this.zzczk >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.zzczk)) / ((float) this.wH);
                    if (uptimeMillis < 1.0f) {
                        i = 0;
                    }
                    if (i != 0) {
                        this.wE = 0;
                    }
                    this.wI = (int) ((Math.min(uptimeMillis, 1.0f) * ((float) (this.wF + 0))) + 0.0f);
                    break;
                }
                break;
        }
        i2 = i;
        i = this.wI;
        boolean z = this.wy;
        Drawable drawable = this.wL;
        Drawable drawable2 = this.wM;
        if (i2 != 0) {
            if (!z || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.wG) {
                drawable2.setAlpha(this.wG);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z) {
            drawable.setAlpha(this.wG - i);
        }
        drawable.draw(canvas);
        if (z) {
            drawable.setAlpha(this.wG);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.wG);
        }
        invalidateSelf();
    }

    public int getChangingConfigurations() {
        return (super.getChangingConfigurations() | this.wK.wT) | this.wK.wU;
    }

    public ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.wK.wT = getChangingConfigurations();
        return this.wK;
    }

    public int getIntrinsicHeight() {
        return Math.max(this.wL.getIntrinsicHeight(), this.wM.getIntrinsicHeight());
    }

    public int getIntrinsicWidth() {
        return Math.max(this.wL.getIntrinsicWidth(), this.wM.getIntrinsicWidth());
    }

    public int getOpacity() {
        if (!this.wP) {
            this.wQ = Drawable.resolveOpacity(this.wL.getOpacity(), this.wM.getOpacity());
            this.wP = true;
        }
        return this.wQ;
    }

    @TargetApi(11)
    public void invalidateDrawable(Drawable drawable) {
        if (zzs.zzavj()) {
            Callback callback = getCallback();
            if (callback != null) {
                callback.invalidateDrawable(this);
            }
        }
    }

    public Drawable mutate() {
        if (!this.wJ && super.mutate() == this) {
            if (canConstantState()) {
                this.wL.mutate();
                this.wM.mutate();
                this.wJ = true;
            } else {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
        }
        return this;
    }

    protected void onBoundsChange(Rect rect) {
        this.wL.setBounds(rect);
        this.wM.setBounds(rect);
    }

    @TargetApi(11)
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (zzs.zzavj()) {
            Callback callback = getCallback();
            if (callback != null) {
                callback.scheduleDrawable(this, runnable, j);
            }
        }
    }

    public void setAlpha(int i) {
        if (this.wI == this.wG) {
            this.wI = i;
        }
        this.wG = i;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.wL.setColorFilter(colorFilter);
        this.wM.setColorFilter(colorFilter);
    }

    public void startTransition(int i) {
        this.mFrom = 0;
        this.wF = this.wG;
        this.wI = 0;
        this.wH = i;
        this.wE = 1;
        invalidateSelf();
    }

    @TargetApi(11)
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (zzs.zzavj()) {
            Callback callback = getCallback();
            if (callback != null) {
                callback.unscheduleDrawable(this, runnable);
            }
        }
    }

    public Drawable zzarm() {
        return this.wM;
    }
}
