package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;

public class TextObject implements Parcelable {
    public static final Creator<TextObject> CREATOR = new C43761();
    public String text;

    /* renamed from: com.sina.weibo.sdk.api.TextObject$1 */
    static class C43761 implements Creator<TextObject> {
        C43761() {
        }

        public TextObject createFromParcel(Parcel parcel) {
            return new TextObject(parcel);
        }

        public TextObject[] newArray(int i) {
            return new TextObject[i];
        }
    }

    public TextObject(Parcel parcel) {
        this.text = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.text);
    }

    public boolean checkArgs() {
        if (TextUtils.isEmpty(this.text) || this.text.length() > 1024) {
            return false;
        }
        return true;
    }

    public int getObjType() {
        return 1;
    }
}
