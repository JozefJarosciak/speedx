package com.beastbikes.android.modules.message.dto;

import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.utils.C2555d;
import java.io.Serializable;
import java.util.Date;
import org.json.JSONObject;

public class MessageDTO implements Serializable {
    private static final long serialVersionUID = -1384512063795831984L;
    private Date availableTime;
    private String message;

    public MessageDTO(JSONObject jSONObject) {
        this.message = jSONObject.optString(AVStatus.MESSAGE_TAG);
        this.availableTime = C2555d.m12820h(jSONObject.optString("availableTime"));
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public Date getAvailableTime() {
        return this.availableTime;
    }

    public void setAvailableTime(Date date) {
        this.availableTime = date;
    }
}
