package com.mapbox.mapboxsdk.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import com.mapbox.mapboxsdk.R$drawable;
import com.mapbox.mapboxsdk.R$styleable;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraPosition.Builder;
import com.mapbox.mapboxsdk.utils.ColorUtils;
import java.util.Arrays;

public class MapboxMapOptions implements Parcelable {
    public static final Creator<MapboxMapOptions> CREATOR = new MapboxMapOptions$1();
    private static final float DIMENSION_SEVENTY_SIX_DP = 76.0f;
    private static final float DIMENSION_SEVEN_DP = 7.0f;
    private static final float DIMENSION_SIXTEEN_DP = 16.0f;
    private static final float DIMENSION_TEN_DP = 10.0f;
    @Deprecated
    private String accessToken;
    private String apiBaseUrl;
    private boolean attributionEnabled;
    private int attributionGravity;
    private int[] attributionMargins;
    @ColorInt
    private int attributionTintColor;
    private CameraPosition cameraPosition;
    private boolean compassEnabled;
    private int compassGravity;
    private int[] compassMargins;
    private boolean debugActive;
    private boolean fadeCompassFacingNorth;
    private boolean logoEnabled;
    private int logoGravity;
    private int[] logoMargins;
    private float maxZoom;
    private float minZoom;
    private int myLocationAccuracyAlpha;
    private int myLocationAccuracyTintColor;
    private Drawable myLocationBackgroundDrawable;
    private int[] myLocationBackgroundPadding;
    private int myLocationBackgroundTintColor;
    private boolean myLocationEnabled;
    private Drawable myLocationForegroundBearingDrawable;
    private Drawable myLocationForegroundDrawable;
    private int myLocationForegroundTintColor;
    private boolean rotateGesturesEnabled;
    private boolean scrollGesturesEnabled;
    private String style;
    @Deprecated
    private boolean textureMode;
    private boolean tiltGesturesEnabled;
    private boolean zoomControlsEnabled;
    private boolean zoomGesturesEnabled;

    public MapboxMapOptions() {
        this.compassEnabled = true;
        this.fadeCompassFacingNorth = true;
        this.compassGravity = 8388661;
        this.logoEnabled = true;
        this.logoGravity = 8388691;
        this.attributionTintColor = -1;
        this.attributionEnabled = true;
        this.attributionGravity = 80;
        this.minZoom = 0.0f;
        this.maxZoom = 21.0f;
        this.rotateGesturesEnabled = true;
        this.scrollGesturesEnabled = true;
        this.tiltGesturesEnabled = true;
        this.zoomGesturesEnabled = true;
        this.zoomControlsEnabled = false;
    }

    private MapboxMapOptions(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.compassEnabled = true;
        this.fadeCompassFacingNorth = true;
        this.compassGravity = 8388661;
        this.logoEnabled = true;
        this.logoGravity = 8388691;
        this.attributionTintColor = -1;
        this.attributionEnabled = true;
        this.attributionGravity = 80;
        this.minZoom = 0.0f;
        this.maxZoom = 21.0f;
        this.rotateGesturesEnabled = true;
        this.scrollGesturesEnabled = true;
        this.tiltGesturesEnabled = true;
        this.zoomGesturesEnabled = true;
        this.zoomControlsEnabled = false;
        this.cameraPosition = (CameraPosition) parcel.readParcelable(CameraPosition.class.getClassLoader());
        this.debugActive = parcel.readByte() != (byte) 0;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.compassEnabled = z;
        this.compassGravity = parcel.readInt();
        this.compassMargins = parcel.createIntArray();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.fadeCompassFacingNorth = z;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.logoEnabled = z;
        this.logoGravity = parcel.readInt();
        this.logoMargins = parcel.createIntArray();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.attributionEnabled = z;
        this.attributionGravity = parcel.readInt();
        this.attributionMargins = parcel.createIntArray();
        this.attributionTintColor = parcel.readInt();
        this.minZoom = parcel.readFloat();
        this.maxZoom = parcel.readFloat();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.rotateGesturesEnabled = z;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.scrollGesturesEnabled = z;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.tiltGesturesEnabled = z;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.zoomControlsEnabled = z;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.zoomGesturesEnabled = z;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.myLocationEnabled = z;
        Bitmap bitmap = (Bitmap) parcel.readParcelable(getClass().getClassLoader());
        if (bitmap != null) {
            this.myLocationForegroundDrawable = new BitmapDrawable(bitmap);
        }
        bitmap = (Bitmap) parcel.readParcelable(getClass().getClassLoader());
        if (bitmap != null) {
            this.myLocationForegroundBearingDrawable = new BitmapDrawable(bitmap);
        }
        bitmap = (Bitmap) parcel.readParcelable(getClass().getClassLoader());
        if (bitmap != null) {
            this.myLocationBackgroundDrawable = new BitmapDrawable(bitmap);
        }
        this.myLocationForegroundTintColor = parcel.readInt();
        this.myLocationBackgroundTintColor = parcel.readInt();
        this.myLocationBackgroundPadding = parcel.createIntArray();
        this.myLocationAccuracyAlpha = parcel.readInt();
        this.myLocationAccuracyTintColor = parcel.readInt();
        this.style = parcel.readString();
        this.accessToken = parcel.readString();
        this.apiBaseUrl = parcel.readString();
        if (parcel.readByte() == (byte) 0) {
            z2 = false;
        }
        this.textureMode = z2;
    }

