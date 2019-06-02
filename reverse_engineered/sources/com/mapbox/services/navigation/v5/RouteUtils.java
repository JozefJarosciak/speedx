package com.mapbox.services.navigation.v5;

import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.commons.geojson.Point;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.commons.turf.TurfException;
import com.mapbox.services.commons.turf.TurfMeasurement;
import com.mapbox.services.commons.turf.TurfMisc;
import com.mapbox.services.commons.utils.PolylineUtils;
import com.mapbox.services.directions.v5.models.LegStep;
import com.mapbox.services.directions.v5.models.RouteLeg;
import java.util.List;
import java.util.Locale;

public class RouteUtils {
    public static final double DEFAULT_OFF_ROUTE_THRESHOLD_KM = 0.1d;
    private double offRouteThresholdKm;

    public RouteUtils() {
        this.offRouteThresholdKm = 0.1d;
    }

    public RouteUtils(double d) {
        this.offRouteThresholdKm = d;
    }

    public double getOffRouteThresholdKm() {
        return this.offRouteThresholdKm;
    }

    public boolean isInStep(Position position, RouteLeg routeLeg, int i) throws ServicesException, TurfException {
        return getDistanceToStep(position, routeLeg, i) <= this.offRouteThresholdKm;
    }

    public double getDistanceToStep(Position position, RouteLeg routeLeg, int i) throws ServicesException, TurfException {
        return TurfMeasurement.distance(Point.fromCoordinates(position), Point.fromCoordinates(getSnapToRoute(position, routeLeg, i)), "kilometers");
    }

    public Position getSnapToRoute(Position position, RouteLeg routeLeg, int i) throws ServicesException, TurfException {
        List decode = PolylineUtils.decode(validateStep(routeLeg, i).getGeometry(), 5);
        if (decode.size() == 1) {
            return (Position) decode.get(0);
        }
        return ((Point) TurfMisc.pointOnLine(Point.fromCoordinates(position), decode).getGeometry()).getCoordinates();
    }

    public boolean isOffRoute(Position position, RouteLeg routeLeg) throws ServicesException, TurfException {
        for (int i = 0; i < routeLeg.getSteps().size(); i++) {
            if (isInStep(position, routeLeg, i)) {
                return false;
            }
        }
        return true;
    }

    public int getClosestStep(Position position, RouteLeg routeLeg) throws ServicesException, TurfException {
        int i = 0;
        double d = Double.MAX_VALUE;
        int i2 = 0;
        while (i < routeLeg.getSteps().size()) {
            double distanceToStep = getDistanceToStep(position, routeLeg, i);
            if (distanceToStep < d) {
                i2 = i;
                d = distanceToStep;
            }
            i++;
        }
        return i2;
    }

    private LegStep validateStep(RouteLeg routeLeg, int i) throws ServicesException {
        if (routeLeg == null) {
            throw new ServicesException("The provided route is empty.");
        } else if (routeLeg.getSteps() == null || routeLeg.getSteps().size() == 0) {
            throw new ServicesException("The provided route has an empty set of steps.");
        } else if (i < routeLeg.getSteps().size()) {
            return (LegStep) routeLeg.getSteps().get(i);
        } else {
            throw new ServicesException(String.format(Locale.US, "The provided route doesn't have so many steps (%d).", new Object[]{Integer.valueOf(i)}));
        }
    }
}
