package com.j256.ormlite.dao;

public class Dao$CreateOrUpdateStatus {
    private boolean created;
    private int numLinesChanged;
    private boolean updated;

    public Dao$CreateOrUpdateStatus(boolean z, boolean z2, int i) {
        this.created = z;
        this.updated = z2;
        this.numLinesChanged = i;
    }

    public boolean isCreated() {
        return this.created;
    }

    public boolean isUpdated() {
        return this.updated;
    }

    public int getNumLinesChanged() {
        return this.numLinesChanged;
    }
}
