package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;

public class CSGroupItem implements Parcelable {
    public static final Creator<CSGroupItem> CREATOR = new C53711();
    private String id = "";
    private String name = "";
    private boolean online = false;

    /* renamed from: io.rong.imlib.model.CSGroupItem$1 */
    static class C53711 implements Creator<CSGroupItem> {
        C53711() {
        }

        public CSGroupItem createFromParcel(Parcel parcel) {
            return new CSGroupItem(parcel);
        }

        public CSGroupItem[] newArray(int i) {
            return new CSGroupItem[i];
        }
    }

    public CSGroupItem(String str, String str2, boolean z) {
        this.id = str;
        this.name = str2;
        this.online = z;
    }

    public CSGroupItem(Parcel parcel) {
        boolean z = true;
        this.id = ParcelUtils.readFromParcel(parcel);
        this.name = ParcelUtils.readFromParcel(parcel);
        if (ParcelUtils.readIntFromParcel(parcel).intValue() != 1) {
            z = false;
        }
        this.online = z;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean getOnline() {
        return this.online;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.id);
        ParcelUtils.writeToParcel(parcel, this.name);
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.online ? 1 : 0));
    }
}
