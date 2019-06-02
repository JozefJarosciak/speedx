package com.mapbox.services.directions.v5.models;

import com.mapbox.services.commons.models.Position;

public class DirectionsWaypoint {
    private double[] location;
    private String name;

    public String getName() {
        return this.name;
    }

    public double[] getLocation() {
        return this.location;
    }

    public Position asPosition() {
        return Position.fromCoordinates(this.location[0], this.location[1]);
    }
}
