package com.mapbox.services.commons.turf;

import com.mapbox.services.commons.geojson.Feature;
import com.mapbox.services.commons.geojson.FeatureCollection;
import com.mapbox.services.commons.geojson.GeoJSON;
import com.mapbox.services.commons.geojson.Point;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.commons.utils.TextUtils;

public class TurfInvariant {
    public static Position getCoord(Feature feature) throws TurfException {
        if (feature.getGeometry().getClass().equals(Point.class)) {
            return getCoord((Point) feature.getGeometry());
        }
        throw new TurfException("A coordinate, feature, or point geometry is required");
    }

    public static Position getCoord(Point point) throws TurfException {
        if (point != null) {
            return point.getCoordinates();
        }
        throw new TurfException("A coordinate, feature, or point geometry is required");
    }

    public static void geojsonType(GeoJSON geoJSON, String str, String str2) throws TurfException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new TurfException("Type and name required");
        } else if (geoJSON == null || !geoJSON.getType().equals(str)) {
            throw new TurfException("Invalid input to " + str2 + ": must be a " + str + ", given " + geoJSON.getType());
        }
    }

    public static void featureOf(Feature feature, String str, String str2) throws TurfException {
        if (TextUtils.isEmpty(str2)) {
            throw new TurfException(".featureOf() requires a name");
        } else if (feature == null || !feature.getType().equals("Feature") || feature.getGeometry() == null) {
            throw new TurfException("Invalid input to " + str2 + ", Feature with geometry required");
        } else if (feature.getGeometry() == null || !feature.getGeometry().getType().equals(str)) {
            throw new TurfException("Invalid input to " + str2 + ": must be a " + str + ", given " + feature.getGeometry().getType());
        }
    }

    public static void collectionOf(FeatureCollection featureCollection, String str, String str2) throws TurfException {
        if (TextUtils.isEmpty(str2)) {
            throw new TurfException("collectionOf() requires a name");
        } else if (featureCollection == null || !featureCollection.getType().equals("FeatureCollection") || featureCollection.getFeatures() == null) {
            throw new TurfException("Invalid input to " + str2 + ", FeatureCollection required");
        } else {
            for (Feature feature : featureCollection.getFeatures()) {
                if (feature == null || !feature.getType().equals("Feature") || feature.getGeometry() == null) {
                    throw new TurfException("Invalid input to " + str2 + ", Feature with geometry required");
                }
                if (feature.getGeometry() != null) {
                    if (!feature.getGeometry().getType().equals(str)) {
                    }
                }
                throw new TurfException("Invalid input to " + str2 + ": must be a " + str + ", given " + feature.getGeometry().getType());
            }
        }
    }
}
