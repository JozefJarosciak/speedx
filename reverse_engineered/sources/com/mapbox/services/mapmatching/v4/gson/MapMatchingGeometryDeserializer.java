package com.mapbox.services.mapmatching.v4.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mapbox.services.commons.geojson.Geometry;
import com.mapbox.services.commons.geojson.LineString;
import java.lang.reflect.Type;

public class MapMatchingGeometryDeserializer implements JsonDeserializer<Geometry> {
    public Geometry deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonObject()) {
            return null;
        }
        return LineString.fromPolyline(jsonElement.getAsString(), 6);
    }
}
