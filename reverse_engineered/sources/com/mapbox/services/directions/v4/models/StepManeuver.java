package com.mapbox.services.directions.v4.models;

public class StepManeuver {
    private String instruction;
    private ManeuverPoint location;
    private String mode;
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public ManeuverPoint getLocation() {
        return this.location;
    }

    public void setLocation(ManeuverPoint maneuverPoint) {
        this.location = maneuverPoint;
    }

    public String getInstruction() {
        return this.instruction;
    }

    public void setInstruction(String str) {
        this.instruction = str;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String str) {
        this.mode = str;
    }
}
