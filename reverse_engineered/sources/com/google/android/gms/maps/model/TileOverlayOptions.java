package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.maps.model.internal.zzi;
import com.google.android.gms.maps.model.internal.zzi.zza;

public final class TileOverlayOptions implements SafeParcelable {
    public static final zzo CREATOR = new zzo();
    private zzi aiU;
    private TileProvider aiV;
    private boolean aiW;
    private float aim;
    private boolean ain;
    private float aiu;
    private final int mVersionCode;

    /* renamed from: com.google.android.gms.maps.model.TileOverlayOptions$1 */
    class C34641 implements TileProvider {
        private final zzi aiX = this.aiY.aiU;
        final /* synthetic */ TileOverlayOptions aiY;

        C34641(TileOverlayOptions tileOverlayOptions) {
            this.aiY = tileOverlayOptions;
        }

        public Tile getTile(int i, int i2, int i3) {
            try {
                return this.aiX.getTile(i, i2, i3);
            } catch (RemoteException e) {
                return null;
            }
        }
    }

    public TileOverlayOptions() {
        this.ain = true;
        this.aiW = true;
        this.aiu = 0.0f;
        this.mVersionCode = 1;
    }

    TileOverlayOptions(int i, IBinder iBinder, boolean z, float f, boolean z2, float f2) {
        this.ain = true;
        this.aiW = true;
        this.aiu = 0.0f;
        this.mVersionCode = i;
        this.aiU = zza.zzja(iBinder);
        this.aiV = this.aiU == null ? null : new C34641(this);
        this.ain = z;
        this.aim = f;
        this.aiW = z2;
        this.aiu = f2;
    }

    public int describeContents() {
        return 0;
    }

    public TileOverlayOptions fadeIn(boolean z) {
        this.aiW = z;
        return this;
    }

    public boolean getFadeIn() {
        return this.aiW;
    }

    public TileProvider getTileProvider() {
        return this.aiV;
    }

    public float getTransparency() {
        return this.aiu;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public float getZIndex() {
        return this.aim;
    }

    public boolean isVisible() {
        return this.ain;
    }

    public TileOverlayOptions tileProvider(final TileProvider tileProvider) {
        this.aiV = tileProvider;
        this.aiU = this.aiV == null ? null : new zza(this) {
            final /* synthetic */ TileOverlayOptions aiY;

            public Tile getTile(int i, int i2, int i3) {
                return tileProvider.getTile(i, i2, i3);
            }
        };
        return this;
    }

    public TileOverlayOptions transparency(float f) {
        boolean z = f >= 0.0f && f <= 1.0f;
        zzab.zzb(z, (Object) "Transparency must be in the range [0..1]");
        this.aiu = f;
        return this;
    }

    public TileOverlayOptions visible(boolean z) {
        this.ain = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzo.zza(this, parcel, i);
    }

    public TileOverlayOptions zIndex(float f) {
        this.aim = f;
        return this;
    }

    IBinder zzbqj() {
        return this.aiU.asBinder();
    }
}
