package io.rong.imageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import io.rong.imageloader.core.assist.ImageSize;
import io.rong.imageloader.core.assist.ViewScaleType;

public class NonViewAware implements ImageAware {
    protected final ImageSize imageSize;
    protected final String imageUri;
    protected final ViewScaleType scaleType;

    public NonViewAware(ImageSize imageSize, ViewScaleType viewScaleType) {
        this(null, imageSize, viewScaleType);
    }

    public NonViewAware(String str, ImageSize imageSize, ViewScaleType viewScaleType) {
        if (imageSize == null) {
            throw new IllegalArgumentException("imageSize must not be null");
        } else if (viewScaleType == null) {
            throw new IllegalArgumentException("scaleType must not be null");
        } else {
            this.imageUri = str;
            this.imageSize = imageSize;
            this.scaleType = viewScaleType;
        }
    }

    public int getWidth() {
        return this.imageSize.getWidth();
    }

    public int getHeight() {
        return this.imageSize.getHeight();
    }

    public ViewScaleType getScaleType() {
        return this.scaleType;
    }

    public View getWrappedView() {
        return null;
    }

    public boolean isCollected() {
        return false;
    }

    public int getId() {
        return TextUtils.isEmpty(this.imageUri) ? super.hashCode() : this.imageUri.hashCode();
    }

    public boolean setImageDrawable(Drawable drawable) {
        return true;
    }

    public boolean setImageBitmap(Bitmap bitmap) {
        return true;
    }
}
