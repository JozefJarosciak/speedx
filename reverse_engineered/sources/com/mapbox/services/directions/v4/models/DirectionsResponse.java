package com.mapbox.services.directions.v4.models;

import java.util.ArrayList;
import java.util.List;

public class DirectionsResponse {
    private DirectionsFeature destination;
    private DirectionsFeature origin;
    private List<DirectionsRoute> routes = new ArrayList();
    private List<DirectionsFeature> waypoints = new ArrayList();

    public DirectionsFeature getOrigin() {
        return this.origin;
    }

    public void setOrigin(DirectionsFeature directionsFeature) {
        this.origin = directionsFeature;
    }

    public DirectionsFeature getDestination() {
        return this.destination;
    }

    public void setDestination(DirectionsFeature directionsFeature) {
        this.destination = directionsFeature;
    }

    public List<DirectionsFeature> getWaypoints() {
        return this.waypoints;
    }

    public void setWaypoints(List<DirectionsFeature> list) {
        this.waypoints = list;
    }

    public List<DirectionsRoute> getRoutes() {
        return this.routes;
    }

    public void setRoutes(List<DirectionsRoute> list) {
        this.routes = list;
    }
}
