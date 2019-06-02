package com.mapbox.mapboxsdk.offline;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class OfflineRegion {
    private static final String LOG_TAG = "OfflineRegion";
    public static final int STATE_ACTIVE = 1;
    public static final int STATE_INACTIVE = 0;
    private boolean deliverInactiveMessages = false;
    private Handler handler;
    private OfflineRegionDefinition mDefinition = null;
    private long mId = 0;
    private byte[] mMetadata = null;
    private long mOfflineRegionPtr = 0;
    private OfflineManager offlineManager;
    private int state = 0;

    public interface OfflineRegionObserver {
        void mapboxTileCountLimitExceeded(long j);

        void onError(OfflineRegionError offlineRegionError);

        void onStatusChanged(OfflineRegionStatus offlineRegionStatus);
    }

    public interface OfflineRegionStatusCallback {
        void onError(String str);

        void onStatus(OfflineRegionStatus offlineRegionStatus);
    }

    public interface OfflineRegionDeleteCallback {
        void onDelete();

        void onError(String str);
    }

    public interface OfflineRegionUpdateMetadataCallback {
        void onError(String str);

        void onUpdate(byte[] bArr);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DownloadState {
    }

    private native void deleteOfflineRegion(OfflineRegionDeleteCallback offlineRegionDeleteCallback);

    private native void destroyOfflineRegion();

    private native void getOfflineRegionStatus(OfflineRegionStatusCallback offlineRegionStatusCallback);

    private native void setOfflineRegionDownloadState(int i);

    private native void setOfflineRegionObserver(OfflineRegionObserver offlineRegionObserver);

    private native void updateOfflineRegionMetadata(byte[] bArr, OfflineRegionUpdateMetadataCallback offlineRegionUpdateMetadataCallback);

    static {
        System.loadLibrary("mapbox-gl");
    }

    public boolean isDeliveringInactiveMessages() {
        return this.deliverInactiveMessages;
    }

    public void setDeliverInactiveMessages(boolean z) {
        this.deliverInactiveMessages = z;
    }

    private boolean deliverMessages() {
        if (this.state == 1 || isDeliveringInactiveMessages()) {
            return true;
        }
        return false;
    }

    private OfflineRegion() {
    }

    public long getID() {
        return this.mId;
    }

    public OfflineRegionDefinition getDefinition() {
        return this.mDefinition;
    }

    public byte[] getMetadata() {
        return this.mMetadata;
    }

    private Handler getHandler() {
        if (this.handler == null) {
            this.handler = new Handler(Looper.getMainLooper());
        }
        return this.handler;
    }

    public void setObserver(@NonNull final OfflineRegionObserver offlineRegionObserver) {
        setOfflineRegionObserver(new OfflineRegionObserver() {
            public void onStatusChanged(OfflineRegionStatus offlineRegionStatus) {
                if (OfflineRegion.this.deliverMessages()) {
                    OfflineRegion.this.getHandler().post(new OfflineRegion$1$1(this, offlineRegionStatus));
                }
            }

            public void onError(OfflineRegionError offlineRegionError) {
                if (OfflineRegion.this.deliverMessages()) {
                    OfflineRegion.this.getHandler().post(new OfflineRegion$1$2(this, offlineRegionError));
                }
            }

            public void mapboxTileCountLimitExceeded(long j) {
                if (OfflineRegion.this.deliverMessages()) {
                    OfflineRegion.this.getHandler().post(new OfflineRegion$1$3(this, j));
                }
            }
        });
    }

    public void setDownloadState(int i) {
        this.state = i;
        setOfflineRegionDownloadState(i);
    }

    public void getStatus(@NonNull final OfflineRegionStatusCallback offlineRegionStatusCallback) {
        getOfflineRegionStatus(new OfflineRegionStatusCallback() {
            public void onStatus(OfflineRegionStatus offlineRegionStatus) {
                OfflineRegion.this.getHandler().post(new OfflineRegion$2$1(this, offlineRegionStatus));
            }

            public void onError(String str) {
                OfflineRegion.this.getHandler().post(new OfflineRegion$2$2(this, str));
            }
        });
    }

    public void delete(@NonNull final OfflineRegionDeleteCallback offlineRegionDeleteCallback) {
        deleteOfflineRegion(new OfflineRegionDeleteCallback() {
            public void onDelete() {
                OfflineRegion.this.getHandler().post(new OfflineRegion$3$1(this));
            }

            public void onError(String str) {
                OfflineRegion.this.getHandler().post(new OfflineRegion$3$2(this, str));
            }
        });
    }

    public void updateMetadata(@NonNull byte[] bArr, @NonNull final OfflineRegionUpdateMetadataCallback offlineRegionUpdateMetadataCallback) {
        updateOfflineRegionMetadata(bArr, new OfflineRegionUpdateMetadataCallback() {
            public void onUpdate(byte[] bArr) {
                OfflineRegion.this.getHandler().post(new OfflineRegion$4$1(this, bArr));
            }

            public void onError(String str) {
                OfflineRegion.this.getHandler().post(new OfflineRegion$4$2(this, str));
            }
        });
    }

    protected void finalize() {
        try {
            super.finalize();
            destroyOfflineRegion();
        } catch (Throwable th) {
            Log.e(LOG_TAG, "Failed to finalize OfflineRegion: " + th.getMessage());
        }
    }
}
