package com.mapbox.mapboxsdk.offline;

public class OfflineRegionStatus {
    private long completedResourceCount = 0;
    private long completedResourceSize = 0;
    private long completedTileCount = 0;
    private long completedTileSize = 0;
    private int downloadState = 0;
    private long requiredResourceCount = 0;
    private boolean requiredResourceCountIsPrecise = true;

    private OfflineRegionStatus() {
    }

    public boolean isComplete() {
        return this.completedResourceCount == this.requiredResourceCount;
    }

    public int getDownloadState() {
        return this.downloadState;
    }

    public long getCompletedResourceCount() {
        return this.completedResourceCount;
    }

    public long getCompletedResourceSize() {
        return this.completedResourceSize;
    }

    public long getCompletedTileCount() {
        return this.completedTileCount;
    }

    public long getCompletedTileSize() {
        return this.completedTileSize;
    }

    public long getRequiredResourceCount() {
        return this.requiredResourceCount;
    }

    public boolean isRequiredResourceCountPrecise() {
        return this.requiredResourceCountIsPrecise;
    }
}
