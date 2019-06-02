package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.rong.push.common.ParcelUtils;
import java.util.List;

public class MentionedInfo implements Parcelable {
    public static final Creator<MentionedInfo> CREATOR = new C53771();
    private String mentionedContent;
    private MentionedType type;
    private List<String> userIdList;

    /* renamed from: io.rong.imlib.model.MentionedInfo$1 */
    static class C53771 implements Creator<MentionedInfo> {
        C53771() {
        }

        public MentionedInfo createFromParcel(Parcel parcel) {
            return new MentionedInfo(parcel);
        }

        public MentionedInfo[] newArray(int i) {
            return new MentionedInfo[i];
        }
    }

    public enum MentionedType {
        ALL(1),
        PART(2);
        
        private int value;

        private MentionedType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static MentionedType valueOf(int i) {
            for (MentionedType mentionedType : values()) {
                if (mentionedType.getValue() == i) {
                    return mentionedType;
                }
            }
            return ALL;
        }
    }

    public MentionedInfo(Parcel parcel) {
        setType(MentionedType.valueOf(ParcelUtils.readIntFromParcel(parcel).intValue()));
        setMentionedUserIdList(ParcelUtils.readListFromParcel(parcel, String.class));
        setMentionedContent(ParcelUtils.readFromParcel(parcel));
    }

    public MentionedInfo(MentionedType mentionedType, List<String> list, String str) {
        if (mentionedType != null && mentionedType.equals(MentionedType.ALL)) {
            this.userIdList = null;
        } else if (mentionedType != null && mentionedType.equals(MentionedType.PART)) {
            if (list == null) {
                throw new IllegalArgumentException("When mentioned parts of the group memebers, userIdList can't be null!");
            }
            this.userIdList = list;
        }
        this.type = mentionedType;
        this.mentionedContent = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getType().getValue()));
        ParcelUtils.writeToParcel(parcel, getMentionedUserIdList());
        ParcelUtils.writeToParcel(parcel, getMentionedContent());
    }

    public MentionedType getType() {
        return this.type;
    }

    public List<String> getMentionedUserIdList() {
        return this.userIdList;
    }

    public String getMentionedContent() {
        return this.mentionedContent;
    }

    public void setType(MentionedType mentionedType) {
        this.type = mentionedType;
    }

    public void setMentionedUserIdList(List<String> list) {
        this.userIdList = list;
    }

    public void setMentionedContent(String str) {
        this.mentionedContent = str;
    }
}
