package com.beastbikes.android.modules.user.dto;

import ch.qos.logback.core.CoreConstants;
import java.io.Serializable;
import org.json.JSONObject;

public class MedalDTO implements Serializable {
    private int activityId;
    private String detail;
    private GiftDTO gift;
    private int giftId;
    private String giftName;
    private int giftSituation;
    private int giftType;
    private int id;
    private String lightText;
    private String lightUrl;
    private String name;
    private int rank;
    private int status;
    private int totalLight;
    private String unLightUrl;

    public MedalDTO(JSONObject jSONObject) {
        this.id = jSONObject.optInt("id");
        this.name = jSONObject.optString("name");
        this.lightUrl = jSONObject.optString("lightUrl");
        this.lightText = jSONObject.optString("lightText");
        this.detail = jSONObject.optString("detail");
        this.unLightUrl = jSONObject.optString("unLightUrl");
        this.giftType = jSONObject.optInt("giftType");
        this.giftSituation = jSONObject.optInt("giftSituation");
        this.status = jSONObject.optInt("status");
        this.rank = jSONObject.optInt("rank");
        this.giftId = jSONObject.optInt("giftId");
        this.totalLight = jSONObject.optInt("totalLight");
        this.giftName = jSONObject.optString("giftName");
        this.activityId = jSONObject.optInt("activityId");
        JSONObject optJSONObject = jSONObject.optJSONObject("gift");
        if (optJSONObject != null) {
            this.gift = new GiftDTO(optJSONObject);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getLightUrl() {
        return this.lightUrl;
    }

    public void setLightUrl(String str) {
        this.lightUrl = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getLightText() {
        return this.lightText;
    }

    public void setLightText(String str) {
        this.lightText = str;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String str) {
        this.detail = str;
    }

    public String getUnLightUrl() {
        return this.unLightUrl;
    }

    public void setUnLightUrl(String str) {
        this.unLightUrl = str;
    }

    public int getGiftType() {
        return this.giftType;
    }

    public void setGiftType(int i) {
        this.giftType = i;
    }

    public int getGiftSituation() {
        return this.giftSituation;
    }

    public void setGiftSituation(int i) {
        this.giftSituation = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int i) {
        this.rank = i;
    }

    public int getGiftId() {
        return this.giftId;
    }

    public void setGiftId(int i) {
        this.giftId = i;
    }

    public String getGiftName() {
        return this.giftName;
    }

    public void setGiftName(String str) {
        this.giftName = str;
    }

    public int getActivityId() {
        return this.activityId;
    }

    public void setActivityId(int i) {
        this.activityId = i;
    }

    public int getTotalLight() {
        return this.totalLight;
    }

    public void setTotalLight(int i) {
        this.totalLight = i;
    }

    public GiftDTO getGift() {
        return this.gift;
    }

    public void setGift(GiftDTO giftDTO) {
        this.gift = giftDTO;
    }

    public String toString() {
        return "MedalDTO{id=" + this.id + ", name='" + this.name + CoreConstants.SINGLE_QUOTE_CHAR + ", lightUrl='" + this.lightUrl + CoreConstants.SINGLE_QUOTE_CHAR + ", lightText='" + this.lightText + CoreConstants.SINGLE_QUOTE_CHAR + ", detail='" + this.detail + CoreConstants.SINGLE_QUOTE_CHAR + ", unLightUrl='" + this.unLightUrl + CoreConstants.SINGLE_QUOTE_CHAR + ", giftType='" + this.giftType + CoreConstants.SINGLE_QUOTE_CHAR + ", giftSituation='" + this.giftSituation + CoreConstants.SINGLE_QUOTE_CHAR + ", status=" + this.status + ", rank=" + this.rank + ", giftId=" + this.giftId + ", totalLight=" + this.totalLight + ", gift=" + this.gift + CoreConstants.CURLY_RIGHT;
    }
}
