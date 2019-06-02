package com.beastbikes.android.modules.cycling.route.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RouteDTO implements Serializable {
    private static final long serialVersionUID = -2149866648468981051L;
    private String areaCode;
    private String coverUrl;
    private String desc;
    private double destinationAltitude;
    private double destinationLatitude;
    private double destinationLongitude;
    private double difficultyCoefficient;
    private double distanceToMe;
    private String englishName;
    private String id;
    private boolean isFollowed;
    private boolean isUse;
    private String mapUrl;
    private String name;
    private List<RouteNodeDTO> nodes;
    private int numberOfFollowers;
    private double originAltitude;
    private double originLatitude;
    private double originLongitude;
    private double totalDistance;
    private double trafficCoefficient;
    private String userId;
    private double viewCoefficient;

    public RouteDTO(JSONObject jSONObject) {
        String[] split;
        this.name = jSONObject.optString("name");
        this.desc = jSONObject.optString("desc");
        this.areaCode = jSONObject.optString("cityId");
        this.totalDistance = jSONObject.optDouble("distance");
        this.numberOfFollowers = jSONObject.optInt("followers");
        this.difficultyCoefficient = jSONObject.optDouble("difficultyCoefficient");
        this.trafficCoefficient = jSONObject.optDouble("trafficCoefficient");
        this.viewCoefficient = jSONObject.optDouble("viewCoefficient");
        this.id = jSONObject.optString("id");
        this.englishName = jSONObject.optString("name_en");
        this.isFollowed = jSONObject.optBoolean("hasFollowed");
        this.coverUrl = jSONObject.optString("cover");
        this.mapUrl = jSONObject.optString("map");
        if (jSONObject.has("routeNodes")) {
            this.nodes = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("routeNodes");
            for (int i = 0; i < optJSONArray.length(); i++) {
                this.nodes.add(new RouteNodeDTO(optJSONArray.optJSONObject(i)));
            }
        }
        try {
            split = jSONObject.getString("origin").split("\\s*,\\s*");
            if (split.length > 0) {
                this.originLatitude = Double.parseDouble(split[1]);
            }
            if (split.length > 1) {
                this.originLongitude = Double.parseDouble(split[0]);
            }
            if (split.length > 2) {
                this.originAltitude = Double.parseDouble(split[2]);
            }
        } catch (JSONException e) {
        }
        try {
            split = jSONObject.getString("destination").split("\\s*,\\s*");
            if (split.length > 0) {
                this.destinationLatitude = Double.parseDouble(split[1]);
            }
            if (split.length > 1) {
                this.destinationLongitude = Double.parseDouble(split[0]);
            }
            if (split.length > 2) {
                this.destinationAltitude = Double.parseDouble(split[2]);
            }
        } catch (JSONException e2) {
        }
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public void setEnglishName(String str) {
        this.englishName = str;
    }

    public boolean isFollowed() {
        return this.isFollowed;
    }

    public void setFollowed(boolean z) {
        this.isFollowed = z;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAreaCode() {
        return this.areaCode;
    }

    public void setAreaCode(String str) {
        this.areaCode = str;
    }

    public String getDescription() {
        return this.desc;
    }

    public void setDescription(String str) {
        this.desc = str;
    }

    public double getDifficultyCoefficient() {
        return this.difficultyCoefficient;
    }

    public void setDifficultyCoefficient(double d) {
        this.difficultyCoefficient = d;
    }

    public double getTrafficCoefficient() {
        return this.trafficCoefficient;
    }

    public void setTrafficCoefficient(double d) {
        this.trafficCoefficient = d;
    }

    public double getViewCoefficient() {
        return this.viewCoefficient;
    }

    public void setViewCoefficient(double d) {
        this.viewCoefficient = d;
    }

    public double getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(double d) {
        this.totalDistance = d;
    }

    public double getDistanceToMe() {
        return this.distanceToMe;
    }

    public void setDistanceToMe(double d) {
        this.distanceToMe = d;
    }

    public String getCoverURL() {
        return this.coverUrl;
    }

    public void setCoverURL(String str) {
        this.coverUrl = str;
    }

    public String getMapURL() {
        return this.mapUrl;
    }

    public void setMapURL(String str) {
        this.mapUrl = str;
    }

    public int getNumberOfFollowers() {
        return this.numberOfFollowers;
    }

    public void setNumberOfFollowers(int i) {
        this.numberOfFollowers = i;
    }

    public double getOriginAltitude() {
        return this.originAltitude;
    }

    public void setOriginAltitude(double d) {
        this.originAltitude = d;
    }

    public double getOriginLatitude() {
        return this.originLatitude;
    }

    public void setOriginLatitude(double d) {
        this.originLatitude = d;
    }

    public double getOriginLongitude() {
        return this.originLongitude;
    }

    public void setOriginLongitude(double d) {
        this.originLongitude = d;
    }

    public double getDestinationAltitude() {
        return this.destinationAltitude;
    }

    public void setDestinationAltitude(double d) {
        this.destinationAltitude = d;
    }

    public double getDestinationLatitude() {
        return this.destinationLatitude;
    }

    public void setDestinationLatitude(double d) {
        this.destinationLatitude = d;
    }

    public double getDestinationLongitude() {
        return this.destinationLongitude;
    }

    public void setDestinationLongitude(double d) {
        this.destinationLongitude = d;
    }

    public boolean isUse() {
        return this.isUse;
    }

    public void setUse(boolean z) {
        this.isUse = z;
    }

    public List<RouteNodeDTO> getNodes() {
        return this.nodes;
    }

    public void setNodes(List<RouteNodeDTO> list) {
        this.nodes = list;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String toString() {
        return "RouteDTO [id=" + this.id + ", name=" + this.name + ", englishName=" + this.englishName + ", desc=" + this.desc + ", areaCode=" + this.areaCode + ", difficultyCoefficient=" + this.difficultyCoefficient + ", trafficCoefficient=" + this.trafficCoefficient + ", viewCoefficient=" + this.viewCoefficient + ", totalDistance=" + this.totalDistance + ", distanceToMe=" + this.distanceToMe + ", coverUrl=" + this.coverUrl + ", mapUrl=" + this.mapUrl + ", numberOfFollowers=" + this.numberOfFollowers + ", originAltitude=" + this.originAltitude + ", originLatitude=" + this.originLatitude + ", originLongitude=" + this.originLongitude + ", destinationAltitude=" + this.destinationAltitude + ", destinationLatitude=" + this.destinationLatitude + ", destinationLongitude=" + this.destinationLongitude + ", isFollowed=" + this.isFollowed + "]";
    }
}
