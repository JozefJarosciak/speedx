package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;

public class PoiDetailResult extends SearchResult implements Parcelable {
    public static final Creator<PoiDetailResult> CREATOR = new C1165a();
    /* renamed from: a */
    int f3379a;
    public String address;
    /* renamed from: b */
    String f3380b;
    public int checkinNum;
    public int commentNum;
    public String detailUrl;
    public double environmentRating;
    public double facilityRating;
    public int favoriteNum;
    public int grouponNum;
    public double hygieneRating;
    public int imageNum;
    public LatLng location;
    public String name;
    public double overallRating;
    public double price;
    public double serviceRating;
    public String shopHours;
    public String tag;
    public double tasteRating;
    public double technologyRating;
    public String telephone;
    public String type;
    public String uid;

    protected PoiDetailResult(Parcel parcel) {
        this.f3379a = parcel.readInt();
        this.f3380b = parcel.readString();
        this.name = parcel.readString();
        this.location = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.address = parcel.readString();
        this.telephone = parcel.readString();
        this.uid = parcel.readString();
        this.tag = parcel.readString();
        this.detailUrl = parcel.readString();
        this.type = parcel.readString();
        this.price = parcel.readDouble();
        this.overallRating = parcel.readDouble();
        this.tasteRating = parcel.readDouble();
        this.serviceRating = parcel.readDouble();
        this.environmentRating = parcel.readDouble();
        this.facilityRating = parcel.readDouble();
        this.hygieneRating = parcel.readDouble();
        this.technologyRating = parcel.readDouble();
        this.imageNum = parcel.readInt();
        this.grouponNum = parcel.readInt();
        this.commentNum = parcel.readInt();
        this.favoriteNum = parcel.readInt();
        this.checkinNum = parcel.readInt();
        this.shopHours = parcel.readString();
    }

    public PoiDetailResult(ERRORNO errorno) {
        super(errorno);
    }

    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.address;
    }

    public int getCheckinNum() {
        return this.checkinNum;
    }

    public int getCommentNum() {
        return this.commentNum;
    }

    public String getDetailUrl() {
        return this.detailUrl;
    }

    public double getEnvironmentRating() {
        return this.environmentRating;
    }

    public double getFacilityRating() {
        return this.facilityRating;
    }

    public int getFavoriteNum() {
        return this.favoriteNum;
    }

    public int getGrouponNum() {
        return this.grouponNum;
    }

    public double getHygieneRating() {
        return this.hygieneRating;
    }

    public int getImageNum() {
        return this.imageNum;
    }

    public LatLng getLocation() {
        return this.location;
    }

    public String getName() {
        return this.name;
    }

    public double getOverallRating() {
        return this.overallRating;
    }

    public double getPrice() {
        return this.price;
    }

    public double getServiceRating() {
        return this.serviceRating;
    }

    public String getShopHours() {
        return this.shopHours;
    }

    public String getTag() {
        return this.tag;
    }

    public double getTasteRating() {
        return this.tasteRating;
    }

    public double getTechnologyRating() {
        return this.technologyRating;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String getType() {
        return this.type;
    }

    public String getUid() {
        return this.uid;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3379a);
        parcel.writeString(this.f3380b);
        parcel.writeString(this.name);
        parcel.writeValue(this.location);
        parcel.writeString(this.address);
        parcel.writeString(this.telephone);
        parcel.writeString(this.uid);
        parcel.writeString(this.tag);
        parcel.writeString(this.detailUrl);
        parcel.writeString(this.type);
        parcel.writeDouble(this.price);
        parcel.writeDouble(this.overallRating);
        parcel.writeDouble(this.tasteRating);
        parcel.writeDouble(this.serviceRating);
        parcel.writeDouble(this.environmentRating);
        parcel.writeDouble(this.facilityRating);
        parcel.writeDouble(this.hygieneRating);
        parcel.writeDouble(this.technologyRating);
        parcel.writeInt(this.imageNum);
        parcel.writeInt(this.grouponNum);
        parcel.writeInt(this.commentNum);
        parcel.writeInt(this.favoriteNum);
        parcel.writeInt(this.checkinNum);
        parcel.writeString(this.shopHours);
    }
}
