package com.mapbox.services.commons.turf;

import com.mapbox.services.commons.geojson.MultiPolygon;
import com.mapbox.services.commons.geojson.Point;
import com.mapbox.services.commons.geojson.Polygon;
import com.mapbox.services.commons.models.Position;
import java.util.ArrayList;
import java.util.List;

public class TurfJoins {
    public static boolean inside(Position position, List<Position> list) throws TurfException {
        List arrayList = new ArrayList();
        arrayList.add(list);
        return inside(Point.fromCoordinates(position), Polygon.fromCoordinates(arrayList));
    }

    public static boolean inside(Point point, Polygon polygon) throws TurfException {
        List coordinates = polygon.getCoordinates();
        List arrayList = new ArrayList();
        arrayList.add(coordinates);
        return inside(point, MultiPolygon.fromCoordinates(arrayList));
    }

    public static boolean inside(Point point, MultiPolygon multiPolygon) throws TurfException {
        Position coord = TurfInvariant.getCoord(point);
        List coordinates = multiPolygon.getCoordinates();
        boolean z = false;
        for (int i = 0; i < coordinates.size() && !z; i++) {
            if (inRing(coord, (List) ((List) coordinates.get(i)).get(0))) {
                int i2 = 0;
                for (int i3 = 1; i3 < ((List) coordinates.get(i)).size() && i2 == 0; i3++) {
                    if (inRing(coord, (List) ((List) coordinates.get(i)).get(i3))) {
                        i2 = 1;
                    }
                }
                if (i2 == 0) {
                    z = true;
                }
            }
        }
        return z;
    }

    private static boolean inRing(Position position, List<Position> list) {
        boolean z = false;
        int size = list.size() - 1;
        for (int i = 0; i < list.size(); i++) {
            boolean z2;
            double longitude = ((Position) list.get(i)).getLongitude();
            double latitude = ((Position) list.get(i)).getLatitude();
            double longitude2 = ((Position) list.get(size)).getLongitude();
            double latitude2 = ((Position) list.get(size)).getLatitude();
            Object obj = (((latitude > position.getLatitude() ? 1 : (latitude == position.getLatitude() ? 0 : -1)) > 0 ? 1 : null) == ((latitude2 > position.getLatitude() ? 1 : (latitude2 == position.getLatitude() ? 0 : -1)) > 0 ? 1 : null) || position.getLongitude() >= longitude + (((longitude2 - longitude) * (position.getLatitude() - latitude)) / (latitude2 - latitude))) ? null : 1;
            if (obj != null) {
                boolean z3;
                if (z) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                z2 = z3;
            } else {
                z2 = z;
            }
            z = z2;
            size = i;
        }
        return z;
    }
}
