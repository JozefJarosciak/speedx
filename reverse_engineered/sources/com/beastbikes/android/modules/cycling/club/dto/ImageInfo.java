package com.beastbikes.android.modules.cycling.club.dto;

import com.mapbox.mapboxsdk.style.layers.Property;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageInfo implements Serializable {
    private int height;
    private String id;
    private String mine;
    private String url;
    private int width;

    public ImageInfo(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.id = jSONObject.optString("id");
            this.url = jSONObject.optString("url");
            this.width = jSONObject.optInt(Property.ICON_TEXT_FIT_WIDTH);
            this.height = jSONObject.optInt(Property.ICON_TEXT_FIT_HEIGHT);
            this.mine = jSONObject.optString("mine");
        }
    }

    public JSONObject obj2Json() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.id);
            jSONObject.put("url", this.url);
            jSONObject.put(Property.ICON_TEXT_FIT_WIDTH, this.width);
            jSONObject.put(Property.ICON_TEXT_FIT_HEIGHT, this.height);
            jSONObject.put("mine", this.mine);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public String getMine() {
        return this.mine;
    }

    public void setMine(String str) {
        this.mine = str;
    }
}
