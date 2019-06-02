package com.mapbox.services.commons.geojson;

import com.google.gson.GsonBuilder;
import com.mapbox.services.commons.geojson.custom.PositionDeserializer;
import com.mapbox.services.commons.geojson.custom.PositionSerializer;
import com.mapbox.services.commons.models.Position;

public class Point implements Geometry<Position> {
    private Position coordinates;
    private final String type = "Point";

    private Point(Position position) {
        this.coordinates = position;
    }

    public String getType() {
        return "Point";
    }

    public Position getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(Position position) {
        this.coordinates = position;
    }

    public static Point fromCoordinates(Position position) {
        return new Point(position);
    }

    public static Point fromCoordinates(double[] dArr) {
        return fromCoordinates(Position.fromCoordinates(dArr));
    }

    public static Point fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Position.class, new PositionDeserializer());
        return (Point) gsonBuilder.create().fromJson(str, Point.class);
    }

    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Position.class, new PositionSerializer());
        return gsonBuilder.create().toJson((Object) this);
    }
}
