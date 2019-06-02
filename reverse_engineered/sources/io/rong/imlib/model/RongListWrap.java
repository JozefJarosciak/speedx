package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;
import java.util.ArrayList;
import java.util.List;

public class RongListWrap implements Parcelable {
    public static final Creator<RongListWrap> CREATOR = new C53831();
    public static Class mClass;
    private List mList = new ArrayList();

    /* renamed from: io.rong.imlib.model.RongListWrap$1 */
    static class C53831 implements Creator<RongListWrap> {
        C53831() {
        }

        public RongListWrap createFromParcel(Parcel parcel) {
            return new RongListWrap(parcel);
        }

        public RongListWrap[] newArray(int i) {
            return new RongListWrap[i];
        }
    }

    public static RongListWrap obtain(List list, Class cls) {
        return new RongListWrap(list, cls);
    }

    public RongListWrap(List list, Class cls) {
        this.mList = list;
        mClass = cls;
    }

    public RongListWrap(Parcel parcel) {
        this.mList = ParcelUtils.readListFromParcel(parcel, Message.class);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeListToParcel(parcel, this.mList);
    }

    public List getList() {
        return this.mList;
    }

    public void setList(List list) {
        this.mList = list;
    }
}
