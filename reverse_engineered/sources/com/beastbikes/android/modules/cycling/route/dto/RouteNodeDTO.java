package com.beastbikes.android.modules.cycling.route.dto;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;
import com.baidu.platform.comapi.location.CoordinateType;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.Serializable;
import org.json.JSONObject;

public class RouteNodeDTO implements Serializable {
    private static final long serialVersionUID = -4343512128919138872L;
    private double altitude;
    private String coordinate;
    private String id;
    private long keyNode;
    private double latitude;
    private double longitude;
    private String name;
    private long ordinal;
    private String routeId;

    public RouteNodeDTO(JSONObject jSONObject) {
        this.coordinate = jSONObject.optString("coordinate");
        this.keyNode = (long) jSONObject.optInt("keyNode");
        this.altitude = jSONObject.optDouble(MapboxEvent.KEY_ALTITUDE);
        this.latitude = jSONObject.optDouble(MapboxEvent.KEY_LATITUDE);
        this.longitude = jSONObject.optDouble(MapboxEvent.KEY_LONGITUDE);
        CoordinateConverter coordinateConverter;
        LatLng convert;
        if (this.coordinate.equals(CoordinateType.WGS84)) {
            coordinateConverter = new CoordinateConverter();
            coordinateConverter.from(CoordType.GPS);
            coordinateConverter.coord(new LatLng(this.latitude, this.longitude));
            convert = coordinateConverter.convert();
            this.latitude = convert.latitude;
            this.longitude = convert.longitude;
        } else if (this.coordinate.equals(CoordinateType.GCJ02)) {
            coordinateConverter = new CoordinateConverter();
            coordinateConverter.from(CoordType.COMMON);
            coordinateConverter.coord(new LatLng(this.latitude, this.longitude));
            convert = coordinateConverter.convert();
            this.latitude = convert.latitude;
            this.longitude = convert.longitude;
        }
        this.name = jSONObject.optString("name");
        this.ordinal = (long) jSONObject.optInt("ordinal");
        this.routeId = jSONObject.optString("routeId");
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(String str) {
        this.coordinate = str;
    }

    public long getKeyNode() {
        return this.keyNode;
    }

    public void setKeyNode(long j) {
        this.keyNode = j;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public void setAltitude(double d) {
        this.altitude = d;
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

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public long getOrdinal() {
        return this.ordinal;
    }

    public void setOrdinal(long j) {
        this.ordinal = j;
    }

    public String getRouteId() {
        return this.routeId;
    }

    public void setRouteId(String str) {
        this.routeId = str;
    }

    public String toString() {
        return "RouteNodeDTO [id=" + this.id + ", coordinate=" + this.coordinate + ", keyNode=" + this.keyNode + ", altitude=" + this.altitude + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", name=" + this.name + ", ordinal=" + this.ordinal + ", routeId=" + this.routeId + "]";
    }
}
