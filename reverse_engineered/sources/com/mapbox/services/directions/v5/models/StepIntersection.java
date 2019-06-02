package com.mapbox.services.directions.v5.models;

import com.mapbox.services.commons.models.Position;

public class StepIntersection {
    private int[] bearings;
    private boolean[] entry;
    private int in;
    private double[] location;
    private int out;

    public double[] getLocation() {
        return this.location;
    }

    public int[] getBearings() {
        return this.bearings;
    }

    public boolean[] getEntry() {
        return this.entry;
    }

    public int getIn() {
        return this.in;
    }

    public int getOut() {
        return this.out;
    }

    public Position asPosition() {
        return Position.fromCoordinates(this.location[0], this.location[1]);
    }
}
