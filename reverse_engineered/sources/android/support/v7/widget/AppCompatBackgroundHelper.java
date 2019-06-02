package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0246R;
import android.util.AttributeSet;
import android.view.View;

class AppCompatBackgroundHelper {
    private int mBackgroundResId = -1;
    private BackgroundTintInfo mBackgroundTint;
    private final AppCompatDrawableManager mDrawableManager;
    private BackgroundTintInfo mInternalBackgroundTint;
    private BackgroundTintInfo mTmpInfo;
    private final View mView;

    private static class BackgroundTintInfo extends TintInfo {
        public ColorStateList mOriginalTintList;

        BackgroundTintInfo() {
        }

        void clear() {
            super.clear();
            this.mOriginalTintList = null;
        }
    }

    AppCompatBackgroundHelper(View view) {
        this.mView = view;
        this.mDrawableManager = AppCompatDrawableManager.get();
    }

    void loadFromAttributes(AttributeSet attributeSet, int i) {
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet, C0246R.styleable.ViewBackgroundHelper, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(C0246R.styleable.ViewBackgroundHelper_android_background)) {
                this.mBackgroundResId = obtainStyledAttributes.getResourceId(C0246R.styleable.ViewBackgroundHelper_android_background, -1);
                ColorStateList tintList = this.mDrawableManager.getTintList(this.mView.getContext(), this.mBackgroundResId);
                if (tintList != null) {
                    setInternalBackgroundTint(tintList);
                }
            }
            if (obtainStyledAttributes.hasValue(C0246R.styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.setBackgroundTintList(this.mView, obtainStyledAttributes.getColorStateList(C0246R.styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (obtainStyledAttributes.hasValue(C0246R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.setBackgroundTintMode(this.mView, DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(C0246R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    void onSetBackgroundResource(int i) {
        this.mBackgroundResId = i;
        setInternalBackgroundTint(this.mDrawableManager != null ? this.mDrawableManager.getTintList(this.mView.getContext(), i) : null);
        if (updateBackgroundTint()) {
            applySupportBackgroundTint();
        }
    }

    void onSetBackgroundDrawable(Drawable drawable) {
        this.mBackgroundResId = -1;
        setInternalBackgroundTint(null);
        if (updateBackgroundTint()) {
            applySupportBackgroundTint();
        }
    }

    void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new BackgroundTintInfo();
        }
        this.mBackgroundTint.mOriginalTintList = colorStateList;
        this.mBackgroundTint.mTintList = null;
        this.mBackgroundTint.mHasTintList = true;
        if (updateBackgroundTint()) {
            applySupportBackgroundTint();
        }
    }

    private boolean updateBackgroundTint() {
        if (this.mBackgroundTint != null && this.mBackgroundTint.mHasTintList) {
            if (this.mBackgroundResId >= 0) {
                ColorStateList tintList = this.mDrawableManager.getTintList(this.mView.getContext(), this.mBackgroundResId, this.mBackgroundTint.mOriginalTintList);
                if (tintList != null) {
                    this.mBackgroundTint.mTintList = tintList;
                    return true;
                }
            }
            if (this.mBackgroundTint.mTintList != this.mBackgroundTint.mOriginalTintList) {
                this.mBackgroundTint.mTintList = this.mBackgroundTint.mOriginalTintList;
                return true;
            }
        }
        return false;
    }

    ColorStateList getSupportBackgroundTintList() {
        return this.mBackgroundTint != null ? this.mBackgroundTint.mTintList : null;
    }

    void setSupportBackgroundTintMode(Mode mode) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new BackgroundTintInfo();
        }
        this.mBackgroundTint.mTintMode = mode;
        this.mBackgroundTint.mHasTintMode = true;
        applySupportBackgroundTint();
    }

    Mode getSupportBackgroundTintMode() {
        return this.mBackgroundTint != null ? this.mBackgroundTint.mTintMode : null;
    }

    void applySupportBackgroundTint() {
        Drawable background = this.mView.getBackground();
        if (background == null) {
            return;
        }
        if (VERSION.SDK_INT != 21 || !applyFrameworkTintUsingColorFilter(background)) {
            if (this.mBackgroundTint != null) {
                AppCompatDrawableManager.tintDrawable(background, this.mBackgroundTint, this.mView.getDrawableState());
            } else if (this.mInternalBackgroundTint != null) {
                AppCompatDrawableManager.tintDrawable(background, this.mInternalBackgroundTint, this.mView.getDrawableState());
            }
        }
    }

    void setInternalBackgroundTint(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.mInternalBackgroundTint == null) {
                this.mInternalBackgroundTint = new BackgroundTintInfo();
            }
            this.mInternalBackgroundTint.mTintList = colorStateList;
            this.mInternalBackgroundTint.mHasTintList = true;
        } else {
            this.mInternalBackgroundTint = null;
        }
        applySupportBackgroundTint();
    }

    private boolean applyFrameworkTintUsingColorFilter(@NonNull Drawable drawable) {
        if (this.mTmpInfo == null) {
            this.mTmpInfo = new BackgroundTintInfo();
        }
        TintInfo tintInfo = this.mTmpInfo;
        tintInfo.clear();
        ColorStateList backgroundTintList = ViewCompat.getBackgroundTintList(this.mView);
        if (backgroundTintList != null) {
            tintInfo.mHasTintList = true;
            tintInfo.mTintList = backgroundTintList;
        }
        Mode backgroundTintMode = ViewCompat.getBackgroundTintMode(this.mView);
        if (backgroundTintMode != null) {
            tintInfo.mHasTintMode = true;
            tintInfo.mTintMode = backgroundTintMode;
        }
        if (!tintInfo.mHasTintList && !tintInfo.mHasTintMode) {
            return false;
        }
        AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
        return true;
    }
}
