package com.mapbox.services.commons.geojson.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mapbox.services.commons.models.Position;
import java.lang.reflect.Type;

public class PositionSerializer implements JsonSerializer<Position> {
    public JsonElement serialize(Position position, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(Double.valueOf(position.getLongitude())));
        jsonArray.add(new JsonPrimitive(Double.valueOf(position.getLatitude())));
        if (position.hasAltitude()) {
            jsonArray.add(new JsonPrimitive(Double.valueOf(position.getAltitude())));
        }
        return jsonArray;
    }
}
