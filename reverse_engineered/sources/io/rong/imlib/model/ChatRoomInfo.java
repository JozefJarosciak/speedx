package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;
import java.util.List;

public class ChatRoomInfo implements Parcelable {
    public static final Creator<ChatRoomInfo> CREATOR = new C53721();
    private String chatRoomId;
    private List<ChatRoomMemberInfo> memberInfo;
    private ChatRoomMemberOrder order;
    private int totalMemberCount;

    /* renamed from: io.rong.imlib.model.ChatRoomInfo$1 */
    static class C53721 implements Creator<ChatRoomInfo> {
        C53721() {
        }

        public ChatRoomInfo createFromParcel(Parcel parcel) {
            return new ChatRoomInfo(parcel);
        }

        public ChatRoomInfo[] newArray(int i) {
            return new ChatRoomInfo[i];
        }
    }

    public enum ChatRoomMemberOrder {
        RC_CHAT_ROOM_MEMBER_ASC(1),
        RC_CHAT_ROOM_MEMBER_DESC(2);
        
        int value;

        private ChatRoomMemberOrder(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public String getChatRoomId() {
        return this.chatRoomId;
    }

    public void setChatRoomId(String str) {
        this.chatRoomId = str;
    }

    public ChatRoomMemberOrder getMemberOrder() {
        return this.order;
    }

    public void setMemberOrder(ChatRoomMemberOrder chatRoomMemberOrder) {
        this.order = chatRoomMemberOrder;
    }

    public void setUsers(List<ChatRoomMemberInfo> list) {
        this.memberInfo = list;
    }

    public void setTotalMemberCount(int i) {
        this.totalMemberCount = i;
    }

    public List<ChatRoomMemberInfo> getMemberInfo() {
        return this.memberInfo;
    }

    public void setMemberInfo(List<ChatRoomMemberInfo> list) {
        this.memberInfo = list;
    }

    public int getTotalMemberCount() {
        return this.totalMemberCount;
    }

    public ChatRoomInfo(Parcel parcel) {
        this.chatRoomId = ParcelUtils.readFromParcel(parcel);
        this.totalMemberCount = ParcelUtils.readIntFromParcel(parcel).intValue();
        this.memberInfo = ParcelUtils.readListFromParcel(parcel, ChatRoomMemberInfo.class);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.chatRoomId);
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.totalMemberCount));
        ParcelUtils.writeToParcel(parcel, this.memberInfo);
    }
}
