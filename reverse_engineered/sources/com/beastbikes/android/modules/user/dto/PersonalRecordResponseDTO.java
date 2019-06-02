package com.beastbikes.android.modules.user.dto;

import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class PersonalRecordResponseDTO implements Serializable {
    private ArrayList<Double> distanceList;
    private ArrayList<PersonalRecordDataDTO> personalRecordDataDTOs = new ArrayList();
    private ArrayList<Double> timeList;

    public PersonalRecordResponseDTO(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject != null) {
            int length;
            int i2;
            this.distanceList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("distance_info");
            if (optJSONArray != null) {
                length = optJSONArray.length();
                for (i2 = 0; i2 < length; i2++) {
                    this.distanceList.add(Double.valueOf(optJSONArray.optDouble(i2) / 1000.0d));
                }
            }
            this.timeList = new ArrayList();
            optJSONArray = jSONObject.optJSONArray("time_info");
            if (optJSONArray != null) {
                length = optJSONArray.length();
                for (i2 = 0; i2 < length; i2++) {
                    this.timeList.add(Double.valueOf(optJSONArray.optDouble(i2) / 3600.0d));
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("route_summary_info");
            if (optJSONArray2 != null) {
                int length2 = optJSONArray2.length();
                while (i < length2) {
                    this.personalRecordDataDTOs.add(new PersonalRecordDataDTO(optJSONArray2.optJSONObject(i)));
                    i++;
                }
            }
        }
    }

    public ArrayList<PersonalRecordDataDTO> getPersonalRecordDataDTOs() {
        return this.personalRecordDataDTOs;
    }

    public void setPersonalRecordDataDTOs(ArrayList<PersonalRecordDataDTO> arrayList) {
        this.personalRecordDataDTOs = arrayList;
    }

    public ArrayList<Double> getDistanceList() {
        return this.distanceList;
    }

    public void setDistanceList(ArrayList<Double> arrayList) {
        this.distanceList = arrayList;
    }

    public ArrayList<Double> getTimeList() {
        return this.timeList;
    }

    public void setTimeList(ArrayList<Double> arrayList) {
        this.timeList = arrayList;
    }
}
