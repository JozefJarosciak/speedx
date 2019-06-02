package com.beastbikes.android.modules.train.dto;

import java.io.Serializable;
import org.json.JSONObject;

public class FtpDTO implements Serializable {
    private long date;
    private int ftp;
    private int id;

    public FtpDTO(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.id = jSONObject.optInt("id");
            this.ftp = jSONObject.optInt("ftp");
            this.date = jSONObject.optLong("get_start_date");
        }
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getFtp() {
        return this.ftp;
    }

    public void setFtp(int i) {
        this.ftp = i;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long j) {
        this.date = j;
    }
}
