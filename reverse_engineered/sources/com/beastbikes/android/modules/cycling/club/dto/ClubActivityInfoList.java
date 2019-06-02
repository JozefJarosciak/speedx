package com.beastbikes.android.modules.cycling.club.dto;

import ch.qos.logback.core.CoreConstants;
import java.io.Serializable;
import java.util.List;

public class ClubActivityInfoList implements Serializable {
    private List<ClubActivityListDTO> list;

    public List<ClubActivityListDTO> getList() {
        return this.list;
    }

    public void setList(List<ClubActivityListDTO> list) {
        this.list = list;
    }

    public String toString() {
        return "ClubActivityInfoList{list=" + this.list + CoreConstants.CURLY_RIGHT;
    }

    public ClubActivityInfoList(List<ClubActivityListDTO> list) {
        this.list = list;
    }
}
