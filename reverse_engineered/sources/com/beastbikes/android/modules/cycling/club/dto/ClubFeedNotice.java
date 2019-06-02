package com.beastbikes.android.modules.cycling.club.dto;

import java.util.Date;
import org.json.JSONObject;

public class ClubFeedNotice extends ClubFeedBase {
    public ClubFeedNotice(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.clubId = jSONObject.optString("clubId");
            setText(jSONObject.optString("content"));
            setDate(new Date((long) jSONObject.optInt("timestamp")));
        }
    }
}
