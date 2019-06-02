package com.mapbox.services.geocoding.v5.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mapbox.services.commons.geojson.Geometry;
import com.mapbox.services.commons.geojson.Point;
import com.mapbox.services.commons.models.Position;
import java.lang.reflect.Type;

public class CarmenGeometryDeserializer implements JsonDeserializer<Geometry> {
    public Geometry deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) jsonElement;
        String asString = jsonObject.get("type").getAsString();
        if (asString.equals("Point")) {
            JsonArray asJsonArray = jsonObject.getAsJsonArray("coordinates");
            return Point.fromCoordinates(Position.fromCoordinates(asJsonArray.get(0).getAsDouble(), asJsonArray.get(1).getAsDouble()));
        }
        throw new JsonParseException("Unexpected geometry found: " + asString);
    }
}
