package com.mapbox.services.commons.turf;

import com.mapbox.services.commons.geojson.Feature;
import com.mapbox.services.commons.geojson.LineString;
import com.mapbox.services.commons.geojson.Point;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.commons.turf.models.LineIntersectsResult;
import java.util.ArrayList;
import java.util.List;

public class TurfMisc {
    public static LineString lineSlice(Point point, Point point2, Feature feature) throws TurfException {
        if (feature.getGeometry().getType().equals("LineString")) {
            return lineSlice(point, point2, (LineString) feature.getGeometry());
        }
        throw new TurfException("input must be a LineString Feature or Geometry");
    }

    public static LineString lineSlice(Point point, Point point2, LineString lineString) throws TurfException {
        List coordinates = lineString.getCoordinates();
        Feature pointOnLine = pointOnLine(point, coordinates);
        Feature pointOnLine2 = pointOnLine(point2, coordinates);
        List arrayList = new ArrayList();
        if (((Integer) pointOnLine.getNumberProperty("index")).intValue() <= ((Integer) pointOnLine2.getNumberProperty("index")).intValue()) {
            arrayList.add(pointOnLine);
            arrayList.add(pointOnLine2);
        } else {
            arrayList.add(pointOnLine2);
            arrayList.add(pointOnLine);
        }
        List arrayList2 = new ArrayList();
        arrayList2.add(((Point) ((Feature) arrayList.get(0)).getGeometry()).getCoordinates());
        LineString fromCoordinates = LineString.fromCoordinates(arrayList2);
        for (int intValue = ((Integer) ((Feature) arrayList.get(0)).getNumberProperty("index")).intValue() + 1; intValue < ((Integer) ((Feature) arrayList.get(1)).getNumberProperty("index")).intValue() + 1; intValue++) {
            List coordinates2 = fromCoordinates.getCoordinates();
            coordinates2.add(coordinates.get(intValue));
            fromCoordinates.setCoordinates(coordinates2);
        }
        arrayList2 = fromCoordinates.getCoordinates();
        arrayList2.add(((Point) ((Feature) arrayList.get(1)).getGeometry()).getCoordinates());
        fromCoordinates.setCoordinates(arrayList2);
        return fromCoordinates;
    }

