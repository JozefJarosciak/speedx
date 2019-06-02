package com.beastbikes.android.modules.cycling.route.p068a;

import android.app.Activity;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.cycling.route.dto.C2187a;
import com.beastbikes.android.modules.cycling.route.dto.C2190d;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.dto.RouteNodeDTO;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: RouteManager */
/* renamed from: com.beastbikes.android.modules.cycling.route.a.a */
public class C2185a extends C1397a {
    /* renamed from: a */
    private static final Logger f10249a = LoggerFactory.getLogger(C2185a.class);
    /* renamed from: b */
    private C1418b f10250b;
    /* renamed from: c */
    private Activity f10251c;

    public C2185a(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f10251c = activity;
        this.f10250b = (C1418b) new C1772d(activity).m9399a(C1418b.class, C1768c.f8075a, C1768c.m9391a(activity));
    }

    /* renamed from: a */
    public List<C2187a> m11195a() throws BusinessException {
        List<C2187a> list = null;
        try {
            JSONObject a = this.f10250b.a();
            if (a != null) {
                if (a.optInt("code") == 0) {
                    JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                    list = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        list.add(new C2187a(optJSONArray.optJSONObject(i)));
                    }
                } else {
                    String optString = a.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f10251c, optString);
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public List<RouteDTO> m11196a(String str) throws BusinessException {
        List<RouteDTO> list = null;
        try {
            JSONObject a = this.f10250b.a(str);
            if (a != null) {
                if (a.optInt("code") == 0) {
                    JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                    list = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        list.add(new RouteDTO(optJSONArray.optJSONObject(i)));
                    }
                } else {
                    String optString = a.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f10251c, optString);
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: b */
    public int m11200b(String str) throws BusinessException {
        try {
            JSONObject b = this.f10250b.b(str);
            if (b == null) {
                return -1;
            }
            if (b.optInt("code") == 0) {
                return b.optInt(C0882j.f2229c);
            }
            String optString = b.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return -1;
            }
            Toasts.showOnUiThread(this.f10251c, optString);
            return -1;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public List<C2190d> m11197a(String str, int i, int i2) throws BusinessException {
        List<C2190d> list = null;
        try {
            JSONObject a = this.f10250b.a(str, i2, i);
            if (a != null) {
                if (a.optInt("code") == 0) {
                    JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
                    list = new ArrayList();
                    JSONArray optJSONArray = optJSONObject.optJSONArray("routeComments");
                    for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                        C2190d c2190d = new C2190d(optJSONArray.optJSONObject(i3));
                        c2190d.m11222a(optJSONObject.optInt("routeCommentsCount"));
                        list.add(c2190d);
                    }
                } else {
                    String optString = a.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f10251c, optString);
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public int m11194a(String str, String str2, String str3) throws BusinessException {
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        try {
            JSONObject a = this.f10250b.a(str, str2, str3);
            if (a == null) {
                return -1;
            }
            if (a.optInt("code") == 0) {
                return a.optInt("count");
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return -1;
            }
            Toasts.showOnUiThread(this.f10251c, optString);
            return -1;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: c */
    public List<String> m11203c(String str) throws BusinessException {
        List<String> list = null;
        try {
            JSONObject d = this.f10250b.d(str);
            if (d != null) {
                if (d.optInt("code") == 0) {
                    list = new ArrayList();
                    JSONArray optJSONArray = d.optJSONArray(C0882j.f2229c);
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        list.add(optJSONArray.optJSONObject(i).getString("photoUrl"));
                    }
                } else {
                    String optString = d.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f10251c, optString);
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: b */
    public List<RouteDTO> m11201b() throws BusinessException {
        List<RouteDTO> list = null;
        try {
            JSONObject a = this.f10250b.a(1, 500);
            if (a != null) {
                if (a.optInt("code") == 0) {
                    JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                    list = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        list.add(new RouteDTO(optJSONArray.optJSONObject(i)));
                    }
                } else {
                    String optString = a.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f10251c, optString);
                    }
                }
            }
            return list;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: d */
    public RouteDTO m11204d(String str) throws BusinessException {
        RouteDTO routeDTO = null;
        try {
            JSONObject c = this.f10250b.c(str);
            if (c != null) {
                if (c.optInt("code") == 0) {
                    routeDTO = new RouteDTO(c.optJSONObject(C0882j.f2229c));
                } else {
                    String optString = c.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(optString)) {
                        Toasts.showOnUiThread(this.f10251c, optString);
                    }
                }
            }
            return routeDTO;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: e */
    public boolean m11205e(String str) throws BusinessException {
        try {
            JSONObject e = this.f10250b.e(str);
            if (e == null) {
                return false;
            }
            if (e.optInt("code") == 0) {
                Toasts.showOnUiThread(this.f10251c, this.f10251c.getResources().getString(C1373R.string.deleteroutesuccess));
                return e.optBoolean(C0882j.f2229c);
            }
            String optString = e.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            Toasts.showOnUiThread(this.f10251c, optString);
            return false;
        } catch (Throwable e2) {
            throw new BusinessException(e2);
        }
    }

    /* renamed from: f */
    public JSONObject m11206f(String str) throws BusinessException {
        JSONObject jSONObject = null;
        try {
            JSONObject f = this.f10250b.f(str);
            if (f != null) {
                String optString = f.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f10251c, optString);
                }
                if (f.optInt("code") == 0) {
                    jSONObject = f.optJSONObject(C0882j.f2229c);
                }
            }
            return jSONObject;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public boolean m11199a(String str, String str2) throws BusinessException {
        try {
            JSONObject a = this.f10250b.a(str, str2);
            if (a == null) {
                return false;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f10251c, optString);
            }
            return a.optBoolean(C0882j.f2229c);
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public boolean m11198a(RouteDTO routeDTO, List<RouteNodeDTO> list, String str) throws BusinessException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(routeDTO.getOriginLongitude())).append(",").append(String.valueOf(routeDTO.getOriginLatitude())).append(",").append(String.valueOf(routeDTO.getOriginAltitude()));
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(String.valueOf(routeDTO.getDestinationLongitude())).append(",").append(String.valueOf(routeDTO.getDestinationLatitude())).append(",").append(String.valueOf(routeDTO.getDestinationAltitude()));
        JSONArray jSONArray = new JSONArray();
        for (RouteNodeDTO routeNodeDTO : list) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("keyNode", routeNodeDTO.getKeyNode());
            } catch (Throwable e) {
                f10249a.error("Set route node key_node err", e);
            }
            try {
                jSONObject.put("name", routeNodeDTO.getName());
            } catch (Throwable e2) {
                f10249a.error("Set route node name err", e2);
            }
            try {
                jSONObject.put(MapboxEvent.KEY_LONGITUDE, routeNodeDTO.getLongitude());
            } catch (Throwable e22) {
                f10249a.error("Set route node longitude err", e22);
            }
            try {
                jSONObject.put(MapboxEvent.KEY_LATITUDE, routeNodeDTO.getLatitude());
            } catch (Throwable e222) {
                f10249a.error("Set route node latitude err", e222);
            }
            try {
                jSONObject.put("ordinal", routeNodeDTO.getOrdinal());
            } catch (Throwable e2222) {
                f10249a.error("Set route node ordinal err", e2222);
            }
            try {
                jSONObject.put("coordinate", routeNodeDTO.getCoordinate());
            } catch (Throwable e22222) {
                f10249a.error("Set route node coordinate err", e22222);
            }
            try {
                jSONObject.put(MapboxEvent.KEY_ALTITUDE, routeNodeDTO.getAltitude());
            } catch (Throwable e3) {
                f10249a.error("Set route node altitude err", e3);
            }
            jSONArray.put(jSONObject);
        }
        try {
            JSONObject a = this.f10250b.a(routeDTO.getId(), routeDTO.getName(), stringBuilder.toString(), stringBuilder2.toString(), routeDTO.getTotalDistance(), str, jSONArray.toString());
            if (a == null) {
                return false;
            }
            if (a.optInt("code") == 0) {
                return a.optBoolean(C0882j.f2229c);
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f10251c, optString);
            }
            return false;
        } catch (Throwable e32) {
            throw new BusinessException(e32);
        }
    }

    /* renamed from: b */
    public boolean m11202b(RouteDTO routeDTO, List<RouteNodeDTO> list, String str) throws BusinessException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(routeDTO.getOriginLongitude())).append(",").append(String.valueOf(routeDTO.getOriginLatitude())).append(",").append(String.valueOf(routeDTO.getOriginAltitude()));
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(String.valueOf(routeDTO.getDestinationLongitude())).append(",").append(String.valueOf(routeDTO.getDestinationLatitude())).append(",").append(String.valueOf(routeDTO.getDestinationAltitude()));
        JSONArray jSONArray = new JSONArray();
        for (RouteNodeDTO routeNodeDTO : list) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("keyNode", routeNodeDTO.getKeyNode());
            } catch (Throwable e) {
                f10249a.error("Set route node key_node err", e);
            }
            try {
                jSONObject.put("name", routeNodeDTO.getName());
            } catch (Throwable e2) {
                f10249a.error("Set route node name err", e2);
            }
            try {
                jSONObject.put(MapboxEvent.KEY_LONGITUDE, routeNodeDTO.getLongitude());
            } catch (Throwable e22) {
                f10249a.error("Set route node longitude err", e22);
            }
            try {
                jSONObject.put(MapboxEvent.KEY_LATITUDE, routeNodeDTO.getLatitude());
            } catch (Throwable e222) {
                f10249a.error("Set route node latitude err", e222);
            }
            try {
                jSONObject.put("ordinal", routeNodeDTO.getOrdinal());
            } catch (Throwable e2222) {
                f10249a.error("Set route node ordinal err", e2222);
            }
            try {
                jSONObject.put("coordinate", routeNodeDTO.getCoordinate());
            } catch (Throwable e22222) {
                f10249a.error("Set route node coordinate err", e22222);
            }
            try {
                jSONObject.put(MapboxEvent.KEY_ALTITUDE, routeNodeDTO.getAltitude());
            } catch (Throwable e3) {
                f10249a.error("Set route node altitude err", e3);
            }
            jSONArray.put(jSONObject);
        }
        try {
            JSONObject a = this.f10250b.a(routeDTO.getName(), stringBuilder.toString(), stringBuilder2.toString(), routeDTO.getTotalDistance(), str, jSONArray.toString());
            if (a == null) {
                return false;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (a.optInt("code") == 0) {
                Toasts.showOnUiThread(this.f10251c, this.f10251c.getResources().getString(C1373R.string.uploadmapsuccessed));
            } else if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f10251c, optString);
            }
            return a.optBoolean(C0882j.f2229c);
        } catch (Throwable e32) {
            throw new BusinessException(e32);
        }
    }
}
