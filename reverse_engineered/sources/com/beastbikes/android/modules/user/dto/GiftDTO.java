package com.beastbikes.android.modules.user.dto;

import com.avos.avoscloud.AVStatus;
import java.io.Serializable;
import org.json.JSONObject;

public class GiftDTO implements Serializable {
    private String detail;
    private int id;
    private String image;
    private String name;
    private int type;

    public GiftDTO(JSONObject jSONObject) {
        this.id = jSONObject.optInt("id");
        this.detail = jSONObject.optString("detail");
        this.image = jSONObject.optString(AVStatus.IMAGE_TAG);
        this.type = jSONObject.optInt("type");
        this.name = jSONObject.optString("name");
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String str) {
        this.detail = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
