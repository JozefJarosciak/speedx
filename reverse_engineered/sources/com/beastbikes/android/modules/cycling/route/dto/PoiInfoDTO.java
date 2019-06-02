package com.beastbikes.android.modules.cycling.route.dto;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.search.core.PoiInfo;
import java.io.Serializable;

public class PoiInfoDTO implements Serializable {
    private static final long serialVersionUID = -5914132297437168129L;
    private String address;
    private String area;
    private String city;
    private int index;
    private boolean isEdit;
    private double latitude;
    private double longitude;
    private String name;
    private String province;

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public PoiInfoDTO(PoiInfo poiInfo) {
        if (poiInfo != null) {
            this.name = poiInfo.name;
            this.address = poiInfo.address;
            this.latitude = poiInfo.location.latitude;
            this.longitude = poiInfo.location.longitude;
            this.city = poiInfo.city;
            this.isEdit = false;
        }
    }

    public PoiInfoDTO(C2188b c2188b) {
        this.name = c2188b.m11215a();
        this.address = c2188b.m11217b();
        this.latitude = c2188b.m11218c();
        this.longitude = c2188b.m11219d();
        this.city = c2188b.m11220e();
        this.isEdit = false;
    }

    public boolean isEdit() {
        return this.isEdit;
    }

    public void setEdit(boolean z) {
        this.isEdit = z;
    }

    public PoiInfoDTO(BDLocation bDLocation) {
        this.name = bDLocation.getDistrict();
        this.address = bDLocation.getAddrStr();
        this.latitude = bDLocation.getLatitude();
        this.longitude = bDLocation.getLongitude();
        this.city = bDLocation.getCity();
    }

    public PoiInfoDTO(RouteNodeDTO routeNodeDTO) {
        this.name = routeNodeDTO.getName();
        this.latitude = routeNodeDTO.getLatitude();
        this.longitude = routeNodeDTO.getLongitude();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String toString() {
        return "PoiInfoDTO [name=" + this.name + ", address=" + this.address + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", city=" + this.city + "]";
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String str) {
        this.area = str;
    }
}
