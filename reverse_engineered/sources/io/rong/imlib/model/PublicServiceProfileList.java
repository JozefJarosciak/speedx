package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;
import java.util.ArrayList;

public class PublicServiceProfileList implements Parcelable {
    public static final Creator<PublicServiceProfileList> CREATOR = new C53811();
    private ArrayList<PublicServiceProfile> mList;

    /* renamed from: io.rong.imlib.model.PublicServiceProfileList$1 */
    static class C53811 implements Creator<PublicServiceProfileList> {
        C53811() {
        }

        public PublicServiceProfileList createFromParcel(Parcel parcel) {
            return new PublicServiceProfileList(parcel);
        }

        public PublicServiceProfileList[] newArray(int i) {
            return new PublicServiceProfileList[i];
        }
    }

    public PublicServiceProfileList(ArrayList<PublicServiceProfile> arrayList) {
        this.mList = arrayList;
    }

    public PublicServiceProfileList(Parcel parcel) {
        this.mList = ParcelUtils.readListFromParcel(parcel, PublicServiceProfile.class);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeListToParcel(parcel, this.mList);
    }

    public ArrayList<PublicServiceProfile> getPublicServiceData() {
        return this.mList;
    }
}
