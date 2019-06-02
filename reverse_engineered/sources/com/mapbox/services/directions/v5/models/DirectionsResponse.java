package com.mapbox.services.directions.v5.models;

import java.util.List;

public class DirectionsResponse {
    private String code;
    private List<DirectionsRoute> routes;
    private List<DirectionsWaypoint> waypoints;

    public DirectionsResponse(List<DirectionsRoute> list, List<DirectionsWaypoint> list2) {
        this.routes = list;
        this.waypoints = list2;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public List<DirectionsWaypoint> getWaypoints() {
        return this.waypoints;
    }

    public void setWaypoints(List<DirectionsWaypoint> list) {
        this.waypoints = list;
    }

    public List<DirectionsRoute> getRoutes() {
        return this.routes;
    }

    public void setRoutes(List<DirectionsRoute> list) {
        this.routes = list;
    }
}