    public static Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static MapboxMapOptions createFromAttributes(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        MapboxMapOptions mapboxMapOptions = new MapboxMapOptions();
        float f = context.getResources().getDisplayMetrics().density;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MapView, 0, 0);
        try {
            Drawable drawable;
            Drawable drawable2;
            mapboxMapOptions.debugActive(obtainStyledAttributes.getBoolean(R$styleable.MapView_debug_active, false));
            mapboxMapOptions.camera(new Builder(obtainStyledAttributes).build());
            mapboxMapOptions.accessToken(obtainStyledAttributes.getString(R$styleable.MapView_access_token));
            mapboxMapOptions.styleUrl(obtainStyledAttributes.getString(R$styleable.MapView_style_url));
            mapboxMapOptions.apiBaseUrl(obtainStyledAttributes.getString(R$styleable.MapView_api_base_url));
            mapboxMapOptions.zoomGesturesEnabled(obtainStyledAttributes.getBoolean(R$styleable.MapView_zoom_enabled, true));
            mapboxMapOptions.scrollGesturesEnabled(obtainStyledAttributes.getBoolean(R$styleable.MapView_scroll_enabled, true));
            mapboxMapOptions.rotateGesturesEnabled(obtainStyledAttributes.getBoolean(R$styleable.MapView_rotate_enabled, true));
            mapboxMapOptions.tiltGesturesEnabled(obtainStyledAttributes.getBoolean(R$styleable.MapView_tilt_enabled, true));
            mapboxMapOptions.zoomControlsEnabled(obtainStyledAttributes.getBoolean(R$styleable.MapView_zoom_controls_enabled, false));
            mapboxMapOptions.maxZoom(obtainStyledAttributes.getFloat(R$styleable.MapView_zoom_max, 21.0f));
            mapboxMapOptions.minZoom(obtainStyledAttributes.getFloat(R$styleable.MapView_zoom_min, 0.0f));
            mapboxMapOptions.compassEnabled(obtainStyledAttributes.getBoolean(R$styleable.MapView_compass_enabled, true));
            mapboxMapOptions.compassGravity(obtainStyledAttributes.getInt(R$styleable.MapView_compass_gravity, 8388661));
            mapboxMapOptions.compassMargins(new int[]{(int) (obtainStyledAttributes.getDimension(R$styleable.MapView_compass_margin_left, DIMENSION_TEN_DP) * f), (int) obtainStyledAttributes.getDimension(R$styleable.MapView_compass_margin_top, DIMENSION_TEN_DP * f), (int) obtainStyledAttributes.getDimension(R$styleable.MapView_compass_margin_right, DIMENSION_TEN_DP * f), (int) obtainStyledAttributes.getDimension(R$styleable.MapView_compass_margin_bottom, DIMENSION_TEN_DP * f)});
            mapboxMapOptions.compassFadesWhenFacingNorth(obtainStyledAttributes.getBoolean(R$styleable.MapView_compass_fade_facing_north, true));
            mapboxMapOptions.logoEnabled(obtainStyledAttributes.getBoolean(R$styleable.MapView_logo_enabled, true));
            mapboxMapOptions.logoGravity(obtainStyledAttributes.getInt(R$styleable.MapView_logo_gravity, 8388691));
            mapboxMapOptions.logoMargins(new int[]{(int) (obtainStyledAttributes.getDimension(R$styleable.MapView_logo_margin_left, DIMENSION_SIXTEEN_DP) * f), (int) (obtainStyledAttributes.getDimension(R$styleable.MapView_logo_margin_top, DIMENSION_SIXTEEN_DP) * f), (int) (obtainStyledAttributes.getDimension(R$styleable.MapView_logo_margin_right, DIMENSION_SIXTEEN_DP) * f), (int) (obtainStyledAttributes.getDimension(R$styleable.MapView_logo_margin_bottom, DIMENSION_SIXTEEN_DP) * f)});
            mapboxMapOptions.attributionTintColor(obtainStyledAttributes.getColor(R$styleable.MapView_attribution_tint, -1));
            mapboxMapOptions.attributionEnabled(obtainStyledAttributes.getBoolean(R$styleable.MapView_attribution_enabled, true));
            mapboxMapOptions.attributionGravity(obtainStyledAttributes.getInt(R$styleable.MapView_attribution_gravity, 80));
            mapboxMapOptions.attributionMargins(new int[]{(int) (obtainStyledAttributes.getDimension(R$styleable.MapView_attribution_margin_left, DIMENSION_SEVENTY_SIX_DP) * f), (int) (obtainStyledAttributes.getDimension(R$styleable.MapView_attribution_margin_top, DIMENSION_SEVEN_DP) * f), (int) (obtainStyledAttributes.getDimension(R$styleable.MapView_attribution_margin_right, DIMENSION_SEVEN_DP) * f), (int) (obtainStyledAttributes.getDimension(R$styleable.MapView_attribution_margin_bottom, DIMENSION_SEVEN_DP) * f)});
            mapboxMapOptions.locationEnabled(obtainStyledAttributes.getBoolean(R$styleable.MapView_my_location_enabled, false));
            mapboxMapOptions.myLocationForegroundTintColor(obtainStyledAttributes.getColor(R$styleable.MapView_my_location_foreground_tint, 0));
            mapboxMapOptions.myLocationBackgroundTintColor(obtainStyledAttributes.getColor(R$styleable.MapView_my_location_background_tint, 0));
            Drawable drawable3 = obtainStyledAttributes.getDrawable(R$styleable.MapView_my_location_foreground);
            if (drawable3 == null) {
                drawable = ContextCompat.getDrawable(context, R$drawable.ic_mylocationview_normal);
            } else {
                drawable = drawable3;
            }
            drawable3 = obtainStyledAttributes.getDrawable(R$styleable.MapView_my_location_foreground_bearing);
            if (drawable3 == null) {
                drawable2 = ContextCompat.getDrawable(context, R$drawable.ic_mylocationview_bearing);
            } else {
                drawable2 = drawable3;
            }
            drawable3 = obtainStyledAttributes.getDrawable(R$styleable.MapView_my_location_background);
            if (drawable3 == null) {
                drawable3 = ContextCompat.getDrawable(context, R$drawable.ic_mylocationview_background);
            }
            mapboxMapOptions.myLocationForegroundDrawables(drawable, drawable2);
            mapboxMapOptions.myLocationBackgroundDrawable(drawable3);
            mapboxMapOptions.myLocationBackgroundPadding(new int[]{(int) (obtainStyledAttributes.getDimension(R$styleable.MapView_my_location_background_left, 0.0f) * f), (int) (obtainStyledAttributes.getDimension(R$styleable.MapView_my_location_background_top, 0.0f) * f), (int) (obtainStyledAttributes.getDimension(R$styleable.MapView_my_location_background_right, 0.0f) * f), (int) (obtainStyledAttributes.getDimension(R$styleable.MapView_my_location_background_bottom, 0.0f) * f)});
            mapboxMapOptions.myLocationAccuracyAlpha(obtainStyledAttributes.getInt(R$styleable.MapView_my_location_accuracy_alpha, 100));
            mapboxMapOptions.myLocationAccuracyTint(obtainStyledAttributes.getColor(R$styleable.MapView_my_location_accuracy_tint, ColorUtils.getPrimaryColor(context)));
            mapboxMapOptions.textureMode(obtainStyledAttributes.getBoolean(R$styleable.MapView_texture_mode, false));
            return mapboxMapOptions;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public MapboxMapOptions apiBaseUrl(String str) {
        this.apiBaseUrl = str;
        return this;
    }

    public MapboxMapOptions camera(CameraPosition cameraPosition) {
        this.cameraPosition = cameraPosition;
        return this;
    }

    @Deprecated
    public MapboxMapOptions accessToken(String str) {
        this.accessToken = str;
        return this;
    }

    public MapboxMapOptions styleUrl(String str) {
        this.style = str;
        return this;
    }

    public MapboxMapOptions debugActive(boolean z) {
        this.debugActive = z;
        return this;
    }

    public MapboxMapOptions minZoom(float f) {
        this.minZoom = f;
        return this;
    }

    public MapboxMapOptions maxZoom(float f) {
        this.maxZoom = f;
        return this;
    }

    public MapboxMapOptions compassEnabled(boolean z) {
        this.compassEnabled = z;
        return this;
    }

    public MapboxMapOptions compassGravity(int i) {
        this.compassGravity = i;
        return this;
    }

    public MapboxMapOptions compassMargins(int[] iArr) {
        this.compassMargins = iArr;
        return this;
    }

    public MapboxMapOptions compassFadesWhenFacingNorth(boolean z) {
        this.fadeCompassFacingNorth = z;
        return this;
    }

    public MapboxMapOptions logoEnabled(boolean z) {
        this.logoEnabled = z;
        return this;
    }

    public MapboxMapOptions logoGravity(int i) {
        this.logoGravity = i;
        return this;
    }

    public MapboxMapOptions logoMargins(int[] iArr) {
        this.logoMargins = iArr;
        return this;
    }

    public MapboxMapOptions attributionEnabled(boolean z) {
        this.attributionEnabled = z;
        return this;
    }

    public MapboxMapOptions attributionGravity(int i) {
        this.attributionGravity = i;
        return this;
    }

    public MapboxMapOptions attributionMargins(int[] iArr) {
        this.attributionMargins = iArr;
        return this;
    }

    public MapboxMapOptions attributionTintColor(@ColorInt int i) {
        this.attributionTintColor = i;
        return this;
    }

    public MapboxMapOptions rotateGesturesEnabled(boolean z) {
        this.rotateGesturesEnabled = z;
        return this;
    }

    public MapboxMapOptions scrollGesturesEnabled(boolean z) {
        this.scrollGesturesEnabled = z;
        return this;
    }

    public MapboxMapOptions tiltGesturesEnabled(boolean z) {
        this.tiltGesturesEnabled = z;
        return this;
    }

    public MapboxMapOptions zoomControlsEnabled(boolean z) {
        this.zoomControlsEnabled = z;
        return this;
    }

    public MapboxMapOptions zoomGesturesEnabled(boolean z) {
        this.zoomGesturesEnabled = z;
        return this;
    }

    public MapboxMapOptions locationEnabled(boolean z) {
        this.myLocationEnabled = z;
        return this;
    }

    public MapboxMapOptions myLocationForegroundDrawables(Drawable drawable, Drawable drawable2) {
        this.myLocationForegroundDrawable = drawable;
        this.myLocationForegroundBearingDrawable = drawable2;
        return this;
    }

    public MapboxMapOptions myLocationForegroundDrawable(Drawable drawable) {
        this.myLocationForegroundDrawable = drawable;
        return this;
    }

    public MapboxMapOptions myLocationBackgroundDrawable(Drawable drawable) {
        this.myLocationBackgroundDrawable = drawable;
        return this;
    }

    public MapboxMapOptions myLocationForegroundTintColor(@ColorInt int i) {
        this.myLocationForegroundTintColor = i;
        return this;
    }

    public MapboxMapOptions myLocationBackgroundTintColor(@ColorInt int i) {
        this.myLocationBackgroundTintColor = i;
        return this;
    }

    public MapboxMapOptions myLocationBackgroundPadding(int[] iArr) {
        this.myLocationBackgroundPadding = iArr;
        return this;
    }

    public MapboxMapOptions myLocationAccuracyTint(@ColorInt int i) {
        this.myLocationAccuracyTintColor = i;
        return this;
    }

    public MapboxMapOptions myLocationAccuracyAlpha(@IntRange(from = 0, to = 255) int i) {
        this.myLocationAccuracyAlpha = i;
        return this;
    }

    public MapboxMapOptions textureMode(boolean z) {
        this.textureMode = z;
        return this;
    }

    public String getApiBaseUrl() {
        return this.apiBaseUrl;
    }

    public CameraPosition getCamera() {
        return this.cameraPosition;
    }

    public float getMinZoom() {
        return this.minZoom;
    }

    public float getMaxZoom() {
        return this.maxZoom;
    }

    public boolean getCompassEnabled() {
        return this.compassEnabled;
    }

    public int getCompassGravity() {
        return this.compassGravity;
    }

    public int[] getCompassMargins() {
        return this.compassMargins;
    }

    public boolean getCompassFadeFacingNorth() {
        return this.fadeCompassFacingNorth;
    }

    public boolean getLogoEnabled() {
        return this.logoEnabled;
    }

    public int getLogoGravity() {
        return this.logoGravity;
    }

    public int[] getLogoMargins() {
        return this.logoMargins;
    }

    @Deprecated
    public String getAccessToken() {
        return this.accessToken;
    }

    public String getStyle() {
        return this.style;
    }

    public boolean getRotateGesturesEnabled() {
        return this.rotateGesturesEnabled;
    }

    public boolean getScrollGesturesEnabled() {
        return this.scrollGesturesEnabled;
    }

    public boolean getTiltGesturesEnabled() {
        return this.tiltGesturesEnabled;
    }

    public boolean getZoomControlsEnabled() {
        return this.zoomControlsEnabled;
    }

    public boolean getZoomGesturesEnabled() {
        return this.zoomGesturesEnabled;
    }

    public boolean getAttributionEnabled() {
        return this.attributionEnabled;
    }

    public int getAttributionGravity() {
        return this.attributionGravity;
    }

    public int[] getAttributionMargins() {
        return this.attributionMargins;
    }

    @ColorInt
    public int getAttributionTintColor() {
        return this.attributionTintColor;
    }

    public boolean getLocationEnabled() {
        return this.myLocationEnabled;
    }

    public Drawable getMyLocationForegroundDrawable() {
        return this.myLocationForegroundDrawable;
    }

    public Drawable getMyLocationForegroundBearingDrawable() {
        return this.myLocationForegroundBearingDrawable;
    }

    public Drawable getMyLocationBackgroundDrawable() {
        return this.myLocationBackgroundDrawable;
    }

    public int getMyLocationForegroundTintColor() {
        return this.myLocationForegroundTintColor;
    }

    public int getMyLocationBackgroundTintColor() {
        return this.myLocationBackgroundTintColor;
    }

    public int[] getMyLocationBackgroundPadding() {
        return this.myLocationBackgroundPadding;
    }

    public int getMyLocationAccuracyTintColor() {
        return this.myLocationAccuracyTintColor;
    }

    public int getMyLocationAccuracyAlpha() {
        return this.myLocationAccuracyAlpha;
    }

    public boolean getDebugActive() {
        return this.debugActive;
    }

    public boolean getTextureMode() {
        return this.textureMode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        Parcelable bitmapFromDrawable;
        Parcelable parcelable = null;
        int i3 = 1;
        parcel.writeParcelable(this.cameraPosition, i);
        parcel.writeByte((byte) (this.debugActive ? 1 : 0));
        if (this.compassEnabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeInt(this.compassGravity);
        parcel.writeIntArray(this.compassMargins);
        if (this.fadeCompassFacingNorth) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.logoEnabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeInt(this.logoGravity);
        parcel.writeIntArray(this.logoMargins);
        if (this.attributionEnabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeInt(this.attributionGravity);
        parcel.writeIntArray(this.attributionMargins);
        parcel.writeInt(this.attributionTintColor);
        parcel.writeFloat(this.minZoom);
        parcel.writeFloat(this.maxZoom);
        if (this.rotateGesturesEnabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.scrollGesturesEnabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.tiltGesturesEnabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.zoomControlsEnabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.zoomGesturesEnabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.myLocationEnabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.myLocationForegroundDrawable != null) {
            bitmapFromDrawable = getBitmapFromDrawable(this.myLocationForegroundDrawable);
        } else {
            bitmapFromDrawable = null;
        }
        parcel.writeParcelable(bitmapFromDrawable, i);
        if (this.myLocationForegroundBearingDrawable != null) {
            bitmapFromDrawable = getBitmapFromDrawable(this.myLocationForegroundBearingDrawable);
        } else {
            bitmapFromDrawable = null;
        }
        parcel.writeParcelable(bitmapFromDrawable, i);
        if (this.myLocationBackgroundDrawable != null) {
            parcelable = getBitmapFromDrawable(this.myLocationBackgroundDrawable);
        }
        parcel.writeParcelable(parcelable, i);
        parcel.writeInt(this.myLocationForegroundTintColor);
        parcel.writeInt(this.myLocationBackgroundTintColor);
        parcel.writeIntArray(this.myLocationBackgroundPadding);
        parcel.writeInt(this.myLocationAccuracyAlpha);
        parcel.writeInt(this.myLocationAccuracyTintColor);
        parcel.writeString(this.style);
        parcel.writeString(this.accessToken);
        parcel.writeString(this.apiBaseUrl);
        if (!this.textureMode) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MapboxMapOptions mapboxMapOptions = (MapboxMapOptions) obj;
        if (this.debugActive != mapboxMapOptions.debugActive || this.compassEnabled != mapboxMapOptions.compassEnabled || this.fadeCompassFacingNorth != mapboxMapOptions.fadeCompassFacingNorth || this.compassGravity != mapboxMapOptions.compassGravity || this.logoEnabled != mapboxMapOptions.logoEnabled || this.logoGravity != mapboxMapOptions.logoGravity || this.attributionTintColor != mapboxMapOptions.attributionTintColor || this.attributionEnabled != mapboxMapOptions.attributionEnabled || this.attributionGravity != mapboxMapOptions.attributionGravity || Float.compare(mapboxMapOptions.minZoom, this.minZoom) != 0 || Float.compare(mapboxMapOptions.maxZoom, this.maxZoom) != 0 || this.rotateGesturesEnabled != mapboxMapOptions.rotateGesturesEnabled || this.scrollGesturesEnabled != mapboxMapOptions.scrollGesturesEnabled || this.tiltGesturesEnabled != mapboxMapOptions.tiltGesturesEnabled || this.zoomGesturesEnabled != mapboxMapOptions.zoomGesturesEnabled || this.zoomControlsEnabled != mapboxMapOptions.zoomControlsEnabled || this.myLocationEnabled != mapboxMapOptions.myLocationEnabled || this.myLocationForegroundTintColor != mapboxMapOptions.myLocationForegroundTintColor || this.myLocationBackgroundTintColor != mapboxMapOptions.myLocationBackgroundTintColor || this.myLocationAccuracyTintColor != mapboxMapOptions.myLocationAccuracyTintColor || this.myLocationAccuracyAlpha != mapboxMapOptions.myLocationAccuracyAlpha) {
            return false;
        }
        if (this.cameraPosition != null) {
            if (!this.cameraPosition.equals(mapboxMapOptions.cameraPosition)) {
                return false;
            }
        } else if (mapboxMapOptions.cameraPosition != null) {
            return false;
        }
        if (!Arrays.equals(this.compassMargins, mapboxMapOptions.compassMargins) || !Arrays.equals(this.logoMargins, mapboxMapOptions.logoMargins) || !Arrays.equals(this.attributionMargins, mapboxMapOptions.attributionMargins)) {
            return false;
        }
        if (this.myLocationForegroundDrawable != null) {
            if (!this.myLocationForegroundDrawable.equals(mapboxMapOptions.myLocationForegroundDrawable)) {
                return false;
            }
        } else if (mapboxMapOptions.myLocationForegroundDrawable != null) {
            return false;
        }
        if (this.myLocationForegroundBearingDrawable != null) {
            if (!this.myLocationForegroundBearingDrawable.equals(mapboxMapOptions.myLocationForegroundBearingDrawable)) {
                return false;
            }
        } else if (mapboxMapOptions.myLocationForegroundBearingDrawable != null) {
            return false;
        }
        if (this.myLocationBackgroundDrawable != null) {
            if (!this.myLocationBackgroundDrawable.equals(mapboxMapOptions.myLocationBackgroundDrawable)) {
                return false;
            }
        } else if (mapboxMapOptions.myLocationBackgroundDrawable != null) {
            return false;
        }
        if (!Arrays.equals(this.myLocationBackgroundPadding, mapboxMapOptions.myLocationBackgroundPadding)) {
            return false;
        }
        if (this.style != null) {
            if (!this.style.equals(mapboxMapOptions.style)) {
                return false;
            }
        } else if (mapboxMapOptions.style != null) {
            return false;
        }
        if (this.apiBaseUrl != null) {
            if (!this.apiBaseUrl.equals(mapboxMapOptions.apiBaseUrl)) {
                return false;
            }
        } else if (mapboxMapOptions.apiBaseUrl != null) {
            return false;
        }
        if (this.accessToken != null) {
            z = this.accessToken.equals(mapboxMapOptions.accessToken);
        } else if (mapboxMapOptions.accessToken != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i;
        int i2 = 1;
        int i3 = 0;
        int hashCode = (this.cameraPosition != null ? this.cameraPosition.hashCode() : 0) * 31;
        if (this.debugActive) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.compassEnabled) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.fadeCompassFacingNorth) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (((((i + hashCode) * 31) + this.compassGravity) * 31) + Arrays.hashCode(this.compassMargins)) * 31;
        if (this.logoEnabled) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (((((((i + hashCode) * 31) + this.logoGravity) * 31) + Arrays.hashCode(this.logoMargins)) * 31) + this.attributionTintColor) * 31;
        if (this.attributionEnabled) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (((((i + hashCode) * 31) + this.attributionGravity) * 31) + Arrays.hashCode(this.attributionMargins)) * 31;
        if (this.minZoom != 0.0f) {
            i = Float.floatToIntBits(this.minZoom);
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.maxZoom != 0.0f) {
            i = Float.floatToIntBits(this.maxZoom);
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.rotateGesturesEnabled) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.scrollGesturesEnabled) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.tiltGesturesEnabled) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.zoomGesturesEnabled) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.zoomControlsEnabled) {
            i = 1;
        } else {
            i = 0;
        }
        i = (i + hashCode) * 31;
        if (!this.myLocationEnabled) {
            i2 = 0;
        }
        i2 = (i + i2) * 31;
        if (this.myLocationForegroundDrawable != null) {
            i = this.myLocationForegroundDrawable.hashCode();
        } else {
            i = 0;
        }
        i2 = (i + i2) * 31;
        if (this.myLocationForegroundBearingDrawable != null) {
            i = this.myLocationForegroundBearingDrawable.hashCode();
        } else {
            i = 0;
        }
        i2 = (i + i2) * 31;
        if (this.myLocationBackgroundDrawable != null) {
            i = this.myLocationBackgroundDrawable.hashCode();
        } else {
            i = 0;
        }
        i2 = (((((((((((i + i2) * 31) + this.myLocationForegroundTintColor) * 31) + this.myLocationBackgroundTintColor) * 31) + Arrays.hashCode(this.myLocationBackgroundPadding)) * 31) + this.myLocationAccuracyTintColor) * 31) + this.myLocationAccuracyAlpha) * 31;
        if (this.style != null) {
            i = this.style.hashCode();
        } else {
            i = 0;
        }
        i2 = (i + i2) * 31;
        if (this.accessToken != null) {
            i = this.accessToken.hashCode();
        } else {
            i = 0;
        }
        i = (i + i2) * 31;
        if (this.apiBaseUrl != null) {
            i3 = this.apiBaseUrl.hashCode();
        }
        return i + i3;
    }
}
