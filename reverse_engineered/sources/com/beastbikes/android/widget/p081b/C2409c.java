package com.beastbikes.android.widget.p081b;

/* compiled from: ItemObject */
/* renamed from: com.beastbikes.android.widget.b.c */
public class C2409c {
    public static final int ITEM_TYPE_FOOTER = 2;
    public static final int ITEM_TYPE_ITEM = 0;
    public static final int ITEM_TYPE_NO_DATA = 3;
    public static final int ITEM_TYPE_TOP = 1;
    private long headerId;
    private int itemType;
    private int month;
    private double monthDistance;
    private int totalCount;
    private long totalTime;
    private int year;

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int i) {
        this.itemType = i;
    }

    public long getHeaderId() {
        return this.headerId;
    }

    public void setHeaderId(long j) {
        this.headerId = j;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int i) {
        this.totalCount = i;
    }

    public long getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(long j) {
        this.totalTime = j;
    }

    public double getMonthDistance() {
        return this.monthDistance;
    }

    public void setMonthDistance(double d) {
        this.monthDistance = d;
    }
}
