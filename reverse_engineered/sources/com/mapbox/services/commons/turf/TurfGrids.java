package com.mapbox.services.commons.turf;

import com.mapbox.services.commons.geojson.Feature;
import com.mapbox.services.commons.geojson.FeatureCollection;
import com.mapbox.services.commons.geojson.Point;
import com.mapbox.services.commons.geojson.Polygon;
import java.util.ArrayList;
import java.util.List;

public class TurfGrids {
    public static FeatureCollection within(FeatureCollection featureCollection, FeatureCollection featureCollection2) throws TurfException {
        List arrayList = new ArrayList();
        for (int i = 0; i < featureCollection2.getFeatures().size(); i++) {
            for (int i2 = 0; i2 < featureCollection.getFeatures().size(); i2++) {
                Point point = (Point) ((Feature) featureCollection.getFeatures().get(i2)).getGeometry();
                if (TurfJoins.inside(point, (Polygon) ((Feature) featureCollection2.getFeatures().get(i)).getGeometry())) {
                    arrayList.add(Feature.fromGeometry(point));
                }
            }
        }
        return FeatureCollection.fromFeatures(arrayList);
    }
}
