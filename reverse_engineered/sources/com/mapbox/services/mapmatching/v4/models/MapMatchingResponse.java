package com.mapbox.services.mapmatching.v4.models;

import com.google.gson.JsonArray;
import com.mapbox.services.commons.geojson.Feature;
import com.mapbox.services.commons.geojson.FeatureCollection;
import com.mapbox.services.commons.models.Position;
import java.util.List;

public class MapMatchingResponse extends FeatureCollection {
    private static final String KEY_MATCHED_POINTS = "matchedPoints";
    private String code;

    public MapMatchingResponse(List<Feature> list) {
        super(list);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public Position[] getMatchedPoints() {
        return getMatchedPoints(0);
    }

    public Position[] getMatchedPoints(int i) {
        JsonArray asJsonArray = ((Feature) getFeatures().get(i)).getProperties().getAsJsonArray(KEY_MATCHED_POINTS);
        Position[] positionArr = new Position[asJsonArray.size()];
        for (int i2 = 0; i2 < asJsonArray.size(); i2++) {
            positionArr[i2] = Position.fromCoordinates(asJsonArray.get(i2).getAsJsonArray().get(0).getAsDouble(), asJsonArray.get(i2).getAsJsonArray().get(1).getAsDouble());
        }
        return positionArr;
    }
}
