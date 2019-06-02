package io.rong.imlib.statistics;

import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

class Event {
    private static final String COUNT_KEY = "count";
    private static final String KEY_KEY = "key";
    private static final String SEGMENTATION_KEY = "segmentation";
    private static final String SUM_KEY = "sum";
    private static final String TIMESTAMP_KEY = "timestamp";
    public int count;
    public String key;
    public Map<String, String> segmentation;
    public double sum;
    public int timestamp;

    Event() {
    }

    JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", this.key);
            jSONObject.put(COUNT_KEY, this.count);
            jSONObject.put(TIMESTAMP_KEY, this.timestamp);
            if (this.segmentation != null) {
                jSONObject.put(SEGMENTATION_KEY, new JSONObject(this.segmentation));
            }
            jSONObject.put(SUM_KEY, this.sum);
        } catch (Throwable e) {
            if (Statistics.sharedInstance().isLoggingEnabled()) {
                Log.w("Statistics", "Got exception converting an Event to JSON", e);
            }
        }
        return jSONObject;
    }

    static Event fromJSON(JSONObject jSONObject) {
        Event event;
        Event event2 = new Event();
        try {
            if (!jSONObject.isNull("key")) {
                event2.key = jSONObject.getString("key");
            }
            event2.count = jSONObject.optInt(COUNT_KEY);
            event2.sum = jSONObject.optDouble(SUM_KEY, 0.0d);
            event2.timestamp = jSONObject.optInt(TIMESTAMP_KEY);
            if (!jSONObject.isNull(SEGMENTATION_KEY)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(SEGMENTATION_KEY);
                Map hashMap = new HashMap(jSONObject2.length());
                Iterator keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    if (!jSONObject2.isNull(str)) {
                        hashMap.put(str, jSONObject2.getString(str));
                    }
                }
                event2.segmentation = hashMap;
            }
            event = event2;
        } catch (Throwable e) {
            if (Statistics.sharedInstance().isLoggingEnabled()) {
                Log.w("Statistics", "Got exception converting JSON to an Event", e);
            }
            event = null;
        }
        if (event == null || event.key == null || event.key.length() <= 0) {
            return null;
        }
        return event;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        if (this.key == null) {
            if (event.key != null) {
                return false;
            }
        } else if (!this.key.equals(event.key)) {
            return false;
        }
        if (this.timestamp != event.timestamp) {
            return false;
        }
        if (this.segmentation == null) {
            if (event.segmentation != null) {
                return false;
            }
        } else if (!this.segmentation.equals(event.segmentation)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        int hashCode = (this.key != null ? this.key.hashCode() : 1) ^ (this.segmentation != null ? this.segmentation.hashCode() : 1);
        if (this.timestamp != 0) {
            i = this.timestamp;
        }
        return hashCode ^ i;
    }
}
