package com.beastbikes.android.modules.cycling.stage.dto;

import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class StageDTO implements Serializable {
    private int collectId;
    private double distance;
    private double elevationDiff;
    private String endName;
    private int level;
    private int participateNum;
    private List<StagePointDTO> points = new ArrayList();
    private double slope;
    private int stageId;
    private String stageName;
    private String startName;
    private StagePointDTO startPoint;

    public StageDTO(JSONObject jSONObject) {
        this.stageId = jSONObject.optInt("id");
        this.stageName = jSONObject.optString("name");
        this.distance = jSONObject.optDouble("distance");
        this.level = jSONObject.optInt("level");
        this.elevationDiff = jSONObject.optDouble(MapboxEvent.KEY_ALTITUDE);
        this.slope = jSONObject.optDouble("slope") * 100.0d;
        this.participateNum = jSONObject.optInt("join_user_count");
        this.collectId = jSONObject.optInt("collect_id");
        this.startName = jSONObject.optString("origin_name");
        this.endName = jSONObject.optString("destination_name");
        JSONArray optJSONArray = jSONObject.optJSONArray("get_origin");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            this.startPoint = new StagePointDTO(optJSONArray.optDouble(0), optJSONArray.optDouble(1));
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("samples");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            for (int i = 0; i < optJSONArray2.length(); i++) {
                JSONArray optJSONArray3 = optJSONArray2.optJSONArray(i);
                if (optJSONArray3 != null && optJSONArray3.length() > 1) {
                    this.points.add(new StagePointDTO(optJSONArray3.optDouble(0), optJSONArray3.optDouble(1)));
                }
            }
        }
    }

    public int getStageId() {
        return this.stageId;
    }

    public void setStageId(int i) {
        this.stageId = i;
    }

    public String getStageName() {
        return this.stageName;
    }

    public void setStageName(String str) {
        this.stageName = str;
    }

    public String getStartName() {
        return this.startName;
    }

    public void setStartName(String str) {
        this.startName = str;
    }

    public String getEndName() {
        return this.endName;
    }

    public void setEndName(String str) {
        this.endName = str;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public double getElevationDiff() {
        return this.elevationDiff;
    }

    public void setElevationDiff(double d) {
        this.elevationDiff = d;
    }

    public double getSlope() {
        return this.slope;
    }

    public void setSlope(double d) {
        this.slope = d;
    }

    public int getParticipateNum() {
        return this.participateNum;
    }

    public void setParticipateNum(int i) {
        this.participateNum = i;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getCollectId() {
        return this.collectId;
    }

    public void setCollectId(int i) {
        this.collectId = i;
    }

    public StagePointDTO getStartPoint() {
        return this.startPoint;
    }

    public void setStartPoint(StagePointDTO stagePointDTO) {
        this.startPoint = stagePointDTO;
    }

    public List<StagePointDTO> getPoints() {
        return this.points;
    }

    public void setPoints(List<StagePointDTO> list) {
        this.points = list;
    }
}
