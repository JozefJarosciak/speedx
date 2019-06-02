package com.mapbox.services.commons.turf;

import com.mapbox.services.commons.geojson.Feature;
import com.mapbox.services.commons.geojson.FeatureCollection;
import com.mapbox.services.commons.geojson.Geometry;
import com.mapbox.services.commons.geojson.LineString;
import com.mapbox.services.commons.geojson.Point;
import com.mapbox.services.commons.models.Position;
import java.util.List;

public class TurfMeasurement {
    public static double bearing(Position position, Position position2) {
        return bearing(Point.fromCoordinates(position), Point.fromCoordinates(position2));
    }

    public static double bearing(Point point, Point point2) {
        Position coordinates = point.getCoordinates();
        Position coordinates2 = point2.getCoordinates();
        double longitude = coordinates.getLongitude() * 0.017453292519943295d;
        double longitude2 = coordinates2.getLongitude() * 0.017453292519943295d;
        double latitude = coordinates.getLatitude() * 0.017453292519943295d;
        double latitude2 = 0.017453292519943295d * coordinates2.getLatitude();
        return Math.atan2(Math.sin(longitude2 - longitude) * Math.cos(latitude2), (Math.cos(latitude) * Math.sin(latitude2)) - ((Math.cos(latitude2) * Math.sin(latitude)) * Math.cos(longitude2 - longitude))) * 57.29577951308232d;
    }

    public static Position destination(Position position, double d, double d2, String str) throws TurfException {
        return destination(Point.fromCoordinates(position), d, d2, str).getCoordinates();
    }

    public static Point destination(Point point, double d, double d2, String str) throws TurfException {
        Position coordinates = point.getCoordinates();
        double longitude = coordinates.getLongitude() * 0.017453292519943295d;
        double latitude = coordinates.getLatitude() * 0.017453292519943295d;
        double d3 = 0.017453292519943295d * d2;
        double distanceToRadians = TurfHelpers.distanceToRadians(d, str);
        double asin = Math.asin((Math.sin(latitude) * Math.cos(distanceToRadians)) + ((Math.cos(latitude) * Math.sin(distanceToRadians)) * Math.cos(d3)));
        return Point.fromCoordinates(Position.fromCoordinates((Math.atan2((Math.sin(d3) * Math.sin(distanceToRadians)) * Math.cos(latitude), Math.cos(distanceToRadians) - (Math.sin(latitude) * Math.sin(asin))) + longitude) * 57.29577951308232d, 57.29577951308232d * asin));
    }

    public static double distance(Position position, Position position2, String str) throws TurfException {
        return distance(Point.fromCoordinates(position), Point.fromCoordinates(position2), str);
    }

    public static double distance(Point point, Point point2) throws TurfException {
        return distance(point, point2, "kilometers");
    }

    public static double distance(Point point, Point point2, String str) throws TurfException {
        Position coordinates = point.getCoordinates();
        Position coordinates2 = point2.getCoordinates();
        double longitude = (coordinates2.getLongitude() - coordinates.getLongitude()) * 0.017453292519943295d;
        double latitude = coordinates.getLatitude() * 0.017453292519943295d;
        double latitude2 = 0.017453292519943295d * coordinates2.getLatitude();
        latitude2 = (Math.cos(latitude2) * (Math.pow(Math.sin(longitude / 2.0d), 2.0d) * Math.cos(latitude))) + Math.pow(Math.sin(((coordinates2.getLatitude() - coordinates.getLatitude()) * 0.017453292519943295d) / 2.0d), 2.0d);
        return TurfHelpers.radiansToDistance(Math.atan2(Math.sqrt(latitude2), Math.sqrt(1.0d - latitude2)) * 2.0d, str);
    }

    public static double lineDistance(FeatureCollection featureCollection, String str) throws TurfException {
        double d = 0.0d;
        for (int i = 0; i < featureCollection.getFeatures().size(); i++) {
            d += lineDistance((Feature) featureCollection.getFeatures().get(i), str);
        }
        return d;
    }

    public static double lineDistance(Feature feature, String str) throws TurfException {
        return lineDistance(feature.getGeometry(), str);
    }

    public static double lineDistance(Geometry geometry, String str) throws TurfException {
        double d = 0.0d;
        int i = 0;
        if (geometry.getType().equals("LineString")) {
            return length((List) geometry.getCoordinates(), str);
        }
        List list;
        if (geometry.getType().equals("Polygon") || geometry.getType().equals("MultiLineString")) {
            list = (List) geometry.getCoordinates();
            while (i < list.size()) {
                d += length((List) list.get(i), str);
                i++;
            }
            return d;
        } else if (geometry.getType().equals("MultiPolygon")) {
            list = (List) geometry.getCoordinates();
            for (int i2 = 0; i2 < list.size(); i2++) {
                for (int i3 = 0; i3 < ((List) list.get(i2)).size(); i3++) {
                    d += length((List) ((List) list.get(i2)).get(i3), str);
                }
            }
            return d;
        } else {
            throw new TurfException("Input must be a LineString, MultiLineString, Polygon, or MultiPolygon Feature or Geometry (or a FeatureCollection containing only those types)");
        }
    }

    private static double length(List<Position> list, String str) throws TurfException {
        double d = 0.0d;
        Point fromCoordinates = Point.fromCoordinates((Position) list.get(0));
        Point fromCoordinates2 = Point.fromCoordinates((Position) list.get(0));
        int i = 1;
        while (i < list.size()) {
            fromCoordinates2.setCoordinates((Position) list.get(i));
            d += distance(fromCoordinates, fromCoordinates2, str);
            i++;
            Point point = fromCoordinates;
            fromCoordinates = fromCoordinates2;
            fromCoordinates2 = point;
        }
        return d;
    }

    public static Position midpoint(Position position, Position position2) throws TurfException {
        Point midpoint = midpoint(Point.fromCoordinates(position), Point.fromCoordinates(position2));
        return Position.fromCoordinates(midpoint.getCoordinates().getLongitude(), midpoint.getCoordinates().getLatitude());
    }

    public static Point midpoint(Point point, Point point2) throws TurfException {
        return destination(point, distance(point, point2, TurfConstants.UNIT_MILES) / 2.0d, bearing(point, point2), TurfConstants.UNIT_MILES);
    }

    public static Point along(LineString lineString, double d, String str) throws TurfException {
        List coordinates = lineString.getCoordinates();
        int i = 0;
        double d2 = 0.0d;
        while (i < coordinates.size() && (d < d2 || i != coordinates.size() - 1)) {
            if (d2 >= d) {
                d2 = d - d2;
                if (d2 == 0.0d) {
                    return Point.fromCoordinates((Position) coordinates.get(i));
                }
                return Point.fromCoordinates(destination((Position) coordinates.get(i), d2, bearing((Position) coordinates.get(i), (Position) coordinates.get(i - 1)) - 180.0d, str));
            }
            d2 += distance((Position) coordinates.get(i), (Position) coordinates.get(i + 1), str);
            i++;
        }
        return Point.fromCoordinates((Position) coordinates.get(coordinates.size() - 1));
    }
}
