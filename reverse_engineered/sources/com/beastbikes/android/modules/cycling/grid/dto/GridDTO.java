package com.beastbikes.android.modules.cycling.grid.dto;

import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.grid.dao.entity.Grid;
import com.beastbikes.android.utils.C2555d;
import com.mapbox.mapboxsdk.geometry.LatLng;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONObject;

public class GridDTO implements Serializable {
    private int count;
    private int gridId;
    private LatLng latLng1;
    private LatLng latLng2;
    private LatLng latLng3;
    private LatLng latLng4;
    private ArrayList<LatLng> polygons;
    private String unlockAt;
    private String userId;

    public GridDTO(JSONObject jSONObject, String str) {
        this.gridId = jSONObject.optInt("gridId");
        this.count = jSONObject.optInt("count");
        this.unlockAt = jSONObject.optString("unlockAt");
        this.userId = str;
        initLatLng();
    }

    public GridDTO(Grid grid) {
        this.gridId = Integer.valueOf(grid.getId()).intValue();
        this.count = grid.getCount();
        this.unlockAt = grid.getUnlockAt();
        this.userId = grid.getUserId();
        initLatLng();
    }

    public GridDTO(double d, double d2) {
        this.gridId = (int) (Math.floor((180.0d + d2) * 100.0d) + (Math.floor((90.0d + d) * 100.0d) * 36000.0d));
        this.count = 1;
        this.unlockAt = C2555d.m12799a(new Date());
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.userId = currentUser.getObjectId();
        }
        initLatLng();
    }

    private void initLatLng() {
        if (this.gridId != 0) {
            double d = (((double) (this.gridId % 36000)) / 100.0d) - 180.0d;
            String format = String.format("%.6f", new Object[]{Double.valueOf((((double) (this.gridId / 36000)) / 100.0d) - 90.0d)});
            String format2 = String.format("%.6f", new Object[]{Double.valueOf(d)});
            double doubleValue = Double.valueOf(format).doubleValue();
            d = Double.valueOf(format2).doubleValue();
            this.polygons = new ArrayList();
            this.latLng1 = new LatLng(doubleValue, d);
            this.latLng2 = new LatLng(Double.valueOf(String.format("%.6f", new Object[]{Double.valueOf(0.01d + doubleValue)})).doubleValue(), d);
            this.latLng3 = new LatLng(Double.valueOf(String.format("%.6f", new Object[]{Double.valueOf(0.01d + doubleValue)})).doubleValue(), Double.valueOf(String.format("%.6f", new Object[]{Double.valueOf(0.01d + d)})).doubleValue());
            this.latLng4 = new LatLng(doubleValue, Double.valueOf(String.format("%.6f", new Object[]{Double.valueOf(d + 0.01d)})).doubleValue());
            this.polygons.add(this.latLng1);
            this.polygons.add(this.latLng2);
            this.polygons.add(this.latLng3);
            this.polygons.add(this.latLng4);
            this.polygons.add(this.latLng1);
        }
    }

    public int getGridId() {
        return this.gridId;
    }

    public void setGridId(int i) {
        this.gridId = i;
    }

    public String getUnlockAt() {
        return this.unlockAt;
    }

    public void setUnlockAt(String str) {
        this.unlockAt = str;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public LatLng getLatLng1() {
        return this.latLng1;
    }

    public void setLatLng1(LatLng latLng) {
        this.latLng1 = latLng;
    }

    public LatLng getLatLng2() {
        return this.latLng2;
    }

    public void setLatLng2(LatLng latLng) {
        this.latLng2 = latLng;
    }

    public LatLng getLatLng3() {
        return this.latLng3;
    }

    public void setLatLng3(LatLng latLng) {
        this.latLng3 = latLng;
    }

    public LatLng getLatLng4() {
        return this.latLng4;
    }

    public void setLatLng4(LatLng latLng) {
        this.latLng4 = latLng;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public ArrayList<LatLng> getPolygons() {
        return this.polygons;
    }

    public void setPolygons(ArrayList<LatLng> arrayList) {
        this.polygons = arrayList;
    }
}
