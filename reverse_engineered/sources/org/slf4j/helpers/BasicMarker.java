package org.slf4j.helpers;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.slf4j.Marker;

public class BasicMarker implements Marker {
    private static String CLOSE = " ]";
    private static String OPEN = "[ ";
    private static String SEP = ", ";
    private static final long serialVersionUID = 1803952589649545191L;
    private final String name;
    private List<Marker> referenceList;

    BasicMarker(String str) {
        if (str == null) {
            throw new IllegalArgumentException("A marker name cannot be null");
        }
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void add(Marker marker) {
        if (marker == null) {
            throw new IllegalArgumentException("A null value cannot be added to a Marker as reference.");
        } else if (!contains(marker)) {
            if (!marker.contains(this)) {
                if (this.referenceList == null) {
                    this.referenceList = new Vector();
                }
                this.referenceList.add(marker);
            }
        }
    }

    public synchronized boolean hasReferences() {
        boolean z;
        z = this.referenceList != null && this.referenceList.size() > 0;
        return z;
    }

    public boolean hasChildren() {
        return hasReferences();
    }

    public synchronized Iterator<Marker> iterator() {
        Iterator<Marker> it;
        if (this.referenceList != null) {
            it = this.referenceList.iterator();
        } else {
            it = Collections.emptyList().iterator();
        }
        return it;
    }

    public synchronized boolean remove(Marker marker) {
        boolean z;
        if (this.referenceList == null) {
            z = false;
        } else {
            int size = this.referenceList.size();
            for (int i = 0; i < size; i++) {
                if (marker.equals((Marker) this.referenceList.get(i))) {
                    this.referenceList.remove(i);
                    z = true;
                    break;
                }
            }
            z = false;
        }
        return z;
    }

    public boolean contains(Marker marker) {
        if (marker == null) {
            throw new IllegalArgumentException("Other cannot be null");
        } else if (equals(marker)) {
            return true;
        } else {
            if (!hasReferences()) {
                return false;
            }
            for (int i = 0; i < this.referenceList.size(); i++) {
                if (((Marker) this.referenceList.get(i)).contains(marker)) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean contains(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Other cannot be null");
        } else if (this.name.equals(str)) {
            return true;
        } else {
            if (!hasReferences()) {
                return false;
            }
            for (int i = 0; i < this.referenceList.size(); i++) {
                if (((Marker) this.referenceList.get(i)).contains(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Marker)) {
            return false;
        }
        return this.name.equals(((Marker) obj).getName());
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        if (!hasReferences()) {
            return getName();
        }
        Iterator it = iterator();
        StringBuilder stringBuilder = new StringBuilder(getName());
        stringBuilder.append(' ').append(OPEN);
        while (it.hasNext()) {
            stringBuilder.append(((Marker) it.next()).getName());
            if (it.hasNext()) {
                stringBuilder.append(SEP);
            }
        }
        stringBuilder.append(CLOSE);
        return stringBuilder.toString();
    }
}
