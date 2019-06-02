package org.slf4j.helpers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.IMarkerFactory;
import org.slf4j.Marker;

public class BasicMarkerFactory implements IMarkerFactory {
    private final ConcurrentMap<String, Marker> markerMap = new ConcurrentHashMap();

    public Marker getMarker(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Marker name cannot be null");
        }
        Marker marker = (Marker) this.markerMap.get(str);
        if (marker != null) {
            return marker;
        }
        BasicMarker basicMarker = new BasicMarker(str);
        marker = (Marker) this.markerMap.putIfAbsent(str, basicMarker);
        return marker != null ? marker : basicMarker;
    }

    public boolean exists(String str) {
        if (str == null) {
            return false;
        }
        return this.markerMap.containsKey(str);
    }

    public boolean detachMarker(String str) {
        if (str == null || this.markerMap.remove(str) == null) {
            return false;
        }
        return true;
    }

    public Marker getDetachedMarker(String str) {
        return new BasicMarker(str);
    }
}
