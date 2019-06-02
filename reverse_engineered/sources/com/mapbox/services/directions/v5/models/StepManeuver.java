package com.mapbox.services.directions.v5.models;

import ch.qos.logback.core.CoreConstants;
import com.google.gson.annotations.SerializedName;
import com.mapbox.services.commons.models.Position;
import java.util.Arrays;

public class StepManeuver {
    @SerializedName("bearing_after")
    private double bearingAfter;
    @SerializedName("bearing_before")
    private double bearingBefore;
    private String instruction;
    private double[] location;
    private String modifier;
    private String pronunciation;
    private String type;

    public double[] getLocation() {
        return this.location;
    }

    public double getBearingBefore() {
        return this.bearingBefore;
    }

    public double getBearingAfter() {
        return this.bearingAfter;
    }

    public String getType() {
        return this.type;
    }

    public String getPronunciation() {
        return this.pronunciation;
    }

    public String getModifier() {
        return this.modifier;
    }

    public String getInstruction() {
        return this.instruction;
    }

    public Position asPosition() {
        return Position.fromCoordinates(this.location[0], this.location[1]);
    }

    public String toString() {
        return "StepManeuver{location=" + Arrays.toString(this.location) + ", bearingBefore=" + this.bearingBefore + ", bearingAfter=" + this.bearingAfter + ", instruction='" + this.instruction + CoreConstants.SINGLE_QUOTE_CHAR + ", type='" + this.type + CoreConstants.SINGLE_QUOTE_CHAR + ", pronunciation='" + this.pronunciation + CoreConstants.SINGLE_QUOTE_CHAR + ", modifier='" + this.modifier + CoreConstants.SINGLE_QUOTE_CHAR + CoreConstants.CURLY_RIGHT;
    }
}
