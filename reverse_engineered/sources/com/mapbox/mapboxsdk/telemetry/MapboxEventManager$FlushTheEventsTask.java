package com.mapbox.mapboxsdk.telemetry;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.utils.MathUtils;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import okhttp3.C5473g.C5471a;
import okhttp3.C5602x;
import okhttp3.C5614u.C5613a;
import okhttp3.C5621w.C5620a;
import okhttp3.C5627y;
import org.json.JSONArray;
import org.json.JSONObject;

class MapboxEventManager$FlushTheEventsTask extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ MapboxEventManager this$0;

    private MapboxEventManager$FlushTheEventsTask(MapboxEventManager mapboxEventManager) {
        this.this$0 = mapboxEventManager;
    }

    protected Void doInBackground(Void... voidArr) {
        C5627y b;
        Exception e;
        Throwable th;
        if (MapboxEventManager.access$300(this.this$0).isEmpty()) {
            Log.d("MapboxEventManager", "No events in the queue to send so returning.");
            return null;
        } else if (MapboxAccountManager.getInstance().isConnected().booleanValue()) {
            C5627y c5627y = null;
            try {
                JSONArray jSONArray = new JSONArray();
                Iterator it = ((Vector) MapboxEventManager.access$300(this.this$0).clone()).iterator();
                while (it.hasNext()) {
                    String str;
                    Hashtable hashtable = (Hashtable) it.next();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.putOpt("event", hashtable.get("event"));
                    jSONObject.putOpt(MapboxEvent.ATTRIBUTE_CREATED, hashtable.get(MapboxEvent.ATTRIBUTE_CREATED));
                    jSONObject.putOpt("userId", hashtable.get("userId"));
                    jSONObject.putOpt(MapboxEvent.ATTRIBUTE_ENABLED_TELEMETRY, hashtable.get(MapboxEvent.ATTRIBUTE_ENABLED_TELEMETRY));
                    jSONObject.putOpt(MapboxEvent.ATTRIBUTE_SOURCE, hashtable.get(MapboxEvent.ATTRIBUTE_SOURCE));
                    jSONObject.putOpt(MapboxEvent.ATTRIBUTE_SESSION_ID, hashtable.get(MapboxEvent.ATTRIBUTE_SESSION_ID));
                    jSONObject.putOpt(MapboxEvent.KEY_LATITUDE, hashtable.get(MapboxEvent.KEY_LATITUDE));
                    if (hashtable.containsKey(MapboxEvent.KEY_LONGITUDE)) {
                        double doubleValue = ((Double) hashtable.get(MapboxEvent.KEY_LONGITUDE)).doubleValue();
                        if (doubleValue < -180.0d || doubleValue > 180.0d) {
                            doubleValue = MathUtils.wrap(doubleValue, -180.0d, 180.0d);
                        }
                        jSONObject.put(MapboxEvent.KEY_LONGITUDE, doubleValue);
                    }
                    jSONObject.putOpt(MapboxEvent.KEY_ALTITUDE, hashtable.get(MapboxEvent.KEY_ALTITUDE));
                    jSONObject.putOpt(MapboxEvent.KEY_ZOOM, hashtable.get(MapboxEvent.KEY_ZOOM));
                    jSONObject.putOpt(MapboxEvent.ATTRIBUTE_OPERATING_SYSTEM, hashtable.get(MapboxEvent.ATTRIBUTE_OPERATING_SYSTEM));
                    jSONObject.putOpt("userId", hashtable.get("userId"));
                    jSONObject.putOpt(MapboxEvent.ATTRIBUTE_MODEL, hashtable.get(MapboxEvent.ATTRIBUTE_MODEL));
                    jSONObject.putOpt(MapboxEvent.ATTRIBUTE_RESOLUTION, hashtable.get(MapboxEvent.ATTRIBUTE_RESOLUTION));
                    jSONObject.putOpt(MapboxEvent.ATTRIBUTE_ACCESSIBILITY_FONT_SCALE, hashtable.get(MapboxEvent.ATTRIBUTE_ACCESSIBILITY_FONT_SCALE));
                    jSONObject.putOpt(MapboxEvent.ATTRIBUTE_BATTERY_LEVEL, hashtable.get(MapboxEvent.ATTRIBUTE_BATTERY_LEVEL));
                    jSONObject.putOpt(MapboxEvent.ATTRIBUTE_PLUGGED_IN, hashtable.get(MapboxEvent.ATTRIBUTE_PLUGGED_IN));
                    jSONObject.putOpt(MapboxEvent.ATTRIBUTE_WIFI, hashtable.get(MapboxEvent.ATTRIBUTE_WIFI));
                    if (hashtable.containsKey(MapboxEvent.ATTRIBUTE_ORIENTATION)) {
                        str = (String) hashtable.get(MapboxEvent.ATTRIBUTE_ORIENTATION);
                        if (!TextUtils.isEmpty(str)) {
                            jSONObject.putOpt(MapboxEvent.ATTRIBUTE_ORIENTATION, str);
                        }
                    }
                    if (hashtable.containsKey(MapboxEvent.ATTRIBUTE_CARRIER)) {
                        str = (String) hashtable.get(MapboxEvent.ATTRIBUTE_CARRIER);
                        if (!TextUtils.isEmpty(str)) {
                            jSONObject.putOpt(MapboxEvent.ATTRIBUTE_CARRIER, str);
                        }
                    }
                    if (hashtable.containsKey(MapboxEvent.ATTRIBUTE_APPLICATION_STATE) && !TextUtils.isEmpty((String) hashtable.get(MapboxEvent.ATTRIBUTE_APPLICATION_STATE))) {
                        jSONObject.putOpt(MapboxEvent.ATTRIBUTE_APPLICATION_STATE, hashtable.get(MapboxEvent.ATTRIBUTE_APPLICATION_STATE));
                    }
                    str = (String) hashtable.get("event");
                    if (!TextUtils.isEmpty(str) && str.equalsIgnoreCase(MapboxEvent.TYPE_MAP_CLICK)) {
                        jSONObject.put(MapboxEvent.KEY_GESTURE_ID, hashtable.get(MapboxEvent.KEY_GESTURE_ID));
                    }
                    if (hashtable.containsKey(MapboxEvent.ATTRIBUTE_CELLULAR_NETWORK_TYPE)) {
                        if (TextUtils.isEmpty((String) hashtable.get(MapboxEvent.ATTRIBUTE_CELLULAR_NETWORK_TYPE))) {
                            jSONObject.put(MapboxEvent.ATTRIBUTE_CELLULAR_NETWORK_TYPE, null);
                        } else {
                            jSONObject.put(MapboxEvent.ATTRIBUTE_CELLULAR_NETWORK_TYPE, hashtable.get(MapboxEvent.ATTRIBUTE_CELLULAR_NETWORK_TYPE));
                        }
                    }
                    jSONArray.put(jSONObject);
                }
                C5471a c5471a = new C5471a();
                String[] strArr;
                if (MapboxEventManager.access$400(this.this$0)) {
                    strArr = new String[]{"sha256/5kJvNEMw0KjrCAu7eXY5HZdvyCS13BbA0VJG1RSP91w="};
                    strArr = new String[]{"sha256/r/mIkG3eEpVdm+u/ko/cwxzOMo1bk4TyHIlByibiA5E="};
                    c5471a.a("cloudfront-staging.tilestream.net", new String[]{"sha256/3euxrJOrEZI15R4104UsiAkDqe007EPyZ6eTL/XxdAY="}).a("cloudfront-staging.tilestream.net", strArr).a("cloudfront-staging.tilestream.net", strArr);
                } else {
                    strArr = new String[]{"sha256/owrR9U9FWDWtrFF+myoRIu75JwU4sJwzvhCNLZoY37g="};
                    strArr = new String[]{"sha256/SQVGZiOrQXi+kqxcvWWE96HhfydlLVqFr4lQTqI5qqo="};
                    strArr = new String[]{"sha256/Tb0uHZ/KQjWh8N9+CZFLc4zx36LONQ55l6laDi1qtT4="};
                    strArr = new String[]{"sha256/RRM1dGqnDFsCJXBTHky16vi1obOlCgFFn/yOhI/y+ho="};
                    strArr = new String[]{"sha256/WoiWRyIOVNa9ihaBciRSC7XHjliYS9VwUGOIud4PB18="};
                    c5471a.a("events.mapbox.com", new String[]{"sha256/BhynraKizavqoC5U26qgYuxLZst6pCu9J5stfL6RSYY="}).a("events.mapbox.com", strArr).a("events.mapbox.com", strArr).a("events.mapbox.com", strArr).a("events.mapbox.com", strArr).a("events.mapbox.com", strArr);
                }
                b = new C5613a().a(c5471a.a()).a(new GzipRequestInterceptor()).b().a(new C5620a().a(MapboxEventManager.access$600(this.this$0) + "/events/v2?access_token=" + MapboxEventManager.access$700(this.this$0)).a("User-Agent", MapboxEventManager.access$800(this.this$0)).a(C5602x.create(MapboxEventManager.access$500(), jSONArray.toString())).a()).b();
                try {
                    Log.d("MapboxEventManager", "response code = " + b.b() + " for events " + MapboxEventManager.access$300(this.this$0).size());
                    if (!(b == null || b.g() == null)) {
                        b.g().close();
                    }
                    MapboxEventManager.access$300(this.this$0).removeAllElements();
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Log.e("MapboxEventManager", "FlushTheEventsTask borked: " + e);
                        e.printStackTrace();
                        if (!(b == null || b.g() == null)) {
                            b.g().close();
                        }
                        MapboxEventManager.access$300(this.this$0).removeAllElements();
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        c5627y = b;
                        if (!(c5627y == null || c5627y.g() == null)) {
                            c5627y.g().close();
                        }
                        MapboxEventManager.access$300(this.this$0).removeAllElements();
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                b = null;
            } catch (Throwable th3) {
                th = th3;
            }
            return null;
        } else {
            Log.w("MapboxEventManager", "Not connected to network, so empty events cache and return without attempting to send events");
            MapboxEventManager.access$300(this.this$0).removeAllElements();
            return null;
        }
    }
}
