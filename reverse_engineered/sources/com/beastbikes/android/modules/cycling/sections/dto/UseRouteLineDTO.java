package com.beastbikes.android.modules.cycling.sections.dto;

import java.io.Serializable;

public class UseRouteLineDTO implements Serializable {
    private String routeName;

    public UseRouteLineDTO(String str) {
        this.routeName = str;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public void setRouteName(String str) {
        this.routeName = str;
    }
}
