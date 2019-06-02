package io.rong.imlib.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class StatisticsStore {
    private static final String CONNECTIONS_PREFERENCE = "CONNECTIONS";
    private static final String DELIMITER = ":::";
    private static final String EVENTS_PREFERENCE = "EVENTS";
    private static final String LATEST_UPLOAD = "LATEST";
    private static final String LOCATION_PREFERENCE = "LOCATION";
    private static final String PREFERENCES = "COUNTLY_STORE";
    private static final int UPLOAD_DURATION = 86400;
    private final SharedPreferences preferences_;

    /* renamed from: io.rong.imlib.statistics.StatisticsStore$1 */
    class C54031 implements Comparator<Event> {
        C54031() {
        }

        public int compare(Event event, Event event2) {
            return event.timestamp - event2.timestamp;
        }
    }

    StatisticsStore(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("must provide valid context");
        }
        this.preferences_ = context.getSharedPreferences(PREFERENCES, 0);
    }

    public String[] connections() {
        String string = this.preferences_.getString(CONNECTIONS_PREFERENCE, "");
        return string.length() == 0 ? new String[0] : string.split(DELIMITER);
    }

    public String[] events() {
        String string = this.preferences_.getString(EVENTS_PREFERENCE, "");
        return string.length() == 0 ? new String[0] : string.split(DELIMITER);
    }

    public List<Event> eventsList() {
        String[] events = events();
        List<Event> arrayList = new ArrayList(events.length);
        for (String jSONObject : events) {
            try {
                Event fromJSON = Event.fromJSON(new JSONObject(jSONObject));
                if (fromJSON != null) {
                    arrayList.add(fromJSON);
                }
            } catch (JSONException e) {
            }
        }
        Collections.sort(arrayList, new C54031());
        return arrayList;
    }

    public boolean isEmptyConnections() {
        return this.preferences_.getString(CONNECTIONS_PREFERENCE, "").length() == 0;
    }

    public synchronized void addConnection(String str) {
        if (str != null) {
            if (str.length() > 0) {
                Collection arrayList = new ArrayList(Arrays.asList(connections()));
                arrayList.add(str);
                this.preferences_.edit().putString(CONNECTIONS_PREFERENCE, join(arrayList, DELIMITER)).commit();
            }
        }
    }

    public boolean uploadIfNeed() {
        int currentTimestamp = Statistics.currentTimestamp();
        int i = this.preferences_.getInt(LATEST_UPLOAD, 0);
        if (Statistics.sharedInstance().isLoggingEnabled()) {
            Log.w("Statistics", "uploadIfNeed : last = " + i + ", current = " + currentTimestamp);
        }
        if (i == 0) {
            updateLatestUploadTime();
            return true;
        } else if (i + UPLOAD_DURATION > currentTimestamp) {
            return false;
        } else {
            updateLatestUploadTime();
            return true;
        }
    }

    public void updateLatestUploadTime() {
        this.preferences_.edit().putInt(LATEST_UPLOAD, Statistics.currentTimestamp()).commit();
    }

    public synchronized void removeConnection(String str) {
        if (str != null) {
            if (str.length() > 0) {
                Collection arrayList = new ArrayList(Arrays.asList(connections()));
                if (arrayList.remove(str)) {
                    this.preferences_.edit().putString(CONNECTIONS_PREFERENCE, join(arrayList, DELIMITER)).commit();
                }
            }
        }
    }

    void addEvent(Event event) {
        Collection eventsList = eventsList();
        eventsList.add(event);
        this.preferences_.edit().putString(EVENTS_PREFERENCE, joinEvents(eventsList, DELIMITER)).commit();
    }

    void setLocation(double d, double d2) {
        this.preferences_.edit().putString(LOCATION_PREFERENCE, d + "," + d2).commit();
    }

    String getAndRemoveLocation() {
        String string = this.preferences_.getString(LOCATION_PREFERENCE, "");
        if (!string.equals("")) {
            this.preferences_.edit().remove(LOCATION_PREFERENCE).commit();
        }
        return string;
    }

    public synchronized void addEvent(String str, Map<String, String> map, int i, int i2, double d) {
        Event event = new Event();
        event.key = str;
        event.segmentation = map;
        event.timestamp = i;
        event.count = i2;
        event.sum = d;
        addEvent(event);
    }

    public synchronized void removeEvents(Collection<Event> collection) {
        if (collection != null) {
            if (collection.size() > 0) {
                Collection eventsList = eventsList();
                if (eventsList.removeAll(collection)) {
                    this.preferences_.edit().putString(EVENTS_PREFERENCE, joinEvents(eventsList, DELIMITER)).commit();
                }
            }
        }
    }

    static String joinEvents(Collection<Event> collection, String str) {
        Collection arrayList = new ArrayList();
        for (Event toJSON : collection) {
            arrayList.add(toJSON.toJSON().toString());
        }
        return join(arrayList, str);
    }

    static String join(Collection<String> collection, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (String append : collection) {
            stringBuilder.append(append);
            int i2 = i + 1;
            if (i2 < collection.size()) {
                stringBuilder.append(str);
            }
            i = i2;
        }
        return stringBuilder.toString();
    }

    public synchronized String getPreference(String str) {
        return this.preferences_.getString(str, null);
    }

    public synchronized void setPreference(String str, String str2) {
        if (str2 == null) {
            this.preferences_.edit().remove(str).commit();
        } else {
            this.preferences_.edit().putString(str, str2).commit();
        }
    }

    synchronized void clear() {
        Editor edit = this.preferences_.edit();
        edit.remove(EVENTS_PREFERENCE);
        edit.remove(CONNECTIONS_PREFERENCE);
        edit.commit();
    }
}
