package com.baidu.platform.core.p046a;

import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.district.DistrictResult;
import com.baidu.platform.base.C1212e;
import com.mapbox.mapboxsdk.style.layers.Property;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.a.b */
public class C1294b extends C1212e {
    /* renamed from: c */
    boolean f3944c = false;
    /* renamed from: d */
    String f3945d = null;

    /* renamed from: a */
    private boolean m4974a(String str, DistrictResult districtResult) {
        if (str == null || "".equals(str) || districtResult == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject == null) {
                return false;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            jSONObject = jSONObject.optJSONObject("city_result");
            if (optJSONObject == null || jSONObject == null) {
                return false;
            }
            if (optJSONObject.optInt("error") != 0) {
                return false;
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("content");
            if (optJSONObject2 == null) {
                return false;
            }
            jSONObject = optJSONObject2.optJSONObject("sgeo");
            if (jSONObject != null) {
                JSONArray optJSONArray = jSONObject.optJSONArray("geo_elements");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    List arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray(Property.SYMBOL_PLACEMENT_POINT);
                            if (optJSONArray2 != null) {
                                int length = optJSONArray2.length();
                                if (length > 0) {
                                    List arrayList2 = new ArrayList();
                                    int i2 = 0;
                                    int i3 = 0;
                                    for (int i4 = 0; i4 < length; i4++) {
                                        int optInt = optJSONArray2.optInt(i4);
                                        if (i4 % 2 == 0) {
                                            i3 += optInt;
                                        } else {
                                            i2 += optInt;
                                            arrayList2.add(CoordUtil.mc2ll(new GeoPoint((double) i2, (double) i3)));
                                        }
                                    }
                                    arrayList.add(arrayList2);
                                }
                            }
                        }
                    }
                    if (arrayList.size() > 0) {
                        districtResult.setPolylines(arrayList);
                        districtResult.setCenterPt(CoordUtil.decodeLocation(optJSONObject2.optString("geo")));
                        districtResult.setCityCode(optJSONObject2.optInt("code"));
                        districtResult.setCityName(optJSONObject2.optString("cname"));
                        districtResult.error = ERRORNO.NO_ERROR;
                        return true;
                    }
                }
            }
            districtResult.setCityName(optJSONObject2.optString("uid"));
            this.f3945d = optJSONObject2.optString("cname");
            districtResult.setCenterPt(CoordUtil.decodeLocation(optJSONObject2.optString("geo")));
            districtResult.setCityCode(optJSONObject2.optInt("code"));
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    private List<List<LatLng>> m4975b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String[] split = str.split("\\|");
        if (split.length < 3) {
            return null;
        }
        String[] split2 = split[2].split("\\;");
        if (split2 == null || split2.length == 0) {
            return null;
        }
        List<List<LatLng>> arrayList = new ArrayList();
        for (String split3 : split2) {
            String[] split4 = split3.split("\\,");
            List arrayList2 = new ArrayList();
            for (int i = 0; i < split4.length; i += 2) {
                try {
                    arrayList2.add(CoordUtil.mc2ll(new GeoPoint(Double.valueOf(split4[i + 1]).doubleValue(), Double.valueOf(split4[i]).doubleValue())));
                } catch (Exception e) {
                }
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    /* renamed from: b */
    private boolean m4976b(String str, DistrictResult districtResult) {
        if (str == null || str.equals("") || districtResult == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject == null) {
                return false;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            jSONObject = jSONObject.optJSONObject("content");
            if (optJSONObject == null || jSONObject == null || optJSONObject.optInt("error") != 0) {
                return false;
            }
            List b;
            List arrayList;
            List arrayList2 = new ArrayList();
            if (this.f3945d != null) {
                if ((this.f3945d.indexOf("福建") > -1 || this.f3945d.indexOf("浙江") > -1) && this.f3945d.length() <= 3) {
                    b = m4975b(jSONObject.optString("geo"));
                    if (r0 != null) {
                        for (List<LatLng> list : r0) {
                            arrayList = new ArrayList();
                            for (LatLng add : list) {
                                arrayList.add(add);
                            }
                            arrayList2.add(arrayList);
                        }
                    }
                    if (arrayList2.size() > 0) {
                        districtResult.setPolylines(arrayList2);
                    }
                    districtResult.setCityName(this.f3945d);
                    districtResult.error = ERRORNO.NO_ERROR;
                    this.f3945d = null;
                    return true;
                }
                try {
                    b = CoordUtil.decodeLocationList2D(jSONObject.optString("geo"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (r0 != null) {
                    for (List<LatLng> list2 : r0) {
                        arrayList = new ArrayList();
                        while (r5.hasNext()) {
                            arrayList.add(add);
                        }
                        arrayList2.add(arrayList);
                    }
                }
                if (arrayList2.size() > 0) {
                    districtResult.setPolylines(arrayList2);
                }
                districtResult.setCityName(this.f3945d);
                districtResult.error = ERRORNO.NO_ERROR;
                this.f3945d = null;
                return true;
            }
            b = null;
            if (r0 != null) {
                for (List<LatLng> list22 : r0) {
                    arrayList = new ArrayList();
                    while (r5.hasNext()) {
                        arrayList.add(add);
                    }
                    arrayList2.add(arrayList);
                }
            }
            if (arrayList2.size() > 0) {
                districtResult.setPolylines(arrayList2);
            }
            districtResult.setCityName(this.f3945d);
            districtResult.error = ERRORNO.NO_ERROR;
            this.f3945d = null;
            return true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public void mo2683a(String str) {
        SearchResult districtResult = new DistrictResult();
        if (!m4539a(str, districtResult, false)) {
            if (this.f3944c) {
                m4976b(str, districtResult);
            } else if (!m4974a(str, districtResult)) {
                districtResult.error = ERRORNO.RESULT_NOT_FOUND;
            }
        }
        if (this.a != null) {
            this.a.mo2687a(districtResult);
        }
    }

    /* renamed from: b */
    public void m4978b() {
        this.f3944c = true;
    }
}
