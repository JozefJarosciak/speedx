package com.mapbox.services.commons.tidy;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.commons.geojson.Feature;
import com.mapbox.services.commons.geojson.FeatureCollection;
import com.mapbox.services.commons.geojson.LineString;
import com.mapbox.services.commons.geojson.Point;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.commons.turf.TurfException;
import com.mapbox.services.commons.turf.TurfMeasurement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Tidy {
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final int DEFAULT_MAXIMUM_POINTS = 100;
    private static final int DEFAULT_MINIMUM_DISTANCE = 10;
    private static final int DEFAULT_MINIMUM_TIME = 5000;
    public static final String KEY_COORD_TIMES = "coordTimes";
    private SimpleDateFormat dateFormat;
    private int maximumPoints;
    private int minimumDistance;
    private int minimumTime;

    public Tidy() {
        this.minimumDistance = 10;
        this.minimumTime = 5000;
        this.maximumPoints = 100;
        this.dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
    }

    public Tidy(int i, int i2, int i3) {
        this.minimumDistance = i;
        this.minimumTime = i2;
        this.maximumPoints = i3;
        this.dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
    }

    public int getMinimumDistance() {
        return this.minimumDistance;
    }

    public void setMinimumDistance(int i) {
        this.minimumDistance = i;
    }

    public int getMinimumTime() {
        return this.minimumTime;
    }

    public void setMinimumTime(int i) {
        this.minimumTime = i;
    }

    public int getMaximumPoints() {
        return this.maximumPoints;
    }

    public void setMaximumPoints(int i) {
        this.maximumPoints = i;
    }

    public SimpleDateFormat getDateFormat() {
        return this.dateFormat;
    }

    public void setDateFormat(SimpleDateFormat simpleDateFormat) {
        this.dateFormat = simpleDateFormat;
    }

    public FeatureCollection execute(FeatureCollection featureCollection) throws TurfException, ServicesException {
        List arrayList = new ArrayList();
        for (int i = 0; i < featureCollection.getFeatures().size(); i++) {
            Feature feature = (Feature) featureCollection.getFeatures().get(i);
            if (feature.getGeometry().getType().equals("LineString")) {
                List coordinates = ((LineString) feature.getGeometry()).getCoordinates();
                JsonArray asJsonArray = feature.getProperties().getAsJsonArray(KEY_COORD_TIMES);
                ArrayList arrayList2 = new ArrayList();
                JsonElement jsonArray = new JsonArray();
                List list = arrayList2;
                int i2 = 0;
                while (i2 < coordinates.size()) {
                    if (i2 == 0 || i2 == coordinates.size() - 1) {
                        list.add(coordinates.get(i2));
                        if (asJsonArray != null && asJsonArray.size() > 0) {
                            jsonArray.add(asJsonArray.get(i2));
                        }
                    } else if (TurfMeasurement.distance(Point.fromCoordinates((Position) coordinates.get(i2)), Point.fromCoordinates((Position) coordinates.get(i2 + 1)), "kilometers") * 1000.0d < ((double) this.minimumDistance)) {
                        continue;
                    } else {
                        if (asJsonArray != null && asJsonArray.size() > 0) {
                            try {
                                if (this.dateFormat.parse(asJsonArray.get(i2 + 1).getAsString()).getTime() - this.dateFormat.parse(asJsonArray.get(i2).getAsString()).getTime() < ((long) this.minimumTime)) {
                                }
                            } catch (ParseException e) {
                                throw new ServicesException("Tidy expects the date in this format (you can use setDateFormat() to set your own): yyyy-MM-dd'T'HH:mm:ss'Z'");
                            }
                        }
                        list.add(coordinates.get(i2));
                        if (asJsonArray != null && asJsonArray.size() > 0) {
                            jsonArray.add(asJsonArray.get(i2));
                        }
                        if (list.size() % this.maximumPoints == 0) {
                            feature = Feature.fromGeometry(LineString.fromCoordinates(list));
                            feature.addProperty(KEY_COORD_TIMES, jsonArray);
                            arrayList.add(feature);
                            list = new ArrayList();
                            jsonArray = new JsonArray();
                            list.add(coordinates.get(i2));
                            if (asJsonArray != null && asJsonArray.size() > 0) {
                                jsonArray.add(asJsonArray.get(i2));
                            }
                        }
                    }
                    i2++;
                }
                feature = Feature.fromGeometry(LineString.fromCoordinates(list));
                feature.addProperty(KEY_COORD_TIMES, jsonArray);
                arrayList.add(feature);
            }
        }
        return FeatureCollection.fromFeatures(arrayList);
    }
}
