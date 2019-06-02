package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;

public class CSHumanEvaluateItem implements Parcelable {
    public static final Creator<CSHumanEvaluateItem> CREATOR = new C54041();
    private String description = "";
    private int value;

    /* renamed from: io.rong.message.CSHumanEvaluateItem$1 */
    static class C54041 implements Creator<CSHumanEvaluateItem> {
        C54041() {
        }

        public CSHumanEvaluateItem createFromParcel(Parcel parcel) {
            return new CSHumanEvaluateItem(parcel);
        }

        public CSHumanEvaluateItem[] newArray(int i) {
            return new CSHumanEvaluateItem[i];
        }
    }

    public CSHumanEvaluateItem(int i, String str) {
        this.value = i;
        this.description = str;
    }

    public CSHumanEvaluateItem(Parcel parcel) {
        this.value = ParcelUtils.readIntFromParcel(parcel).intValue();
        this.description = ParcelUtils.readFromParcel(parcel);
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.value));
        ParcelUtils.writeToParcel(parcel, this.description);
    }
}
