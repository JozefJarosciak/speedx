package com.baidu.mapapi.map;

import java.util.ArrayList;

public final class MapBaseIndoorMapInfo {
    /* renamed from: d */
    private static final String f2962d = MapBaseIndoorMapInfo.class.getSimpleName();
    /* renamed from: a */
    String f2963a;
    /* renamed from: b */
    String f2964b;
    /* renamed from: c */
    ArrayList<String> f2965c;

    public enum SwitchFloorError {
        SWITCH_OK,
        FLOOR_INFO_ERROR,
        FLOOR_OVERLFLOW,
        FOCUSED_ID_ERROR,
        SWITCH_ERROR
    }

    public MapBaseIndoorMapInfo(MapBaseIndoorMapInfo mapBaseIndoorMapInfo) {
        this.f2963a = mapBaseIndoorMapInfo.f2963a;
        this.f2964b = mapBaseIndoorMapInfo.f2964b;
        this.f2965c = mapBaseIndoorMapInfo.f2965c;
    }

    public MapBaseIndoorMapInfo(String str, String str2, ArrayList<String> arrayList) {
        this.f2963a = str;
        this.f2964b = str2;
        this.f2965c = arrayList;
    }

    public String getCurFloor() {
        return this.f2964b;
    }

    public ArrayList<String> getFloors() {
        return this.f2965c;
    }

    public String getID() {
        return this.f2963a;
    }
}