    public static Feature pointOnLine(Point point, List<Position> list) throws TurfException {
        String str = TurfConstants.UNIT_MILES;
        Feature fromGeometry = Feature.fromGeometry(Point.fromCoordinates(Position.fromCoordinates(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)));
        fromGeometry.addNumberProperty("dist", Double.valueOf(Double.POSITIVE_INFINITY));
        int i = 0;
        Feature feature = fromGeometry;
        while (i < list.size() - 1) {
            Feature feature2;
            Feature fromGeometry2 = Feature.fromGeometry(Point.fromCoordinates((Position) list.get(i)));
            Feature fromGeometry3 = Feature.fromGeometry(Point.fromCoordinates((Position) list.get(i + 1)));
            Feature feature3 = fromGeometry2;
            feature3.addNumberProperty("dist", Double.valueOf(TurfMeasurement.distance(point, (Point) fromGeometry2.getGeometry(), str)));
            feature3 = fromGeometry3;
            feature3.addNumberProperty("dist", Double.valueOf(TurfMeasurement.distance(point, (Point) fromGeometry3.getGeometry(), str)));
            double bearing = TurfMeasurement.bearing((Point) fromGeometry2.getGeometry(), (Point) fromGeometry3.getGeometry());
            fromGeometry = Feature.fromGeometry(TurfMeasurement.destination(point, 1000.0d, bearing + 90.0d, str));
            LineIntersectsResult lineIntersects = lineIntersects(point.getCoordinates().getLongitude(), point.getCoordinates().getLatitude(), ((Point) fromGeometry.getGeometry()).getCoordinates().getLongitude(), ((Point) fromGeometry.getGeometry()).getCoordinates().getLatitude(), ((Point) fromGeometry2.getGeometry()).getCoordinates().getLongitude(), ((Point) fromGeometry2.getGeometry()).getCoordinates().getLatitude(), ((Point) fromGeometry3.getGeometry()).getCoordinates().getLongitude(), ((Point) fromGeometry3.getGeometry()).getCoordinates().getLatitude());
            if (lineIntersects == null) {
                fromGeometry = Feature.fromGeometry(TurfMeasurement.destination(point, 1000.0d, bearing - 90.0d, str));
                lineIntersects = lineIntersects(point.getCoordinates().getLongitude(), point.getCoordinates().getLatitude(), ((Point) fromGeometry.getGeometry()).getCoordinates().getLongitude(), ((Point) fromGeometry.getGeometry()).getCoordinates().getLatitude(), ((Point) fromGeometry2.getGeometry()).getCoordinates().getLongitude(), ((Point) fromGeometry2.getGeometry()).getCoordinates().getLatitude(), ((Point) fromGeometry3.getGeometry()).getCoordinates().getLongitude(), ((Point) fromGeometry3.getGeometry()).getCoordinates().getLatitude());
            }
            fromGeometry.addNumberProperty("dist", Double.valueOf(Double.POSITIVE_INFINITY));
            fromGeometry = null;
            if (lineIntersects != null) {
                fromGeometry = Feature.fromGeometry(Point.fromCoordinates(Position.fromCoordinates(lineIntersects.getY().doubleValue(), lineIntersects.getX().doubleValue())));
                fromGeometry.addNumberProperty("dist", Double.valueOf(TurfMeasurement.distance(point, (Point) fromGeometry.getGeometry(), str)));
            }
            if (((Double) fromGeometry2.getNumberProperty("dist")).doubleValue() < ((Double) feature.getNumberProperty("dist")).doubleValue()) {
                fromGeometry2.addNumberProperty("index", Integer.valueOf(i));
                feature = fromGeometry2;
            }
            if (((Double) fromGeometry3.getNumberProperty("dist")).doubleValue() < ((Double) feature.getNumberProperty("dist")).doubleValue()) {
                fromGeometry3.addNumberProperty("index", Integer.valueOf(i));
                feature2 = fromGeometry3;
            } else {
                feature2 = feature;
            }
            if (fromGeometry == null || ((Double) fromGeometry.getNumberProperty("dist")).doubleValue() >= ((Double) feature2.getNumberProperty("dist")).doubleValue()) {
                fromGeometry = feature2;
            } else {
                fromGeometry.addNumberProperty("index", Integer.valueOf(i));
            }
            i++;
            feature = fromGeometry;
        }
        return feature;
    }

    private static LineIntersectsResult lineIntersects(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        LineIntersectsResult lineIntersectsResult = new LineIntersectsResult();
        double d9 = ((d8 - d6) * (d3 - d)) - ((d7 - d5) * (d4 - d2));
        if (d9 != 0.0d) {
            double d10 = d2 - d6;
            double d11 = d - d5;
            double d12 = ((d7 - d5) * d10) - ((d8 - d6) * d11);
            d10 = (d10 * (d3 - d)) - (d11 * (d4 - d2));
            d11 = d12 / d9;
            d9 = d10 / d9;
            lineIntersectsResult.setX(Double.valueOf(((d3 - d) * d11) + d));
            lineIntersectsResult.setY(Double.valueOf(((d4 - d2) * d11) + d2));
            if (d11 > 0.0d && d11 < 1.0d) {
                lineIntersectsResult.setOnLine1(true);
            }
            if (d9 > 0.0d && d9 < 1.0d) {
                lineIntersectsResult.setOnLine2(true);
            }
            return (lineIntersectsResult.isOnLine1() && lineIntersectsResult.isOnLine2()) ? lineIntersectsResult : null;
        } else if (lineIntersectsResult.getX() == null || lineIntersectsResult.getY() == null) {
            return null;
        } else {
            return lineIntersectsResult;
        }
    }
}
