package io.rong.imlib.statistics;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;

public class EventQueue {
    private final StatisticsStore statisticsStore_;

    EventQueue(StatisticsStore statisticsStore) {
        this.statisticsStore_ = statisticsStore;
    }

    int size() {
        return this.statisticsStore_.events().length;
    }

    String events() {
        Collection<Event> eventsList = this.statisticsStore_.eventsList();
        JSONArray jSONArray = new JSONArray();
        for (Event toJSON : eventsList) {
            jSONArray.put(toJSON.toJSON());
        }
        String jSONArray2 = jSONArray.toString();
        this.statisticsStore_.removeEvents(eventsList);
        try {
            jSONArray2 = URLEncoder.encode(jSONArray2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return jSONArray2;
    }

    void recordEvent(String str, Map<String, String> map, int i, double d) {
        this.statisticsStore_.addEvent(str, map, Statistics.currentTimestamp(), i, d);
    }

    StatisticsStore getCountlyStore() {
        return this.statisticsStore_;
    }
}
