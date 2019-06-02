package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import io.rong.common.ParcelUtils;
import io.rong.imlib.NativeObject.DiscussionInfo;
import java.util.Arrays;
import java.util.List;

public class Discussion implements Parcelable {
    public static final Creator<Discussion> CREATOR = new C53751();
    private String creatorId;
    private String id;
    private boolean isOpen;
    private List<String> memberIdList;
    private String name;

    /* renamed from: io.rong.imlib.model.Discussion$1 */
    static class C53751 implements Creator<Discussion> {
        C53751() {
        }

        public Discussion createFromParcel(Parcel parcel) {
            return new Discussion(parcel);
        }

        public Discussion[] newArray(int i) {
            return new Discussion[i];
        }
    }

    public Discussion(DiscussionInfo discussionInfo) {
        boolean z = true;
        this.isOpen = true;
        this.id = discussionInfo.getDiscussionId();
        this.name = discussionInfo.getDiscussionName();
        this.creatorId = discussionInfo.getAdminId();
        if (!TextUtils.isEmpty(discussionInfo.getUserIds())) {
            this.memberIdList = Arrays.asList(discussionInfo.getUserIds().split("\n"));
        }
        Log.d("Discussion", "info.getInviteStatus():" + discussionInfo.getInviteStatus());
        if (discussionInfo.getInviteStatus() == 1) {
            z = false;
        }
        this.isOpen = z;
    }

    public Discussion(Parcel parcel) {
        boolean z = true;
        String readFromParcel = ParcelUtils.readFromParcel(parcel);
        String readFromParcel2 = ParcelUtils.readFromParcel(parcel);
        String readFromParcel3 = ParcelUtils.readFromParcel(parcel);
        if (ParcelUtils.readIntFromParcel(parcel).intValue() != 1) {
            z = false;
        }
        this(readFromParcel, readFromParcel2, readFromParcel3, z, ParcelUtils.readListFromParcel(parcel, String.class));
    }

    public Discussion(String str, String str2) {
        this.isOpen = true;
        this.id = str;
        this.name = str2;
    }

    public Discussion(String str, String str2, String str3, boolean z, List<String> list) {
        this.isOpen = true;
        this.id = str;
        this.name = str2;
        this.creatorId = str3;
        this.isOpen = z;
        this.memberIdList = list;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(String str) {
        this.creatorId = str;
    }

    public List<String> getMemberIdList() {
        return this.memberIdList;
    }

    public void setMemberIdList(List<String> list) {
        this.memberIdList = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, getId());
        ParcelUtils.writeToParcel(parcel, getName());
        ParcelUtils.writeToParcel(parcel, getCreatorId());
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(isOpen() ? 1 : 0));
        ParcelUtils.writeToParcel(parcel, getMemberIdList());
    }
}
