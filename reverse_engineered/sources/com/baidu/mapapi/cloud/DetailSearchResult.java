package com.baidu.mapapi.cloud;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailSearchResult extends BaseSearchResult {
    public CloudPoiInfo poiInfo;

    public void parseFromJSON(JSONObject jSONObject) throws JSONException {
        super.parseFromJSON(jSONObject);
        if (this.status == 0) {
            JSONArray optJSONArray = jSONObject.optJSONArray("contents");
            if (optJSONArray != null) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(0);
                if (optJSONObject != null) {
                    this.poiInfo = new CloudPoiInfo();
                    this.poiInfo.m4030a(optJSONObject);
                }
            }
        }
    }
}
