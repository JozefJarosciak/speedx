package com.mapbox.services.commons.turf;

import java.util.HashMap;
import java.util.Map;

public class TurfHelpers {
    private static final Map<String, Double> factors = new HashMap();

    static {
        factors.put(TurfConstants.UNIT_MILES, Double.valueOf(3960.0d));
        factors.put(TurfConstants.UNIT_NAUTICAL_MILES, Double.valueOf(3441.145d));
        factors.put(TurfConstants.UNIT_DEGREES, Double.valueOf(57.2957795d));
        factors.put(TurfConstants.UNIT_RADIANS, Double.valueOf(1.0d));
        factors.put(TurfConstants.UNIT_INCHES, Double.valueOf(2.509056E8d));
        factors.put(TurfConstants.UNIT_YARDS, Double.valueOf(6969600.0d));
        factors.put(TurfConstants.UNIT_METERS, Double.valueOf(637300.0d));
        factors.put("kilometers", Double.valueOf(6373.0d));
        factors.put("metres", Double.valueOf(637300.0d));
        factors.put("kilometres", Double.valueOf(6373.0d));
    }

    public static double radiansToDistance(double d) throws TurfException {
        return radiansToDistance(d, "kilometers");
    }

    public static double radiansToDistance(double d, String str) throws TurfException {
        Double d2 = (Double) factors.get(str);
        if (d2 != null) {
            return d2.doubleValue() * d;
        }
        throw new TurfException("Invalid unit.");
    }

    public static double distanceToRadians(double d) throws TurfException {
        return distanceToRadians(d, "kilometers");
    }

    public static double distanceToRadians(double d, String str) throws TurfException {
        Double d2 = (Double) factors.get(str);
        if (d2 != null) {
            return d / d2.doubleValue();
        }
        throw new TurfException("Invalid unit.");
    }
}
