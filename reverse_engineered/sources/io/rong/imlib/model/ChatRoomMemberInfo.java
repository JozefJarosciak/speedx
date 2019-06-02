package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import io.rong.common.ParcelUtils;

public class ChatRoomMemberInfo implements Parcelable {
    public static final Creator<ChatRoomMemberInfo> CREATOR = new C53731();
    private String id;
    private long joinTime;

    /* renamed from: io.rong.imlib.model.ChatRoomMemberInfo$1 */
    static class C53731 implements Creator<ChatRoomMemberInfo> {
        C53731() {
        }

        public ChatRoomMemberInfo createFromParcel(Parcel parcel) {
            return new ChatRoomMemberInfo(parcel);
        }

        public ChatRoomMemberInfo[] newArray(int i) {
            return new ChatRoomMemberInfo[i];
        }
    }

    public long getJoinTime() {
        return this.joinTime;
    }

    public void setJoinTime(long j) {
        this.joinTime = j;
    }

    public ChatRoomMemberInfo(Parcel parcel) {
        setUserId(ParcelUtils.readFromParcel(parcel));
        setJoinTime(ParcelUtils.readLongFromParcel(parcel).longValue());
    }

    public String getUserId() {
        if (!TextUtils.isEmpty(this.id)) {
            return this.id;
        }
        throw new NullPointerException("userId  is null");
    }

    public void setUserId(String str) {
        this.id = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, getUserId());
        ParcelUtils.writeToParcel(parcel, Long.valueOf(getJoinTime()));
    }
}
