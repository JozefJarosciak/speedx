package com.beastbikes.android.modules.map;

public enum MapType {
    BaiDu,
    Google,
    MapBox;

    public static MapType pareType(int i) {
        switch (i) {
            case 0:
                return BaiDu;
            case 1:
                return Google;
            case 2:
                return MapBox;
            default:
                return null;
        }
    }
}
