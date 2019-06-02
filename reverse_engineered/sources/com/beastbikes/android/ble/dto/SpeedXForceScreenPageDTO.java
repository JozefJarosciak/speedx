package com.beastbikes.android.ble.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class SpeedXForceScreenPageDTO implements Serializable {
    private boolean isChecked;
    private ArrayList<Integer> pagePositions;

    public SpeedXForceScreenPageDTO(boolean z, ArrayList<Integer> arrayList) {
        this.isChecked = z;
        this.pagePositions = arrayList;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public ArrayList<Integer> getPagePositions() {
        return this.pagePositions;
    }

    public void setPagePositions(ArrayList<Integer> arrayList) {
        this.pagePositions = arrayList;
    }
}
