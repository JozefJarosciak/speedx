package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchResult implements Parcelable {
    public ERRORNO error;

    public enum ERRORNO {
        NO_ERROR,
        RESULT_NOT_FOUND,
        AMBIGUOUS_KEYWORD,
        AMBIGUOUS_ROURE_ADDR,
        NOT_SUPPORT_BUS,
        NOT_SUPPORT_BUS_2CITY,
        ST_EN_TOO_NEAR,
        KEY_ERROR,
        PERMISSION_UNFINISHED,
        NETWORK_TIME_OUT,
        NETWORK_ERROR,
        POIINDOOR_BID_ERROR,
        POIINDOOR_FLOOR_ERROR,
        POIINDOOR_SERVER_ERROR,
        INDOOR_ROUTE_NO_IN_BUILDING,
        INDOOR_ROUTE_NO_IN_SAME_BUILDING,
        MASS_TRANSIT_SERVER_ERROR,
        MASS_TRANSIT_OPTION_ERROR,
        MASS_TRANSIT_NO_POI_ERROR,
        SEARCH_SERVER_INTERNAL_ERROR,
        SEARCH_OPTION_ERROR
    }

    public SearchResult() {
        this.error = ERRORNO.NO_ERROR;
    }

    protected SearchResult(Parcel parcel) {
        int readInt = parcel.readInt();
        this.error = readInt == -1 ? null : ERRORNO.values()[readInt];
    }

    public SearchResult(ERRORNO errorno) {
        this.error = errorno;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.error == null ? -1 : this.error.ordinal());
    }
}
