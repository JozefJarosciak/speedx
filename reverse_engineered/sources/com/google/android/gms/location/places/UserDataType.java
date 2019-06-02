package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zzf;
import java.util.Set;

public final class UserDataType extends AbstractSafeParcelable {
    public static final zzm CREATOR = new zzm();
    public static final UserDataType afe = zzw("test_type", 1);
    public static final UserDataType aff = zzw("labeled_place", 6);
    public static final UserDataType afg = zzw("here_content", 7);
    public static final Set<UserDataType> afh = zzf.zza(afe, aff, afg);
    final int afi;
    final int mVersionCode;
    final String zzcgd;

    UserDataType(int i, String str, int i2) {
        zzab.zzhs(str);
        this.mVersionCode = i;
        this.zzcgd = str;
        this.afi = i2;
    }

    private static UserDataType zzw(String str, int i) {
        return new UserDataType(0, str, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserDataType)) {
            return false;
        }
        UserDataType userDataType = (UserDataType) obj;
        return this.zzcgd.equals(userDataType.zzcgd) && this.afi == userDataType.afi;
    }

    public int hashCode() {
        return this.zzcgd.hashCode();
    }

    public String toString() {
        return this.zzcgd;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzm.zza(this, parcel, i);
    }
}
