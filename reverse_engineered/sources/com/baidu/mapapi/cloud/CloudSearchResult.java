package com.baidu.mapapi.cloud;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudSearchResult extends BaseSearchResult {
    public List<CloudPoiInfo> poiList;

    public void parseFromJSON(JSONObject jSONObject) throws JSONException {
        super.parseFromJSON(jSONObject);
        if (this.status == 0) {
            this.poiList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("contents");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        CloudPoiInfo cloudPoiInfo = new CloudPoiInfo();
                        cloudPoiInfo.m4030a(optJSONObject);
                        this.poiList.add(cloudPoiInfo);
                    }
                }
            }
        }
    }
}
