package com.mapbox.mapboxsdk.annotations;

import android.graphics.Bitmap;

public class Icon {
    private Bitmap mBitmap;
    private String mId;

    Icon(String str, Bitmap bitmap) {
        this.mId = str;
        this.mBitmap = bitmap;
    }

    public String getId() {
        return this.mId;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Icon icon = (Icon) obj;
        if (this.mBitmap.equals(icon.mBitmap)) {
            return this.mId.equals(icon.mId);
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        if (this.mBitmap != null) {
            i = this.mBitmap.hashCode();
        }
        if (this.mId != null) {
            return (i * 31) + this.mId.hashCode();
        }
        return i;
    }
}
