package com.digits.sdk.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

class AuthConfig implements Parcelable, Serializable {
    public static final Creator<AuthConfig> CREATOR = new AuthConfig$1();
    private static final long serialVersionUID = 5677912742763353323L;
    @SerializedName("email_enabled")
    public boolean isEmailEnabled;
    @SerializedName("voice_enabled")
    public boolean isVoiceEnabled;
    @SerializedName("tos_update")
    public boolean tosUpdate;

    protected AuthConfig(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.tosUpdate = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.isVoiceEnabled = z;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        this.isEmailEnabled = z2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        if (this.tosUpdate) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (this.isVoiceEnabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!this.isEmailEnabled) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthConfig authConfig = (AuthConfig) obj;
        if (this.tosUpdate == authConfig.tosUpdate && this.isVoiceEnabled == authConfig.isVoiceEnabled && this.isEmailEnabled == authConfig.isEmailEnabled) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 1;
        if (this.tosUpdate) {
            i = 1;
        } else {
            i = 0;
        }
        int i3 = i * 31;
        if (this.isVoiceEnabled) {
            i = 1;
        } else {
            i = 0;
        }
        i = (i + i3) * 31;
        if (!this.isEmailEnabled) {
            i2 = 0;
        }
        return i + i2;
    }
}
