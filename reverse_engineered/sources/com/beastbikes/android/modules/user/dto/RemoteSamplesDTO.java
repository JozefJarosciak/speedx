package com.beastbikes.android.modules.user.dto;

import com.alipay.sdk.packet.C0861d;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoteSamplesDTO implements Serializable {
    private static final long serialVersionUID = -8710840339271193514L;
    private String activityId;
    private JSONArray dataArray;
    private List<C2411a> datas = new ArrayList();
    private int sequence;
    private String userId;

    public RemoteSamplesDTO(JSONObject jSONObject) {
        this.activityId = jSONObject.optString("activityId");
        this.dataArray = jSONObject.optJSONArray(C0861d.f2139k);
        this.sequence = jSONObject.optInt("sequence");
        this.userId = jSONObject.optString("userId");
        int i = 0;
        while (i < this.dataArray.length()) {
            try {
                String string = this.dataArray.getString(i);
                if (!string.isEmpty()) {
                    C2411a c2411a = new C2411a(new JSONObject(string));
                    if (!(c2411a.m12229e() == 0.0d || c2411a.m12229e() == Double.MIN_VALUE)) {
                        this.datas.add(c2411a);
                    }
                    i++;
                } else {
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String str) {
        this.activityId = str;
    }

    public JSONArray getDataArray() {
        return this.dataArray;
    }

    public void setDataArray(JSONArray jSONArray) {
        this.dataArray = jSONArray;
    }

    public int getSequence() {
        return this.sequence;
    }

    public void setSequence(int i) {
        this.sequence = i;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public List<C2411a> getDatas() {
        return this.datas;
    }

    public void setDatas(List<C2411a> list) {
        this.datas = list;
    }
}
