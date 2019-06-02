package org.xclcharts.event.click;

public abstract class PositionRecord {
    protected int mDataChildID = -1;
    protected int mDataID = -1;

    protected abstract boolean compareRange(float f, float f2);

    public int getDataID() {
        return this.mDataID;
    }

    public int getDataChildID() {
        return this.mDataChildID;
    }

    public int getRecordID() {
        if (-1 == this.mDataID && -1 == this.mDataChildID) {
            return -1;
        }
        int i = 0;
        if (this.mDataID > 0) {
            i = 0 + this.mDataChildID;
        }
        if (this.mDataChildID > 0) {
            return i + this.mDataChildID;
        }
        return i;
    }

    protected void saveDataID(int i) {
        this.mDataID = i;
    }

    protected void saveDataChildID(int i) {
        this.mDataChildID = i;
    }
}
