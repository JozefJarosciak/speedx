package com.mapbox.mapboxsdk.annotations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.mapbox.mapboxsdk.R$drawable;
import com.mapbox.mapboxsdk.exceptions.TooManyIconsException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class IconFactory {
    private static final String ICON_ID_PREFIX = "com.mapbox.icons.icon_";
    public static final Bitmap ICON_MARKERVIEW_BITMAP = Bitmap.createBitmap(1, 1, Config.ALPHA_8);
    public static final String ICON_MARKERVIEW_ID = "com.mapbox.icons.icon_marker_view";
    private static IconFactory sInstance;
    private Context mContext;
    private Icon mDefaultMarker;
    private Icon mDefaultMarkerView;
    private int mNextId = 0;
    private Options mOptions;

    public static synchronized IconFactory getInstance(@NonNull Context context) {
        IconFactory iconFactory;
        synchronized (IconFactory.class) {
            if (sInstance == null) {
                sInstance = new IconFactory(context.getApplicationContext());
            }
            iconFactory = sInstance;
        }
        return iconFactory;
    }

    private IconFactory(@NonNull Context context) {
        this.mContext = context;
        DisplayMetrics displayMetrics = null;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (VERSION.SDK_INT >= 17) {
            displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics2);
        this.mOptions = new Options();
        this.mOptions.inScaled = true;
        this.mOptions.inDensity = 160;
        this.mOptions.inTargetDensity = displayMetrics2.densityDpi;
        if (displayMetrics != null) {
            this.mOptions.inScreenDensity = displayMetrics.densityDpi;
        }
    }

    public Icon fromBitmap(@NonNull Bitmap bitmap) {
        if (this.mNextId < 0) {
            throw new TooManyIconsException();
        }
        StringBuilder append = new StringBuilder().append(ICON_ID_PREFIX);
        int i = this.mNextId + 1;
        this.mNextId = i;
        return new Icon(append.append(i).toString(), bitmap);
    }

    public Icon fromDrawable(@NonNull Drawable drawable) {
        return fromDrawable(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    public Icon fromDrawable(@NonNull Drawable drawable, int i, int i2) {
        if (i < 0 || i2 < 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Rect bounds = drawable.getBounds();
        drawable.setBounds(new Rect(0, 0, i, i2));
        drawable.draw(canvas);
        drawable.setBounds(bounds);
        return fromBitmap(createBitmap);
    }

    public Icon fromResource(@DrawableRes int i) {
        Bitmap bitmap;
        Drawable drawable = ContextCompat.getDrawable(this.mContext, i);
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else {
            if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
                bitmap = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        return fromBitmap(bitmap);
    }

    public Icon defaultMarker() {
        if (this.mDefaultMarker == null) {
            this.mDefaultMarker = fromResource(R$drawable.default_marker);
        }
        return this.mDefaultMarker;
    }

    public Icon defaultMarkerView() {
        if (this.mDefaultMarkerView == null) {
            this.mDefaultMarkerView = fromResource(R$drawable.default_markerview);
        }
        return this.mDefaultMarkerView;
    }

    private Icon fromInputStream(@NonNull InputStream inputStream) {
        return fromBitmap(BitmapFactory.decodeStream(inputStream, null, this.mOptions));
    }

    public Icon fromAsset(@NonNull String str) {
        try {
            return fromInputStream(this.mContext.getAssets().open(str));
        } catch (IOException e) {
            return null;
        }
    }

    public Icon fromPath(@NonNull String str) {
        return fromBitmap(BitmapFactory.decodeFile(str, this.mOptions));
    }

    public Icon fromFile(@NonNull String str) {
        try {
            return fromInputStream(this.mContext.openFileInput(str));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Icon recreate(@NonNull String str, @NonNull Bitmap bitmap) {
        return new Icon(str, bitmap);
    }
}
