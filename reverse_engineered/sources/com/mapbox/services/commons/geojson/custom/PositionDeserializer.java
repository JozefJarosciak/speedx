package com.mapbox.services.commons.geojson.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mapbox.services.commons.models.Position;
import java.lang.reflect.Type;

public class PositionDeserializer implements JsonDeserializer<Position> {
    public Position deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonArray asJsonArray = jsonElement.getAsJsonArray();
        double asDouble = asJsonArray.get(0).getAsDouble();
        double asDouble2 = asJsonArray.get(1).getAsDouble();
        if (asJsonArray.size() > 2) {
            return Position.fromCoordinates(asDouble, asDouble2, asJsonArray.get(2).getAsDouble());
        }
        return Position.fromCoordinates(asDouble, asDouble2);
    }
}
